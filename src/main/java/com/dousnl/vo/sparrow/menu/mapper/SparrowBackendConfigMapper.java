package com.dousnl.vo.sparrow.menu.mapper;

import com.dousnl.vo.sparrow.menu.dto.SparrowBackendConfigDTO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SparrowBackendConfigMapper extends Mapper<SparrowBackendConfigDTO> {

    List<SparrowBackendConfigDTO> listByTemplateAlias(@Param("templateAlias") String templateAlias);
}
