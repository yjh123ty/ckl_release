/**
 * Project Name:ckl
 * File Name:CarPartStockIncomeMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.CarPartStockIncome;
import tech.youmu.ckl.query.CarPartStockIncomeQuery;

/**
 * <p>Title:CarPartStockIncomeMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月12日下午4:48:31</p>
 * <p>Description:零部件入库数据访问接口</p>
 */
public interface CarPartStockIncomeMapper extends BaseMapper {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月13日下午3:09:43;</p>
     *	<p>Description: 向仓库id为depot的仓库中批量插入入库记录;</p>
     *  @param depotId
     *  @param carPartStockIncomes
     */
    public void batchSave(@Param("depotId") Long depotId, @Param("list") List<CarPartStockIncome> carPartStockIncomes);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月19日下午3:05:16;</p>
     *	<p>Description: 查询老化的商品信息;</p>
     *  @param carPartCodes 老化商品的编码
     * @param months 
     *  @return
     */
    public List<CarPartStockIncome> findOldCarPartInfos(@Param("list") Set<String> carPartCodes);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月20日下午1:23:16;</p>
     *	<p>Description: 入库保存;</p>
     *  @param employeeId
     *  @param id
     *  @param carPartStockIncomes
     */
    public void batchSaveCarPartStockIncome(@Param("employeeId")Long employeeId,@Param("incomeType")Integer incomeType, @Param("carPartDepotId")Long carPartDepotId, @Param("list")List<CarPartStockIncome> carPartStockIncomes);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月20日下午5:16:30;</p>
     *	<p>Description: 获取已经入库的商品;</p>
     *  @param carPartStockIncomes
     *  @return
     */
    public List<CarPartStockIncome> findRlreadyIncome(@Param("list")List<CarPartStockIncome> carPartStockIncomes,@Param("incomeType")Integer incomeType);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月25日上午9:23:18;</p>
     *	<p>Description: TODO;</p>
     *  @param query
     *  @return
     */
    public long getOldCarPartCountByQuery(CarPartStockIncomeQuery query);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月25日上午9:23:24;</p>
     *	<p>Description: TODO;</p>
     * @param query 
     *  @return
     */
    public List<CarPartStockIncome> getOldCarPartByQuery(CarPartStockIncomeQuery query);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月25日下午4:40:12;</p>
     *	<p>Description: TODO;</p>
     *  @param allCodes
     *  @param days
     *  @return
     */
    public Set<String> findOldCarPartCodes(@Param("list") List<String> carPartCodes, @Param("remindDate")Date remindDate);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月25日下午5:34:07;</p>
     *	<p>Description: 删除所有的老化商品信息;</p>
     */
    public void deleteCarPartOldInfo();

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月25日下午5:34:33;</p>
     *	<p>Description: 批量添加老化商品信息;</p>
     *  @param incomes
     */
    public void batchSaveCarPartOld(@Param("list") List<CarPartStockIncome> incomes);
    
    
}
