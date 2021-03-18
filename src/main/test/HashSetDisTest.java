import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/1/3 14:25
 */
public class HashSetDisTest {

    public static void main(String[] args) {
        List<String> list=Arrays.asList("1","1","2","3");
        List<String> list1=Arrays.asList("1","2","3","4");
        HashSet<String> set=new HashSet<String>();
        set.addAll(list);
        set.addAll(list1);
        System.out.println(JSON.toJSONString(set));
        HashSet<String> set1=new HashSet<String>();
        set1.add("1");set1.add("3");set1.add("4");
        if (set.containsAll(set1)){
            System.out.println("包含set1");
        }
        User u=new User();
        u.setMoney(0);
        System.out.println(JSON.toJSONString(u));
    }
}
