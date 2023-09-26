package com.dousnl.domain.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_content_audit")
public class UserContentAudit {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户编号
     */
    @Column(name = "user_no")
    private Integer userNo;

    /**
     * 审核状态(1:通过，2：拒绝，3：待审核，4:已过期)
     */
    @Column(name = "audit_status")
    private Integer auditStatus;

    /**
     * 审核类型（1：头像管理，2：昵称管理）
     */
    @Column(name = "audit_type")
    private Integer auditType;

    /**
     * 第三方审核状态(1:通过，2：拒绝，3：待审核，4:已过期)
     */
    @Column(name = "original_status")
    private Integer originalStatus;

    /**
     * 风险类型
     */
    @Column(name = "risk_type")
    private Integer riskType;

    /**
     * 第三方风险类型 头像 (0:正常,100:涉政,200:色情,210:性感,300:广告,310:二维码,320:水印,400:暴恐,500:违规,510:不良场景,700:黑名单,710:白名单,800:高危账号,900:自定义)  昵称：(0:正常,100:涉政,200:色情,210:辱骂,300:广告,400:灌水,500:无意义,600:违禁,700:其他,720:黑账号,730:黑 IP,800:高危账号,900:自定义)
     */
    @Column(name = "original_risk_type")
    private Integer originalRiskType;

    /**
     * 第三方流水号
     */
    @Column(name = "third_serial_no")
    private String thirdSerialNo;

    /**
     * 第三方类型
     */
    @Column(name = "third_type")
    private Integer thirdType;

    /**
     * 昵称
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    @Column(name = "profile_photo")
    private String profilePhoto;

    /**
     * 是否操作过(0：否，1：是)
     */
    @Column(name = "operate_record")
    private Integer operateRecord;

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
     * 区号
     */
    @Column(name = "area_code")
    private String areaCode;

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
     * 获取用户编号
     *
     * @return user_no - 用户编号
     */
    public Integer getUserNo() {
        return userNo;
    }

    /**
     * 设置用户编号
     *
     * @param userNo 用户编号
     */
    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    /**
     * 获取审核状态(1:通过，2：拒绝，3：待审核，4:已过期)
     *
     * @return audit_status - 审核状态(1:通过，2：拒绝，3：待审核，4:已过期)
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置审核状态(1:通过，2：拒绝，3：待审核，4:已过期)
     *
     * @param auditStatus 审核状态(1:通过，2：拒绝，3：待审核，4:已过期)
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取审核类型（1：头像管理，2：昵称管理）
     *
     * @return audit_type - 审核类型（1：头像管理，2：昵称管理）
     */
    public Integer getAuditType() {
        return auditType;
    }

    /**
     * 设置审核类型（1：头像管理，2：昵称管理）
     *
     * @param auditType 审核类型（1：头像管理，2：昵称管理）
     */
    public void setAuditType(Integer auditType) {
        this.auditType = auditType;
    }

    /**
     * 获取第三方审核状态(1:通过，2：拒绝，3：待审核，4:已过期)
     *
     * @return original_status - 第三方审核状态(1:通过，2：拒绝，3：待审核，4:已过期)
     */
    public Integer getOriginalStatus() {
        return originalStatus;
    }

    /**
     * 设置第三方审核状态(1:通过，2：拒绝，3：待审核，4:已过期)
     *
     * @param originalStatus 第三方审核状态(1:通过，2：拒绝，3：待审核，4:已过期)
     */
    public void setOriginalStatus(Integer originalStatus) {
        this.originalStatus = originalStatus;
    }

    /**
     * 获取风险类型
     *
     * @return risk_type - 风险类型
     */
    public Integer getRiskType() {
        return riskType;
    }

    /**
     * 设置风险类型
     *
     * @param riskType 风险类型
     */
    public void setRiskType(Integer riskType) {
        this.riskType = riskType;
    }

    /**
     * 获取第三方风险类型 头像 (0:正常,100:涉政,200:色情,210:性感,300:广告,310:二维码,320:水印,400:暴恐,500:违规,510:不良场景,700:黑名单,710:白名单,800:高危账号,900:自定义)  昵称：(0:正常,100:涉政,200:色情,210:辱骂,300:广告,400:灌水,500:无意义,600:违禁,700:其他,720:黑账号,730:黑 IP,800:高危账号,900:自定义)
     *
     * @return original_risk_type - 第三方风险类型 头像 (0:正常,100:涉政,200:色情,210:性感,300:广告,310:二维码,320:水印,400:暴恐,500:违规,510:不良场景,700:黑名单,710:白名单,800:高危账号,900:自定义)  昵称：(0:正常,100:涉政,200:色情,210:辱骂,300:广告,400:灌水,500:无意义,600:违禁,700:其他,720:黑账号,730:黑 IP,800:高危账号,900:自定义)
     */
    public Integer getOriginalRiskType() {
        return originalRiskType;
    }

    /**
     * 设置第三方风险类型 头像 (0:正常,100:涉政,200:色情,210:性感,300:广告,310:二维码,320:水印,400:暴恐,500:违规,510:不良场景,700:黑名单,710:白名单,800:高危账号,900:自定义)  昵称：(0:正常,100:涉政,200:色情,210:辱骂,300:广告,400:灌水,500:无意义,600:违禁,700:其他,720:黑账号,730:黑 IP,800:高危账号,900:自定义)
     *
     * @param originalRiskType 第三方风险类型 头像 (0:正常,100:涉政,200:色情,210:性感,300:广告,310:二维码,320:水印,400:暴恐,500:违规,510:不良场景,700:黑名单,710:白名单,800:高危账号,900:自定义)  昵称：(0:正常,100:涉政,200:色情,210:辱骂,300:广告,400:灌水,500:无意义,600:违禁,700:其他,720:黑账号,730:黑 IP,800:高危账号,900:自定义)
     */
    public void setOriginalRiskType(Integer originalRiskType) {
        this.originalRiskType = originalRiskType;
    }

    /**
     * 获取第三方流水号
     *
     * @return third_serial_no - 第三方流水号
     */
    public String getThirdSerialNo() {
        return thirdSerialNo;
    }

    /**
     * 设置第三方流水号
     *
     * @param thirdSerialNo 第三方流水号
     */
    public void setThirdSerialNo(String thirdSerialNo) {
        this.thirdSerialNo = thirdSerialNo;
    }

    /**
     * 获取第三方类型
     *
     * @return third_type - 第三方类型
     */
    public Integer getThirdType() {
        return thirdType;
    }

    /**
     * 设置第三方类型
     *
     * @param thirdType 第三方类型
     */
    public void setThirdType(Integer thirdType) {
        this.thirdType = thirdType;
    }

    /**
     * 获取昵称
     *
     * @return real_name - 昵称
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置昵称
     *
     * @param realName 昵称
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取头像
     *
     * @return profile_photo - 头像
     */
    public String getProfilePhoto() {
        return profilePhoto;
    }

    /**
     * 设置头像
     *
     * @param profilePhoto 头像
     */
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    /**
     * 获取是否操作过(0：否，1：是)
     *
     * @return operate_record - 是否操作过(0：否，1：是)
     */
    public Integer getOperateRecord() {
        return operateRecord;
    }

    /**
     * 设置是否操作过(0：否，1：是)
     *
     * @param operateRecord 是否操作过(0：否，1：是)
     */
    public void setOperateRecord(Integer operateRecord) {
        this.operateRecord = operateRecord;
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

    /**
     * 获取区号
     *
     * @return area_code - 区号
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置区号
     *
     * @param areaCode 区号
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
