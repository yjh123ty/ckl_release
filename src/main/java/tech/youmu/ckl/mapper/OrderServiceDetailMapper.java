/**
 * Project Name:ckl
 * File Name:OrderServiceDetailMapper.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.OrderServiceDetail;

/**
 * <p>Title:OrderServiceDetailMapper</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月2日上午10:52:58</p>
 * <p>Description:TODO</p>
 */
public interface OrderServiceDetailMapper extends BaseMapper<OrderServiceDetail>{
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月2日上午11:10:04;</p>
     *	<p>Description: 根据订单id查询订单明细;</p>
     */
    List<OrderServiceDetail> getByOrderId(Long id);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月14日下午6:02:27;</p>
     *	<p>Description: 批量添加;</p>
     *  @param orderServiceDetails
     *  @param id
     *  @param orderNumber
     */
    void batchSaveOrderServiceDetail(@Param("list")List<OrderServiceDetail> orderServiceDetails, @Param("orderId")Long orderId, @Param("orderNumber")String orderNumber);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月19日上午10:15:53;</p>
     *	<p>Description: TODO;</p>
     *  @param deleteOrderServiceDetails
     */
    void batchUpdateOrderServiceDetail(@Param("list")List<OrderServiceDetail> deleteOrderServiceDetails);
}
