/**
 * Project Name:ckl
 * File Name:ICarCarePriceService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.CarCarePriceInfo;
import tech.youmu.ckl.domain.CarCarePrice;

/**
 * <p>Title:ICarCarePriceService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月10日上午10:35:33</p>
 * <p>Description:TODO</p>
 */
public interface ICarCarePriceService extends IBaseService<CarCarePrice> {

    List<CarCarePriceInfo> findCarCarePriceInfo(String key);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月19日上午9:59:06;</p>
     *	<p>Description: 保存保养价格;</p>
     *  @param img
     *  @param carCarePrice
     */
    void save(MultipartFile img, CarCarePrice carCarePrice);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月19日上午9:59:29;</p>
     *	<p>Description: 修改保养价格;</p>
     *  @param img
     *  @param carCarePrice
     */
    void update(MultipartFile img, CarCarePrice carCarePrice);

}
