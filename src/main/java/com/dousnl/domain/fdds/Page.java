package com.dousnl.domain.fdds;

import java.io.Serializable;

import jodd.util.StringUtil;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * Description: 分页信息
 * Company    : 上海黄豆网络科技有限公司
 * Author     : lixm
 * Date       : 2017年11月21日 下午6:30:28
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0      2017年11月21日             lixm         新增              1001
 *******************************************************************
 */
public class Page implements Serializable {

    /**
     * 总页数
     */
    private String pageCount="0";
    /**
     * 当前页码
     */
    private String pageNo;
    /**
     * 每页记录数
     */
    private String pageSize="10";
    /**
     * 总记录数
     */
    private String totalCount="0";

    public Page() {

    }
    /**
     *
     * Description: 构建分页对象，计算总页数
     * Tags       : @param totalCount
     */
    public Page(Integer totalCount) {
        if (totalCount != null && totalCount > 1) {
            this.totalCount = totalCount.toString();
            Integer num = (totalCount - 1) / Integer.valueOf(pageSize) + 1;
            this.pageCount = num.toString();
        }
    }

    public Page(Integer totalCount,boolean extra) {
        if (totalCount != null && totalCount >= 1) {
            this.totalCount = totalCount.toString();
            Integer num = (totalCount - 1) / Integer.valueOf(pageSize) + 1;
            this.pageCount = num.toString();
        }
    }

    /**
     *
     * Description: 构建分页对象，计算总页数
     * Tags       : @param totalCount
     * Tags       : @param pageNo
     * Tags       : @param pageSize
     */
    public Page(Integer totalCount, Integer pageNo, Integer pageSize) {
        setPageNo(pageNo);
        setPageSize(pageSize);
        if (totalCount != null && totalCount > 1) {
            this.totalCount = totalCount.toString();
            Integer num = (totalCount - 1) / Integer.valueOf(this.pageSize) + 1;
            this.pageCount = num.toString();
        }
    }

    public Page(Integer totalCount, Integer pageNo, Integer pageSize,boolean extra) {
        setPageNo(pageNo);
        setPageSize(pageSize);
        if (totalCount != null && totalCount >= 1) {
            this.totalCount = totalCount.toString();
            Integer num = (totalCount - 1) / Integer.valueOf(this.pageSize) + 1;
            this.pageCount = num.toString();
        }
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getPageNo() {
        return pageNo;
    }

    //防止非法数据
    public void setPageNo(String pageNo) {
        if(!NumberUtils.isNumber(pageNo)) {
            pageNo="1";
        }
        setPageNo(Integer.valueOf(pageNo)) ;
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo!=null&&pageNo>0) {
            this.pageNo = pageNo.toString();
        }else {
            this.pageNo = "1";
        }
    }
    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        if(!org.apache.commons.lang3.math.NumberUtils.isNumber(pageSize)) {
            pageSize="10";
        }
        setPageSize(Integer.valueOf(pageSize)) ;
    }
    public void setPageSize(Integer pageSize) {
        if(pageSize!=null&&pageSize>0&&pageSize<=1000) {
            this.pageSize = pageSize.toString();
        }else {
            this.pageSize = "10";
        }

    }
    public String getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
    public void setTotalCount(Long totalCount) {
        if(totalCount!=null) {
            this.totalCount = totalCount.toString();
        }

    }
    public void setPageCount(Integer pageCount) {
        if(pageCount!=null) {
            this.pageCount = pageCount.toString();
        }

    }
    /**
     *
     * Title      : Page
     * Description: 设置分页信息
     * Param      :  totalCount
     * Param      :  pageCount
     * Param      :  pageNo
     * Param      :  pageSize 参数
     * Return     : void 返回类型
     * Throws     : 抛出的异常，有多个用逗号分隔
     */
    public void setPage(Long totalCount, Integer pageCount,Integer pageNo, Integer pageSize) {
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
        this.setPageCount(pageCount);
        this.setTotalCount(totalCount);
    }

    public void setPage(Long totalCount, Integer pageNo, Integer pageSize) {
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
        Long num = (totalCount - 1) / pageSize + 1;
        this.setPageCount(num.toString());
        this.setTotalCount(totalCount);
    }


    public int countOffset(){
        int pageSize = countPageSize();
        return countOffset(pageSize);
    }

    public int countPageSize(){
        int pageSize = Integer.parseInt(this.pageSize);
        if(pageSize<=0){
            pageSize = 10;
        }
        return pageSize;
    }

    public int countPageNo(){
        int pageNo = StringUtil.isBlank(this.pageNo)?1:Integer.parseInt(this.pageNo);
        if(pageNo<=0){
            pageNo = 1;
        }
        return pageNo;
    }

    public int countOffset(int pageSize){
        int pageNo = countPageNo();
        return (pageNo-1)*pageSize;
    }

}
