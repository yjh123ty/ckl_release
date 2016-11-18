/**
 * Project Name:ckl
 * File Name:OrderInfoHistoryQuery.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.query;

/**
 * <p>Title:OrderInfoHistoryQuery</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月8日下午3:57:23</p>
 * <p>Description:员工服务订单的历史情况的查询对象</p>
 */
public class OrderInfoHistoryQuery extends BaseQuery {
    private Long empId;
    private String recordMonth;
    private Long stationId;
    
    public Long getStationId() {
        return stationId;
    }
    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }
    public Long getEmpId() {
        return empId;
    }
    public void setEmpId(Long empId) {
        this.empId = empId;
    }
    public String getRecordMonth() {
        return recordMonth;
    }
    public void setRecordMonth(String recordMonth) {
        this.recordMonth = recordMonth;
    }
    
    
}
