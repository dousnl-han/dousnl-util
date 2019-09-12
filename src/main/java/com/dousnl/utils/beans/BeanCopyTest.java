package com.dousnl.utils.beans;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.domain.UserDto;
/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/9/12 16:26
 */
public class BeanCopyTest {

    public static void main(String[] args) {
        User u = new User("张三", 18, "北京");
        UserDto userDto = BeanCopierUtils.copyOne2One(u, UserDto.class);
        System.out.println(JSON.toJSONString(userDto));
    }
}
