import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/1/10 19:09
 */
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
    }
}
