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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.NavigateRouteInfo;
import tech.youmu.ckl.app.vo.RouteDetailInfo;
import tech.youmu.ckl.app.vo.RouteGuideInfo;
import tech.youmu.ckl.app.vo.RouteGuideSubInfo;
import tech.youmu.ckl.app.vo.RouteInfo;
import tech.youmu.ckl.app.vo.RouteSelectInfo;
import tech.youmu.ckl.app.vo.RouteStationInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.Route;
import tech.youmu.ckl.domain.Station;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.RouteMapper;
import tech.youmu.ckl.mapper.UserRouteMapper;
import tech.youmu.ckl.service.IRouteService;
import tech.youmu.ckl.utils.GaodeUtil;
import tech.youmu.ckl.utils.ImageURLUtil;
import tech.youmu.ckl.utils.UploadUtils;
import tech.youmu.ckl.utils.UserContext;

/**
  * @ClassName: RouteServiceImpl
  * @Description: TODO
  * @author youmu-zh
  * @date 2016年8月17日 下午5:33:33
  *
  */
@Service
public class RouteServiceImpl extends BaseServiceImpl<Route> implements IRouteService{
	
	private RouteMapper routeMapper;
	
	@Autowired
    private UserRouteMapper userRouteMapper;

	@Autowired
	public RouteServiceImpl(RouteMapper routeMapper) {
		super(routeMapper);
		this.routeMapper = routeMapper;
	}

	/**
	  * @see tech.youmu.ckl.service.IRouteService#getTemplateRoutes()
	  */
	@Override
	public List<Route> getAllTemplateRoutes() {
		return routeMapper.getAllTemplateRoutes();
	}

	/**
	  * @see tech.youmu.ckl.service.IRouteService#saveTemplateRoute(tech.youmu.ckl.domain.Route)
	  */
	@Override
	public void saveTemplateRoute(Route route) {
		if(route.getId() == null) {
			throw new BizExecption("模板线路的Id不能为空");
		}
		
		// 确保模板路线的模板为空
		route.setTemplate(null);
		routeMapper.saveAndGetId(route);
		
		if(route.getStations()==null || route.getStations().size() == 0) {
			return;
		}
		
		routeMapper.saveRouteStations(route);
	}

    @Override
    public List<RouteInfo> findRouteInfo(Integer page,Integer rows) {
        List<RouteInfo> routeInfos =  routeMapper.findRouteInfo((page-1)*rows,rows);
        for(RouteInfo routeInfo:routeInfos){
            routeInfo.setImg(ImageURLUtil.fillPath(routeInfo.getImg()));
        }
        return routeInfos;
    }

    @Override
    public RouteDetailInfo getRouteDetailInfo(Long routeId,Long userId) {
        RouteDetailInfo routeDetailInfo =  routeMapper.getRouteDetailInfo(routeId,userId);
        Station userRouteStation = userRouteMapper.getPassUserRouteStation(userId);
        if(userRouteStation == null){
            return routeDetailInfo;
        }
        List<RouteStationInfo> routeStationInfos = routeDetailInfo.getRouteStationInfos();
        for(RouteStationInfo routeStationInfo:routeStationInfos){
            routeStationInfo.setIsPass(StatusConst.TRUE);
            if(userRouteStation.getId().equals(routeStationInfo.getStationId())){
                break;
            }
        }
        long currentRouteId =routeDetailInfo.getCurrentRouteId();
        if(currentRouteId == routeDetailInfo.getRouteId()){
            routeDetailInfo.setIsCurrentUserRoute(StatusConst.TRUE);
        }
        return routeDetailInfo;
    }


    @Override
    public List<RouteSelectInfo> findRouteSelectInfo() {
        List<RouteSelectInfo> routeSelectInfos = new ArrayList<>();
        List<Route> routes = routeMapper.getAll();
        for(Route r:routes){
            RouteSelectInfo routeSelectInfo = new RouteSelectInfo(r.getId(),r.getName());
            List<Station> stations = r.getStations();
            if(stations ==null||stations.size()==0){
                continue;
            }
            Station startStation = stations.get(0);
            Station endStation = stations.get(stations.size()-1);
            if(startStation !=null&&endStation !=null){
                routeSelectInfo.setStartSite(startStation.getName());
                routeSelectInfo.setEndSite(endStation.getName());
            }
            routeSelectInfos.add(routeSelectInfo);
            
        }
        return routeSelectInfos;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IRouteService#save(org.springframework.web.multipart.MultipartFile, tech.youmu.ckl.domain.Route)
     */
    @Override
    public void save(MultipartFile img, Route route) {
        if(routeMapper.getRepeatCount(route) > 0){
            throw new BizExecption("路线名称重复");
        }
        //判断上传文件是否为空      
        if(img != null && !img.isEmpty()) {
            route.setImg(UploadUtils.uploadFile(img, UploadUtils.ROUTE_PATH));
        }
        route.setCreateUser(UserContext.getUser());
        routeMapper.saveAndGetId(route);
        // 保存路线的适合人群
        if(route.getSuits() != null && route.getSuits().size() != 0) {
            routeMapper.saveRouteSuits(route);
        }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IRouteService#updateById(org.springframework.web.multipart.MultipartFile, tech.youmu.ckl.domain.Route)
     */
    @Override
    public void updateById(MultipartFile img, Route route) {
        if(routeMapper.getRepeatCount(route) > 0){
            throw new BizExecption("路线名称重复");
        }
        if(img != null && !img.isEmpty()) {
            route.setImg(UploadUtils.uploadFile(img, UploadUtils.ROUTE_PATH));
        }
        super.updateById(route);
        
        // 修改路线的适合人群
        if(route.getSuits() != null && route.getSuits().size() != 0) {
            routeMapper.deleteRouteSuits(route.getId());
            routeMapper.saveRouteSuits(route);
        }
    }

    @Override
    public NavigateRouteInfo getNavigateRouteInfo(String startLng, String startLat, String endLng, String endLat) {
        StringBuffer origins = new StringBuffer();
        origins.append(startLng).append(",").append(startLat);
        StringBuffer destination = new StringBuffer();
        destination.append(endLng).append(",").append(endLat);
        NavigateRouteInfo navigateRouteInfo = GaodeUtil.getNavigateRouteInfo(origins.toString(), destination.toString(), 0);
        navigateRouteInfo.setLng(endLng);
        navigateRouteInfo.setLat(endLat);
        navigateRouteInfo.setType(StatusConst.ONE);
        return navigateRouteInfo;
    }

    @Override
    public List<RouteGuideInfo> findRouteGuideInfo(Long routeId) {
        List<RouteGuideInfo> routeGuideInfos = routeMapper.findRouteGuideInfo(routeId);
        for(RouteGuideInfo routeGuideInfo:routeGuideInfos){
            routeGuideInfo.setImg(ImageURLUtil.fillPath(routeGuideInfo.getImg()));
            for(RouteGuideSubInfo routeGuideSubInfo:routeGuideInfo.getRouteGuideSubInfos()){
                routeGuideSubInfo.setImg(ImageURLUtil.fillPath(routeGuideSubInfo.getImg()));
            }
        }
        return routeGuideInfos;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IRouteService#findNoneTemplateTravelNoteRoutes()
     */
    @Override
    public List<Route> findNoneTemplateTravelNoteRoutes() {
        return routeMapper.findNoneTemplateTravelNoteRoutes();
    }
}
