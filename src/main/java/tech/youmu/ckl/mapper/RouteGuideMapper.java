/**
 * Project Name:ckl
 * File Name:RouteGuideMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.RouteGuideItem;
import tech.youmu.ckl.domain.RouteGuideSubItem;
/**
 * <p>Title:RouteGuideMapper</p>
 * @author zh
 * @version v1.0
 * <p>Date:2016年9月22日下午6:05:43</p>
 * <p>Description:路线攻略的数据访问接口</p>
 */
public interface RouteGuideMapper {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午6:08:37;</p>
     *  <p>Description: 根据路线的名称查询路线的攻略;</p>
     *  @param routeId
     *  @return
     */
    public List<RouteGuideItem> findRouteGuide(Long routeId);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午6:08:37;</p>
     *  <p>Description: 根据路线的名称查询路线的攻略;</p>
     *  @param routeId
     *  @return
     */
    public List<RouteGuideItem> findRouteGuideTitle(Long routeId);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午6:12:00;</p>
     *  <p>Description: 查找;</p>
     *  @param routeId
     *  @return
     */
    public List<RouteGuideItem> findRouteGuideItems(Long routeId);   
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午6:13:57;</p>
     *  <p>Description: 查询该父标题下的所有子标题;</p>
     *  @param superId
     *  @return
     */
    public List<RouteGuideSubItem> findRouteGuideSubItems(Long superId);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午6:15:05;</p>
     *  <p>Description: 保存一个路线父标题;</p>
     *  @param item
     */
    public void saveRouteGuideItem(RouteGuideItem item);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午6:15:43;</p>
     *  <p>Description: 保存路线子标题;</p>
     *  @param item
     */
    public  void saveRouteGuideSubItem(RouteGuideSubItem item);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午6:16:18;</p>
     *  <p>Description: 删除游记下面的父标题</p>
     *  @param item
     */
    public void deleteRouteGuideItem(Long id);
    
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午6:17:32;</p>
     *  <p>Description: 删除一个路线子标题，不会影响父标题;</p>
     *  @param item
     */
    public void deleteRouteGuideSubItem(Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午6:17:32;</p>
     *  <p>Description: 删除一个路线父标题下的所有子标题;</p>
     *  @param item
     */
    public void deleteRouteGuideSubItemBySuperId(Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午6:16:18;</p>
     *  <p>Description: 修改游记下面的父标题 </p>
     *  @param item
     */
    public void updateRouteGuideItem(RouteGuideItem item);
    
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午6:17:32;</p>
     *  <p>Description: 修改一个路线子标题，不会影响父标题;</p>
     *  @param item
     */
    public void updateRouteGuideSubItem(RouteGuideSubItem item);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月23日下午12:14:02;</p>
     *	<p>Description: 根据id 查找父标题;</p>
     *  @param id
     */
    public RouteGuideItem getRouteGuideItem(Long id);
    
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月23日下午12:14:02;</p>
     *	<p>Description: 根据id 查找子标题;</p>
     *  @param id
     */
    public RouteGuideSubItem getRouteGuideSubItem(Long id);
}
