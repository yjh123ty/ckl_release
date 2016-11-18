/**
 * Project Name:ckl
 * File Name:FormulaServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.Formula;
import tech.youmu.ckl.mapper.FormulaMapper;
import tech.youmu.ckl.service.IFormulaService;

/**
 * <p>Title:FormulaServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月23日下午6:15:31</p>
 * <p>Description:TODO</p>
 */
@Service
public class FormulaServiceImpl extends BaseServiceImpl<Formula> implements IFormulaService{
    
    private FormulaMapper formulaMapper;

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月23日下午6:15:45;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public FormulaServiceImpl(FormulaMapper formulaMapper) {
        super(formulaMapper);
        this.formulaMapper = formulaMapper;
    }

}
