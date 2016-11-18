/**
 * Project Name:ckl
 * File Name:SalaryPointStandardServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.Department;
import tech.youmu.ckl.domain.SalaryPointStandard;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.BaseMapper;
import tech.youmu.ckl.mapper.SalaryPointStandardMapper;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.ISalaryPointStandardService;

/**
 * <p>Title:SalaryPointStandardServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月9日下午2:41:48</p>
 * <p>Description:薪点工资标准表</p>
 */
@Service
public class SalaryPointStandardServiceImpl extends BaseServiceImpl<SalaryPointStandard> implements ISalaryPointStandardService{

    private SalaryPointStandardMapper salaryPointStandardMapper;
    
    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月9日下午3:45:46;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public SalaryPointStandardServiceImpl(SalaryPointStandardMapper salaryPointStandardMapper) {
        super(salaryPointStandardMapper);
        this.salaryPointStandardMapper = salaryPointStandardMapper;
    }


}
