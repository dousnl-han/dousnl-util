package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import java.util.concurrent.TimeUnit;

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

    @PostMapping("/string")
    public void xp1() throws Exception {
        redisTemplate.opsForValue().set("num",23);

        User u1= (User) redisTemplate.opsForValue().get("user_id");
        if (u1!=null){
            System.out.println(JSON.toJSONString(u1));
            return;
        }

        redisTemplate.opsForValue().set("user_id",new User(),2,TimeUnit.MINUTES);



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

    }

    @PostMapping("/list")
    public void list() throws Exception {
        Long num = redisTemplate.opsForList().size("list");
        System.out.println(num);
        redisTemplate.opsForList().leftPush("list","java");
        redisTemplate.opsForList().leftPush("list", "c++");
        Long aLong = redisTemplate.opsForList().leftPush("list", "pyphon");
        System.out.println(aLong);
        List list = redisTemplate.opsForList().range("list", 0, -1);
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
    }

    @PostMapping("/hash")
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

    @PostMapping("/set")
    public void set() throws Exception {
        String[] str=new String[]{"zhangsan","lisi","wangwu","zhaoliu","111","234","567"};
        redisTemplate.opsForSet().add("set", str);
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

    @PostMapping("/zset")
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
}
