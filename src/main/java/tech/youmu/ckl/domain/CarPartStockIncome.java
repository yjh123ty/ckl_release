/**
 * Project Name:ckl
 * File Name:CarPartInStockDetail.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title:CarPartInStockDetail</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月21日下午4:21:04</p>
 * <p>Description:零部件入库记录po</p>
 */
public class CarPartStockIncome {
    
    /**
     * 主键
     */
    private Long id;
    
    /**
     * 零部件的唯一条形码
     */
    private String code;
    
    /**
     * 零部件信息
     */
    private CarPart carPart;
    private Long carPartId;
    
    /**
     * 零部件对应的仓库
     */
    private CarPartDepot depot;
    private Long depotId;
    
    /**
     * 入库时间
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date incomeDate;
    
    /**
     * 入库人员
     */
    private Employee incomeUser;
    private Employee employeeId;
    
    /**
     * 入库类型 1. 采购入库   2. 退货入库
     */
    private Integer incomeType;
    
    /**
     * 入库价格
     */
    private Double inPrice;
    
    private Boolean isDel;
    
    private Date createTime;
    
    private Date modifyTime;
    
    public CarPartStockIncome() {
    }
    
    public CarPartStockIncome(Long carPartId, String code, Double incomePrice) {
        this.carPartId = carPartId;
        this.code = code;
        this.inPrice = incomePrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CarPart getCarPart() {
        return carPart;
    }

    public void setCarPart(CarPart carPart) {
        this.carPart = carPart;
    }

    public CarPartDepot getDepot() {
        return depot;
    }

    public void setDepot(CarPartDepot depot) {
        this.depot = depot;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    

    public Double getInPrice() {
        return inPrice;
    }

    public void setInPrice(Double inPrice) {
        this.inPrice = inPrice;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getIncomeUser() {
        return incomeUser;
    }

    public void setIncomeUser(Employee incomeUser) {
        this.incomeUser = incomeUser;
    }

    public Integer getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(Integer incomeType) {
        this.incomeType = incomeType;
    }

    public Long getCarPartId() {
        return carPartId;
    }

    public void setCarPartId(Long carPartId) {
        this.carPartId = carPartId;
    }

    public Long getDepotId() {
        return depotId;
    }

    public void setDepotId(Long depotId) {
        this.depotId = depotId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    public Date getOldDate(){
        if(this.incomeDate != null && this.carPart != null && this.carPart.getOldMonths() != null){
            return DateUtils.addMonths(incomeDate, this.carPart.getOldMonths());
        }
        return null;
    }
    
    
}
