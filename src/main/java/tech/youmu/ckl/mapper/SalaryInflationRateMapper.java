/**
 * Project Name:ckl
 * File Name:SalaryInflationRateMapper.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.mapper;

import tech.youmu.ckl.domain.SalaryInflationRate;

/**
 * <p>Title:SalaryInflationRateMapper</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月9日下午4:02:31</p>
 * <p>Description:薪点值的数据层接口</p>
 */
public interface SalaryInflationRateMapper extends BaseMapper<SalaryInflationRate> {
    public SalaryInflationRate getByYear(Integer year);
}
