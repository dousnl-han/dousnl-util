import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/10/23 14:16
 */
public class AtomicTest {

    private static Integer adviceSeq = 0;
    private static HashMap<String, Integer> map = new HashMap<>();

    static {
        map.put("seq", 0);
    }

    static int seq = 0;
    private static final CountDownLatch ctl = new CountDownLatch(10);
    public static void main(String[] args) throws InterruptedException {


        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (adviceSeq) {
                        adviceSeq = adviceSeq == 0 ? map.get("seq") + 1 : adviceSeq + 1;
                        seq = adviceSeq;
                        System.out.println(Thread.currentThread().getName()+"-seq:"+seq);
                    }
                    ctl.countDown();
                }
            }).start();
        }
        ctl.await();
        System.out.println("最终seq:" + seq);

    }
}
