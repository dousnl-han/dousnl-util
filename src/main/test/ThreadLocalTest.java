/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/7/3 15:14
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
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
        new Thread(){
            @Override
            public void run() {
                System.out.println(">>>>>>>thread:"+Thread.currentThread().getName()+">>>run...");
                update();
            }
        }.start();
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
}
