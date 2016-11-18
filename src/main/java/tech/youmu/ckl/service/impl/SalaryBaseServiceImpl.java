/**
 * Project Name:ckl
 * File Name:SalaryBaseServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.SalaryBase;
import tech.youmu.ckl.mapper.SalaryBaseMapper;
import tech.youmu.ckl.service.ISalaryBaseService;

/**
 * <p>Title:SalaryBaseServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月30日下午5:23:10</p>
 * <p>Description:TODO</p>
 */
@Service
public class SalaryBaseServiceImpl extends BaseServiceImpl<SalaryBase> implements ISalaryBaseService{
    
    private SalaryBaseMapper salaryBaseMapper;

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月30日下午5:23:31;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public SalaryBaseServiceImpl(SalaryBaseMapper salaryBaseMapper) {
        super(salaryBaseMapper);
        this.salaryBaseMapper = salaryBaseMapper;
    }

}
