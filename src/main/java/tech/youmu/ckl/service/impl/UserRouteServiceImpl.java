/**
 * @Title: RouteServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月17日 下午5:33:33
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.app.vo.CurrentUserRouteInfo;
import tech.youmu.ckl.app.vo.UserRouteInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.Route;
import tech.youmu.ckl.domain.Station;
import tech.youmu.ckl.domain.TemplateTravelNote;
import tech.youmu.ckl.domain.TemplateTravelNoteContent;
import tech.youmu.ckl.domain.Track;
import tech.youmu.ckl.domain.TrackCoord;
import tech.youmu.ckl.domain.TravelNote;
import tech.youmu.ckl.domain.TravelNoteContent;
import tech.youmu.ckl.domain.UserRoute;
import tech.youmu.ckl.domain.UserRouteStation;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.RouteMapper;
import tech.youmu.ckl.mapper.StationMapper;
import tech.youmu.ckl.mapper.TemplateTravelNoteMapper;
import tech.youmu.ckl.mapper.TrackMapper;
import tech.youmu.ckl.mapper.TravelNoteMapper;
import tech.youmu.ckl.mapper.UserRouteMapper;
import tech.youmu.ckl.service.IUserRouteService;
import tech.youmu.ckl.service.component.BadgeComponent;

/**
 * 
 * <p>Title:UserRouteServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月22日上午11:04:00</p>
 * <p>Description:行程</p>
 */
@Service
public class UserRouteServiceImpl implements IUserRouteService{
	
	
	@Autowired
    private UserRouteMapper userRouteMapper;
	
	@Autowired
	private RouteMapper routeMapper;
	
	@Autowired
    private StationMapper stationMapper;
	
	@Autowired
    private TrackMapper trackMapper;
	
	@Autowired
    private TravelNoteMapper travelNoteMapper;
	
	@Autowired
    private TemplateTravelNoteMapper templateTravelNoteMapper;
	
	@Autowired
	private BadgeComponent badgeComponent;

    @Override
    public CurrentUserRouteInfo getCurrentUserRouteInfo(Long userId) {
        UserRoute currentUserRoute =userRouteMapper.getCurrentUserRoute(userId);
        CurrentUserRouteInfo currentUserRouteInfo = new CurrentUserRouteInfo();
        if(currentUserRoute == null){
            currentUserRouteInfo.setIsExistUserRoute(StatusConst.FALSE);
        }else{
            currentUserRouteInfo.setCurrentRouteId(currentUserRoute.getRouteId());
            currentUserRouteInfo.setIsExistUserRoute(StatusConst.TRUE);
        }
        return currentUserRouteInfo;
    }

    @Override
    public long startNavigate(Long userId, Long routeId,Long stationId,String lng,String lat) {
        UserRoute currentUserRoute =userRouteMapper.getCurrentUserRoute(userId);
        if(currentUserRoute !=null){
            throw new BizExecption("还有行程没有结束");
        }
        List<Station> stations = stationMapper.findRouteStationByRouteId(routeId);
        if(stations ==null||stations.size()==0){
            throw new BizExecption("该路线没有站点");
        }
        Station startStation =null;
        Station endStation =stations.get(stations.size()-1);
        if(stationId !=null){
            for(Station station:stations){
                if(station.getId().equals(stationId)){
                    startStation = station;
                }
            }
        }
        if(startStation == null){
            startStation = stations.get(0);
        }
        if(currentUserRoute == null){
            String startStationName = startStation.getName();
            String endStationName = endStation.getName();
            currentUserRoute = new UserRoute(userId,routeId,startStationName,endStationName,lng,lat,StatusConst.ONE);
            userRouteMapper.save(currentUserRoute);
        }
        userRouteMapper.saveUserRouteStation(new UserRouteStation(currentUserRoute.getId(),startStation.getId(),StatusConst.ONE));
        return currentUserRoute.getId();
    }

    @Override
    public long endNavigate(Long userId) {
        UserRoute currentUserRoute = userRouteMapper.getCurrentUserRoute(userId);
        if(currentUserRoute ==null){
            throw new BizExecption("行程已经结束");
        }
        Station station=userRouteMapper.getArriveUserRouteStation(userId);
        userRouteMapper.updateUserRouteStationByUserRouteId(currentUserRoute.getId());
        List<Station> stations = stationMapper.findCurrentRouteStationByUserId(userId);
        Station endStation = stations.get(stations.size()-1);
        if(stations!=null&&stations.size()>0&&station.getId().equals(endStation.getId())){
            currentUserRoute.setStatus(StatusConst.THREE);
            userRouteMapper.update(currentUserRoute);
            this.createUserTrack(currentUserRoute,endStation);
            this.createTravelNote(currentUserRoute);
        }
        badgeComponent.routeBadge(userId);
        return currentUserRoute.getId();
    }

    @Override
    public long endUserRoute(Long userId) {
        UserRoute currentUserRoute = userRouteMapper.getCurrentUserRoute(userId);
        if(currentUserRoute == null){
            throw new BizExecption("行程已经结束");
        }
        Station lastStation = userRouteMapper.getLastUserRouteStation(userId);
        if(lastStation !=null){
            this.createUserTrack(currentUserRoute,lastStation);
            this.createTravelNote(currentUserRoute);
            currentUserRoute.setEndStationName(lastStation.getName());
        }
        currentUserRoute.setStatus(StatusConst.TWO);
        userRouteMapper.update(currentUserRoute);
        badgeComponent.routeBadge(userId);
        return currentUserRoute.getId();
    }

    @Override
    public List<UserRouteInfo> findUserRouteInfo(Integer page, Integer rows, Long userId) {
        List<UserRouteInfo> userRouteInfos =  userRouteMapper.findUserRouteInfo((page-1)*rows,rows,userId);
        return userRouteInfos;
    }

    @Override
    public void deleteUserRoute(Long userRouteId) {
        UserRoute userRoute=  userRouteMapper.getById(userRouteId);
        if(userRoute ==null){
            throw new BizExecption("行程已经删除");
        }
        if(userRoute.getStatus().equals(StatusConst.ONE)){
            throw new BizExecption("正在进行中的行程不能删除");
        }
        userRoute.setIsDel(StatusConst.TRUE);
        userRouteMapper.update(userRoute);
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月26日上午9:24:39;</p>
     *	<p>Description: 生成轨迹;</p>
     *  @param userRoute
     *  @param endStation
     */
    private void createUserTrack(UserRoute userRoute,Station endStation){
        Track track = trackMapper.getByUserRouteId(userRoute.getId());
        if(track == null){
            Route route = routeMapper.getById(userRoute.getRouteId());
            track = new Track(userRoute.getUserId(), userRoute.getStartStationName(), endStation.getName(), route.getDistance(), route.getDays(), route.getCost(), userRoute.getId(), route.getImg());
            trackMapper.saveTrack(track);
            if(route.getSuitNames()!=null&&route.getSuitNames().size()>0){
                trackMapper.bacthSaveTrackSuit(route.getSuitNames(),track.getId());
            }
            List<TrackCoord> trackCoords = new ArrayList();
            TrackCoord startTrackCoord = new TrackCoord(userRoute.getLongtitude(),userRoute.getLatitude(),0);
            trackCoords.add(startTrackCoord);
            List<Station> stations = stationMapper.findRouteStationByRouteId(userRoute.getRouteId());
            for(Station station :stations){
                if(!station.getId().equals(endStation.getId())){
                    TrackCoord trackCoord = new TrackCoord(station.getLongtitude(),station.getLatitude(),station.getOrder());
                    trackCoords.add(trackCoord);
                }else {
                    TrackCoord trackCoord = new TrackCoord(station.getLongtitude(),station.getLatitude(),station.getOrder());
                    trackCoords.add(trackCoord);
                    break;
                }
            }
            if(trackCoords!=null&&trackCoords.size()>0){
                trackMapper.bacthSaveTrackCoord(trackCoords, track.getId());
            }
        }
    }
    
    private void createTravelNote(UserRoute userRoute){
        TravelNote travelNote = travelNoteMapper.getByUserRouteId(userRoute.getId());
        if(travelNote == null){
            TemplateTravelNote templateTravelNote = templateTravelNoteMapper.getTemplateTravelNoteByRouteId(userRoute.getRouteId());
            if(templateTravelNote == null){
                templateTravelNote = new TemplateTravelNote();
            }
            travelNote = new TravelNote(templateTravelNote.getTitle(),templateTravelNote.getDistance(),templateTravelNote.getDayCount(),templateTravelNote.getCapitaCost(),templateTravelNote.getRouteImg(),userRoute.getId(),userRoute.getCreateTime(),userRoute.getUserId());
            travelNoteMapper.saveTravelNote(travelNote);
            List<String> travelNoteSuitNames = templateTravelNote.getTemplateTravelNoteSuitNames();
            if(travelNoteSuitNames!=null&&travelNoteSuitNames.size()>0){
                travelNoteMapper.batchSaveTravelNoteSuit(travelNoteSuitNames, travelNote.getId());
            }
            List<TravelNoteContent> travelNoteContents = new ArrayList<>();
            List<TemplateTravelNoteContent>  templateTravelNoteContents =  templateTravelNote.getTemplateTravelNoteContents();
            if(templateTravelNoteContents !=null ){
                for(TemplateTravelNoteContent templateTravelNoteContent:templateTravelNoteContents){
                    TravelNoteContent travelNoteContent = new TravelNoteContent();
                    BeanUtils.copyProperties(templateTravelNoteContent, travelNoteContent);
                    travelNoteContents.add(travelNoteContent);
                }
            }
            if(travelNoteContents.size()>0){
                travelNoteMapper.batchSaveTravelNoteContent(travelNoteContents, travelNote.getId());
            }
        }
    }
    

    @Override
    public long continueNavigate(Long userId, Long stationId) {
        UserRoute currentUserRoute =userRouteMapper.getCurrentUserRoute(userId);
        if(currentUserRoute ==null){
            throw new BizExecption("行程已结束");
        }
        if(stationId == null){
            return currentUserRoute.getId();
        }else{
            userRouteMapper.updateUserRouteStationByUserRouteId(currentUserRoute.getId());
            userRouteMapper.saveUserRouteStation(new UserRouteStation(currentUserRoute.getId(),stationId,StatusConst.ONE));
        }
        return currentUserRoute.getId();
    }

}
