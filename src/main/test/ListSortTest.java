import com.dousnl.domain.User;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/7/17 16:29
 */
public class ListSortTest {

    public static void main(String[] args) {
        List<User> users=Lists.newArrayList(
                new User("zhang",null,"beijing"),
                new User("li",19,"shanghai"),new User("wang",20,"shenzhen"));
        List<Object> user2=Lists.newArrayList(
                new User("zhang1",18,"beijing"),
                new User("li1",19,"shanghai"),new User("wang1",20,"shenzhen"));
        List<List<Object>> list=new ArrayList<List<Object>>();
        //list.add(users);
        list.add(user2);
        list.indexOf(1);

        List<User> collect = users.stream().filter(e -> Objects.nonNull(e.getAge())).collect(Collectors.toList());

        System.out.println(">>>>>>>>:"+collect);

        /*List<Object> pile=Lists.newArrayList(
                new PrivatePile("200010","北京"),
                new PrivatePile("200020","上海"));
        int index=2;
        if (list.size()>=(index-1)) {list.add(index-1,pile);}

        System.out.println(list);*/
    }
}
