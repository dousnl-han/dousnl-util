package com.dousnl.domain.es;

import lombok.Data;

import java.io.*;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2020/12/25
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2020/12/25       hanliangliang     新增              1001
 ********************************************************************/

public class User implements Serializable {
    private String username;
    private Integer age;
    private String address;
    private Integer count;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
