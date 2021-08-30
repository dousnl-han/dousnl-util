package com.dousnl.domain.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_my_config")
public class MyConfig {
    @Id
    private Integer id;

    /**
     * 组
     */
    @Column(name = "group_code")
    private String groupCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * icon
     */
    private String icon;

    /**
     * 排序序号
     */
    private Integer seq;

    /**
     * 1.未生效，2已生效
     */
    @Column(name = "shelf_status")
    private Integer shelfStatus;

    /**
     * 操作人名称
     */
    @Column(name = "operator_name")
    private String operatorName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 1:未删除2:删除
     */
    @Column(name = "del_flag")
    private Integer delFlag;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取组
     *
     * @return group_code - 组
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * 设置组
     *
     * @param groupCode 组
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取icon
     *
     * @return icon - icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置icon
     *
     * @param icon icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取排序序号
     *
     * @return seq - 排序序号
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 设置排序序号
     *
     * @param seq 排序序号
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取1.未生效，2已生效
     *
     * @return shelf_status - 1.未生效，2已生效
     */
    public Integer getShelfStatus() {
        return shelfStatus;
    }

    /**
     * 设置1.未生效，2已生效
     *
     * @param shelfStatus 1.未生效，2已生效
     */
    public void setShelfStatus(Integer shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    /**
     * 获取操作人名称
     *
     * @return operator_name - 操作人名称
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 设置操作人名称
     *
     * @param operatorName 操作人名称
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取1:未删除2:删除
     *
     * @return del_flag - 1:未删除2:删除
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置1:未删除2:删除
     *
     * @param delFlag 1:未删除2:删除
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}