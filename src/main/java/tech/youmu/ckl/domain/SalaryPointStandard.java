/**
 * Project Name:ckl
 * File Name:PointSalary.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Title:PointSalary
 * </p>
 * @author yjh
 * @version v1.0
 *          <p>
 *          Date:2016年10月8日下午4:50:05
 *          </p>
 *          <p>
 *          Description:薪点工资标准
 *                      当计算某位员工的薪点工资时，首先获取他的岗位类别（job_id），之后确定他的salary_level = x，再遍历相加for(int x=salary_level;x>0;x--)
 *          </p>
 */
public class SalaryPointStandard {
    private Long id;

    /**
     * 岗位类别
     */
    private JobTitle jobTitle;

    /**
     * 薪点级别（1-9）
     */
    private Integer salaryLevel;

    /**
     * 薪点工资
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

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(Integer salaryLevel) {
        this.salaryLevel = salaryLevel;
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
        return "PointSalaryStandard [id=" + id + ", jobTitle=" + jobTitle + ", salaryLevel=" + salaryLevel + ", salary=" + salary + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
    }

}
