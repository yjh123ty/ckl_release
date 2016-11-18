/**
 * Project Name:ckl
 * File Name:FormulaQuery.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.query;

/**
 * <p>Title:FormulaQuery</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月23日下午6:12:16</p>
 * <p>Description:TODO</p>
 */
public class FormulaQuery extends BaseQuery {
    private String keywords;    //关键词  (职位名) 
    private String beginTime;   //起始时间
    private String endTime;     //截止时间
    private Long deptId;        //部门
    private Long jobId;         //职位
    private Integer pointCondition; //分数条件
    
    
    public Integer getPointCondition() {
        return pointCondition;
    }
    public void setPointCondition(Integer pointCondition) {
        this.pointCondition = pointCondition;
    }
    public Long getJobId() {
        return jobId;
    }
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
    
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public Long getDeptId() {
        return deptId;
    }
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
    public String getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    
    
}
