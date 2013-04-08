package com.rootls.service.impl;

import com.rootls.bean.*;
import com.rootls.model.Contract;
import com.rootls.model.Guest;
import com.rootls.repository.BillRepository;
import com.rootls.repository.LinkmanRepository;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import com.rootls.model.Bill;
import com.rootls.model.Contract;
import com.rootls.model.Guest;
import com.rootls.model.LinkMan;
import com.rootls.repository.BillRepository;
import com.rootls.repository.ContractRepository;
import com.rootls.repository.GuestRepository;
import com.rootls.repository.LinkmanRepository;
import com.rootls.service.BillService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.rootls.bean.Config.UPLOAD_PATH;
import static com.rootls.bean.Config.importLog;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.isNumeric;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-22
 * Time: 下午3:04
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BillServiceImpl implements BillService {

    Logger logger = Logger.getLogger(BillServiceImpl.class);

    @Resource
    BillRepository billRepository;

    @Resource
    LinkmanRepository linkmanRepository;

    @Resource
    ContractRepository contractRepository;

    @Resource
    GuestRepository guestRepository;


    @Override
    public List<Bill> getList(SearchBean searchBean, Page page) {

        Integer pageNumber = page.getPageNumber();
        Integer pageSize = page.getPageSize();
        String defaultOrder = page.getOrder() != null ? page.getOrder() : "id desc";

        String sql = getQuerySql(searchBean, pageNumber, pageSize, defaultOrder);

        List<Bill> billList = billRepository.list(sql);

        return billList;
    }

    @Override
    public String upload(MultipartFile file) {
        return UploadUtil.upload(file, Config.UPLOAD_PATH);
    }

    @Override
    public void importExl(String uploadPath) {

        try {
            //从excel中取得List
            Workbook workbook = Workbook.getWorkbook(new File(uploadPath));
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();
            int rowIdx = sheet.findCell(Config.EXL_NAME).getRow() + 1;

            //遍历excel
            for (; rowIdx < rows; rowIdx++) {

                //获值,保存
                try {
                    Bill bill = new BillExlMapper(rowIdx, new Bill(), sheet).getMappingInstance();
                    save(rowIdx+1, bill);
                } catch (Exception e) {
                    Config.importLog.add(e.getMessage());
                    logger.info(e.getMessage());
                }

            }
        } catch (BiffException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public Page<Bill> list(Page page, SearchBean searchBean) {

        Integer pageNumber = page.getPageNumber();
        Integer pageSize = page.getPageSize();
        String defaultOrder = page.getOrder() != null ? page.getOrder() : "id desc";

        String countSql = getCountSql(searchBean);
        String sql = getQuerySql(searchBean, pageNumber, pageSize, defaultOrder);

        Integer totalCount = billRepository.queryForInt(countSql);

        List<Bill> billList = billRepository.list(sql);

        return new Page<Bill>(pageNumber, pageSize, totalCount, defaultOrder, billList);
    }

    @Override
    public void updateBill(Bill bill) {

        Guest guest = guestRepository.findByGid(bill.getGid());
        Bill dbBill = billRepository.findByReceipt(bill.getReceipt());
        if(guest==null){
            String log = "合同编号为:" + bill.getHtbianhao() + "的合同找不到对应的用户";
            throw new RuntimeException(log);
        }
        LinkMan linkMan = linkmanRepository.findByGidAndType2(guest.getId());

        StringBuilder updateSql = new StringBuilder("");

        //更新电话与手机
        updatePhoneAndMobile(bill, guest, dbBill, linkMan, updateSql,null);

        String sql = "update guestbook_ht_receipt set gid=gid "+appendUpdateSql(bill, dbBill, updateSql)
                +" where receipt='"+bill.getReceipt()+"' ";

        //更新发票
        billRepository.updateByReceipt(sql);

        //更新联系人
        linkmanRepository.updateLinkMan(bill,dbBill);
    }

    /**
     * 保存发票
     *
     * @param bill
     * @return
     */
    private Bill save(Integer rowIdx, Bill bill) {

        //检查是否存在此用户
        Guest guest = guestRepository.findByName(bill.getName());
        if (guest == null) {
            String log = "第" + rowIdx + "行,用户名为:" + bill.getName() + " 的用户不存在！";
            throw new RuntimeException(log);
        }

        //检查是否存在此合同
        Contract contract = contractRepository.findByHtbianhao(bill.getHtbianhao());
        if (contract == null) {
            String log = "第" + rowIdx + "行,合同编号为:" + bill.getHtbianhao() + " 的合同不存在！";
            throw new RuntimeException(log);
        }

        //检查是否存在此发票号
        Bill dbBill = billRepository.findByReceipt(bill.getReceipt());
        if (dbBill == null) {
            String log = "第" + rowIdx + "行,发票号为:" + bill.getReceipt() + " 的发票不存在！";
            throw new RuntimeException(log);
        }

        //根据合同编号找出的id是否与用户guest的id相同
        if (!contract.getGuestId().equals(guest.getId())) {
            String log = "第" + rowIdx + "行,合同编号与用户名不匹配！";
            throw new RuntimeException(log);
        }

        //检查是否存此联系人
        LinkMan linkMan = linkmanRepository.findByGidAndType2(guest.getId());
        if (linkMan == null) {
            String log = "第" + rowIdx + "行,用户名对应的联系人不存在！";
            throw new RuntimeException(log);
        }

        StringBuilder updateSql = new StringBuilder("");

        //更新电话号码与手机号
        updatePhoneAndMobile(bill, guest, dbBill, linkMan, updateSql,rowIdx);


        String sql = "update guestbook_ht_receipt set gid=gid "+appendUpdateSql(bill, dbBill, updateSql)+" where receipt='"+bill.getReceipt()+"' ";
        int result = billRepository.updateByReceipt(sql);

        updateContract(bill, contract);


        if(result!=0){
            return bill;
        }
        return null;
    }


    /**
     * 更新合同
     * @param bill
     * @param contract
     */
    private void updateContract(Bill bill, Contract contract) {
        //更新款项类型
        StringBuilder contactSql = new StringBuilder("");
        if (bill.getKuanxianglx() != null) {
            contactSql.append(",kuanxianglx=" + bill.getKuanxianglx());
        }
        //更新到账日期
        if (contract.checkDaozhangtime(bill.getDaoZhangTime())) {
//            contractRepository.updateDaoZhangTime(bill.getDaoZhangTime(), bill.getHtbianhao());
            contactSql.append(",daozhangtime='"+bill.getDaoZhangTime()+"' ");
        }
        String htsql="update guestbook_ht set id=id "+contactSql.toString()+"  where htbianhao='"+bill.getHtbianhao()+"'";
        contractRepository.update(htsql);
    }





    /**
     * 更新电话号码与手机号
     * @param bill
     * @param guest
     * @param dbBill
     * @param linkMan
     * @param updateSql
     */
    private void updatePhoneAndMobile(Bill bill, Guest guest, Bill dbBill,
                                      LinkMan linkMan, StringBuilder updateSql,Integer rowIdx) {
        //更新电话号
        if (isNotBlank(bill.getSendPhone())
                && bill.getSendPhone().length() > 10
                && !bill.getSendPhone().equals(dbBill.getSendPhone())
                && !bill.getSendPhone().equals(linkMan.getFormatPhone()) ) {

            updateSql.append(" ,send_phone='" + bill.getSendPhone() + "' ");
            linkmanRepository.updateFormatPhoneByGidAndType2(guest.getId(), bill.getSendPhone());
        }
        //更新手机
        if (isNotBlank(bill.getSendMobile())
                && bill.getSendMobile().length() > 10
                && (bill.getSendMobile().startsWith("013")
                || bill.getSendMobile().startsWith("014")
                || bill.getSendMobile().startsWith("015")
                || bill.getSendMobile().startsWith("018") )
                && !bill.getSendMobile().equals(dbBill.getSendMobile())
                && !bill.getSendMobile().equals(linkMan.getFormatPhone()) ) {

            updateSql.append(" ,send_mobile='" + bill.getSendMobile() + "' ");
            linkmanRepository.updateFormatMobileByGidAndType2(guest.getId(), bill.getSendMobile());
        }
    }


    /**
     * 拼接更新sql
     * @param bill
     * @param dbBill
     * @param updateSql
     * @return
     */
    private String appendUpdateSql(Bill bill, Bill dbBill, StringBuilder updateSql) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //更新开票时间
        if (bill.getReceiptdate() != null) {
            String receiptDate = dateFormat.format(bill.getReceiptdate());
            String dbReceiptDate = dbBill.getReceiptdate() == null ? null
                    : dateFormat.format(dbBill.getReceiptdate());
            if (!receiptDate.equals(dbReceiptDate)) {
                updateSql.append(" ,receiptdate='" + receiptDate + "' ");
            }
        }
        //更新邮寄时间
        if (bill.getSendTime()!=null) {
            String sendTime = dateFormat.format(bill.getSendTime());
            String dbSendTime = dbBill.getSendTime() == null ? null
                    : dateFormat.format(dbBill.getSendTime());
            if (!sendTime.equals(dbSendTime)) {
                updateSql.append(" ,send_time='" + sendTime + "' ");
            }
        }
        //更新收入项目
        if (bill.getShouruxianmu() != null) {
            updateSql.append(" ,shouruxianmu='" + bill.getShouruxianmu() + "' ");
        }
        //更新发票金额
        if (bill.getReceiptsum() != null) {
            updateSql.append(" ,receiptsum='" + bill.getReceiptsum() + "' ");
        }
        //更新邮寄状态
        if (bill.getSendtype() != null) {
            updateSql.append(" ,send_type=" + bill.getSendtype() + " ");
        }
        //更新发票状态
        if (bill.getReceipttype() != null) {
            updateSql.append(" ,receipttype=" + bill.getReceipttype() + " ");
        }
        //更邮寄地址
        if (isNotBlank(bill.getSendAddr())) {
            updateSql.append(" ,send_addr='" + bill.getSendAddr() + "' ");
        }
        //更新联系人
        if (isNotBlank(bill.getSendLxr())) {
            updateSql.append(" ,send_lxr='" + bill.getSendLxr() + "' ");
        }
        //更新邮编
        if (isNotBlank(bill.getSendZip())) {
            updateSql.append(" ,send_zip='" + bill.getSendZip() + "' ");
        }
        //更新邮寄内容
        if (isNotBlank(bill.getSendContent())) {
            updateSql.append(" ,send_content='" + bill.getSendContent() + "' ");
        }
        return updateSql.toString();
    }



    /**
     * 取得计数sql
     *
     * @param searchBean
     * @return
     */
    private String getCountSql(SearchBean searchBean) {
        String countSql = "select count(id) from v_ht_receipt where 1=1 %s";
        return String.format(countSql, appendWhere(searchBean).toString());
    }



    /**
     * 取得查询sql
     *
     * @param searchBean
     * @param pageNumber
     * @param pageSize
     * @param defaultOrder
     * @return
     */
    private String getQuerySql(SearchBean searchBean, Integer pageNumber,
                               Integer pageSize, String defaultOrder) {

        int startRow = (pageNumber - 1) * pageSize;
        int endRow = pageNumber * pageSize;

        String sql = "select * from (select * ,ROW_NUMBER() over(order by " + defaultOrder + " ) as row_number " +
                "from v_ht_receipt where 1=1 %s) receipt " +
                "where row_number >" + startRow + " and row_number <= " + endRow + " order by " + defaultOrder;

        return String.format(sql, appendWhere(searchBean).toString());
    }



    /**
     * 构建查询条件
     *
     * @param searchBean
     * @return
     */
    private StringBuilder appendWhere(SearchBean searchBean) {

        String receipt = searchBean.getReceipt();
        String htbianhao = searchBean.getHtbianhao();
        String startreceiptdate = searchBean.getStartReceiptdate();
        String endreceiptdate = searchBean.getEndReceiptdate();
        String recieptsum = searchBean.getRecieptsum();
        Integer receipttype = searchBean.getReceipttype();

        String sendAddr = searchBean.getSendAddr();
        String sendLxr = searchBean.getSendLxr();
        String sendPhone = searchBean.getSendPhone();
        String sendMobile = searchBean.getSendMobile();
        String sendTime = searchBean.getSendTime();
        String sendContent = searchBean.getSendContent();

        String qymc = searchBean.getQymc();
        String name = searchBean.getName();
        Integer trackId = searchBean.getTrackId();

        Integer like = searchBean.getLike();

        Integer guestType = searchBean.getGuestType();

        StringBuilder whereSql = new StringBuilder();

        if (receipttype != null) {
            whereSql.append(" and receipttype=" + receipttype.intValue());
        }
        if (isNotBlank(startreceiptdate)) {
            whereSql.append(" and CONVERT(CHAR(10), startreceiptdate, 23)>='" + startreceiptdate.trim() + "' ");
        }
        if (isNotBlank(endreceiptdate)) {
            whereSql.append(" and CONVERT(CHAR(10), endreceiptdate, 23)<'" + endreceiptdate.trim() + "' ");
        }
        if (trackId != null) {
            whereSql.append(" and trackId= " + trackId.intValue());
        }

        if (guestType != null && !guestType.equals(0)) {
            if (guestType.equals(1)) {
                whereSql.append(" and trial=0 and endtime >= getdate() ");
            }
            if (guestType.equals(2)) {
                whereSql.append(" and trial=0 and endtime < getdate() ");
            }
            if (guestType.equals(3)) {
                whereSql.append(" and trial=1 and endtime >= getdate() ");
            }
            if (guestType.equals(4)) {
                whereSql.append(" and trial=1 and endtime < getdate() ");
            }
        }

        if (isNotBlank(sendTime)) {
            whereSql.append(" and CONVERT(CHAR(10), endreceiptdate, 23) = '" + sendTime.trim() + "' ");
        }
        if (isNotBlank(recieptsum)) {
            String[] sumArr = recieptsum.trim().split("-");
            if (sumArr.length < 2 && isNumeric(sumArr[0])) {
                whereSql.append(" and recieptsum= " + Float.valueOf(sumArr[0]));
            }
            if (sumArr.length == 2 && isNumeric(sumArr[0]) && isNumeric(sumArr[1])) {
                whereSql.append(" and recieptsum> " + Float.valueOf(sumArr[0]));
                whereSql.append(" and recieptsum< " + Float.valueOf(sumArr[1]));
            }
        }

        if (like.equals(0)) {
            appendEq(receipt, htbianhao, sendAddr, sendLxr, sendPhone, sendMobile, sendContent, qymc, name, whereSql);
        }
        if (like.equals(1)) {
            appendLike(receipt, htbianhao, sendAddr, sendLxr, sendPhone, sendMobile, sendContent, qymc, name, whereSql);
        }

        return whereSql;
    }



    private void appendEq(String receipt, String htbianhao, String sendAddr, String sendLxr, String sendPhone, String sendMobile,
                          String sendContent, String qymc, String name, StringBuilder whereSql) {
        if (isNotBlank(receipt)) {
            whereSql.append(" and receipt='" + receipt.trim() + "' ");
        }
        if (isNotBlank(htbianhao)) {
            whereSql.append(" and htbianhao='" + htbianhao.trim() + "' ");
        }
        if (isNotBlank(sendAddr)) {
            whereSql.append(" and sendAddr= '" + sendAddr.trim() + "' ");
        }
        if (isNotBlank(sendLxr)) {
            whereSql.append(" and sendLxr= '" + sendLxr.trim() + "' ");
        }
        if (isNotBlank(sendPhone)) {
            whereSql.append(" and sendPhone= '" + sendPhone.trim() + "' ");
        }
        if (isNotBlank(sendMobile)) {
            whereSql.append(" and sendMobile= '" + sendMobile.trim() + "' ");
        }
        if (isNotBlank(sendContent)) {
            whereSql.append(" and sendContent= '" + sendContent.trim() + "' ");
        }
        if (isNotBlank(qymc)) {
            whereSql.append(" and qymc= '" + qymc.trim() + "' ");
        }
        if (isNotBlank(name)) {
            whereSql.append(" and name= '" + name.trim() + "' ");
        }
    }



    private void appendLike(String receipt, String htbianhao, String sendAddr, String sendLxr, String sendPhone, String sendMobile,
                            String sendContent, String qymc, String name, StringBuilder whereSql) {
        if (isNotBlank(receipt)) {
            whereSql.append(" and receipt like '%" + receipt.trim() + "%'");
        }
        if (isNotBlank(htbianhao)) {
            whereSql.append(" and htbianhao like '%" + htbianhao.trim() + "%'");
        }
        if (isNotBlank(sendAddr)) {
            whereSql.append(" and sendAddr like '%" + sendAddr.trim() + "%'");
        }
        if (isNotBlank(sendLxr)) {
            whereSql.append(" and sendLxr like '%" + sendLxr.trim() + "%'");
        }
        if (isNotBlank(sendPhone)) {
            whereSql.append(" and sendPhone like '%" + sendPhone.trim() + "%'");
        }
        if (isNotBlank(sendMobile)) {
            whereSql.append(" and sendMobile like '%" + sendMobile.trim() + "%'");
        }
        if (isNotBlank(sendContent)) {
            whereSql.append(" and sendContent like '%" + sendContent.trim() + "%'");
        }
        if (isNotBlank(qymc)) {
            whereSql.append(" and qymc like '%" + qymc.trim() + "%'");
        }
        if (isNotBlank(name)) {
            whereSql.append(" and name like '%" + name.trim() + "%'");
        }
    }
}
