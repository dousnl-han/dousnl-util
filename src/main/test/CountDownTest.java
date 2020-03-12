import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.utils.response.Resp;
import com.dousnl.utils.response.RespStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/10/23 15:42
 */
@Slf4j
public class CountDownTest {


    private static final CountDownLatch ctl=new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "-执行");
                    ctl.countDown();
                    ;
                }
            }).start();
        }
        ctl.await();
        System.out.println("主线程执行....");
        Resp resp = new Resp();
        RespStatus respStatus=new RespStatus("1","失败");
        resp.setStatus(respStatus);
        System.out.println("resp:" + JSON.toJSONString(resp));
        Resp r = JSON.parseObject(JSON.toJSONString(resp), Resp.class);
        System.out.println("R:" + JSON.toJSONString(r));
        JSONObject postData = new JSONObject();
        postData.put("id", "1111");//TMS订单号
        postData.put("id2", "21111");//TMS订单号
        if (false||true){
            System.out.println("sadfafdf");
        }
        log.info(">>>>>>postData:{}", postData);
    }
}
