package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.Config;
import com.dousnl.domain.entity.UserContentAudit;
import com.dousnl.mapper.ConfigMapper;
import com.dousnl.mapper.UserContentAuditMapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2020/11/16
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2020/11/16       hanliangliang     新增              1001
 ********************************************************************/
@RestController
@RequestMapping("/user/audit")
public class UserAuditController {

    @Autowired
    private UserContentAuditMapper userContentAuditMapper;
    @Autowired
    private ConfigMapper configMapper;

    @GetMapping("/save")
    public String saveUserAudit(Integer num){
            UserContentAudit audit=new UserContentAudit();
            audit.setAreaCode("+86");
            audit.setAuditStatus(1);
            audit.setAuditType(1);
            audit.setCreateBy("hanliangliang");
            audit.setCreateTime(new Date());
            audit.setDelFlag(1);
            audit.setMobile("13162505297");
            audit.setOperateRecord(1);
            audit.setOriginalRiskType(1);
            audit.setProfilePhoto("image://image/png;1603347897ade418fe2add5567c7fe06df48562ba3ocpti3"+1);
            audit.setRealName("手机用户_"+1);
            audit.setRiskType(1);
            audit.setUserNo(1);
            audit.setThirdSerialNo(String.valueOf(System.currentTimeMillis()));
            audit.setUpdateTime(new Date());
            audit.setUpdateBy("hanliangliang");
            userContentAuditMapper.insertSelective(audit);
            System.out.println(1);
        return "success";
    }

    @PostMapping("/list")
    public List<UserContentAudit> list(@RequestBody Map<String,Object> map){
        UserContentAudit audit=new UserContentAudit();
        audit.setId(MapUtils.getInteger(map,"num"));
        RowBounds rowBounds=new RowBounds(0,100);
        List<UserContentAudit> select = userContentAuditMapper.selectByRowBounds(audit, rowBounds);

        System.out.println(JSON.toJSONString(select));

        final String traceId = TraceContext.traceId();
        System.out.println(traceId);

        return select;
    }

    @GetMapping("/updateSeq")
    public Integer update(@RequestParam("num") Integer num){
        List<Config> list = Lists.newArrayList();
        list.add(new Config(1,"1"));
        list.add(new Config(2,"3"));
        list.add(new Config(3,"5"));
        Integer integer = configMapper.updateSeq(list);
        return integer;
    }
}
