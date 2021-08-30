import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/23 10:03
 */
public class IntegerEqualsTest {

    public static void main(String[] args) {
        System.out.println("AnnontionTypeTest:"+AnnontionTypeTest.flag);
        User u1=new User();
        User u2=new User();
        Integer bbb=(int)u1.getAaa();
        System.out.println("bbb:"+bbb);
        u1.setMoney(5);u2.setMoney(5);
        System.out.println(u1.getMoney()==u2.getMoney());
        u1.setMoney(335);u2.setMoney(335);
        System.out.println(u1.getMoney()==u2.getMoney());
        u1.setMoney(128);u2.setMoney(128);
        System.out.println(u1.getMoney().equals(u2.getMoney()));
        u1.setMoney(128);u2.setMoney(128);
        System.out.println(u1.getMoney()==u2.getMoney());
        u1.setMoney(127);u2.setMoney(127);
        System.out.println(u1.getMoney()==u2.getMoney());

        Integer i=0x7fffffff;
        System.out.println(i);
        List<Integer> integers = Arrays.asList(1, 2);
        System.out.println(integers.contains(1));

        Double aDouble = Double.valueOf("4.6");
        System.out.println(9611%32);
        System.out.println(aDouble);
        Object str="123";
        System.out.println(str.toString());
        String substring = "10210113007794".substring(2);
        System.out.println(substring);
        long l = Long.parseLong(substring);
        System.out.println(l);
        List<Integer> result = new ArrayList<>();
        List<Integer> re=Arrays.asList(1,2,3,4);
        List<Integer> list=new ArrayList();
        list.addAll(re);
        list.stream().filter(a->Objects.equals(1,a)).forEach(a->result.add(a));
        System.out.println(result);
        System.out.println((long)1/10);
        Map<Integer, Integer> typeIdsMap = Maps.newLinkedHashMap();
        System.out.println(typeIdsMap);
        ArrayList<Integer> list1 = new ArrayList(6);
        Collections.addAll(list1, 3,1,2,0);
        System.out.println(list1);
        BigDecimal divide = BigDecimal.valueOf(0.02).divide(BigDecimal.valueOf(100));
        System.out.println(divide);
        String s = UUID.randomUUID().toString();
        String a = UUID.randomUUID().toString();
        String a1 = UUID.randomUUID().toString();
        String a2 = UUID.randomUUID().toString();
        System.out.println(s);
        System.out.println(a);
        System.out.println(a1);
        System.out.println(a1.substring(0,5));
        System.out.println(a2.substring(0,5));
    }
}
