import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/23 10:03
 */
public class IntegerEqualsTest {

    public static void main(String[] args) {
        User u1=new User();
        User u2=new User();
        u1.setMoney(5);u2.setMoney(5);
        System.out.println(u1.getMoney()==u2.getMoney());
        u1.setMoney(335);u2.setMoney(335);
        System.out.println(u1.getMoney()==u2.getMoney());
        u1.setMoney(128);u2.setMoney(128);
        System.out.println(u1.getMoney().equals(u2.getMoney()));
        u1.setMoney(128);u2.setMoney(128);
        System.out.println(u1.getMoney()==u2.getMoney());
        u1.setMoney(127);u2.setMoney(127);
        System.out.println(u1.getMoney()==u2.getMoney());

        Integer i=0x7fffffff;
        System.out.println(i);
        List<Integer> integers = Arrays.asList(1, 2);
        System.out.println(integers.contains(1));

        Double aDouble = Double.valueOf("4.6");
        System.out.println(9611%32);
        System.out.println(aDouble);
        Object str="123";
        System.out.println(str.toString());
    }
}
