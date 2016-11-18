/**
 * Project Name:ckl
 * File Name:ISalaryService.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tech.youmu.ckl.domain.Salary;
import tech.youmu.ckl.query.SalaryQuery;

/**
 * <p>Title:ISalaryService</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月9日下午2:40:55</p>
 * <p>Description:员工工资表服务层接口</p>
 */
public interface ISalaryService extends IBaseService<Salary>{
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月22日下午6:02:26;</p>
     *	<p>Description: 导出工资表;</p>
     *  @param baseQuery
     *  @param request
     *  @param response
     *  @throws Exception
     */
    public void download(SalaryQuery baseQuery,HttpServletRequest request,
            HttpServletResponse response) throws Exception;

}
