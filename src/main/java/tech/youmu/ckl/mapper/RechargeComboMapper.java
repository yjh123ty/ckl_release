package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.PresentServiceInfo;
import tech.youmu.ckl.app.vo.PromotionCodeInfo;
import tech.youmu.ckl.app.vo.RechargeComboInfo;
import tech.youmu.ckl.domain.RechargeCombo;

public interface RechargeComboMapper extends BaseMapper<RechargeCombo> {

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月11日上午11:37:07;</p>
     *	<p>Description: 套餐列表;</p>
     *  @return
     */
    List<RechargeComboInfo> findRechargeComboInfo();

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月11日下午2:23:00;</p>
     *	<p>Description: TODO;</p>
     *  @param rechargeComboId
     *  @return
     */
    List<PresentServiceInfo> findPresentServiceInfoByRechargeComboId(Long rechargeComboId);

    List<PresentServiceInfo> findPresentServiceInfoByCode(String code);

    RechargeCombo getRechargeComboByCode(String code);

    List<PromotionCodeInfo> findPromotionCodeInfo(@Param("code")String code);

    RechargeComboInfo getRechargeComboInfoByPromotionCode(@Param("code")String code);

    PromotionCodeInfo getPromotionCodeInfo(Long userId, Long rechargeComboId);

}