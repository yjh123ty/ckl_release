/**
 * Project Name:ckl
 * File Name:ServiceRuleServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.ServiceRule;
import tech.youmu.ckl.mapper.ServiceRuleMapper;
import tech.youmu.ckl.service.IServiceRuleService;

/**
 * <p>Title:ServiceRuleServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月24日下午4:55:57</p>
 * <p>Description:服务单数产生分数规则</p>
 */
@Service
public class ServiceRuleServiceImpl extends BaseServiceImpl<ServiceRule> implements IServiceRuleService {

    private ServiceRuleMapper serviceRuleMapper;
    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月24日下午4:56:12;</p>
     *	<p>Description: 构造器;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public ServiceRuleServiceImpl(ServiceRuleMapper serviceRuleMapper) {
        super(serviceRuleMapper);
        this.serviceRuleMapper = serviceRuleMapper;
    }

}
