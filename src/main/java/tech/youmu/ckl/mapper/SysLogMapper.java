/**
 * Project Name:ckl
 * File Name:SysLogMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import tech.youmu.ckl.domain.SysLog;

/**
 * <p>Title:SysLogMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月23日下午4:44:41</p>
 * <p>Description:系统日志的数据操作</p>
 */
public interface SysLogMapper {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月23日下午4:45:53;</p>
     *	<p>Description: 插入一条日志到数据库;</p>
     *  @param log
     */
    public void save(SysLog log);
    
}
