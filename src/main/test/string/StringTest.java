package string;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.domain.User;
import com.dousnl.utils.TimeCount;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/10/31 17:48
 */
public class StringTest {

    private static TimeCount timeCount = new TimeCount();

    private static Date orderDateBase = new Date(946656000000L);


    public static Long generateOrderNumber(Integer userId) {
        Date orderTime = new Date();
        Integer days = (int) ((orderTime.getTime() - orderDateBase.getTime()) / (1000 * 60 * 60 * 24L));
        Date today = DateUtils.truncate(orderTime, Calendar.DAY_OF_MONTH);
        Long timeOfDay = (orderTime.getTime()-today.getTime())/1000;
        int random = timeCount.getSecondCount();
        int p1 = days + 10000;
        long p2 = timeOfDay + 10000;
        String p3 = StringUtils.leftPad(random%100+"",2,"0");
        String p4 = StringUtils.leftPad(userId%1000000+"",6,"0");
        String orderNumber = p1 + "" + p2 + "" + p3+ "" + p4;
        return Long.parseLong(orderNumber);
    }

    public static Long generateOrderNumber1(Integer userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1); //增加一天

        Date orderTime = calendar.getTime();
        Integer days = (int) ((orderTime.getTime() - orderDateBase.getTime()) / (1000 * 60 * 60 * 24L));
        Date today = DateUtils.truncate(orderTime, Calendar.DAY_OF_MONTH);
        Long timeOfDay = (orderTime.getTime()-today.getTime())/1000;
        int random = timeCount.getSecondCount();
        int p1 = days + 10000;
        long p2 = timeOfDay + 10000;
        String p3 = StringUtils.leftPad(random%100+"",2,"0");
        String p4 = StringUtils.leftPad(userId%1000000+"",6,"0");
        String orderNumber = p1 + "" + p2 + "" + p3+ "" + p4;
        return Long.parseLong(orderNumber);
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println(generateOrderNumber(400521));
        System.out.println(generateOrderNumber(400521));
        System.out.println(generateOrderNumber(400521));
        System.out.println(generateOrderNumber(400521));

        System.out.println(getCount());
        //Thread.sleep(1000);
        System.out.println(getCount());
        System.out.println(getCount());
        System.out.println(generateOrderNumber1(400521));

        User u=new User("1",1,"1");
        User u1=new User("2",1,null);
        List<User> list=new ArrayList();
        list.add(u);
        list.add(u1);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);

        System.out.println(list);
        Map<String, User> userCompletedNum = Collections.emptyMap();
        Map<String, User> userCompletedNum1 = Collections.emptyMap();
        //非空判断
        userCompletedNum = list.stream().filter(e -> e.getName() != null)
                .filter(e -> e.getAge() != null && e.getAddress()!=null).collect(Collectors.toMap(User::getAddress, Function.identity()));
        //非空判断
        userCompletedNum = list.stream().filter(e->Objects.nonNull(e)).filter(e -> e.getName() != null)
                        .filter(e -> e.getAge() != null && e.getAddress()!=null).collect(Collectors.toMap(User::getAddress, Function.identity()));

        System.out.println(userCompletedNum);
    }

    public static int getCount(){
        User u=new User();
        u.setName("1111");
        u.setAge(1222);
        u.setAddress("11");
        u.setDa(new Date());
        //u.setAddress1("");
        u.setName("1111");
        u.setAge(1222);
        u.setAddress("11");
        u.setDa(new Date());
        //u.setAddress1("");
        return timeCount.getSecondCount();
    }
}
