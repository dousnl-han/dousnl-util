package com.dousnl.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 私桩数据
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/3/16 15:47
 */
public class PrivatePile implements Serializable {

    private String deviceCode;//私桩编码
    private String provinceCode;//省(一级编码)
    private String provinceName;//省中文名
    private String cityCode;//市(二级编码)
    private String cityName;//市中文名
    private String areaCode;//区(三级编码)
    private String areaName;//区域中文名
    private BigDecimal privatePileLongitude;//私桩经度
    private BigDecimal privatePileLatitude;//私桩纬度

    private Integer type;

    public PrivatePile(){

    }
    public PrivatePile(String cityCode, String cityName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public BigDecimal getPrivatePileLongitude() {
        return privatePileLongitude;
    }

    public void setPrivatePileLongitude(BigDecimal privatePileLongitude) {
        this.privatePileLongitude = privatePileLongitude;
    }

    public BigDecimal getPrivatePileLatitude() {
        return privatePileLatitude;
    }

    public void setPrivatePileLatitude(BigDecimal privatePileLatitude) {
        this.privatePileLatitude = privatePileLatitude;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
