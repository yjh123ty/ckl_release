/**
 * Project Name:ckl
 * File Name:TravelQuery.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.query;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import tech.youmu.ckl.constants.Global;

/**
 * <p>Title:TravelQuery</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月5日下午3:02:45</p>
 * <p>Description:游记的查询对象</p>
 */
public class TravelNoteQuery extends BaseQuery {
    
    @DateTimeFormat(pattern=Global.QUERY_DATE_TIME_FORMAT)
    private Date startTime;
    
    @DateTimeFormat(pattern=Global.QUERY_DATE_TIME_FORMAT)
    private Date endTime;
    
    private String keyword;
    
    private Long userId;
    

    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
        if(StringUtils.isNotBlank(keyword)) {
            this.keyword = keyword;
        }
    }
}
