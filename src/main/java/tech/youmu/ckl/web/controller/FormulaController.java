/**
 * Project Name:ckl
 * File Name:FormulaController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Formula;
import tech.youmu.ckl.query.FormulaQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IDepartmentService;
import tech.youmu.ckl.service.IFormulaService;
import tech.youmu.ckl.service.IJobTitleService;

/**
 * <p>Title:FormulaController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月23日下午6:00:45</p>
 * <p>Description:员工绩效公式管理</p>
 */
@Controller
@RequestMapping("/formula")
public class FormulaController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IFormulaService formulaService;
    
    @Autowired
    private IDepartmentService departmentService;
    
    @Autowired
    private IJobTitleService jobTitleService;
    
    /**
     * 导向页面
     * @return
     */
    @RequestMapping("/index.do")
    public String index(String cmd, Long id, Model model){
        //获取部门
        model.addAttribute("depts", departmentService.getAll());
        //获取职位
        model.addAttribute("jobs",jobTitleService.getAll());
        
        // 跳转到编辑页面
        if(StringUtils.equals("save", cmd)) {
            return "formula/formula_edit";
        }
        
        // 获取数据跳转到编辑页面
        if(StringUtils.equals("update", cmd)) {
            //获取要修改的数据
            model.addAttribute("formula", formulaService.getById(id));
            return "formula/formula_edit";
        }
        
        //跳转到绩效管理页面
        return "formula/formula";
        
    }
    
    /**
     * 绩效列表
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<Formula> list(FormulaQuery formulaQuery,String beginTimeStr,String endTimeStr) throws Exception{
        formulaQuery.setBeginTime(beginTimeStr);
        formulaQuery.setEndTime(endTimeStr);
        return formulaService.getPageList(formulaQuery);
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月24日上午10:23:55;</p>
     *	<p>Description: 添加/修改绩效公式;</p>
     *  @return
     */
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(Formula formula){
        AjaxResult result= null;
        try{
            if(formula.getId()==null){
                //新增一条数据，记录录入时间
                formula.setCreateTime(new Date());
                //添加
                formulaService.save(formula);
                result = AjaxResult.success("添加成功");
            }else{
                //修改员工
                formulaService.updateById(formula);
                result = AjaxResult.success("修改成功");
            }
        }catch(Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("服务异常！" , 200);
        }
        return result;
    }
}
