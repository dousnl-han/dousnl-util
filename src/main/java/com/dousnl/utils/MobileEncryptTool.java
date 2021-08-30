package com.dousnl.utils;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/4/9
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/4/9       hanliangliang     新增              1001
 ********************************************************************/
public class MobileEncryptTool {
    private static final char[][] charGroups =
            new char[][] {{'z', 'k', 'n', 'x', 's', 'w', 't', '9', '7'}, {'j', 'i', 'o', 'b', '5', 'h', '3', 'r', '8'}, {'f', 'c', 'm', '0', '6', 'p', 'e', 'y', 'q'},
                    {'l', 'a', '4', '2', '1', 'd', 'g', 'v', 'u'}};
    private static final char[] START_CHAR = new char[] {'+'};
    private static final int MOBILE_INFO_LENGTH = 56;
    private static final long MOBILE_MAX_NUM = BigInteger.valueOf(2L).pow(56).longValue() - 1L;

    public MobileEncryptTool() {
    }

    public static String encryptMobile(String mobile) {
        int char0 = indexOf(START_CHAR, mobile.charAt(0));
        if (char0 != -1) {
            mobile = mobile.substring(1);
        }

        int zeroCount = countZero(mobile);
        int a1 = (char0 + 1 & 3) << 4;
        int a2 = zeroCount & 7;
        long a = (long) (a1 + a2);
        long b = (a << 56) + Long.parseLong(mobile);
        return encrypt(b);
    }

    public static String decryptMobile(String text) {
        long value = decrypt(text);
        int b = (int) (value >> 56);
        int a1 = b >> 4;
        int a2 = b & 15;
        long mobileNum = value & MOBILE_MAX_NUM;
        String mobileStr = String.valueOf(mobileNum);
        String mobileNumStr = StringUtils.leftPad(mobileStr, mobileStr.length() + a2, "0");
        return a1 == 0 ? mobileNumStr : START_CHAR[a1 - 1] + mobileNumStr;
    }

    private static String encrypt(long value) {
        Random random = new Random(value);
        if (value == 0L) {
            char[] zeroChars = charGroups[0];
            return zeroChars[random.nextInt(zeroChars.length)] + "";
        } else {
            StringBuilder builder = new StringBuilder(32);

            for (int i = 62; i >= 0; i -= 2) {
                long segValue = value >> i & 3L;
                char[] chars = charGroups[(int) segValue];
                builder.append(chars[random.nextInt(chars.length)]);
            }

            return builder.toString();
        }
    }

    private static long decrypt(String text) {
        if (!"".equals(text) && null != text) {
            char[] textArray = text.toCharArray();
            long value = 0L;

            for (int i = text.length() - 1; i >= 0; --i) {
                char ch = textArray[i];
                long segValue = -1L;

                for (long j = 0L; j < 4L; ++j) {
                    if (indexOf(charGroups[(int) j], ch) != -1) {
                        segValue = j;
                        break;
                    }
                }

                if (segValue < 0L) {
                    throw new IllegalArgumentException("Invalid character in cipher:[" + text + "]");
                }

                value |= segValue << (textArray.length - i - 1) * 2;
            }

            return value;
        } else {
            throw new IllegalArgumentException("不能为空:[" + text + "]");
        }
    }

    private static int indexOf(char[] a, char key) {
        for (int i = 0; i < a.length; ++i) {
            if (a[i] == key) {
                return i;
            }
        }

        return -1;
    }

    private static int countZero(String mobile) {
        int count = 0;
        char[] chars = mobile.toCharArray();

        for (int i = 0; i < chars.length && chars[i] == '0'; ++i) {
            ++count;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(MobileEncryptTool.encryptMobile("13162505004"));
        System.out.println(MobileEncryptTool.encryptMobile("13162504111"));
        System.out.println(MobileEncryptTool.encryptMobile("13162505001"));
        System.out.println(MobileEncryptTool.encryptMobile("13162505002"));
        System.out.println(MobileEncryptTool.decryptMobile("9wssknxkw7s9zxxaxt8dn76z1wrwv13v"));
        System.out.println(MobileEncryptTool.decryptMobile("nksnt7xxkwx77whzbsg2lz3dyhunfc4z"));
        int update = 0;
        update = Integer.parseInt("1");
        System.out.println(update > 0);
        Map map = Maps.newHashMap();
        map.put("1", 1);
        Object o = map.get(1);
        System.out.println(o);
        Long e1 = 150L;
        String abTestHomeCategoryJumpIds = "150,1";
        List<Long> ids = Arrays.stream(abTestHomeCategoryJumpIds.split(",")).map(e -> Long.parseLong(e)).collect(Collectors.toList());
        System.out.println(ids.contains(e1));


        String url = "https://deeplink.dushu.io/link/index.ht";
        String params = url.contains("?") ? url.substring(url.indexOf("?") + 1, url.length()) : "";
        Map<String, String> split = StringUtils.isBlank(params) ? Maps.newHashMap() : Splitter.on("&").withKeyValueSeparator("=").split(params);
        String view = split.get("view");
        System.out.println(view);
        List<User> list = new ArrayList(8);
        List<User> list1 = new ArrayList();
        //list.add(new User("",1,""));
        list1.add(new User("", 1, ""));
        list.addAll(list1);

        Integer i1 = false ? 0 : null;
        Map<String, com.dousnl.domain.es.User> resourceIdBOMap = Maps.newHashMap();

        for (User u : list) {
            u.setName("1");
            u.setAge(null != null ? 0 : null);
            com.dousnl.domain.es.User user = new com.dousnl.domain.es.User();
            //Integer i = user == null ? Integer.valueOf(0) : Integer.valueOf(0);
            u.setAge(user == null ? 0 : user.getCount());

        }
        System.out.println(list);

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println(month);

        Integer i = calendar.get(Calendar.MONTH + 1);
        System.out.println(i);
        System.out.println(i.toString());
        System.out.println(JSON.toJSONString(i));
        System.out.println(Objects.equals(1,Integer.valueOf(1)));

    }
}
