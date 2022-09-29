package com.dousnl.utils;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.utils.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.models.auth.In;
import lombok.Data;
import sun.nio.cs.US_ASCII;

import java.lang.instrument.Instrumentation;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 计算对象大小
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/3/26 10:39
 */
public class ObjectSize {
    private static volatile Instrumentation instru;

    public static void premain(String args, Instrumentation inst) {
        instru = inst;
    }

    public static Long getSizeOf(Object object) {
        if (instru == null) {
            throw new IllegalStateException("Instrumentation is null");
        }
        return instru.getObjectSize(object);
    }


    public static void main(String[] args) throws ParseException {
//将书籍资源LIST转换成MAP,做快速索引用
        Map<Integer, User> resultBooksMap = new HashMap<>(1);
        User u1=new User(18,"18");
        User u2=new User(18,"19");
        User u3=new User(19,"19");
        HashMap<Object, Object> hashMap = Maps.newHashMap();
        UserTest userTest= new UserTest();
        userTest.setType(1);
        userTest.setSkuIds(Lists.newArrayList(1,2));
        UserTest userTest1= new UserTest();
        userTest1.setType(2);
        userTest1.setSkuIds(Lists.newArrayList(1,2));
        ArrayList<UserTest> userTests = Lists.newArrayList(userTest, userTest1);
        String s = JSON.toJSONString(userTests);
        System.out.println(JSON.toJSONString(userTests));

        List<UserTest> userTests1 = JSON.parseArray(s, UserTest.class);
        System.out.println(userTests1);

        hashMap.put(1,Lists.newArrayList(1,2,3));
        hashMap.put(2,Lists.newArrayList(4,5,6));
        System.out.println(JSON.toJSONString(hashMap));
        //u3.setNum(0L);

        ArrayList<User> users = Lists.newArrayList(u1, u2,u3);

        List<Integer> collect = users.stream().map(User::getAge).distinct().collect(Collectors.toList());

        System.out.println("collect："+collect);

        ArrayList<Integer> integers = Lists.newArrayList(1, null, 0);

        List<String> bizIdList = integers.stream().map(String::valueOf).collect(Collectors.toList());

        System.out.println(bizIdList);

        Map<String, User> allRights = new HashMap<>(users.size());

        bizIdList.stream().forEach(e->{
            User user = allRights.get(e);
            user=new User();
            if (Objects.nonNull(u3)){
                //user.setDa(new Date(u3.getNum()));
            }
            if (Objects.nonNull(user)) {
                System.out.println(user);
            }
        });

        Set<Integer> booksId = users.stream().map(User::getAge).collect(Collectors.toSet());

        System.out.println(booksId);

        for (User dtoData : users) {
            resultBooksMap.put(dtoData.getAge(), dtoData);
        }
        System.out.println(resultBooksMap);
    }

    @Data
    public static class UserTest{

        private Integer type;
        private List skuIds;
    }
}
