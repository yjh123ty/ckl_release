/**
 * Project Name:ckl
 * File Name:CarPartStock.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title:CarPartStock</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月21日下午2:18:09</p>
 * <p>Description:商品库存po</p>
 */
@SuppressWarnings("serial")
public class CarPartStock implements Serializable {
    
    private Long id;
    
    /**
     * 商品库存量
     */
    private Integer num;

    /**
     * 库存商品
     */
    private CarPart carPart;
    private Long carPartId;
    
    /**
     * 库存仓库
     */
    private CarPartDepot depot;
    private Long depotId;
    
    /**
     * 删除标记
     */
    private Boolean isDel;
    
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    public CarPartStock() {
    }
    
    public CarPartStock(Long carPartId, Long depotId, Integer num) {
        this.carPartId =carPartId;
        this.depotId = depotId;
        this.num = num;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public CarPart getCarPart() {
        return carPart;
    }

    public void setCarPart(CarPart carPart) {
        this.carPart = carPart;
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

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public CarPartDepot getDepot() {
        return depot;
    }

    public void setDepot(CarPartDepot depot) {
        this.depot = depot;
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
    
    
}
