/**
 * Project Name:ckl
 * File Name:IServiceTypeService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.math.BigDecimal;
import java.util.List;

import tech.youmu.ckl.domain.ServiceType;

/**
 * <p>Title:IServiceTypeService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月25日上午9:13:53</p>
 * <p>Description:服务类型服务</p>
 */
public interface IServiceTypeService {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月25日上午9:23:16;</p>
     *	<p>Description: 获取所有的服务类型;</p>
     *  @return
     */
    public List<ServiceType> getAll();
    
    
    public void updateRoadSidePrice(BigDecimal startPrice, BigDecimal milPrice);


    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月11日上午11:26:57;</p>
     *	<p>Description: 获取道路救援价格信息;</p>
     *  @param i
     *  @return
     */
    public ServiceType getRoadSideInfo();

}
