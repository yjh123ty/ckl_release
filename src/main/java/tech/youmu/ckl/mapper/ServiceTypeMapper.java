/**
 * Project Name:ckl
 * File Name:ServiceTypeMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.ServiceType;

/**
 * <p>Title:ServiceTypeMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月25日上午9:14:18</p>
 * <p>Description:服务类型的数据库操作</p>
 */
public interface ServiceTypeMapper {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月25日上午9:15:17;</p>
     *	<p>Description: 获取全部的服务类型;</p>
     *  @return
     */
    public List<ServiceType> getAll();
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月11日上午10:27:42;</p>
     *	<p>Description: 修改道路救援价格信息;</p>
     *  @param startPrice
     *  @param milPrice
     */
    public void updateRoadSidePrice(@Param("startPrice") BigDecimal startPrice, @Param("milPrice") BigDecimal milPrice);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月11日上午11:28:07;</p>
     *	<p>Description: 获取道路救援信息;</p>
     *  @return
     */
    public ServiceType getRoadSideInfo();
}
