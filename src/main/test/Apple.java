import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/11/11 15:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apple {
    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;

    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();
        Apple apple1 = new Apple(1,"苹果1",new BigDecimal("3.25"),10);
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple2 = new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple3 = new Apple(3,"荔枝",new BigDecimal("9.99"),40);
        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);
        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a,(k1, k2)->k1));
        System.out.println("map:"+ JSON.toJSONString(appleMap));
        Map<Integer, String> map=new HashMap<>();
        map.put(1,"1");
        map.put(1,"2");
        map.put(2,"3");
        System.out.println("map:"+ JSON.toJSONString(map));
    }

}

