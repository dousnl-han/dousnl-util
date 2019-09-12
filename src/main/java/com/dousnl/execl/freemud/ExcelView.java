package com.dousnl.execl.freemud;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 此工具不好用，此处废弃掉
 * 使用导出，请使用 dousnl-util.src.main.java.com.dousnl.execl.ExportUtils.java
 * 详细例子见 com.dousnl.controller.ExeclController.java  /execl/xp1
 */
@Deprecated
public class ExcelView extends AbstractView {

    private static final String CELL_STYLE_HEADER = "cellStyleHeader";
    private static final String CELL_STYLE_DATA = "cellStyleData";
    private static final String FILENAME = "fileName";
    private static final String HEADERS = "headers";
    private static final String DATAS = "datas";

    private static Map<String, CellStyle> cellStyleMap = Maps.newHashMap();

    @Override
    public void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        initCellStyleMap(workbook);
        String fileName = "导出数据";
        if (model.get(FILENAME) != null) {
            fileName = model.get(FILENAME).toString();
        }
        Sheet sheet = workbook.createSheet(fileName);
        sheet.setDefaultColumnWidth(12);
        if (model.get(HEADERS) != null) {
            List<Object> headers = (List<Object>) model.get(HEADERS);
            if (CollectionUtils.isNotEmpty(headers)) {
                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = getCell(sheet, 0, i);
                    cell.setCellStyle(cellStyleMap.get(CELL_STYLE_HEADER));
                    setCellValue(cell, headers.get(i));
                }
            }
        }
        List<?> datas = model.get(DATAS) == null ?  Lists.newArrayList() : (List<List<Object>>) model.get(DATAS);
        if (CollectionUtils.isNotEmpty(datas)) {
            for (int i = 0; i < datas.size(); i++) {
                List<Object> row = (List<Object>) datas.get(i);
                for (int j = 0; j < row.size(); j++) {
                    Cell cell = getCell(sheet, i + 1, j);
                    cell.setCellStyle(cellStyleMap.get(CELL_STYLE_DATA));
                    setCellValue(cell, row.get(j));
                }
            }
        }
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName
                + DateUtils.convert2String(new Date(), DateUtils.FORMAT_YYMMDDHHMMSS) + ".xlsx", "UTF-8"));
        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }

    private static void initCellStyleMap(SXSSFWorkbook workbook) {
        CellStyle cellStyleHeader = workbook.createCellStyle();
        cellStyleHeader.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cellStyleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyleHeader.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleHeader.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyleHeader.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleHeader.setBorderTop(CellStyle.BORDER_THIN);
        cellStyleHeader.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleHeader.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyleHeader.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font headerFont = workbook.createFont();
        headerFont.setFontName("微软雅黑");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        cellStyleHeader.setFont(headerFont);
        cellStyleMap.put(CELL_STYLE_HEADER, cellStyleHeader);

        CellStyle cellStyleData = workbook.createCellStyle();
        cellStyleData.setAlignment(CellStyle.ALIGN_LEFT);
        cellStyleData.setBorderRight(CellStyle.BORDER_THIN);
        cellStyleData.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleData.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyleData.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleData.setBorderTop(CellStyle.BORDER_THIN);
        cellStyleData.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyleData.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyleData.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = workbook.createFont();
        dataFont.setFontName("微软雅黑");
        dataFont.setFontHeightInPoints((short) 10);
        cellStyleData.setFont(dataFont);
        cellStyleMap.put(CELL_STYLE_DATA, cellStyleData);
    }

    private void setCellValue(Cell cell, Object obj) {
        if (obj == null) {
            cell.setCellValue("");
        } else if (obj instanceof Integer) {
            cell.setCellValue((Integer) obj);
        } else if (obj instanceof Long) {
            cell.setCellValue((Long) obj);
        } else if (obj instanceof Double) {
            cell.setCellValue((Double) obj);
        } else if (obj instanceof BigDecimal) {
            cell.setCellValue(((BigDecimal) obj).doubleValue());
        } else if (obj instanceof Float) {
            cell.setCellValue((Float) obj);
        } else if (obj instanceof Date) {
            cell.setCellValue(DateUtils.convert2String((Date) obj, DateUtils.FORMAT_YYYY_MM_DD_HHMMSS) + "");
        } else if (obj instanceof String) {
            cell.setCellValue(String.valueOf(obj));
        }
    }

    private Cell getCell(Sheet sheet, int row, int col) {
        Row sheetRow = sheet.getRow(row);
        if (sheetRow == null) {
            sheetRow = sheet.createRow(row);
        }
        Cell cell = sheetRow.getCell(col);
        if (cell == null) {
            cell = sheetRow.createCell(col);
        }
        return cell;
    }

}
