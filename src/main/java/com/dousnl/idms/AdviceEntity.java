package com.dousnl.idms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.beans.Transient;
import java.util.Date;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/10/10 9:49
 */
@Getter
@Setter
@ToString
@ApiModel(value = "t_advice_order", description = "t_advice_order")
public class AdviceEntity {

    @ApiModelProperty(value = "主键ID", dataType = "string")
    private String id;

    @ApiModelProperty(value = "公司编号", dataType = "string")
    private String corpNo;

    @ApiModelProperty(value = "订单编码", dataType = "string")
    private String adviceNo;

    @ApiModelProperty(value = "安装订单编码", dataType = "string")
    private String orderNo;

    @ApiModelProperty(value = "联系客户时间", dataType = "string")
    private Date contactCustTime;

    @ApiModelProperty(value = "服务商编码", dataType = "string")
    private String supplierNo;

    @ApiModelProperty(value = "服务商名称", dataType = "string")
    private String supplierName;

    @ApiModelProperty(value = "报修类别 01咨询 02报修", dataType = "string")
    private String adviceType;

    @ApiModelProperty(value = "报修类别 01咨询 02报修", dataType = "string")
    private String adviceTypeName;

    @ApiModelProperty(value = "投诉内容", dataType = "string")
    private String adviceDesc;

    @ApiModelProperty(value = "投诉备注", dataType = "string")
    private String adviceRemark;

    @ApiModelProperty(value = "咨询时间 yyyymmdd hhmmss", dataType = "string")
    private Date adviceTime;

    @ApiModelProperty(value = "签收时间/拒绝时间 yyyymmdd hhmmss", dataType = "string")
    private Date receiveTime;

    @ApiModelProperty(value = "解决时间 yyyymmdd hhmmss", dataType = "string")
    private Date solutionTime;

    @ApiModelProperty(value = "签收备注或拒绝备注", dataType = "string")
    private String receiveDesc;

    @ApiModelProperty(value = "解决方案", dataType = "string")
    private String solutionDesc;

    @ApiModelProperty(value = "备注", dataType = "string")
    private String solutionRemark;

    @ApiModelProperty(value = "车主名称", dataType = "string")
    private String carOwner;

    @ApiModelProperty(value = "车主电话", dataType = "string")
    private String carOwnerPhone;

    @ApiModelProperty(value = "车架号", dataType = "string")
    private String vinNo;

    @ApiModelProperty(value = "是否完成 Y 是 N 否", dataType = "string")
    private String finishFlg;

    @ApiModelProperty(value = "安装完成时间", dataType = "local datetime (yyyy-MM-ddTHH:mm:ss)")
    private Date finishTime;

    @ApiModelProperty(value = "编号", dataType = "string")
    private String scrmNo;


    //@Column(name = "PILE_CODE")
    @ApiModelProperty(value = "编号", dataType = "string")
    private String pileCode;

    //@Column(name = "CONTACT")
    @ApiModelProperty(value = "编号", dataType = "string")
    private String contact;

    //@Column(name = "CONTACT_PHONE")
    @ApiModelProperty(value = "编号", dataType = "string")
    private String contactPhone;

    //@Column(name = "PILE_ADDRESS")
    @ApiModelProperty(value = "编号", dataType = "string")
    private String pileAddress;

    //@Column(name = "PILE_MODEL")
    @ApiModelProperty(value = "编号", dataType = "string")
    private String pileModel;

    //@Column(name = "FAULT_DESC")
    @ApiModelProperty(value = "编号", dataType = "string")
    private String faultDesc;

    //@Column(name = "FAULT_PICTURE")
    @ApiModelProperty(value = "编号", dataType = "string")
    private String faultPicture;

    //@Column(name = "APPOINTMENT_REPAIR_TIME")
    @ApiModelProperty(value = "编号", dataType = "string")
    private String appointmentRepairTime;

    //@Column(name = "CUSTOMER_REPORT_TIME")
    @ApiModelProperty(value = "编号", dataType = "string")
    private String customerReportTime;

    //@Column(name = "PILE_LON")
    @ApiModelProperty(value = "编号", dataType = "string")
    private String pileLon;

    //@Column(name = "PILE_LAT")
    @ApiModelProperty(value = "编号", dataType = "string")
    private String pileLat;

    //@Column(name = "TMS_ORDER_NO")
    @ApiModelProperty(value = "编号", dataType = "string")
    private String tmsOrderNo;

    /**
     * 当前步骤
     */
    //@Transient
    private Integer currentStep;

    /**
     * 是否撤回
     */
    //@Transient
    private String rollBackFlg;

    /**
     * 回撤备注
     */
    //@Transient
    private String remark;

    // /**
    //  * 超时开始时间
    //  */
    // //@Transient
    // private Date timeoutStartTime;
    //
    // /**
    //  * 超时开始时间
    //  */
    // //@Transient
    // private Date timeoutEndTime;

    /**
     * 签收状态 01通过 02拒绝
     */
    //@Transient
    private String receiveStatus = "01";

    //@Transient
    private Date baseTime;

    //@Transient
    private Date EndTime;
}
