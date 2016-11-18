/**
 * Project Name:ckl
 * File Name:CarPartDepotMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.CarPartDepot;
import tech.youmu.ckl.domain.Employee;

/**
 * <p>Title:CarPartDepotMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月12日下午4:55:49</p>
 * <p>Description:零部件仓库数据访问接口</p>
 */
public interface CarPartDepotMapper extends BaseMapper {
    
    int checkCount(CarPartDepot depot);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月20日下午12:05:26;</p>
     *	<p>Description: 查询所有的库管员候选人;</p>
     *  @return
     */
    List<Employee> findCandidateKeepers();

    CarPartDepot getByEmployeeId(Long employeeId);

}
