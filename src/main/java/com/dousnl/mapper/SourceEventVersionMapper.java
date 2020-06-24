package com.dousnl.mapper;

import com.dousnl.domain.fdds.dto.SourceEventInfoDTO;
import com.dousnl.domain.fdds.dto.SourceEventVersionDTO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;


public interface SourceEventVersionMapper extends Mapper<SourceEventVersionDTO> {

    SourceEventInfoDTO getSourceEventInfo(@Param("activityId") Integer activityId, @Param("id") Integer id);

}
