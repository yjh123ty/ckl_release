/**
 * Project Name:ckl
 * File Name:ServiceStationReport.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>Title:ServiceStationReport</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月19日上午11:49:02</p>
 * <p>Description:服务站财务报表</p>
 */
@SuppressWarnings("serial")
public class ServiceStationReport implements Serializable {
    
    /**
     * 服务站id
     */
    private Long stationId;
    
    /**
     * 站点名称
     */
    private String stationName;
    
    /**
     * 服务站地址
     */
    private String stationAddress;
    
    /**
     * 总收入
     */
    private BigDecimal allIncome;
    
    /**
     * 饭店总收入
     */
    private BigDecimal restaurantIncome;
    
    /**
     * 酒店总收入
     */
    private BigDecimal hotelIncome;
    
    /**
     * 汽车维护服务总收入
     */
    private BigDecimal carCareIncome;
    
    
    /**
     * 汽车修理服务总收入
     */
    private BigDecimal carRepairIncome;
    
    /**
     * 道路救援服务总收入
     */
    private BigDecimal roadSideIncome;
    
    
    
    /**
     * 平均评分
     */
    private Integer avgStar;

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public BigDecimal getAllIncome() {
        return allIncome;
    }

    public void setAllIncome(BigDecimal allIncome) {
        this.allIncome = allIncome;
    }

    public BigDecimal getRestaurantIncome() {
        return restaurantIncome;
    }

    public void setRestaurantIncome(BigDecimal restaurantIncome) {
        this.restaurantIncome = restaurantIncome;
    }

    public BigDecimal getHotelIncome() {
        return hotelIncome;
    }

    public void setHotelIncome(BigDecimal hotelIncome) {
        this.hotelIncome = hotelIncome;
    }

    public Integer getAvgStar() {
        return avgStar;
    }

    public void setAvgStar(Integer avgStar) {
        this.avgStar = avgStar;
    }

    public BigDecimal getCarCareIncome() {
        return carCareIncome;
    }

    public void setCarCareIncome(BigDecimal carCareIncome) {
        this.carCareIncome = carCareIncome;
    }

    public BigDecimal getCarRepairIncome() {
        return carRepairIncome;
    }

    public void setCarRepairIncome(BigDecimal carRepairIncome) {
        this.carRepairIncome = carRepairIncome;
    }

    public BigDecimal getRoadSideIncome() {
        return roadSideIncome;
    }

    public void setRoadSideIncome(BigDecimal roadSideIncome) {
        this.roadSideIncome = roadSideIncome;
    }
}
