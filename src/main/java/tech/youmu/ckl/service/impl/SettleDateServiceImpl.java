/**
 * Project Name:ckl
 * File Name:SettleDateServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.SettleDate;
import tech.youmu.ckl.mapper.BaseMapper;
import tech.youmu.ckl.mapper.SettleDateMapper;
import tech.youmu.ckl.service.ISettleDateService;

/**
 * <p>Title:SettleDateServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月26日上午11:19:58</p>
 * <p>Description:每月工资结算日的服务层</p>
 */
@Service
public class SettleDateServiceImpl extends BaseServiceImpl<SettleDate> implements ISettleDateService {

    private SettleDateMapper settleDateMapper;
    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月26日上午11:20:03;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public SettleDateServiceImpl(SettleDateMapper settleDateMapper) {
        super(settleDateMapper);
        this.settleDateMapper = settleDateMapper;
    }
    
    /**
     * @see tech.youmu.ckl.service.ISettleDateService#getOne()
     */
    @Override
    public SettleDate getOne() {
        return settleDateMapper.getOne();
    }

}
