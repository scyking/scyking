package com.scyking.common.utils;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Excel工具类
 * <p>
 * 构造excel文件
 * </p>
 *
 * @author scyking
 **/
public class Excels {

    private static final String EXCEL_2003 = ".xls";
    private static final String EXCEL_2007 = ".xlsx";
    private static final String EXCEL_DEFAULT_NAME = "新建 Microsoft Excel 工作表.xlsx";

    private Workbook wb;
    private Sheet sheet;
    private String sheetName;
    private String fileName = EXCEL_DEFAULT_NAME;
    private Map<Integer, String[]> rows = new HashMap<>();

    public Excels() {
    }

    public Excels(Workbook wb, String fileName) {
        this.wb = wb;
        this.fileName = fileName;
    }


    public Workbook getWb() {
        return this.wb;
    }

    public String getFileName() {
        return this.fileName;
    }


    public Workbook build() {
        createWorkbook();
        createSheet();
        createRows();
        return wb;
    }

    public Excels row(String... cells) {
        return row(0, cells);
    }

    public Excels row(int index, String... cells) {
        rows.put(index, cells);
        return this;
    }

    public Excels sheet(String sheetName) {
        this.sheetName = sheetName;
        return this;
    }

    public Excels workbook(String fileName) {
        this.fileName = fileName;
        return this;
    }

    private void createWorkbook() {
        Assert.hasText(fileName, "请正确输入文件名称！");
        if (fileName.endsWith(EXCEL_2003)) {
            wb = new HSSFWorkbook();
        }
        if (fileName.endsWith(EXCEL_2007)) {
            wb = new XSSFWorkbook();
        }
        if (wb == null) {
            throw new IllegalArgumentException("请正确输入文件名称！");
        }
    }

    private void createSheet() {
        if (StringUtils.hasText(sheetName)) {
            sheet = wb.createSheet(sheetName);
        } else {
            sheet = wb.createSheet();
        }
    }

    private void createRows() {
        Set<Integer> keys = rows.keySet();
        for (Integer key : keys) {
            Row row = sheet.createRow(key);
            int i = 0;
            for (String cell : rows.get(key)) {
                row.createCell(i).setCellValue(cell);
                i++;
            }
        }
    }
}
