/**
 * Project Name:ckl
 * File Name:StarsRuleMapper.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.mapper;

import tech.youmu.ckl.domain.StarsRule;

/**
 * <p>Title:StarsRuleMapper</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月25日下午1:24:54</p>
 * <p>Description:获得评分产生分数规则的数据层接口</p>
 */
public interface StarsRuleMapper extends BaseMapper<StarsRule>{
	/**
	 * 根据星数级别获取对应获得的分数
	 * @param stars 
	 * @return
	 */
	StarsRule getRatioByStars(Double stars);
}
