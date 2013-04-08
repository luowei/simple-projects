package com.rootls.common.tool.common;
import org.apache.commons.lang.StringUtils;




public class PhoneNameConver 
{
	/**
	 * 去掉原始机型名中的品牌名前缀
	 * 
	 */
	public static String PhoneFullName2PhoneName(String brandName,String phoneFullName){
		String newBrandName=null;
		String phoneName=phoneFullName;
		if(!StringUtils.isBlank(brandName) && !StringUtils.isBlank(phoneFullName)){
			int oldIndex=0;
			if(brandName.contains("（") && brandName.contains("）"))
			{
				newBrandName=brandName.substring(brandName.indexOf("（")+1, brandName.indexOf("）"));
				phoneName=phoneFullName.replace(newBrandName, "");	//去掉机型名字中的品牌
			}
			else{
				phoneName=phoneFullName.replace(newBrandName, "").trim();
			}
		}
		return phoneName;
	}

}
