/**
 * Project Name:ckl
 * File Name:IServiceStationReportService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tech.youmu.ckl.domain.ServiceStationReport;
import tech.youmu.ckl.query.EmployeeQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.ServiceStationReportQuery;

/**
 * <p>Title:IServiceStationReportService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月19日上午11:47:55</p>
 * <p>Description:服务站财务报表的服务接口</p>
 */
public interface IServiceStationReportService {

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月19日下午4:59:31;</p>
     *	<p>Description: 查询服务站财务报表pageList;</p>
     *  @param query
     *  @return
     */
    PageList<ServiceStationReport> findPageList(ServiceStationReportQuery query);
    
    public void download(ServiceStationReportQuery baseQuery,HttpServletRequest request,
            HttpServletResponse response) throws Exception;

}
