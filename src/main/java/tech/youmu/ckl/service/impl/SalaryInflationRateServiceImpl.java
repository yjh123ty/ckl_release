/**
 * Project Name:ckl
 * File Name:SalaryInflationRateServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.SalaryInflationRate;
import tech.youmu.ckl.mapper.SalaryInflationRateMapper;
import tech.youmu.ckl.service.ISalaryInflationRateService;

/**
 * <p>Title:SalaryInflationRateServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月9日下午4:04:59</p>
 * <p>Description:TODO</p>
 */
@Service
public class SalaryInflationRateServiceImpl extends BaseServiceImpl<SalaryInflationRate> implements ISalaryInflationRateService{

    private SalaryInflationRateMapper salaryInflationRateMapper;
    
    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月9日下午4:05:16;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public SalaryInflationRateServiceImpl(SalaryInflationRateMapper salaryInflationRateMapper) {
        super(salaryInflationRateMapper);
        this.salaryInflationRateMapper = salaryInflationRateMapper;
    }

}
