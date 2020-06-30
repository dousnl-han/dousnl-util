package com.dousnl.domain.fdds;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Description: 活动BO
 * Company    : 上海黄豆网络科技有限公司
 * @author     : hanliangliang
 * @date       : 2020/5/21
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0        2020/5/21        hanliangliang    新增              1001
 ********************************************************************/
@Data
public class SourceEventVO extends BaseCreateUserVO implements Serializable {

    @ApiModelProperty("活动容器编号")
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

    @ApiModelProperty("活动状态(1:生效,0:失效)")
    private Integer status;

    @ApiModelProperty("开始时间")
    @NotNull(message = "活动有效期开始时间不能空")
    private Date startTime;

    @ApiModelProperty("结束时间")
    @NotNull(message = "活动有效期结束时间不能空")
    private Date endTime;

    @ApiModelProperty("活动来源 (1:活动容器)")
    private Integer activityType;

    @ApiModelProperty("删除标志 1：未删除；2：已删除")
    private Integer delFlag;

    @ApiModelProperty("渠道名称")
    private String channelName;
}
