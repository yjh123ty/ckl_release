/**
 * Project Name:ckl
 * File Name:CarPartStockIncomeQuery.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.query;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import tech.youmu.ckl.utils.ConfigUtil;

/**
 * <p>Title:CarPartStockIncomeQuery</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月13日下午3:02:49</p>
 * <p>Description:入库记录查询对象</p>
 */
public class CarPartStockIncomeQuery  extends BaseQuery {
    
    private Boolean centre;
    
    private Long stationId;
    
    private Date startDate;
    
    private Date endDate;
    
    private Long keeperId;
    
    public Boolean getCentre() {
        return centre;
    }

    public void setCentre(Boolean centre) {
        this.centre = centre;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getKeeperId() {
        return keeperId;
    }

    public void setKeeperId(Long keeperId) {
        this.keeperId = keeperId;
    }

    public Date getRemindDate() {
        return DateUtils.addDays(new Date(), -ConfigUtil.getRemindOldDays());
    }
}
