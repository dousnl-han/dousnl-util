package com.dousnl.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Size;
import java.beans.Transient;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/9/26 10:07
 */
@Getter
@Setter
@ToString
public class VaildDomain {


    //能源对接新增字段
    //@Transient  //字段不进行序列化
    @ApiModelProperty(notes = "私桩订单号")
    private String privateOrderNo;

    @ApiModelProperty(notes = "整车订单号")
    private String saleOrder;

    @ApiModelProperty(notes = "车主ID")
    private String customerId;

    @ApiModelProperty(notes = "购车联系人地址")
    private String carContactAddress;

    @ApiModelProperty(notes = "车系")
    private String series;

    @ApiModelProperty(notes = "车系型号")
    private String unit;

    @ApiModelProperty(notes = "车型描述")
    private String typeDescription;

    @ApiModelProperty(notes = "电机号")
    private String machineNo;

    @ApiModelProperty(notes = "渠道商CODE")
    private String channelCode;

    @ApiModelProperty(notes = "电力条件是否满足(0.是，1.否)")
    @Size(min = 1, max = 1)
    private String electricity;

    @ApiModelProperty(notes = "车位条件是否满足(0.是，1.否)")
    @Size(min = 1, max = 1)
    private String truckSpace;

    @ApiModelProperty(notes = "物业是否同意安装(0.是，1.否)")
    //@Size(min = 1, max = 1)
    //@Pattern(regexp = "^[0-1]{1}$")   //只能输入0,1数字
    private String realEstate;

    @ApiModelProperty(notes = "车辆型号")
    private String vehicleType;
}
