package com.dousnl.domain.fdds.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_source_event_version")
public class SourceEventVersionDTO {

    /**
     * 主键
     */
    @Id
    private String tid;

    /**
     * 版本自增键
     */
    private Integer id;

    /**
     * 活动主键
     */
    @Column(name = "fk_event_tid")
    private String fkEventTid;

    /**
     * 发布状态(1:发布中，0：未发布)
     */
    @Column(name = "publish_status")
    private Integer publishStatus;

    /**
     * 发布记录(1:是否发布过，0：未发布过)
     */
    @Column(name = "publish_record")
    private Integer publishRecord;

    /**
     * 发布时间
     */
    @Column(name = "publish_time")
    private Date publishTime;

    /**
     * 发布人
     */
    private String publisher;

    /**
     * 版本地址url
     */
    @Column(name = "version_url")
    private String versionUrl;

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
     * 版本内容
     */
    private String context;

    /**
     * 获取版本自增键
     *
     * @return id - 版本自增键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置版本自增键
     *
     * @param id 版本自增键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取业务主键
     *
     * @return tid - 业务主键
     */
    public String getTid() {
        return tid;
    }

    /**
     * 设置业务主键
     *
     * @param tid 业务主键
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

    /**
     * 获取活动主键
     *
     * @param fkEventTid 活动主键
     */
    public String getFkEventTid() {
        return fkEventTid;
    }

    /**
     * 设置活动主键
     *
     * @param fkEventTid 活动主键
     */
    public void setFkEventTid(String fkEventTid) {
        this.fkEventTid = fkEventTid;
    }

    /**
     * 获取发布状态(1:发布中，0：未发布)
     *
     * @return publish_status - 发布状态(1:发布中，0：未发布)
     */
    public Integer getPublishStatus() {
        return publishStatus;
    }

    /**
     * 设置发布状态(1:发布中，0：未发布)
     *
     * @param publishStatus 发布状态(1:发布中，0：未发布)
     */
    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    /**
     * 获取发布记录(1:是否发布过，0：未发布过)
     *
     * @return publish_record - 发布记录(1:是否发布过，0：未发布过)
     */
    public Integer getPublishRecord() {
        return publishRecord;
    }

    /**
     * 设置发布记录(1:是否发布过，0：未发布过)
     *
     * @param publishRecord 发布记录(1:是否发布过，0：未发布过)
     */
    public void setPublishRecord(Integer publishRecord) {
        this.publishRecord = publishRecord;
    }

    /**
     * 获取发布时间
     *
     * @return publish_time - 发布时间
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置发布时间
     *
     * @param publishTime 发布时间
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取发布人
     *
     * @return publisher - 发布人
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 设置发布人
     *
     * @param publisher 发布人
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 获取版本地址url
     *
     * @return version_url - 版本地址url
     */
    public String getVersionUrl() {
        return versionUrl;
    }

    /**
     * 设置版本地址url
     *
     * @param versionUrl 版本地址url
     */
    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
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
     * 获取版本内容
     *
     * @return context - 版本内容
     */
    public String getContext() {
        return context;
    }

    /**
     * 设置版本内容
     *
     * @param context 版本内容
     */
    public void setContext(String context) {
        this.context = context;
    }
}
