import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: hanliangliang
 * @date: 2020/7/8 19:16
 */
public class OptionTest1 {

    public static void main(String[] args) {
        System.out.println(1<<0);
        System.out.println(1<<1);
        System.out.println(1<<2);
        System.out.println(1<<3);
        System.out.println(1<<4);
        System.out.println(new User().getStatus());
        List<Integer> ids=Arrays.asList(1,2,3,4);
        System.out.println("此改动会导致id="+ids+",的这些banner不被显示,请确认后再提交");
    }
}
