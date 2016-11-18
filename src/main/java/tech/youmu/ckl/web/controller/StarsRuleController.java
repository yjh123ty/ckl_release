/**
 * Project Name:ckl
 * File Name:StarsRuleController.java
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
import tech.youmu.ckl.domain.StarsRule;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IStarsRuleService;

/**
 * <p>Title:StarsRuleController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月25日上午11:37:48</p>
 * <p>Description:获得评分产生分数规则管理</p>
 */
@Controller
@RequestMapping("/starsRule")
public class StarsRuleController {
    
    @Autowired
    private IStarsRuleService starsRuleService;
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月25日下午1:14:58;</p>
     *	<p>Description: 向导页面;</p>
     *  @return
     */
    @RequestMapping("/index.do")
    public String index(String cmd,Long id,Model model){
        //跳转到编辑界面
        if(StringUtils.equals("save", cmd)) {
            return "starsRule/starsRule_edit";
        }
        
        if(StringUtils.equals("update", cmd)) {
            //根据id获取规则
            model.addAttribute("starsRule", starsRuleService.getById(id));
            return "starsRule/starsRule_edit";
        }
        return "starsRule/starsRule";
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月25日下午1:27:34;</p>
     *	<p>Description: 列表;</p>
     *  @param baseQuery
     *  @param beginTimeStr
     *  @param endTimeStr
     *  @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<StarsRule> List(BaseQuery baseQuery,String beginTimeStr,String endTimeStr){
        baseQuery.setBeginTimeStr(beginTimeStr);
        baseQuery.setEndTimeStr(endTimeStr);
        return starsRuleService.getPageList(baseQuery); 
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月25日下午1:17:21;</p>
     *	<p>Description: 保存/修改;</p>
     *  @return
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AjaxResult save(StarsRule starsRule){
        AjaxResult result= null;
        try{
            if(starsRule.getId()==null){
                //新增一条数据，记录录入时间
                starsRule.setCreateTime(new Date());
                //添加
                starsRuleService.save(starsRule);
                result = AjaxResult.success("添加成功");
            }else{
                //修改
                starsRuleService.updateById(starsRule);
                result = AjaxResult.success("修改成功");
            }
        }catch(Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("服务异常！" , 200);
        }
        return result;
    }
    
    
}
