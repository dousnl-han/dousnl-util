package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
//import com.dousnl.service.ContstructService;
import com.dousnl.utils.enums.FileType;
import com.dousnl.utils.execl.ExportUtils;
import com.dousnl.utils.execl.ImportUtils;
import com.dousnl.utils.freemud.ExcelView;
import com.dousnl.utils.response.Resp;
import com.dousnl.vo.RankInfoFlowConfig;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/9/12 13:58
 */

@Controller
@RequestMapping("/execl")
public class ExeclController {

    @Value("${server.port}")
    private String port;

//    @Autowired
//    private ContstructService contstructService;
//    @Autowired
//    private DusyService busyService;

    @RequestMapping("/xp3")
    @ResponseBody
    public String xp3(HttpServletRequest request, HttpServletResponse response) {
        return ">>>>>>"+port+"端口";
    }

    @RequestMapping("/xp22")
    public void xp1() throws Exception {
//        System.out.println(">>>>>>>>>>>>execl.contstructService.busyService>>>>>"+contstructService.getBusyService().toString());
//        System.out.println(">>>>>>>>>>>>execl.busyService>>>>>"+busyService.toString());
    }

    @RequestMapping("/xp")
    @Deprecated
    public void xp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> headers = new ArrayList<String>();
        headers.add("姓名");
        headers.add("年龄");
        headers.add("地址");
        ExcelView vi = new ExcelView();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("headers", headers);
        List<User> list = new ArrayList<User>();
        List<User> list1 = new ArrayList<User>();
        User u = new User("张三", 18, "北京");
        list.add(u);
        User u1 = new User("李四", 20, "上海");
        list1.add(u1);
        List<List<User>> datas = Lists.newArrayList();
        datas.add(list);
        datas.add(list1);
        model.put("datas", datas);
        vi.renderMergedOutputModel(model, request, response);
    }

    /**
     * 浏览器导出execl
     * public static void exportExcelFlush(String[] headers, Collection<?> contents, String[] fieldsName,
     * boolean hiddenFirst, String exportName, HttpServletRequest request, HttpServletResponse response) throws Exception {
     * exportExcelFlush(headers, contents, fieldsName, hiddenFirst, null, exportName, request, response);
     * }
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/xp1")
    public void xp1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> headers = new ArrayList<String>();
        headers.add("姓名");
        headers.add("年龄");
        headers.add("地址");
        ExcelView vi = new ExcelView();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("headers", headers);
        User u = new User("张三", 18, "北京");
        User u1 = new User("李四", 20, "上海");
        List<User> list = new ArrayList<User>();
        list.add(u);
        list.add(u1);
        String[] filds = {"name", "age", "address"};
        ExportUtils.exportExcelFlush(headers.toArray(new String[headers.size()]), list, filds, false, "测试execl", request, response);
    }

    /**
     * 导入execl
     * @param excelFile
     * @return
     * @throws Exception
     */
    @PostMapping ("/imp")
    public Resp imp(@RequestParam(value = "execlFile") MultipartFile excelFile) throws Exception {
        try {
            boolean fileTypeError = FileType.checkExcelType(excelFile);
            if (!fileTypeError) {
                return Resp.failed("上传的文件不是Excel");
            }
            Workbook book = WorkbookFactory.create(excelFile.getInputStream());
            Sheet sheet = book.getSheetAt(0);
            int rowCount = sheet.getLastRowNum();
            System.out.println("*************导入的excel一共----" + rowCount + "----行记录******");
            //校验传入文件格式，判断第一行第一列是否名称是否为"房源"
//            String column1 = ImportUtils.getCellValue(sheet.getRow(0).getCell(0));
//            String column2 = ImportUtils.getCellValue(sheet.getRow(0).getCell(1));
//            String column3 = ImportUtils.getCellValue(sheet.getRow(0).getCell(2));
//            String column4 = ImportUtils.getCellValue(sheet.getRow(0).getCell(3));
//            String column5 = ImportUtils.getCellValue(sheet.getRow(0).getCell(4));
//            if (!column1.equals("产品名称(系统中)") || !column2.equals("房号(系统中)")
//                    || !column3.equals("工号") || !column4.equals("姓名")
//                    || !column5.equals("应发奖金(元)")) {
//                return Resp.failed("excel格式不正确！");
//            }

            final int lastRowNum = sheet.getLastRowNum();

            List<RankInfoFlowConfig> objects = Lists.newArrayList();
            for (int i=1;i<=lastRowNum;i++){
                Row row = sheet.getRow(i);
                if (null == row) {
                    continue;
                }
                RankInfoFlowConfig rankInfoFlowConfig = new RankInfoFlowConfig();
                Integer rankId = Integer.valueOf(ImportUtils.getCellValue(row.getCell(0)));
                String rankName = ImportUtils.getCellValue(row.getCell(1));
                String rankCode = ImportUtils.getCellValue(row.getCell(2));
                String rankCode1 = ImportUtils.getCellValue(row.getCell(3));

                rankInfoFlowConfig.setRankId(rankId);
                rankInfoFlowConfig.setRankName(rankName);
                rankInfoFlowConfig.setRankCacheCode(rankCode);
                rankInfoFlowConfig.setRankCode(rankCode1);
                objects.add(rankInfoFlowConfig);
            }

            System.out.println(JSON.toJSONString(objects));

            if (rowCount < 1) {
                return Resp.failed("excel没有数据");
            }
            //importExecl(sheet, rowCount);
            //return bonusTemplatePointService.importTemplatePoint(sheet);
            return null;

        } catch (Exception e) {
            System.out.println("上传异常：" + e);
            return Resp.failed("上传失败");
        }
    }

    private void importExecl(Sheet sheet, int rowCount) {
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            if (null == row) {
                continue;
            }
            String cellValue = ImportUtils.getCellValue(row.getCell(0));
            String cellValue1 = ImportUtils.getCellValue(row.getCell(1));
            String cellValue2 = ImportUtils.getCellValue(row.getCell(2));
            /**
             * 以下获取到cell内容实现具体业务
             */
        }
    }
}
