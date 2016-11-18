/**
 * Project Name:ckl
 * File Name:PhoneMessageServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.PhoneMessage;
import tech.youmu.ckl.mapper.BaseMapper;
import tech.youmu.ckl.mapper.PhoneMessageMapper;
import tech.youmu.ckl.service.IPhoneMessageService;

/**
 * <p>Title:PhoneMessageServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月10日上午11:37:19</p>
 * <p>Description:TODO</p>
 */
@Service
public class PhoneMessageServiceImpl extends BaseServiceImpl<PhoneMessage> implements IPhoneMessageService{
    
    private PhoneMessageMapper phoneMessageMapper;

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月10日上午11:37:32;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public PhoneMessageServiceImpl(PhoneMessageMapper phoneMessageMapper) {
        super(phoneMessageMapper);
        this.phoneMessageMapper = phoneMessageMapper;
    }

}
