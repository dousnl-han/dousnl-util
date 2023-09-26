package com.dousnl.controller;

import com.dousnl.domain.entity.UserEntity;
import com.dousnl.mapper.UserEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/7/21
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/7/21       hanliangliang     新增              1001
 ********************************************************************/
@Slf4j
@RestController
public class BaseController {

    @Autowired
    private UserEntityMapper userEntityMapper;

    public UserEntity getUser(){
        return userEntityMapper.selectByPrimaryKey(1);
    }

}
