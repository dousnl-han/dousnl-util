package com.dousnl.utils.execl;

import com.dousnl.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/9/12 10:32
 */
public class ExeclTest {

    /**
     * public static String exportExcel(String[] headers, Collection<?> contents, String[] fieldsName, boolean hiddenFirst, String exportName)
     * {
     * return exportExcel(headers, contents, fieldsName, hiddenFirst, null, exportName);
     * }
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        User u = new User("张三", 18, "北京");
        User u1 = new User("李四", 20, "上海");
        List<String> headers = new ArrayList<String>();
        List<User> list = new ArrayList<User>();
        headers.add("姓名");
        headers.add("年龄");
        headers.add("地址");
        list.add(u);
        list.add(u1);
        String[] filds = {"name", "age", "address"};
        ExportUtils.exportExcel(headers.toArray(new String[headers.size()]), list, filds, false, "测试execl");
    }
}
