package com.dousnl.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.*;

@Getter
@Setter
public class FragmentBarPointBO implements Serializable {

    /**
     * 时间点 单位：秒
     */
    private Integer time;
    /**
     * 描述
     */
    private String description;
}
