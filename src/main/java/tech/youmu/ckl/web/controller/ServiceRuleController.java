/**
 * Project Name:ckl
 * File Name:ServiceRuleController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.ServiceRule;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IServiceRuleService;

/**
 * <p>Title:ServiceRuleController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月24日下午4:32:10</p>
 * <p>Description:服务单数产生分数规则</p>
 */
@Controller
@RequestMapping("/serviceRule")
public class ServiceRuleController {
    
    @Autowired
    private IServiceRuleService serviceRuleService;
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月24日下午4:49:19;</p>
     *	<p>Description:向导页面;</p>
     *  @return
     */
    @RequestMapping("index.do")
    public String index(String cmd,Long id,Model model){
        //跳转到编辑界面
        if(StringUtils.equals("save", cmd)) {
            return "pointRule/serviceRule_edit";
        }
        
        if(StringUtils.equals("update", cmd)) {
            //根据id获取规则
            model.addAttribute("serviceRule", serviceRuleService.getById(id));
            return "pointRule/serviceRule_edit";
        }
        return "pointRule/serviceRule";
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月24日下午4:51:17;</p>
     *	<p>Description: 列表展示;</p>
     *  @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public PageList<ServiceRule> list(BaseQuery baseQuery,String beginTimeStr,String endTimeStr){
        baseQuery.setBeginTimeStr(beginTimeStr);
        baseQuery.setEndTimeStr(endTimeStr);
        return serviceRuleService.getPageList(baseQuery);
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月25日上午10:07:30;</p>
     *	<p>Description: 保存/修改;</p>
     *  @return
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AjaxResult save(ServiceRule serviceRule){
        AjaxResult result= null;
        try{
            if(serviceRule.getId()==null){
                //新增一条数据，记录录入时间
                serviceRule.setCreateTime(new Date());
                //添加
                serviceRuleService.save(serviceRule);
                result = AjaxResult.success("添加成功");
            }else{
                //修改
                serviceRuleService.updateById(serviceRule);
                result = AjaxResult.success("修改成功");
            }
        }catch(Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("服务异常！" , 200);
        }
        return result;
    }
    
    
}
