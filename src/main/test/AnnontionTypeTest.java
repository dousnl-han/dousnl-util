import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/7 16:22
 */
public class AnnontionTypeTest {

    public static void main(String[] args) {
        Class<Apple> appleClass = Apple.class;
        System.out.println(appleClass.getName());
        int h = "s".hashCode();
        System.out.println(h ^ (h >>> 16));
        HashMap<String, List<String>> map= Maps.newHashMap();
        map.put("1",Arrays.asList("a","b"));
        map.put("2",Arrays.asList("c","d"));
        List<String> remove = map.remove("1");
        System.out.println(remove);
        System.out.println(map);
        for(int i=0;i<5;i++){
            System.out.println(i);
        }
    }
}
