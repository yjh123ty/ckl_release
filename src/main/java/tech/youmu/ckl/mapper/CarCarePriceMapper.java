/**
 * Project Name:ckl
 * File Name:CarCarePriceMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.CarCarePriceInfo;
import tech.youmu.ckl.domain.CarCarePrice;
import tech.youmu.ckl.domain.OrderServiceDetail;

/**
 * <p>Title:CarCarePriceMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月10日上午9:41:00</p>
 * <p>Description:汽车维护价目表数据访问接口</p>
 */
public interface CarCarePriceMapper extends BaseMapper<CarCarePrice> {

    List<CarCarePriceInfo> findCarCarePriceInfo(@Param("key")String key);

    List<String> findPic(@Param("list")List<OrderServiceDetail> orderServiceDetails);

    int getRepeatCount(CarCarePrice carCarePrice);
   
}
