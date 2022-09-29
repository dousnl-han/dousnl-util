package com.dousnl.design.service;

import com.dousnl.design.BaseOrderParam;
import com.dousnl.design.Order365SourceParam;
import com.dousnl.design.Order365SourceRet;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/12/8
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/12/8       hanliangliang     新增              1001
 ********************************************************************/
public class Order365Source extends BaseOrderSource{

    @Override
    public Order365SourceRet getOrderSource(BaseOrderParam orderParam) {
        return new Order365SourceRet();
    }


    public static void main(String[] args) {
        Order365Source source = new Order365Source();
        Order365SourceRet sourceRet = source.getOrderSource(new Order365SourceParam());
        System.out.println(sourceRet);
    }
}
