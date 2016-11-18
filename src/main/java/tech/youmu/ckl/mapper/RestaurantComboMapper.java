/**
 * Project Name:ckl
 * File Name:RestaurantComboMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.app.vo.RestaurantComboDetailInfo;
import tech.youmu.ckl.domain.RestaurantCombo;
import tech.youmu.ckl.domain.RestaurantDishes;

/**
 * <p>Title:RestaurantComboMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月14日上午10:27:53</p>
 * <p>Description:饭店套餐数据接口</p>
 */
public interface RestaurantComboMapper  extends BaseMapper<RestaurantCombo>{

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月14日上午10:29:06;</p>
     *	<p>Description: 根据饭店的id获取饭店的套餐列表;</p>
     *  @param id 饭店的id
     *  @return
     */
    List<RestaurantCombo> findRestaurantCombos(Long id);


    RestaurantCombo getRestaurantComboById(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午3:32:20;</p>
     *	<p>Description: 套餐的菜品改变后,重新设置该套餐的价格;</p>
     *  @param comboId
     *  @return
     */
    int recountPrice(Long comboId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月20日下午5:52:43;</p>
     *	<p>Description: TODO;</p>
     *  @param comboId
     *  @return
     */
    RestaurantComboDetailInfo getRestaurantComboDetailInfo(Long comboId);
    
    int getRepeatCount(RestaurantCombo combo);
}
