package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.entity.MyConfig;
import com.dousnl.domain.entity.UserContentAudit;
import com.dousnl.mapper.MyConfigMapper;
import com.dousnl.mapper.SourceEventVersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/4/28
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/4/28       hanliangliang     新增              1001
 ********************************************************************/
@Controller
@RequestMapping("/config")
public class ConfigController extends BaseController{

    @Autowired
    private MyConfigMapper myConfigMapper;

    @GetMapping("/update")
    public String list(){
        MyConfig myConfig=new MyConfig();
        myConfig.setOperatorName("111");
        myConfig.setShelfStatus(2);
        myConfig.setSeq(myConfigMapper.selectMaxSeq("111",2));
        myConfig.setId(1);
        myConfigMapper.updateStatus(myConfig);
        return "success";
    }

    @GetMapping("/updateSky")
    public String updateSky(){
        MyConfig myConfig=new MyConfig();
        myConfig.setShelfStatus(2);
        myConfig.setSeq(myConfigMapper.selectMaxSeq("111",2));
        myConfig.setId(1);
        myConfig.setOperatorName("");
        myConfigMapper.updateByPrimaryKeySelective(myConfig);
        return "success";
    }

    @GetMapping("/select")
    public String select(){
        return JSON.toJSONString(getUser());
    }
}
