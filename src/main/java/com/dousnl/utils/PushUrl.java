package com.dousnl.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 维修状态，TMS推送接口
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/10/21 17:06
 */
@Component
@Data
public class PushUrl {

    //@Value("${tms.push.url}")
    private String tmsPushUrl;

    //@Value("${esb.push.url}")
    private String esbPushUrl;

    public static final String TMS_ADVICE_STATUS_PUSH_API="/api/privatePileMaintenanceWorkOrders/pushStatus";

    public static final String ESB_ADVICE_STATUS_PUSH_API="/api/privatePileMaintenanceWorkOrders/pushStatus";
}
