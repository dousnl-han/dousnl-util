import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/3/11
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/3/11       hanliangliang     新增              1001
 ********************************************************************/
public class SubList {
    public static void main(String[] args) {
        List<Integer> list=Arrays.asList(5,7,1,2,3,4,null,8,6);
        list.sort((o1,o2)->{
            if (o1!=null && o2!=null){
                return o1-o2;
            }
            return o1==null? 1:-1;
        });
        System.out.println("sort:"+list);
        //list.sort(Comparator.comparing(Integer::intValue,Comparator.nullsLast(Integer::compareTo)));
        list.sort(Comparator.nullsLast(Comparator.comparing(Integer::intValue)));
        System.out.println("sort1:"+list);
        List<Integer> integers = list.subList(0, 3);
        System.out.println(list);
        System.out.println(integers);
        System.out.println(integers.size());
        List<Integer> dataList = new ArrayList();
        System.out.println(dataList.size());
        System.out.println(dataList.size()>1);
        System.out.println(list);
        fitler(list);
        System.out.println(list);
        List<Integer> sub = sub(list);
        System.out.println(sub);
        Integer a1=null;
        Integer a2=1;
        if (Objects.equals(a1,a2)){
            System.out.println(1);
        }
        Map map= Maps.newHashMap();
        map.put(1,1);
        map.put(2,1);
        map.put(3,1);
        System.out.println(map.get(1));
    }

    private static void fitler(List<Integer> list) {
        System.out.println("");
        List<Integer> collect=Arrays.asList();
        collect = list.stream().filter(o -> Objects.equals(6, o) || Objects.equals(7, o)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collect)){
            collect.sort((o1, o2) -> o1-o2);
        }
        System.out.println(collect);
    }

    private static List<Integer> sub(List<Integer> list) {
        if (list.size()>3){
            list=list.subList(0,2);
        }
        return list;
    }
}
