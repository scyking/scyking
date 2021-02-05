package com.scyking.common.utils;

import com.scyking.common.base.ExportFileException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

/**
 * excel工具类
 *
 * @author scyking
 **/
public class ExcelUtils {

    /**
     * 导出文件 （通过浏览器）
     *
     * @param fileName 文件名称
     * @param wb
     * @param response
     */
    public static void export(String fileName, Workbook wb, HttpServletResponse response) {
        response.reset();
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename="
                + "=?UTF8?B?"
                + new String(Base64.getEncoder().encode(fileName.getBytes()))
                + "?=");
        try {
            OutputStream output = response.getOutputStream();
            wb.write(output);
            wb.close();
            output.flush();
            output.close();
        } catch (IOException e) {
            throw new ExportFileException(fileName, e);
        }
    }

    /**
     * 导出文件（通过浏览器）
     * @param fileName
     * @param excel
     * @param response
     */
    public static void export(String fileName, Excels excel, HttpServletResponse response) {
        export(fileName, excel.build(), response);
    }
}
