/**
 * Project Name:ckl
 * File Name:CarPartTypeController.java
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
import tech.youmu.ckl.domain.CarPartType;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.query.CarPartTypeQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.ICarPartTypeService;
import tech.youmu.ckl.utils.LogUtils;

/**
 * <p>Title:CarPartTypeController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月20日下午2:55:00</p>
 * <p>Description:商品分类管理</p>
 */
@Controller
@RequestMapping("/carPartType")
public class CarPartTypeController {
    
    @Autowired
    private ICarPartTypeService carPartTypeService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月20日下午2:56:08;</p>
     *	<p>Description: 商品分类导航;</p>
     *  @return
     */
    @RequestMapping("/index.do")
    public String index(String cmd,Long id,Model model){
        
      //跳转到编辑界面
        if(StringUtils.equals("save", cmd)) {
            return "carPartType/edit";
        }
        
        if(StringUtils.equals("update", cmd)) {
            //根据id获取规则
            CarPartType byId = carPartTypeService.getById(id);
            LogUtils.getInstance(getClass()).debugBean(byId);
            model.addAttribute("carPartType", byId);
            return "carPartType/edit";
        }
        
        return "carPartType/list";
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月20日下午2:56:27;</p>
     *	<p>Description: 商品分类列表;</p>
     *  @param query
     *  @param beginTimeStr
     *  @param endTimeStr
     *  @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<CarPartType> list(CarPartTypeQuery query){
        return carPartTypeService.getPageList(query);
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月20日下午4:15:36;</p>
     *	<p>Description: 编辑商品分类;</p>
     *  @param carPartType
     *  @return
     */
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(CarPartType carPartType){
        try{
                if(carPartType.getId()==null){
                    //添加
                    carPartTypeService.save(carPartType);
                    return AjaxResult.success("添加成功");
                }else{
                    //修改
                    carPartTypeService.updateById(carPartType);
                    return AjaxResult.success("修改成功");
                }
        }catch(BizExecption e) {
            logger.error("零部件分类编辑异常：", e);
            return AjaxResult.fail(e.getMessage());
        }catch (Exception e) {
            logger.error("零部件分类编辑异常：", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月20日下午4:20:44;</p>
     *	<p>Description: 删除商品分类;</p>
     *  @param id
     *  @return
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public AjaxResult delete(Long id){
        AjaxResult result= null;
        try{
                if(id!=null){
                    carPartTypeService.deleteById(id);
                    result = AjaxResult.success("删除成功！");
                }else{
                    result = AjaxResult.fail("未找到该商品分类！");
                }
        }catch(Exception e) {
            logger.error("零部件分类删除异常：", e);
            result = AjaxResult.fail("系统异常");
        }
        return result;
    }
    
}
