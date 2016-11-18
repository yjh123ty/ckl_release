/**
 * Project Name:ckl
 * File Name:SettleDateMapper.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.mapper;

import tech.youmu.ckl.domain.SettleDate;

/**
 * <p>Title:SettleDateMapper</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月26日上午11:15:26</p>
 * <p>Description:设置每月工资结算日</p>
 */
public interface SettleDateMapper extends BaseMapper<SettleDate>{
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月26日下午2:03:41;</p>
     *	<p>Description: 获取当前结算日;</p>
     *  @return
     */
    SettleDate getOne();
}
