/**
 * Project Name:ckl
 * File Name:SalaryInflationRate.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title:SalaryInflationRate</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月9日下午3:57:46</p>
 * <p>Description:薪点值</p>
 */
public class SalaryInflationRate {
    private Long id;
    
    /**
     * 记录年份
     */
    private Integer recordYear;
    
    /**
     * 薪点值
     */
    private BigDecimal ratio;
    
    private Date createTime;   
    private Date modifyTime;   
    private Boolean isDel;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getRecordYear() {
        return recordYear;
    }
    public void setRecordYear(Integer recordYear) {
        this.recordYear = recordYear;
    }
    public BigDecimal getRatio() {
        return ratio;
    }
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
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
    @Override
    public String toString() {
        return "SalaryInflationRate [id=" + id + ", recordYear=" + recordYear + ", ratio=" + ratio + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
    }    
    
}
