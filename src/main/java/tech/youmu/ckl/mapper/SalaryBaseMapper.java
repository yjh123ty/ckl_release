/**
 * Project Name:ckl
 * File Name:SalaryBaseMapper.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.mapper;

import tech.youmu.ckl.domain.SalaryBase;

/**
 * <p>Title:SalaryBaseMapper</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月30日下午4:57:41</p>
 * <p>Description:基础工资标准表</p>
 */
public interface SalaryBaseMapper extends BaseMapper<SalaryBase>{

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月12日下午5:05:46;</p>
     *	<p>Description: 根据订单总量获取对应的日工资;</p>
     *  @param orderNumCondition
     *  @return
     */
    SalaryBase getSalaryBaseByOrderNum(Integer orderNumCondition);

}
