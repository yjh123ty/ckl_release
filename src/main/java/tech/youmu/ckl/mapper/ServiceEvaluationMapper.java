/**
 * Project Name:ckl
 * File Name:ServiceEvaluationMapper.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.mapper;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.OrderEvaluation;
import tech.youmu.ckl.domain.ServiceEvaluation;

/**
 * <p>Title:ServiceEvaluationMapper</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月30日上午10:34:07</p>
 * <p>Description:TODO</p>
 */
public interface ServiceEvaluationMapper extends BaseMapper<ServiceEvaluation>{

    OrderEvaluation getByOrderId(Long orderId);

}
