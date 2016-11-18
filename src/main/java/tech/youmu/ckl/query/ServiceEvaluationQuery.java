/**
 * Project Name:ckl
 * File Name:ServiceEvaluationQuery.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.query;

/**
 * <p>Title:ServiceEvaluationQuery</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月30日上午10:27:52</p>
 * <p>Description:服务评价查询对象</p>
 */
public class ServiceEvaluationQuery extends BaseQuery {
    private Integer star;       //评价星级
    private Long serviceTypeId; //服务类型id
    private String keywords;    //关键词
    private Long stationId;     //服务站id
    private Long userId;
    private Long employeeId;
    
    
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getStationId() {
        return stationId;
    }
    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }
    public Integer getStar() {
        return star;
    }
    public void setStar(Integer star) {
        this.star = star;
    }
    public Long getServiceTypeId() {
        return serviceTypeId;
    }
    public void setServiceTypeId(Long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    @Override
    public String toString() {
        return "ServiceEvaluationQuery [star=" + star + ", serviceTypeId=" + serviceTypeId + ", keywords=" + keywords + "]";
    }
    
}
