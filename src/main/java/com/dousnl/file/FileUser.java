package com.dousnl.file;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2022/1/27
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2022/1/27       hanliangliang     新增              1001
 ********************************************************************/
@Data
public class FileUser {
    private String taskId;
    private String userId;
    private Date date;

    public FileUser() {
    }

    public static void main(String[] args) {
        String configStr = null;
        User user = JSON.parseObject(configStr, User.class);
        ArrayList<String> mobileList = Lists.newArrayList("(13162505297)","(15664161616)","(561631631631)");
        List<String> mobileListSearch = mobileList.stream().map(e->e.replace("(", "").replace(")", "")).collect(Collectors.toList());
        System.out.println(mobileListSearch);
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        ArrayList<Integer> integers1 = Lists.newArrayList(1, 2, 3, 4);
        ArrayList<Object> objects = Lists.newArrayList();
        for (Integer i : integers) {
            if (i > 2) {
                continue;
            }
            for (Integer j : integers) {
                if (i.equals(j)) {
                    if (i > 2) {
                        continue;
                    }
                }
            }
            objects.add(i);
        }
        System.out.println(objects);
    }
}
