/**
 * Project Name:ckl
 * File Name:ICarPartTypeService.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import tech.youmu.ckl.domain.CarPartStockIncome;
import tech.youmu.ckl.query.CarPartStockIncomeQuery;
import tech.youmu.ckl.query.PageList;

/**
 * <p>Title:ICarPartTypeService</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月20日下午2:52:04</p>
 * <p>Description:零部件入库服务层接口</p>
 */
public interface ICarPartStockIncomeService extends IBaseService<CarPartStockIncome>{

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月19日下午3:04:13;</p>
     *	<p>Description: 查询几个月后老化的商品信息;</p>
     *  @param carPartCodes
     *  @return
     */
    List<CarPartStockIncome> findOldCarPartInfos(Set<String> carPartCodes);

    void income(Long employeeId, Integer incomeType, List<CarPartStockIncome> carPartStockIncomes);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月25日上午9:18:40;</p>
     *	<p>Description: 查询老化商品;</p>
     *  @param query
     *  @return
     */
    PageList<CarPartStockIncome> getOldCarPartPageList(CarPartStockIncomeQuery query);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月25日下午4:37:12;</p>
     *	<p>Description: 查找老化的商品条形码列表;</p>
     *  @param allCodes
     *  @param addDays
     *  @return
     */
    Set<String> findOldCarPartCodes(List<String> allCodes, Date addDays);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月25日下午5:28:26;</p>
     *	<p>Description: 删除所有老化商品信息;</p>
     */
    void deleteCarPartOldInfo();

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月25日下午5:29:32;</p>
     *	<p>Description: 添加老化商品信息;</p>
     *  @param carPartStockIncome
     */
    void batchSaveCarPartOld(List<CarPartStockIncome> carPartStockIncomes);

}
