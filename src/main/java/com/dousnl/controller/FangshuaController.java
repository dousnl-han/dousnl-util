package com.dousnl.controller;

import com.alibaba.fastjson.JSON;
import com.dousnl.annotation.AccessLimit;
import com.dousnl.utils.JsonMsgBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/6/2
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/6/2       hanliangliang     新增              1001
 ********************************************************************/
@RestController
public class FangshuaController extends BaseController{

    @AccessLimit(seconds=60, maxCount=5, nedLogin=true)
    @PostMapping("/fangshua")
    public JsonMsgBean fangshua(){


        return JsonMsgBean.success(JSON.toJSONString(getUser()));
    }
}
