package com.dousnl.mapper;

import com.dousnl.domain.Config;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

public interface ConfigMapper extends Mapper<Config> {

    Integer updateSeq(List<Config> list);
}
