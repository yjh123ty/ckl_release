/**
 * Project Name:ckl
 * File Name:ICarPartTypeService.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.domain.CarPartDepot;
import tech.youmu.ckl.domain.Employee;

/**
 * <p>Title:ICarPartTypeService</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月20日下午2:52:04</p>
 * <p>Description:零部件仓库服务层接口</p>
 */
public interface ICarPartDepotService extends IBaseService<CarPartDepot>{

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月13日下午5:05:19;</p>
     *	<p>Description:  查询所有的中央仓库;</p>
     *  @return
     */
    List<CarPartDepot> findCentreCarPartDepots();

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月13日下午5:10:18;</p>
     *	<p>Description: 查询服务站的仓库;</p>
     *  @return
     */
    List<CarPartDepot> findServiceStationCarPartDepots();

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月20日下午12:04:12;</p>
     *	<p>Description: 查询所有的库管员候选人;</p>
     *  @param i
     *  @return
     */
    List<Employee> findCandidateKeepers();
    
}
