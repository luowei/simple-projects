package com.rootls.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-22
 * Time: 下午4:38
 * To change this template use File | Settings | File Templates.
 */
public class Config {

    public static String EXL_NAME = "用户名";
    public static String EXL_HTBIANHAO = "合同编号";
    public static String EXL_RECEIPTDATE = "开票时间";
    public static String EXL_SHOURUXIANMU = "收入项目";
    public static String EXL_RECEIPTSUM = "发票金额";
    public static String EXL_RECEIPT = "发票号";
    public static String EXL_RECEIPTDZDATE = "到账日期";
    public static String EXL_KUANXIANGLX = "款项类型";
    public static String EXL_RECEIPTTYPE = "发票状态";
    public static String EXL_SENDADDR = "邮寄地址";
    public static String EXL_SENDLXR = "联系人";
    public static String EXL_SENDPHONE = "电话";
    public static String EXL_SENDMOBILE = "手机";
    public static String EXL_SENDTIME = "邮寄时间";
    public static String EXL_SENDCONTENT = "邮寄内容";

    public static String UPLOAD_PATH = "d:/upload/upload/admin/bill";

    //页面大小
    public static Integer PAGE_SIZE = 1;
    //usercache缓存时间
    public static Long USERCACHE_EXPIRE = 24 * 3600 * 1000L;

    //excel导入日志记录器
    public static List<String> importLog = new ArrayList<String>();

}
