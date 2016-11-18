/**
 * Project Name:ckl
 * File Name:ICarPartStockService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.app.vo.CarPartInfo;
import tech.youmu.ckl.domain.CarPartStock;
import tech.youmu.ckl.domain.CarPartStockIncome;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;


/**
 * <p>Title:ICarPartStockService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月14日上午10:21:07</p>
 * <p>Description:零部件即时库存服务接口</p>
 */
public interface ICarPartStockService {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月14日上午10:23:24;</p>
     *	<p>Description: 分页查询即时库存;</p>
     *  @param query
     *  @return
     */
    public PageList<CarPartStock> getPageList(BaseQuery query);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月19日下午2:51:11;</p>
     *	<p>Description: 查询所有库存商品的编码;</p>
     */
    public List<String> findAllStockCarPartCode();
}
