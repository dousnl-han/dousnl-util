package dousnl.apache.commons;

import com.dousnl.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/24 17:59
 */
public class PairAndTripleTest {


    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        boolean contains = ArrayUtils.contains(new int[]{1, 2}, 1);
        System.out.println(contains);
        User u=new User();
        PropertyUtils.setProperty(u,"name","11aa");
        System.out.println(u);
        U u1=new U();
        System.out.println(">>>>>>>>>>u:"+u);
        BeanUtils.copyProperties(u1,u);
        System.out.println(">>>>>>>>>>>u1:"+u1);

    }

    public static class U{
        private String name;
        private String age;
        private Integer asd;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public Integer getAsd() {
            return asd;
        }

        public void setAsd(Integer asd) {
            this.asd = asd;
        }

        @Override
        public String toString() {
            return "[name:"+getName()+" age:"+getAge()+" asd:"+getAsd()+"]";
        }
    }
}
