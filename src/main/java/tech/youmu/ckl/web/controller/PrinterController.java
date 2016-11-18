/**
 * Project Name:ckl
 * File Name:PrinterController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.JobTitle;
import tech.youmu.ckl.domain.Printer;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.JobTitleQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IPrinterService;
import tech.youmu.ckl.service.IStationService;
import tech.youmu.ckl.utils.PrintMessageUtil;

/**
 * <p>Title:PrinterController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年11月3日下午1:11:11</p>
 * <p>Description:TODO</p>
 */
@Controller
@RequestMapping("/printer")
public class PrinterController {
    
    @Autowired
    private IPrinterService printerService;
    
    @Autowired
    private IStationService stationService;
    
    @RequestMapping("/index.do")
    public String index(String cmd, Long id, Model model){
        // 直接跳转到编辑页面
        if(StringUtils.equals("save", cmd)) {
            model.addAttribute("stations", stationService.getAll());
            return "printer/printer_edit";
        }
        
        // 获取数据跳转到编辑页面
        if(StringUtils.equals("update", cmd)) {
            model.addAttribute("stations", stationService.getAll());
            //通过id获得要修改的
            model.addAttribute("printer", printerService.getById(id));
            return "printer/printer_edit";
        }
        return "printer/printer";
    }
    
    /**
     * 列表
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<Printer> list(BaseQuery baseQuery) throws Exception{
        return printerService.getPageList(baseQuery);
    }
    
    /**
     * 修改/添加
     * @param 
     * @return
     */
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(Printer printer){
        AjaxResult result = null;
        try{
            if(printer.getId()==null){
                //添加
                printerService.save(printer);
                result = AjaxResult.success("添加成功");
            }else{
                //修改
                printerService.updateById(printer);
                result = AjaxResult.success("修改成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("添加失败！" , 200);
        }
        return result;
    }
    
}
