/**
 * Project Name:ckl
 * File Name:IEmployeeAttendanceService.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.app.vo.TodayAttendanceInfo;
import tech.youmu.ckl.domain.EmployeeAttendance;
import tech.youmu.ckl.query.EmployeeAttendanceQuery;
import tech.youmu.ckl.query.PageList;

/**
 * <p>Title:IEmployeeAttendanceService</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月10日下午3:04:49</p>
 * <p>Description:TODO</p>
 */
public interface IEmployeeAttendanceService extends IBaseService<EmployeeAttendance>{
    /**
     * 获取当月某位员工的打卡明细记录
     */
//    public List<EmployeeAttendance> getList(EmployeeAttendanceQuery query);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月17日下午2:38:47;</p>
     *	<p>Description: TODO;</p>
     *  @param employeeId
     *  @return
     */
    public List<TodayAttendanceInfo> findTodayAttendanceInfo(Long employeeId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月17日下午3:00:28;</p>
     *	<p>Description: TODO;</p>
     *  @param employeeId
     *  @param lng
     *  @param lat
     */
    public void saveTodayAttendance(Long employeeId, String lng, String lat);
}
