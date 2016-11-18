/**
 * Project Name:ckl
 * File Name:ServiceStationReportMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.ServiceStationReport;
import tech.youmu.ckl.query.ServiceStationReportQuery;

/**
 * <p>Title:ServiceStationReportMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月19日下午1:15:51</p>
 * <p>Description:服务站报表数据接口</p>
 */
public interface ServiceStationReportMapper extends BaseMapper<ServiceStationReport>{
    
    /**
     * 
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月19日下午2:04:58;</p>
     *	<p>Description: 查询服务站的收入报表;</p>
     *  @param query
     *  @return
     */
    public List<ServiceStationReport> findServiceStationReports(ServiceStationReportQuery query);
    
    /**
     * 
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月19日下午2:04:58;</p>
     *  <p>Description: 查询服务站的收入报表数据条数;</p>
     *  @param query
     *  @return
     */
    public long getServiceStationReportsCountByQuery(ServiceStationReportQuery query);
    
    
    
    
}
