/**
 * Project Name:ckl
 * File Name:CarPartStockIncomeController.java
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
import tech.youmu.ckl.domain.CarPartStockIncome;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.query.CarPartStockIncomeQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.ICarPartDepotService;
import tech.youmu.ckl.service.ICarPartStockIncomeService;
import tech.youmu.ckl.service.IStationService;
import tech.youmu.ckl.utils.LogUtils;

/**
 * <p>Title:CarPartStockIncomeController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月20日下午2:55:00</p>
 * <p>Description:商品分类管理</p>
 */
@Controller
@RequestMapping("/carPartStockIncome")
public class CarPartStockIncomeController {
    
    @Autowired
    private ICarPartStockIncomeService carPartStockIncomeService;
    
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
            return "carPartStockIncome/centre_list";
        }
        
        if(StringUtils.equals("station", cmd)) {
            model.addAttribute("stations", stationService.findHasDeoptServiceStations());
            return "carPartStockIncome/station_list";
        }
        
        if(StringUtils.equals("oldList", cmd)){
            return "carPartOld/list";
        }
        return "carPartStockIncome/centre_list";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<CarPartStockIncome> list(CarPartStockIncomeQuery query){
        return carPartStockIncomeService.getPageList(query);
    }
    
    @RequestMapping("/listOld.do")
    @ResponseBody
    public PageList<CarPartStockIncome> listOld(CarPartStockIncomeQuery query){
        return carPartStockIncomeService.getOldCarPartPageList(query);
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
                    carPartStockIncomeService.deleteById(id);
                    result = AjaxResult.success("删除成功！");
                }else{
                    result = AjaxResult.fail("未找到该入库信息！");
                }
        }catch(Exception e) {
            logger.error("库存记录删除异常：", e);
            result = AjaxResult.fail("系统异常");
        }
        return result;
    }
    
}
