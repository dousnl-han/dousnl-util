package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.fdds.*;
import com.dousnl.domain.fdds.dto.SourceEventInfoDTO;
import com.dousnl.mapper.SourceEventVersionMapper;
import com.dousnl.utils.IntegerEncryptTool;
import com.dousnl.utils.beans.BeanCopyUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/22 15:45
 */
@Slf4j
@RestController
@RequestMapping("/source")
public class SourceEventController {

    @Autowired
    private SourceEventVersionMapper sourceEventVersionMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/v100/sourceInfo")
    @ApiOperation(value = "活动落地页信息", notes = "活动落地页信息")
    public SourceEventInfoVO sourceInfo(@RequestBody @Validated SourceInfoParamVO vo) {
        log.debug("=========活动落地页信息参数,vo={}", vo);
        Integer decryptActivityId = 0, decryptId = 0;
        try {
            if (StringUtils.isEmpty(vo.getEncryptAId()) || (decryptActivityId = IntegerEncryptTool.decrypt(vo.getEncryptAId())) == 0) {
                log.warn("=========加密后活动编号为空");
            }
            if (!StringUtils.isEmpty(vo.getEncryptId())) {
                decryptId = IntegerEncryptTool.decrypt(vo.getEncryptId());
            }
            //SourceEventInfoVO result = (SourceEventInfoVO) redisTemplate.opsForValue().get("activity" + decryptActivityId);
            //if (result != null) {
            //    return result;
            //}
            //2000并发，redis，平均2000ms
            //sql直接查询返回，平均3500ms
            //去掉v.context AS context大字段，平均2300ms
            //大字段会影响部分性能，v.context AS context
            SourceEventInfoDTO info = sourceEventVersionMapper.getSourceEventInfo(decryptActivityId, decryptId);
            if (info != null) {
                SourceEventInfoVO ret = new SourceEventInfoVO();
                BeanCopyUtil.beanCopy(info, ret);
                //redisTemplate.opsForValue().set("activity" + decryptActivityId, ret);
                return ret;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @PostMapping("/v100/addSourceEvent")
    @ApiOperation(value = "活动新增", notes = "活动新增")
    public String addSourceEvent(@RequestBody SourceEventVO vo) {
        log.debug("=========活动容器添加参数，vo={}", vo);
        vo.setStatus(1);
        vo.setActivityType(1);
        log.info(">>>>>>>before:>>addSourceEvent:{}",vo);
        assignCreateByAndUpdateBy(vo, "hanliang");
        try {
            log.info(">>>>>>>after:>>>addSourceEvent:{}",vo);
        } catch (Exception e) {
            log.error("=========活动新增失败，vo={}", vo, e);
        }
        return "新增成功";
    }

    @PostMapping("/v100/copySourceEvent")
    @ApiOperation(value = "活动新增", notes = "活动新增")
    public String copySourceEvent(@RequestBody SourceEventCopyVO vo) {
        log.debug("=========活动容器添加参数，vo={}", vo);
        vo.setStatus(1);
        vo.setActivityType(1);
        log.info(">>>>>>>before:>>copySourceEvent:{}", JSON.toJSONString(vo));
        assignCreateByAndUpdateBy(vo, "hanliang");
        try {
            log.info(">>>>>>>af:>>copySourceEvent:{}", JSON.toJSONString(vo));
            log.info(">>>>>>>after:>>>copySourceEvent:{},vo.createBy:{},vo.updateBy:{},vo.createTime:{}",vo,vo.getCreateBy(),vo.getUpdateBy(),vo.getCreateTime());
        } catch (Exception e) {
            log.error("=========活动新增失败，vo={}", vo, e);
        }
        return "新增成功";
    }

    private void assignCreateByAndUpdateBy(Object vo, String username) {
        Date date = new Date();
        if (vo instanceof BaseCreateUserVO) {
            ((BaseCreateUserVO) vo).setCreateBy(username);
            ((BaseCreateUserVO) vo).setUpdateBy(username);
            ((BaseCreateUserVO) vo).setCreateTime(date);
            ((BaseCreateUserVO) vo).setUpdateTime(date);
        }
    }

    public static void main(String[] args) {
        String encrypt = IntegerEncryptTool.encrypt(1);
        System.out.println(encrypt);
    }
}
