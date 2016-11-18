/**
 * Project Name:ckl
 * File Name:HotelMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.RestaurantDetailInfo;
import tech.youmu.ckl.app.vo.RestaurantInfo;
import tech.youmu.ckl.domain.Restaurant;
import tech.youmu.ckl.query.RestaurantQuery;

/**
 * <p>Title:HotelMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月7日下午4:28:38</p>
 * <p>Description:服务站酒店数据访问接口</p>
 */
public interface RestaurantMapper extends BaseMapper<Restaurant> {

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月13日下午5:22:02;</p>
     *	<p>Description: TODO;</p>
     *  @param restaurant
     */
    void saveAndGetId(Restaurant restaurant);

    Restaurant getRestaurantById(Long restaurantId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午5:11:31;</p>
     *	<p>Description: 保存饭店外观图片;</p>
     *  @param urls
     *  @param id
     */
    void saveImgUrls(@Param("list") List<String> urls, @Param("restaurantId") Long restaurantId);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午5:11:31;</p>
     *  <p>Description: 删除饭店外观图片;</p>
     *  @param urls
     *  @param id
     */
    void deleteImgUrlsByRestaurantId(Long restaurantId);
    
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月19日上午11:03:10;</p>
     *  <p>Description:饭店查询;</p>
     *  @param sort
     *  @param star
     *  @param starPrice
     *  @param endPrice
     *  @return
     */
    List<Restaurant> findRestaurant(@Param("sort")Integer sort,@Param("stars")Integer stars, @Param("startPrice")Integer startPrice, @Param("endPrice")Integer endPrice,@Param("search")String search,@Param("stationId")Long stationId);


    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月19日下午3:54:21;</p>
     *	<p>Description: 更改一个饭店的状态;</p>
     *  @param restaurant
     */
    void changeStatus(Restaurant restaurant);

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月19日下午3:55:23;</p>
     *	<p>Description: 合作商家饭店列表;</p>
     *  @param query
     *  @return
     */
    List<Restaurant> getAllCo(RestaurantQuery query);

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月19日下午3:55:57;</p>
     *	<p>Description: 合作商家饭店的总数;</p>
     *  @param query
     *  @return
     */
    Long getCoCountByQuery(RestaurantQuery query);

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月19日下午4:05:31;</p>
     *	<p>Description: 保存合作商家饭店信息并获取id;</p>
     *  @param restaurant
     */
    void saveCoAndGetId(Restaurant restaurant);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月20日下午4:24:45;</p>
     *	<p>Description: 查询服务站饭店的简单信息;</p>
     *  @return
     */
    List<Restaurant> findRestaurantsSimpleInfo();

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月20日上午11:04:36;</p>
     *	<p>Description: TODO;</p>
     *  @param restaurantId
     *  @return
     */
    RestaurantDetailInfo getRestaurantDetailInfo(Long restaurantId);
    
    int getRepeatCount(Restaurant restaurant);

}
