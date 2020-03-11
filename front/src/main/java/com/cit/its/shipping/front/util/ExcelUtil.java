package com.cit.its.shipping.front.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 * @author 杨贺龙
 * @name ExcelUtil
 * @create 2019-11-28 8:48
 * @description: Excel导出工具类
 */

public class ExcelUtil {
    /*/**
     * @Author 杨贺龙
     * @Description //TODO
     * @Date 9:16 2019/11/28
     * @Name getHSSFWorkbook
     * @Param [sheetname表单名称, title表头, values值,二维数据]
     * @return org.apache.poi.hssf.usermodel.HSSFWorkbook
     **/
    public static HSSFWorkbook getHSSFWorkbook(HSSFWorkbook wb,String sheetname, String[] title, String[][] values) {
        //第一步,创建一个HSSFWorkbook,对应一个Excel文件
       // HSSFWorkbook wb = new HSSFWorkbook();
        //第二步,在woekbook中添加一个sheet,对应Excel中的sheet
        HSSFSheet sheet = wb.createSheet();
        //第三步,在sheet中添加表头第0行
        HSSFRow row = sheet.createRow(0);
        //第四步,设置格式
        //设置表头格式
        CellStyle headerStyle = wb.createCellStyle();
        //水平居中
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        //单元格样式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //自动换行
        cellStyle.setWrapText(true);

        //创建内容
        for (int i = 0; i < values.length; i++) {
            if (i == 65535 || i == 0) {
                sheet = wb.createSheet();
                Row headerRow = sheet.createRow(0); //表头
                for (int k = 0; k < title.length; k++) {
                    headerRow.createCell(k).setCellValue(title[k]);
                    headerRow.setRowStyle(headerStyle);
                }

            }
            //创建行
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                //创建单元格
                Cell newCell = row.createCell(j);
                newCell.setCellValue(values[i][j]);
                newCell.setCellStyle(cellStyle);
            }
        }
        for (int i = 0; i < title.length; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
        }
        return wb;
    }
}
