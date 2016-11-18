/**
 * Project Name:ckl
 * File Name:SysLogServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.SysLog;
import tech.youmu.ckl.mapper.SysLogMapper;
import tech.youmu.ckl.service.ISysLogService;

/**
 * <p>Title:SysLogServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月23日下午5:59:50</p>
 * <p>Description:系统日志服务实现类</p>
 */
@Service
public class SysLogServiceImpl implements ISysLogService {
    
    @Autowired
    private SysLogMapper sysLogMapper;
    

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ISysLogService#save(tech.youmu.ckl.domain.SysLog)
     */
    @Override
    public void save(SysLog log) {
        sysLogMapper.save(log);
    }
}
