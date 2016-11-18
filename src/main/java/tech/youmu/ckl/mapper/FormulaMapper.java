/**
 * Project Name:ckl
 * File Name:FormulaMapper.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.Formula;
import tech.youmu.ckl.query.FormulaQuery;

/**
 * <p>Title:FormulaMapper</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月23日下午6:17:16</p>
 * <p>Description:TODO</p>
 */
public interface FormulaMapper extends BaseMapper<Formula>{
    List<Formula> getInfoByQuery(FormulaQuery formulaQuery);
}
