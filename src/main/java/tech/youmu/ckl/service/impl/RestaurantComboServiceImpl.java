/**
 * Project Name:ckl
 * File Name:IRestaurantComboService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.RestaurantComboDetailInfo;
import tech.youmu.ckl.app.vo.RestaurantComboDishInfo;
import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.RestaurantCombo;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.RestaurantComboMapper;
import tech.youmu.ckl.service.IRestaurantComboService;
import tech.youmu.ckl.utils.ImageURLUtil;
import tech.youmu.ckl.utils.UploadUtils;

/**
 * <p>Title:IRestaurantComboService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月14日上午10:17:51</p>
 * <p>Description:饭店套餐的服务接口</p>
 */
@Service
public class RestaurantComboServiceImpl  extends BaseServiceImpl<RestaurantCombo> implements IRestaurantComboService {

    private RestaurantComboMapper restaurantComboMapper;

    @Autowired
    public RestaurantComboServiceImpl(RestaurantComboMapper restaurantComboMapper) {
        super(restaurantComboMapper);
        this.restaurantComboMapper = restaurantComboMapper;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IRestaurantComboService#getRestaurantCombos(java.lang.Long)
     */
    @Override
    public List<RestaurantCombo> findRestaurantCombos(Long id) {
        return restaurantComboMapper.findRestaurantCombos(id);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IRestaurantComboService#save(org.springframework.web.multipart.MultipartFile, tech.youmu.ckl.domain.RestaurantCombo)
     */
    @Override
    public void save(MultipartFile image, RestaurantCombo combo) {
        if(image != null && image.getSize() > 0) {
            combo.setImg(UploadUtils.uploadFile(image, UploadUtils.RESTURANT_COMBO_IMGS_PATH));
        }
        super.save(combo);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IRestaurantComboService#updateById(org.springframework.web.multipart.MultipartFile, tech.youmu.ckl.domain.RestaurantCombo)
     */
    @Override
    public void updateById(MultipartFile image, RestaurantCombo combo) {
        if(image != null && image.getSize() > 0) {
            combo.setImg(UploadUtils.uploadFile(image, UploadUtils.RESTURANT_COMBO_IMGS_PATH));
        }
        super.updateById(combo);
    }

    @Override
    public RestaurantComboDetailInfo getRestaurantComboDetailInfo(Long comboId) {
        RestaurantComboDetailInfo restaurantComboDetailInfo = restaurantComboMapper.getRestaurantComboDetailInfo(comboId);
        if(restaurantComboDetailInfo == null){
            throw new BizExecption("没有该套餐");
        }
        restaurantComboDetailInfo.setComboCoverImg(ImageURLUtil.fillPath(restaurantComboDetailInfo.getComboCoverImg()));
        restaurantComboDetailInfo.setRestaurantcoverImg(ImageURLUtil.fillPath(restaurantComboDetailInfo.getRestaurantcoverImg()));
        for(RestaurantComboDishInfo restaurantComboDishInfo:restaurantComboDetailInfo.getComboDishInfos()){
            restaurantComboDishInfo.setDishImg(ImageURLUtil.fillPath(restaurantComboDishInfo.getDishImg()));
        }
        return restaurantComboDetailInfo;
    }


}
