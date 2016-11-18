/**
 * Project Name:ckl
 * File Name:SalaryPointStandardQuery.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.query;

/**
 * <p>Title:SalaryPointStandardQuery</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月9日下午3:43:43</p>
 * <p>Description:TODO</p>
 */
public class SalaryPointStandardQuery extends BaseQuery {
    private Long JobId;
    private Integer salaryLevel;

    public Long getJobId() {
        return JobId;
    }

    public void setJobId(Long jobId) {
        JobId = jobId;
    }

    public Integer getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(Integer salaryLevel) {
        this.salaryLevel = salaryLevel;
    }
    
    
}
