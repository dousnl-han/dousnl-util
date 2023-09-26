package com.dousnl.listener.spring;

import lombok.Data;
import org.springframework.context.ApplicationEvent;
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
public class OrderApplicationEvent extends ApplicationEvent {

    private String orderNum;

    public OrderApplicationEvent() {
        super(1);
    }

    public OrderApplicationEvent(Object source) {
        super(source);
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

}
