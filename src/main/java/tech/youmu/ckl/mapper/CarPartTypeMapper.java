/**
 * Project Name:ckl
 * File Name:CarPartTypeMapper.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.mapper;

import tech.youmu.ckl.domain.CarPartType;

/**
 * <p>Title:CarPartTypeMapper</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月20日下午2:53:47</p>
 * <p>Description:TODO</p>
 */
public interface CarPartTypeMapper extends BaseMapper<CarPartType>{
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月12日下午4:14:21;</p>
     *	<p>Description: 获取name为name值得零部件分类个数  插入时做判断;</p>
     *  @param name
     *  @return
     */
    int checkCount(CarPartType carPartType);
}
