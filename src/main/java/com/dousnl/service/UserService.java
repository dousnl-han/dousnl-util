package com.dousnl.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dousnl.domain.entity.UserEntity;
import com.dousnl.mapper.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

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
public class UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    public List<UserEntity> listUserEntity(){

        String user = (String) redisTemplate.opsForValue().get("user");
        if (Objects.isNull(user)){
            Example example=new Example(UserEntity.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("roleId",2);
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

    public void addUser(){
        UserEntity u=new UserEntity();
        u.setUsername(String.valueOf(new Random().nextInt(10)));
        u.setPassword(String.valueOf(new Random().nextInt(10)));
        u.setRoleId(2);
        userEntityMapper.insertSelective(u);
        redisTemplate.delete("user");
    }
}