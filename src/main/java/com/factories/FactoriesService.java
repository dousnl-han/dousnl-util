package com.factories;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/9/17
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/9/17       hanliangliang     新增              1001
 ********************************************************************/
@Service
public class FactoriesService {

    public String factories(Object o){
        System.out.println("fastjson factories invoker...");
        return JSON.toJSONString(o);
    }
}
