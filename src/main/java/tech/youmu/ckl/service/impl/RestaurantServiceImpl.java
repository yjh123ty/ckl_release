/**
 * Project Name:ckl
 * File Name:RestaurantServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.RestaurantComboInfo;
import tech.youmu.ckl.app.vo.RestaurantDetailInfo;
import tech.youmu.ckl.app.vo.RestaurantInfo;
import tech.youmu.ckl.domain.Restaurant;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.RestaurantMapper;
import tech.youmu.ckl.service.IRestaurantService;
import tech.youmu.ckl.utils.GaodeUtil;
import tech.youmu.ckl.utils.ImageURLUtil;
import tech.youmu.ckl.utils.UploadUtils;

/**
 * <p>Title:RestaurantServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月13日下午3:36:37</p>
 * <p>Description:服务类</p>
 */
@Service
public class RestaurantServiceImpl  extends BaseServiceImpl<Restaurant> implements IRestaurantService {

    private RestaurantMapper restaurantMapper;

    @Autowired
    public RestaurantServiceImpl(RestaurantMapper restaurantMapper) {
        super(restaurantMapper);
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public void saveAndGetId(MultipartFile coverImg, MultipartFile[] images, Restaurant restaurant) {
        if(restaurantMapper.getRepeatCount(restaurant)>0){
            throw new BizExecption("饭店名称重复");
        }
        if(coverImg != null) {
            restaurant.setCover(UploadUtils.uploadFile(coverImg, UploadUtils.RESTURANT_COVER_IMG_PATH));
        }
        restaurantMapper.saveAndGetId(restaurant);
        if(ArrayUtils.isNotEmpty(images)) {
            List<String> urls = UploadUtils.uploadFiles(images, UploadUtils.RESTURANT_IMGS_PATH);
            if(urls != null && urls.size() != 0) {
                restaurantMapper.saveImgUrls(urls, restaurant.getId());
            }
        }
    }
    
    @Override
    public void saveCooperator(MultipartFile cover, MultipartFile[] imgs, Restaurant restaurant){
    	if(restaurantMapper.getRepeatCount(restaurant)>0){
            throw new BizExecption("饭店名称重复");
        }
    	//上传封面图片
        if(cover != null) {
        	restaurant.setCover(UploadUtils.uploadFile(cover, UploadUtils.RESTURANT_COVER_IMG_PATH));
        }
        restaurant.setType(2);	//设置为合作商家
        restaurantMapper.saveAndGetId(restaurant);
        if(restaurant.getId() != null) {
            // 保存图片
            if(ArrayUtils.isNotEmpty(imgs)) {
                List<String> imgsUrls =  UploadUtils.uploadFiles(imgs, UploadUtils.RESTURANT_IMGS_PATH);
                restaurantMapper.saveImgUrls(imgsUrls, restaurant.getId());
            }
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateById(MultipartFile coverImg, MultipartFile[] images, Restaurant restaurant) {
        if(restaurantMapper.getRepeatCount(restaurant)>0){
            throw new BizExecption("饭店名称重复");
        }
        if(coverImg != null) {
            restaurant.setCover(UploadUtils.uploadFile(coverImg, UploadUtils.RESTURANT_COVER_IMG_PATH));
        }
        super.updateById(restaurant);
        if(ArrayUtils.isNotEmpty(images)) {
            List<String> urls = UploadUtils.uploadFiles(images, UploadUtils.RESTURANT_IMGS_PATH);
            if(urls != null && urls.size() != 0) {
                restaurantMapper.deleteImgUrlsByRestaurantId(restaurant.getId());
                restaurantMapper.saveImgUrls(urls, restaurant.getId());
            }
        }
    }
    
    
    public List<RestaurantInfo> findRestaurantInfo(String lng, String lat, Integer sort, Integer stars, Integer startPrice, Integer endPrice,String search,Long stationId) {
        List<RestaurantInfo> restaurantInfos = new ArrayList<>();
        List<Restaurant> restaurants =  restaurantMapper.findRestaurant(sort,stars,startPrice,endPrice,search,stationId);
        StringBuffer origins = new StringBuffer();
        StringBuffer destination = new StringBuffer();
        destination.append(lng).append(",").append(lat);
        for(Restaurant restaurant :restaurants){
            if(restaurant.getLongtitude() == null||restaurant.getLongtitude() ==null){
                origins.append(0).append(",").append(0).append("|");
            }else{
                origins.append(restaurant.getLongtitude()).append(",").append(restaurant.getLatitude()).append("|");
            }
        }
        List<Double> distances = GaodeUtil.getDistances(origins.toString(), destination.toString(), 1);
        for(int i=0;i<restaurants.size();i++){
        	if(distances.size()==0){
        		Restaurant restaurant = restaurants.get(i);
                RestaurantInfo restaurantInfo = new RestaurantInfo();
                restaurantInfo.setRestaurantId(restaurant.getId());
                restaurantInfo.setRestaurantName(restaurant.getName());
                restaurantInfo.setStationName(restaurant.getStationName());
                restaurantInfo.setMinConsume(restaurant.getMinimun());
                restaurantInfo.setDistance(0);
                restaurantInfo.setType(restaurant.getType());
                restaurantInfo.setStars(restaurant.getStars());
                restaurantInfo.setCoverImg(ImageURLUtil.fillPath(restaurant.getCover()));
                restaurantInfos.add(restaurantInfo);
        	}else{
	            double distance = distances.get(i);
	            if(distance>0){
	                Restaurant restaurant = restaurants.get(i);
	                RestaurantInfo restaurantInfo = new RestaurantInfo();
	                restaurantInfo.setRestaurantId(restaurant.getId());
	                restaurantInfo.setRestaurantName(restaurant.getName());
	                restaurantInfo.setStationName(restaurant.getStationName());
	                restaurantInfo.setMinConsume(restaurant.getMinimun());
	                restaurantInfo.setDistance(distance/1000);
	                restaurantInfo.setType(restaurant.getType());
	                restaurantInfo.setStars(restaurant.getStars());
	                restaurantInfo.setCoverImg(ImageURLUtil.fillPath(restaurant.getCover()));
	                restaurantInfos.add(restaurantInfo);
	            }
        	}
        }
        if(sort ==1){
            Collections.sort(restaurantInfos);
        }
        return restaurantInfos;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IRestaurantService#changeStatus(tech.youmu.ckl.domain.Restaurant)
     */
    @Override
    public void changeStatus(Restaurant restaurant) {
        restaurantMapper.changeStatus(restaurant);
    }


    @Override
    public List<Restaurant> findRestaurantsSimpleInfo() {
        return restaurantMapper.findRestaurantsSimpleInfo();
    }

    @Override
    public RestaurantDetailInfo getRestaurantDetailInfo(Long restaurantId) {
        RestaurantDetailInfo restaurantDetailInfo =  restaurantMapper.getRestaurantDetailInfo(restaurantId);
        if(restaurantDetailInfo == null){
        	throw new BizExecption("没有该饭店");
        }
        List<RestaurantComboInfo> restaurantComboInfos = restaurantDetailInfo.getComboInfos();
        for(RestaurantComboInfo restaurantComboInfo:restaurantComboInfos){
            restaurantComboInfo.setComboCoverImg(ImageURLUtil.fillPath(restaurantComboInfo.getComboCoverImg()));
        }
        restaurantDetailInfo.setCoverImg(ImageURLUtil.fillPath(restaurantDetailInfo.getCoverImg()));
        restaurantDetailInfo.setRestaurantImgs(ImageURLUtil.fillPath(restaurantDetailInfo.getRestaurantImgs()));
        return restaurantDetailInfo;
    }

}
