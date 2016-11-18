/**
 * Project Name:ckl
 * File Name:SalaryInflationRateController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Department;
import tech.youmu.ckl.domain.SalaryInflationRate;
import tech.youmu.ckl.service.ISalaryInflationRateService;

/**
 * <p>Title:SalaryInflationRateController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月9日下午4:10:30</p>
 * <p>Description:薪值点</p>
 */
@Controller
@RequestMapping("/salaryInflationRate")
public class SalaryInflationRateController {
    
    @Autowired
    private ISalaryInflationRateService salaryInflationRateService;
    
    @RequestMapping("/index.do")
    public String index(String cmd, Long id, Model model){
        // 跳转到编辑页面
        if(StringUtils.equals("save", cmd)) {
            return "salary/salaryInflationRate_edit";
        }
        
        // 获取数据跳转到编辑页面
        if(StringUtils.equals("update", cmd)) {
            model.addAttribute("salaryInflationRate", salaryInflationRateService.getById(id));
            return "salary/salaryInflationRate_edit";
        }
        
        return "salary/salaryInflationRate";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public List<SalaryInflationRate> list(){
        return salaryInflationRateService.getAll();
    }
    
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(SalaryInflationRate salaryInflationRate){
        AjaxResult result = null;
        try{
            if(salaryInflationRate.getId()==null){
                //添加
                salaryInflationRateService.save(salaryInflationRate);
                result = AjaxResult.success("添加成功");
            }else{
                //修改
                salaryInflationRateService.updateById(salaryInflationRate);
                result = AjaxResult.success("修改成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("服务异常！" , 200);
        }
        return result;
    }
}
