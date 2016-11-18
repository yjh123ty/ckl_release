/**
 * Project Name:ckl
 * File Name:RestaurantDishes.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.RestaurantDishes;

/**
 * <p>Title:RestaurantDishes</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月18日下午2:08:44</p>
 * <p>Description:菜品数据访问接口</p>
 */
public interface RestaurantDishesMapper extends BaseMapper<RestaurantDishes> {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午2:22:37;</p>
     *	<p>Description: 根据套餐的id获取所有套餐的菜品</p>
     *  @param id 套餐id
     *  @return 套餐菜品列表
     */
    public List<RestaurantDishes> getDishesByComboId(Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月21日下午1:32:21;</p>
     *	<p>Description: 保存套餐的一种菜品信息;</p>
     *  @param comboId
     *  @param dishesId
     *  @param num
     */
    public void saveComboDishes(@Param("comboId") Long comboId, @Param("dishesId") Long dishesId,  @Param("num") Integer num);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月21日下午1:33:03;</p>
     *	<p>Description: 修改套餐的一种菜品信息;</p>
     *  @param comboId
     *  @param dishesId
     *  @param num
     */
    public void updateComboDishes(@Param("comboId") Long comboId, @Param("dishesId") Long dishesId,  @Param("num") Integer num);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月21日下午1:33:41;</p>
     *	<p>Description: 删除一个套餐下的某个菜品;</p>
     *  @param comboId
     *  @param dishesId
     */
    public void deleteComboDishes(@Param("comboId") Long comboId, @Param("dishesId") Long dishesId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月21日下午2:08:48;</p>
     *	<p>Description: 查询不在套餐中的饭店菜品;</p>
     *  @param restaurantId
     *  @param comboId
     *  @return
     */
    public List<RestaurantDishes> findNotInComboDishes(@Param("restaurantId") Long restaurantId, @Param("comboId") Long comboId);
    
    int getRepeatCount(RestaurantDishes dishes);

}
