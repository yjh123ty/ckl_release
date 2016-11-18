/**
 * Project Name:ckl
 * File Name:SalaryBase.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title:SalaryBase</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月30日下午4:52:29</p>
 * <p>Description:基础工资标准表</p>
 */
public class SalaryBase {
    private Long id;
    
    /**
     * 订单数量（月洗车量,满足条件）
     */
    private Integer orderNum;
    
    /**
     * 日工资
     */
    private BigDecimal salary;
    
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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
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
        return "SalaryBase [id=" + id + ", orderNum=" + orderNum + ", salary=" + salary + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
    }
    
    
    
}
