package com.dousnl.domain;

import java.io.Serializable;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/3/17 14:39
 */
public class DataPairParams<T> implements Serializable {
    private DataParams<T> supperManagerDataParams;
    private DataParams<T> tenantDataParams;

    public DataPairParams() {
    }

    public DataPairParams(DataParams<T> supperManagerDataParams, DataParams<T> tenantDataParams) {
        this.supperManagerDataParams = supperManagerDataParams;
        this.tenantDataParams = tenantDataParams;
    }

    public DataParams<T> getSupperManagerDataParams() {
        return this.supperManagerDataParams;
    }

    public void setSupperManagerDataParams(DataParams<T> supperManagerDataParams) {
        this.supperManagerDataParams = supperManagerDataParams;
    }

    public DataParams<T> getTenantDataParams() {
        return this.tenantDataParams;
    }

    public void setTenantDataParams(DataParams<T> tenantDataParams) {
        this.tenantDataParams = tenantDataParams;
    }
}
