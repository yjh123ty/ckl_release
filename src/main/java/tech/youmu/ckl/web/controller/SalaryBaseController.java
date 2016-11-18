/**
 * Project Name:ckl
 * File Name:SalaryBaseController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.SalaryBase;
import tech.youmu.ckl.domain.SalaryInflationRate;
import tech.youmu.ckl.service.ISalaryBaseService;

/**
 * <p>Title:SalaryBaseController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月30日下午5:25:13</p>
 * <p>Description:基础工资标准</p>
 */
@Controller
@RequestMapping("/salaryBase")
public class SalaryBaseController {
    
    @Autowired
    private ISalaryBaseService salaryBaseService;
    
    @RequestMapping("/index.do")
    public String index(String cmd,Long id,Model model){
        if(StringUtils.equals(cmd, "update")){
            model.addAttribute("salaryBase", salaryBaseService.getById(id));
            return "salary/salaryBase_edit";
        }
        return "salary/salaryBase";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public List<SalaryBase> list(){
        return salaryBaseService.getAll();
    }
    
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(SalaryBase salaryBase){
        AjaxResult result = null;
        try{
            if(salaryBase.getId()==null){
                result = AjaxResult.fail("未找到对应记录！");
            }else{
                //修改
                salaryBaseService.updateById(salaryBase);
                result = AjaxResult.success("修改成功！");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("服务异常！" , 200);
        }
        return result;
    }
}
