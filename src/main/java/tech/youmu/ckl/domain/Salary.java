/**
 * Project Name:ckl
 * File Name:Salary.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title:Salary</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月9日下午2:32:18</p>
 * <p>Description:员工工资表</p>
 */
public class Salary {
     private Long id;
     
     /**
      * 员工
      */
     private Employee employee;
     
     /**
      * 月基本工资
      */
     private BigDecimal baseSalary;
     
     /**
      * 薪点工资
      */
     private BigDecimal pointSalary;
     
     /**
      * 绩效工资
      */
     private BigDecimal performanceSalary;
     
     /**
      * 津贴
      */
     private BigDecimal allowance;
     
     /**
      * 奖金
      */
     private BigDecimal bouns;
     
     /**
      * 扣减工资
      */
     private BigDecimal deductSalary;
     
     /**
      * 实际应发工资
      */
     private BigDecimal realSalary;
     
     /**
      * 备注信息
      */
     private String intro;
     
     /**
      * 记录月份
      */
     private String recordMonth;
     
     /**
      * 创建时间
      */
     private Date createTime;
     
     /**
      * 修改时间
      */
     private Date modifyTime;
     
     /**
      * 是否删除
      */
     private Boolean isDel;

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

    public BigDecimal getBouns() {
        return bouns;
    }

    public void setBouns(BigDecimal bouns) {
        this.bouns = bouns;
    }

    public BigDecimal getRealSalary() {
        return realSalary;
    }

    public void setRealSalary(BigDecimal realSalary) {
        this.realSalary = realSalary;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getRecordMonth() {
        return recordMonth;
    }

    public void setRecordMonth(String recordMonth) {
        this.recordMonth = recordMonth;
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

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public BigDecimal getPointSalary() {
        return pointSalary;
    }

    public void setPointSalary(BigDecimal pointSalary) {
        this.pointSalary = pointSalary;
    }

    public BigDecimal getPerformanceSalary() {
        return performanceSalary;
    }

    public void setPerformanceSalary(BigDecimal performanceSalary) {
        this.performanceSalary = performanceSalary;
    }

    public BigDecimal getAllowance() {
        return allowance;
    }

    public void setAllowance(BigDecimal allowance) {
        this.allowance = allowance;
    }

    public BigDecimal getDeductSalary() {
        return deductSalary;
    }

    public void setDeductSalary(BigDecimal deductSalary) {
        this.deductSalary = deductSalary;
    }

    @Override
    public String toString() {
        return "Salary [id=" + id + ", employee=" + employee + ", baseSalary=" + baseSalary + ", pointSalary=" + pointSalary + ", performanceSalary=" + performanceSalary + ", allowance=" + allowance + ", bouns=" + bouns + ", deductSalary=" + deductSalary + ", realSalary=" + realSalary + ", intro=" + intro + ", recordMonth=" + recordMonth + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
    }
    
    

     
}
