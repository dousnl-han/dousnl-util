package com.dousnl.domain;

import com.dousnl.utils.date.DateUtil;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2022/3/13
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2022/3/13       hanliangliang     新增              1001
 ********************************************************************/
@Data
public class TestUser {

    private String name;
    private Integer age;
    private Date dd;
    private Integer id;
    private Integer num;

    @SneakyThrows public static void main(String[] args) {
        TestUser testUser = new TestUser();
        TestUser testUser1 = new TestUser();
        ArrayList<TestUser> testUsers = Lists.newArrayList(testUser, testUser1);
        testUsers.sort(Comparator.comparing(TestUser::getDd, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());

        List<Integer> finalExcludeSkuIds = Lists.newArrayList();
        if (finalExcludeSkuIds.contains(1)){

        }
        finalExcludeSkuIds.add(1);
        System.out.println(finalExcludeSkuIds);

        System.out.println(testUsers);

        for (int j=0;j<3;j++){
            int z =j ;
            if (Lists.newArrayList(1,2,3).stream().filter(e->e.equals(z)).findFirst().orElse(null) != null){
                continue;
            }
            System.out.println(2);
        }
    }
}
