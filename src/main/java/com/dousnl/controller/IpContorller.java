package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.domain.AdviceCanel;
import com.dousnl.domain.TestUser;
import com.dousnl.domain.TestUserA;
import com.dousnl.domain.User;
import com.dousnl.domain.UserT;
import com.dousnl.domain.entity.AdvertImageDTO;
import com.dousnl.mapper.AdvertImageDTOMapper;
import com.dousnl.utils.date.DateUtil;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/8 15:02
 */
@RestController
@RequestMapping("/ip")
public class IpContorller {
    private static final Logger logger = LoggerFactory.getLogger(IpContorller.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AdvertImageDTOMapper advertImageDTOMapper;

    @PostMapping("/sh")
    public String sh(@RequestBody Map<String, Object> data) {
        AdvertImageDTO advertImageDTO=new AdvertImageDTO();
        advertImageDTO.setId(1231231);
        List<AdvertImageDTO> select = advertImageDTOMapper.select(advertImageDTO);
        System.out.println(JSON.toJSONString(select));
        return "success";
    }

    @PostMapping("/getUser")
    public User getUser() {
        User u = new User();
        u.setDate(new Date());
        u.setUid("111");
        return u;
    }


    @RequestMapping(value = "/setUser", method = RequestMethod.GET)
    public void setUser() throws Exception {
        User u = new User();
        u.setAge(11);
        u.setName("zahngsa");
        u.setUid("111111");
        redisTemplate.opsForValue().set("user-json", JSON.toJSONString(u));
        String o = (String) redisTemplate.opsForValue().get("user-json");
        User parse = JSON.parseObject(o, User.class);
        System.out.println(JSON.toJSONString(parse));
    }

    @PostMapping("/clutchshot")
    public void clutchShot(@RequestBody Map<String, Object> data) {
        Object awardsStr = data.get("st");
        List<TestUser> objects = JSON.parseArray(JSON.toJSONString(awardsStr), TestUser.class);
        for (TestUser object : objects) {
            System.out.println(object);
        }
    }

    @PostMapping("/user")
    public String user(@RequestBody TestUser user) {
        //redisTemplate.convertAndSend("message.disc","hello world");
        System.out.println(JSON.toJSONString(user));
        TestUserA userA = new TestUserA();
        BeanUtils.copyProperties(user, userA, TestUserA.class);
        //userA.setAge(Integer.parseInt(user.getAge()));
        System.out.println(userA);
        return "success";
    }

}
