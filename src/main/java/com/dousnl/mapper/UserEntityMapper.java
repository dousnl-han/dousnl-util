package com.dousnl.mapper;

import com.dousnl.domain.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserEntityMapper extends Mapper<UserEntity> {

    int updateUser(@Param("ids") List<Integer> ids);
}
