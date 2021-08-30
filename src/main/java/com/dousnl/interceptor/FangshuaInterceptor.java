package com.dousnl.interceptor;

import com.alibaba.fastjson.JSON;
import com.dousnl.annotation.AccessLimit;
import com.dousnl.utils.JsonMsgBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/6/2
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/6/2       hanliangliang     新增              1001
 ********************************************************************/
//@Component
public class FangshuaInterceptor extends HandlerInterceptorAdapter{

    //@Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        if (handler instanceof HandlerMethod){
            HandlerMethod hm= (HandlerMethod) handler;

            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit==null){
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean nedLogin = accessLimit.nedLogin();
            String key = request.getRequestURI();

            //如果需要登录
            if(nedLogin){
                //获取登录的session进行判断
                //.....
                key+=""+"13162505297";  //这里假设用户是1,项目中是动态获取的userId
            }

            //AccessKey ak = AccessKey.withExpire(seconds);
            //从redis中获取用户访问的次数
            Integer count = (Integer) redisTemplate.opsForValue().get(key);
            if(count == null){
                //第一次访问
                redisTemplate.opsForValue().increment(key,1);
                redisTemplate.expire(key,seconds,TimeUnit.SECONDS);
            }else if(count < maxCount){
                //加1
                redisTemplate.opsForValue().increment(key,1);
            }else{
                //超出访问次数
                render(response,"超出访问次数"); //这里的CodeMsg是一个返回参数
                return false;
            }
        }
        return true;
    }

    private void render(HttpServletResponse response, String cm)throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str  = JSON.toJSONString(JsonMsgBean.error(cm));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
