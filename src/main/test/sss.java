import java.util.Optional;

/**
 * TODO
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/8 19:17
 */
public class sss {

    public static void main(String[] args) {
        User user=new User();
        Optional<User> user1 = Optional.of(user);

        System.out.println(user1.map(User::getAge).orElse(1));
        System.out.println(user1.map(User::getName).orElse("1"));
    }
}
