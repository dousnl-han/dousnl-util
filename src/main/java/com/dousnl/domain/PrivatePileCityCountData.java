package com.dousnl.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 私桩城市统计数据集
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/3/18 11:14
 */
public class PrivatePileCityCountData implements Serializable {

    List<PrivatePileCityCount> privatePileCityCountData= new ArrayList();

    public List<PrivatePileCityCount> getPrivatePileCityCountData() {
        return privatePileCityCountData;
    }

    public void setPrivatePileCityCountData(List<PrivatePileCityCount> privatePileCityCountData) {
        this.privatePileCityCountData = privatePileCityCountData;
    }
}
