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
        User u=new User();
        u.setName("zzz");
        String aa=u.getName();
        aa="bb";
        System.out.println(u);
    }
}
