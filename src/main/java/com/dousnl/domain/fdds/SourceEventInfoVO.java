package com.dousnl.domain.fdds;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * APP落地页版本信息
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/27 19:05
 */
@Data
public class SourceEventInfoVO implements Serializable{

    @ApiModelProperty("主键")
    private String tid;

    @ApiModelProperty("活动容器编号")
    private Integer activityId;

    @ApiModelProperty("活动名称")
    private String name;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("加密后渠道id")
    private String composeChannelId;

    @ApiModelProperty("渠道名称")
    private String channelName;

    @ApiModelProperty("业务方名称")
    private String bussinessName;

    @ApiModelProperty("版本地址url")
    private String versionUrl;

    @ApiModelProperty("版本内容")
    private String context;
}
