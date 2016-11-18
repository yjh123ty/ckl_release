/**
 * Project Name:ckl
 * File Name:IOrderServiceDetailService.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.domain.OrderServiceDetail;

/**
 * <p>Title:IOrderServiceDetailService</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月2日上午11:11:26</p>
 * <p>Description:TODO</p>
 */
public interface IOrderServiceDetailService extends IBaseService<OrderServiceDetail>{
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月2日上午11:12:04;</p>
     *	<p>Description: 根据订单id获取订单明细;</p>
     *  @return
     */
    List<OrderServiceDetail> getByOrderId(Long id);
}
