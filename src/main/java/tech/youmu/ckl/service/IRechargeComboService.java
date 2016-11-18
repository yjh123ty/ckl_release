/**
 * Project Name:ckl
 * File Name:IRestaurantComboService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.app.vo.PresentServiceInfo;
import tech.youmu.ckl.app.vo.PromotionCodeInfo;
import tech.youmu.ckl.app.vo.RechargeComboInfo;
import tech.youmu.ckl.domain.RechargeCombo;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;

/**
 * 
 * <p>Title:IRechargeComboService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年10月11日上午11:30:21</p>
 * <p>Description:TODO</p>
 */
public interface IRechargeComboService extends IBaseService<RechargeCombo>{

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月11日上午11:32:39;</p>
     *	<p>Description: 套餐信息;</p>
     *  @return
     */
    List<RechargeComboInfo> findRechargeComboInfo();

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月11日下午2:22:33;</p>
     *	<p>Description: TODO;</p>
     *  @param rechargeComboId
     *  @return
     */
    List<PresentServiceInfo> findPresentServiceInfoByRechargeComboId(Long rechargeComboId);

    List<PresentServiceInfo> findPresentServiceInfoByPromotionCode(String promotionCode);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月11日下午6:09:36;</p>
     *	<p>Description: 推广套餐;</p>
     *  @param userId
     *  @return
     */
    List<PromotionCodeInfo> findPromotionCodeInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月19日下午4:22:09;</p>
     *	<p>Description: TODO;</p>
     *  @param promotionCode
     *  @return
     */
    RechargeComboInfo getRechargeComboInfoByPromotionCode(String promotionCode);


    PromotionCodeInfo getPromotionCodeInfo(Long userId, Long rechargeComboId);
}
