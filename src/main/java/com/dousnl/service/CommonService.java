package com.dousnl.service;

import com.dousnl.domain.entity.UserEntity;
import com.dousnl.mapper.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 可以注入，但不是注入这个抽象类，
 * 而是注入了这个抽象类的子类的代理类，这个子类必须被spring管理
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/8/14 11:13
 */
@Service
public class CommonService {

    @Autowired
    private UserEntityMapper userEntityMapper;
    @Autowired
    private DusyService dusyService;

    public List<UserEntity> getUserById(Integer id) throws InterruptedException {
        Example example = new Example(UserEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        List<UserEntity> entities = userEntityMapper.selectByExample(example);
        CompletableFuture<String> future = dusyService.busyMethod("1");
        //List<UserEntity> userById = dusyService.getUserById(1);
        return entities;
    }
}
