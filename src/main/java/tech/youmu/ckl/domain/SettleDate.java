/**
 * Project Name:ckl
 * File Name:SettleDay.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.util.Date;

/**
 * <p>Title:SettleDay</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月26日上午11:06:18</p>
 * <p>Description:每月工资结算日</p>
 */
public class SettleDate {
    private Long id;
    private Employee employee;     //设置的员工
    private Boolean isFinal;      //默认不是最后一天
    private Integer settleDay;     //设置结算日期(1-31号之间）
    private Date createTime;       //创建时间
    private Boolean isDel;         //是否删除
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public Boolean getIsFinal() {
        return isFinal;
    }
    public void setIsFinal(Boolean isFinal) {
        this.isFinal = isFinal;
    }
    public Integer getSettleDay() {
        return settleDay;
    }
    public void setSettleDay(Integer settleDay) {
        this.settleDay = settleDay;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Boolean getIsDel() {
        return isDel;
    }
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
    @Override
    public String toString() {
        return "SettleDate [id=" + id + ", employee=" + employee + ", isFinal=" + isFinal + ", settleDay=" + settleDay + ", createTime=" + createTime + ", isDel=" + isDel + "]";
    }
    
    
    
    
}
