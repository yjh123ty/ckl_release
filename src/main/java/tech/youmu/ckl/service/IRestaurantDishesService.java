/**
 * Project Name:ckl
 * File Name:IRestaurantDishesService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.RestaurantDishes;

/**
 * <p>Title:IRestaurantDishesService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月18日下午2:23:44</p>
 * <p>Description:饭店套餐菜品服务接口</p>
 */
public interface IRestaurantDishesService extends IBaseService<RestaurantDishes>{
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月20日下午12:00:08;</p>
     *	<p>Description: 向套餐里面添加一定数量的一种菜品,菜品添加完成后 需要重新计算套餐的总价;</p>
     *  @param comboId
     *  @param dishesId
     *  @param num
     */
    public void saveComboDishes(Long comboId, Long dishesId, Integer num);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月20日下午12:00:08;</p>
     *  <p>Description: 修改套餐里一定数量的一种菜品,菜品修改完成后 需要重新计算套餐的总价;</p>
     *  @param comboId
     *  @param dishesId
     *  @param num
     */
    public void updateComboDishes(Long comboId, Long dishesId, Integer num);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月20日下午12:00:08;</p>
     *  <p>Description: 删除套餐里一种菜品,菜品修删除成后 需要重新计算套餐的总价;</p>
     *  @param comboId
     *  @param dishesId
     *  @param num
     */
    public void deleteComboDishes(Long comboId, Long dishesId);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午2:25:59;</p>
     *	<p>Description: 根据套餐的Id获取套餐的菜品列表;</p>
     *  @param id
     *  @return
     */
    public List<RestaurantDishes> getDishesByComboId(Long comboId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月21日上午10:10:19;</p>
     *	<p>Description: 保存菜品;</p>
     *  @param image
     *  @param restaurantDishes
     */
    public void save(MultipartFile image, RestaurantDishes restaurantDishes);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月21日上午10:10:46;</p>
     *	<p>Description:修改菜品;</p>
     *  @param image
     *  @param restaurantDishes
     */
    public void updateById(MultipartFile image, RestaurantDishes restaurantDishes);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月21日上午11:22:47;</p>
     *	<p>Description: 查询一个套餐下面的所有菜品;</p>
     *  @param comboId
     *  @return
     */
    public List<RestaurantDishes> findDishesByComboId(Long comboId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月21日下午2:07:10;</p>
     *	<p>Description: 查询不在套餐中的饭店菜品;</p>
     *  @param restaurcntId
     *  @return
     */
    public List<RestaurantDishes> findNotInComboDishes(Long restaurcntId, Long comboId);
}
