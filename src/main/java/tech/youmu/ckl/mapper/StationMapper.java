package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.CollectStationInfo;
import tech.youmu.ckl.app.vo.StationDetailInfo;
import tech.youmu.ckl.domain.Hotel;
import tech.youmu.ckl.domain.Restaurant;
import tech.youmu.ckl.domain.ServiceType;
import tech.youmu.ckl.domain.Station;


public interface StationMapper extends BaseMapper {

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月23日下午2:48:26;</p>
	 *	<p>Description: 收藏服务站数量;</p>
	 *  @param userId
	 *  @return
	 */
	int getCollectStationCountByUserId(Long userId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月24日下午3:48:27;</p>
     *	<p>Description: 改变站点的状态;</p>
     *  @param station
     */
    void changeStatus(Station station);
    
    /**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月25日上午11:15:08;</p>
	 *	<p>Description: TODO;</p>
	 *  @param userId
	 *  @return	
	 */
	List<CollectStationInfo> findCollectStationInfo(Long userId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月31日下午2:37:28;</p>
     *	<p>Description: TODO;</p>
     *  @param station
     *  @return
     */
    void saveAndGetId(Station station);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月31日下午4:37:37;</p>
	 *	<p>Description: 收藏站点;</p>
	 *  @param stationId
	 *  @param userId
	 */
    void collectStation(@Param("stationId")Long stationId, @Param("userId")Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年8月31日下午4:48:10;</p>
     *	<p>Description: 取消收藏站点;</p>
     *  @param stationId
     *  @param userId
     */
    void cancelCollectStation(@Param("stationId")Long stationId, @Param("userId")Long userId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月1日下午3:02:42;</p>
     *	<p>Description: 服务站id获取服务类型;</p>
     *  @param id
     *  @return
     */
    List<ServiceType> getServiceTypesByStationId(Long id);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月7日下午3:24:19;</p>
     *	<p>Description: 是否收藏站点;</p>
     *  @param stationId
     *  @param userId
     *  @return
     */
    boolean isCollectStation(@Param("stationId")Long stationId, @Param("userId")Long userId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月6日下午6:07:32;</p>
     *	<p>Description: 保存服务站和服务类型的中间表;</p>
     *  @param station
     */
    void saveStationServiceTypes(Station station);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月6日下午6:11:48;</p>
     *	<p>Description: 删除站点和服务类型的中间表关系;</p>
     *  @param id
     */
    void deleteStationServiceTypes(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月7日上午9:54:44;</p>
     *	<p>Description: 获取该服务站下的所有酒店信息;</p>
     *  @param stationId
     *  @return
     */
    List<Hotel> getStationHotels(Long stationId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月7日上午11:53:04;</p>
     *	<p>Description: 根据服务站的id获取下面的饭店信息;</p>
     *  @param stationId
     *  @return
     */
    List<Restaurant> getStationRestaurants(Long stationId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月8日上午11:14:03;</p>
     *	<p>Description: 获取含有酒店服务类型的服务站列表;</p>
     *  @return
     */
    List<Station> getHasHotelStations();

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月13日下午4:22:05;</p>
     *	<p>Description: 获取包含饭店的服务站;</p>
     *  @return
     */
    List<Station> getHasRestaurantStations();

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午5:47:49;</p>
     *	<p>Description: TODO;</p>
     *  @param urls
     *  @param id
     */
    void saveImgsUrls(@Param("list") List<String> urls, @Param("stationId") Long id);
    
    void deleteImgsUrlsByStationId(Long id);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月22日下午2:00:13;</p>
     *	<p>Description:获取路线站点;</p>
     *  @param routeId
     *  @return
     */
    List<Station> findRouteStationByRouteId(Long routeId);

    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月22日下午3:17:43;</p>
     *	<p>Description: 获取站点明细;</p>
     *  @param stationId
     *  @return
     */
    StationDetailInfo getStationDetailInfo(Long stationId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月22日下午2:43:29;</p>
     *	<p>Description: 当前行程的站点;</p>
     *  @param userId
     *  @return
     */
    List<Station> findCurrentRouteStationByUserId(Long userId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月13日下午4:45:16;</p>
     *	<p>Description: TODO;</p>
     *  @return
     */
    List<Station> findHasDeoptServiceStations();
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月21日上午11:31:59;</p>
     *	<p>Description: 添加 检查是否重复;</p>
     *  @param station
     *  @return
     */
    int getRepeatConut(Station station);

}