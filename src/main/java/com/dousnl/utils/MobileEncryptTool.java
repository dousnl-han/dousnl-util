package com.dousnl.utils;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.utils.date.DateUtil;
import com.dousnl.utils.enums.ABTestTypeEnum;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Integer task = 1111;
        HashMap<Object, Object> map1 = Maps.newHashMap();
        map1.put(1, new User(1, "2424"));
        map1.put(2, new User(1, "2424"));
        map1.put(3, new User(1, "2424"));
        System.out.println(JSON.toJSONString(map1));

        Map<Integer, User> map12 = JSON.parseObject("{\n" +
                "\t1: {\"titleUrl\":\"https://cdn-jd-images.dushu365.com/1652339234208b6e7b9e6e0d33841e17fba7235631p49sh2\",\"welfareImageUrl\":\"\"},\n" +
                "\t2: {\"titleUrl\":\"https://cdn-upyun-images.dushu365.com/16523392345c0eba8d21d1977f46ba6d1a9bc4a824hkkrj5\",\"welfareImageUrl\":\"\"},\n" +
                "\t3: {\"titleUrl\":\"https://cdn-upyun-images.dushu365.com/165233923411a2e8fb98ab8367a85ee11ba25a1b9dw831ki\",\"welfareImageUrl\":\"\"},\n" +
                "\t4: {\"titleUrl\":\"https://cdn-upyun-images.dushu365.com/1652339234bd045bf599bd84ef20c9ea57bbd14028dufi1b\",\"welfareImageUrl\":\"\"},\n" +
                "\t5: {\"titleUrl\":\"https://cdn-upyun-images.dushu365.com/16523392341e6b7ed3728a7602dfcae3c31dd56f90r4xflb\",\"welfareImageUrl\":\"\"}\n" +
                "}", Map.class);
        System.out.println(map12.get(1));

        Date date = DateUtil.getCurrentDate();
        Date startTimeTwo = new DateTime(date).minusWeeks(2).dayOfWeek().withMinimumValue().withTimeAtStartOfDay().toDate();
        Date endTimeTwo = new DateTime(date).minusWeeks(2).dayOfWeek().withMaximumValue().millisOfDay().withMaximumValue().toDate();
        Date startTimeOne = new DateTime(date).minusWeeks(1).dayOfWeek().withMinimumValue().withTimeAtStartOfDay().toDate();
        Date endTimeOne = new DateTime(date).minusWeeks(1).dayOfWeek().withMaximumValue().millisOfDay().withMaximumValue().toDate();
        System.out.println(DateUtil.dateToString(startTimeTwo));
        System.out.println(DateUtil.dateToString(endTimeTwo));
        System.out.println(DateUtil.dateToString(startTimeOne));
        System.out.println(DateUtil.dateToString(endTimeOne));

        Integer agg = 2;
        List<Integer> integers = Arrays.asList(1, 2);
        if (!integers.contains(agg)) {
            System.out.println("不包含");
        }
        System.out.println(MobileEncryptTool.encryptMobile("13123846768"));
        System.out.println(MobileEncryptTool.encryptMobile("18980682000"));
        System.out.println(MobileEncryptTool.encryptMobile("13162505001"));
        System.out.println(MobileEncryptTool.encryptMobile("18321745555"));
        System.out.println(MobileEncryptTool.decryptMobile("swww59sigtpwaijis7e52rqec4lrcl7i"));
        System.out.println(MobileEncryptTool.decryptMobile("txtsk9nksk9t7znlfe25uzrr5aefrwys"));

        System.out.println(JSON.parseObject("{'date':'2099-06-30 23:59:59'}", User.class));

        String sku21DayIds = "[]";
        List<Integer> sku21DayIdsList = JSON.parseArray(sku21DayIds, Integer.class);

        System.out.println(sku21DayIdsList.contains(111));

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
        list1.add(new User("", 2, ""));
        list.addAll(list1);
        String[] split2 = "1.2".split("P.");
        System.out.println("split2.length:" + split2.length);

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println(month);

        Integer i = null;
        System.out.println(i);
        //System.out.println(i.toString());
        System.out.println(JSON.toJSONString(i));
        System.out.println(Objects.equals(1, Integer.valueOf(1)));

        BigDecimal wisdomCoinIosCurrent = BigDecimal.valueOf(222);
        BigDecimal tatalFeeBig = BigDecimal.valueOf(1111).divide(BigDecimal.valueOf(100));
        int i2 = wisdomCoinIosCurrent.compareTo(tatalFeeBig);
        if (wisdomCoinIosCurrent.compareTo(tatalFeeBig) < 0) {
            System.out.println("用户：{}，当前智慧币:{}，小于应扣金额：{}");
        }
        Integer read = 11111;
        Long aLong = Optional.ofNullable(read).map(Long::valueOf).orElse(0L);
        System.out.println(aLong);
        String str = "['xww7tnwwwsttskwgogvq9op2t2vzfpty',\n" +
                "'t77wtznwnkk8f6lakp7nsjx2vymrvx3t',\n" +
                "'tn9tnt9snzsxsz7d0k9crlicir3nwnhv']\n";
        Map<Integer, User> collect = Maps.newHashMap();
        Integer aaa = null;
        if (Objects.equals(1, aaa)) {
            collect = list.stream().collect(Collectors.toMap(k -> Integer.valueOf(k.getName()), e -> e, (v1, v2) -> v1));
            System.out.println("1231242354235");
        }
        User user1 = collect.get(111);
        List<Long> userLikes = Lists.newArrayList();
        if (!userLikes.contains(111)) {
            userLikes = Lists.newArrayList();
        }
        System.out.println(JSON.toJSONString(collect));


    }
}
