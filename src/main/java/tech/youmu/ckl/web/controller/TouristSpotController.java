/**
 * Project Name:ckl
 * File Name:TouristSpotController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
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
import tech.youmu.ckl.domain.Station;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.StationQuery;
import tech.youmu.ckl.service.ITouristSpotService;
import tech.youmu.ckl.utils.VerifyUtil;

/**
 * <p>Title:TouristSpotController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月22日下午2:48:10</p>
 * <p>Description:景点的控制器</p>
 */
@Controller
@RequestMapping("/touristspot")
public class TouristSpotController {
    
    @Autowired
    private ITouristSpotService touristSpotService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @RequestMapping("/index.do")
    public String index(String cmd, Long id, Model model){
        if(StringUtils.equals("save", cmd)) {
            return "tourist_spot/edit";
        }
        
        if(StringUtils.equals("update", cmd)) {
            model.addAttribute("touristSpot",touristSpotService.getById(id));
            return "tourist_spot/edit";
        }
        return "tourist_spot/list";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<Station> list(StationQuery query){
        return touristSpotService.getPageList(query);
    }
    
    
    @RequestMapping("/delete.do")
    @ResponseBody
    public AjaxResult delete(Long id){
        try {
            if(id != null) {
                touristSpotService.deleteById(id);
                return AjaxResult.success("删除成功");
            }else {
                return AjaxResult.fail("参数异常"); 
            }
        } catch (Exception e) {
            logger.error("景点删除异常：", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
    
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult edit(@RequestParam("images") MultipartFile[] images, Station station){
        
        try {
           if(station.getId() == null) {
               if(StringUtils.isBlank(station.getName())){
                   return AjaxResult.fail("名称不能为空");
               }
               
               if(StringUtils.isBlank(station.getFullAddress())){
                   return AjaxResult.fail("地址不能为空");
               }
               
              if(touristSpotService.checkRepeat(station)){
                  return AjaxResult.fail("景点名称已经存在");
              }
              
               if(!VerifyUtil.isNotEmptyMultipartFiles(images)) {
                   return AjaxResult.fail("必须上传图片");
               }
               touristSpotService.save(images, station);
               return AjaxResult.success("保存成功");
           }else {
               touristSpotService.update(images, station);
               return AjaxResult.success("修改成功");
           }
        } catch (BizExecption e) {
            logger .error("景点编辑错误: ", e);
            return AjaxResult.fail(e.getMessage());
        }catch (Exception e) {
            logger .error("景点编辑错误: ", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
    
}
