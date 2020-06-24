package com.dousnl.domain.fdds;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * APP落地页参数信息
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/27 19:05
 */
@Data
public class SourceInfoParamVO {

    @ApiModelProperty("加密后活动编号")
    private String encryptAId;

    @ApiModelProperty("加密后版本id")
    private String encryptId;
}
