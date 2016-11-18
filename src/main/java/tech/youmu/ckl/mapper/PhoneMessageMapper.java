/**
 * Project Name:ckl
 * File Name:PhoneMessageMapper.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.PhoneMessage;

/**
 * 
 * <p>Title:PhoneMessageMapper</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年10月18日下午4:35:25</p>
 * <p>Description:TODO</p>
 */
public interface PhoneMessageMapper extends BaseMapper<PhoneMessage>{

    List<String> findPhone();

    
}
