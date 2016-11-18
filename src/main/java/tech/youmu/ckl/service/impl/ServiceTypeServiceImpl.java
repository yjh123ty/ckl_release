/**
 * Project Name:ckl
 * File Name:ServiceTypeServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.ServiceType;
import tech.youmu.ckl.mapper.ServiceTypeMapper;
import tech.youmu.ckl.service.IServiceTypeService;

/**
 * <p>Title:ServiceTypeServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月25日上午9:22:51</p>
 * <p>Description:站点提供的服务类型服务</p>
 */
@Service
public class ServiceTypeServiceImpl implements IServiceTypeService {
    
    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IServiceTypeService#getAll()
     */
    @Override
    public List<ServiceType> getAll() {
        return serviceTypeMapper.getAll();
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IServiceTypeService#updateRoadSidePrice(java.math.BigDecimal, java.math.BigDecimal)
     */
    @Override
    public void updateRoadSidePrice(BigDecimal startPrice, BigDecimal milPrice) {
        serviceTypeMapper.updateRoadSidePrice(startPrice, milPrice);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IServiceTypeService#getRoadSideInfo()
     */
    @Override
    public ServiceType getRoadSideInfo() {
        return serviceTypeMapper.getRoadSideInfo();
    }
    
}
