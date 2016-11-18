/**
 * Project Name:ckl
 * File Name:OrderQuery.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.query;

import java.math.BigDecimal;

/**
 * <p>Title:OrderQuery</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月31日下午4:09:19</p>
 * <p>Description:订单查询对象</p>
 */
public class OrderQuery extends BaseQuery {
	private String keywords;		//关键词
    private Integer status;         //订单状态(1已退款；2 退款中 ；3已取消；4 待支付；5待服务；6待完成；7待评价；8已完成 )
    private Long serviceTypeId;     //订单类型(1-酒店，2-饭店，3-汽车保养)
    private BigDecimal totalAmount; //订单金额
    private Boolean isPayNew;       //所有服务是否付款完成(是否有补差价的服务)，0没有，1完成
    private Long stationId;         // 服务站的id
    private Long userId;            //用户id
    private Long employeeId;
    
    
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Long getServiceTypeId() {
        return serviceTypeId;
    }
    public void setServiceTypeId(Long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Boolean getIsPayNew() {
        return isPayNew;
    }
    public void setIsPayNew(Boolean isPayNew) {
        this.isPayNew = isPayNew;
    }
    public Long getStationId() {
        return stationId;
    }
    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
}
