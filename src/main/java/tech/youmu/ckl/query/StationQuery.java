/**
 * Project Name:ckl
 * File Name:StationQuery.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.query;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import tech.youmu.ckl.constants.Global;

/**
 * <p>Title:StationQuery</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月24日上午11:01:22</p>
 * <p>Description:站点查询对象</p>
 */
public class StationQuery extends BaseQuery {
    
    /**
     * 服务站的类型
     */
    private Integer type;
    
    /**
     * 站点的状态
     */
    private Integer status;
    
    /**
     * 服务站录入查询开始时间
     */
    @DateTimeFormat(pattern=Global.QUERY_DATE_TIME_FORMAT)
    private Date startTime;
    
    /**
     * 服务站录入查询开始时间
     */
    @DateTimeFormat(pattern=Global.QUERY_DATE_TIME_FORMAT)
    private Date endTime;
    
    /**
     * 关键词查询
     * 根据服务站的名字来查询
     */
    private String keyword;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        if(!StringUtils.isBlank(keyword)) {
            this.keyword = keyword;
        }
    }

    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StationQuery [type=" + type + ", status=" + status + ", startTime=" + startTime + ", endTime=" + endTime + ", keyword=" + keyword + "]";
    }
}
