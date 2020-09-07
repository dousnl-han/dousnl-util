import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/1/10 19:09
 */
@Slf4j
public class ListEqTest {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(l);
        String str="AIZZ1DC900000012";
        System.out.println("leg:"+str.length());

        System.out.println(Integer.parseInt("200020760"));
        //System.out.println(Integer.parseInt("20002076011"));
        System.out.println(Integer.parseInt("2000207601"));
        //System.out.println(Integer.parseInt("20002076011",20));
        System.out.println((int) new BigDecimal("20002076011").longValue());

        System.out.println("5f18e6f0dd1ffb000e18d313".compareTo("5f18e6dcdd1ffb000e18d312"));
        Integer i=null;
        if (Objects.isNull(i) || i!=1){
            System.out.println(1111);
        }


        Map<String, Integer> repliedCommentMap= Maps.newHashMap();
        repliedCommentMap.put("11s",1);
        List<Integer> set = new ArrayList<>();set.add(1);
        if (repliedCommentMap != null) {
            set.addAll(repliedCommentMap.values().stream().map(Integer::intValue).collect(Collectors.toList()));
        }

        System.out.println(">>set:"+set);
        int size = 11111;
        int size1 = set.size();
        long count=size+size1;

        System.out.println("count"+count);
        User u=new User();
        u=null;
        log.error("count:{}",u);

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);

        List<Integer> list2 = list1.stream().filter(e->e.intValue()==2).collect(Collectors.toList());

        snsAccountAddInfoList(list2);


        //System.out.println(list2);















    }

    private static void snsAccountAddInfoList(List<Integer> list2) {
            if (CollectionUtils.isEmpty(list2))
            System.out.println(list2);
            List<Integer> list3 = new ArrayList<>();
            list3.add(1);
            list2.addAll(list3);
        list2.add(2);
        list2.add(3);
        System.out.println(list2);

        List<Integer> integers = list2.subList(0, 2);
        System.out.println(integers);
    }
}
