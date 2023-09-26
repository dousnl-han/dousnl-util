package com.dousnl.utils;

import io.swagger.models.auth.In;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2023/1/6
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2023/1/6       hanliangliang     新增              1001
 ********************************************************************/
public final class ThreadLocalTrackingSource {

    private static ThreadLocal<Integer> trackingSourceThreadLocal = new ThreadLocal();

    public static Integer get() {
        return trackingSourceThreadLocal.get();
    }

    public static void set(Integer trackingSourceId) {
        trackingSourceThreadLocal.set(trackingSourceId);
    }

    public static void clean() {
        trackingSourceThreadLocal.remove();
    }

    private ThreadLocalTrackingSource() {
    }
}
