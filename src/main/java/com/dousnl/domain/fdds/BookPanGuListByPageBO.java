package com.dousnl.domain.fdds;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookPanGuListByPageBO implements Serializable {

    private static final long serialVersionUID = 6639086443635578113L;

    @Deprecated
    private Integer classifyId;

    private List<Integer> classifyIds;

    private Integer sortType;

    private Page page;

    private Integer bookReadStatus;

    private Integer userId;

    /**
     * 是否青少年模式
     */
    private boolean young;


    public static void main(String[] args) {
        BookPanGuListByPageBO bookPanGuListByPageBO = new BookPanGuListByPageBO();
        Page page = new Page();
        //page.setPageNo("1");
        page.setPageSize("10");
        bookPanGuListByPageBO.setPage(page);
        System.out.println(JSON.toJSONString(bookPanGuListByPageBO));

        Integer pageNo = Integer.valueOf(bookPanGuListByPageBO.getPage().getPageNo());
        Integer pageSize = Integer.valueOf(bookPanGuListByPageBO.getPage().getPageSize());
        List<String> cacheKey = Lists.newArrayList().stream().skip((long) ((pageNo - 1) * pageSize)).limit(pageSize).map(a -> "12" + a).collect(Collectors.toList());

        System.out.println(cacheKey);
    }
}
