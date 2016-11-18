/**
 * Project Name:ckl
 * File Name:ISettleDateService.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service;

import tech.youmu.ckl.domain.SettleDate;

/**
 * <p>Title:ISettleDateService</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月26日上午11:18:52</p>
 * <p>Description:每月工资结算日的服务接口</p>
 */
public interface ISettleDateService extends IBaseService<SettleDate>{
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月26日下午2:04:28;</p>
     *	<p>Description: 获取当前结算日;</p>
     *  @return
     */
    SettleDate getOne();
}
