/**
 * Project Name:ckl
 * File Name:RouteGuideServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.RouteGuideItem;
import tech.youmu.ckl.domain.RouteGuideSubItem;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.RouteGuideMapper;
import tech.youmu.ckl.service.IRouteGuideService;
import tech.youmu.ckl.utils.UploadUtils;

/**
 * <p>Title:RouteGuideServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月23日上午10:30:55</p>
 * <p>Description:TODO</p>
 */
@Service
public class RouteGuideServiceImpl implements IRouteGuideService {
    
    @Autowired
    private RouteGuideMapper routeGuideMapper;

    @Override
    public List<RouteGuideItem> findRouteGuide(Long routeId) {
        return routeGuideMapper.findRouteGuide(routeId);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IRouteGuideService#findRouteGuideItem(java.lang.Long)
     */
    @Override
    public RouteGuideItem getRouteGuideItem(Long id) {
        return routeGuideMapper.getRouteGuideItem(id);
    }

    @Override
    public RouteGuideSubItem getRouteGuideSubItem(Long id) {
        return routeGuideMapper.getRouteGuideSubItem(id);
    }

    @Override
    public void deleteRouteGuideItem(Long id) {
        // 删除该父标题下的所有子标题
        routeGuideMapper.deleteRouteGuideSubItemBySuperId(id);
        // 删除该父标题
        routeGuideMapper.deleteRouteGuideItem(id);
    }

    @Override
    public void saveRouteGuideItem(MultipartFile image, RouteGuideItem item) {
        item.setImg(UploadUtils.uploadFile(image, UploadUtils.ROUTE_GUIDE_IMG_PATH));
        routeGuideMapper.saveRouteGuideItem(item);
    }

    @Override
    public void updateRouteGuideItem(MultipartFile image, RouteGuideItem item) {
        item.setImg(UploadUtils.uploadFile(image, UploadUtils.ROUTE_GUIDE_IMG_PATH));
        routeGuideMapper.updateRouteGuideItem(item);
    }

    @Override
    public void saveRouteGuideSubItem(MultipartFile image, RouteGuideSubItem item) {
        item.setImg(UploadUtils.uploadFile(image, UploadUtils.ROUTE_GUIDE_IMG_PATH));
        routeGuideMapper.saveRouteGuideSubItem(item);
    }

    @Override
    public void updateRouteGuideSubItem(MultipartFile image, RouteGuideSubItem item) {
        item.setImg(UploadUtils.uploadFile(image, UploadUtils.ROUTE_GUIDE_IMG_PATH));
        routeGuideMapper.updateRouteGuideSubItem(item);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IRouteGuideService#deleteRouteGuideSubItem(java.lang.Long)
     */
    @Override
    public void deleteRouteGuideSubItem(Long id) {
        routeGuideMapper.deleteRouteGuideSubItem(id);
    }

}
