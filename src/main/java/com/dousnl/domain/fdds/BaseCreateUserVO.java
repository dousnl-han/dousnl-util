package com.dousnl.domain.fdds;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/29 15:44
 */
@Data
public class BaseCreateUserVO {
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("更新人")
    private String updateBy;
}
