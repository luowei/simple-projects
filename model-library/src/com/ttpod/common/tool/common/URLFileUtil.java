package com.ttpod.common.tool.common;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.ttpod.common.tool.GetId;

/**
 * 手机品牌与机型工具类
 * @ClassName: URLFileUtil
 * @Description: 
 * @author wei.luo
 * @date 2012-1-17
 *
 */
public class URLFileUtil 
{	
	/**
	 * 根据指定的url保存文件到path
	 * @param url
	 * @param path
	 * @param Filename
	 * @return	保存成功返回假，保存失败返回真
	 */
    public static boolean saveFile(String url,String path,String Filename)
    {
    	InputStream is;
    	File f;
    	FileOutputStream fos;
    	try
    		{
    			URLConnection uc=new URL(url).openConnection();
    			uc.connect();
    			is=uc.getInputStream();
    			f=new File(path,Filename);
    			fos=new FileOutputStream(f);
    			int t=is.read();
    			while(t!=-1)
    			{
    				fos.write(t); 
    				t=is.read(); 				
    			}
    			is.close();
    			fos.close();
    			return true;
    		}
    		catch(IOException e)
    		{
    			return false;
    		}
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

	/**
	 * 下载图片
	 * @author luowei
	 * @param img 图片下载地址
	 * @param 	targetRelativePath 图片存放的目标路径
	 * @return String filename:若下载成功，返回图片相对于本地服务器的地址；否则返回空
	 */
	public static String downloadImg(String img,String targetRelativePath,HttpServletRequest request) {
		String filename=img;
		if(!StringUtils.isBlank(img) && !StringUtils.isBlank(targetRelativePath))
		{
			String temp[]=img.split("\\.");
			filename=GetId.getNewId()+"."+temp[temp.length-1];
			String path=request.getRealPath(targetRelativePath);
			if(URLFileUtil.saveFile(img,path,filename)){
				return request.getContextPath()+targetRelativePath+filename;
			}
		}
		return null;
	}

	/**
	 * 下载图片
	 * @author luowei
	 * @param img 图片下载地址
	 * @param 	targetPath 图片存放的目标路径
	 * @return String filename:若下载成功，返回图片相对于本地服务器的地址；否则返回空
	 */
	public static String downImg(String img,String path) {
		String fileName=img;
		if(!StringUtils.isBlank(img) && !StringUtils.isBlank(path))
		{
			String temp[]=img.split("\\.");
			fileName=GetId.getNewId()+"."+temp[temp.length-1];
			if(URLFileUtil.saveFile(img,path,fileName)){
				return fileName;
			}
		}
		return null;
	}
	
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

	/**
	 * 去掉原始机型名中的品牌名前缀
	 * @param fileTagName 页面中的file标签名
	 * @param targetRelativePath 上传目标在服务器端的相对路径，如：images/phone
	 * @return 返回去掉了品牌名前缀的机型名
	 */
	public static String PhoneFullName2PhoneName(String brandName,String phoneFullName){
		String newBrandName=null;
		String phoneName=phoneFullName;
		if(!StringUtils.isBlank(brandName) && !StringUtils.isBlank(phoneFullName)){
			int oldIndex=0;
			if(brandName.contains("（") && brandName.contains("）"))
			{
				newBrandName=brandName.substring(brandName.indexOf("（")+1, brandName.indexOf("）"));
				phoneName=phoneFullName.replace(newBrandName, "").trim();	//去掉机型名字中的品牌
			}
			else{
				phoneName=phoneFullName.replace(brandName, "").trim();
			}
		}
		return phoneName;
	}

	/**
	 * 上传机型图片
	 * @author luowei
	 * @param fileTagName 页面中的file标签名
	 * @param targetRelativePath 上传目标在服务器端的相对路径，如：images/phone
	 * @param request	请求
	 * @return String 返回上传之后的文件名
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
	 * 上传机型图片
	 * @author luowei
	 * @param fileTagName 页面中的file标签名
	 * @param targetRelativePath 上传目标在服务器端的相对路径，如：images/phone
	 * @param request	请求
	 * @return String 返回上传之后的文件名
	 */
	public static String upLoadImg(MultipartFile file,String path) {
		String fileName=null;
		if(file!=null && path!=null){
			fileName = GetId.getNewId()+file.getOriginalFilename();
			File targetFile = new File(path, fileName);
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
	 * 以列表形式，返回txt文件中每一行的数据
	 * @param @param filePath
	 * @return List<String>    以list返回txt文件中的每一行的数据
	 * @throws
	 */
	public static List<String> readTxtLines(String filePath){
		List<String> str=new ArrayList<String>();
		if(filePath!=null){
			File file = new File(filePath);
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            int line = 1;
	            while ((tempString = reader.readLine()) != null) {
	            	if(StringUtils.isNotEmpty(tempString)){
	            		str.add(tempString);
	            	}
	                line++;
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } 
	                catch (IOException e1) {
	                	e1.printStackTrace();
	                }
	            }
	        }
		}
		return str;
	}
	
	public static void main(String[] args) {
		List<String> platformList=new ArrayList<String>();
		File file = new File("com/ttpod/common/tool/common/platformShortName.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
            	if(StringUtils.isNotEmpty(tempString)){
            		platformList.add(tempString);
            	}
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        
        for(String platformShortName:platformList){
        	System.out.println("platformShortName:"+platformShortName);
        }
        
	}
}
