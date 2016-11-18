/**
 * Project Name:ckl
 * File Name:ServiceDetailMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import tech.youmu.ckl.domain.ServiceDetail;

/**
 * <p>Title:ServiceDetailMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月2日下午3:51:22</p>
 * <p>Description:服务项的数据访问接口</p>
 */
public interface ServiceDetailMapper {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月2日下午3:53:01;</p>
     *	<p>Description:  保存一项服务的明细项;</p>
     */
    public void save(ServiceDetail detail);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月2日下午3:54:01;</p>
     *	<p>Description: 删除一个明细项;</p>
     */
    public void deleteById(Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月2日下午3:54:01;</p>
     *  <p>Description: 更新一个明细项;</p>
     */
    public void updateById(ServiceDetail detail);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月2日下午3:54:01;</p>
     *  <p>Description: 获取细项;</p>
     */
    public ServiceDetail getById(Long id);
}
