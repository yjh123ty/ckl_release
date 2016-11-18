/**
 * Project Name:ckl
 * File Name:StarsRuleServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.StarsRule;
import tech.youmu.ckl.mapper.StarsRuleMapper;
import tech.youmu.ckl.service.IStarsRuleService;

/**
 * <p>Title:StarsRuleServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月25日下午1:23:00</p>
 * <p>Description:获得评分产生规则的服务实现类</p>
 */
@Service
public class StarsRuleServiceImpl extends BaseServiceImpl<StarsRule> implements IStarsRuleService{
    
    private StarsRuleMapper starsRuleMapper;

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月25日下午1:23:06;</p>
     *	<p>Description: 构造器;</p>
     *  @param TODO 
     */
    @Autowired
    public StarsRuleServiceImpl(StarsRuleMapper starsRuleMapper) {
        super(starsRuleMapper);
        this.starsRuleMapper = starsRuleMapper;
    }

}
