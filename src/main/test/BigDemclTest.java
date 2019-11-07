import com.dousnl.domain.User;

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
        push();
        User u=new User();
        if (null==u.getAge()){
            System.out.println("111");
        }
        if (1==u.getAge()){
            System.out.println("111");
        }
    }

    private static void push() {
        //BigDecimal bigDecimal=new BigDecimal("0.00");
        BigDecimal bigDecimal=new BigDecimal("0.00");
    }
}
