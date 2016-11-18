/**
 * Project Name:ckl
 * File Name:Formula.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title:Formula</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月23日下午6:07:55</p>
 * <p>Description:员工绩效公式</p>
 */
public class Formula {
    private Long id;                 //id
    private JobTitle jobTitle;       //职位
    private Integer pointCondition;  //积分条件（员工分数：如满50，满100分等）
    private BigDecimal bouns;        //对应奖金（元）
    private Boolean isDel;           //是否删除
    private Date createTime;         //创建时间
    private Date modifyTime;         //修改时间
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public JobTitle getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }
    public Integer getPointCondition() {
        return pointCondition;
    }
    public void setPointCondition(Integer pointCondition) {
        this.pointCondition = pointCondition;
    }
    public BigDecimal getBouns() {
        return bouns;
    }
    public void setBouns(BigDecimal bouns) {
        this.bouns = bouns;
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
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    @Override
    public String toString() {
        return "Formula [id=" + id + ", jobTitle=" + jobTitle + ", pointCondition=" + pointCondition + ", bouns=" + bouns + ", isDel=" + isDel + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
    }
    
    
}
