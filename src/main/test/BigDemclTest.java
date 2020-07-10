import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.domain.UserDto;
import com.dousnl.utils.beans.BeanCopyUtil;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/11/6 13:54
 */
public class BigDemclTest {
    /**
     * The array of bins. Lazily initialized upon first insertion.
     * Size is always a power of two. Accessed directly by iterators.
     */
    public static void main(String[] args) {
        BigDecimal bigDecimal=new BigDecimal("0.00");
        if (bigDecimal.compareTo(BigDecimal.ZERO)==0){
            System.out.println("zero");
        }
        //push();
        User u=new User();
        if (null==u.getAge()){
            System.out.println("111");
        }
        //if (1==u.getAge()){
            System.out.println("111");
        //}
        String str="hasText";
        if (StringUtils.hasText(str)){
            System.out.println("true");
        }
        //userTarget();
        UserDto dto=new UserDto();
        dto.setNickname("nickName1");
        dto.setName("saad");
        push(dto);
    }

    private static void push(UserDto dto) {
        pushUser(dto);
    }

    private static void pushUser(UserDto dto) {
        User u=new User();
        BeanCopyUtil.beanCopy(dto,u);
        System.out.println("copy u:"+ JSON.toJSONString(u));
    }

    private static void push() {
        //BigDecimal bigDecimal=new BigDecimal("0.00");
        BigDecimal bigDecimal=new BigDecimal("0.00");
    }





    private static void userTarget(){
        User u=new User();
        System.out.println("u:"+u);
        User u1=new User();
        System.out.println("u1:"+u1);
        u1=u;
        System.out.println("u1New:"+u1);
        if (u==u1){
            System.out.println(true);
        }
    }


}
