import org.junit.Test;

import java.util.Optional;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/7/7 15:17
 */
public class OptionalTest {


    public static void main(String[] args) {
        User user = new User("zhang", 11, 11, false);
        Optional<User> user1 = Optional.ofNullable(user);
        Optional<User> user2 = Optional.empty();

        //Optional<User> user3 = Optional.of(null);
        Optional<User> user4 = Optional.ofNullable(null);
        String str = "111";
        System.out.println(str + user1.map(User::getName).orElse(""));
        //System.out.println(str+user3.map(User::getName));
        System.out.println(str + user4.map(User::getName));
    }


    @Test
    public void test() {
        Optional<User> op = Optional.of(new User(null, 11, 11, false));
        String str = "";
        //System.out.println(str+op.map(User::getName).orElse(""));
        //System.out.println(Optional.of(null));
        String s = Optional.ofNullable(null).map(o -> o.toString()).orElse(null);
        OpalModel model=new OpalModel();
        model.setChild(new OpalModelChild());
        Integer add = Optional.ofNullable(model).map(o -> o.getChild()).map(o -> o.getAdd()).orElse(null);
        System.out.println(add);
    }
}
