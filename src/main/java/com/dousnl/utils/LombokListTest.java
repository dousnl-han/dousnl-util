package com.dousnl.utils;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2020/8/21
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2020/8/21       hanliangliang     新增              1001
 ********************************************************************/

@Slf4j
@Data
public class LombokListTest {

    private String name;

    private List<Integer> ids;

    private List<User> users;


    public static void main(String[] args) {
        List<String> list=Lists.newArrayList();
        list.addAll(Arrays.asList("1"));
        if (list.contains("1")){
            System.out.println("111122");
        }
        System.out.println(JSON.toJSONString(list));
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if ("2".equals(next)){
                iterator.remove();
            }
        }
        System.out.println(JSON.toJSONString(list));
        Set<Integer> set= Sets.newHashSet();
        //int i = RandomUtils.nextInt(list.size());
        log.error("123,user:{}",1);
    }

}
