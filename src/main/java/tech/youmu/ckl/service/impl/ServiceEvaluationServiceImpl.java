/**
 * Project Name:ckl
 * File Name:ServiceEvaluationServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.ServiceEvaluation;
import tech.youmu.ckl.mapper.ServiceEvaluationMapper;
import tech.youmu.ckl.service.IServiceEvaluationService;

/**
 * <p>Title:ServiceEvaluationServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月30日上午10:33:13</p>
 * <p>Description:服务评价</p>
 */
@Service
public class ServiceEvaluationServiceImpl extends BaseServiceImpl<ServiceEvaluation> implements IServiceEvaluationService{

    private ServiceEvaluationMapper serviceEvaluationMapper;
    
    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月30日上午10:33:31;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public ServiceEvaluationServiceImpl(ServiceEvaluationMapper serviceEvaluationMapper) {
        super(serviceEvaluationMapper);
        this.serviceEvaluationMapper = serviceEvaluationMapper;
    }

}
