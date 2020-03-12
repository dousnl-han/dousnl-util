package com.dousnl.domain;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/9/12 10:36
 */
@Data
public class User {
    private String name;
    private Integer age;
    private String address;
    private Object address1;
    private Date da;

    public User() {
    }

    public User(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
