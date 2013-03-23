package com.ttpod.common.tool.Controler;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.ttpod.common.tool.GetId;
import com.ttpod.common.tool.common.URLFileUtil;

public class PhoneUtils {

	/**
	 * 处理临时机型分辨率
	 * @author luowei
	 * @param phoneSize:机型尺寸
	 * @return String: 分辨率
	 */
	public static String handleZOLResolution(String phoneSize) {
		if(phoneSize!=null && phoneSize.contains("像素"))		//如果分辨率不为空
		{
	        int endIndex=phoneSize.indexOf("像素");
	        int beginIndex=endIndex-7;
	        return phoneSize.substring(beginIndex, endIndex);
		}
		return null;
	}

//	/**
//	 * 处理临时机型名称和简称
//	 * @author luowei
//	 * @param inBrandName:临时机型品牌名
//	 * @param inPhoneName:临时机型名
//	 * @return String[]： name[0] 机型名称，name[1] 机型简称
//	 */
//	public static String[] headnameAndName(String inBrandName,String inPhoneName) {
//		String[] name=null;
//		String newBrandName=null;
//		if(!StringUtils.isBlank(inPhoneName) && !StringUtils.isBlank(inBrandName)){
//			name=new String[2];
//			name[0]=inBrandName;
//			int oldIndex=0;
//			name[1]=inPhoneName.substring(oldIndex,oldIndex+1);
//			if(inBrandName.contains("（") && inBrandName.contains("）"))
//			{
//				newBrandName=inBrandName.substring(inBrandName.indexOf("（")+1, inBrandName.indexOf("）"));
//				name[0]=inPhoneName.replace(newBrandName, "");	//去掉机型名字中的品牌
//				name[1]=name[0].substring(0,1);
//			}
//		}
//		return name;
//	}
	
	/**
	 * 下载图片
	 * @author luowei
	 * @param 
	 * @param 
	 * @return String filename:下载后的图片,映射为本地服务的链接地址
	 */
	public static String downloadImg(String img,String targetRelativePath,HttpServletRequest request) {
		String filename=img;
		if(!StringUtils.isBlank(img) && !StringUtils.isBlank(targetRelativePath))
		{
			String temp[]=img.split("\\.");
			filename=GetId.getNewId()+"."+temp[temp.length-1];
			String path=request.getRealPath(targetRelativePath);
			URLFileUtil.saveFile(img,path,filename);
			return request.getContextPath()+targetRelativePath+filename;
		}
		return filename;
	}
	
	/**
	 * 上传机型图片
	 * @param fileTagName 页面中的file标签名
	 * @param targetRelativePath 上传目标在服务器端的相对路径，如：images/phone
	 * @param request	请求
	 * @return String 返回上传之后的文件名，如果request中没有文件路径，
	 */
	public static String upLoadPhomImg(String fileTagName,String targetRelativePath,DefaultMultipartHttpServletRequest request) {
		MultipartFile file=request.getFile(fileTagName);
		String fileName = file.getOriginalFilename();
		String path = request.getRealPath(targetRelativePath);
		if(!StringUtils.isBlank(fileName) && path!=null){
			File targetFile = new File(path, GetId.getNewId()+fileName);
			fileName=targetFile.getName();
	        if(!targetFile.exists()){
	            targetFile.mkdirs();
	        }
	        try {
	            file.transferTo(targetFile);	//上传目标文件
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		return fileName;
	}

	/**
	 * 删除机型图片
	 * 
	 */
	public static boolean deletePhoneImg(String realphoneImg){
		if(StringUtils.isBlank(realphoneImg)){
			File file=new File(realphoneImg);
			if(file.exists()){
				file.delete();
			}
			return true;
		}
		else{
			return false;
		}
	}
}
