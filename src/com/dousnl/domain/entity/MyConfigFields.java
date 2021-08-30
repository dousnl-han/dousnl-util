package com.dousnl.domain.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_my_config_fields")
public class MyConfigFields {
    @Id
    private Integer id;

    /**
     * 配置表外键
     */
    @Column(name = "fk_my_config_id")
    private Integer fkMyConfigId;

    /**
     * 属性-key
     */
    @Column(name = "field_name")
    private String fieldName;

    /**
     * 属性-值
     */
    @Column(name = "field_value")
    private String fieldValue;

    @Column(name = "create_time")
    private Date createTime;

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
     * 获取配置表外键
     *
     * @return fk_my_config_id - 配置表外键
     */
    public Integer getFkMyConfigId() {
        return fkMyConfigId;
    }

    /**
     * 设置配置表外键
     *
     * @param fkMyConfigId 配置表外键
     */
    public void setFkMyConfigId(Integer fkMyConfigId) {
        this.fkMyConfigId = fkMyConfigId;
    }

    /**
     * 获取属性-key
     *
     * @return field_name - 属性-key
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * 设置属性-key
     *
     * @param fieldName 属性-key
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * 获取属性-值
     *
     * @return field_value - 属性-值
     */
    public String getFieldValue() {
        return fieldValue;
    }

    /**
     * 设置属性-值
     *
     * @param fieldValue 属性-值
     */
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
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
}