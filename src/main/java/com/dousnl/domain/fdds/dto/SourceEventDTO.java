package com.dousnl.domain.fdds.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_source_event")
public class SourceEventDTO {
    /**
     * 主键
     */
    @Id
    private String tid;

    /**
     * 活动容器编号
     */
    @Column(name = "activity_id")
    private Integer activityId;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 渠道id
     */
    @Column(name = "channel_id")
    private Integer channelId;

    /**
     * 业务方名称
     */
    @Column(name = "bussiness_name")
    private String bussinessName;

    /**
     * 活动描述
     */
    private String remark;

    /**
     * 活动状态(1:生效,0:失效)
     */
    private Integer status;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 活动来源 (1:活动容器)
     */
    @Column(name = "activity_type")
    private Integer activityType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 删除标志 1：未删除；2：已删除
     */
    @Column(name = "del_flag")
    private Integer delFlag;

    /**
     * 获取主键
     *
     * @return tid - 主键
     */
    public String getTid() {
        return tid;
    }

    /**
     * 设置主键
     *
     * @param tid 主键
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

    /**
     * 获取活动容器编号
     *
     * @return activity_id - 活动容器编号
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * 设置活动容器编号
     *
     * @param activityId 活动容器编号
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * 获取活动名称
     *
     * @return name - 活动名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置活动名称
     *
     * @param name 活动名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取渠道id
     *
     * @return channel_id - 渠道id
     */
    public Integer getChannelId() {
        return channelId;
    }

    /**
     * 设置渠道id
     *
     * @param channelId 渠道id
     */
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    /**
     * 获取业务方名称
     *
     * @return bussiness_name - 业务方名称
     */
    public String getBussinessName() {
        return bussinessName;
    }

    /**
     * 设置业务方名称
     *
     * @param bussinessName 业务方名称
     */
    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }

    /**
     * 获取活动描述
     *
     * @return remark - 活动描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置活动描述
     *
     * @param remark 活动描述
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取活动状态(1:生效,0:失效)
     *
     * @return status - 活动状态(1:生效,0:失效)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置活动状态(1:生效,0:失效)
     *
     * @param status 活动状态(1:生效,0:失效)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取活动来源 (1:活动容器)
     *
     * @return activity_type - 活动来源 (1:活动容器)
     */
    public Integer getActivityType() {
        return activityType;
    }

    /**
     * 设置活动来源 (1:活动容器)
     *
     * @param activityType 活动来源 (1:活动容器)
     */
    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取更新人
     *
     * @return update_by - 更新人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取删除标志 1：未删除；2：已删除
     *
     * @return del_flag - 删除标志 1：未删除；2：已删除
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标志 1：未删除；2：已删除
     *
     * @param delFlag 删除标志 1：未删除；2：已删除
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
