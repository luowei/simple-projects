package com.rootls.bean;

import jxl.Sheet;
import com.rootls.model.Bill;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-22
 * Time: 下午5:51
 * To change this template use File | Settings | File Templates.
 */
public class BillExlMapper {

    Sheet sheet;
    int rowIdx;
    Bill bill;

    public BillExlMapper() {
    }


    public BillExlMapper(int rowIdx, Bill bill, Sheet sheet) {
        this.sheet = sheet;
        this.rowIdx = rowIdx;

        bill.setReceipt(getContents(EXL_RECEIPT));
        bill.setName(getContents(EXL_NAME));
        bill.setHtbianhao(getContents(EXL_HTBIANHAO));
        bill.setShouruxianmu(getContents(EXL_SHOURUXIANMU));
        bill.setReceiptsum(getFloat(rowIdx, EXL_RECEIPTSUM));
        bill.setReceiptdzdate(getDate(rowIdx, EXL_RECEIPTDZDATE));
        bill.setKuanxianglx(getInteger(rowIdx, EXL_KUANXIANGLX));
        bill.setReceipttype(getInteger(rowIdx, EXL_RECEIPTTYPE));
        bill.setReceiptdate(getDate(rowIdx, EXL_RECEIPTDATE));

        bill.setSendAddr(getContents(EXL_SENDADDR));
        bill.setSendLxr(getContents(EXL_SENDLXR));
        bill.setSendPhone(getContents(EXL_SENDPHONE));
        bill.setSendMobile(getContents(EXL_SENDMOBILE));
        bill.setSendTime(getDate(rowIdx, EXL_SENDTIME));
        bill.setSendContent(getContents(EXL_SENDCONTENT));

        this.bill = bill;
    }

    private String getContents(String name) {
        if (name == null || name.equals(""))
            return null;

        String val = sheet.getCell(sheet.findCell(name).getColumn(), rowIdx).getContents().trim();
        return val == null ? "" : val.trim();
    }


    private Integer getInteger(int rowIdx, String name) {
        String content = getContents(name);

        //如果是款项类型
        if(name.equals(EXL_KUANXIANGLX)){
            if ("应收".equals(content)){
                return 1;
            }else {
                return null;
            }
        }
        //如果是发票状态
        if(name.equals(EXL_RECEIPTTYPE)){
            if ("未寄出".equals(content)){
                return 0;
            }else if ("已寄出".equals(content)){
                return 1;
            }else if ("已到达".equals(content)){
                return 2;
            }else if ("退回".equals(content)){
                return -1;
            }else {
                return null;
            }
        }

        try {
            return Integer.valueOf(content);
        } catch (Exception e) {
            String log = "第" + rowIdx + "行," + name + " 的值d格式不正确，类型转换失败！";
            throw new RuntimeException(log);
        }

    }

    private Float getFloat(int rowIdx, String name) {
        String content = getContents(name);

        try {
            return Float.valueOf(content);
        } catch (Exception e) {
            String log = "第" + rowIdx + "行," + name + " 的值d格式不正确，类型转换失败！";
            throw new RuntimeException(log);
        }
    }

    private Date getDate(int rowIdx, String name) {

        String content = getContents(name);

        if (isBlank(content)) {
            return null;
        }
        if (content.length() != 10) {
            String log = "第" + rowIdx + "行," + name + " 的值格式不正确,不是'2013-03-01'日期格式！";
            throw new RuntimeException(log);
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(content);
        } catch (Exception e) {
            String log = "第" + rowIdx + "行," + name + " 的值格式不正确，类型转换失败！";
            throw new RuntimeException(log);
        }
    }


    public Bill getMappingInstance() {
        return bill;
    }
}
