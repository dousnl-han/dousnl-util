package com.dousnl.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.*;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FragmentPanGuBO implements Serializable {

    private static final long serialVersionUID = -3952725153704486363L;
    private Integer id;

    private Integer resourceId;

    private Integer seq;

    private String title;

    private String titleImageUrl;

    private Integer type;

    private Integer bookId;

    private Integer categoryId;

    private String categoryName;

    private String summary;

    private Integer authorId;

    private String author;

    private Boolean hidden;

    private Boolean memberOnly;

    private Date publishTime;

    private Integer mediaDuration;

    private Integer trialMediaDuration;

    private Integer readCount;

    private Integer likeCount;

    private Integer favoriteCount;

    private Integer commentCount;

    private Integer readCount24h;

    private Integer status;

    private Date createTime;

    private String mediaUrls;

    private String trialMediaUrls;

    private String trialMediaFilesize;

    private String mediaFileSize;

    private Integer dataType;

    private Integer shareRelationId;
    /**
     * 片段类型
     */
    private Integer fragmentType;

    private List<FragmentBarPointBO> barPoints;

    private String contentUrl;
    private String trialContentUrl;

    public Integer getShareRelationId() {
        return shareRelationId;
    }

    public void setShareRelationId(Integer shareRelationId) {
        this.shareRelationId = shareRelationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleImageUrl() {
        return titleImageUrl;
    }

    public void setTitleImageUrl(String titleImageUrl) {
        this.titleImageUrl = titleImageUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getMemberOnly() {
        return memberOnly;
    }

    public void setMemberOnly(Boolean memberOnly) {
        this.memberOnly = memberOnly;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getMediaDuration() {
        return mediaDuration;
    }

    public void setMediaDuration(Integer mediaDuration) {
        this.mediaDuration = mediaDuration;
    }

    public Integer getReadCount() {
        return readCount == null ? 0 : readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getLikeCount() {
        return likeCount == null ? 0 : likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getFavoriteCount() {
        return favoriteCount == null ? 0 : favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Integer getCommentCount() {
        return commentCount == null ? 0 : commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getReadCount24h() {
        return readCount24h == null ? 0 : readCount24h;
    }

    public void setReadCount24h(Integer readCount24h) {
        this.readCount24h = readCount24h;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMediaUrls() {
        return mediaUrls;
    }

    public void setMediaUrls(String mediaUrls) {
        this.mediaUrls = mediaUrls;
    }

    public String getTrialMediaUrls() {
        return trialMediaUrls;
    }

    public void setTrialMediaUrls(String trialMediaUrls) {
        this.trialMediaUrls = trialMediaUrls;
    }

    public Integer getTrialMediaDuration() {
        return trialMediaDuration;
    }

    public void setTrialMediaDuration(Integer trialMediaDuration) {
        this.trialMediaDuration = trialMediaDuration;
    }

    public String getTrialMediaFilesize() {
        return trialMediaFilesize;
    }

    public void setTrialMediaFilesize(String trialMediaFilesize) {
        this.trialMediaFilesize = trialMediaFilesize;
    }

    public String getMediaFileSize() {
        return mediaFileSize;
    }

    public void setMediaFileSize(String mediaFileSize) {
        this.mediaFileSize = mediaFileSize;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public List<FragmentBarPointBO> getBarPoints() {
        return barPoints;
    }

    public void setBarPoints(List<FragmentBarPointBO> barPoints) {
        this.barPoints = barPoints;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getTrialContentUrl() {
        return trialContentUrl;
    }

    public void setTrialContentUrl(String trialContentUrl) {
        this.trialContentUrl = trialContentUrl;
    }

    public Integer getFragmentType() {
        return fragmentType;
    }

    public void setFragmentType(Integer fragmentType) {
        this.fragmentType = fragmentType;
    }
}
