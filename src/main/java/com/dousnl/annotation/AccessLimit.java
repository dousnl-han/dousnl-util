package com.dousnl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/6/2
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/6/2       hanliangliang     新增              1001
 ********************************************************************/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {

    int seconds();

    int maxCount();

    boolean nedLogin() default false;
}
