/**
 * Project Name:ckl
 * File Name:IRestaurantComboService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.RestaurantComboDetailInfo;
import tech.youmu.ckl.domain.RestaurantCombo;

/**
 * <p>Title:IRestaurantComboService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月14日上午10:17:51</p>
 * <p>Description:饭店套餐的服务接口</p>
 */
public interface IRestaurantComboService  extends IBaseService<RestaurantCombo>{

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月14日上午10:20:53;</p>
     *	<p>Description: TODO;</p>
     *  @param id
     *  @return
     */
    List<RestaurantCombo> findRestaurantCombos(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午5:40:35;</p>
     *	<p>Description: TODO;</p>
     *  @param image
     *  @param room
     */
    void save(MultipartFile image, RestaurantCombo room);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午5:41:20;</p>
     *	<p>Description: TODO;</p>
     *  @param image
     *  @param room
     */
    void updateById(MultipartFile image, RestaurantCombo room);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月20日下午5:51:24;</p>
     *	<p>Description: 套餐明细;</p>
     *  @param comboId
     *  @return
     */
    RestaurantComboDetailInfo getRestaurantComboDetailInfo(Long comboId);

}
