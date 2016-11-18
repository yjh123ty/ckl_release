/**
 * Project Name:ckl
 * File Name:HistoryStationCountOrderInfoQuery.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.query;

/**
 * <p>Title:HistoryStationCountOrderInfoQuery</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月12日下午4:09:47</p>
 * <p>Description:TODO</p>
 */
public class HistoryStationCountOrderInfoQuery extends BaseQuery{
    private Long stationId;
    
    private String recordMonth;

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getRecordMonth() {
        return recordMonth;
    }

    public void setRecordMonth(String recordMonth) {
        this.recordMonth = recordMonth;
    }
    
    
}
