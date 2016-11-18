/**
 * Project Name:ckl
 * File Name:CarCarePriceController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

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
import tech.youmu.ckl.domain.CarCarePrice;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.ICarCarePriceService;

/**
 * <p>Title:CarCarePriceController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月10日上午10:37:37</p>
 * <p>Description:汽车维护价目表 控制器</p>
 */
@Controller
@RequestMapping("/carcareprice")
public class CarCarePriceController {
    
    @Autowired
    private ICarCarePriceService carCarePriceService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @RequestMapping("/index.do")
    public String index(String cmd, Long id, Model model){
        if("save".equals(cmd)){
            return "car_care_price/edit";
        }
        
        if("update".equals(cmd)){
            model.addAttribute("carCarPrice", carCarePriceService.getById(id));
            return "car_care_price/edit";
        }
        return "car_care_price/list";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<CarCarePrice> list(BaseQuery query){
        return carCarePriceService.getPageList(query);
    }
    
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(@RequestParam(value = "img", required=false) MultipartFile img, CarCarePrice carCarePrice){
        try {
            if(carCarePrice.getId() == null){
                if(img == null || img.isEmpty()){
                    return AjaxResult.fail("必须上传保养项目图片");
                }
                if(StringUtils.isBlank(carCarePrice.getName())){
                    return AjaxResult.fail("名称不能为空");
                }
                if(carCarePrice.getPrice() == null){
                    return AjaxResult.fail("价格不能为空");
                }
                carCarePriceService.save(img, carCarePrice);
                return AjaxResult.success("保存成功！");
            }else{
                carCarePriceService.update(img, carCarePrice);
                return AjaxResult.success("修改成功！");
            }
        } catch (BizExecption e) {
            logger.error("汽车保养项目价格编辑异常：", e);
            return AjaxResult.fail(e.getMessage());
        }catch (Exception e) {
            logger.error("汽车保养项目价格编辑异常：", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
    @RequestMapping("/delete.do")
    @ResponseBody
    public AjaxResult delete(Long id){
        try{
            if(id!=null){
                carCarePriceService.deleteById(id);
                return AjaxResult.success("删除成功！");
            }else{
                return AjaxResult.fail("未找到该商品！");
            }
        }catch(Exception e) {
            logger.error("汽车保养项目价格删除异常：", e);
            return AjaxResult.fail("系统异常");
        }
    }
}
