/**
 * Project Name:ckl
 * File Name:ISysLogService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import tech.youmu.ckl.domain.SysLog;

/**
 * <p>Title:ISysLogService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月23日下午5:58:59</p>
 * <p>Description:日志服务</p>
 */
public interface ISysLogService {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月23日下午5:59:24;</p>
     *	<p>Description: 保存一条日志;</p>
     *  @param log
     */
    public void save(SysLog log);
}
