/**
 * Project Name:ckl
 * File Name:CarPartStockOutcomeController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.CarPartStockOutcome;
import tech.youmu.ckl.query.CarPartStockOutcomeQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.ICarPartDepotService;
import tech.youmu.ckl.service.ICarPartStockOutcomeService;
import tech.youmu.ckl.service.IStationService;

/**
 * <p>Title:CarPartStockOutcomeController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月20日下午2:55:00</p>
 * <p>Description:商品分类管理</p>
 */
@Controller
@RequestMapping("/carPartStockOutcome")
public class CarPartStockOutcomeController {
    
    @Autowired
    private ICarPartStockOutcomeService carPartStockOutcomeService;
    
    @Autowired
    private ICarPartDepotService carPartDepotService;
    
    @Autowired
    private IStationService stationService;
    
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
        
        if(StringUtils.equals("centre", cmd)) {
            return "carPartStockOutcome/centre_list";
        }
        
        if(StringUtils.equals("station", cmd)) {
            model.addAttribute("stations", stationService.findHasDeoptServiceStations());
            return "carPartStockOutcome/station_list";
        }
        return "carPartStockOutcome/centre_list";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<CarPartStockOutcome> list(CarPartStockOutcomeQuery query){
        return carPartStockOutcomeService.getPageList(query);
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月20日下午4:15:36;</p>
     *	<p>Description: 编辑商品分类;</p>
     *  @param carPartStockOutcome
     *  @return
     */
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(CarPartStockOutcome carPartStockOutcome){
        try{
                if(carPartStockOutcome.getId()==null){
                    //添加
                    carPartStockOutcomeService.save(carPartStockOutcome);
                    return AjaxResult.success("添加成功");
                }else{
                    //修改
                    carPartStockOutcomeService.updateById(carPartStockOutcome);
                    return AjaxResult.success("修改成功");
                }
        }catch(Exception e) {
            logger.error("零部件编辑异常：", e);
            return AjaxResult.fail(e.getMessage());
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
                    carPartStockOutcomeService.deleteById(id);
                    result = AjaxResult.success("删除成功！");
                }else{
                    result = AjaxResult.fail("未找到该出库信息！");
                }
        }catch(Exception e) {
            logger.error("零部件删除异常：", e);
            result = AjaxResult.fail("系统异常");
        }
        return result;
    }
    
}
