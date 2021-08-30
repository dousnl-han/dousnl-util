import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/4/18 10:53
 */
public class ConstructClass {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<?> construct = Class.forName("Construct");
        Object instance =  construct.newInstance();
        System.out.println(instance);
        List<String> list = null;
        User u = null;
        try{
            u=new User();
            list=Arrays.asList("1");
        }catch (Exception e){

        }
        u.getAge();
        list.get(0);
        Integer i=1;
        if (i.equals(null)){
            list=new ArrayList<String>();
            list.add("1");
            i=1;
        }
        System.out.println(list);
        System.out.println(i);

    }


    class Construct{
        private String name;
        private Integer age;

        public Construct(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
