package com.dousnl.domain.fdds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookClassifyPanGuBO implements Serializable {
    private static final long serialVersionUID = 374407204223311826L;
    private Integer id;
    private String name;
    private String remark;
    private Integer showFlag;
    private Date createTime;
    private Integer color;
    private Boolean vipFlag;
    private Integer seq;
    private String newId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Boolean getVipFlag() {
        return vipFlag;
    }

    public void setVipFlag(Boolean vipFlag) {
        this.vipFlag = vipFlag;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }
}
