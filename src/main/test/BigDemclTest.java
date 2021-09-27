import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.domain.UserDto;
import com.dousnl.enums.OrderSourceEnum;
import com.dousnl.utils.beans.BeanCopyUtil;
import com.dousnl.utils.soybean.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/11/6 13:54
 */
public class BigDemclTest {
    /**
     * The array of bins. Lazily initialized upon first insertion.
     * Size is always a power of two. Accessed directly by iterators.
     */
    public static void main(String[] args) throws IOException {
        BigDecimal bigDecimal = new BigDecimal("0.00");
        if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("zero");
        }
        //push();
        User u = new User();
        if (null == u.getAge()) {
            System.out.println("111");
        }
        //if (1==u.getAge()){
        System.out.println("111");
        //}
        String str = "hasText";
        if (StringUtils.hasText(str)) {
            System.out.println("true");
        }
        //userTarget();
        UserDto dto = new UserDto();
        dto.setNickname("nickName1");
        dto.setName("saad");
        push(dto);
        Map<String, Integer> jumpUrlMap = Maps.newHashMap();
        jumpUrlMap.put("1", 1);
        Long id = 1L;
        Integer integer = jumpUrlMap.get(id);
        System.out.println(integer);
        boolean flag = false;
        String redisLock = getLock();
        System.out.println("redisLock:" + redisLock);
        AB a = new AB();
        a.setName("樊登讲书1年VIP");
        a.setPrice(365);
        System.out.println(JSON.toJSONString(a));
        Map map = Collections.emptyMap();
        Object o = map.get(1);
        System.out.println(o);
        Integer i = 0 / 10 + 1;
        System.out.println(i.toString());

        System.out.println("order:" + OrderSourceEnum.SOURCE_EVENT.getCode());

        BigDecimal big = BigDecimal.valueOf(1000);
        BigDecimal mix = big;

        mix = mix.subtract(BigDecimal.valueOf(50));
        mix = mix.subtract(BigDecimal.valueOf(50));

        System.out.println(big);
        System.out.println(mix);


        User u1 = new User(17, "1");
        User u2 = new User(18, "2");
        User u3 = new User(19, "3");
        List<User> list = Lists.newArrayList();
        list.add(u1);
        list.add(u2);
        list.add(u3);

        HashMap<Object, Object> newHashMap = Maps.newHashMap();
        User user = list.stream().filter(p -> filterUser(p, newHashMap)).findFirst().get();
        user.setName("555");

        System.out.println(list);
        System.out.println("newhashMap:" + newHashMap);

        //下单场景
        Integer orderScene = Optional.ofNullable(user.getAge()).orElse(1);
        System.out.println("order:" + orderScene);

        Map<String, Object> attrs = null;
        Integer channelId = (Integer) Optional.ofNullable(attrs).map(p -> p.get("channelId")).orElse(null);
        System.out.println(channelId);

        setChannel(channelId);

        System.out.println("channelId:" + channelId);
        //当前值
        Consumer<Integer> consumer1 = x -> System.out.println("当前值 : " + x);
        Consumer<Integer> consumer2 = x -> System.out.println("当前值加 : " + (x + x));
        consumer1.andThen(consumer2).accept(3);

        LocalDate localDate = LocalDate.now();
        System.out.println("localDate:" + localDate);
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime:" + localTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime:" + localDateTime);
        LocalDate date = LocalDate.of(2021, 7, 28);
        System.out.println("LocalDate.of:" + date);
        LocalDate ld1 = LocalDate.of(2020, 7, 6);
        LocalDate ld2 = LocalDate.of(2020, 7, 7);
        boolean after = ld1.isAfter(ld2);
        System.out.println("ld1是否在ld2之后 = " + after);
        //
        LocalDateTime of = LocalDateTime.of(2021, 7, 30, 14, 46);
        LocalDateTime of1 = LocalDateTime.of(2021, 7, 30, 14, 47);
        boolean after1 = of.isAfter(of1);
        System.out.println("of is after of1 :" + after1);

        Duration duration = Duration.between(of, of1);
        System.out.println("dur days:"+duration.toDays());
        System.out.println("dur hours:"+duration.toHours());
        System.out.println("dur mins:"+duration.toMinutes());

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("ldt = " + ldt);
        //输出: ldt = 2020-07-07T18:32:34.757

        String format1 = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("format1 = " + format1);
        //输出: format1 = 2020-07-07

        LocalDateTime parse = LocalDateTime.parse("2021-07-30 16:46:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Date from = Date.from(parse.atZone(ZoneId.systemDefault()).toInstant());
        u1.setDa(from);
        System.out.println("u1.date:"+ u1.getDa());

        System.out.println("notifysharding-hashcode:"+"notifysharding".hashCode());
        List<User> users=Lists.newArrayList();
        users.add(new User(18,"1",DateUtil.parseDate("2021-01-02")));
        users.add(new User(19,"1",DateUtil.parseDate("2021-01-03")));
        users.add(new User(21,"",DateUtil.parseDate("2021-01-04")));
        users.add(new User(11,"1",DateUtil.parseDate("2021-09-13 20:18:12")));
        users.add(new User(11,"1",DateUtil.parseDate("2021-09-13 20:44:56")));
        users.add(new User(11,"1",DateUtil.parseDate("2021-09-13 14:53:46")));
        User user2 = users.stream().filter(e -> !Arrays.asList(11, 18,19).contains(e.getAge())).findAny().orElse(null);

        System.out.println(JSON.toJSONString(user2));

        List<User> collect1 = users.stream().filter(e -> Objects.nonNull(e.getAge())).sorted(Comparator.comparing(x -> x.getDa())).collect(Collectors.toList());
        Map<Integer, Date> userMap = Maps.newHashMap();
        for (User user1 : collect1) {
            userMap.put(user1.getAge(),user1.getDa());
        }
        Object aaa= null;
        System.out.println((String) aaa);
        System.out.println(userMap);

        System.out.println("大于20的用户"+0);

        Map<Integer, String> collect = users.stream().collect(Collectors.toMap(User::getAge,
                User::getAddress));
        System.out.println(JSON.toJSONString(new User(18,"1")));
        String aliasName = (String) JSON.parseObject("{\"address\":\"1\",\"age\":18,\"at\":false}").get("goodsAlias");
        System.out.println(aliasName);

        Integer i1=Integer.parseInt("-129");
        Integer i2=129;
        short i3=129;
        String s = Optional.ofNullable(user.getNum()).map(String::valueOf).orElse(null);
        System.out.println("s:"+s);

        int year = LocalDate.now().lengthOfYear();
        System.out.println(year);
        Map map1=new HashMap();
        map1.put(1,1);
        map1.put(2,null);
        map1.put(3,null);
        System.out.println(map1);
        map1 = users.stream().filter(e->Objects.nonNull(e.getAge())).collect(Collectors.toMap(User::getAge, User::getAddress));
        System.out.println(map1);
        Integer longNum = (int) u1.getLongNum();
        Integer integer1 = Optional.ofNullable(u1.getLongNum()).map(Long::intValue).orElse(0);
        System.out.println("longNum:"+Optional.ofNullable(u1.getLongNum()).map(Long::intValue).orElse(0));

        //退款时间
        Date refundTime = null;
        //订单完成时间
        Date completeTime =null;
        int daysBetween = DateUtil.getDaysBetween(completeTime, refundTime);
        System.out.println("day:"+daysBetween);

        users.add(new User(null,""));

        List<Integer> ages = users.stream().filter(e->Objects.nonNull(e.getAge())).map(User::getAge).collect(Collectors.toList());
        System.out.println(ages);

    }

    private static void setChannel(Integer channelId) {
        channelId = 5;
    }

    private static boolean filterUser(User p, HashMap<Object, Object> map) {
        if (p.getAge() >= 18) {
            map.put(p.getAge(), true);
            return true;
        }
        map.put(p.getAge(), false);
        return false;
    }


    private static String getLock() {
        int i = 0;
        while (true) {
            if (i >= 5) {
                return i + "";
            }
            i++;
            System.out.println("i:" + i);
        }
    }

    private static boolean getFlag() {
        return false;
    }

    private static void push(UserDto dto) {
        pushUser(dto);
    }

    private static void pushUser(UserDto dto) {
        User u = new User();
        BeanCopyUtil.beanCopy(dto, u);
        System.out.println("copy u:" + JSON.toJSONString(u));
    }

    private static void push() {
        //BigDecimal bigDecimal=new BigDecimal("0.00");
        BigDecimal bigDecimal = new BigDecimal("0.00");
    }


    private static void userTarget() {
        User u = new User();
        System.out.println("u:" + u);
        User u1 = new User();
        System.out.println("u1:" + u1);
        u1 = u;
        System.out.println("u1New:" + u1);
        if (u == u1) {
            System.out.println(true);
        }
    }


}

class AB {
    private String name;
    private Integer price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
