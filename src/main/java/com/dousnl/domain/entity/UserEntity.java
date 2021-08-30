package com.dousnl.domain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Data
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
    private BigDecimal roleId;

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

}
