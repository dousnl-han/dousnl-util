package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.domain.AdviceCanel;
import com.dousnl.domain.User;
import com.dousnl.domain.entity.AdvertImageDTO;
import com.dousnl.mapper.AdvertImageDTOMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/8 15:02
 */
@Controller
@RequestMapping("/redis")
public class RedisContorller {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AdvertImageDTOMapper advertImageDTOMapper;

    @RequestMapping(value = "/string",method = RequestMethod.GET)
    public void xp1() throws Exception {
        redisTemplate.opsForValue().set("num",23);
        Object num = redisTemplate.opsForValue().get("num");
        System.out.println("num:"+num);
        redisTemplate.delete("num");
        Boolean num1 = redisTemplate.hasKey("num");
        System.out.println(num1);
        redisTemplate.opsForValue().set("num",24,20,TimeUnit.SECONDS);
        Object num2 = redisTemplate.opsForValue().getAndSet("num", 25);
        System.out.println("num2:"+num2);
        System.out.println("num-getAndSet:"+redisTemplate.opsForValue().get("num"));

        System.out.println("num.size:"+redisTemplate.opsForValue().size("num"));

        User u=new User("zhang",18,"shangh");
        User u1=new User("li",19,"beij");
        List<User> list=new ArrayList<>();
        list.add(u);list.add(u1);
        redisTemplate.opsForValue().set("userlist",list);
        Object userlist = redisTemplate.opsForValue().get("userlist");
        List<User> list1 = (List<User>) redisTemplate.opsForValue().get("userlist");
        System.out.println(list1.toString());

        Object id = redisTemplate.opsForValue().get("source_event:activity_id");
        if (id!=null){
            id = redisTemplate.opsForValue().increment("source_event:activity_id", 1);
        }else{
            //操作数据库，设置id
            redisTemplate.opsForValue().set("source_event:activity_id",1);
        }
        System.out.println("=====id:"+id);
        redisTemplate.opsForValue().set("config:banner:group:showLimit_businessActivity",null);
        Integer showLimit = (Integer) redisTemplate.opsForValue().get("config:banner:group:showLimit_businessActivity");
        System.out.println("showLimit:"+showLimit);
        if (Objects.isNull(showLimit)) {
            redisTemplate.opsForValue().set("config:banner:group:showLimit_businessActivity", 10, 10, TimeUnit.MINUTES);
        }
        showLimit = (Integer) redisTemplate.opsForValue().get("config:banner:group:showLimit_businessActivity");
        System.out.println("showLimit:"+showLimit);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public void list() throws Exception {
        Long num = redisTemplate.opsForList().size("list");
        System.out.println(num);
        redisTemplate.opsForList().leftPush("list","java");
        redisTemplate.opsForList().leftPush("list", "c++");
        Long aLong = redisTemplate.opsForList().leftPush("list", "pyphon");
        System.out.println(aLong);
        List list = redisTemplate.opsForList().range("list11111", 0, -1);
        redisTemplate.delete("list");
        System.out.println(list.toString());

        redisTemplate.opsForList().rightPush("list-r","java");
        redisTemplate.opsForList().rightPush("list-r","cc");
        redisTemplate.opsForList().rightPush("list-r","pp");
        List listr = redisTemplate.opsForList().range("list-r", 0, -1);
        System.out.println(listr.toString());
        String[]strs=new String[]{"java","num","ak"};
        redisTemplate.opsForList().rightPushAll("list-a-r",strs);
        List listar = redisTemplate.opsForList().range("list-a-r", 0, -1);
        System.out.println(listar);
        redisTemplate.opsForList().set("list-a-r",1,"setvalue");
        List listarnew = redisTemplate.opsForList().range("list-a-r", 0, -1);
        System.out.println(listarnew);
        redisTemplate.opsForList().remove("list-a-r",1,"stevalue");
        List listarremove = redisTemplate.opsForList().range("list-a-r", 0, -1);
        System.out.println(listarremove);
        Object index = redisTemplate.opsForList().index("list-a-r", 1);
        System.out.println(index);

        Object leftPop = redisTemplate.opsForList().leftPop("list-a-r");
        System.out.println(leftPop);
        List listarLeftPop = redisTemplate.opsForList().range("list-a-r", 0, -1);
        System.out.println(listarLeftPop);

        User user=new User("1",1,"1");
        User user1=new User("2",2,"2");
        List listUser= Lists.newArrayList();
        listUser.add(user);listUser.add(user1);
        List<User> list2 = redisTemplate.opsForList().range("list-a-user", 0, -1);
        if (CollectionUtils.isEmpty(list2)){
            redisTemplate.opsForList().rightPushAll("list-a-user",listUser);
            redisTemplate.expire("list-a-user",10,TimeUnit.SECONDS);
        }
        List<User> list1 = redisTemplate.opsForList().range("list-a-user", 0, -1);
        System.out.println(list1);

    }

    @RequestMapping(value = "/hash",method = RequestMethod.GET)
    public void hash() throws Exception {
        redisTemplate.opsForHash().put("user","name","zhangsan");
        redisTemplate.opsForHash().put("user","age","21");
        redisTemplate.opsForHash().put("user","sex","woman");
        Map user = redisTemplate.opsForHash().entries("user");
        System.out.println(user.toString());
        Map<String,Object> hashmap=new HashMap<>();
        hashmap.put("name","lisi");
        hashmap.put("age",23);
        hashmap.put("sex","man");
        redisTemplate.opsForHash().putAll("user2",hashmap);
        Map user2 = redisTemplate.opsForHash().entries("user2");
        System.out.println(user2.toString());
        List user21 = redisTemplate.opsForHash().values("user2");
        System.out.println(user21.toString());
    }

    @RequestMapping(value = "/set",method = RequestMethod.GET)
    public void set() throws Exception {
        redisTemplate.delete("set:aaa:1");
        redisTemplate.delete("set");
        String[] str=new String[]{"zhangsan","lisi","wangwu","zhaoliu","111","234","567"};
        redisTemplate.opsForSet().add("set", str);

        String[] str2=new String[]{"zhangsan","lisi","46645"};
        redisTemplate.opsForSet().add("set", str2);

        Set set = redisTemplate.opsForSet().members("set");
        System.out.println(set);
        Object pop1 = redisTemplate.opsForSet().pop("set");
        Object pop2 = redisTemplate.opsForSet().pop("set");
        Object pop3 = redisTemplate.opsForSet().pop("set");
        System.out.println(pop1+" "+pop2+" "+pop3);
        redisTemplate.opsForSet().remove("set",new String[]{"11","wangwu"});
        Set setremove = redisTemplate.opsForSet().members("set");
        System.out.println(setremove);
        String[] str1=new String[]{"zhangsan","lisi","wangwu","zhaoliu"};
        redisTemplate.opsForSet().add("set1", str1);
        redisTemplate.opsForSet().move("set1","lisi","set2");
        Set set2 = redisTemplate.opsForSet().members("set2");
        System.out.println(set2);
    }

    @RequestMapping(value = "/setDel",method = RequestMethod.GET)
    public void setDel() throws Exception {
        redisTemplate.opsForSet().add("set1:AA:BB"+1, "2");
        redisTemplate.opsForSet().add("set1:AA:CC"+1, "3");
        redisTemplate.opsForSet().add("set1:AA:DD"+1, "3");
        redisTemplate.opsForSet().add("set1:AA:DD"+1, "4");
        redisTemplate.delete("set1:AA:BB"+1);
        redisTemplate.delete("set1:AA:CC"+1);
        redisTemplate.delete("set1:AA:BB"+2);
        redisTemplate.delete("set1:AA:ff"+1);
    }

    @RequestMapping(value = "/zset",method = RequestMethod.GET)
    public void zset() throws Exception {
        redisTemplate.opsForZSet().add("zset","111",1);
        redisTemplate.opsForZSet().add("zset","222",3);
        redisTemplate.opsForZSet().add("zset","333",2);
        Set zset = redisTemplate.opsForZSet().range("zset", 0, -1);
        System.out.println(zset.toString());
        ZSetOperations.TypedTuple<Object> tuple1=new DefaultTypedTuple<Object>("zset-1",1.1);
        ZSetOperations.TypedTuple<Object> tuple2=new DefaultTypedTuple<Object>("zset-2",1.3);
        ZSetOperations.TypedTuple<Object> tuple3=new DefaultTypedTuple<Object>("zset-3",1.2);
        ZSetOperations.TypedTuple<Object> tuple4=new DefaultTypedTuple<Object>("zset-4",1.4);
        Set<ZSetOperations.TypedTuple<Object>> tupleSet=new HashSet<>();
        tupleSet.add(tuple1);
        tupleSet.add(tuple2);
        tupleSet.add(tuple3);
        tupleSet.add(tuple4);
        redisTemplate.opsForZSet().add("zset-r",tupleSet);
        System.out.println(redisTemplate.opsForZSet().range("zset-r",0,-1));
        redisTemplate.opsForZSet().remove("zset-r", "zset-2");
        System.out.println(redisTemplate.opsForZSet().range("zset-r",0,-1));
        //返回排名
        Long rank = redisTemplate.opsForZSet().rank("zset-r", "zset-3");
        System.out.println("zset-3排名："+rank);
        Long count = redisTemplate.opsForZSet().count("zset-r", 0, 1.2);
        System.out.println("0-1.2个数："+count);
        Long size = redisTemplate.opsForZSet().size("zset-r");
        System.out.println("zset长度："+size);
        Double score = redisTemplate.opsForZSet().score("zset-r", "zset-3");
        System.out.println("zset-3分数："+score);
        System.out.println(redisTemplate.opsForZSet().range("zset", 0, -1).toString());
        redisTemplate.opsForZSet().removeRange("zset",1,2);
        System.out.println("removeRange:"+redisTemplate.opsForZSet().range("zset", 0, -1).toString());
    }

    @GetMapping("/get")
    public void get(AdviceCanel adviceCanel) {
        System.out.println(adviceCanel);
        System.out.println(adviceCanel);
    }
    @GetMapping("/incm")
    public void incm() {
        System.out.println(redisTemplate.opsForValue().get("incm"));
        redisTemplate.opsForValue().increment("incm",1);
    }

    @GetMapping("/image")
    public void image(String group) {
        Example example=new Example(AdvertImageDTO.class);
        example.createCriteria().andEqualTo("delFlag",1)
                .andEqualTo("groupCode","intellect");
        List<AdvertImageDTO> advertImageDTOS = advertImageDTOMapper.selectByExample(example);
        String val = JSON.toJSONString(advertImageDTOS);
        redisTemplate.opsForValue().set("banner:code:set:" + group, val);
        Map<String,AdvertImageDTO> hashmap=new HashMap<>();
        for (AdvertImageDTO dto:advertImageDTOS){
            hashmap.put(dto.getId().toString(),dto);
        }
        redisTemplate.opsForHash().putAll("banner:code:hash:" + group, hashmap);
        redisTemplate.opsForValue().multiSet(hashmap);
    }

    @PostMapping("/imageIdsSet")
    @ResponseBody
    public List<AdvertImageDTO> imageIdsSet(@RequestBody List<Integer> ids) {
        long start=System.currentTimeMillis();
        String value = (String) redisTemplate.opsForValue().get("banner:code:set:intellect");
        List<AdvertImageDTO> advertImageVOS = JSONObject.parseArray(value, AdvertImageDTO.class);
        List<AdvertImageDTO> collect = advertImageVOS.stream().filter(o -> ids.contains(o.getId())).collect(Collectors.toList());
        System.out.println((System.currentTimeMillis()-start)+"ms");
        return collect;
    }
    @PostMapping("/imageIdsHash")
    @ResponseBody
    public List<AdvertImageDTO> imageIdsHash(@RequestBody List<Integer> ids) {
        long start=System.currentTimeMillis();
        List<String> stringList = ids.stream().map(String::valueOf).collect(Collectors.toList());
        List<AdvertImageDTO> list = redisTemplate.opsForHash().multiGet("banner:code:hash:intellect", stringList);
        System.out.println((System.currentTimeMillis()-start)+"ms");
        return list;
    }
    @PostMapping("/imageIdsMSet")
    @ResponseBody
    public List<AdvertImageDTO> imageIdsMSet(@RequestBody List<Integer> ids) {
        long start=System.currentTimeMillis();
        List<String> stringList = ids.stream().map(String::valueOf).collect(Collectors.toList());
        List<AdvertImageDTO> list = redisTemplate.opsForValue().multiGet( stringList);
        System.out.println((System.currentTimeMillis()-start)+"ms");
        return list;
    }
}
