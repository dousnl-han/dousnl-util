package com.dousnl.domain.fdds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.*;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BookPanGuBaseBO implements Serializable {

    private static final long serialVersionUID = 2832265310172695526L;
    private Integer id;

    private Integer resourceId;

    private String shareTitle;

    private String shareSubTitle;

    private String publishingHouse;

    private BookClassifyPanGuBO classify;

    private List<BookClassifyPanGuBO> classifies;

    private List<BookPanGuTagBO> tags;

    private String name;

    private String author;

    private String isbn;

    private String summary;

    private String readingCrowd;

    private Integer buyWay;

    private String buyUrl;

    private String coverImageUrl;

    private Boolean memberOnly;

    private Boolean publishStatus;

    private Date publishTime;

    private String intro;

    /**
     * 显示开始时间
     */
    private Date shareTitleStartTime;

    /**
     * 显示结束时间
     */
    private Date shareTitleEndTime;

    private Integer seq;

    private long readCount;

    private long likeCount;

    private long playCompletedNum;

    private Date createTime;

    public Boolean acquireDisabled() {
        return !publishStatus;
    }
}
