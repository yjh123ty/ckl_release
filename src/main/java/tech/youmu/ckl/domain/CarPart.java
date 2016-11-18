/**
 * Project Name:ckl
 * File Name:Product.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import tech.youmu.ckl.constants.Global;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * Title:CarPart
 * </p>
 * @author zh
 * @version v1.0
 *          <p>
 *          Date:2016年9月6日下午2:23:49
 *          </p>
 *          <p>
 *          Description:车刻丽零部件商品
 *          </p>
 */
@SuppressWarnings("serial")
public class CarPart implements Serializable {
    
    private Long id;
    
    /**
     * 商品的唯一编号
     */
    private String sn;

    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 商品图片
     */
    private String pic;

    /**
     * 售价
     */
    private Double salePrice;

    /**
     * 备注
     */
    private String remark;

    /**
     * 商品类型
     */
    private CarPartType type;

    /**
     * 商品计量单位
     */
    private SysDicDetail unit;
    
    /**
     * 商品的型号
     */
    private String model;

    /**
     * 是否删除
     */
    private Boolean isDel;
    
    /**
     * 商品老化的月份数
     */
    private Integer oldMonths;
    
    /**
     * 中央仓库出库价格
     */
    private BigDecimal centreOutPrice;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date modifyTime;

    public CarPart() {
        super();
    }
    
    public CarPart(String sn, String name, String pic, Double salePrice, String remark, CarPartType type, SysDicDetail unit, String model) {
        this.sn = sn;
        this.name = name;
        this.pic = pic;
        this.salePrice = salePrice;
        this.remark = remark;
        this.type = type;
        this.unit = unit;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarPartType getType() {
        return type;
    }

    public void setType(CarPartType type) {
        this.type = type;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    @JsonFormat(pattern = Global.DATE_TIME_FORMAT)
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
    
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    
    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public SysDicDetail getUnit() {
        return unit;
    }

    public void setUnit(SysDicDetail unit) {
        this.unit = unit;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getOldMonths() {
        return oldMonths;
    }

    public void setOldMonths(Integer oldMonths) {
        this.oldMonths = oldMonths;
    }

    public BigDecimal getCentreOutPrice() {
        return centreOutPrice;
    }

    public void setCentreOutPrice(BigDecimal centreOutPrice) {
        this.centreOutPrice = centreOutPrice;
    }
}
