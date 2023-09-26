package com.dousnl.listener.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2022/10/12
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2022/10/12       hanliangliang     新增              1001
 ********************************************************************/
public class OrderEventTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnnotationTestConfig.class);

        OrderApplicationEvent orderApplicationEvent = applicationContext.getBean(OrderApplicationEvent.class);
        //OrderApplicationListener bean = applicationContext.getBean(OrderApplicationListener.class);

        orderApplicationEvent.setOrderNum("1124234234");
        applicationContext.publishEvent(orderApplicationEvent);
    }
}
