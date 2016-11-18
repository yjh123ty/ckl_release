/**
 * Project Name:ckl
 * File Name:SalaryPointStandardController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.SalaryBase;
import tech.youmu.ckl.domain.SalaryPointStandard;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.SalaryPointStandardQuery;
import tech.youmu.ckl.service.ISalaryPointStandardService;

/**
 * <p>Title:SalaryPointStandardController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月9日下午2:46:23</p>
 * <p>Description:TODO</p>
 */
@Controller
@RequestMapping("/salaryPointStandard")
public class SalaryPointStandardController {
    
    @Autowired
    private ISalaryPointStandardService salaryPointStandardService;

    @RequestMapping("/index.do")
    public String index(){
        return "salary/salaryPointStandard";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<SalaryPointStandard> list(SalaryPointStandardQuery query){
        return salaryPointStandardService.getPageList(query);
    }
    
}
