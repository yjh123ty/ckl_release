/**
 * Project Name:ckl
 * File Name:EmployeeAttendanceServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.app.vo.TodayAttendanceInfo;
import tech.youmu.ckl.domain.EmployeeAttendance;
import tech.youmu.ckl.domain.Station;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.EmployeeAttendanceMapper;
import tech.youmu.ckl.mapper.StationMapper;
import tech.youmu.ckl.query.EmployeeAttendanceQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IEmployeeAttendanceService;
import tech.youmu.ckl.utils.DateUtil;
import tech.youmu.ckl.utils.GaodeUtil;

/**
 * <p>Title:EmployeeAttendanceServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月10日下午3:05:59</p>
 * <p>Description:TODO</p>
 */
@Service
public class EmployeeAttendanceServiceImpl extends BaseServiceImpl<EmployeeAttendance> implements IEmployeeAttendanceService{

    private EmployeeAttendanceMapper employeeAttendanceMapper;
    
    @Autowired
    private StationMapper stationMapper;
    
    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月10日下午3:06:12;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public EmployeeAttendanceServiceImpl(EmployeeAttendanceMapper employeeAttendanceMapper) {
        super(employeeAttendanceMapper);
        this.employeeAttendanceMapper = employeeAttendanceMapper;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IEmployeeAttendanceService#getDetailsByEmpIdAndRecordMonth(tech.youmu.ckl.query.EmployeeAttendanceQuery)
     */
//    @Override
//    public PageList<EmployeeAttendance> getList(EmployeeAttendanceQuery query) {
//        return employeeAttendanceMapper.getDetailsByEmpIdAndRecordMonth(query);
//    }

    @Override
    public List<TodayAttendanceInfo> findTodayAttendanceInfo(Long employeeId) {
        return employeeAttendanceMapper.findTodayAttendanceInfo(employeeId,DateUtil.getDate());
    }

    @Override
    public void saveTodayAttendance(Long employeeId, String lng, String lat) {
        StringBuffer origins = new StringBuffer();
        StringBuffer destination = new StringBuffer();
        destination.append(lng).append(",").append(lat);
        List<Station> stations = stationMapper.getAll();
        for(Station station :stations){
            if(station.getLongtitude() == null||station.getLongtitude() ==null){
                origins.append(0).append(",").append(0).append("|");
            }else{
                origins.append(station.getLongtitude()).append(",").append(station.getLatitude()).append("|");
            }
        }
        List<Double> distances = GaodeUtil.getDistances(origins.toString(), destination.toString(), 1);
        double minDistance=0;
        long stationId = 0;
        for(int i=0;i<distances.size();i++){
            double distance = distances.get(i);
            Station station = stations.get(i);
            if(minDistance ==0){
                minDistance = distance;
                stationId = station.getId();
            }else if(minDistance!=0&&distance!=0&&distance<minDistance){
                minDistance = distance;
                stationId = station.getId();
            }
        }
        if(minDistance ==0||minDistance<EmployeeAttendance.RADII_DISTANCE){
            throw new BizExecption("未进入打卡区域，请进入打卡区域后再打卡");
        }
        if(employeeAttendanceMapper.isTodayAttendance(employeeId,stationId,DateUtil.getDate())){
            throw new BizExecption("你今日已经在该服务站打卡啦");
        }
        employeeAttendanceMapper.save(new EmployeeAttendance(employeeId,stationId));

        
        
        
    }

}
