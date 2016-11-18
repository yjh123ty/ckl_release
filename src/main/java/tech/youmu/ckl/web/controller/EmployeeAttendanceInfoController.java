/**
 * Project Name:ckl
 * File Name:EmployeeAttendanceInfoController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.EmployeeAttendanceInfo;
import tech.youmu.ckl.query.EmployeeAttendanceInfoQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IEmployeeAttendanceInfoService;
import tech.youmu.ckl.service.IEmployeeAttendanceService;

/**
 * <p>Title:EmployeeAttendanceInfoController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月9日下午5:39:30</p>
 * <p>Description:员工每月考勤记录</p>
 */
@Controller
@RequestMapping("/employeeAttendanceInfo")
public class EmployeeAttendanceInfoController {
    
    @Autowired
    private IEmployeeAttendanceInfoService employeeAttendanceInfoService;
    
    @RequestMapping("/index.do")
    public String index(){
        return "salary/employeeAttendanceInfo";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<EmployeeAttendanceInfo> pageList(EmployeeAttendanceInfoQuery query,String searchTimeStr){
        query.setSearchTimeStr(searchTimeStr);
        return employeeAttendanceInfoService.getPageList(query);
    }
}
