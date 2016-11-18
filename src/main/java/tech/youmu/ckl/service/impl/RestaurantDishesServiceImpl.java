/**
 * Project Name:ckl
 * File Name:RestaurantDishesServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.RestaurantDishes;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.RestaurantComboMapper;
import tech.youmu.ckl.mapper.RestaurantDishesMapper;
import tech.youmu.ckl.service.IRestaurantDishesService;
import tech.youmu.ckl.utils.UploadUtils;

/**
 * <p>Title:RestaurantDishesServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月18日下午2:27:14</p>
 * <p>Description:饭店菜品图服务实现类</p>
 */
@Service
public class RestaurantDishesServiceImpl extends BaseServiceImpl<RestaurantDishes> implements IRestaurantDishesService {

    private RestaurantDishesMapper restaurantDishesMapper;
    
    @Autowired
    private RestaurantComboMapper restaurantComboMapper;
    
    @Autowired
    public RestaurantDishesServiceImpl(RestaurantDishesMapper restaurantDishesMapper) {
        super(restaurantDishesMapper);
        this.restaurantDishesMapper = restaurantDishesMapper;
    }

    @Override
    public List<RestaurantDishes> getDishesByComboId(Long id) {
        return restaurantDishesMapper.getDishesByComboId(id);
    }

    @Override
    public void saveComboDishes(Long comboId, Long dishesId, Integer num) {
        restaurantDishesMapper.saveComboDishes(comboId, dishesId, num);
        restaurantComboMapper.recountPrice(comboId);
    }

    @Override
    public void updateComboDishes(Long comboId, Long dishesId, Integer num) {
        restaurantDishesMapper.updateComboDishes(comboId, dishesId, num);
        restaurantComboMapper.recountPrice(comboId);
    }

    @Override
    public void deleteComboDishes(Long comboId, Long dishesId) {
        restaurantDishesMapper.deleteComboDishes(comboId, dishesId);
        restaurantComboMapper.recountPrice(comboId);
    }

    @Override
    public void save(MultipartFile image, RestaurantDishes restaurantDishes) {
        if(restaurantDishesMapper.getRepeatCount(restaurantDishes) > 0){
            throw new BizExecption("菜品名称重复");
        }
        if(image != null) {
            restaurantDishes.setImg(UploadUtils.uploadFile(image, UploadUtils.RESTURANT_DISHES_IMG_PATH));
        }
        super.save(restaurantDishes);
        
    }

    @Override
    public void updateById(MultipartFile image, RestaurantDishes restaurantDishes) {
        if(restaurantDishesMapper.getRepeatCount(restaurantDishes) > 0){
            throw new BizExecption("菜品名称重复");
        }
        if(image != null) {
            restaurantDishes.setImg(UploadUtils.uploadFile(image, UploadUtils.RESTURANT_DISHES_IMG_PATH));
        }
        super.updateById(restaurantDishes);
    }

    @Override
    public List<RestaurantDishes> findDishesByComboId(Long comboId) {
        return restaurantDishesMapper.getDishesByComboId(comboId);
    }

    @Override
    public List<RestaurantDishes> findNotInComboDishes(Long restaurantId, Long comboId) {
        return restaurantDishesMapper.findNotInComboDishes(restaurantId, comboId);
    }
}
