package com.dousnl.config;

import com.dousnl.interceptor.FangshuaInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/6/2
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/6/2       hanliangliang     新增              1001
 ********************************************************************/
//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    //@Autowired
    private FangshuaInterceptor interceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}
