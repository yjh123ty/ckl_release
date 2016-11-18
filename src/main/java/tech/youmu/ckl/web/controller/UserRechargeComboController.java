/**
 * Project Name:ckl
 * File Name:RechargeComboController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.RechargeCombo;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IRechargeComboService;

/**
 * <p>Title:RechargeComboController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月20日下午2:55:00</p>
 * <p>Description:用户充值套餐管理</p>
 */
@Controller
@RequestMapping("/rechargeCombo")
public class UserRechargeComboController {
    
    @Autowired
    private IRechargeComboService rechargeComboService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月20日下午2:56:08;</p>
     *	<p>Description: 用户充值套餐导航;</p>
     *  @return
     */
    @RequestMapping("/index.do")
    public String index(String cmd,Long id,Model model){
        if("save".equals(cmd)){
            return "userRechargeCombo/edit";
        }
        if("update".equals(cmd)){
            model.addAttribute("rechargeCombo", rechargeComboService.getById(id));
            return "userRechargeCombo/edit";
        }
        return "userRechargeCombo/list";
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月20日下午2:56:27;</p>
     *	<p>Description: 用户充值套餐列表;</p>
     *  @param query
     *  @param beginTimeStr
     *  @param endTimeStr
     *  @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<RechargeCombo> list(BaseQuery query){
        return rechargeComboService.getPageList(query);
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月20日下午4:15:36;</p>
     *	<p>Description: 编辑用户充值套餐;</p>
     *  @param rechargeCombo
     *  @return
     */
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(RechargeCombo rechargeCombo){
        try{
                if(rechargeCombo.getId()==null){
                    //添加
                    rechargeComboService.save(rechargeCombo);
                    return AjaxResult.success("添加成功");
                }else{
                    //修改
                    rechargeComboService.updateById(rechargeCombo);
                    return AjaxResult.success("修改成功");
                }
        }catch(Exception e) {
            logger.error("零部件分类编辑异常：", e);
            return AjaxResult.fail(e.getMessage());
        }
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月20日下午4:20:44;</p>
     *	<p>Description: 删除用户充值套餐;</p>
     *  @param id
     *  @return
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public AjaxResult delete(Long id){
        AjaxResult result= null;
        try{
                if(id!=null){
                    rechargeComboService.deleteById(id);
                    result = AjaxResult.success("删除成功！");
                }else{
                    result = AjaxResult.fail("未找到该用户充值套餐！");
                }
        }catch(Exception e) {
            logger.error("零部件分类删除异常：", e);
            result = AjaxResult.fail(e.getMessage());
        }
        return result;
    }
    
}
