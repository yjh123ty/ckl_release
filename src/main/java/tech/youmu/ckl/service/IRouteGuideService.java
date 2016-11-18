/**
 * Project Name:ckl
 * File Name:IRouteGuideService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.RouteGuideItem;
import tech.youmu.ckl.domain.RouteGuideSubItem;

/**
 * <p>Title:IRouteGuideService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月23日上午10:12:48</p>
 * <p>Description:路线攻略服务</p>
 */
public interface IRouteGuideService {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月23日上午11:43:38;</p>
     *	<p>Description: 查找路线攻略;</p>
     *  @param routeId
     *  @return
     */
    public List<RouteGuideItem> findRouteGuide(Long routeId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月23日下午12:12:06;</p>
     *	<p>Description: TODO;</p>
     *  @param id
     */
    public RouteGuideItem getRouteGuideItem(Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月23日下午12:12:06;</p>
     *	<p>Description: TODO;</p>
     *  @param id
     */
    public RouteGuideSubItem getRouteGuideSubItem(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月23日下午2:31:31;</p>
     *	<p>Description: TODO;</p>
     *  @param id
     */
    public void deleteRouteGuideItem(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月23日下午2:41:52;</p>
     *	<p>Description: TODO;</p>
     *  @param image
     *  @param item
     */
    public void saveRouteGuideItem(MultipartFile image, RouteGuideItem item);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月23日下午2:41:59;</p>
     *	<p>Description: TODO;</p>
     *  @param image
     *  @param item
     */
    public void updateRouteGuideItem(MultipartFile image, RouteGuideItem item);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月23日下午3:15:31;</p>
     *	<p>Description: TODO;</p>
     *  @param image
     *  @param item
     */
    public void saveRouteGuideSubItem(MultipartFile image, RouteGuideSubItem item);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月23日下午3:15:36;</p>
     *	<p>Description: TODO;</p>
     *  @param image
     *  @param item
     */
    public void updateRouteGuideSubItem(MultipartFile image, RouteGuideSubItem item);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月23日下午3:46:20;</p>
     *	<p>Description: 删除子标题;</p>
     *  @param id
     */
    public void deleteRouteGuideSubItem(Long id);
}
