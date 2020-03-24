package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dousnl.domain.*;
import com.dousnl.utils.response.Resp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2019/10/23 15:11
 */
@RestController
@RequestMapping("/valid")
public class ValidController {

    private static final String TAG = "数据决策（大屏）";
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    Gson gson;

    /**
     * 车主取消订单接口
     * @return
     */
    @ApiOperation(value = "车主取消订单接口", notes = "车主取消订单接口")
    @PostMapping(value = "/cancel")
    public Resp tmsPush(@Valid @Validated @RequestBody AdviceCanel canel, BindingResult bindingResult) {
        Resp resp = Resp.success();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return Resp.failed(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
//        try{
//            //JsonMsgBean msgBean = _adviceService.carOwnerCancelAdviceNo(canel);
//            if (!msgBean.getSuccess()) {
//                return Resp.failed((String) msgBean.getData());
//            }
//        } catch (PushException e) {
//            logger.error(e.getMessage(), e);
//            return Resp.failed(e.getMessage());
//        } catch (Exception e) {
//            logger.error("维修订单终止失败...", e);
//            return Resp.failed("维修订单终止失败...");
//        }
        return resp;
    }
    @ApiOperation(value = "车主取消订单接口", notes = "车主取消订单接口")
    @GetMapping(value = "/cancel1")
    public Resp tmsPushCancel(AdviceCanel canel, BindingResult bindingResult) {
        Resp resp = Resp.success();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            return Resp.failed(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
//        try{
//            //JsonMsgBean msgBean = _adviceService.carOwnerCancelAdviceNo(canel);
//            if (!msgBean.getSuccess()) {
//                return Resp.failed((String) msgBean.getData());
//            }
//        } catch (PushException e) {
//            logger.error(e.getMessage(), e);
//            return Resp.failed(e.getMessage());
//        } catch (Exception e) {
//            logger.error("维修订单终止失败...", e);
//            return Resp.failed("维修订单终止失败...");
//        }
        return resp;
    }

    @GetMapping("/pushDistPrivateCharge1")
    @ApiOperation(value = "获取私桩分布数据", notes = "获取充电桩分布数据")
    public void pushDistPrivateCharge1() {
        String str="{\"supperManagerDataParams\":{\"data\":{\"privatePileData\":[{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051504\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"AIHY1AC191000022\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"863010034501150\",\"privatePileLatitude\":31.2788880000000000,\"privatePileLongitude\":31.2788880000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051503\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC200110006\",\"privatePileLatitude\":31.2408610000000000,\"privatePileLongitude\":121.4548580000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"AIHY1AC191000002\",\"privatePileLatitude\":31.2024880000000000,\"privatePileLongitude\":121.4284740000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC200110002\",\"privatePileLatitude\":31.2300730000000000,\"privatePileLongitude\":121.4845980000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051501\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC200110004\",\"privatePileLatitude\":31.2348800000000000,\"privatePileLongitude\":121.4709510000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"L201910281412\",\"privatePileLatitude\":31.2316220000000000,\"privatePileLongitude\":121.4748470000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051500\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"12444214\",\"privatePileLatitude\":31.2319100000000000,\"privatePileLongitude\":121.4697040000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"L201910281412\",\"privatePileLatitude\":31.2316220000000000,\"privatePileLongitude\":121.4748470000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"AIZM1AC1912280098786\",\"privatePileLatitude\":31.2311760000000000,\"privatePileLongitude\":121.4721080000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"302\",\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC200110005\",\"privatePileLatitude\":31.2377790000000000,\"privatePileLongitude\":121.4848130000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051505\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051502\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"330100\",\"areaName\":\"杭州市\",\"cityCode\":\"3301\",\"cityName\":\"杭州市\",\"deviceCode\":\"201912111457\",\"privatePileLatitude\":31.2345200000000000,\"privatePileLongitude\":121.4723150000000000,\"provinceCode\":\"33\",\"provinceName\":\"浙江省\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"L201910281412\",\"privatePileLatitude\":31.2316220000000000,\"privatePileLongitude\":121.4748470000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"5435345\",\"privatePileLatitude\":31.2346320000000000,\"privatePileLongitude\":121.4785020000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"test202001151815\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051506\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"AIHY1AC191000022\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC221200113\",\"privatePileLatitude\":31.2327170000000000,\"privatePileLongitude\":121.4729660000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC200110001\",\"privatePileLatitude\":31.2344770000000000,\"privatePileLongitude\":121.4791050000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC201916690\",\"privatePileLatitude\":31.2306970000000000,\"privatePileLongitude\":121.4745560000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"43543sfs\",\"privatePileLatitude\":31.2376050000000000,\"privatePileLongitude\":121.4741250000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC221200003\",\"privatePileLatitude\":22.2846810000000000,\"privatePileLongitude\":114.1581770000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海\",\"deviceCode\":\"2019100815440001\",\"privatePileLatitude\":31.2340430000000000,\"privatePileLongitude\":121.4709420000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海\"},{\"areaCode\":\"310110\",\"cityCode\":\"3101\",\"deviceCode\":\"4434dd\",\"provinceCode\":\"31\"}]},\"tenantId\":\"0\"},\"tenantDataParams\":{\"data\":{\"privatePileData\":[]},\"tenantId\":\"test-\"}}";
        DataPairParams<PrivatePileData> data=JSON.parseObject(str,DataPairParams.class);

        redisTemplate.opsForValue().set("pile",JSON.toJSONString(data));
//        for(PrivatePile pile:data.getSupperManagerDataParams().getData().getPrivatePileData()){
//            redisTemplate.opsForHash().put("key-sup",pile.getProvinceCode(),pile);
//            redisTemplate.opsForHash().put("key-sup",pile.getCityCode(),pile);
//        }
        System.out.println(">>>>>>>>>>>>>>>获取私桩分布数据");
    }

    @GetMapping("/findDistPrivateCharge1")
    @ApiOperation(value = "获取私桩分布数据", notes = "获取充电桩分布数据")
    public String findDistPrivateCharge1(@RequestParam(value = "provinceCode",required = false)String provinceCode,
                                         @RequestParam(value = "cityCode",required = false)String cityCode,
                                         @RequestParam("type") Integer type) {


        String pile = (String) redisTemplate.opsForValue().get("pile");
        DataPairParams<PrivatePileData> data=JSON.parseObject(pile,DataPairParams.class);
        //Object o = redisTemplate.opsForHash().get("key-sup", "31");
        PrivatePileData a=JSON.parseObject(JSONObject.toJSONString(data.getSupperManagerDataParams().getData()),PrivatePileData.class);

        DataParams<PrivatePileData> supper=new DataParams<>();
        PrivatePileData newp=new PrivatePileData();
        List<PrivatePile> privatePileData= new ArrayList();
        for(PrivatePile p:a.getPrivatePileData()){
            if (1==type){
                privatePileData.add(p);
            }
            if (2==type){
                if (provinceCode.equals(p.getProvinceCode())){
                    privatePileData.add(p);
                }
            }
            if (3==type){
                if (cityCode.equals(p.getCityCode())){
                    privatePileData.add(p);
                }
            }
        }
        newp.setPrivatePileData(privatePileData);
        supper.setData(newp);
        data.setSupperManagerDataParams(supper);

        //System.out.println(">>>>>>>>>>>>省份信息："+JSON.toJSONString(o));
        System.out.println(">>>>>>>>>>>>>>>获取私桩分布数据");
        return JSON.toJSONString(data);
    }

    @GetMapping("/pushDistPrivateCharge2")
    @ApiOperation(value = "获取私桩分布数据", notes = "获取充电桩分布数据")
    public void pushDistPrivateCharge2() {
        String str="{\"supperManagerDataParams\":{\"data\":{\"privatePileData\":[{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051504\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"AIHY1AC191000022\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"863010034501150\",\"privatePileLatitude\":31.2788880000000000,\"privatePileLongitude\":31.2788880000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051503\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC200110006\",\"privatePileLatitude\":31.2408610000000000,\"privatePileLongitude\":121.4548580000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"AIHY1AC191000002\",\"privatePileLatitude\":31.2024880000000000,\"privatePileLongitude\":121.4284740000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC200110002\",\"privatePileLatitude\":31.2300730000000000,\"privatePileLongitude\":121.4845980000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051501\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC200110004\",\"privatePileLatitude\":31.2348800000000000,\"privatePileLongitude\":121.4709510000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"L201910281412\",\"privatePileLatitude\":31.2316220000000000,\"privatePileLongitude\":121.4748470000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051500\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"12444214\",\"privatePileLatitude\":31.2319100000000000,\"privatePileLongitude\":121.4697040000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"L201910281412\",\"privatePileLatitude\":31.2316220000000000,\"privatePileLongitude\":121.4748470000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"AIZM1AC1912280098786\",\"privatePileLatitude\":31.2311760000000000,\"privatePileLongitude\":121.4721080000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"302\",\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC200110005\",\"privatePileLatitude\":31.2377790000000000,\"privatePileLongitude\":121.4848130000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051505\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051502\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"330100\",\"areaName\":\"杭州市\",\"cityCode\":\"3301\",\"cityName\":\"杭州市\",\"deviceCode\":\"201912111457\",\"privatePileLatitude\":31.2345200000000000,\"privatePileLongitude\":121.4723150000000000,\"provinceCode\":\"33\",\"provinceName\":\"浙江省\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"L201910281412\",\"privatePileLatitude\":31.2316220000000000,\"privatePileLongitude\":121.4748470000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"5435345\",\"privatePileLatitude\":31.2346320000000000,\"privatePileLongitude\":121.4785020000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"test202001151815\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"201911051506\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"deviceCode\":\"AIHY1AC191000022\",\"privatePileLatitude\":31.2323920000000000,\"privatePileLongitude\":121.4733450000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC221200113\",\"privatePileLatitude\":31.2327170000000000,\"privatePileLongitude\":121.4729660000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC200110001\",\"privatePileLatitude\":31.2344770000000000,\"privatePileLongitude\":121.4791050000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC201916690\",\"privatePileLatitude\":31.2306970000000000,\"privatePileLongitude\":121.4745560000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"43543sfs\",\"privatePileLatitude\":31.2376050000000000,\"privatePileLongitude\":121.4741250000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"110101\",\"areaName\":\"东城区\",\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"deviceCode\":\"AIHY1AC221200003\",\"privatePileLatitude\":22.2846810000000000,\"privatePileLongitude\":114.1581770000000000,\"provinceCode\":\"11\",\"provinceName\":\"北京市\"},{\"areaCode\":\"310110\",\"areaName\":\"杨浦区\",\"cityCode\":\"3101\",\"cityName\":\"上海\",\"deviceCode\":\"2019100815440001\",\"privatePileLatitude\":31.2340430000000000,\"privatePileLongitude\":121.4709420000000000,\"provinceCode\":\"31\",\"provinceName\":\"上海\"},{\"areaCode\":\"310110\",\"cityCode\":\"3101\",\"deviceCode\":\"4434dd\",\"provinceCode\":\"31\"}]},\"tenantId\":\"0\"},\"tenantDataParams\":{\"data\":{\"privatePileData\":[]},\"tenantId\":\"test-\"}}";
        DataPairParams<PrivatePileData> data=JSON.parseObject(str,DataPairParams.class);
        DataParams<PrivatePileData> supperManagerDataParams = data.getSupperManagerDataParams();
        DataParams<PrivatePileData> tenantDataParams = data.getTenantDataParams();

        redisTemplate.opsForValue().set("pile"+supperManagerDataParams.getTenantId(),JSON.toJSONString(supperManagerDataParams));
        redisTemplate.opsForValue().set("pile"+tenantDataParams.getTenantId(),JSON.toJSONString(tenantDataParams));

        System.out.println(">>>>>>>>>>>>>>>获取私桩分布数据");
    }

    @GetMapping("/findDistPrivateCharge2")
    @ApiOperation(value = "获取私桩分布数据", notes = "获取充电桩分布数据")
    public String findDistPrivateCharge2(@RequestParam(value = "provinceCode",required = false)String provinceCode,
                                         @RequestParam(value = "cityCode",required = false)String cityCode,
                                         @RequestParam("type") Integer type,
                                         @RequestParam(value = "tenantId") String tenantId) {


        String pile = (String) redisTemplate.opsForValue().get("pile"+tenantId);
        DataParams<PrivatePileData> data=JSON.parseObject(pile,DataParams.class);
        PrivatePileData a=JSON.parseObject(JSONObject.toJSONString(data.getData()),PrivatePileData.class);

        DataParams<PrivatePileData> resultData=new DataParams<>();
        PrivatePileData newp=new PrivatePileData();

        List<PrivatePile> newPrivatePileData= new ArrayList();
        for(PrivatePile p:a.getPrivatePileData()){
            if (1==type){
                newPrivatePileData.add(p);
            }
            if (2==type && Objects.equals(provinceCode,p.getProvinceCode())){
                newPrivatePileData.add(p);
            }
            if (3==type && Objects.equals(cityCode,p.getCityCode())){
                newPrivatePileData.add(p);
            }
        }
        newp.setPrivatePileData(newPrivatePileData);
        resultData.setTenantId(tenantId);
        resultData.setData(newp);

        System.out.println(">>>>>>>>>>>>>>>获取私桩分布数据");
        return JSON.toJSONString(resultData);
    }

    @PostMapping("/pushDistPrivateCityCount")
    @ApiOperation(value = "推送私桩城市统计数据", notes = "推送私桩城市统计数据")
    public void pushDistPrivateCityCount(@RequestBody DataPairParams<PrivatePileCityCountData> params) {
        String str="{\"supperManagerDataParams\":{\"data\":{\"privatePileCityCountData\":[{\"acNum\":9,\"cityCode\":\"1101\",\"cityName\":\"北京市\",\"dcNum\":0,\"provinceCode\":\"11\"},{\"acNum\":20,\"cityCode\":\"3101\",\"cityName\":\"上海市\",\"dcNum\":1,\"provinceCode\":\"31\"},{\"acNum\":1,\"cityCode\":\"3301\",\"cityName\":\"杭州市\",\"dcNum\":0,\"provinceCode\":\"33\"}]},\"tenantId\":\"0\"},\"tenantDataParams\":{\"data\":{\"privatePileCityCountData\":[]},\"tenantId\":\"test-\"}}";
        DataPairParams<PrivatePileCityCountData> data=JSON.parseObject(str,DataPairParams.class);

        DataParams<PrivatePileCityCountData> supperManagerDataParams = data.getSupperManagerDataParams();
        DataParams<PrivatePileCityCountData> tenantDataParams = data.getTenantDataParams();

        redisTemplate.opsForValue().set("pile-count"+supperManagerDataParams.getTenantId(),JSON.toJSONString(supperManagerDataParams));
        redisTemplate.opsForValue().set("pile-count"+tenantDataParams.getTenantId(),JSON.toJSONString(tenantDataParams));
        System.out.println(">>>>>>>>>>>>>>>推送私桩城市统计数据");
    }

    @GetMapping("/findDistPrivateCityCount")
    @ApiOperation(value = "获取私桩分布数据", notes = "获取充电桩分布数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantId", value = "租户id", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "provinceCode", value = "省编码", required = false, paramType = "query", dataType = "string")
    })
    public DataParams<PrivatePileCityCountData> findDistPrivateCityCount(@RequestParam(value = "tenantId") String tenantId,
                                                                         @RequestParam(value = "provinceCode", required = false) String provinceCode) {
        String pile = (String) redisTemplate.opsForValue().get("pile-count"+tenantId);
        DataParams<PrivatePileCityCountData> data=JSON.parseObject(pile,DataParams.class);
        PrivatePileCityCountData a=JSON.parseObject(JSONObject.toJSONString(data.getData()),PrivatePileCityCountData.class);

        PrivatePileCityCountData newPileData = new PrivatePileCityCountData();
        List<PrivatePileCityCount> newPrivatePileList = new ArrayList();
        //省市区筛选
        for (PrivatePileCityCount p : a.getPrivatePileCityCountData()) {
            if (Objects.equals(provinceCode, p.getProvinceCode())) {
                newPrivatePileList.add(p);
            }
        }
        newPileData.setPrivatePileCityCountData(newPrivatePileList);
        DataParams<PrivatePileCityCountData> resultData = new DataParams<>();
        resultData.setTenantId(tenantId);
        resultData.setData(newPileData);
        return resultData;
    }
}
