package com.dousnl.utils.execl;

import com.dousnl.utils.date.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;


public class ExportUtils {

    /** 当大于ROWS时候采用SXSSFWorkbook，默认采用XSSFWorkbook*/
    private static final int ROWS = 3000;
    /** 每次从CPU读取多少行*/
    private static final int READROWS = 500;

    /**
     * 导出excel通用方法
     *
     * @param headers    excel头信息
     * @param contents   excel内容信息
     * @param fieldsName 内容映射实体字段名称
     * @return 文件路径（不包括服务器前缀路径）
     * @throws Exception
     */
    @Deprecated
    public static String exportExcel(String[] headers, Collection<?> contents, String[] fieldsName) throws Exception {
        return ExportUtils.exportExcel(headers, contents, fieldsName, false);
    }

    /**
     * 导出excel通用方法
     *
     * @param headers     excel头信息
     * @param contents    excel内容信息
     * @param fieldsName  内容映射实体字段名称
     * @param hiddenFirst 是否隐藏第一列
     * @return 文件路径（不包括服务器前缀路径）
     * @throws Exception
     */
    @Deprecated
    public static String exportExcel(String[] headers, Collection<?> contents, String[] fieldsName, boolean hiddenFirst) throws Exception {
        //生成文件名
        String fileName = UUID.randomUUID().toString() + ".xlsx";

        // 声明一个工作薄
        Workbook workbook = null != contents && contents.size() > ROWS ? new SXSSFWorkbook(READROWS) : new XSSFWorkbook();
        // 生成一个表格
        Sheet sheet = workbook.createSheet("sheet1");
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);

        //判断是否隐藏第一列
        if (hiddenFirst) {
            sheet.setColumnHidden(0, true);
        }
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();

        /**如有特殊样式和字体，可在这里新增或扩展**/
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        //设置excel第一行头信息显示
        Row row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //冻结第一行
        sheet.createFreezePane(0, 1, 0, 1);

        //遍历集合数据，产生数据行
        Iterator<?> it = contents.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Object obj = it.next();
            for (int i = 0; i < fieldsName.length; i++) {
                Cell cell = row.createCell(i);
                String getMethodName = "get"
                        + fieldsName[i].substring(0, 1).toUpperCase()
                        + fieldsName[i].substring(1);
                Class<?> tCls = obj.getClass();
                Method getMethod = tCls.getMethod(getMethodName,
                        new Class[]{});
                Object value = getMethod.invoke(obj, new Object[]{});
                /**如获取值比较特殊，可在这里扩展值的转换方法**/
                if (value == null) {
                    value = new String("");
                }
                XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                cell.setCellValue(richString);
            }
        }
        //生成文件夹路径
        String path = ExportUtils.newFilePath();
        File file = new File(path + fileName);
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();

        return GlobalParameter.ip + path + fileName;
    }


    /**
     * 导出客户信息 excel
     *
     * @param headers     excel头信息
     * @param contents    excel内容信息
     * @param fieldsName  内容映射实体字段名称
     * @param hiddenFirst 是否隐藏第一列
     * @return 文件路径（不包括服务器前缀路径）
     * @throws Exception
     */
    @Deprecated
    public static String exportCustomerExcel(String[] headers, Collection<?> contents, String[] fieldsName, boolean hiddenFirst, Map<Integer, List<String>> map) throws Exception {
        //生成文件名
        String fileName = UUID.randomUUID().toString() + ".xlsx";

        // 声明一个工作薄
        Workbook workbook = null != contents && contents.size() > ROWS ? new SXSSFWorkbook(READROWS) : new XSSFWorkbook();
        // 生成一个表格
        Sheet sheet = workbook.createSheet("sheet1");
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);

        //判断是否隐藏第一列
        if (hiddenFirst) {
            sheet.setColumnHidden(0, true);
        }

        // 生成一个样式
        CellStyle style = workbook.createCellStyle();

        /**如有特殊样式和字体，可在这里新增或扩展**/
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        //设置excel第一行头信息显示
        Row row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //遍历集合数据，产生数据行
        Iterator<?> it = contents.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Object obj = it.next();
            int i;
            for (i = 0; i < fieldsName.length; i++) {
                Cell cell = row.createCell(i);
                String getMethodName = "get"
                        + fieldsName[i].substring(0, 1).toUpperCase()
                        + fieldsName[i].substring(1);
                Class<?> tCls = obj.getClass();
                Method getMethod = tCls.getMethod(getMethodName,
                        new Class[]{});
                Object value = getMethod.invoke(obj, new Object[]{});
                /**如获取值比较特殊，可在这里扩展值的转换方法**/
                if (value == null) {
                    value = new String("");
                }

                XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                cell.setCellValue(richString);
            }

            String getMethodName = "getCustomerId";
            Class<?> tCls = obj.getClass();
            Method getMethod = tCls.getMethod(getMethodName,
                    new Class[]{});
            Object value = getMethod.invoke(obj, new Object[]{});
            List<String> stringList = map.get(Integer.parseInt(value.toString()));
            for (String str : stringList) {
                Cell cell = row.createCell(i);
                XSSFRichTextString richString = new XSSFRichTextString(str);
                cell.setCellValue(richString);
                i++;
            }
        }
        //生成文件夹路径
        String path = ExportUtils.newFilePath();
        File file = new File(path + fileName);
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();

        return GlobalParameter.ip + path + fileName;
    }

    public static String exportExcel(String[] headers, Collection<?> contents, String[] fieldsName, boolean hiddenFirst, String exportName) throws Exception {
        return exportExcel(headers, contents, fieldsName, hiddenFirst, null, exportName);
    }

    public static void exportExcelFlush(String[] headers, Collection<?> contents, String[] fieldsName, boolean hiddenFirst, String exportName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        exportExcelFlush(headers, contents, fieldsName, hiddenFirst, null, exportName, request, response);
    }

    private static void exportExcelFlush(String[] headers, Collection<?> contents, String[] fieldsName, boolean hiddenFirst, String exportType, String exportName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String export = null;
        if (exportType != null) {
            export = exportType + "_" + exportName;
        } else {
            export = exportName;
        }

        //生成文件名
        String fileName = export + "_" + DateUtil.formatTime(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".xlsx";

        // 声明一个工作薄
        //XSSFWorkbook workbook = new XSSFWorkbook();
        Workbook workbook = null != contents && contents.size() > ROWS ? new SXSSFWorkbook(READROWS) : new XSSFWorkbook();
        // 生成一个表格
        Sheet sheet  = workbook.createSheet(export);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);

        //判断是否隐藏第一列
        if (hiddenFirst) {
            sheet.setColumnHidden(0, true);
        }
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();

        /**如有特殊样式和字体，可在这里新增或扩展**/
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        //设置excel第一行头信息显示
        Row row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //冻结第一行
        sheet.createFreezePane(0, 1, 0, 1);
        //Font dataFont = workbook.createFont();
        //dataFont.setFontName("微软雅黑");
        //dataFont.setFontHeightInPoints((short) 10);
        //字符串样式
        CellStyle stringStyle = workbook.createCellStyle();
        stringStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        //数字格式化样式
        CellStyle decimalStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        decimalStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
        decimalStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        //时间格式化样式
        CellStyle styleB = workbook.createCellStyle();
        styleB.setDataFormat(workbook.createDataFormat().getFormat("m/d/yy"));
        styleB.setAlignment(HSSFCellStyle.ALIGN_LEFT);


        //遍历集合数据，产生数据行
        Iterator<?> it = contents.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Object obj = it.next();
            for (int i = 0; i < fieldsName.length; i++) {
                Cell cell = row.createCell(i);
                String getMethodName = "get"
                        + fieldsName[i].substring(0, 1).toUpperCase()
                        + fieldsName[i].substring(1);
                Class<?> tCls = obj.getClass();
                Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                Object value = getMethod.invoke(obj, new Object[]{});
                /**如获取值比较特殊，可在这里扩展值的转换方法**/
                if (value == null) {
                    value = new String("");
                }
                if (value instanceof Integer) {
                    cell.setCellStyle(stringStyle);
                    cell.setCellValue((Integer) value);
                } else if (value instanceof BigDecimal) {
                    if (null != value || value != BigDecimal.ZERO) {
                        cell.setCellStyle(decimalStyle);
                        cell.setCellValue(((BigDecimal) value).doubleValue());
                    } else {
                        cell.setCellStyle(stringStyle);
                        cell.setCellValue(0);
                    }
                } else if (value instanceof Date) {
                    cell.setCellStyle(styleB);
                    cell.setCellValue((Date) value);
                } else {
                    XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                    cell.setCellStyle(stringStyle);
                    cell.setCellValue(richString);
                }
            }
        }
        //生成文件夹路径
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }

    /**
     * @param headers
     * @param contents
     * @param fieldsName
     * @param hiddenFirst
     * @param exportName
     * @param width
     * @return
     * @throws Exception
     */
    public static String exportExcelWidth(String[] headers, Collection<?> contents, String[] fieldsName, boolean hiddenFirst, String exportName, Map<Integer, Integer> width) throws Exception {
        return exportExcelByWidth(headers, contents, fieldsName, hiddenFirst, null, exportName, width);
    }

    public static String orderExportExcel(String[] headers, Collection<?> contents, String[] fieldsName, boolean hiddenFirst, String exportName) throws Exception {
        return exportExcel(headers, contents, fieldsName, hiddenFirst, null, exportName);
    }






    public static void main(String[] args) {

        // 声明一个工作薄
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheetAt = workbook.createSheet();
            sheetAt.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
            sheetAt.addMergedRegion(new CellRangeAddress(0, 0, 1, 3));
            for (int i = 0; i < args.length; i++) {

            }

            //生成文件夹路径
            String path = ExportUtils.newFilePath();
            File file = new File(path + "export.xlsx");
            OutputStream os = new FileOutputStream(file);
            workbook.write(os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 桉模板导出excel
     *
     * @param templatePath 模板路径
     * @param dataBeginRow 数据起始行 从0开始
     * @param contents     结果集内容
     * @param fieldsName   结果集字段映射
     * @param exportName   导出文件名
     * @param title        要修改第一行的title，key表示第几行
     * @return
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws Exception
     */
    public static String exportByTemplate(String templatePath, Integer dataBeginRow, Collection<?> contents, String[] fieldsName, String exportName, Map<Integer, String> title) throws Exception {
        //生成文件名
        String fileName = exportName + "_" + DateUtil.formatTime(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".xlsx";
        templatePath = "/moban/downloadExcel/template/" + templatePath;
        // 根据模板路径读取一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(templatePath));

        // 获取第一个表格
        XSSFSheet sheetAt = workbook.getSheetAt(0);

        for (Integer key : title.keySet()) {
            sheetAt.getRow(0).getCell(key).setCellValue(title.get(key));
        }
        boolean bool = true;
        //遍历集合数据，产生数据行
        if (null == contents) {
            bool = false;
        }
        Iterator<?> it = contents.iterator();
        int index = dataBeginRow;

        CellStyle decimalStyle = workbook.createCellStyle();
        XSSFDataFormat dataFormat = workbook.createDataFormat();
        decimalStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
        while (bool && it.hasNext()) {
            XSSFRow row = sheetAt.createRow(index);
            index++;
            Object obj = it.next();
            for (int i = 0; i < fieldsName.length; i++) {
                XSSFCell cell = row.createCell(i);
                String getMethodName = "get"
                        + fieldsName[i].substring(0, 1).toUpperCase()
                        + fieldsName[i].substring(1);
                Class<?> tCls = obj.getClass();
                Method getMethod = tCls.getMethod(getMethodName,
                        new Class[]{});
                Object value = getMethod.invoke(obj, new Object[]{});
                /**如获取值比较特殊，可在这里扩展值的转换方法**/
                if (value == null) {
                    value = new String("");
                }
                if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof BigDecimal) {
                    if (null != value || value != BigDecimal.ZERO) {
                        cell.setCellStyle(decimalStyle);
                        cell.setCellValue(((BigDecimal) value).doubleValue());
                    } else {
                        cell.setCellValue(0);
                    }
                } else if (value instanceof Date) {
                    CellStyle styleB = workbook.createCellStyle();
                    styleB.setDataFormat(workbook.createDataFormat().getFormat("m/d/yy"));
                    cell.setCellStyle(styleB);
                    cell.setCellValue((Date) value);
                } else {
                    XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                    cell.setCellValue(richString);
                }
            }
        }
        //生成文件夹路径
        String path = ExportUtils.newFilePath();
        File file = new File(path + fileName);
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();

        return GlobalParameter.ip + path + fileName;
    }

    /**
     * 桉模板导出excel
     *
     * @param templatePath 模板路径
     * @param dataBeginRow 数据起始行 从0开始
     * @param contents     结果集内容
     * @param fieldsName   结果集字段映射
     * @param exportName   导出文件名
     * @return
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws Exception
     */
    public static String exportByTemplate(String templatePath, Integer dataBeginRow, Collection<?> contents, String[] fieldsName, String exportName) throws Exception {
        //生成文件名
        String fileName = exportName + "_" + DateUtil.formatTime(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".xlsx";
        templatePath = "/moban/downloadExcel/template/" + templatePath;
        // 根据模板路径读取一个工作薄
        Workbook workbook = new XSSFWorkbook(new FileInputStream(templatePath));
        // 获取第一个表格
        Sheet sheetAt = workbook.getSheetAt(0);

        //遍历集合数据，产生数据行
        Iterator<?> it = contents.iterator();
        int index = dataBeginRow;

        CellStyle decimalStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        decimalStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
        while (it.hasNext()) {
            Row row = sheetAt.createRow(index);
            index++;
            Object obj = it.next();
            for (int i = 0; i < fieldsName.length; i++) {
                Cell cell = row.createCell(i);
                String getMethodName = "get"
                        + fieldsName[i].substring(0, 1).toUpperCase()
                        + fieldsName[i].substring(1);
                Class<?> tCls = obj.getClass();
                Method getMethod = tCls.getMethod(getMethodName,
                        new Class[]{});
                Object value = getMethod.invoke(obj, new Object[]{});
                /**如获取值比较特殊，可在这里扩展值的转换方法**/
                if (value == null) {
                    value = new String("");
                }
                if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof BigDecimal) {
                    cell.setCellStyle(decimalStyle);
                    cell.setCellValue(((BigDecimal) value).doubleValue());
                } else if (value instanceof Date) {
                    CellStyle styleB = workbook.createCellStyle();
                    styleB.setDataFormat(workbook.createDataFormat().getFormat("m/d/yy"));
                    cell.setCellStyle(styleB);
                    cell.setCellValue((Date) value);
                } else {
                    XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                    cell.setCellValue(richString);
                }
            }
        }
        //生成文件夹路径
        String path = ExportUtils.newFilePath();
        File file = new File(path + fileName);
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();

        return GlobalParameter.ip + path + fileName;
    }


    public static String exportExcel(String[] headers,Collection<?> contents,String[] fieldsName,List<Integer> hiddenCloumnIndex, String exportType, String exportName) throws Exception {

        String export = null;
        if (exportType != null) {
            export = exportType + "_" + exportName;
        } else {
            export = exportName;
        }

        //生成文件名
        String fileName = export + "_" + DateUtil.formatTime(System.currentTimeMillis(), "yyyyMMddHHmmssSSS")+ ".xlsx";

        // 声明一个工作薄
        Workbook workbook = null != contents && contents.size() > ROWS ? new SXSSFWorkbook(READROWS) : new XSSFWorkbook();
        // 生成一个表格
        Sheet sheet = workbook.createSheet(export);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short)15);

        //判断是否隐藏第一列
        if(hiddenCloumnIndex != null && !hiddenCloumnIndex.isEmpty()){
            for (Integer i : hiddenCloumnIndex){
                sheet.setColumnHidden(i, true);
            }
        }
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();

        /**如有特殊样式和字体，可在这里新增或扩展**/
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        //设置excel第一行头信息显示
        Row row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //冻结第一行
        sheet.createFreezePane( 0, 1, 0, 1 );

        //遍历集合数据，产生数据行
        Iterator<?> it = contents.iterator();
        int index = 0;
        while(it.hasNext()){
            index++;
            row = sheet.createRow(index);
            Object obj =  it.next();
            for (int i = 0; i < fieldsName.length; i++) {
                Cell cell = row.createCell(i);
                String getMethodName = "get"
                        + fieldsName[i].substring(0, 1).toUpperCase()
                        + fieldsName[i].substring(1);
                Class<?> tCls = obj.getClass();
                Method getMethod = tCls.getMethod(getMethodName,
                        new Class[] {});
                Object value = getMethod.invoke(obj, new Object[] {});
                /**如获取值比较特殊，可在这里扩展值的转换方法**/
                if(value == null){
                    value = new String("");
                }
                if(value instanceof Integer){
                    cell.setCellValue((Integer)value);
                }else if(value instanceof BigDecimal){
                    DecimalFormat df = new DecimalFormat("0.00");
                    cell.setCellValue(df.format(((BigDecimal)value).doubleValue()));
					/*cell.setCellValue(((BigDecimal)value).doubleValue());*/
                }else if (value instanceof Date){
                    CellStyle styleB = workbook.createCellStyle();
                    styleB.setDataFormat(workbook.createDataFormat().getFormat("m/d/yy"));
                    cell.setCellStyle(styleB);
                    cell.setCellValue((Date)value);
                }else{
                    XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                    cell.setCellValue(richString);
                }
            }
        }
        //生成文件夹路径
        String path = ExportUtils.newFilePath();
        File file = new File(path+fileName);
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();

        return path + fileName;
    }

    public static String exportExcel(String[] headers, Collection<?> contents, String[] fieldsName, boolean hiddenFirst, String exportType, String exportName) throws Exception {

        String export = null;
        if (exportType != null) {
            export = exportType + "_" + exportName;
        } else {
            export = exportName;
        }

        //生成文件名
        String fileName = export + "_" + DateUtil.formatTime(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".xlsx";

        // 声明一个工作薄
        //XSSFWorkbook workbook = new XSSFWorkbook();
        Workbook workbook = null != contents && contents.size() > ROWS ? new SXSSFWorkbook(READROWS) : new XSSFWorkbook();
        // 生成一个表格
        Sheet sheet  = workbook.createSheet(export);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);

        //判断是否隐藏第一列
        if (hiddenFirst) {
            sheet.setColumnHidden(0, true);
        }
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();

        /**如有特殊样式和字体，可在这里新增或扩展**/
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        //设置excel第一行头信息显示
        Row row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
        	Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //冻结第一行
        sheet.createFreezePane(0, 1, 0, 1);
        //字符串样式
        CellStyle stringStyle = workbook.createCellStyle();
        stringStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        //数字格式化样式
        CellStyle decimalStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        decimalStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
        decimalStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        //时间格式化样式
        CellStyle styleB = workbook.createCellStyle();
        styleB.setDataFormat(workbook.createDataFormat().getFormat("m/d/yy"));
        styleB.setAlignment(HSSFCellStyle.ALIGN_LEFT);



        //遍历集合数据，产生数据行
        Iterator<?> it = contents.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Object obj = it.next();
            for (int i = 0; i < fieldsName.length; i++) {
                Cell cell = row.createCell(i);
                String getMethodName = "get"
                        + fieldsName[i].substring(0, 1).toUpperCase()
                        + fieldsName[i].substring(1);
                Class<?> tCls = obj.getClass();
                Method getMethod = tCls.getMethod(getMethodName,
                        new Class[]{});
                Object value = getMethod.invoke(obj, new Object[]{});
                /**如获取值比较特殊，可在这里扩展值的转换方法**/
                if (value == null) {
                    value = new String("");
                }
                if (value instanceof Integer) {
                    cell.setCellStyle(stringStyle);
                    cell.setCellValue((Integer) value);
                } else if (value instanceof BigDecimal) {
                    if (null != value || value != BigDecimal.ZERO) {
                        cell.setCellStyle(decimalStyle);
                        cell.setCellValue(((BigDecimal) value).doubleValue());
                    } else {
                        cell.setCellStyle(stringStyle);
                        cell.setCellValue(0);
                    }
                } else if (value instanceof Date) {
                    cell.setCellStyle(styleB);
                    cell.setCellValue((Date) value);
                } else {
                    XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                    cell.setCellStyle(stringStyle);
                    cell.setCellValue(richString);
                }
            }
        }
        //生成文件夹路径
        String path = ExportUtils.newFilePath();
        File file = new File(path + fileName);
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();

        return /*GlobalParameter.ip + */path + fileName;
    }

    public static String exportExcelByWidth(String[] headers, Collection<?> contents, String[] fieldsName, boolean hiddenFirst, String exportType, String exportName, Map<Integer, Integer> width) throws Exception {

        String export = null;
        if (exportType != null) {
            export = exportType + "_" + exportName;
        } else {
            export = exportName;
        }

        //生成文件名
        String fileName = export + "_" + DateUtil.formatTime(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".xlsx";

        // 声明一个工作薄
        Workbook workbook = null != contents && contents.size() > ROWS ? new SXSSFWorkbook(READROWS) : new XSSFWorkbook();
        // 生成一个表格
        Sheet sheet = workbook.createSheet(export);
        // 设置表格默认列宽度 汉字是512，数字是256.
        for (Integer key : width.keySet()) {
            sheet.setColumnWidth(key, width.get(key));
        }

        //判断是否隐藏第一列
        if (hiddenFirst) {
            sheet.setColumnHidden(0, true);
        }
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();

        /**如有特殊样式和字体，可在这里新增或扩展**/
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        //设置excel第一行头信息显示
        Row row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //冻结第一行
        sheet.createFreezePane(0, 1, 0, 1);
        CellStyle decimalStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        decimalStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
        //遍历集合数据，产生数据行
        Iterator<?> it = contents.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Object obj = it.next();
            for (int i = 0; i < fieldsName.length; i++) {
                Cell cell = row.createCell(i);
                String getMethodName = "get"
                        + fieldsName[i].substring(0, 1).toUpperCase()
                        + fieldsName[i].substring(1);
                Class<?> tCls = obj.getClass();
                Method getMethod = tCls.getMethod(getMethodName,
                        new Class[]{});
                Object value = getMethod.invoke(obj, new Object[]{});
                /**如获取值比较特殊，可在这里扩展值的转换方法**/
                if (value == null) {
                    value = new String("");
                }
                if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof BigDecimal) {
                    if (null != value || value != BigDecimal.ZERO) {
                        cell.setCellStyle(decimalStyle);
                        cell.setCellValue(((BigDecimal) value).doubleValue());
                    } else {
                        cell.setCellValue(0);
                    }
                }else if(value instanceof Long){
                    if (null != value) {
                        cell.setCellValue(((Long)value).longValue());
                    } else {
                        cell.setCellValue(0);
                    }
                } else if (value instanceof Date) {
                    CellStyle styleB = workbook.createCellStyle();
                    styleB.setDataFormat(workbook.createDataFormat().getFormat("m/d/yy"));
                    cell.setCellStyle(styleB);
                    cell.setCellValue((Date) value);
                } else {
                    XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                    cell.setCellValue(richString);
                }
            }
        }
        //生成文件夹路径
        String path = ExportUtils.newFilePath();
        File file = new File(path + fileName);
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();

        return GlobalParameter.ip + path + fileName;
    }

    public static String exportTemplatePointExcel(String[] headers, Collection<?> contents, String[] fieldsName, boolean hiddenFirst, String exportType, String exportName) throws Exception {

        String export = null;
        if (exportType != null) {
            export = exportType + "_" + exportName;
        } else {
            export = exportName;
        }

        //生成文件名
        String fileName = export + "_" + DateUtil.formatTime(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".xlsx";

        // 声明一个工作薄
        Workbook workbook = null != contents && contents.size() > ROWS ? new SXSSFWorkbook(READROWS) : new XSSFWorkbook();
        // 生成一个表格
        Sheet sheet = workbook.createSheet(export);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);

        //判断是否隐藏第一列
        if (hiddenFirst) {
            sheet.setColumnHidden(0, true);
        }
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();

        /**如有特殊样式和字体，可在这里新增或扩展**/
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        //设置excel第一行头信息显示
        Row row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //冻结第一行
        sheet.createFreezePane(0, 1, 0, 1);

        //遍历集合数据，产生数据行
        Iterator<?> it = contents.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Object obj = it.next();
            for (int i = 0; i < fieldsName.length; i++) {
                Cell cell = row.createCell(i);
                String getMethodName = "get"
                        + fieldsName[i].substring(0, 1).toUpperCase()
                        + fieldsName[i].substring(1);
                Class<?> tCls = obj.getClass();
                Method getMethod = tCls.getMethod(getMethodName,
                        new Class[]{});
                Object value = getMethod.invoke(obj, new Object[]{});
                /**如获取值比较特殊，可在这里扩展值的转换方法**/
                if (value == null) {
                    value = new String("");
                }
                if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof BigDecimal) {
                    CellStyle styleB = getCellStyleStatic(workbook);
                    styleB.setDataFormat(workbook.createDataFormat().getFormat("0.000000"));
                    cell.setCellStyle(styleB);
                    cell.setCellValue(((BigDecimal) value).doubleValue());
                } else {
                    XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                    cell.setCellValue(richString);
                }
            }
        }
        //生成文件夹路径
        String path = ExportUtils.newFilePath();
        File file = new File(path + fileName);
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();

        return GlobalParameter.ip + path + fileName;
    }



    public static String newFilePath() {
        //当前日期
        Date date = new Date();
        //格式化并转换String类型
        String path = "E:/";
        //String path = "/moban/downloadExcel/" + new SimpleDateFormat("yyyy-MM-dd").format(date) + "/";
        //创建文件夹
        File f = new File(path);
        if (!f.exists())
            f.mkdirs();
        return path;
    }


    /**
     * 导出已经有表头的excel
     *
     * @param fields
     * @param contents
     * @param prxName
     * @return
     */
    public static String exportHasHeaderExcel(Workbook workbook, String[] fields, Collection<?> contents, String prxName) throws Exception {
        //生成文件名
        String fileName = prxName + DateUtil.formatTime(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".xlsx";
        Sheet sheet = workbook.getSheetAt(0);
        //设置excel第一行头信息显示
        Row row = null;
        //遍历集合数据，产生数据行
        Iterator<?> it = contents.iterator();
        int index = 1;
        while (it.hasNext()) {
        	index++;
            row = sheet.createRow(index);
            Object obj = it.next();
            for (int i = 0; i < fields.length; i++) {
                Cell cell = row.createCell(i);
                String getMethodName = "get"
                        + fields[i].substring(0, 1).toUpperCase()
                        + fields[i].substring(1);
                Class<?> tCls = obj.getClass();
                Method getMethod = tCls.getMethod(getMethodName,
                        new Class[]{});
                Object value = getMethod.invoke(obj, new Object[]{});
                /**如获取值比较特殊，可在这里扩展值的转换方法**/
                if (value == null) {
                    value = new String("");
                }
                XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                cell.setCellValue(richString);
            }
        }
        String path = ExportUtils.newFilePath();//生成文件夹路径
        File file = new File(path + fileName);
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();
        return GlobalParameter.ip + path + fileName;
    }




    //佣金统计数据填充
    private static int addStatisticData(Workbook workbook, Sheet sheet, int index, int length, int colApt, int area, int totalPrice, int unitPrice, int settleFee) {
        Row row = sheet.createRow(++index);//结算资料说明
        Cell cell = row.createCell(0);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellValue("结算资料说明：");

        row = sheet.createRow(++index);//可结算套数
        cell = row.createCell(0);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellValue("可结算套数");
        cell = row.createCell(length - 1);
        CellStyle styleB = getCellStyleStatic(workbook);
        styleB.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));
        cell.setCellStyle(styleB);
        cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula(getExcelColName(colApt + 2) + (index - 1));
        cell = row.createCell(length);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellValue("套");

        row = sheet.createRow(++index);//本次结算面积
        cell = row.createCell(0);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellValue("本次结算面积");
        cell = row.createCell(length - 1);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula(getExcelColName(area + 2) + (index - 2));
        cell = row.createCell(length);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellValue("㎡");

        row = sheet.createRow(++index);//本次结算合同总价
        cell = row.createCell(0);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellValue("本次结算合同总价");
        cell = row.createCell(length - 1);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula(getExcelColName(totalPrice + 2) + (index - 3));
        cell = row.createCell(length);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellValue("元");

        row = sheet.createRow(++index);//本次结算合同均价
        cell = row.createCell(0);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellValue("本次结算合同均价");
        cell = row.createCell(length - 1);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula(getExcelColName(unitPrice + 2) + (index - 4));
        cell = row.createCell(length);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellValue("元/㎡");

        row = sheet.createRow(++index);//本次结算代理费收入
        cell = row.createCell(0);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellValue("本次结算代理费收入 =");
        cell = row.createCell(length - 1);
        CellStyle style = getCellStyleStatic(workbook);
        /*Font font = style.getFont();
        font.setUnderline(FontUnderline.SINGLE);*/
        cell.setCellStyle(style);
        cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula(getExcelColName(settleFee + 2) + (index - 5));
        cell = row.createCell(length);
        cell.setCellStyle(getCellStyleStatic(workbook));
        cell.setCellValue("元");
        return index;
    }

    //佣金sheet底部样式填充
    private static void addSheetBottom(Workbook workbook, Sheet sheet, int index, int length) {
        Row row = null;
        Cell cell = null;
        for (int i = 1; i <= 10; i++) {
            row = sheet.createRow(index + i);
            for (int j = -1; j < length; j++) {
                if (i == 1 || i == 6) {
                    CellStyle styleBottom = workbook.createCellStyle();
                    styleBottom.setBorderTop(XSSFCellStyle.BORDER_DOUBLE);
                    cell = row.createCell(j + 1);
                    cell.setCellStyle(styleBottom);
                } else if (i == 10) {
                    CellStyle styleBottom = workbook.createCellStyle();
                    styleBottom.setBorderBottom(XSSFCellStyle.BORDER_DOUBLE);
                    cell = row.createCell(j + 1);
                    cell.setCellStyle(styleBottom);
                }
                if (j == length / 2 && (i == 1 || i == 6)) {
                    CellStyle styleBottom = workbook.createCellStyle();
                    cell = row.createCell(j + 1);
                    styleBottom.setBorderTop(XSSFCellStyle.BORDER_DOUBLE);
                    styleBottom.setBorderLeft(XSSFCellStyle.BORDER_THIN);
                    cell.setCellStyle(styleBottom);
                } else if (j == length / 2 && i == 10) {
                    CellStyle styleBottom = workbook.createCellStyle();
                    cell = row.createCell(j + 1);
                    styleBottom.setBorderBottom(XSSFCellStyle.BORDER_DOUBLE);
                    styleBottom.setBorderLeft(XSSFCellStyle.BORDER_THIN);
                    cell.setCellStyle(styleBottom);
                } else if (j == length / 2) {
                    CellStyle styleBottom = workbook.createCellStyle();
                    cell = row.createCell(j + 1);
                    styleBottom.setBorderLeft(XSSFCellStyle.BORDER_THIN);
                    cell.setCellStyle(styleBottom);
                }
            }
        }

    }

    /**
     * 获取excel列名
     *
     * @param index
     * @return
     */
    private static String getExcelColName(int index) {
        StringBuffer retStr = new StringBuffer();
        index--;
        char[] a = "a".toCharArray();
        int c = a[0];
        if (index < 26) {
            retStr.append((char) (c + index));
        } else {
            retStr.append((char) (c + (int) (index / 26) - 1)).append((char) (c + index % 26));
        }
        return retStr.toString();
    }

    private static CellStyle getCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        Font fontBody = workbook.createFont();
        fontBody.setFontName("宋体");
        fontBody.setFontHeightInPoints((short) 8);
        style.setFont(fontBody);
        return style;
    }

    private static CellStyle getCellStyleStatic(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("#,##0.00"));
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 8);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        return style;
    }

    public static String exportExcelForSheets(List<SheetVO> results, String exportName) throws Exception {
        String path = ExportUtils.newFilePath();
        //生成文件名
        String fileName = exportName + "_" + DateUtil.formatTime(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".xlsx";
        // 声明一个工作薄
        Workbook workbook = null != results && results.size() > ROWS ? new SXSSFWorkbook(READROWS) : new XSSFWorkbook();
        for (SheetVO sheetvo : results) {
            // 生成一个表格
            Sheet sheet = workbook.createSheet(sheetvo.getSheetName());
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth((short) 15);
            // 生成一个样式
            CellStyle style = workbook.createCellStyle();

            /**如有特殊样式和字体，可在这里新增或扩展**/
            // 设置这些样式
            style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            // 生成一个字体
            Font font = workbook.createFont();
            font.setColor(HSSFColor.BLACK.index);
            font.setFontHeightInPoints((short) 12);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            style.setFont(font);

            //设置excel第一行头信息显示
            Row row = sheet.createRow(0);
            List<String> headers = sheetvo.getHeader();
            for (short i = 0; i < headers.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(style);
                XSSFRichTextString text = new XSSFRichTextString(headers.get(i));
                cell.setCellValue(text);
            }
            //冻结第一行
            sheet.createFreezePane(0, 1, 0, 1);

            List<?> content = sheetvo.getContent();
            List<String> fieldName = sheetvo.getFieldName();
//	         String contentClazzName = sheetvo.getContentClazzName();


            //遍历集合数据，产生数据行
            Iterator<?> it = content.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                Object obj = it.next();
                for (int i = 0; i < fieldName.size(); i++) {
                    Cell cell = row.createCell(i);
                    String getMethodName = "get"
                            + fieldName.get(i).substring(0, 1).toUpperCase()
                            + fieldName.get(i).substring(1);
                    Class<?> tCls = obj.getClass();
                    Method getMethod;
                    getMethod = tCls.getMethod(getMethodName,
                            new Class[]{});
                    Object value = getMethod.invoke(obj, new Object[]{});
                    /**如获取值比较特殊，可在这里扩展值的转换方法**/
                    if (value == null) {
                        value = new String("");
                    }
                    if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    } else if (value instanceof BigDecimal) {
                        cell.setCellValue(((BigDecimal) value).doubleValue());
                    } else if (value instanceof Date) {
                        CellStyle styleB = workbook.createCellStyle();
                        styleB.setDataFormat(workbook.createDataFormat().getFormat("m/d/yy"));
                        cell.setCellStyle(styleB);
                        cell.setCellValue((Date) value);
                    } else {
                        XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                        cell.setCellValue(richString);
                    }

                }
            }
        }
        //生成文件夹路径
        File file = new File(path + fileName);
        try (OutputStream os = new FileOutputStream(file)) {
            workbook.write(os);
        }
        return path + fileName;
    }

	public static String exportExcelFlow(Map<String, List<String>> headerMap, Map<String, List<?>> result, Map<String, List<String>> fieldMap, String exportName) throws Exception  {
        //生成文件名
        String fileName = exportName + "_" + DateUtil.formatTime(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".xlsx";
        // 声明一个工作薄
        //XSSFWorkbook workbook = new XSSFWorkbook();
        Workbook workbook = new XSSFWorkbook();
        result.forEach((k, v) ->{
        	// 生成一个表格
        	Sheet sheet  = workbook.createSheet(k);
        	// 设置表格默认列宽度为15个字节
        	sheet.setDefaultColumnWidth((short) 15);
        	// 生成一个样式
        	CellStyle style = workbook.createCellStyle();

        	/**如有特殊样式和字体，可在这里新增或扩展**/
        	// 设置这些样式
        	style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        	style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        	style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        	style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        	style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        	style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        	style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        	// 生成一个字体
        	Font font = workbook.createFont();
        	font.setColor(HSSFColor.BLACK.index);
        	font.setFontHeightInPoints((short) 12);
        	font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        	// 把字体应用到当前的样式
        	style.setFont(font);

        	//设置excel第一行头信息显示
        	Row row = sheet.createRow(0);
        	List<String> headerList =headerMap.get(k);
        	for (short i = 0; i < headerList.size(); i++) {
        		Cell cell = row.createCell(i);
        		cell.setCellStyle(style);
        		XSSFRichTextString text = new XSSFRichTextString(headerList.get(i));
        		cell.setCellValue(text);
        	}

        	//冻结第一行
        	sheet.createFreezePane(0, 1, 0, 1);
        	CellStyle decimalStyle = workbook.createCellStyle();
        	DataFormat dataFormat = workbook.createDataFormat();
        	decimalStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));

        	List<String> fieldList = fieldMap.get(k);
        	//遍历集合数据，产生数据行
        	Iterator<?> it = v.iterator();
        	int index = 0;
        	while (it.hasNext()) {
        		index++;
        		row = sheet.createRow(index);
        		Object obj = it.next();
        		for (int i = 0; i < fieldList.size(); i++) {
        			Cell cell = row.createCell(i);
        			String getMethodName = "get"
        					+ fieldList.get(i).substring(0, 1).toUpperCase()
        					+ fieldList.get(i).substring(1);
        			Class<?> tCls = obj.getClass();
        			Object value = null;
					try {
						Method getMethod = tCls.getMethod(getMethodName,
								new Class[]{});
						value = getMethod.invoke(obj, new Object[]{});
					} catch (Exception e) {
						e.printStackTrace();
					}
        			/**如获取值比较特殊，可在这里扩展值的转换方法**/
        			if (value == null) {
        				value = new String("");
        			}
        			if (value instanceof Integer) {
        				cell.setCellValue((Integer) value);
        			} else if (value instanceof BigDecimal) {
        				if (null != value || value != BigDecimal.ZERO) {
        					cell.setCellStyle(decimalStyle);
        					cell.setCellValue(((BigDecimal) value).doubleValue());
        				} else {
        					cell.setCellValue(0);
        				}
        			} else if (value instanceof Date) {
        				CellStyle styleB = workbook.createCellStyle();
        				styleB.setDataFormat(workbook.createDataFormat().getFormat("m/d/yy"));
        				cell.setCellStyle(styleB);
        				cell.setCellValue((Date) value);
        			} else {
        				XSSFRichTextString richString = new XSSFRichTextString(value.toString());
        				cell.setCellValue(richString);
        			}
        		}
        	}
        });
        //生成文件夹路径
        String path = ExportUtils.newFilePath();
        File file = new File(path + fileName);
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();
        return GlobalParameter.ip + path + fileName;
	}

	/**
     * 桉模板导出excel
     *
     * @param templatePath 模板路径
     * @param dataBeginRow 数据起始行 从0开始
     * @param contents     结果集内容
     * @param fieldsName   结果集字段映射
     * @param exportName   导出文件名
     * @param unit   万元单位标识
     * @return
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws Exception
     */
    public static String exportByTemplateAdvert(String templatePath, Integer dataBeginRow, Collection<?> contents, String[] fieldsName, String exportName,Integer unit) throws Exception {
        //生成文件名
        String fileName = exportName + "_" + DateUtil.formatTime(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".xlsx";
        templatePath = "/moban/downloadExcel/template/" + templatePath;
        // 根据模板路径读取一个工作薄
        Workbook workbook = new XSSFWorkbook(new FileInputStream(templatePath));
        // 获取第一个表格
        Sheet sheetAt = workbook.getSheetAt(0);

        //遍历集合数据，产生数据行
        Iterator<?> it = contents.iterator();
        int index = dataBeginRow;

        CellStyle decimalStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        if(null!=unit && unit==2){//万元
        	decimalStyle.setDataFormat(dataFormat.getFormat("#,##0.000000"));
        }else if(null!=unit && unit==3){
        	decimalStyle.setDataFormat(dataFormat.getFormat("#,##0.0000"));
        }else{
        	decimalStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
        }
        while (it.hasNext()) {
            Row row = sheetAt.createRow(index);
            index++;
            Object obj = it.next();
            for (int i = 0; i < fieldsName.length; i++) {
                Cell cell = row.createCell(i);
                String getMethodName = "get"
                        + fieldsName[i].substring(0, 1).toUpperCase()
                        + fieldsName[i].substring(1);
                Class<?> tCls = obj.getClass();
                Method getMethod = tCls.getMethod(getMethodName,
                        new Class[]{});
                Object value = getMethod.invoke(obj, new Object[]{});
                /**如获取值比较特殊，可在这里扩展值的转换方法**/
                if (value == null) {
                    value = new String("");
                }
                if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof BigDecimal) {
                    cell.setCellStyle(decimalStyle);
                    cell.setCellValue(((BigDecimal) value).doubleValue());
                } else if (value instanceof Date) {
                    CellStyle styleB = workbook.createCellStyle();
                    styleB.setDataFormat(workbook.createDataFormat().getFormat("m/d/yy"));
                    cell.setCellStyle(styleB);
                    cell.setCellValue((Date) value);
                } else {
                    XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                    cell.setCellValue(richString);
                }
            }
        }
        //生成文件夹路径
        String path = ExportUtils.newFilePath();
        File file = new File(path + fileName);
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();

        return GlobalParameter.ip + path + fileName;
    }

    public static String exportStatisticsConsultReport(String[] headers, Collection<?> contents, String[] fieldsName, boolean hiddenFirst, String exportType, String exportName,Integer unit) throws Exception {

        String export = null;
        if (exportType != null) {
            export = exportType + "_" + exportName;
        } else {
            export = exportName;
        }

        //生成文件名
        String fileName = export + "_" + DateUtil.formatTime(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".xlsx";

        // 声明一个工作薄
        //XSSFWorkbook workbook = new XSSFWorkbook();
        Workbook workbook = null != contents && contents.size() > ROWS ? new SXSSFWorkbook(READROWS) : new XSSFWorkbook();
        // 生成一个表格
        Sheet sheet  = workbook.createSheet(export);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);

        //判断是否隐藏第一列
        if (hiddenFirst) {
            sheet.setColumnHidden(0, true);
        }
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();

        /**如有特殊样式和字体，可在这里新增或扩展**/
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        //设置excel第一行头信息显示
        Row row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //冻结第一行
        sheet.createFreezePane(0, 1, 0, 1);
        CellStyle decimalStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        if(null!=unit && unit==2){//万元
            decimalStyle.setDataFormat(dataFormat.getFormat("#,##0.000000"));
        }else{
            decimalStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
        }
        //遍历集合数据，产生数据行
        Iterator<?> it = contents.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Object obj = it.next();
            for (int i = 0; i < fieldsName.length; i++) {
                Cell cell = row.createCell(i);
                String getMethodName = "get"
                        + fieldsName[i].substring(0, 1).toUpperCase()
                        + fieldsName[i].substring(1);
                Class<?> tCls = obj.getClass();
                Method getMethod = tCls.getMethod(getMethodName,
                        new Class[]{});
                Object value = getMethod.invoke(obj, new Object[]{});
                /**如获取值比较特殊，可在这里扩展值的转换方法**/
                if (value == null) {
                    value = new String("");
                }
                if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof BigDecimal) {
                    if (null != value || value != BigDecimal.ZERO) {
                        cell.setCellStyle(decimalStyle);
                        cell.setCellValue(((BigDecimal) value).doubleValue());
                    } else {
                        cell.setCellValue(0);
                    }
                } else if (value instanceof Date) {
                    CellStyle styleB = workbook.createCellStyle();
                    styleB.setDataFormat(workbook.createDataFormat().getFormat("m/d/yy"));
                    cell.setCellStyle(styleB);
                    cell.setCellValue((Date) value);
                } else {
                    XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                    cell.setCellValue(richString);
                }
            }
        }
        //生成文件夹路径
        String path = ExportUtils.newFilePath();
        File file = new File(path + fileName);
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();

        return GlobalParameter.ip + path + fileName;
    }
    
    /**
     * 结算单异常信息---桉模板导出excel
     *
     * @param templatePath 模板路径
     * @param dataBeginRow 数据起始行 从0开始
     * @param contents     结果集内容
     * @param fieldsName   结果集字段映射
     * @param exportName   导出文件名
     * @return
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws Exception
     */
    public static String exportCommissionExceptionByTemplate(String templatePath, Integer dataBeginRow, List<Row> contents, String[] fieldsName, String exportName) throws Exception {
        //生成文件名
        String fileName = exportName + "_" + DateUtil.formatTime(System.currentTimeMillis(), "yyyyMMddHHmmssSSS") + ".xlsx";
        templatePath = "/moban/downloadExcel/template/" + templatePath;
        // 根据模板路径读取一个工作薄
        Workbook workbook = new XSSFWorkbook(new FileInputStream(templatePath));
        // 获取第一个表格
        Sheet sheetAt = workbook.getSheetAt(0);

        //遍历集合数据，产生数据行
        CellStyle styleRed = workbook.createCellStyle();
        styleRed.setFillForegroundColor(IndexedColors.RED.getIndex());//标红样式
        styleRed.setFillPattern(CellStyle.SOLID_FOREGROUND);
        int index = dataBeginRow;

        CellStyle decimalStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        decimalStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
        for(Row r:contents){
            Row row = sheetAt.getRow(index);
            if(index>=2)
            	row=sheetAt.createRow(index);
            index++;
            for (int i = 0; i < fieldsName.length; i++) {
            	Cell cell = row.createCell(i);
                Object value = r.getCell(i);
                /**如获取值比较特殊，可在这里扩展值的转换方法**/
                if (value == null) {
                    value = new String("");
                }
                if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof BigDecimal) {
                    cell.setCellStyle(decimalStyle);
                    cell.setCellValue(((BigDecimal) value).doubleValue());
                } else if (value instanceof Date) {
                    CellStyle styleB = workbook.createCellStyle();
                    styleB.setDataFormat(workbook.createDataFormat().getFormat("m-d-yy"));
                    cell.setCellStyle(styleB);
                    cell.setCellValue((Date) value);
                } else {
                    if ((i >= 7 && i <= 10) || i > 13) {
                    	//数据格式化
                    	CellStyle styleNumber = workbook.createCellStyle();
                	    DataFormat format = workbook.createDataFormat();
                	    styleNumber.setDataFormat(format.getFormat("0.00"));
                	    
                    	/*if(fieldsName[i].equals("canSettleSum")){
                    		value=ImportUtils.getCellValueByFormat(r.getCell(i),1,null);
                        	if(StringUtils.isEmpty(value) || "0.00".equals(value)){
                        		styleRed.setDataFormat(format.getFormat("0.00"));
                        		cell.setCellStyle(styleRed);
                        		cell.setCellValue(Double.valueOf("".equals(value.toString()) ? "0" : value.toString()));
                        	}else{
                        		cell.setCellStyle(styleNumber);
                        		cell.setCellValue(Double.valueOf("".equals(value.toString()) ? "0" : value.toString()));
                        	}
                        }else */
                        if(fieldsName[i].equals("agentRate")){
                        	BigDecimal big=new BigDecimal(value.toString());
                        	if(StringUtils.isEmpty(value) || 0==(BigDecimal.ZERO.compareTo(big))){
                                styleRed.setDataFormat(format.getFormat("0.00"));
                                cell.setCellStyle(styleRed);
                                cell.setCellValue(Double.valueOf("".equals(value.toString()) ? "0" : value.toString()));
                        	}else{
                        	    cell.setCellStyle(styleNumber);
                        	    cell.setCellValue(Double.valueOf("".equals(value.toString()) ? "0" : value.toString()));
                        	}
                        }else if(fieldsName[i].equals("agentFee")){
                        	value=ImportUtils.getCellValueByFormat(r.getCell(i),1,null);
                        	if(StringUtils.isEmpty(value) || "0.00".equals(value)){
                        		styleRed.setDataFormat(format.getFormat("0.00"));
                                cell.setCellStyle(styleRed);
                                cell.setCellValue(Double.valueOf("".equals(value.toString()) ? "0" : value.toString()));
                        	}else{
                        	    cell.setCellStyle(styleNumber);
                        	    cell.setCellValue(Double.valueOf("".equals(value.toString()) ? "0" : value.toString()));
                        	}
                        }else{
                        	cell.setCellStyle(styleNumber);
                        	cell.setCellValue(Double.valueOf("".equals(value.toString()) ? "0" : value.toString()));
                        }
                    }else{
                    	XSSFRichTextString richString = new XSSFRichTextString(value.toString());
                        cell.setCellValue(richString);
                    }
                }
            }
        }
        //生成文件夹路径
        String path = ExportUtils.newFilePath();
        File file = new File(path + fileName);
        OutputStream os = new FileOutputStream(file);
        workbook.write(os);
        os.close();

        return GlobalParameter.ip + path + fileName;
    }
}
