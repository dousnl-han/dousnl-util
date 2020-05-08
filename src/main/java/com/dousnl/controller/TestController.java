package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.utils.ObjectSize;
import io.swagger.annotations.ApiOperation;
import org.apache.lucene.util.RamUsageEstimator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

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
public class TestController {

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
     * @param num
     * @return
     */
    @ApiOperation(value = "车主取消订单接口", notes = "车主取消订单接口")
    @GetMapping(value = "/v1")
    public String v2(@RequestParam("num") Integer num) {
        long start=System.currentTimeMillis();
        User u=new User(1 + "", 1, 1 + "");
        String l = RamUsageEstimator.humanSizeOf(u);
        System.out.println("user 大小："+l);
        List<User> list = new ArrayList<User>(99915);
        for (int i = 0; i < num; i++) {
            if (i==99910) {
                System.out.println("i长度：" + RamUsageEstimator.humanSizeOf(i));
            }
            list.add(new User(i + "", i, i + ""));
        }
        System.out.println("list长度：" + list.size());
        //大约产生10m内存---RamUsageEstimator.humanSizeOf(list)
        System.out.println("list大小字节：" + RamUsageEstimator.humanSizeOf(list));
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
    @ApiOperation(value = "车主取消订单接口", notes = "车主取消订单接口")
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
}
