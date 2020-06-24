package com.dousnl.domain.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user")
public class UserEntity {
    /**
     * 主键ID
     */
    @Id
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 订单(合同)号
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 开始时间
     */
    @Column(name = "begin_date")
    private Date beginDate;

    /**
     * 结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取订单(合同)号
     *
     * @return order_id - 订单(合同)号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单(合同)号
     *
     * @param orderId 订单(合同)号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取开始时间
     *
     * @return begin_date - 开始时间
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 设置开始时间
     *
     * @param beginDate 开始时间
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取结束时间
     *
     * @return end_date - 结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束时间
     *
     * @param endDate 结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}