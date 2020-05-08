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
