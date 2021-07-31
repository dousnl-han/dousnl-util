package com.dousnl.service;

import com.dousnl.domain.entity.UserEntity;
import com.dousnl.mapper.UserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * TODO
 *
 * @version 1.0
 * @author: hanliangliang
 * @date: 2020/8/14 11:13
 */
public abstract class CommonService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    public List<UserEntity> getUserById(Integer id) throws InterruptedException {
        Example example = new Example(UserEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        List<UserEntity> entities = userEntityMapper.selectByExample(example);
        return entities;
    }
}
