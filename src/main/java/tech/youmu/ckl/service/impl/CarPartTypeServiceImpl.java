/**
 * Project Name:ckl
 * File Name:CarPartTypeServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.CarPartType;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.CarPartTypeMapper;
import tech.youmu.ckl.service.ICarPartTypeService;

/**
 * <p>Title:CarPartTypeServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月20日下午2:52:59</p>
 * <p>Description:TODO</p>
 */
@Service
public class CarPartTypeServiceImpl extends BaseServiceImpl<CarPartType> implements ICarPartTypeService{
    
    private CarPartTypeMapper carPartTypeMapper;

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月20日下午2:53:03;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public CarPartTypeServiceImpl(CarPartTypeMapper carPartTypeMapper) {
        super(carPartTypeMapper);
        this.carPartTypeMapper = carPartTypeMapper;
    }
    
    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.impl.BaseServiceImpl#save(java.lang.Object)
     */
    @Override
    public boolean save(CarPartType t) {
        if(carPartTypeMapper.checkCount(t) != 0){
            throw new BizExecption("分类已经存在");
        }
        return super.save(t);
    }
    
    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.impl.BaseServiceImpl#updateById(java.lang.Object)
     */
    @Override
    public boolean updateById(CarPartType t) {
        if(carPartTypeMapper.checkCount(t) != 0){
            throw new BizExecption("分类已经存在");
        }
        return super.updateById(t);
    }
}
