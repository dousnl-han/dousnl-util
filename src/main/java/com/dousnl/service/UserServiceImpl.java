package com.dousnl.service;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.domain.entity.UserEntity;
import com.dousnl.mapper.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<UserEntity> listUserEntity() {
        Example example = new Example(UserEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId",21111);
        List<UserEntity> list = userEntityMapper.selectByExample(example);


        List<UserEntity> userEntities = userEntityMapper.listUserEntity(21111);


        System.out.println("listUserEntity:"+list.size());

        String user = (String) redisTemplate.opsForValue().get("user11");
        if (Objects.isNull(user)) {
//            Example example = new Example(UserEntity.class);
//            Example.Criteria criteria = example.createCriteria();
//            criteria.andEqualTo("roleId",21111);
//            List<UserEntity> list = userEntityMapper.selectByExample(example);
//            if (!CollectionUtils.isEmpty(list)) {
//                redisTemplate.opsForValue().set("user", JSON.toJSONString(list));
//            }
            return list;
        } else {
            List<UserEntity> objects = JSON.parseArray(user, UserEntity.class);
            return objects;
        }
    }

    @Override
    public void addUser() {
        UserEntity u = new UserEntity();
        u.setUsername(String.valueOf(new Random().nextInt(10)));
        u.setPassword(String.valueOf(new Random().nextInt(10)));
        u.setRoleId(new BigDecimal(2));
        u.setBeginDate(new Date());
        u.setEndDate(new Date());
        userEntityMapper.insertSelective(u);
        redisTemplate.delete("user");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(Map<String, Integer> map) {
        Integer id = map.get("id");
        UserEntity u = new UserEntity();
        u.setId(10);
        userEntityMapper.updateByPrimaryKeySelective(u);
        final Thread thread = Thread.currentThread();
        System.out.println("当前线程：" + thread.getName());
        UserEntity userEntity = userEntityMapper.selectByPrimaryKey(10);

        userEntity.setOrderId((Integer.valueOf(userEntity.getOrderId()) + 1) + "");
        userEntityMapper.updateByPrimaryKeySelective(userEntity);

        System.out.println(userEntity);
    }


    @Async
    @Override
    public User getUser() throws InterruptedException {
        Thread.sleep(3000);
        User user = new User("async", 18, "shanghai");
        System.out.println(">>>>user:" + JSON.toJSONString(user));
        int i = 1 / 0;
        return user;
    }
}
