import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/7/3 15:14
 */
public class ThreadLocalTest {
    static Pattern pattern = Pattern.compile("\\d(\\d{9})");

    public static void main(String[] args) throws InterruptedException {
        User user=new User();
        user.setMoney(111);
        new Thread(){
            @Override
            public void run() {
                //user.setName("222");
            }
        }.start();
        Thread.sleep(1000l);
        System.out.println(user);
        new Thread(){
            @Override
            public void run() {
                System.out.println(">>>>>>>thread:"+Thread.currentThread().getName()+">>>run...");
                update();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                System.out.println(">>>>>>>thread:"+Thread.currentThread().getName()+">>>run...");
                update();
            }
        }.start();

        String nameTransition = realNameTransition("asdasd123211sfsf");
        System.out.println(nameTransition);
        user=null;
        if (user!=null && Optional.ofNullable(user.getStatus()).isPresent()){
            System.out.println("22222");
        }
    }

    private static void update() {
        threadLocal.set(threadLocal.get()+66);
        System.out.println(threadLocal.get());
    }

    private static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 10;
        }
    };


    public static String realNameTransition(String realName) {
        if (StringUtils.isBlank(realName)) {
            return null;
        } else {
            Matcher m = pattern.matcher(realName);
            StringBuffer sb = new StringBuffer();

            while(m.find()) {
                String group = m.group(1);
                m.appendReplacement(sb, m.group().replace(group, StringUtils.leftPad("", group.length(), "*")));
            }

            m.appendTail(sb);
            return sb.toString();
        }
    }
}
