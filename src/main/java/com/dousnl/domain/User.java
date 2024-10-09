package com.dousnl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/9/12 10:36
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class User implements InitializingBean {
    private String name;
    private Integer age;
    private String address;
    private Date date;
    private List list;
    private Boolean at=true;
    private Boolean own;
    private String uid;
    private Long num;
    private long longNum;
    private Integer fragmentType;
    private PrivatePileData privatePileData;
    private PrivatePileData privatePileData2;
    private PrivatePileData privatePileData3;
    private Double score;


    public User() {
    }

    public User(Integer age, String address) {
        this.age = age;
        this.address = address;
    }

    public User(Integer age, String address,Date da) {
        this.date=da;
        this.age = age;
        this.address = address;
    }

    public User(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(">>>>>>>>>>>>>>user...init...");
    }

    public boolean belongClassify(Integer classifyId) {
        if (classifyId == null) {
            return false;
        }
        if (CollectionUtils.isEmpty(getList())){
            return false;
        }
        return getList().stream().anyMatch(c -> classifyId.equals(c));
    }

//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println(">>>>>>>>>>>user..postProcessBeforeInitialization.....");
//        return null;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println(">>>>>>>>>>>user..postProcessAfterInitialization.....");
//        return null;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date da) {
        this.date = da;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Boolean getAt() {
        return at;
    }

    public void setAt(Boolean at) {
        this.at = at;
    }

    public Boolean getOwn() {
        return own;
    }

    public void setOwn(Boolean own) {
        this.own = own;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public static User createUser(){
        return new User();
    }
}
