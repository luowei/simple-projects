package com.rootls.model;

import com.rootls.bean.CustomJsonDateDeserializer;
import com.rootls.bean.CustomJsonDateSerializer;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-4-16
 * Time: 下午4:06
 * To change this template use File | Settings | File Templates.
 */
public class CallResponse implements Serializable {

    //调用错误值，0调用成功，非0调用失败
    Integer errorCode;
    //调用错误的原因描述
    String errorString;
    List<CallLog> callLogList;

    public Integer getErrorCode() {
        return errorCode;
    }

    @JsonProperty("ErrorCode")
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorString() {
        return errorString;
    }

    @JsonProperty("ErrorString")
    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    public List<CallLog> getCallLogList() {
        return callLogList;
    }

    @JsonProperty("Data")
    public void setCallLogList(List<CallLog> callLogList) {
        this.callLogList = callLogList;
    }

    public static class CallLog{
        //发起呼叫的时间
        Date callDateTime;
        //1表示呼入，2表示呼出，不填或者填0表示全部
        Integer callDirect;
        Integer callIndex;
        //呼叫结果
        Integer callResult;
        //呼叫时长(秒)
        Long callTimeLen;
        //接通的时间，如果未接通为空
        Date connectDateTime;
        //通话时长(秒)
        Long connectTimeLen;
        //通话结束的时间
        Date disconnectTime;
        //分机号
        String extCode;
        Integer id;
        //对方号码
        String otherCode;
        //录音文件保存到服务器的路径
        String recordFile;
        String sessionID;

        Integer adminId;
        String adminName;

        Integer guestBookId;
        Integer infoSourceId;
        //对应联系人表中的gc类型
        Integer gclx;
        Integer myCustomerId;

        //号码归属地
        String phoneFrom;
        //默认为0表示不是手机号,1表示是手机号
        Integer isMobile;
        private String adminPhone;
        private String LX;

        public CallLog() {
        }

        public CallLog(Date callDateTime, Integer callDirect, Integer callIndex,
                       Integer callResult, Long callTimeLen, Date connectDateTime,
                       Long connectTimeLen, Date disconnectTime, String extCode, Integer id,
                       String otherCode, String recordFile, String sessionID) {
            this.callDateTime = callDateTime;
            this.callDirect = callDirect;
            this.callIndex = callIndex;
            this.callResult = callResult;
            this.callTimeLen = callTimeLen;
            this.connectDateTime = connectDateTime;
            this.connectTimeLen = connectTimeLen;
            this.disconnectTime = disconnectTime;
            this.extCode = extCode;
            this.id = id;
            this.otherCode = otherCode;
            this.recordFile = recordFile;
            this.sessionID = sessionID;
        }

        @JsonSerialize(using = CustomJsonDateSerializer.class)
        public Date getCallDateTime() {
            return callDateTime;
        }

        @JsonProperty("CallDateTime") @JsonDeserialize(using = CustomJsonDateDeserializer.class)
        public void setCallDateTime(Date callDateTime) {
            this.callDateTime = callDateTime;
        }

        public Integer getCallDirect() {
            return callDirect;
        }

        @JsonProperty("CallDirect")
        public void setCallDirect(Integer callDirect) {
            this.callDirect = callDirect;
        }

        public Integer getCallIndex() {
            return callIndex;
        }

        @JsonProperty("CallIndex")
        public void setCallIndex(Integer callIndex) {
            this.callIndex = callIndex;
        }

        public Integer getCallResult() {
            return callResult;
        }

        @JsonProperty("CallResult")
        public void setCallResult(Integer callResult) {
            this.callResult = callResult;
        }

        public Long getCallTimeLen() {
            return callTimeLen;
        }

        @JsonProperty("CallTimeLen")
        public void setCallTimeLen(Long callTimeLen) {
            this.callTimeLen = callTimeLen;
        }

        public Date getConnectDateTime() {
            return connectDateTime;
        }

        @JsonProperty("ConnectDateTime")   @JsonDeserialize(using = CustomJsonDateDeserializer.class)
        public void setConnectDateTime(Date connectDateTime) {
            this.connectDateTime = connectDateTime;
        }

        public Long getConnectTimeLen() {
            if(connectDateTime==null){
                return 0l;
            }
            return connectTimeLen;
        }

        @JsonProperty("ConnectTimeLen")
        public void setConnectTimeLen(Long connectTimeLen) {
            this.connectTimeLen = connectTimeLen;
        }

        @JsonSerialize(using = CustomJsonDateSerializer.class)
        public Date getDisconnectTime() {
            return disconnectTime;
        }

        @JsonProperty("DisconnectTime")  @JsonDeserialize(using = CustomJsonDateDeserializer.class)
        public void setDisconnectTime(Date disconnectTime) {
            this.disconnectTime = disconnectTime;
        }

        public String getExtCode() {
            //删除电话号码中"<"以后的内容
            int index = extCode.indexOf("<");
            if (extCode != null && index != -1) {
                extCode = extCode.substring(0, index);
            }
            return extCode;
        }

        @JsonProperty("ExtCode")
        public void setExtCode(String extCode) {
            this.extCode = extCode;
        }

        public Integer getId() {
            return id;
        }

        @JsonProperty("ID")
        public void setId(Integer id) {
            this.id = id;
        }

        public String getOtherCode() {
            return otherCode;
        }

        @JsonProperty("OtherCode")
        public void setOtherCode(String otherCode) {
            this.otherCode = otherCode;
        }

        public String getRecordFile() {
            return recordFile;
        }

        @JsonProperty("RecordFile")
        public void setRecordFile(String recordFile) {
            this.recordFile = recordFile;
        }

        public String getSessionID() {
            return sessionID;
        }

        @JsonProperty("SessionID")
        public void setSessionID(String sessionID) {
            this.sessionID = sessionID;
        }


        public Integer getAdminId() {
            return adminId;
        }

        public void setAdminId(Integer adminId) {
            this.adminId = adminId;
        }

        public String getAdminName() {
            return adminName;
        }

        public void setAdminName(String adminName) {
            this.adminName = adminName;
        }

        public Integer getGuestBookId() {
            return guestBookId;
        }

        public void setGuestBookId(Integer guestBookId) {
            this.guestBookId = guestBookId;
        }

        public Integer getInfoSourceId() {
            return infoSourceId;
        }

        public void setInfoSourceId(Integer infoSourceId) {
            this.infoSourceId = infoSourceId;
        }

        public Integer getGclx() {
            return gclx;
        }

        public void setGclx(Integer gclx) {
            this.gclx = gclx;
        }

        public Integer getMyCustomerId() {
            return myCustomerId;
        }

        public void setMyCustomerId(Integer myCustomerId) {
            this.myCustomerId = myCustomerId;
        }

        public String getPhoneFrom() {
            return phoneFrom;
        }

        public void setPhoneFrom(String phoneFrom) {
            this.phoneFrom = phoneFrom;
        }

        public Integer getMobile() {
            return isMobile;
        }

        public void setMobile(Integer mobile) {
            isMobile = mobile;
        }

        public String getAdminPhone() {
            return adminPhone;
        }

        public void setAdminPhone(String adminPhone) {
            this.adminPhone = adminPhone;
        }

        public String getLX() {
            return LX;
        }

        public void setLX(String LX) {
            this.LX = LX;
        }
    }

}
