/**
 * Project Name:ckl
 * File Name:HotelController.java
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
import tech.youmu.ckl.domain.Hotel;
import tech.youmu.ckl.domain.Station;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.query.HotelQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IHotelService;
import tech.youmu.ckl.utils.VerifyUtil;

/**
 * <p>Title:HotelController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月7日下午3:57:41</p>
 * <p>Description:酒店管理控制器</p>
 */
@Controller
@RequestMapping("/hotel")
public class HotelController {
    
    @Autowired
    private IHotelService hotelService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @RequestMapping("/index.do")
    public String index(String cmd, Long id, Model model){
        
        // 跳转到酒店房间管理
        if(StringUtils.equals("save", cmd)) {
            model.addAttribute("content", hotelService.getServiceContents());
            return "hotel/hotel_edit";
        }
        
        // 跳转到酒店房间管理
        if(StringUtils.equals("update", cmd)) {
            model.addAttribute("content", hotelService.getServiceContents());
            model.addAttribute("hotel", hotelService.getById(id));
            return "hotel/hotel_edit";
        }
        
        // 默认跳转酒店管理界面
        return "hotel/hotel_manager";
    }
    
    
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<Hotel> list(HotelQuery query){
        query.setType(1);
        return hotelService.getPageList(query);
    }
    
    @RequestMapping("/cooperatorList.do")
    @ResponseBody
    public PageList<Hotel> cooperatorList(HotelQuery query){
        query.setType(2);
        return hotelService.getPageList(query);
    }
    
    
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult edit(@RequestParam(value="cover_img",required=false) MultipartFile coverImg, @RequestParam("outside_imgs") MultipartFile[] outsideImgs, @RequestParam("inner_imgs") MultipartFile[] innerImgs, Hotel hotel){
        if((hotel.getStation() == null || hotel.getStation().getId() == null) && hotel.getType()==1) {
            return AjaxResult.fail("必须选择服务站");
        }
        try {
            if(hotel.getId() == null) {
                if(StringUtils.isEmpty(hotel.getName())){
                    return AjaxResult.fail("名称不能为空");
                }
                
                if(hotel.getMinimun() == null) {
                    return AjaxResult.fail("必须填写最低消费");
                }
                
                if(StringUtils.isEmpty(hotel.getMobile())){
                    return AjaxResult.fail("联系电话不能为空");
                }
                
                if(coverImg == null || coverImg.isEmpty()) {
                    return AjaxResult.fail("封面图片不能为空");
                }
                
                if(outsideImgs == null || !VerifyUtil.isNotEmptyMultipartFiles(outsideImgs)) {
                    return AjaxResult.fail("外观图片不能为空");
                }
                
                if(innerImgs == null || !VerifyUtil.isNotEmptyMultipartFiles(innerImgs)) {
                    return AjaxResult.fail("大厅图片不能为空");
                }
                hotel.setType(1);
                hotelService.saveHotelAndServiceContents(coverImg, outsideImgs, innerImgs, hotel);
                AjaxResult r = AjaxResult.success("添加成功");
                r.setData(hotel.getId());
                return r;
            }else {
                hotelService.updateHotelAndServiceContents(coverImg, outsideImgs, innerImgs, hotel);
                return AjaxResult.success("修改成功");
            }
        } catch (BizExecption e) {
            logger.error("酒店编辑异常：", e);
            return AjaxResult.fail(e.getMessage());
        } catch (Exception e) {
            logger.error("酒店编辑异常：", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月19日下午2:25:33;</p>
     *	<p>Description: 更改酒店状态;</p>
     *  @param station
     *  @return
     */
    @RequestMapping("/changeStatus.do")
    @ResponseBody
    public AjaxResult changeStatus(Hotel hotel) {

        try {
            if (hotel == null || hotel.getId() == null || hotel.getStatus() == null) {
                return AjaxResult.fail("参数异常");
            }
            hotelService.changeStatus(hotel);
            return AjaxResult.success("状态修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
    
}
