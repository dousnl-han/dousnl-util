
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/24 17:45
 */
public class BeanCopyTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        List<Integer> strings = Arrays.asList("1,2,3".split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(strings);
        List<Integer> str1 = Arrays.asList(1,2,3,4);
        List<Integer> str2 = Arrays.asList(1,2,3,4);
        List<Integer> collect = str1.stream().filter(e -> !str2.contains(e)).collect(Collectors.toList());
        System.out.println(collect);
//        List<Integer> strings1=getList();
//        for (Integer i:strings1){
//            System.out.println(i);
//        }
        List<Integer> cardDTOList = null;
        //List<Integer> tidList = cardDTOList.stream().map(Integer::intValue).collect(Collectors.toList());
        Integer type=1;
        boolean flag=false;
        switch (type){
            case 1:
            case 2:
                flag=true;
                break;
            case 3:
                flag=true;
                break;
            default:
                break;
        }
        System.out.println("flag:"+flag);
    }

    private static List<Integer> getList() {
        return null;
    }
}
