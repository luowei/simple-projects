package com.rootls.service.impl;

import com.rootls.model.*;
import com.rootls.repository.CallRepository;
import com.rootls.repository.SomeRepository;
import com.rootls.service.CallService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.rootls.bean.Config.*;
import static com.rootls.model.CallResponse.CallLog;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-4-16
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CallSerivceImpl implements CallService {

    Logger logger = LoggerFactory.getLogger(CallSerivceImpl.class);

    @Resource
    CallRepository callRepository;

    @Resource
    SomeRepository someRepository;


    //日期
    Date date = new Date();

    //每次提前3秒钟钟执行任务
    long three_second = 3000;

    //定时器
    Timer timer = new Timer();

    //启动定时任务
    public void runTask() {
        logger.info("=========== start get call log task ===============");
        timer.schedule(new TargetTask(), 0);
    }

    //停止定时任务
    public void stopTask() {
        logger.info("=========== stop get call log task ==============");
        timer.cancel();
    }

    private class TargetTask extends TimerTask {
        @Override
        public void run() {
            date.setTime(getCallLog(null, "auto") - three_second);
//            timer.purge();
            timer.schedule(new TargetTask(), date);

            logger.info("========== get call log ==============");

        }
    }


    /**
     * 返回等待 GETCALLLOG_IDEL 毫秒执行一次任务
     *
     * @param callRequest
     * @return
     */
    public Long getCallLog(CallRequest callRequest, String type) {

        ObjectMapper mapper = new ObjectMapper();
        CallResponse callResponse = null;
        List<CallLog> callLogList = null;
        try {

            int id = someRepository.getRequestCallId().getCallId();

            //组织请求参数
            String params = "?UserName=" + USERNAME + "&UserPassword=" + PASSWORD;
            String url = "";
            if (type.equals("auto")) {
                Integer callId = 0;
                if(callId==null || callId.equals(0)){
                    callId = Integer.valueOf(STARTID);
                }
                params = params + "&ID=" + callId;
                url = CALL_LIST_URL + params;
            } else {
                url = CALL_LIST_URL + params + callRequest.toParams();
            }

            callResponse = mapper.readValue(new URL(url), CallResponse.class);

            callLogList = callResponse.getCallLogList();
            for (CallLog callLog : callLogList) {

                //判断这条记录是否存在
                CallLog callLogDB = callRepository.exsitCallLog(callLog, getTableName(callLog));

                //根据我方电话找出admin
//                String adminPhone = isBlank(callLog.getExtCode()) ? null : "05332591" + callLog.getExtCode();
                Admin admin = someRepository.findAdminByExtCode(callLog.getExtCode());

                //根据对方电话找出联系人信息
                LinkMan linkMan = someRepository.findLinkManByPhoneOrMobile(callLog.getOtherCode());

                //根据对方电话找出客户
                Guest guest = null;
                if (linkMan != null && linkMan.getGid() != null) {
                    guest = someRepository.findGuestById(linkMan.getGid());
                }

                //根据对方电话找出我的客户信息
                MyCustomer myCustomer = someRepository.findMyCustomerByPhoneOrMobile(callLog.getOtherCode());

                //根据对方电话找出信息源信息
                InfoSource infoSource = someRepository.findInfoSourceByPhoneOrMobile(callLog.getOtherCode());

                //构建其它电话信息
                PhoneInfo phoneInfo = getPhoneInfo(callLog);

                String sql = null;
                if (callLogDB == null) {

                    try {
                        sql = getSaveSql(callLog, admin, guest, linkMan, myCustomer, infoSource, phoneInfo);
                        callRepository.save(sql);
                    } catch (Exception e) {
                        logger.error("\n===========insert sql faild !=============\n" + sql + "\n================================\n");
                        logger.error(e.getMessage(),e);
                    }

                } else {
                    try {
                        sql = update(callLogDB, callLog, admin, guest, linkMan, myCustomer, infoSource, phoneInfo);
                        callRepository.update(sql);
                    } catch (Exception e) {
                        logger.error("\n===========update sql faild !=============\n" + sql + "\n================================\n");
                        logger.error(e.getMessage(),e);
                    }
                }

            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (callLogList != null && callLogList.size() > 0) {
                Integer lastId = callLogList.get(callLogList.size() - 1).getId();
                someRepository.saveLastCallId(lastId);
            }
        }

        Date date = new Date();
        date.setTime(date.getTime() + GETCALLLOG_IDEL);
        return date.getTime();
    }


    private String update(CallLog callLogDB, CallLog callLog, Admin admin, Guest guest, LinkMan linkMan,
                          MyCustomer myCustomer, InfoSource infoSource, PhoneInfo phoneInfo) {
        String sql = "update " + getTableName(callLog) + " set PL_Log_ID=PL_Log_ID";

        if ((callLogDB.getId() == null || callLogDB.getId().equals(0)) && admin!=null && admin.getId()!=null) {
            sql = sql + ",PL_adminid=" + admin.getId();
        }
        if ((callLogDB.getAdminName() == null || callLogDB.getAdminName().equals("")) && admin!=null && admin.getId()!=null) {
            sql = sql + " ,PL_adminName='" + admin.getName() + "'";
        }
        if ((callLogDB.getAdminPhone() == null || callLogDB.getAdminPhone().equals("")) && admin!=null && admin.getId()!=null) {
            sql = sql + ",PL_adminphone='" + getMyPhone(callLog, admin) + "'";
        }
        if ((callLogDB.getGuestBookId() == null || callLogDB.getGuestBookId().equals(0)) && guest!=null && guest.getId()!=null) {
            sql = sql + ",PL_Guestbookid=" + guest.getId();
        }
        if (callLogDB.getOtherCode() == null && callLogDB.getOtherCode().equals("")) {
            String otherCode = getOtherCode(callLog);
            sql = sql + ",PL_GuestbookPhone='" + otherCode + "'";
        }
        if ((callLogDB.getInfoSourceId() == null || callLogDB.getInfoSourceId().equals(0) ) && infoSource!=null && infoSource.getId()!=0) {
            sql = sql + ",PL_ET_infoSources=" + infoSource.getId();
        }
        if ((callLogDB.getGclx() == null || callLogDB.getGclx().equals(0) ) && linkMan!=null && linkMan.getGCLX()!=0) {
            sql = sql + ",PL_GC_LX=" + linkMan.getGCLX() + "'";
        }
        if ((callLogDB.getLX() == null || callLogDB.getLX().equals("") ) && phoneInfo!=null && !phoneInfo.getLX().equals("")) {
            sql = sql + ",PL_LX='" + phoneInfo.getLX() + "'";
        }
        if (callLogDB.getConnectDateTime() == null || callLogDB.getConnectDateTime().equals(0)) {
            sql = sql + ",PL_PhoneUse='" + callLog.getConnectTimeLen() + "'";
        }
        if ((callLogDB.getPhoneFrom() == null || callLogDB.getPhoneFrom().equals("")) && phoneInfo!=null && !phoneInfo.getPhoneFrom().equals("")) {
            sql = sql + ",PL_PhoneFrom='" + phoneInfo.getPhoneFrom() + "'";
        }
        if (callLogDB.getCallResult() == null) {
            Integer callResult = getCallResult(callLog);
            sql = sql + ",PL_State=" + callResult;
        }
        if (callLogDB.getExtCode() == null || callLogDB.getExtCode().equals("")) {
            sql = sql + ",PL_ExtNo='" + callLog.getExtCode() + "'";
        }
        if ((callLogDB.getMyCustomerId() == null || callLogDB.getMyCustomerId().equals(0) ) && myCustomer!=null && myCustomer.getId()!=0) {
            sql = sql + ",PL_MyCustomerID=" + myCustomer.getId();
        }
        if (callLogDB.getCallDateTime() == null) {
            sql = sql + ",PL_Calltime=" + callLog.getCallDateTime();
        }
        if (callLogDB.getDisconnectTime() == null) {
            sql = sql + ",PL_EndTime=" + callLog.getDisconnectTime();
        }
        if (callLogDB.getRecordFile() == null || callLogDB.getRecordFile().equals("")) {
            sql = sql + ",PL_RecordFile='" + callLog.getRecordFile() + "'";
        }
        sql = sql + " where PL_CallSessionID='" + callLog.getSessionID()+"'";

        return sql;

    }

    private Integer getCallResult(CallLog callLog) {
        Integer callResult = callLog.getCallResult();
        if (callResult != null && callResult.equals(1)) {
            callResult = 999;
        }
        return callResult == null ? 0 : callResult;
    }

    private String getMyPhone(CallLog callLog, Admin admin) {
        if (admin == null) {
            String extCode = callLog.getExtCode();
            return "05332591" + extCode;
        }
        String myPhone = admin.getPhone();

        if (myPhone == null && callLog.getExtCode().trim().length() == 3) {
            myPhone = "05332591" + callLog.getExtCode();
        }
        return myPhone;
    }

    private String getOtherCode(CallLog callLog) {
        String otherCode = callLog.getOtherCode();
        if (otherCode.length() == 3) {
            otherCode = "05332591" + otherCode;
        }
        if (otherCode.length() == 7) {
            otherCode = "0533" + otherCode;
        }
        if (otherCode.startsWith("13") || otherCode.startsWith("14") ||
                otherCode.startsWith("15") || otherCode.startsWith("18")) {
            otherCode = "0" + otherCode;
        }
        return otherCode == null ? "" : otherCode;
    }


    private PhoneInfo getPhoneInfo(CallLog callLog) {
        PhoneInfo phoneInfo = new PhoneInfo();

        //根据手机号得到归属地
        PhoneInfo phoneInfoDB = someRepository.getPhoneInfoByMobile(callLog.getOtherCode());
        phoneInfo.setPhoneFrom(phoneInfoDB == null ? "" : phoneInfoDB.getPhoneFrom());

        //根据电话或手机得出类型
        phoneInfo.setLX(getLX(callLog.getOtherCode(), (phoneInfoDB == null ? "" : phoneInfoDB.getArea())));

        //设置是否是手机号
        phoneInfo.setMobile(getIsMobile(callLog.getOtherCode()));

        return phoneInfo;
    }

    private String getLX(String otherCode, String area) {
        if (area != null && !area.equals("") && !area.contains("淄博")) {
            return "国内长途";
        }
        if (!otherCode.startsWith("0533") || /*!otherCode.startsWith("05332591")*/
                otherCode.length() != 3 || otherCode.startsWith("13") || otherCode.startsWith("14") ||
                otherCode.startsWith("15") || otherCode.startsWith("18")) {
            return "国内长途";
        }

        return "本地";
    }

    private String getSaveSql(CallLog callLog, Admin admin, Guest guest, LinkMan linkMan,
                              MyCustomer myCustomer, InfoSource infoSource, PhoneInfo phoneInfo) {
        String sql = "insert into " + getTableName(callLog) + "(PL_Log_ID,PL_PhoneTime,PL_adminid,PL_adminName,PL_adminphone," +
                "PL_Guestbookid,PL_GuestbookPhone,PL_ET_infoSources,PL_GC_LX,PL_LX,PL_PhoneUse,PL_Phonefare,PL_PhoneFrom," +
                "PL_IsMobile,PL_State,PL_ExtNo,PL_MyCustomerID,PL_CallSessionID,PL_Calltime,PL_EndTime,PL_RecordFile) " +
                " values(%s)";

        String empty = "";
        String zero = "0";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String callTime = callLog.getCallDateTime() == null ? empty : df.format(callLog.getCallDateTime());
        String connectTime = callLog.getConnectDateTime() == null ? callTime : df.format(callLog.getConnectDateTime());

        String value = "0,'" +
                (callLog == null ? empty : connectTime) + "'," +
                (admin == null ? zero : (admin.getId() == null ? zero : admin.getId())) + ",'" +
                (admin == null ? empty : (admin.getName() == null ? empty : admin.getName())) + "','" +
                getMyPhone(callLog, admin) + "'," +
                (guest == null ? zero : (guest.getId() == null ? zero : callLog.getId())) + ",'" +
                getOtherCode(callLog) + "'," +
                (infoSource == null ? zero : infoSource.getId()) + "," +
                (linkMan == null ? zero : linkMan.getGCLX()) + ",'" +
                (phoneInfo == null ? empty : (phoneInfo.getLX() == null ? empty : phoneInfo.getLX())) + "'," +
                (callLog == null ? zero : callLog.getConnectTimeLen()) +
                ",0,'" +
                (phoneInfo == null ? empty : (phoneInfo.getPhoneFrom() == null ? empty : phoneInfo.getPhoneFrom())) + "'," +
                (phoneInfo == null ? zero : phoneInfo.getIsMobile()) + "," +
                getCallResult(callLog) + ",'" +
                (callLog.getExtCode() == null ? "" : (callLog.getExtCode() == null ? empty : callLog.getExtCode())) + "'," +
                (myCustomer == null ? zero : myCustomer.getId()) + ",'" +
                (callLog.getSessionID() == null ? empty : callLog.getSessionID()) + "','" +
                (callLog.getCallDateTime() == null ? empty : df.format(callLog.getCallDateTime())) + "','" +
                callTime + "','" +
                (callLog.getRecordFile() == null ? empty : callLog.getRecordFile().replace("D:\\RecordFiles\\","").replace("\\","/")) + "'";

        return String.format(sql, value);
    }

    /**
     * 判断是不是手机号,0：不是，1：是
     *
     * @param otherCode
     * @return
     */
    private Integer getIsMobile(String otherCode) {
        if (otherCode.startsWith("0") && (
                otherCode.startsWith("013") || otherCode.startsWith("014") ||
                        otherCode.startsWith("015") || otherCode.startsWith("018")
        ) ||
                otherCode.startsWith("13") || otherCode.startsWith("14") ||
                otherCode.startsWith("15") || otherCode.startsWith("18")
                ) {
            return 1;
        }
        return 0;
    }

    /**
     * 根据呼叫方向获得表名
     *
     * @param callLog
     * @return
     */
    private String getTableName(CallLog callLog) {
        String table = null;
        if (callLog.getCallDirect().equals(1)) {
            table = "Guestbook_CallInLogList";
        } else {
            table = "Guestbook_PhoneLogList";
        }
        return table;
    }


}
