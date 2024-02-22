package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.domain.AdviceCanel;
import com.dousnl.domain.PrivatePile;
import com.dousnl.domain.PrivatePileData;
import com.dousnl.domain.TestUser;
import com.dousnl.domain.TestUserA;
import com.dousnl.domain.User;
import com.dousnl.domain.UserT;
import com.dousnl.domain.entity.AdvertImageDTO;
import com.dousnl.domain.fdds.BookPanGuBO;
import com.dousnl.file.FileUser;
import com.dousnl.mapper.AdvertImageDTOMapper;
import com.dousnl.utils.BeanCommonUtils;
import com.dousnl.utils.date.DateUtil;
import com.dousnl.utils.file.TxtUtil;
import com.dousnl.vo.FragmentPanGuBO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.val;
import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.*;
import java.text.ParseException;
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
@RestController
@RequestMapping("/redis")
public class RedisContorller {
    private static final Logger logger = LoggerFactory.getLogger(RedisContorller.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource(name = "templatePangu")
    private RedisTemplate<String, Object> redisTemplatePanGu;
    @Autowired
    private AdvertImageDTOMapper advertImageDTOMapper;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public void xp2() throws Exception {
        List list=Lists.newArrayList();
        User u=new User();
        u.setName("111");
        u.setAge(2);
        u.setAddress("22222");
        list.add(u);

        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set("user:quene:prod:1".getBytes(), JSON.toJSONString(list).getBytes());
                return null;
            }
        });

        String user = (String) redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte [] buffer = redisConnection.get("user:quene:prod:1".getBytes());
                return buffer==null?null:new String(buffer);
            }
        });
        List<UserT> mainBusinessBOS = JSONObject.parseArray(user, UserT.class);


        System.out.println(mainBusinessBOS);


    }

    @RequestMapping(value = "/string",method = RequestMethod.GET)
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
        redisTemplate.opsForValue().set("config:banner:group:showLimit_businessActivity",null);
        Integer showLimit = (Integer) redisTemplate.opsForValue().get("config:banner:group:showLimit_businessActivity");
        System.out.println("showLimit:"+showLimit);
        if (Objects.isNull(showLimit)) {
            redisTemplate.opsForValue().set("config:banner:group:showLimit_businessActivity", 10, 10, TimeUnit.MINUTES);
        }
        showLimit = (Integer) redisTemplate.opsForValue().get("config:banner:group:showLimit_businessActivity");
        System.out.println("showLimit:"+showLimit);
    }

    @PostMapping("/list")
    public void list() throws Exception {
        Long num = redisTemplate.opsForList().size("list");
        System.out.println(num);
        final Long aLong1 = redisTemplate.opsForList().leftPush("list", "java");
        final Long list3 = redisTemplate.opsForList().leftPush("list", "c++");
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

    @PostMapping("/hash")
    public void hash() throws Exception {
        redisTemplate.opsForHash().put("user","name","zhangsan");
        redisTemplate.opsForHash().put("user","age","21");
        redisTemplate.opsForHash().put("user","sex","woman");

        redisTemplate.opsForHash().delete("user","age");
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

    @RequestMapping(value = "/mset",method = RequestMethod.GET)
    public String mset() throws Exception {
        User user=new User("lisi",20,"shanghai");
        User user1=new User("zhangsan",18,"beijing");
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(month);
        System.out.println(day);
        String s = UUID.randomUUID().toString();
        System.out.println(s);
        String substring = s.substring(0, 5);
        String key="user:realname:100_"+substring;
        //redisTemplate.opsForValue().set(key,user,60,TimeUnit.DAYS);
        String s1 = UUID.randomUUID().toString();
        System.out.println(s1);
        String substring1 = s1.substring(0, 5);
        String key1="user:realname:100_"+substring1;
        //redisTemplate.opsForValue().set(key1,user1,60,TimeUnit.DAYS);

        Integer time = (Integer) redisTemplate.opsForValue().get("user:realname:10001_5");
//        if (time == null) {
//            time = 1;
//        } else {
//            if (time>=3){
//                return "本月修改已达3次";
//            }
//            time += 1;
//        }
        redisTemplate.opsForValue().increment("user:realname:10001_5", 1);
        redisTemplate.expire("user:realname:10001_5", 60,TimeUnit.SECONDS);
        System.out.println(time);
        return null;
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
        boolean flag=false;
    }

    @RequestMapping(value = "/setList",method = RequestMethod.GET)
    public List<User> setList() throws Exception {
        String cacheKey = String.format("openpage_cache_%s", 1);

        List<User> cacheList = (List<User>) redisTemplate.opsForValue().get(cacheKey);
        if (cacheList != null){
            return cacheList;
        }
        List<User> list=Lists.newArrayList();
        User u=new User();
        u.setAge(11);
        PrivatePileData privatePileData = new PrivatePileData();
        PrivatePile privatePile = new PrivatePile();
        privatePile.setAreaCode("11");
        privatePile.setAreaName("22");
        privatePile.setDeviceCode("243");
        privatePileData.setPile(privatePile);
        u.setPrivatePileData(privatePileData);
        list.add(u);
        redisTemplate.opsForValue().set(cacheKey, list, 5, TimeUnit.MINUTES);

        redisTemplate.opsForValue().set("openpage_cache_2", list);
        redisTemplate.expireAt("openpage_cache_2",DateUtil.addMillis(new Date(),10000));
        return list;
    }

    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public Object getList() throws Exception {
        Integer userId=114423432;
        int pageNo=1;
        String s = "app_notify_userId:" + userId + ":pageNo:" + pageNo;
        System.out.println("s:"+s);
        redisTemplate.opsForValue().set("app_notify_userId:114423432:pageNo:1", JSON.toJSONString(1));
        String USER_NOTIFY_CACHE_KEY = "app_notify_userId:%s:pageNo:%s";
        String format = String.format(USER_NOTIFY_CACHE_KEY, userId, pageNo);
        System.out.println("format:"+format);
        Object o = redisTemplate.opsForValue().get(format);

        redisTemplate.opsForValue().set("app_notify_userId", JSON.toJSONString(1));
        Object o1 = redisTemplate.opsForValue().get("app_notify_userId");
        return o1;
    }

    @RequestMapping(value = "/lpush",method = RequestMethod.GET)
    public List<Integer> lpush() throws Exception {
        redisTemplate.opsForList().leftPush("lpush-userIds", "1");
        redisTemplate.opsForList().leftPush("lpush-userIds", "2");
        redisTemplate.opsForList().leftPush("lpush-userIds", "3");

        Object o = redisTemplate.opsForList().rightPop("lpush-userIds");
        System.out.println(o);
        int i=0;
        for (;;){
            Object o1 = redisTemplate.opsForList().leftPush("lpush-userIds",i);
            System.out.println(o1);
            i++;
            if (i>2000000) break;
        }
        for (;;){
            Object o1 = redisTemplate.opsForList().rightPop("lpush-userIds");
            if (o1==null){
                break;
            }
            System.out.println(o1);
        }

        List range = redisTemplate.opsForList().range("lpush-userIds", 0, -1);
        System.out.println(range);
        return null;
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

        User user = new User();
        user.setName("111");
        User user1 = new User();
        user1.setName("2222");
        redisTemplate.opsForZSet().add("12442124",user,1);
        redisTemplate.opsForZSet().add("12442124",user1,2);
        User user2 = new User();
        user2.setName("2222");
        redisTemplate.opsForZSet().remove("12442124",user2);

        Set rank1 = redisTemplate.opsForZSet().range("12442124",0,-1);

        System.out.println("rank1:"+rank1);



    }

    @PostMapping("/12313")
    public void get(@RequestBody Integer pageNo) {
        List<Integer> integers = JSON.parseArray(
                "[400017862,400017695,400017631,400017598,400017564,400017505,400017401,400017220,400017225,400016858,400016860,400017193,400016859,400016857,400016856," +
                        "400016851,400016814,400016394,400016083,400016012,400015992,400015906,400015880,400015814,400015779,400015711,400015608,400015343,400015114,400015088,400014977," +
                        "400014829,400014774,400014439,400014555,400014445,400014349,400014248,400011659,400011320,400011295,400011016,400010727,400009396,400008296,400007872,400007272," +
                        "400006832,400006349,400005425,400005130,400004937,400004632,400004116,400003744,400003351,400003100,400002877,400002562,400002338,400002113,400001915,400001918,400001898," +
                        "400001749,400001656,400001528,400001560,400001511,400001497,400001208,400001172,400001045,400001031,400000994,400000875,400000829,400000834,400000721,400000570,400000566,400000516," +
                        "400000348,400000108,400000093,400000085,400000084,400000083,400000075,400000074,400000065,400000055,400000056,400000050,400000042,200000150,200000149,200000148,200000147,200000146,200000145," +
                        "200000144,200000143,200000142,200000141,200000137,200000136,200000135,200000134,200000133,200000132,200000131,200000128,200000126,200000123,200000120,200000118,200000115,200000114,200000113," +
                        "200000112,200000111,200000109,200000104,200000102,200000101,200000100,200000099,200000098,200000097,200000096,200000090,200000089,200000088,200000087,200000086,200000085,200000084,200000083," +
                        "200000082,200000081,200000080,200000079,200000078,200000076,200000074,200000073,200000071,200000069,200000068,200000067,200000066,200000065,200000064,200000063,200000062,200000061,200000060," +
                        "200000059,200000058,200000057,200000056,200000055,200000054,200000053,200000052,200000051,200000050,200000048,200000047,200000046,200000045,200000043,200000044,200000042,200000041,200000040,200000038," +
                        "200000037,200000036,200000035,200000034,200000033,200000032,200000031,200000030,200000029,200000025,200000024,200000023,200000022,200000020,200000019,200000018,200000017,200000016,200000015,200000014," +
                        "200000013,200000011,200000010,200000009,200000008,200000006,200000005,200000004,200000003,597,596,595,594,592,593,591,589,588,587,586,200000049,585,584,315,583,581,580,579,578,577,576,575,574,573,572,571," +
                        "570,569,567,565,563,561,558,555,554,553,552,551,550,549,548,547,521,495,492,475,424,388,386,385,384,383,382,381,380,379,378,377,376,375,374,373,372,371,370,369,368,367,366,365,364,363,362,361,360,358,357,356," +
                        "355,354,348,353,352,351,350,349,347,346,345,344,343,342,341,340,339,338,337,335,334,333,332,331,330,156,328,327,325,67,141,146,135,321,108,320,319,316,314,63,312,311,310,100,309,307,305,173,128,21,103,302,116," +
                        "152,297,294,69,148,160,127,102,272,180,206,183,178,177,176,175,174,172,171,166,170,167,165,163,162,161,157,155,153,150,147,144,92,91,84,76,72,65,55,18,19,20," +
                        "22]", Integer.class);
        for (Integer userId: integers) {
            redisTemplate.opsForValue().set("dengkong:userId:"+userId, userId,180,TimeUnit.DAYS);
        }
//        Map<String, List<BookPanGuBO>> map = (Map<String, List<BookPanGuBO>>) redisTemplatePanGu.opsForValue().get("book_category_real_data_v2");
//        //System.out.println(map);
//        List<String> cacheKey = integers.stream().skip((long) ((pageNo - 1) * 50)).limit(50).map(a -> "" + a).collect(Collectors.toList());
//        List<Object> bookPanGu = redisTemplate.opsForValue().multiGet(cacheKey);
//        List<BookPanGuBO> bookPanGuBOS = new ArrayList<>();
//        bookPanGuBOS = BeanCommonUtils.copyList(bookPanGu, BookPanGuBO.class);
//        if (CollectionUtils.isEmpty(bookPanGuBOS)){
//            System.out.println("cacheKey is null....111111");
//        }
    }

    @PostMapping("/redisArrayList")
    public List<User> get234(@RequestBody Integer pageNo) {
        Object o = redisTemplate.opsForValue().get("arrayList");
        if (o == null) {
            final List<Object> objects = Collections.emptyList();
            redisTemplate.opsForValue().set("arrayList", objects, 5, TimeUnit.MINUTES);
            return null;
        }
        List<User> o1 = (List<User>) o;
        System.out.println(o1);

        final List<User> users = BeanCommonUtils.copyList(o1, User.class);
        return o1;
    }

    public static void main(String[] args) {
        final ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        for (int i = 1; i <= integers.size(); i++) {
            if (i > 10) {
                break;
            }
            final Integer integer = integers.get(i - 1);
            System.out.println(integer);
        }
    }

    @PostMapping("/decrement")
    public int get346(@RequestBody Integer pageNo) {
        redisTemplate.opsForValue().set("decrement", 1);
        //redisTemplate.opsForValue().decrement("decrement");
        //redisTemplate.opsForValue().decrement("decrement");
        System.out.println(redisTemplate.opsForValue().get("decrement"));

        redisTemplate.opsForValue().setBit("decrement-bit", 11, true);

        Boolean bit = redisTemplate.opsForValue().getBit("decrement-bit", 11);
        System.out.println(bit);
        return (int) redisTemplate.opsForValue().get("decrement");
    }

    @PostMapping("/45646")
    public void get1(@RequestBody Integer pageNo) throws Exception{


        Map<String, JSONArray> adad = JSON.parseObject(TxtUtil.txt2String(new File("D:/1.txt")), Map.class);
        Map<String, List<BookPanGuBO>> bookMap1 = new LinkedHashMap<>();
        for (Map.Entry entry : adad.entrySet()){
            JSONArray objects = adad.get(entry.getKey());
            bookMap1.put(entry.getKey().toString(),objects.toJavaList(BookPanGuBO.class));
        }

        Map<String, List<BookPanGuBO>> bookMap = new LinkedHashMap<>();
        for (Map.Entry<String, List<BookPanGuBO>> entry : bookMap1.entrySet()) {
            List<BookPanGuBO> list = new ArrayList<>();
            for (BookPanGuBO item : entry.getValue()) {
                BookPanGuBO bookPanGuBO = new BookPanGuBO();
                bookPanGuBO.setAuthor(item.getAuthor());
                bookPanGuBO.setReadCount(item.getReadCount());
                bookPanGuBO.setCoverImageUrl(item.getCoverImageUrl());
                bookPanGuBO.setId(item.getId());
                bookPanGuBO.setName(item.getName());
                bookPanGuBO.setPublishTime(item.getPublishTime());
                bookPanGuBO.setSummary(item.getSummary());
                FragmentPanGuBO article = new FragmentPanGuBO();
                if (item.getArticle() != null) {
                    article.setId(item.getArticle().getId());
                    bookPanGuBO.setArticle(article);
                }
                FragmentPanGuBO audio = new FragmentPanGuBO();
                if (item.getAudio() != null) {
                    audio.setId(item.getAudio().getId());
                    bookPanGuBO.setAudio(audio);
                }
                FragmentPanGuBO video = new FragmentPanGuBO();
                if (item.getVideo() != null) {
                    video.setId(item.getVideo().getId());
                    bookPanGuBO.setVideo(video);
                }

                list.add(bookPanGuBO);
            }
            bookMap.put(entry.getKey(), list);
        }
        //redisTemplatePanGu.opsForValue().set("book_category_real_data_v3",bookMap);
        Map<String, List<BookPanGuBO>> map = (Map<String, List<BookPanGuBO>>) redisTemplatePanGu.opsForValue().get("book_category_real_data_v3");
        System.out.println("cacheKey is null....111111");
    }


    @GetMapping("/incm")
    public void incm(Integer userId, Integer bookId) {
        System.out.println(redisTemplate.opsForValue().get("incm"));
        redisTemplate.opsForValue().increment("incm",1);
        redisTemplate.opsForValue().setBit("userIdSetBit",1,true);
        redisTemplate.opsForValue().setBit("userIdSetBit",2,true);
        redisTemplate.opsForValue().setBit("userIdSetBit",6,true);
        redisTemplate.opsForValue().setBit("userIdSetBit",213181752,true);
        Boolean userIdSetBit7 = redisTemplate.opsForValue().getBit("userIdSetBit", 7);
        Boolean userIdSetBit17 = redisTemplate.opsForValue().getBit("userIdSetBit", 17);
        System.out.println(userIdSetBit7);
        System.out.println(userIdSetBit17);
        redisTemplate.opsForValue().set("bookIdSetBit_心灵","[1,2,3,4,45645767,56756]");
        redisTemplate.opsForValue().set("bookIdSetBit_人文","[1,2,3,4,45645767,21433]");
        redisTemplate.opsForValue().set("bookIdSetBit_历史","[1,2,3,4,45645767,56867]");

        System.out.println(redisTemplate.opsForValue().get("bookIdSetBit_心灵"));
        System.out.println(redisTemplate.opsForValue().get("bookIdSetBit_人文"));
        System.out.println(redisTemplate.opsForValue().get("bookIdSetBit_历史"));
    }

    @GetMapping("/image")
    public void image(String group) {
        Example example=new Example(AdvertImageDTO.class);
        example.createCriteria().andEqualTo("delFlag",1)
                .andEqualTo("groupCode","intellect");
        List<AdvertImageDTO> advertImageDTOS = advertImageDTOMapper.selectByExample(example);
        advertImageDTOS= advertImageDTOS.subList(0,10);

        String val = JSON.toJSONString(advertImageDTOS);
        redisTemplate.opsForValue().set("banner:code:set:" + group, val);
        Map<String,AdvertImageDTO> hashmap=new HashMap<>();
        ArrayList<Object> objects = Lists.newArrayList();

        for (AdvertImageDTO dto:advertImageDTOS){
            hashmap.put(dto.getId().toString(),dto);
            hashmap.put(dto.getId().toString(),dto);
            objects.add(dto.getId().toString());
            objects.add(dto.getId().toString());
        }
        redisTemplate.opsForHash().putAll("banner:code:hash:" + group, hashmap);
        redisTemplate.opsForValue().multiSet(hashmap);

        List<AdvertImageDTO> list = redisTemplate.opsForValue().multiGet(objects);

        System.out.println(list);
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
        Map entries = redisTemplate.opsForHash().entries("");
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


    @GetMapping("/v100/exportUserToekn")
    public Boolean exportUserToekn() {

        Set<String> keysTmp = new HashSet<>();
        Set<String> keys = (Set<String>) redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            ScanOptions scanOptions = ScanOptions.scanOptions().match("TOKEN:" + "*")
                    .count(100)
                    .build();
            Cursor<byte[]> cursor = connection.scan(scanOptions);
            while (cursor.hasNext()) {
                keysTmp.add(new String(cursor.next()));
            }
            return keysTmp;
        });
        for (String key : keys) {
            System.out.println(key.split(":")[1]);
        }
        return true;
    }



    @PostMapping("/topic")
    public String imagePost(@Validated @RequestBody User user) {
        //redisTemplate.convertAndSend("message.disc","hello world");
        System.out.println(JSON.toJSONString(user));
        try{
            int i = 1/0;
        }catch (Exception e){
            logger.error("订单完成扣除冻结积分失败.....orderNumber：{}，orderExtentBOs:{}",1111,new User(),e);
        }
        return "success";
    }

    @PostMapping("/sh")
    public String sh(@RequestBody Map<String, Object> data) {
        //System.out.println("success.....");
        if (data != null){
            String date = (String) data.get("date");
            try {
                System.out.println(DateUtil.dateToString(DateUtil.parse(date)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    @PostMapping("/getUser")
    public User getUser() {
        User u=new User();
        u.setDate(new Date());
        u.setUid("111");
        return u;
    }


    @RequestMapping(value = "/setUser",method = RequestMethod.GET)
    public void setUser() throws Exception {
        User u=new User();
        u.setAge(11);
        u.setName("zahngsa");
        u.setUid("111111");
        redisTemplate.opsForValue().set("user-json",JSON.toJSONString(u));
        String o = (String) redisTemplate.opsForValue().get("user-json");
        User parse = JSON.parseObject(o,User.class);
        System.out.println(JSON.toJSONString(parse));
    }

    @PostMapping("/clutchshot")
    public void clutchShot(@RequestBody Map<String, Object> data) {
        Object awardsStr = data.get("st");
        List<TestUser> objects = JSON.parseArray(JSON.toJSONString(awardsStr), TestUser.class);
        for (TestUser object : objects){
            System.out.println(object);
        }
    }

    @PostMapping("/user")
    public String user(@RequestBody TestUser user) {
        //redisTemplate.convertAndSend("message.disc","hello world");
        System.out.println(JSON.toJSONString(user));
        TestUserA userA=new TestUserA();
        BeanUtils.copyProperties(user,userA,TestUserA.class);
        //userA.setAge(Integer.parseInt(user.getAge()));
        System.out.println(userA);
        return "success";
    }

}
