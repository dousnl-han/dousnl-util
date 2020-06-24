
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/24 17:45
 */
public class BeanCopyTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        User u=new User();u.setName("11");
        User u1=new User();
        BeanUtils.copyProperties(u,u1);
        System.out.println(u1);
    }
}
