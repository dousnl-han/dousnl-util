package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.annotation.AccessLimit;
import com.dousnl.domain.entity.UserEntity;
import com.dousnl.mapper.UserEntityMapper;
import com.dousnl.utils.JsonMsgBean;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2022/6/23
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2022/6/23       hanliangliang     新增              1001
 ********************************************************************/
@RestController("/miaosha")
public class MiaoShaController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserEntityMapper userEntityMapper;

    private static int i = 10;
    private static int s = 1;

    @PostMapping("/order")
    public JsonMsgBean fangshua(@RequestBody Integer userId) throws Exception {

        UserEntity u=new UserEntity();
        u.setId(1);
        UserEntity userEntity = userEntityMapper.selectByPrimaryKey(u);
        System.out.println(userEntity);

//        Boolean lock = redisTemplate.opsForValue().setIfAbsent("userLock"+userId, 1);
//        if (lock) {
//            try {
//                redisTemplate.expire("userLock"+userId, 60,TimeUnit.SECONDS);
//                UserEntity user = new UserEntity();
//                user.setId(i);
//                user.setRoleId(BigDecimal.valueOf(i));
//                int id = userEntityMapper.insertSelective(user);
//                System.out.println(id);
//                i++;
//            } finally {
//                //redisTemplate.delete("userLock"+userId);
//            }
//        } else {
//            System.out.println("没有获取到锁...."+s);
//            s++;
//            //throw new Exception("获取失败");
//        }
        return JsonMsgBean.success("1");
    }
}
