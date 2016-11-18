/**
 * Project Name:ckl
 * File Name:SosController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Sos;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IOrderService;
import tech.youmu.ckl.service.ISosService;

/**
 * <p>Title:SosController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月19日下午5:47:55</p>
 * <p>Description:TODO</p>
 */
@Controller
@RequestMapping("/sos")
public class SosController {
    
    @Autowired
    private ISosService sosService;
    
    @Autowired
    private IOrderService orderService;
    
    @RequestMapping("/index.do")
    public String index(String cmd){
        if(StringUtils.equals(cmd, "employeeIsNull")){
            return "sos/sosEmployeeIsNull";
        }
        return "sos/sos";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<Sos> list(BaseQuery query){
        return sosService.getPageList(query);
    }
    
    @RequestMapping("/getByEmployeeIsNull.do")
    @ResponseBody
    public PageList<Sos> getByEmployeeIsNull(BaseQuery query){
        return sosService.getByEmployeeIsNull(query);
    }
    
    @RequestMapping("/handleById.do")
    @ResponseBody
    public AjaxResult handleById(Long id){
        AjaxResult result = null;
        try {
            if(id==null){
                result = AjaxResult.fail("未找到该求助信息");
            }
            if(sosService.handleById(id)){
                result = AjaxResult.success("处理成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("操作失败！" , 200);
        }
        return result;
    }
}
