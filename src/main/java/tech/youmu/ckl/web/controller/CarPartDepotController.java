/**
 * Project Name:ckl
 * File Name:CarPartDepotController.java
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
import tech.youmu.ckl.domain.CarPartDepot;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.CarPartDepotQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IAdminService;
import tech.youmu.ckl.service.ICarPartDepotService;
import tech.youmu.ckl.service.IEmployeeService;
import tech.youmu.ckl.service.IStationService;
import tech.youmu.ckl.utils.LogUtils;

/**
 * <p>Title:CarPartDepotController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月20日下午2:55:00</p>
 * <p>Description:仓库管理</p>
 */
@Controller
@RequestMapping("/carPartDepot")
public class CarPartDepotController {
    
    @Autowired
    private ICarPartDepotService carPartDepotService;
    
    @Autowired
    private IStationService stationService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月20日下午2:56:08;</p>
     *	<p>Description: 仓库管理跳转;</p>
     *  @return
     */
    @RequestMapping("/index.do")
    public String index(String cmd,Long id,Model model){
        
      //跳转到编辑界面
        if(StringUtils.equals("save", cmd)) {
            model.addAttribute("stations", stationService.getAll());
            model.addAttribute("admins", carPartDepotService.findCandidateKeepers());
            return "carPartDepot/edit";
        }
        
        if(StringUtils.equals("update", cmd)) {
            //根据id获取规则
            CarPartDepot byId = carPartDepotService.getById(id);
            LogUtils.getInstance(getClass()).debugBean(byId);
            model.addAttribute("carPartDepot", byId);
            model.addAttribute("stations", stationService.getAll());
            model.addAttribute("admins", carPartDepotService.findCandidateKeepers());
            return "carPartDepot/edit";
        }
        
        return "carPartDepot/list";
    }
    
    /**
     * 
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月20日下午2:56:27;</p>
     *	<p>Description: 仓库列表;</p>
     *  @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<CarPartDepot> list(CarPartDepotQuery query){
        return carPartDepotService.getPageList(query);
    }
    
    /**
     * 
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月20日下午4:15:36;</p>
     *	<p>Description: 编辑仓库;</p>
     *  @param carPartDepot
     *  @return
     */
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(CarPartDepot carPartDepot){
        try{
            if(carPartDepot.getCentre()) {
                carPartDepot.setStation(null);
            }
            
            if(carPartDepot.getId()==null){
                if(StringUtils.isBlank(carPartDepot.getName())) {
                    return AjaxResult.fail("仓库名称不能为空");
                }
                if(StringUtils.isBlank(carPartDepot.getSn())) {
                    return AjaxResult.fail("仓库编码不能为空");
                }
                if(carPartDepot.getKeeper() == null || carPartDepot.getKeeper().getId() == null) {
                    return AjaxResult.fail("库管员不能为空");
                }
                //添加
                carPartDepotService.save(carPartDepot);
                return AjaxResult.success("添加成功");
            }else{
                //修改
                carPartDepotService.updateById(carPartDepot);
                return AjaxResult.success("修改成功");
            }
        }catch(BizExecption e) {
            logger.error("零部件仓库编辑异常：", e);
            return AjaxResult.fail(e.getMessage());
        }catch (Exception e) {
            logger.error("零部件仓库编辑异常：", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
    /**
     * 
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月20日下午4:20:44;</p>
     *	<p>Description: 删除仓库;</p>
     *  @param id
     *  @return
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public AjaxResult delete(Long id){
        AjaxResult result= null;
        try{
                if(id!=null){
                    carPartDepotService.deleteById(id);
                    result = AjaxResult.success("删除成功！");
                }else{
                    result = AjaxResult.fail("未找到该仓库！");
                }
        }catch(Exception e) {
            logger.error("零部件分类删除异常：", e);
            result =  AjaxResult.fail("系统异常");
        }
        return result;
    }
    
}
