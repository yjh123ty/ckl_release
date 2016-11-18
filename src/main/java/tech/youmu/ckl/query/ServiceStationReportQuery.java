/**
 * Project Name:ckl
 * File Name:ServiceStationReportQuery.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.query;

import org.apache.commons.lang3.StringUtils;


/**
 * <p>Title:ServiceStationReportQuery</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月19日下午1:50:33</p>
 * <p>Description:服务站财务报表查询对象</p>
 */
public class ServiceStationReportQuery extends BaseQuery {
    
    private String startTime;
    
    private String endTime;
    
    private String keyword;
    
    private Long stationId;

    public String getStartTime() {
            return StringUtils.isNotBlank(startTime) ? startTime : null;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return StringUtils.isNotBlank(endTime) ? endTime : null;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }
}
