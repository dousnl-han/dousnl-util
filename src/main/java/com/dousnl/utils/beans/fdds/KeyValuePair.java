package com.dousnl.utils.beans.fdds;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/27 15:23
 */
public class KeyValuePair {
    private static final long serialVersionUID = -7188986497524557521L;
    protected String key;
    protected String value;

    public KeyValuePair() {
    }

    public KeyValuePair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static KeyValuePair define(String key, String value) {
        return new KeyValuePair(key, value);
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + this.key + ": " + this.value + "}";
    }
}
