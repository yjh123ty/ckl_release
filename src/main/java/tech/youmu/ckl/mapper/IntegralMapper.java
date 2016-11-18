package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.IntegralDetailInfo;
import tech.youmu.ckl.app.vo.IntegralMonthInfo;
import tech.youmu.ckl.domain.IntegralLevelRule;

public interface IntegralMapper extends BaseMapper{

    List<IntegralMonthInfo> findIntegralMonthInfo(Long userId);
	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年9月8日上午9:55:14;</p>
	 *	<p>Description: 下一个等级的积分;</p>
	 *  @param integral
	 *  @return
	 */
    int getNextLevelIntegral(Integer integral);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月8日上午10:23:55;</p>
     *	<p>Description: 根据等级获取对应的积分;</p>
     *  @param level
     *  @return
     */
    int getIntegral(String level);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月8日上午10:45:32;</p>
     *	<p>Description: 获取积分等级;</p>
     *  @param string
     *  @return
     */
    IntegralLevelRule getIntegralLevelRule(Integer integral);

   
}