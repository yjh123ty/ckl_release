/**
 * Project Name:ckl
 * File Name:StationOrderCount.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * <p>Title:StationOrderCount</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月12日下午3:20:02</p>
 * <p>Description:每月各服务站完成订单总量</p>
 */
public class HistoryStationCountOrderInfo {
    private Long id;
    
    /**
     * 服务站id
     */
    private Long stationId;
    
    /**
     * 当月完成汽车类订单总数
     */
    private Integer orderNum;
    
    /**
     * 当月该服务站完成的订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 记录月份
     */
    private String recordMonth;
    
    private Date createTime;
    
    private Date modifyTime;
    
    private Boolean isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getRecordMonth() {
        return recordMonth;
    }

    public void setRecordMonth(String recordMonth) {
        this.recordMonth = recordMonth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "HistoryStationCountOrderInfo [id=" + id + ", stationId=" + stationId + ", orderNum=" + orderNum + ", totalAmount=" + totalAmount + ", recordMonth=" + recordMonth + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
    }
    
    
    
}
