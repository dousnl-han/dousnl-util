package com.dousnl.listener.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2022/10/12
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2022/10/12       hanliangliang     新增              1001
 ********************************************************************/
@Component
public class OrderApplicationListener implements ApplicationListener<OrderApplicationEvent> {
    @Override
    public void onApplicationEvent(OrderApplicationEvent applicationEvent) {
        String orderNumber = applicationEvent.getOrderNum();
        System.out.println("打印订单号："+applicationEvent.getOrderNum());
    }
}
