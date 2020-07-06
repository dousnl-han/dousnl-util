/**
 * 计数器限流思想
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/7/3 16:11
 */
public class CounterDemo {

    private static long timeStamp = System.currentTimeMillis();
    //限制1s内限制在100个请求
    private static long limitCount = 100;
    private static long interval = 1000;
    //请求数
    private static long reqCount = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread() {
                @Override
                public void run() {
                    if (grant()) {
                        System.out.println(Thread.currentThread().getName()+">>执行业务逻辑");
                    } else {
                        System.out.println(Thread.currentThread().getName()+">>限流");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        run();
                    }
                }
            }.start();
        }
    }

    private static boolean grant() {
        long now = System.currentTimeMillis();
        if (now < timeStamp + interval) {
            if (reqCount < limitCount) {
                ++reqCount;
                return true;
            } else {
                //System.out.println("now:"+now+"ms,timeStamp+interval:"+(timeStamp+interval)+"ms"+" reqCount:"+reqCount);
                return false;
            }
        } else {
            timeStamp = System.currentTimeMillis();
            reqCount = 0;
            return true;
        }
    }
}
