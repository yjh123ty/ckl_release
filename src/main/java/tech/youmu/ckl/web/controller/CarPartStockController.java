/**
 * Project Name:ckl
 * File Name:CarPartStockController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import javax.xml.ws.RespectBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.CarPartStock;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.ICarPartStockService;

/**
 * <p>Title:CarPartStockController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月14日上午10:26:12</p>
 * <p>Description:TODO</p>
 */
@Controller
@RequestMapping("/carPartStock")
public class CarPartStockController {
    
    @Autowired
    private ICarPartStockService carPartStockService;
    
    @RequestMapping("/index.do")
    public String index() {
        return "carPartStock/list";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<CarPartStock> list(BaseQuery query){
        return carPartStockService.getPageList(query);
    }
}
