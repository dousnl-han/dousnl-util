package com.dousnl.file;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.Task;
import com.dousnl.domain.TasklL;
import com.dousnl.domain.User;
import com.dousnl.utils.date.DateUtil;
import com.dousnl.utils.execl.ExcelUtil;
import com.dousnl.utils.http.HttpClentUtils;
import com.dousnl.utils.http.Result;
import com.google.common.collect.Lists;
import org.apache.lucene.util.RamUsageEstimator;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2022/1/27
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2022/1/27       hanliangliang     新增              1001
 ********************************************************************/
@Component
public class ReadFileSendPostRequestUtil {


    public void sendPostReq() throws IOException {
        String url = "http://127.0.0.1:9999/redis/sh";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        List<FileUser> list = ExcelUtil.importDataFromExcel(new FileUser(),
                new FileInputStream("E://fandeng-orch.xls"),
                "fandeng-orch.xls");
        //System.out.println(list);
        System.out.println(RamUsageEstimator.shallowSizeOf(list)+"字节");
        String date="2022-01-26 16:05:00";


        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            FileUser user = list.get(i);
            Map<String, Object> params = new HashMap<>();
            params.put("taskId", user.getTaskId());
            params.put("userId", user.getUserId());
            params.put("date", date);

            Result post = HttpClentUtils.postJson(url, headers, params, "UTF-8");
            System.out.println(post.getBody());
        }
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }


    public static void main(String[] args) throws IOException {
        //Map<String, Object> params = new HashMap<>();
        //params.put("taskId", "1");
        //params.put("userId", new Date());
        //System.out.println(JSON.toJSONString(params));
        //new ReadFileSendPostRequestUtil().sendPostReq();

        Task task = new Task();
        task.setUserId(111);
        ArrayList<TasklL> objects = Lists.newArrayList();
        TasklL tasklL = new TasklL();
        tasklL.setTaskId(222);
        tasklL.setBeginTime("2022-01-28 16:05:00");
        tasklL.setEndTime("2022-01-28 16:05:00");
        objects.add(tasklL);
        task.setList(objects);

        System.out.println(JSON.toJSONString(task));
    }

}
