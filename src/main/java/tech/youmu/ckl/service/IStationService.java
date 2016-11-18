/**
 * Project Name:ckl
 * File Name:IServiceStationService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.CollectStationInfo;
import tech.youmu.ckl.app.vo.NavigateRouteInfo;
import tech.youmu.ckl.app.vo.StationDetailInfo;
import tech.youmu.ckl.app.vo.StationInfo;
import tech.youmu.ckl.domain.Hotel;
import tech.youmu.ckl.domain.Restaurant;
import tech.youmu.ckl.domain.Station;

/**
 * <p>Title:IServiceStationService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月24日上午11:05:42</p>
 * <p>Description:服务站服务</p>
 */
public interface IStationService extends IBaseService<Station> {

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月24日下午3:43:24;</p>
     *	<p>Description: 修改站点的状态;</p>
     *  @param station
     */
    void changeStatus(Station station);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年8月25日上午11:33:24;</p>
     *	<p>Description: TODO;</p>
     *  @param userId
     *  @return
     */
	List<CollectStationInfo> findCollectStationInfo(Long userId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月31日下午2:36:23;</p>
     *	<p>Description: 保存并回填id;</p>
     *  @param station
     */
    void saveAndGetId(Station station);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月31日下午4:34:11;</p>
	 *	<p>Description: 收藏站点;</p>
	 *  @param stationId
	 *  @param userId
	 */
    void collectStation(Long stationId, Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年8月31日下午4:47:26;</p>
     *	<p>Description: 取消收藏;</p>
     *  @param stationId
     *  @param userId
     */
    void cancelCollectStation(Long stationId, Long userId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月6日下午6:03:45;</p>
     *	<p>Description: 保存数据到站点表和站点服务中间表;</p>
     * @param images 
     *  @param station
     */
    void saveStationWithServcieTypes(MultipartFile[] images, Station station);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月6日下午6:08:03;</p>
     *	<p>Description: 更新服务站及服务站服务类型中间表;</p>
     * @param images 
     *  @param station
     */
    void updateStationWithServcieTypes(MultipartFile[] images, Station station);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月7日上午9:53:09;</p>
     *	<p>Description: 获取酒店服务信息;</p>
     *  @param stationId 服务站点
     *  @return
     */
    List<Hotel> getHotelServiceInfo(Long stationId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月7日上午11:51:55;</p>
     *	<p>Description: 根据服务站点获取下面的饭店服务信息;</p>
     *  @param stationId
     *  @return
     */
    List<Restaurant> getRestaurantServiceInfo(Long stationId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月8日上午11:13:18;</p>
     *	<p>Description: 获取所有包含酒店服务类型的服务站;</p>
     *  @return
     */
    List<Station> getHasHotelStations();

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月13日下午4:20:43;</p>
     *	<p>Description: 获取包含饭店服务类型的服务站;</p>
     *  @return
     */
    List<Station> getHasRestaurantStations();

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月20日下午12:04:55;</p>
     *	<p>Description: 获取最近的驿站;</p>
     *  @param lng
     *  @param lat
     *  @return
     */
    StationInfo getRecentStationInfo(String lng, String lat);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月21日下午2:29:09;</p>
     *	<p>Description: TODO;</p>
     *  @param lng
     *  @param lat
     *  @return
     */
    List<NavigateRouteInfo> findNavigateRouteInfo(Long userId, String lng, String lat);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月21日下午6:29:04;</p>
     *	<p>Description: 服务站明细;</p>
     *  @param stationId
     *  @return
     */
    StationDetailInfo getStationDetailInfo(Long stationId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月13日下午4:42:47;</p>
     *	<p>Description: 查询含有仓库的服务站;</p>
     *  @return
     */
    List<Station> findHasDeoptServiceStations();
}
