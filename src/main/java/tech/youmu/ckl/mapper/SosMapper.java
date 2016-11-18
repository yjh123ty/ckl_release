/**
 * Project Name:ckl
 * File Name:AdminMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.Sos;
import tech.youmu.ckl.query.BaseQuery;

/**
 * 
 * <p>Title:SosMapper</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年10月19日下午5:23:14</p>
 * <p>Description:TODO</p>
 */
public interface SosMapper extends BaseMapper<Sos> {

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月20日下午3:32:41;</p>
     *	<p>Description: 查询未被处理的SOS;</p>
     *  @param query
     */
    List<Sos> getByEmployeeIsNull(BaseQuery query);
    
    long getCountByEmployeeIsNull(BaseQuery query);


}
