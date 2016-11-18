/**
 * Project Name:ckl
 * File Name:CarPartStockOutcomeMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.CarPartStockOutcome;

/**
 * <p>Title:CarPartStockOutcomeMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月14日上午11:07:42</p>
 * <p>Description:TODO</p>
 */
public interface CarPartStockOutcomeMapper extends BaseMapper<CarPartStockOutcome> {

    List<CarPartStockOutcome> findRlreadyOutcome(@Param("list")List<CarPartStockOutcome> carPartStockOutcomes, @Param("outcomeType")Integer outcomeType);

    void batchSaveCarPartStockOutcome(@Param("employeeId")Long employeeId,@Param("outcomeType")Integer outcomeType, @Param("carPartDepotId")Long carPartDepotId, @Param("list") List<CarPartStockOutcome> carPartStockOutcomes);

}
