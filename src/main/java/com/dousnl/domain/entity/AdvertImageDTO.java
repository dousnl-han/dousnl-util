package com.dousnl.domain.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_advert_image")
public class AdvertImageDTO {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 广告位code
     */
    @Column(name = "group_code")
    private String groupCode;

    private String name;

    /**
     * 广告图地址
     */
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    /**
     * 广告跳转目标类型
     */
    @Column(name = "target_type")
    private Integer targetType;

    /**
     * 对应的值或json串
     */
    private String extparam;

    /**
     * 顺序
     */
    private Integer seq;

    /**
     * 删除标志
     */
    @Column(name = "del_flag")
    private Integer delFlag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    private String description;

    /**
     * 目标
     */
    private String target;

    /**
     * 扩展字段
     */
    @Column(name = "fields_info")
    private String fieldsInfo;

    /**
     * 广告图地址
     */
    @Column(name = "raw_image_url")
    private String rawImageUrl;

    /**
     * 业务线
     */
    private String bussiness;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 状态(1.审核通过，2.审核中，3.未通过)
     */
    @Column(name = "audit_status")
    private Byte auditStatus;

    /**
     * 拒绝原因
     */
    private String reason;

    /**
     * 审核步骤
     */
    private Integer step;

    /**
     * banner类型(1:通用banner,2:默认banner)
     */
    private Byte type;

    /**
     * 指定分发方式(1:算法分发,2:指定位置分发)
     */
    private Byte appoint;

    /**
     * 位置类型(1:首页,2:我的页面)
     */
    @Column(name = "position_type")
    private Integer positionType;

    /**
     * 指定位置
     */
    private Integer position;

    /**
     * 重复降权(1:是,0:否)
     */
    @Column(name = "repeat_down")
    private Byte repeatDown;

    /**
     * 权重系数
     */
    private Double weight;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取广告位code
     *
     * @return group_code - 广告位code
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * 设置广告位code
     *
     * @param groupCode 广告位code
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取广告图地址
     *
     * @return image_url - 广告图地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置广告图地址
     *
     * @param imageUrl 广告图地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return start_date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return end_date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取广告跳转目标类型
     *
     * @return target_type - 广告跳转目标类型
     */
    public Integer getTargetType() {
        return targetType;
    }

    /**
     * 设置广告跳转目标类型
     *
     * @param targetType 广告跳转目标类型
     */
    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    /**
     * 获取对应的值或json串
     *
     * @return extparam - 对应的值或json串
     */
    public String getExtparam() {
        return extparam;
    }

    /**
     * 设置对应的值或json串
     *
     * @param extparam 对应的值或json串
     */
    public void setExtparam(String extparam) {
        this.extparam = extparam;
    }

    /**
     * 获取顺序
     *
     * @return seq - 顺序
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置顺序
     *
     * @param seq 顺序
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取删除标志
     *
     * @return del_flag - 删除标志
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标志
     *
     * @param delFlag 删除标志
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取目标
     *
     * @return target - 目标
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置目标
     *
     * @param target 目标
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * 获取扩展字段
     *
     * @return fields_info - 扩展字段
     */
    public String getFieldsInfo() {
        return fieldsInfo;
    }

    /**
     * 设置扩展字段
     *
     * @param fieldsInfo 扩展字段
     */
    public void setFieldsInfo(String fieldsInfo) {
        this.fieldsInfo = fieldsInfo;
    }

    /**
     * 获取广告图地址
     *
     * @return raw_image_url - 广告图地址
     */
    public String getRawImageUrl() {
        return rawImageUrl;
    }

    /**
     * 设置广告图地址
     *
     * @param rawImageUrl 广告图地址
     */
    public void setRawImageUrl(String rawImageUrl) {
        this.rawImageUrl = rawImageUrl;
    }

    /**
     * 获取业务线
     *
     * @return bussiness - 业务线
     */
    public String getBussiness() {
        return bussiness;
    }

    /**
     * 设置业务线
     *
     * @param bussiness 业务线
     */
    public void setBussiness(String bussiness) {
        this.bussiness = bussiness;
    }

    /**
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取状态(1.审核通过，2.审核中，3.未通过)
     *
     * @return audit_status - 状态(1.审核通过，2.审核中，3.未通过)
     */
    public Byte getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置状态(1.审核通过，2.审核中，3.未通过)
     *
     * @param auditStatus 状态(1.审核通过，2.审核中，3.未通过)
     */
    public void setAuditStatus(Byte auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取拒绝原因
     *
     * @return reason - 拒绝原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置拒绝原因
     *
     * @param reason 拒绝原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 获取审核步骤
     *
     * @return step - 审核步骤
     */
    public Integer getStep() {
        return step;
    }

    /**
     * 设置审核步骤
     *
     * @param step 审核步骤
     */
    public void setStep(Integer step) {
        this.step = step;
    }

    /**
     * 获取banner类型(1:通用banner,2:默认banner)
     *
     * @return type - banner类型(1:通用banner,2:默认banner)
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置banner类型(1:通用banner,2:默认banner)
     *
     * @param type banner类型(1:通用banner,2:默认banner)
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取指定分发方式(1:算法分发,2:指定位置分发)
     *
     * @return appoint - 指定分发方式(1:算法分发,2:指定位置分发)
     */
    public Byte getAppoint() {
        return appoint;
    }

    /**
     * 设置指定分发方式(1:算法分发,2:指定位置分发)
     *
     * @param appoint 指定分发方式(1:算法分发,2:指定位置分发)
     */
    public void setAppoint(Byte appoint) {
        this.appoint = appoint;
    }

    /**
     * 获取位置类型(1:首页,2:我的页面)
     *
     * @return position_type - 位置类型(1:首页,2:我的页面)
     */
    public Integer getPositionType() {
        return positionType;
    }

    /**
     * 设置位置类型(1:首页,2:我的页面)
     *
     * @param positionType 位置类型(1:首页,2:我的页面)
     */
    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    /**
     * 获取指定位置
     *
     * @return position - 指定位置
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * 设置指定位置
     *
     * @param position 指定位置
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * 获取重复降权(1:是,0:否)
     *
     * @return repeat_down - 重复降权(1:是,0:否)
     */
    public Byte getRepeatDown() {
        return repeatDown;
    }

    /**
     * 设置重复降权(1:是,0:否)
     *
     * @param repeatDown 重复降权(1:是,0:否)
     */
    public void setRepeatDown(Byte repeatDown) {
        this.repeatDown = repeatDown;
    }

    /**
     * 获取权重系数
     *
     * @return weight - 权重系数
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * 设置权重系数
     *
     * @param weight 权重系数
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }
}