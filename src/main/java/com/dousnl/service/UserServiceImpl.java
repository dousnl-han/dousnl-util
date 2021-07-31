package com.dousnl.service;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.domain.entity.UserEntity;
import com.dousnl.mapper.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/17 10:13
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<UserEntity> listUserEntity(){

        String user = (String) redisTemplate.opsForValue().get("user");
        if (Objects.isNull(user)){
            Example example=new Example(UserEntity.class);
            //Example.Criteria criteria = example.createCriteria();
            //criteria.andEqualTo("roleId",2);
            List<UserEntity> list = userEntityMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(list)){
                redisTemplate.opsForValue().set("user", JSON.toJSONString(list));
            }
            return list;
        }else{
            List<UserEntity> objects = JSON.parseArray(user,UserEntity.class);
            return objects;
        }
    }
    @Override
    public void addUser(){
        UserEntity u=new UserEntity();
        u.setUsername(String.valueOf(new Random().nextInt(10)));
        u.setPassword(String.valueOf(new Random().nextInt(10)));
        u.setRoleId(2);
        u.setBeginDate(new Date());
        u.setEndDate(new Date());
        userEntityMapper.insertSelective(u);
        redisTemplate.delete("user");
    }


    @Async
    @Override
    public User getUser() throws InterruptedException {
        Thread.sleep(3000);
        User user = new User("async", 18, "shanghai");
        System.out.println(">>>>user:"+JSON.toJSONString(user));
        int i=1/0;
        return user;
    }
}
