package com.rootls.bean;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-3-25
 * Time: 上午9:34
 * To change this template use File | Settings | File Templates.
 */
public class UploadUtil {

    /**
     * 上传后返回上传后的路径
     * @param file
     * @param savePath
     * @return
     */
    public static synchronized String upload(MultipartFile file, String savePath) {
        if (file.isEmpty() || isBlank(savePath)) {
            return null;
        }
        // 获取路径，生成完整的文件路径
        String originalFileName = file.getOriginalFilename();

        String filePrefix = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String yyyyMMDDHHMMSS = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        //生成的文件名为 "物理路径/前缀_日期时间+后缀"
        String saveFileName = filePrefix + "_" + yyyyMMDDHHMMSS
                + originalFileName.substring(originalFileName.lastIndexOf("."));
        String fullSaveFileName = savePath + "/" + saveFileName;
        File dirFile = new File(savePath);
        if (!dirFile.exists())
            dirFile.mkdirs();
        File uploadFile = new File(fullSaveFileName);
        try {
            FileCopyUtils.copy(file.getBytes(), uploadFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fullSaveFileName;
    }

    /**
     * 上传文件;
     * @param uploadFile
     * @param savePath 文件保存的路径
     * @param saveName 文件名(如"20080611.gif")
     */
    public static void uploadFile(MultipartFile uploadFile, String savePath, String saveName) {
        try {
            InputStream stream = uploadFile.getInputStream();
            OutputStream bos = null;
            File localDir = new File(savePath);
            File localFile = new File(savePath+"/"+saveName);
            if (!localDir.isDirectory()) {
                localDir.mkdirs(); // 不存在一系列目录则新建;
            }
            if(!localFile.exists()) {
                localFile.createNewFile();
            }
            bos = new FileOutputStream(localFile);
//            bos.write(uploadFile.getBytes());
            inputStreamToOutputStream(stream,bos);
            bos.flush();
            bos.close();
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void inputStreamToOutputStream(final InputStream inputStream, final OutputStream out) {
        Thread t = new Thread(new Runnable() {

            public void run() {
                try {
                    int d;
                    while ((d = inputStream.read()) != -1) {
                        out.write(d);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    /**
     * 获得上传文件后缀;(不包括后缀前面的".")
     * @param file
     * @return
     */
    public static String getUploadFileExt(MultipartFile file) {
        String fileExt = null;
        int index = file.getOriginalFilename().lastIndexOf(".");
        if (index >= 0) {
            fileExt = file.getOriginalFilename().substring(index + 1).trim();
        }
        return fileExt;
    }


    /**
     * 是否是允许的文件;(gif, jpg, swf等);
     * @param upFile
     * @param allowExt 允许的文件后缀名.
     * @return
     */
    public static boolean isAllowFile(MultipartFile upFile, String allowExt[]) {
        boolean bok = false;
        int index = upFile.getOriginalFilename().lastIndexOf(".");
        if (index >= 0 && allowExt != null) {
            String fileExt = getUploadFileExt(upFile);
            if (fileExt == null) {
                return false;
            }
            for (int i = 0; i < allowExt.length; i++) {
                if (fileExt.equalsIgnoreCase(allowExt[i])) {
                    bok = true;
                    break;
                }
            }
        }
        return bok;
    }
}
