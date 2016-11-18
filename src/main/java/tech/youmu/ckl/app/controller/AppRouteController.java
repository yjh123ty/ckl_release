package tech.youmu.ckl.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tech.youmu.ckl.app.vo.CurrentUserRouteInfo;
import tech.youmu.ckl.app.vo.NavigateRouteInfo;
import tech.youmu.ckl.app.vo.RemoteResult;
import tech.youmu.ckl.app.vo.RouteDetailInfo;
import tech.youmu.ckl.app.vo.RouteGuideInfo;
import tech.youmu.ckl.app.vo.RouteInfo;
import tech.youmu.ckl.app.vo.RouteSelectInfo;
import tech.youmu.ckl.app.vo.StationDetailInfo;
import tech.youmu.ckl.app.vo.TrackCoordInfo;
import tech.youmu.ckl.app.vo.TrackDetailInfo;
import tech.youmu.ckl.app.vo.TravelNoteDetailInfo;
import tech.youmu.ckl.exception.ParamException;
import tech.youmu.ckl.service.IRouteService;
import tech.youmu.ckl.service.IStationService;
import tech.youmu.ckl.service.ITrackService;
import tech.youmu.ckl.service.ITravelNoteService;
import tech.youmu.ckl.service.IUserRouteService;
import tech.youmu.ckl.utils.ResultUtils;

@RestController
@RequestMapping(value = "/route/remote")
@Api(description = "路线页面")
public class AppRouteController {

	@Autowired
	private IRouteService routeService;
	
	@Autowired
    private IUserRouteService userRouteService;
	
	@Autowired
    private IStationService stationService;
	
	@Autowired
    private ITravelNoteService travelNoteService;
	
	@Autowired
    private ITrackService trackService;

	@ApiOperation(value = "路线列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "第几页", required = true,paramType="query", dataType = "int",defaultValue="1"),
        @ApiImplicitParam(name = "rows", value = "每页个数", required = true,paramType="query", dataType = "int",defaultValue="10")
    })
	@RequestMapping(value = "findRouteInfo.action", method = RequestMethod.POST)
	public RemoteResult<RouteInfo> findRouteInfo(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer rows) {
	    List<RouteInfo> routeInfos = routeService.findRouteInfo(page,rows);
		return ResultUtils.createDefResult(routeInfos);
	}

	@ApiOperation(value="路线明细")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "routeId", value = "路线id", required = true,paramType="query", dataType = "long",defaultValue="149"),
	    @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
	})
	@RequestMapping(value="getRouteDetailInfo.action",method=RequestMethod.POST)
    public RemoteResult<RouteDetailInfo> getRouteDetailInfo(Long routeId,Long userId) {
         if(routeId == null){
             throw new ParamException("routeId不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        RouteDetailInfo routeDetailInfo = routeService.getRouteDetailInfo(routeId,userId);
        return ResultUtils.createDefResult(routeDetailInfo);
    }
	
	
	@ApiOperation(value="收藏站点")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stationId", value = "站点id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="collectStation.action",method=RequestMethod.POST)
    public RemoteResult collectStation(Long stationId,Long userId) {
         if(stationId == null){
             throw new ParamException("stationId不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        stationService.collectStation(stationId,userId);
        return ResultUtils.createNullResult();
    }
	
	
	@ApiOperation(value="取消收藏站点")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stationId", value = "站点id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="cancelCollectStation.action",method=RequestMethod.POST)
    public RemoteResult cancelCollectStation(Long stationId,Long userId) {
         if(stationId == null){
             throw new ParamException("stationId不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        stationService.cancelCollectStation(stationId,userId);
        return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value="获取路线攻略")
    @ApiImplicitParam(name = "routeId", value = "路线id", required = true,paramType="query", dataType = "long",defaultValue="1")
    @RequestMapping(value="findRouteGuideInfo.action",method=RequestMethod.POST)
    public RemoteResult<RouteGuideInfo> findRouteGuideInfo(Long routeId) {
         if(routeId == null){
             throw new ParamException("routeId不能为空");
         }
         List<RouteGuideInfo> routeGuideInfos = routeService.findRouteGuideInfo(routeId);
        return ResultUtils.createDefResult(routeGuideInfos);
    }
	
	@ApiOperation(value = "路线选择列表")
    @RequestMapping(value = "findRouteSelectInfo.action", method = RequestMethod.POST)
    public RemoteResult<RouteSelectInfo> findRouteSelectInfo() {
        List<RouteSelectInfo> routeInfos = routeService.findRouteSelectInfo();
        return ResultUtils.createDefResult(routeInfos);
    }

	@ApiOperation(value = "获取推荐路线导航信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "startLng", value = "开始的纬度", required = true,paramType="query", dataType = "double",defaultValue="116.416357"),
        @ApiImplicitParam(name = "startLat", value = "开始的经度", required = true,paramType="query", dataType = "double",defaultValue="39.928353"),
        @ApiImplicitParam(name = "endLng", value = "结束的纬度", required = true,paramType="query", dataType = "double",defaultValue="116.426357"),
        @ApiImplicitParam(name = "endLat", value = "结束的经度", required = true,paramType="query", dataType = "double",defaultValue="39.938353")
    })
    @RequestMapping(value = "getNavigateRouteInfo.action", method = RequestMethod.POST)
    public RemoteResult<NavigateRouteInfo> getRecommendRouteInfo(String startLng,String startLat ,String endLng ,String endLat ) {
	    if(startLng == null){
            throw new ParamException("startLng不能为空");
        }
	    if(startLat == null){
            throw new ParamException("startLat不能为空");
        }
	    if(endLng == null){
            throw new ParamException("endLng不能为空");
        }
	    if(endLat == null){
            throw new ParamException("endLat不能为空");
        }
	    NavigateRouteInfo recommendRouteInfo = routeService.getNavigateRouteInfo(startLng, startLat, endLng, endLat);
        return ResultUtils.createDefResult(recommendRouteInfo);
    }
	
	@ApiOperation(value = "获取服务站信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "stationId", value = "服务站id", required = true,paramType="query", dataType = "long",defaultValue="1"),
    })
    @RequestMapping(value = "getStationDetailInfo.action", method = RequestMethod.POST)
    public RemoteResult<StationDetailInfo> getStationDetailInfo(Long stationId ) {
	    if(stationId == null){
            throw new ParamException("stationId不能为空");
        }
	    StationDetailInfo stationDetailInfo = stationService.getStationDetailInfo(stationId);
        return ResultUtils.createDefResult(stationDetailInfo);
    }
	
	
	@ApiOperation(value = "获取当前行程信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
    })
    @RequestMapping(value = "getCurrentUserRouteInfo.action", method = RequestMethod.POST)
    public RemoteResult<CurrentUserRouteInfo> getCurrentUserRouteInfo(Long userId ) {
	    if(userId == null){
            throw new ParamException("userId不能为空");
        }
	    CurrentUserRouteInfo currentUserRouteInfo = userRouteService.getCurrentUserRouteInfo(userId);
        return ResultUtils.createDefResult(currentUserRouteInfo);
    }
	
	@ApiOperation(value = "开始导航(返回行程id，即userRouteId)")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "routeId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="150"),
        @ApiImplicitParam(name = "stationId", value = "开始站点id", required = false,paramType="query", dataType = "long"),
        @ApiImplicitParam(name = "lng", value = "纬度", required = true,paramType="query", dataType = "double",defaultValue="116.426357"),
        @ApiImplicitParam(name = "lat", value = "经度", required = true,paramType="query", dataType = "double",defaultValue="39.938353")
    })
    @RequestMapping(value = "startNavigate.action", method = RequestMethod.POST)
    public RemoteResult startNavigate(Long userId,Long routeId,Long stationId,String lng,String lat) {
	    if(userId == null){
            throw new ParamException("userId不能为空");
        }
	    if(routeId == null){
            throw new ParamException("routeId不能为空");
        }
	    if(lng == null){
            throw new ParamException("lng不能为空");
        }
	    if(lat == null){
            throw new ParamException("lat不能为空");
        }
	    long userRouteId = userRouteService.startNavigate(userId,routeId,stationId,lng,lat);
        return ResultUtils.createDefResult(userRouteId);
    }
	
	
	@ApiOperation(value = "继续导航(返回行程id，即userRouteId)")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "stationId", value = "开始站点id", required = false,paramType="query", dataType = "long"),
    })
    @RequestMapping(value = "continueNavigate.action", method = RequestMethod.POST)
    public RemoteResult continueNavigate(Long userId,Long stationId) {
        if(userId == null){
            throw new ParamException("userId不能为空");
        }
        long userRouteId = userRouteService.continueNavigate(userId,stationId);
        return ResultUtils.createDefResult(userRouteId);
    }
	
	@ApiOperation(value = "结束导航(返回行程id，即userRouteId)")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
    })
    @RequestMapping(value = "endNavigate.action", method = RequestMethod.POST)
    public RemoteResult<Long> endNavigate(Long userId) {
        if(userId == null){
            throw new ParamException("userId不能为空");
        }
        long userRouteId = userRouteService.endNavigate(userId);
        return ResultUtils.createDefResult(userRouteId);
    }
	
	
	@ApiOperation(value = "结束行程(返回行程id,即userRouteId)")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value = "endUserRoute.action", method = RequestMethod.POST)
    public RemoteResult<Long> endUserRoute(Long userId) {
        if(userId == null){
            throw new ParamException("userId不能为空");
        }
        long userRouteId = userRouteService.endUserRoute(userId);
        return ResultUtils.createDefResult(userRouteId);
    }
	
	
	@ApiOperation(value="轨迹明细")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userRouteId", value = "行程id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })
    @RequestMapping(value="getTrackDetailInfo.action",method=RequestMethod.POST)
    public RemoteResult<TrackDetailInfo> getTrackDetailInfo(@RequestParam(value="userRouteId") Long userRouteId) {
        if(userRouteId == null){
             throw new ParamException("routeId不能为空");
        }
        TrackDetailInfo trackDetailInfo = trackService.getTrackDetailInfo(null,userRouteId);
        return ResultUtils.createDefResult(trackDetailInfo);
    }
	
	 
    @ApiOperation(value="游记明细")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userRouteId", value = "行程id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })
    @RequestMapping(value="getTravelNoteDetailInfo.action",method=RequestMethod.POST)
    public RemoteResult<TravelNoteDetailInfo> getTravelNoteDetailInfo(@RequestParam(value="userRouteId") Long userRouteId) {
        if(userRouteId == null){
             throw new ParamException("userRouteId不能为空");
        }
        TravelNoteDetailInfo travelNoteDetailInfo = travelNoteService.getTravelNoteDetailInfo(null,userRouteId);
        return ResultUtils.createDefResult(travelNoteDetailInfo);
    }
    
    @ApiOperation(value="分享游记")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "travelNoteId", value = "游记id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="shareTravelNote.action",method=RequestMethod.POST)
    public RemoteResult shareTravelNote(Long travelNoteId,Long userId) {
        if(travelNoteId == null){
             throw new ParamException("travelNoteId不能为空");
        }
        if(userId == null){
            throw new ParamException("userId不能为空");
       }
        travelNoteService.shareTravelNote(travelNoteId,userId);
        return ResultUtils.createNullResult();
    }
    
    
    @ApiOperation(value="分享轨迹")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "trackId", value = "轨迹id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="shareTrack.action",method=RequestMethod.POST)
    public RemoteResult shareTrack(Long trackId,Long userId) {
        if(trackId == null){
             throw new ParamException("trackId不能为空");
        }
        if(userId == null){
            throw new ParamException("userId不能为空");
       }
        trackService.shareTrack(trackId,userId);
        return ResultUtils.createNullResult();
    }
    
    @ApiOperation(value="轨迹h5")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "trackId", value = "轨迹id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })
    @RequestMapping(value="showTrackHtml")
    public ModelAndView showTrackHtml(Long trackId) {
        ModelAndView modelAndView = new ModelAndView("h5/trackHtml");
        if(trackId == null){
            modelAndView.addObject("trackCoordInfos", new JSONArray());
            return modelAndView;
        }
        TrackDetailInfo trackDetailInfo = trackService.getTrackDetailInfo(trackId, null);
        modelAndView.addObject("trackDetailInfo", trackDetailInfo);
        if(trackDetailInfo == null||trackDetailInfo.getTrackCoordInfos() == null){
            modelAndView.addObject("trackCoordInfos", new JSONArray());
            return modelAndView;
        }
        modelAndView.addObject("trackCoordInfos", JSONArray.fromObject(trackDetailInfo.getTrackCoordInfos()));
        return modelAndView;
    }
    
    
    @ApiOperation(value="游记h5")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "travelNoteId", value = "游记id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })
    @RequestMapping(value="showTravelNoteHtml")
    public ModelAndView showTravelNoteHtml(Long travelNoteId) {
        ModelAndView modelAndView = new ModelAndView("h5/travelNoteHtml");
        if(travelNoteId == null){
            return modelAndView;
        }
        TravelNoteDetailInfo travelNoteDetailInfo = travelNoteService.getTravelNoteDetailInfo(travelNoteId,null);
        modelAndView.addObject("travelNoteDetailInfo", travelNoteDetailInfo);
        return modelAndView;
    }

}