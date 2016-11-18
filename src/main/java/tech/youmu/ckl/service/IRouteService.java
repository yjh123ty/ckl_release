/**
 * @Title: IRouteService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月17日 下午4:39:34
 * @version V1.0
 */

package tech.youmu.ckl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.NavigateRouteInfo;
import tech.youmu.ckl.app.vo.RouteDetailInfo;
import tech.youmu.ckl.app.vo.RouteGuideInfo;
import tech.youmu.ckl.app.vo.RouteInfo;
import tech.youmu.ckl.app.vo.RouteSelectInfo;
import tech.youmu.ckl.domain.Route;

/**
  * @ClassName: IRouteService
  * @Description: 路线服务
  * @author youmu-zh
  * @date 2016年8月17日 下午4:39:34
  *
  */

public interface IRouteService extends IBaseService<Route> {

	/**
	  * getTemplateRoutes(查询模板线路列表)
	  *
	  */
	List<Route> getAllTemplateRoutes();

	/**
	  * saveTemplateRoute(保存模板路线)
	  *
	  */
	void saveTemplateRoute(Route route);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月30日下午3:42:05;</p>
	 *	<p>Description: 路线列表;</p>
	 *  @return
	 */
    List<RouteInfo> findRouteInfo(Integer page,Integer rows);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年8月30日下午4:35:39;</p>
     *	<p>Description: 路线明细;</p>
     *  @param routeId
     *  @return
     */
    RouteDetailInfo getRouteDetailInfo(Long routeId, Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月1日上午9:52:18;</p>
     *	<p>Description: 获取攻略;</p>
     *  @param routeId
     *  @return
     */
    List<RouteGuideInfo> findRouteGuideInfo(Long routeId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月6日下午2:45:22;</p>
     *	<p>Description: 选择路线列表;</p>
     *  @return
     */
    List<RouteSelectInfo> findRouteSelectInfo();

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午5:42:36;</p>
     *	<p>Description: TODO;</p>
     *  @param img
     *  @param route
     */
    void save(MultipartFile img, Route route);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午5:42:45;</p>
     *	<p>Description: TODO;</p>
     *  @param img
     *  @param route
     */
    void updateById(MultipartFile img, Route route);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月21日上午10:40:05;</p>
     *	<p>Description: 推荐路线信息;</p>
     *  @param startLng
     *  @param startLat
     *  @param endLng
     *  @param endLat
     *  @return
     */

    NavigateRouteInfo getNavigateRouteInfo(String startLng, String startLat, String endLng, String endLat);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月8日上午9:32:48;</p>
     *	<p>Description: 查询没有游记模板的路线;</p>
     *  @return
     */
    List<Route> findNoneTemplateTravelNoteRoutes();

    
}
