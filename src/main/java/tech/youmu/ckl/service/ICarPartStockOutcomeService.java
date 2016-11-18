/**
 * Project Name:ckl
 * File Name:ICarPartTypeService.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.domain.CarPartStockIncome;
import tech.youmu.ckl.domain.CarPartStockOutcome;

/**
 * <p>Title:ICarPartTypeService</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月20日下午2:52:04</p>
 * <p>Description:零部件入库服务层接口</p>
 */
public interface ICarPartStockOutcomeService extends IBaseService<CarPartStockOutcome>{

    void outcome(Long employeeId,Double totalAmount, Integer type, List<CarPartStockOutcome> carPartStockOutcomes);

}
