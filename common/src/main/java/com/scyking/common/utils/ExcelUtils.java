package com.scyking.common.utils;


import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;

/**
 * Excel工具类
 *
 * @author scyking
 **/
@Data
public class ExcelUtils {

    private Workbook wb;
    private Sheet sheet;

    public static ExcelUtils builder() {
        return new ExcelUtils();
    }

    public ExcelUtils rows(String... rows) {
        return rows(0, rows);
    }

    public ExcelUtils rows(int index, String... rows) {
        Assert.notNull(sheet, "请先创建sheet");
        Row row = sheet.createRow(index);
        for (int i = 0; i < rows.length; i++) {
            row.createCell(i).setCellValue(rows[0]);
        }
        return this;
    }

    public ExcelUtils sheet(String sheetName) {
        Assert.notNull(wb, "请先创建workbook");
        Assert.notNull(sheetName, "sheet名称不能为空");
        sheet = wb.createSheet(sheetName);
        return this;
    }

    public ExcelUtils workbook(String fileName) {
        Assert.notNull(fileName, "文件名称不能为空！");
        String[] temp = fileName.split("\\.");
        if (temp.length > 1) {
            if ("xlsx".equals(temp[1])) {
                wb = new XSSFWorkbook();
                return this;
            }
            if ("xls".equals(temp[1])) {
                wb = new HSSFWorkbook();
                return this;
            }
        }
        throw new IllegalArgumentException("请正确输入文件名称！");
    }
}
