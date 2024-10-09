package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.domain.entity.UserEntity;
import com.dousnl.mapper.UserEntityMapper;
//import com.dousnl.service.CommonService;
//import com.dousnl.service.ContstructService;
//import com.dousnl.service.DusyService;
import com.dousnl.service.UserService;
import com.dousnl.utils.ObjectSize;
import com.dousnl.utils.fdds.SoybeanRequestWrapper;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.NonNull;
import org.apache.commons.lang3.RandomUtils;
import org.apache.lucene.util.RamUsageEstimator;
import org.openjdk.jol.info.ClassLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 计算对象大小第一种方式---maven-pom文件增加 lucene-core  ---对应方法v1
 * 计算对象大小第二种方式---maven-pom文件增加打包插件---启动参数vm增加 -javaagent:target/ObjectSize.jar ----对应v2
 *
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/3/24 9:59
 */
@RestController
@RequestMapping("/test")
public class UserSizeController {

    private Logger logger = LoggerFactory.getLogger(UserSizeController.class);
    
    @Autowired
    private UserService userService;
//    @Autowired
//    private ContstructService contstructService;
//
//    //@Qualifier(value="dusyService")
//    @Autowired
//    private CommonService commonService;
//
//    @Autowired
//    private DusyService dusyService;
    @Autowired
    private UserEntityMapper userEntityMapper;


    /**
     * //计算指定对象及其引用树上的所有对象的综合大小，单位字节
     * long RamUsageEstimator.sizeOf(Object obj)
     *
     * //计算指定对象本身在堆空间的大小，单位字节
     * long RamUsageEstimator.shallowSizeOf(Object obj)
     *
     * //计算指定对象及其引用树上的所有对象的综合大小，返回可读的结果，如：2KB
     * String RamUsageEstimator.humanSizeOf(Object obj)
     *
     * @return
     */
    public static void main(String[] args) {
        String str="-非凡精读馆-为你解决的问题如果你";
        long l = RamUsageEstimator.sizeOf(str.getBytes());
        System.out.println(l);

        ArrayList<Integer> integers = Lists.newArrayList(13, 9);
        integers.clear();
        integers.add(11);
        System.out.println(integers);
        integers.clear();
        integers.add(1);
        System.out.println(integers);

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 50000; i++) {
            list.add((int) RandomUtils.nextLong(0L, 9999999L));
        }
        System.out.println("list长度：" + list.size());
        //大约产生10m内存---RamUsageEstimator.humanSizeOf(list)
        System.out.println("list大小字节：" + RamUsageEstimator.humanSizeOf(list));


        List<User> list1 = new ArrayList<User>();
        for (int i = 0; i < 50000; i++) {
            if (i==99910) {
                System.out.println("i长度：" + RamUsageEstimator.shallowSizeOf(i));
            }
            list1.add(new User(i + "", i, i + ""));
        }
        System.out.println("list长度：" + list1.size());
        //大约产生10m内存---RamUsageEstimator.humanSizeOf(list)
        System.out.println("list大小字节：" + RamUsageEstimator.humanSizeOf(list1));

        String s = "[\n" +
                "    {\n" +
                "        \"sk\": \"rightsRecord.createTime\",\n" +
                "        \"so\": 2,\n" +
                "        \"sp\": 1,\n" +
                "        \"skt\": 0\n" +
                "    }\n" +
                "]";
        List<SortTest> sorts = JSON.parseArray(s, SortTest.class);
        System.out.println(sorts);
    }
    @ApiOperation(value = "计算对象大小，单位字节", notes = "计算对象大小，单位字节")
    @GetMapping(value = "/v1")
    public String v2(@RequestParam("num") Integer num) {
        long start=System.currentTimeMillis();
        User u=new User("1", 1, "1");
        long l = RamUsageEstimator.shallowSizeOf(u);
        System.out.println("user 大小："+l);
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < num; i++) {
            if (i==99910) {
                System.out.println("i长度：" + RamUsageEstimator.shallowSizeOf(i));
            }
            list.add(new User(i + "", i, i + ""));
        }
        System.out.println("list长度：" + list.size());
        //大约产生10m内存---RamUsageEstimator.humanSizeOf(list)
        System.out.println("list大小字节：" + RamUsageEstimator.shallowSizeOf(list));
        //99911-----大约15.5m内存
        long end=System.currentTimeMillis();
        return "v1 seccuss...."+(end-start)+"ms";
    }

    @ApiOperation(value = "计算对象大小，单位字节", notes = "计算对象大小，单位字节")
    @GetMapping(value = "/v3")
    public String v3(@RequestParam("num") Integer num) {
        long start=System.currentTimeMillis();
        User u=new User(1 + "", 1, 1 + "");
        System.out.println("user 大小："+ClassLayout.parseInstance(u).toPrintable());
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < num; i++) {
            if (i==99910) {
                System.out.println("i长度：" + RamUsageEstimator.shallowSizeOf(i));
            }
            list.add(new User(i + "", i, i + ""));
        }
        System.out.println("list长度：" + list.size());
        //大约产生10m内存---RamUsageEstimator.humanSizeOf(list)
        System.out.println("list大小字节：" + ClassLayout.parseInstance(list).toPrintable());
        //99911-----大约15.5m内存
        long end=System.currentTimeMillis();
        return "v1 seccuss...."+(end-start)+"ms";
    }

    /**
     * 要正常使用，需要放开pom文件如下配置
     * 计算对象大小-第二种方式，此处先注释掉，需要测试时再放开
     * @param num
     * @return
     */
    @ApiOperation(value = "计算对象大小接口V2", notes = "计算对象大小接口V2")
    @GetMapping(value = "/v2")
    public String v1(@RequestParam("num") Integer num) {
        User u=new User(1 + "", 1, 1 + "");
        long l = ObjectSize.getSizeOf(u);
        System.out.println("user 大小："+l);
        List<User> list = new ArrayList<User>();
        long is=0;
        for (int i = 0; i < num; i++) {
            if (i==1) {
                is=ObjectSize.getSizeOf(u);
                System.out.println("i长度：" + is);
            }
            list.add(new User(i + "", i, i + ""));
        }
        System.out.println("list长度：" + list.size());
        System.out.println("is 大小字节："+(is*list.size()));
        System.out.println("list大小字节：" + ObjectSize.getSizeOf(list));
        return "v2 seccuss..";
    }


    @PostMapping("v100/uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws UnsupportedEncodingException {
        SoybeanRequestWrapper requestWrapper = (SoybeanRequestWrapper)request;
        String body = requestWrapper.getBody();
        Pattern URL_PATTERN = Pattern.compile("http[s]?://[A-Za-z.-]+\\.dushu\\.io/[a-zA-Z0-9-_/.]+(\\?contentType=[a-zA-Z0-9%/-]+)?");
        Matcher matcher = URL_PATTERN.matcher(body);
        String fileName = file.getOriginalFilename();
        return fileName;
    }

    @GetMapping("v100/user")
    @ResponseBody
    public List<UserEntity> listUser() {
        ArrayList<Integer> integers = Lists.newArrayList(13, 9);
        integers.clear();
        integers.add(11);
        System.out.println(integers);
        integers.clear();
        integers.add(1);
        System.out.println(integers);
        //userEntityMapper.updateUser(Lists.newArrayList(13,9));


        Example example = new Example(UserEntity.class);
        example.createCriteria()
                .andEqualTo("id", 1)
                .andLessThanOrEqualTo("beginDate", new Date())
                .andGreaterThanOrEqualTo("endDate", new Date());
        example.orderBy("endDate").asc();
        List<UserEntity> taskDTO = userEntityMapper.selectByExample(example);
        System.out.println(JSON.toJSONString(taskDTO));
        return null;
    }

    @GetMapping("v100/adduser")
    public void adduser() {
        //userService.addUser();
        userService.listUserEntity();
    }

    @PostMapping("v100/updateUser")
    public void updateUser(@RequestBody Map<String, Integer> map) {
        userService.updateUser(map);
    }

    @PostMapping("v100/getuser")
    @ResponseBody
    public String getuser(@RequestBody User vo) {
        logger.info(">>>>>>user:{}", vo);
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            logger.error(">>>>>>>>user:{}", vo, e);
        }
        return "success";
    }

    @GetMapping("/v100/getUserById")
    @ResponseBody
    public List<UserEntity> getUserById(Integer id) throws InterruptedException {
//        return commonService.getUserById(id);
        return null;
    }

    public void test(@NonNull Integer id) {
        System.out.println("test:"+id);
    }
}
