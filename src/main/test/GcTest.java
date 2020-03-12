import com.dousnl.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/3/3 10:35
 */
public class GcTest {


    public static void main(String[] args) {

        List<User> list=new ArrayList<User>();
        int cout=0;
        while (true){
            list.add(new User());
            System.out.println("Instance: "+(++cout));
        }
    }
}
