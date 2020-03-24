package com.dousnl.domain;

import java.io.Serializable;

/**
 * 私桩城市统计数据
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/3/18 11:11
 */
public class PrivatePileCityCount implements Serializable {
    private String provinceCode;//省(一级编码)
    private String cityCode;//市(二级编码)
    private String cityName;//市中文名
    private Integer acNum;//交流数量
    private Integer dcNum;//直流数量

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getAcNum() {
        return acNum;
    }

    public void setAcNum(Integer acNum) {
        this.acNum = acNum;
    }

    public Integer getDcNum() {
        return dcNum;
    }

    public void setDcNum(Integer dcNum) {
        this.dcNum = dcNum;
    }
}
