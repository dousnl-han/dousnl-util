package com.dousnl.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/8/26
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/8/26       hanliangliang     新增              1001
 ********************************************************************/
@Slf4j
@Component
public class RedisMqMessageListener implements MessageListener {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        Object value = redisTemplate.getValueSerializer().deserialize(message.getBody());
        log.info("consumer message: " + String.valueOf(value));

       log.info(">>>>>>>>接收订阅消息：{}",String.valueOf(value));
    }
}
