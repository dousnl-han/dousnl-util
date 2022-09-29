package com.dousnl.domain.fdds;

import java.io.*;
import java.util.Date;
import java.util.List;

public class VipLogSearchBO implements Serializable {

    private static final long serialVersionUID = 8591165871739191384L;
    private String mobile;
    private String userId;
    private String realName;
    private String city;
    private List<String> changeSources;
    private String excludedSource;
    private String description;
    private List<Integer> types;
    private List<String> distributorIds;
    private Date operationDateFrom;
    private Date operationDateTo;
    private Boolean paid;
    private Boolean trial;
    private Integer timeUnit;
    private List<Integer> timeValue;
    private Long orderNumber;
    private String giftId;
    private Integer sourceEventId;
    private Boolean reverted;
    private Long sinceId;
    private Long maxId;
    private Integer offset;
    private Integer pageSize;
    private Integer orderType;
    private String orderField;

    private List<Integer> sources;
    private Integer excSource;
    private Integer isPay;
    private Integer isTrial;
    private Integer isReverted;
    private String fromChannelId;
    private String toChannelId;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getChangeSources() {
        return changeSources;
    }

    public void setChangeSources(List<String> changeSources) {
        this.changeSources = changeSources;
    }

    public String getExcludedSource() {
        return excludedSource;
    }

    public void setExcludedSource(String excludedSource) {
        this.excludedSource = excludedSource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getTypes() {
        return types;
    }

    public void setTypes(List<Integer> types) {
        this.types = types;
    }

    public List<String> getDistributorIds() {
        return distributorIds;
    }

    public void setDistributorIds(List<String> distributorIds) {
        this.distributorIds = distributorIds;
    }

    public Date getOperationDateFrom() {
        return operationDateFrom;
    }

    public void setOperationDateFrom(Date operationDateFrom) {
        this.operationDateFrom = operationDateFrom;
    }

    public Date getOperationDateTo() {
        return operationDateTo;
    }

    public void setOperationDateTo(Date operationDateTo) {
        this.operationDateTo = operationDateTo;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getTrial() {
        return trial;
    }

    public void setTrial(Boolean trial) {
        this.trial = trial;
    }

    public Integer getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(Integer timeUnit) {
        this.timeUnit = timeUnit;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getSourceEventId() {
        return sourceEventId;
    }

    public void setSourceEventId(Integer sourceEventId) {
        this.sourceEventId = sourceEventId;
    }

    public Boolean getReverted() {
        return reverted;
    }

    public void setReverted(Boolean reverted) {
        this.reverted = reverted;
    }

    public Long getSinceId() {
        return sinceId;
    }

    public void setSinceId(Long sinceId) {
        this.sinceId = sinceId;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public List<Integer> getSources() {
        return sources;
    }

    public void setSources(List<Integer> sources) {
        this.sources = sources;
    }

    public Integer getExcSource() {
        return excSource;
    }

    public void setExcSource(Integer excSource) {
        this.excSource = excSource;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Integer getIsTrial() {
        return isTrial;
    }

    public void setIsTrial(Integer isTrial) {
        this.isTrial = isTrial;
    }

    public Integer getIsReverted() {
        return isReverted;
    }

    public void setIsReverted(Integer isReverted) {
        this.isReverted = isReverted;
    }

    public List<Integer> getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(List<Integer> timeValue) {
        this.timeValue = timeValue;
    }

    public Long getMaxId() {
        return maxId;
    }

    public void setMaxId(Long maxId) {
        this.maxId = maxId;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFromChannelId() {
        return fromChannelId;
    }

    public void setFromChannelId(String fromChannelId) {
        this.fromChannelId = fromChannelId;
    }

    public String getToChannelId() {
        return toChannelId;
    }

    public void setToChannelId(String toChannelId) {
        this.toChannelId = toChannelId;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VipLogSearchBO{");
        sb.append("mobile='").append(mobile).append('\'');
        sb.append(", realName='").append(realName).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", changeSources=").append(changeSources);
        sb.append(", excludedSource='").append(excludedSource).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", types=").append(types);
        sb.append(", distributorIds=").append(distributorIds);
        sb.append(", operationDateFrom=").append(operationDateFrom);
        sb.append(", operationDateTo=").append(operationDateTo);
        sb.append(", paid=").append(paid);
        sb.append(", trial=").append(trial);
        sb.append(", timeUnit=").append(timeUnit);
        sb.append(", timeValue=").append(timeValue);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", giftId='").append(giftId).append('\'');
        sb.append(", sourceEventId=").append(sourceEventId);
        sb.append(", reverted=").append(reverted);
        sb.append(", sinceId=").append(sinceId);
        sb.append(", maxId=").append(maxId);
        sb.append(", offset=").append(offset);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", orderType=").append(orderType);
        sb.append(", orderField='").append(orderField).append('\'');
        sb.append(", sources=").append(sources);
        sb.append(", excSource=").append(excSource);
        sb.append(", isPay=").append(isPay);
        sb.append(", isTrial=").append(isTrial);
        sb.append(", isReverted=").append(isReverted);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
