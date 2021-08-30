package com.dousnl.queue;

import java.util.Objects;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/3/26
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/3/26       hanliangliang     新增              1001
 ********************************************************************/
public class QueueDemo {

    private static Queue<Integer> queue=new LinkedBlockingQueue();

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                int i=0;
                for (;;){
                    queue.add(i);
                    i++;
                    if (i>10) {
                        break;
                    }
                }
            }
        },0,2000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("执行"+System.currentTimeMillis()+"ms");
                while (true){
                    Integer poll = queue.poll();
                    if (Objects.isNull(poll)){
                        break;
                    }
                    System.out.println("queue:"+poll);
                }
            }
        },0,2000);
    }
}
