/**
 * Project Name:ckl
 * File Name:SalaryQuery.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.query;

/**
 * <p>Title:SalaryQuery</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月9日下午2:39:44</p>
 * <p>Description:工资表查询对象</p>
 */
public class SalaryQuery extends BaseQuery {
    private String keywords;    //关键词  (员工账号，姓名，职位) 
    private String searchTime;  //查询时间
    private Long employeeId;
    
    
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public String getSearchTime() {
        return searchTime;
    }
    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }
    
}
