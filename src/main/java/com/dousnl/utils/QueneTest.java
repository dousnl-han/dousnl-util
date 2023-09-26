package com.dousnl.utils;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2023/5/5
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2023/5/5       hll    新增              1001
 ********************************************************************/
public class QueneTest {
    private static ArrayBlockingQueue<Integer> queue =new ArrayBlockingQueue(4);


    public static void main(String[] args) {
        try{
            queue.add(1);
            queue.add(2);
            queue.add(3);
            queue.add(5);
            queue.add(4);

            for (int i=0; i<100;i++){
                Integer poll = queue.poll();
                if (poll == null){
                    break;
                }
                System.out.println(poll);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
