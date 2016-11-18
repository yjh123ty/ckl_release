/**
 * Project Name:ckl
 * File Name:HotelRoomRecordController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.query.HotelRoomRecordQuery;
import tech.youmu.ckl.service.IHotelRoomRecordService;

/**
 * <p>Title:HotelRoomRecordController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月12日上午9:48:00</p>
 * <p>Description:酒店房间记录的控制器</p>
 */
@Controller
@RequestMapping("/hotelroomrecord")
public class HotelRoomRecordController {
    
    @Autowired
    private IHotelRoomRecordService hotelRoomRecordService;
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月12日上午10:31:44;</p>
     *	<p>Description: 显示未来两个月内的房间预订数量和线下的预订数量的分页数据展示;</p>
     *  @param query
     *  @return
     */
    @RequestMapping("/list.do")
    public String list(Model model, HotelRoomRecordQuery query){
        model.addAttribute("query", query);
        model.addAttribute("page", hotelRoomRecordService.getPageList(query));
        return "hotel_room_record/hotel_room_record";
    }
    
    @RequestMapping("/addRoomNum")
    @ResponseBody
    public AjaxResult addOfflineRoomNum(Long recordId){
        try {
            if(recordId != null) {
                // 将线下的房间数量加一
                if(hotelRoomRecordService.addOfflineRoomNum(recordId)){
                    return AjaxResult.success("修改成功");
                } else {
                    return AjaxResult.fail("房间数量有误");
                }
            }else {
                return AjaxResult.fail("记录不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
    
    @RequestMapping("/removeRoomNum")
    @ResponseBody
    public AjaxResult removeOfflineRoomNum(Long recordId){
        try {
            if(recordId != null) {
                // 将线下的房间数量加一
                if(hotelRoomRecordService.removeOfflineRoomNum(recordId)){
                    return AjaxResult.success("修改成功");
                }else {
                    return AjaxResult.fail("房间数量有误");
                }
            }else {
                return AjaxResult.fail("记录不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
    
}
