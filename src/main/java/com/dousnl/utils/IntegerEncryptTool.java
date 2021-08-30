package com.dousnl.utils;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.utils.date.DateUtil;
import com.google.common.collect.Lists;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/22 14:37
 */
public class IntegerEncryptTool {

    private static final char[][] charGroups = new char[][]{{'z', 'k', 'n', 'x', 's', 'w', 't', '9', '7'}, {'j', 'i', 'o', 'b', '5', 'h', '3', 'r', '8'}, {'f', 'c', 'm', '0', '6', 'p', 'e', 'y', 'q'}, {'l', 'a', '4', '2', '1', 'd', 'g', 'v', 'u'}};

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
        //System.out.println(decrypt(str));
        System.out.println(decrypt("7ttsk7swnbc1avhg"));
        System.out.println(0x7fffffff);
        System.out.println(encrypt(336));
        System.out.println("a".concat("fsfsd"));
        User u=new User();
        u.setOwn(true);
        u.setAt(u.getOwn());
        u.setDa(DateUtil.parse("2021-07-01","yyyy-MM-dd"));
        System.out.println(u);
        Object cou=null;
        Integer count = Optional.ofNullable(cou).map(g->Integer.valueOf(g.toString())).orElse(0);
        Optional.ofNullable(u.getAge()).ifPresent(e -> {
            e.longValue();

        });
        System.out.println(count);
        Date currentDate = DateUtil.getCurrentDate();
        if (u.getDa().compareTo(currentDate)>0){
            System.out.println("da is gatter than new date");
        }
        Date date = new Date();
        if (u.getDa().compareTo(date)>0){
            System.out.println("da is gatter than new date");
        }
        String str="";
        str=realName(str);
        System.out.println("str:"+str);
        Boolean flag = getFlag();
        if (flag == null || !flag) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        Integer orderNum=1412414123;
        System.out.println(DateUtil.parse("2021-07-20","yyyy-MM-dd").getTime());
        System.out.println(DateUtil.parse("2021-07-25","yyyy-MM-dd").getTime());
        String jumpUrlConfig ="https://deeplink.dushu.io/link/index.html?op=jump&view=fillInAddress&orderNumber=#";
        jumpUrlConfig = jumpUrlConfig.replaceFirst("#",orderNum.toString());
        jumpUrlConfig.concat("1");

        System.out.println(jumpUrlConfig);

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
