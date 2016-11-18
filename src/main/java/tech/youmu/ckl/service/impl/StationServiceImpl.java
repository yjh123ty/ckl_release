/**
 * Project Name:ckl
 * File Name:StationServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.CollectStationInfo;
import tech.youmu.ckl.app.vo.NavigateRouteInfo;
import tech.youmu.ckl.app.vo.StationDetailInfo;
import tech.youmu.ckl.app.vo.StationInfo;
import tech.youmu.ckl.app.vo.StationServiceInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.Hotel;
import tech.youmu.ckl.domain.Restaurant;
import tech.youmu.ckl.domain.ServiceType;
import tech.youmu.ckl.domain.Station;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.RouteMapper;
import tech.youmu.ckl.mapper.StationMapper;
import tech.youmu.ckl.mapper.UserRouteMapper;
import tech.youmu.ckl.service.IStationService;
import tech.youmu.ckl.utils.GaodeUtil;
import tech.youmu.ckl.utils.ImageURLUtil;
import tech.youmu.ckl.utils.UploadUtils;

/**
 * <p>Title:StationServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月24日上午11:13:17</p>
 * <p>Description:服务站服务实现类</p>
 */
@Service
public class StationServiceImpl extends BaseServiceImpl<Station> implements IStationService {

    private StationMapper stationMapper;
    
    @Autowired
    private RouteMapper routeMapper;
    
    @Autowired
    private UserRouteMapper userRouteMapper;
    
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月24日上午11:13:35;</p>
     *	<p>Description: 服务实现类;</p>
     */
    @Autowired
    public StationServiceImpl(StationMapper stationMapper) {
        super(stationMapper);
        this.stationMapper = stationMapper;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IStationService#changeStatus(tech.youmu.ckl.domain.Station)
     */
    @Override
    public void changeStatus(Station station) {
        stationMapper.changeStatus(station);
    }

	@Override
	public List<CollectStationInfo> findCollectStationInfo(Long userId) {
		return stationMapper.findCollectStationInfo(userId);
	}

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IStationService#saveAndGetId(tech.youmu.ckl.domain.Station)
     */
    @Override
    public void saveAndGetId(Station station) {
       stationMapper.saveAndGetId(station);
    }

    @Override
    public void collectStation(Long stationId, Long userId) {
        if(stationMapper.isCollectStation(stationId,userId)){
            throw new BizExecption("已经收藏");
        }
        stationMapper.collectStation(stationId,userId);
    }

    @Override
    public void cancelCollectStation(Long stationId, Long userId) {
        stationMapper.cancelCollectStation(stationId,userId);
    }

    @Override
    public void saveStationWithServcieTypes(MultipartFile[] images, Station station) {
        if(stationMapper.getRepeatConut(station) > 0) {
            throw new BizExecption("服务站名称已经存在");
        }
        // 通过高德地图 地址反向获取经纬度
        String[] coordinate = GaodeUtil.getCoordinateByAddress(station.getFullAddress());
        station.setLongtitude(coordinate[0]);
        station.setLatitude(coordinate[1]);
        
        stationMapper.saveAndGetId(station);
        if(ArrayUtils.isNotEmpty(images)) {
            List<String> urls = UploadUtils.uploadFiles(images, UploadUtils.STATION_IMG);
            if(urls != null && urls.size() != 0) {
                stationMapper.saveImgsUrls(urls, station.getId());
            }
        }
        List<ServiceType> serviceTypes = station.getServiceTypes();
        if(station.getId() != null && serviceTypes != null && station.getServiceTypes().size() != 0) {
            stationMapper.saveStationServiceTypes(station);
        }
        
    }

    @Override
    public void updateStationWithServcieTypes(MultipartFile[] images, Station station) {
        if(stationMapper.getRepeatConut(station) > 0) {
            throw new BizExecption("服务站名称已经存在");
        }
        // 通过高德地图 地址反向获取经纬度
        String[] coordinate = GaodeUtil.getCoordinateByAddress(station.getFullAddress());
        station.setLongtitude(coordinate[0]);
        station.setLatitude(coordinate[1]);
        
        if(ArrayUtils.isNotEmpty(images)) {
            List<String> urls = UploadUtils.uploadFiles(images, UploadUtils.STATION_IMG);
            if(urls != null && urls.size() != 0) {
                stationMapper.deleteImgsUrlsByStationId(station.getId());
                stationMapper.saveImgsUrls(urls, station.getId());
            }
        }
        // 修改服务站
        stationMapper.updateById(station);
        //　删除服务和服务类型中间表关系
       stationMapper.deleteStationServiceTypes(station.getId());
        // 建立服务站和服务类型的中间表的关系
        List<ServiceType> serviceTypes = station.getServiceTypes();
        if(station.getId() != null && serviceTypes != null && station.getServiceTypes().size() != 0) {
            stationMapper.saveStationServiceTypes(station);
        }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IStationService#getHotelServiceInfo(java.lang.Long)
     */
    @Override
    public List<Hotel> getHotelServiceInfo(Long stationId) {
        return stationMapper.getStationHotels(stationId);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IStationService#getRestaurantServiceInfo(java.lang.Long)
     */
    @Override
    public List<Restaurant> getRestaurantServiceInfo(Long stationId) {
        return stationMapper.getStationRestaurants(stationId);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IStationService#getHasHotelStations()
     */
    @Override
    public List<Station> getHasHotelStations() {
        return stationMapper.getHasHotelStations();
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IStationService#getHasRestaurantStations()
     */
    @Override
    public List<Station> getHasRestaurantStations() {
        return stationMapper.getHasRestaurantStations();
    }

    @Override
    public StationInfo getRecentStationInfo(String lng, String lat) {
        List<Station> stations =  stationMapper.getAll();
        StringBuffer origins = new StringBuffer();
        StringBuffer destination = new StringBuffer();
        destination.append(lng).append(",").append(lat);
        for(Station station :stations){
            if(station.getLongtitude() == null||station.getLongtitude() ==null){
                origins.append(0).append(",").append(0).append("|");
            }else{
                origins.append(station.getLongtitude()).append(",").append(station.getLatitude()).append("|");
            }
        }
        List<Double> distances = GaodeUtil.getDistances(origins.toString(), destination.toString(), 1);
        int maxIndex =0;
        double minDistance = 0;
        for(int i=0;i<distances.size();i++){
            double distance = distances.get(i)/1000;
            if(minDistance==0||minDistance>distance){
                minDistance = distance;
                maxIndex = i;
            }
        }
        double price = StationInfo.BASE_PRICE+minDistance*StationInfo.DISTANCE_PRICE;
        Station minStation = stations.get(maxIndex);
        StationInfo  stationInfo= new StationInfo(minStation.getId(),minStation.getName(),minStation.getMobile(),price);
        return stationInfo;
    }

    @Override
    public List<NavigateRouteInfo> findNavigateRouteInfo(Long userId,String lng, String lat) {
        List<NavigateRouteInfo> navigateRouteInfos = new ArrayList<>();
        navigateRouteInfos.add(getMinNavigateRouteInfo(lng, lat));
        NavigateRouteInfo wayNavigateRouteInfo = getWayNavigateRouteInfo( userId,lng,lat);
        if(wayNavigateRouteInfo !=null){
            navigateRouteInfos.add(getWayNavigateRouteInfo(userId,lng,lat));
        }
        return navigateRouteInfos;
        
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月21日下午3:43:02;</p>
     *	<p>Description: 顺路;</p>
     *  @return
     */
    private NavigateRouteInfo getWayNavigateRouteInfo(Long userId,String lng, String lat){
        Station  nextStation = userRouteMapper.getArriveUserRouteStation(userId);
        if(nextStation !=null){
            NavigateRouteInfo wayNavigateRouteInfo = getNavigateRouteInfo(lng, lat, nextStation.getLongtitude(), nextStation.getLatitude());
            wayNavigateRouteInfo.setType(StatusConst.TWO);
            wayNavigateRouteInfo.setStationId(nextStation.getId());
            return wayNavigateRouteInfo;
        }else{
            return null;
        }
        
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月21日下午3:40:36;</p>
     *	<p>Description: 最近的信息;</p>
     *  @param lng
     *  @param lat
     *  @return
     */
    private NavigateRouteInfo getMinNavigateRouteInfo(String lng, String lat){
        List<Station> stations =  stationMapper.getAll();
        StringBuffer origins = new StringBuffer();
        StringBuffer destination = new StringBuffer();
        destination.append(lng).append(",").append(lat);
        for(Station station :stations){
            if(station.getLongtitude() == null||station.getLongtitude() ==null){
                origins.append(0).append(",").append(0).append("|");
            }else{
                origins.append(station.getLongtitude()).append(",").append(station.getLatitude()).append("|");
            }
        }
        List<Double> distances = GaodeUtil.getDistances(origins.toString(), destination.toString(), 1);
        int maxIndex =0;
        double minDistance = 0;
        for(int i=0;i<distances.size();i++){
            double distance = distances.get(i)/1000;
            if(minDistance==0||minDistance>distance){
                minDistance = distance;
                maxIndex = i;
            }
        }
        Station minStation = stations.get(maxIndex);
        NavigateRouteInfo minNavigateRouteInfo = getNavigateRouteInfo(lng, lat, minStation.getLongtitude(), minStation.getLatitude());
        minNavigateRouteInfo.setType(StatusConst.ONE);
        minNavigateRouteInfo.setStationId(minStation.getId());
        return minNavigateRouteInfo;
    }
    
    private NavigateRouteInfo getNavigateRouteInfo(String startLng, String startLat, String endLng, String endLat) {
        StringBuffer origins = new StringBuffer();
        origins.append(startLng).append(",").append(startLat);
        StringBuffer destination = new StringBuffer();
        destination.append(endLng).append(",").append(endLat);
        NavigateRouteInfo navigateRouteInfo = GaodeUtil.getNavigateRouteInfo(origins.toString(), destination.toString(), 0);
        navigateRouteInfo.setLng(endLng);
        navigateRouteInfo.setLat(endLat);
        return navigateRouteInfo;
    }

    @Override
    public StationDetailInfo getStationDetailInfo(Long stationId) {
        StationDetailInfo stationDetailInfo =  stationMapper.getStationDetailInfo(stationId);
        stationDetailInfo.setStationImgs(ImageURLUtil.fillPath(stationDetailInfo.getStationImgs()));
        stationDetailInfo.setStationCoverImg(ImageURLUtil.fillPath(stationDetailInfo.getStationCoverImg()));
        return stationDetailInfo;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IStationService#findHasDeoptServiceStations()
     */
    @Override
    public List<Station> findHasDeoptServiceStations() {
        return stationMapper.findHasDeoptServiceStations();
    }
}
