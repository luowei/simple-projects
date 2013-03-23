package com.ttpod.common.tool.common;

/**
 * @className:StringUtil
 * @classDescription:描述功能
 * @author xiping.tang
 * @createTime 2012-01-17
 * @version v0.0.1
 */
public class StringUtil {
	/**
	 * String to Integer
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param str
	 *		source String
	 * @return Integer
	 * 		result
	 */
	public static Integer convertStringToInteger(String str) {
		Integer num = 0;
		try {
			num = Integer.parseInt(str);
		} catch (java.lang.NumberFormatException e) {
			return num;
		}
		return num;
	}
}
