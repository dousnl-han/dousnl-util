import com.alibaba.fastjson.JSON;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/11/1 9:51
 */
public class HashSetTest {

    public static void main(String[] args) {
        Set<String> set=new HashSet<String>();
        set.add("a");set.add("c");set.add("b");
        Set<String> set1=new HashSet<String>();
        set1.add("a");
        set.removeAll(set1);
        System.out.println("set:"+ JSON.toJSONString(set));
        Integer status=null;
        status=1;
        if (1==status){
            System.out.println("fsfdgg111");
        }
        push(status);
        List<String> list=null;
        for (String s:list){
            System.out.println("s:"+s);
        }
    }

    private static void push(int status) {
        if (1==status){
            System.out.println("sdsdfsd");
        }
    }
}
