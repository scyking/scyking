package com.example.demo.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @ClassName FileUtil
 * @Description 文件处理工具类
 * @Author scyking
 * @Date 2018/12/2 16:13
 * @Version 1.0
 */
public class FileUtil {

    /**
     * 文件下载至web
     *
     * @param response
     * @param fis      待下载文件流
     * @param fileName 文件名（包含后缀名）
     */
    public static void downloadFile(HttpServletResponse response, InputStream fis, String fileName) throws IOException {
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        response.setContentType("application/octet-stream;charset=UTF-8");
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = new BufferedInputStream(fis);
        OutputStream os = response.getOutputStream(); //输出流
        int i = bis.read(buffer);
        while (i != -1) {
            os.write(buffer);
            i = bis.read(buffer);
        }
        bis.close();
        fis.close();
    }

    /**
     * 文件下载至web
     *
     * @param response
     * @param filePath 待下载文件路径
     * @param fileName 文件名称（包括后缀名）
     * @throws IOException
     */
    public static void downloadFile(HttpServletResponse response, String filePath, String fileName) throws IOException {
        if (!StringUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                downloadFile(response, fis, fileName);
            }
        }
    }

}
