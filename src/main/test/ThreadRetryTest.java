import com.dousnl.component.WxErrorException;
import com.dousnl.component.error.WxError;
import com.dousnl.domain.builder.AdminBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/1/14
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/1/14       hanliangliang     新增              1001
 ********************************************************************/
public class ThreadRetryTest {
    private static final Logger log = LoggerFactory.getLogger(ThreadRetryTest.class);
    private static int retrySleepMillis = 1000;
    private static int maxRetryTimes = 3;

    public static void main(String[] args) {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                execute();
            }
        },"executeThread");
        thread.start();
        System.out.println("线程结束");
        AdminBuilder build = AdminBuilder.builder().age(11).build();
        System.out.println(build);
    }

    private static void execute() {
        int retryTimes = 0;
        do {
            try {
                if (retryTimes<3){
                    throw new Exception("test exception....");
                }
                testRetry();
            } catch (Exception e) {
                if (retryTimes + 1 > maxRetryTimes) {
                    log.warn("重试达到最大次数【{}】", maxRetryTimes);
                    //最后一次重试失败后，直接抛出异常，不再等待
                    throw new RuntimeException("微信服务端异常，超出重试次数");
                }
                // -1 系统繁忙, 1000ms后重试
                int sleepMillis = retrySleepMillis * (1 << retryTimes);
                try {
                    log.warn("微信系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException e1) {
                    throw new RuntimeException(e1);
                }

            }
        } while (retryTimes++ < maxRetryTimes);

        log.warn("重试达到最大次数【{}】", maxRetryTimes);
        throw new RuntimeException("微信服务端异常，超出重试次数");
    }

    private static void testRetry() {
        System.out.println("testRetry.........");
    }
}

