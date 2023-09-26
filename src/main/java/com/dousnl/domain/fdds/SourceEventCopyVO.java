package com.dousnl.domain.fdds;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description: 活动复制VO
 * Company    : 上海黄豆网络科技有限公司
 * @author     : hanliangliang
 * @date       : 2020/5/20
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0        2020/5/20        hanliangliang    新增              1001
 ********************************************************************/
@Data
public class SourceEventCopyVO extends BaseCreateUserVO implements Serializable {

    @ApiModelProperty("复制活动主键")
    @NotNull
    private String sourceTid;

    @ApiModelProperty("活动容器主键")
    private Integer activityId;

    @ApiModelProperty("主键")
    private String tid;

    @ApiModelProperty("活动名称")
    @NotNull(message = "活动名称不能空")
    @Size(max = 15,message = "活动名称最大长度为15")
    private String name;

    @ApiModelProperty("渠道id")
    private Integer channelId;

    @ApiModelProperty("业务方名称")
    @NotNull(message = "业务方名称不能空")
    @Size(max = 15,message = "业务方最大长度为15")
    private String bussinessName;

    @ApiModelProperty("活动描述")
    private String remark;

    @ApiModelProperty("活动状态(1:生效,0:失效")
    private Integer status;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("活动来源 (1:活动容器)")
    private Integer activityType;

    @ApiModelProperty("删除标志 1：未删除；2：已删除")
    private Integer delFlag;

    @ApiModelProperty("渠道名称")
    private String channelName;

    @NotNull
    @Size(max = 5)
    private List<Integer> ids;
}
