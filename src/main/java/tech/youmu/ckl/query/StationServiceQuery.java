/**
 * Project Name:ckl
 * File Name:StationServiceQuery.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.query;

/**
 * <p>Title:StationServiceQuery</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月25日下午1:49:52</p>
 * <p>Description:站点服务的查询对象</p>
 */
public class StationServiceQuery extends BaseQuery {
    
    /**
     * 站点的主键
     */
    private Long stationId;
    
    /**
     * 服务的类型
     */
    private Integer serviceType;

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public String toString() {
        return "StationServiceQuery [stationId=" + stationId + ", serviceType=" + serviceType + "]";
    }
}
