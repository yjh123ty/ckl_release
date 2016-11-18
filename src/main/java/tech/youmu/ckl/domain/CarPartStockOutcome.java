/**
 * Project Name:ckl
 * File Name:CarPartInStockDetail.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

import tech.youmu.ckl.constants.Global;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title:CarPartInStockDetail</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月21日下午4:21:04</p>
 * <p>Description:零部件出库记录po</p>
 */
public class CarPartStockOutcome {
    
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
    
    private String carPartName;
    
    /**
     * 零部件对应的仓库
     */
    private CarPartDepot depot;
    
    private Long depotId;
    
    /**
     * 出库时间
     */
    private Date outcomeDate;
    
    private Employee outcomeUser;
    
    private Long employeeId;
    
    /**
     * 出库类型 1.销售出库 2.维修出库
     */
    private Integer outcomeType;
    
    /**
     * 出库价格
     */
    private Double outPrice;
    
    /**
     * 老化时间
     */
    private Date oldDate; 
    
    private Boolean isDel;
    
    private Date createTime;
    
    private Date modifyTime;

    public CarPartStockOutcome() {
    }
    public CarPartStockOutcome(Long carPartId,String carPartName, String code, Double price) {
        this.code = code;
        this.carPartId = carPartId;
        this.outPrice = price;
        this.carPartName = carPartName;
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

    public Date getOldDate() {
        return oldDate;
    }

    public void setOldDate(Date oldDate) {
        this.oldDate = oldDate;
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

    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    public Date getOutcomeDate() {
        return outcomeDate;
    }

    public void setOutcomeDate(Date outcomeDate) {
        this.outcomeDate = outcomeDate;
    }


    public Employee getOutcomeUser() {
        return outcomeUser;
    }

    public void setOutcomeUser(Employee outcomeUser) {
        this.outcomeUser = outcomeUser;
    }

    public Integer getOutcomeType() {
        return outcomeType;
    }

    public void setOutcomeType(Integer outcomeType) {
        this.outcomeType = outcomeType;
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
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public Double getOutPrice() {
        return outPrice;
    }
    public void setOutPrice(Double outPrice) {
        this.outPrice = outPrice;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    public String getCarPartName() {
        return carPartName;
    }
    public void setCarPartName(String carPartName) {
        this.carPartName = carPartName;
    }
    
    
    
    
}
