/**
 * Project Name:ckl
 * File Name:serviceRule.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title:serviceRule</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月24日下午4:21:24</p>
 * <p>Description:服务单数产生分数的规则</p>
 */
public class ServiceRule {
    private Long id;            
    private Integer base;          //每天完成服务订单的基数(表示完成一单获得的分数)
    private Integer condition;     //设置每天完成单数(用作评分条件)
    private BigDecimal persent;    //奖励比例
    private Date createTime;       //创建时间
    private Date modifyTime;       //修改时间
    private Boolean isDel;         //是否删除
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getBase() {
        return base;
    }
    public void setBase(Integer base) {
        this.base = base;
    }
    public Integer getCondition() {
        return condition;
    }
    public void setCondition(Integer condition) {
        this.condition = condition;
    }
    public BigDecimal getPersent() {
        return persent;
    }
    public void setPersent(BigDecimal persent) {
        this.persent = persent;
    }
    public Boolean getIsDel() {
        return isDel;
    }
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    public boolean isDel() {
        return isDel;
    }
    public void setDel(boolean isDel) {
        this.isDel = isDel;
    }
    @Override
    public String toString() {
        return "ServiceRule [id=" + id + ", base=" + base + ", condition=" + condition + ", persent=" + persent + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
    }
    
    
}
