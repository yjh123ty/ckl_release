/**
 * Project Name:ckl
 * File Name:OrderServiceDetailServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.OrderServiceDetail;
import tech.youmu.ckl.mapper.BaseMapper;
import tech.youmu.ckl.mapper.OrderServiceDetailMapper;
import tech.youmu.ckl.service.IOrderServiceDetailService;

/**
 * <p>Title:OrderServiceDetailServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月2日上午11:12:42</p>
 * <p>Description:TODO</p>
 */
@Service
public class OrderServiceDetailServiceImpl extends BaseServiceImpl<OrderServiceDetail> implements IOrderServiceDetailService{
    
    private OrderServiceDetailMapper detailMapper;

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月2日上午11:12:50;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public OrderServiceDetailServiceImpl(OrderServiceDetailMapper detailMapper) {
        super(detailMapper);
        this.detailMapper = detailMapper;
    }

    /**
     * @see tech.youmu.ckl.service.IOrderServiceDetailService#getByOrderId()
     */
    @Override
    public List<OrderServiceDetail> getByOrderId(Long id) {
        return detailMapper.getByOrderId(id);
    }

}
