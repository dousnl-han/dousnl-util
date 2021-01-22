package com.dousnl.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 维修订单终止参数
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/10/21 10:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdviceCanel {

    @ApiModelProperty(value = "订单编码")
    private String adviceNo;

    @ApiModelProperty(value = "终止原因备注")
    private String breakRemark;
}
