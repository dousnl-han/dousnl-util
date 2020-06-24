package com.dousnl.controller;

import com.dousnl.domain.fdds.SourceEventInfoVO;
import com.dousnl.domain.fdds.SourceInfoParamVO;
import com.dousnl.domain.fdds.dto.SourceEventInfoDTO;
import com.dousnl.mapper.SourceEventVersionMapper;
import com.dousnl.utils.IntegerEncryptTool;
import com.dousnl.utils.beans.BeanCopyUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public SourceEventInfoVO sourceInfo(@RequestBody SourceInfoParamVO vo) {
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
}
