package com.dousnl.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.domain.User;
import com.dousnl.utils.date.DateUtil;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/22 14:37
 */
public class IntegerEncryptTool {

    private static final char[][] charGroups = new char[][]{{'z', 'k', 'n', 'x', 's', 'w', 't', '9', '7'}, {'j', 'i', 'o', 'b', '5', 'h', '3', 'r', '8'}, {'f', 'c', 'm', '0', '6', 'p', 'e', 'y', 'q'}, {'l', 'a', '4', '2', '1', 'd', 'g', 'v', 'u'}};

    private static final Logger logger = LoggerFactory.getLogger(IntegerEncryptTool.class);

    public IntegerEncryptTool() {
    }

    public static String encrypt(int value) {
        Random random = new Random((long)value);
        if (value == 0) {
            char[] zeroChars = charGroups[0];
            return zeroChars[random.nextInt(zeroChars.length)] + "";
        } else {
            StringBuilder builder = new StringBuilder(16);

            for(int i = 30; i >= 0; i -= 2) {
                int segValue = value >> i & 3;
                char[] chars = charGroups[segValue];
                builder.append(chars[random.nextInt(chars.length)]);
            }

            return builder.toString();
        }
    }

    public static int decrypt(String text) {
        if (!"".equals(text) && null != text) {
            char[] textArray = text.toCharArray();
            int value = 0;

            for(int i = text.length() - 1; i >= 0; --i) {
                char ch = textArray[i];
                int segValue = -1;

                for(int j = 0; j < 4; ++j) {
                    if (indexOf(charGroups[j], ch) != -1) {
                        segValue = j;
                        break;
                    }
                }

                if (segValue < 0) {
                    throw new IllegalArgumentException("Invalid character in cipher: text");
                }

                value |= segValue << (textArray.length - i - 1) * 2;
            }

            return value;
        } else {
            throw new IllegalArgumentException(text);
        }
    }

    private static int indexOf(char[] a, char key) {
        for(int i = 0; i < a.length; ++i) {
            if (a[i] == key) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws ParseException {
        //String str="NdochBwAilT88VKk/oNhHob85tsF2IDi3ydtSsLTVsgHy63HNk0SJrH+aXO6C7it0/PKd3mITRBv4PbD5SCUqkATX3+XwVPTdeu6M85qY6n6ESEdrHc6TVz0NSzAYsWKS1+gNJeWrlZ9zPI1c22JZbBSJU/eoOBws3Nd5yJPf44=";
        //System.out.println(decrypt(str));"
        String str="sssss11".replace("11","22");

        str.replace("22","33");

        System.out.println(str);
        ArrayList<Integer> objects1 = Lists.newArrayList();
        Long maxId = objects1.stream().map(Long::valueOf).max(Long::compare).orElse(null);

        try {
            int i = 1 / 0;
        } catch (Exception e) {
            logger.error("sdfsdfsf:{},e:{}", str, e);
        }
        Integer userId=11131;
        Long orderNum=1241242342L;
        System.out.println(String.format("会员编号%s,购买樊登讲书多听多送卡，订单编号%s", userId, orderNum));
        String sprString = "[]";
        List<Integer> listRegisterSource = JSON.parseArray(sprString, Integer.class);
        if (listRegisterSource.contains(null)) {
            System.out.println(111);
        }
        List<Integer> batchIdList = JSON.parseArray( "[2250,2252,\n2700,2701]", Integer.class);

        if (batchIdList.contains(2700)){
            System.out.println(2700);
        }

        List<String> batchIdList1 = Arrays.stream("2250,2252,\n2700,2701".split(",")).collect(Collectors.toList());

        if (batchIdList1.contains(2700)){
            System.out.println(2700);
        }

        List<List<Integer>> partition = Lists.partition(Lists.newArrayList(1,2,3,4,5,6), 4);
        System.out.println(partition);

        System.out.println("12312314324："+decrypt("77vyllb8qa0093dm"));
        System.out.println(0x7fffffff);
        System.out.println(encrypt(500007257));
        System.out.println("a".concat("fsfsd"));
        User u=new User();
        u.setOwn(true);
        u.setAt(u.getOwn());
        u.setDate(DateUtil.parse("2021-07-01","yyyy-MM-dd"));
        System.out.println(u);
        Object cou=null;
        Integer count = Optional.ofNullable(cou).map(g->Integer.valueOf(g.toString())).orElse(0);
        Optional.ofNullable(u.getAge()).ifPresent(e -> {
            e.longValue();

        });
        System.out.println(count);
        Date currentDate = DateUtil.getCurrentDate();
        if (u.getDate().compareTo(currentDate)>0){
            System.out.println("da is gatter than new date");
        }
        Date date = new Date();
        if (u.getDate().compareTo(date)>0){
            System.out.println("da is gatter than new date");
        } else
            if(1==1){
                System.out.println(1111);
            }else if(2==2){
                System.out.println(2222);
            }
        String str1="";
        str1=realName(str);
        System.out.println("str:"+str);
        Boolean flag = getFlag();
        if (flag == null || !flag) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        Integer orderNum1=1412414123;
        System.out.println(DateUtil.parse("2021-07-20","yyyy-MM-dd").getTime());
        System.out.println(DateUtil.parse("2021-07-25","yyyy-MM-dd").getTime());
        String jumpUrlConfig ="https://deeplink.dushu.io/link/index.html?op=jump&view=fillInAddress&orderNumber=#";
        jumpUrlConfig = jumpUrlConfig.replaceFirst("#",orderNum.toString());
        jumpUrlConfig.concat("1");

        System.out.println(jumpUrlConfig);
        
        User user = new User();
        user.setName("111");
        user.setAge(20);
        user.setDate(new Date());
        JSONObject s = JSON.parseObject(JSON.toJSONString(user));
        System.out.println(s);
        List<String> moduleNames = null;
        checkList(moduleNames);
        ArrayList<Object> list =null;
        List<Integer> newBookIds = new ArrayList<>();
        List<Integer> bookIds = Arrays.asList(1,2,3,4,5,6,7);
        ArrayList<Integer> objects = Lists.newArrayList(bookIds);
        objects.add(3,9);
        System.out.println("objects:"+objects);
        newBookIds.add(null);
        for (Integer one : bookIds) {
            if (newBookIds.size() < 4) {
                newBookIds.add(one);
            }
        }
        System.out.println(newBookIds);


    }

    private static void checkList(List<String> moduleNames) {
        if (CollectionUtils.isEmpty(moduleNames)) {
            moduleNames = Collections.EMPTY_LIST;
        }
        Integer dis=1;
        System.out.println(dis==2);
        if (moduleNames.contains("1")){
            System.out.println(moduleNames);
        }
    }

    private static Boolean getFlag() {
        return null;
    }

    private static String realName(String str) {
        String uuid = ShortUUID.generate();
        str = "手机用户_" + uuid.substring(uuid.length() - 4, uuid.length());
        return str;
    }
}
