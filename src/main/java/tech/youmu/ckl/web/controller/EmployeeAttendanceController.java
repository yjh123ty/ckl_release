/**
 * Project Name:ckl
 * File Name:EmployeeAttendanceController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.EmployeeAttendance;
import tech.youmu.ckl.domain.EmployeeAttendanceInfo;
import tech.youmu.ckl.query.EmployeeAttendanceInfoQuery;
import tech.youmu.ckl.query.EmployeeAttendanceQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IEmployeeAttendanceService;

/**
 * <p>Title:EmployeeAttendanceController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月10日下午3:08:53</p>
 * <p>Description:TODO</p>
 */
@Controller
@RequestMapping("/employeeAttendance")
public class EmployeeAttendanceController {
    
    @Autowired
    private IEmployeeAttendanceService employeeAttendanceService;
    
    @RequestMapping("/index.do")
    public String index(Model model,Long employeeId,String recordMonth){
        EmployeeAttendanceQuery query = new EmployeeAttendanceQuery();
        query.setRecordMonth(recordMonth);
        query.setEmployeeId(employeeId);
        model.addAttribute("recordMonth",recordMonth);
        model.addAttribute("employeeId", employeeId);
        return "salary/employeeAttendance";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<EmployeeAttendance> pageList(EmployeeAttendanceQuery query){
        return employeeAttendanceService.getPageList(query);
    }
    
}
