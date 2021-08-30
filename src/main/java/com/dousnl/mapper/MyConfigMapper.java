package com.dousnl.mapper;

import com.dousnl.domain.entity.MyConfig;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MyConfigMapper extends Mapper<MyConfig> {

    /**
     * 查询最大顺序
     * @return
     */
    Integer selectMaxSeq(@Param("groupCode")String groupCode, @Param("shelfStatus")Integer shelfStatus);

    /**状态为2需要更新顺序*/
    int updateStatus(MyConfig dto);
}
