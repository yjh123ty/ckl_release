/**
 * @Title: RouteMapper.java
 * @Package tech.youmu.ckl.mapper
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月17日 下午4:44:13
 * @version V1.0
 */

package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.RouteDetailInfo;
import tech.youmu.ckl.app.vo.RouteGuideInfo;
import tech.youmu.ckl.app.vo.RouteInfo;
import tech.youmu.ckl.domain.Route;
import tech.youmu.ckl.domain.Station;

/**
  * @ClassName: RouteMapper
  * @Description: 路线mapper
  * @author youmu-zh
  * @date 2016年8月17日 下午4:44:13
  *
  */
public interface RouteMapper extends BaseMapper<Route> {

	/**
	  * saveAndGetId(保存路线并获取路线的id)
	  * @param route 路线
	  */
	void saveAndGetId(Route route);

	/**
	  * saveRouteStations(保存路线和站点之间的关联关系)
	  * @param route 路线
	  */
	void saveRouteStations(Route route);
	
	/**
     * saveRouteStations(获取所有的模板路线)
     * @param route 路线
     */
   List<Route> getAllTemplateRoutes();
   
   int getRepeatCount(Route route);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月30日下午3:42:55;</p>
	 *	<p>Description: 路线列表;</p>
	 *  @return
	 */
   List<RouteInfo> findRouteInfo(@Param("startRows")Integer startRows, @Param("rows")Integer rows);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年8月30日下午4:36:34;</p>
     *	<p>Description: 获取路线明细;</p>
     *  @param routeId
     *  @return
     */
    RouteDetailInfo getRouteDetailInfo(@Param("routeId")Long routeId,@Param("userId")Long userId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日上午11:13:59;</p>
     *	<p>Description: 保存路线的适合人群;</p>
     *  @param route
     */
    void saveRouteSuits(Route route);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日上午11:14:16;</p>
     *	<p>Description: 删除路线的适合人群;</p>
     *  @param id
     */
    void deleteRouteSuits(Long id);
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月22日下午4:43:48;</p>
     *	<p>Description: 获取路线攻略;</p>
     *  @param routeId
     *  @return
     */
    List<RouteGuideInfo> findRouteGuideInfo(Long routeId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月8日上午9:34:30;</p>
     *	<p>Description: 查询未配置模板游记的路线;</p>
     *  @return
     */
    List<Route> findNoneTemplateTravelNoteRoutes();
}
