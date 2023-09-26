package com.dousnl.listener;

import java.util.Observable;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2022/10/12
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2022/10/12       hanliangliang     新增              1001
 ********************************************************************/
public class ShopObservable extends Observable {

    @Override
    public void notifyObservers(Object arg) {
        System.out.println(arg);
        super.setChanged();
        super.notifyObservers(arg);
    }
}
