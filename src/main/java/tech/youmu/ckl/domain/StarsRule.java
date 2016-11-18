/**
 * Project Name:ckl
 * File Name:StarsRule.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title:StarsRule</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月25日上午11:30:47</p>
 * <p>Description:服务星数评定规则</p>
 */
public class StarsRule {
    private Long id;    
    private Double star;           //获得星数
    private BigDecimal starRatio;  //根据得星 对应获得的奖励比例
    private Date createTime;       //创建时间
    private Date modifyTime;       //修改时间
    private Boolean isDel ;        //逻辑删除
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getStar() {
        return star;
    }
    public void setStar(Double star) {
        this.star = star;
    }
    public BigDecimal getStarRatio() {
        return starRatio;
    }
    public void setStarRatio(BigDecimal starRatio) {
        this.starRatio = starRatio;
    }
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
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
        return "StarsRule [id=" + id + ", star=" + star + ", starRatio=" + starRatio + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
    }
    
    
}
