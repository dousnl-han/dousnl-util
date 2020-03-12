import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Base64;
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

    private static int seq = 0;

    private static final CountDownLatch ctl = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {


        for (int i = 0; i < 100; i++) {
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
        String password="123456";
        String encode1 = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));
        System.out.println("ec:"+encode1);
        byte[] dncode = Base64.getDecoder().decode(encode1);
        String s = new String(dncode,Charset.forName("UTF-8"));
        System.out.println("pass:"+s);
        System.out.println("pass:"+s);


    }
}
