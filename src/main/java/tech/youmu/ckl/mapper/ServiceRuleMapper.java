/**
 * Project Name:ckl
 * File Name:ServiceRuleMapper.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.mapper;

import tech.youmu.ckl.domain.ServiceRule;

/**
 * <p>Title:ServiceRuleMapper</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月24日下午4:57:30</p>
 * <p>Description:服务单数产生分数规则数据层</p>
 */
public interface ServiceRuleMapper extends BaseMapper<ServiceRule>{
    ServiceRule getPersentByCondition(Integer condition);
}
