/**
 * Project Name:ckl
 * File Name:EmployeeAttendanceInfoServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.EmployeeAttendanceInfo;
import tech.youmu.ckl.mapper.BaseMapper;
import tech.youmu.ckl.mapper.EmployeeAttendanceInfoMapper;
import tech.youmu.ckl.service.IEmployeeAttendanceInfoService;

/**
 * <p>Title:EmployeeAttendanceInfoServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月9日下午5:37:13</p>
 * <p>Description:员工每月考勤记录</p>
 */
@Service
public class EmployeeAttendanceInfoServiceImpl extends BaseServiceImpl<EmployeeAttendanceInfo> implements IEmployeeAttendanceInfoService{
    
    private EmployeeAttendanceInfoMapper employeeAttendanceInfoMapper;

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月9日下午5:37:48;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public EmployeeAttendanceInfoServiceImpl(EmployeeAttendanceInfoMapper employeeAttendanceInfoMapper) {
        super(employeeAttendanceInfoMapper);
        this.employeeAttendanceInfoMapper = employeeAttendanceInfoMapper;
    }

}
