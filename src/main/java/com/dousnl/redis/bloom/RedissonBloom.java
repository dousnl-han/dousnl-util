package com.dousnl.redis.bloom;

import com.dousnl.DousnlUtilApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/1/8
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/1/8       hanliangliang     新增              1001
 ********************************************************************/
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = DousnlUtilApplication.class)
public class RedissonBloom {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testBloom(){
        Config config=new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RBloomFilter<Object> phoneFilter= redissonClient.getBloomFilter("phonelist");
        //初始化布隆过滤器：预计元素为100000000L,误差率为3%
        phoneFilter.tryInit(1000000L,0.03);
        //将号码10086插入到布隆过滤器中
        phoneFilter.add("10086");
        phoneFilter.add(123456);
        //判断下面号码是否在布隆过滤器中
        System.out.println(phoneFilter.contains(123456));
        System.out.println(phoneFilter.contains("10086"));

        redisTemplate.delete("phonelist");
    }
}
