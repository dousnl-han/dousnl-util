package com.dousnl.controller;

import com.dousnl.domain.User;
import com.dousnl.execl.ExportUtils;
import com.dousnl.execl.freemud.ExcelView;
import com.google.common.collect.Lists;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * public static void exportExcelFlush(String[] headers, Collection<?> contents, String[] fieldsName,
     * boolean hiddenFirst, String exportName, HttpServletRequest request, HttpServletResponse response) throws Exception {
     *         exportExcelFlush(headers, contents, fieldsName, hiddenFirst, null, exportName, request, response);
     *     }
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
        list.add(u1);String[] filds = {"name", "age", "address"};
        ExportUtils.exportExcelFlush(headers.toArray(new String[headers.size()]), list, filds, false, "测试execl", request, response);
    }
}
