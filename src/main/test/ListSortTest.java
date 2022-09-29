import com.dousnl.domain.User;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
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
                new User("111",25,"beijing"),
                new User("zhang",null,"beijing"),
                new User("li",20,"shanghai"),
                new User("wang",19,"shenzhen"),
                new User("zhang",21,"beijing"),
                new User("wang1",21,"shenzhen"));
        Map<String, User> collect3 = users.stream().collect(Collectors.toMap(e -> e.getName(), Function.identity(), (k1, k2) -> k2));
        ArrayList<User> users1 = new ArrayList<>(collect3.values());
        System.out.println("users1==>"+users1);
        User u=new User();
        u.setList(Arrays.asList(1,2,3));
        users.add(u);

        //Map<String, User> thirdPartyMap = users.stream().collect(Collectors.toMap(User::getName, Function.identity()));

        //System.out.println("age:"+thirdPartyMap.get("zhang").getAge());

        List<User> collect2 = users.stream().filter(a -> a.belongClassify(4)).collect(Collectors.toList());

        System.out.println("collect2:"+collect2);

        users.stream().sorted(Comparator.comparing(User::getAge,Comparator.nullsLast(Integer::compareTo)).reversed());
        System.out.println("users:"+users);
        Collections.sort(users,(o1,o2)->{
            if (o1.getAge()!=null && o2.getAge()!=null){
                return o1.getAge()-o2.getAge();
            }
            return o1.getAge()==null ? 1:-1;
        });
        //users.sort(Comparator.comparing(User::getAge,Comparator.nullsLast(User::getAge)));
        System.out.println(users);
        List<Object> user2=Lists.newArrayList(
                new User("zhang1",18,"beijing"),
                new User("li1",19,"shanghai"),new User("wang1",20,"shenzhen"));
        List<List<Object>> list=new ArrayList<List<Object>>();
        //list.add(users);
        list.add(user2);
        list.indexOf(1);

        List<User> collect = users.stream().filter(e -> Objects.nonNull(e.getAge())).collect(Collectors.toList());

        //System.out.println(">>>>>>>>:"+collect);

        /*List<Object> pile=Lists.newArrayList(
                new PrivatePile("200010","北京"),
                new PrivatePile("200020","上海"));
        int index=2;
        if (list.size()>=(index-1)) {list.add(index-1,pile);}

        System.out.println(list);*/

        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(null);
        list1.add(5);
        list1.add(null);
        list1.add(null);
        list1.add(3);
        list1.add(6);
        list1.add(null);
        list1.add(4);
        System.out.println("之前" + list1);
//        Collections.sort(list1, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                // 写法1：
//                if (o1 != null && o2 != null) {
//                    return o1.compareTo(o2);
//                } else {
//                    return o1 == null ? 1 : -1;
//                }
//                // 写法2：
//			/*return o1 == null ?
//					1 :
//					(o2 == null ? -1 : o1.compareTo(o2));*/
//            }
//        });
        System.out.println("之后" + list1);

        List<Integer> collect1 = list1.stream().sorted(Comparator.comparing(Integer::intValue, Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());

        System.out.println("list1:"+collect1);

        System.out.println((long)1/10);

    }
}
