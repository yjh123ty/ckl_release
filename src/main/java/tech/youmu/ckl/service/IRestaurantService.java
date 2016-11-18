/**
 * Project Name:ckl
 * File Name:IRestaurantService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.RestaurantDetailInfo;
import tech.youmu.ckl.app.vo.RestaurantInfo;
import tech.youmu.ckl.domain.Restaurant;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.RestaurantQuery;

/**
 * <p>Title:IRestaurantService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月13日下午3:35:45</p>
 * <p>Description:饭店服务类</p>
 */
public interface IRestaurantService extends IBaseService<Restaurant> {

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月13日下午5:21:27;</p>
     *	<p>Description: 保存并获取id;</p>
     * @param coverImg 饭店封面图片
     * @param images 
     *  @param restaurant
     */
    void saveAndGetId(MultipartFile coverImg, MultipartFile[] images, Restaurant restaurant);
    
    void saveCooperator(MultipartFile cover, MultipartFile[] imgs, Restaurant restaurant);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午5:07:16;</p>
     *	<p>Description: 修改饭店;</p>
     * @param coverImg 饭店封面图片
     *  @param images
     *  @param restaurant
     */
    void updateById(@Param("cover")MultipartFile coverImg, @Param("imgs")MultipartFile[] images, Restaurant restaurant);
    
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月19日上午11:01:47;</p>
     *  <p>Description: 饭店查询;</p>
     *  @param lng
     *  @param lat
     *  @param sort
     *  @param star
     *  @param starPrice
     *  @param endPrice
     *  @return
     */
    List<RestaurantInfo> findRestaurantInfo(String lng, String lat, Integer sort, Integer stars, Integer startPrice, Integer endPrice,String search,Long stationId);

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月19日下午3:51:25;</p>
     *	<p>Description: 修改饭店状态;</p>
     *  @param restaurant
     */
    void changeStatus(Restaurant restaurant);


    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月20日下午4:21:32;</p>
     *	<p>Description: 查询所有饭店的简单信息;</p>
     */
    List<Restaurant> findRestaurantsSimpleInfo();

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月20日上午11:03:50;</p>
     *	<p>Description: 饭店明细;</p>
     *  @param restaurantId
     *  @return
     */

    RestaurantDetailInfo getRestaurantDetailInfo(Long restaurantId);
}
