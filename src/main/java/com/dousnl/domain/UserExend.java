package com.dousnl.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.lucene.util.CollectionUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/9/12 10:36
 */
@Data
public class UserExend extends User implements InitializingBean {

    @JSONField(name = "Name")
    private String name;

    @JSONField(name = "Age")
    private Integer age;


    private String address;
    private Object address1;
    private Date da;
    private List list;
    private Boolean at=false;
    private Boolean own;
    private String Add;
    private String uid;


    public static void main(String[] args) {
        UserExend userExend=new UserExend();
        userExend.setAge(19);
        userExend.setUid("131de1231231");

        UserExend userExend1=new UserExend();
        userExend1.setUid("131de");
        userExend1.setAge(19);

        UserExend userExend2=new UserExend();
        userExend2.setUid("131de");
        userExend2.setAge(19);
        userExend2.setName("3");

        UserExend userExend3=new UserExend();
        userExend3.setUid("131defffff");
        userExend3.setAge(19);


        List<UserExend> completedInfoWithResourceIdBOS =new ArrayList<>();
        completedInfoWithResourceIdBOS.add(userExend);
        completedInfoWithResourceIdBOS.add(userExend1);
        completedInfoWithResourceIdBOS.add(userExend2);
        completedInfoWithResourceIdBOS.add(userExend3);
        Map<String, UserExend> userCompletedNum = Collections.emptyMap();
        Integer i=1;
        if (i != 0) {

        }
        Map<String, UserExend> collect = completedInfoWithResourceIdBOS.stream().collect(Collectors.toMap(UserExend::getName, e -> e, (v1, v2) -> v1));

        System.out.println(JSON.toJSONString(collect));
        Map<String, User> resourceIdBOMap= Maps.newHashMap();
        if (CollectionUtils.isEmpty(resourceIdBOMap)){
            resourceIdBOMap=new HashMap<>();
            System.out.println("1111");
        }
        User user = resourceIdBOMap.get(1);
        UserDto dto=new UserDto();
        dto.setAdd("add");
        dto.setNickname("nick");
        System.out.println(dto);
    }

}
