/**
 * Project Name:ckl
 * File Name:CarPartStockMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.CarPartStock;

/**
 * <p>Title:CarPartStockMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月14日上午10:09:59</p>
 * <p>Description:零部件即时库存</p>
 */
public interface CarPartStockMapper extends BaseMapper<CarPartStock> {

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月19日下午2:52:05;</p>
     *	<p>Description: 查询所有库存零部件的商品编码;</p>
     *  @return
     */
    List<String> findAllStockCarPartCode();


    List<CarPartStock> findByDepotIdAndCarPartId(@Param("carPartDepotId")Long carPartDepotId,@Param("list") List<Long> carPartIds);


    void batchSaveCarPartStock(@Param("list")List<CarPartStock> addCarPartStocks);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月20日下午4:54:34;</p>
     *	<p>Description: 批量更新库存数量;</p>
     *  @param updateCarPartStocks
     */
    void batchUpdateCarPartStockNum(@Param("list")List<CarPartStock> updateCarPartStocks);
}
