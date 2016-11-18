/**
 * Project Name:ckl
 * File Name:RestaurantDishesController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.RestaurantDishes;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IRestaurantDishesService;

/**
 * <p>Title:RestaurantDishesController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月18日下午2:32:50</p>
 * <p>Description:饭店套餐菜品的控制器</p>
 */
@Controller
@RequestMapping("/restaurantdishes")
public class RestaurantDishesController {
    
    @Autowired
    IRestaurantDishesService restaurantDishesService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
  
    @RequestMapping("/index.do")
    public String index(String cmd, Long id, Model model){
        if(StringUtils.equals("save", cmd)) {
            return "restaurant_dishes/restaurant_dishes_edit";
        }
        if(StringUtils.equals("update", cmd)) {
            model.addAttribute("dishes", restaurantDishesService.getById(id));
            return "restaurant_dishes/restaurant_dishes_edit";
        }
        return "restaurant_dishes/restaurant_dishes";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<RestaurantDishes> list(BaseQuery query){
        return restaurantDishesService.getPageList(query);
    }
    
    @RequestMapping("/listByComboId.do")
    @ResponseBody
    public List<RestaurantDishes> findByComboId(Long id){
        if(id == null) {
            return Collections.emptyList();
        }
        return restaurantDishesService.findDishesByComboId(id);
    }
    
    @RequestMapping("/listNotInComboDishes.do")
    @ResponseBody
    public List<RestaurantDishes> findNotInComboDishes(Long restaurantId, Long comboId){
        if(restaurantId == null || comboId == null) {
            return Collections.emptyList();
        }
        return restaurantDishesService.findNotInComboDishes(restaurantId, comboId);
    }
    
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult edit(@RequestParam(value="image",required=false) MultipartFile image, RestaurantDishes restaurantDishes){
        try {
            if(restaurantDishes.getId() == null) {
                if(image == null || image.getSize() == 0) {
                    return AjaxResult.fail("菜品图片不能为空");
                }
                // 添加
                restaurantDishesService.save(image, restaurantDishes);
                return AjaxResult.success("菜品添加成功");
            }else {
                //修改
                restaurantDishesService.updateById(image, restaurantDishes);
                return AjaxResult.success("菜品修改成功");
            }
        } catch (BizExecption e) {
            logger.error("菜品编辑异常:", e);
            return AjaxResult.fail(e.getMessage());
        }catch (Exception e) {
            logger.error("菜品编辑异常:", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
    @RequestMapping("/delete.do")
    @ResponseBody
    public AjaxResult delete(Long id) {
        try {
            if(id != null) {
                restaurantDishesService.deleteById(id);
                return AjaxResult.success("菜品删除成功");
            }else {
                return AjaxResult.fail("参数错误");
            }
        } catch (Exception e) {
            logger.error("菜品编辑异常:", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
    @RequestMapping("/saveComboDishes.do")
    @ResponseBody
    public AjaxResult saveComboDishes(Long comboId, Long dishesId, Integer num){
        // TODO 参数校验
        if(comboId == null || dishesId == null || num == null || num <= 0){
            return AjaxResult.fail("参数异常");
        }
        try {
            restaurantDishesService.saveComboDishes(comboId, dishesId, num);
            return AjaxResult.success("套餐菜品添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
    
    @RequestMapping("/updateComboDishes.do")
    @ResponseBody
    public AjaxResult updateComboDishes(Long comboId, Long dishesId, Integer num){
        // TODO 参数校验
        if(comboId == null || dishesId == null || num == null || num <= 0){
            return AjaxResult.fail("参数异常");
        }
        try {
            restaurantDishesService.updateComboDishes(comboId, dishesId, num);
            return AjaxResult.success("套餐菜品修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
    
    @RequestMapping("/deleteComboDishes.do")
    @ResponseBody
    public AjaxResult deleteComboDishes(Long comboId, Long dishesId){
        // TODO 参数校验
        if(comboId == null || dishesId == null){
            return AjaxResult.fail("参数异常");
        }
        try {
            restaurantDishesService.deleteComboDishes(comboId, dishesId);
            return AjaxResult.success("套餐菜品删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
    
    
}
