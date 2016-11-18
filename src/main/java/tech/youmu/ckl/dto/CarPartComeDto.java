/**
 * Project Name:ckl
 * File Name:CarPartInStockDetail.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.dto;

import java.util.List;

/**
 * 
 * <p>Title:CarPartIncomeDto</p>
 * @author xiongchuan
 * @version v1.0
 * <p>Date:2016年10月20日上午11:15:41</p>
 * <p>Description:TODO</p>
 */
public class CarPartComeDto {
    private Long employeeId;
    
    private Long orderId;
    
    private Integer type;
    
    private Double totalAmount;
    
    private List<CarPartDto> carPartDtos;
    
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public List<CarPartDto> getCarPartDtos() {
        return carPartDtos;
    }
    public void setCarPartDtos(List<CarPartDto> carPartDtos) {
        this.carPartDtos = carPartDtos;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    
    
}
