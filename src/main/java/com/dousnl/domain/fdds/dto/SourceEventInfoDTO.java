package com.dousnl.domain.fdds.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 活动版本详情
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/27 19:14
 */
@Data
public class SourceEventInfoDTO {

    @ApiModelProperty("主键")
    private String tid;

    @ApiModelProperty("活动容器编号")
    private Integer activityId;

    @ApiModelProperty("活动名称")
    private String name;

    @ApiModelProperty("活动状态(1:生效,0:失效)")
    private Integer status;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("渠道id")
    private Integer channelId;

    @ApiModelProperty("渠道名称")
    private String channelName;

    @ApiModelProperty("业务方名称")
    private String bussinessName;

    @ApiModelProperty("版本地址url")
    private String versionUrl;

    @ApiModelProperty("版本内容")
    private String context;
}
