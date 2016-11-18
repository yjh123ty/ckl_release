/**
 * Project Name:ckl
 * File Name:RestaurantController.java
 * Copyright (c) 成都友木科技 2016
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Restaurant;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.RestaurantQuery;
import tech.youmu.ckl.service.IRestaurantService;
import tech.youmu.ckl.utils.VerifyUtil;

/**
 * <p>Title:RestaurantController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月7日下午3:57:41</p>
 * <p>Description:饭店管理控制器</p>
 */
@Controller
@RequestMapping("/restaurant")
public class RestaurantController {
    
    @Autowired
    private IRestaurantService restaurantService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/index.do")
    public String index(String cmd, Long id, Model model){
        
        // 跳转到酒店房间管理
        if(StringUtils.equals("save", cmd)) {
            return "restaurant/restaurant_edit";
        }
        
        // 跳转到酒店房间管理
        if(StringUtils.equals("update", cmd)) {
            model.addAttribute("restaurant", restaurantService.getById(id));
            return "restaurant/restaurant_edit";
        }
        
        // 默认跳转酒店管理界面
        return "restaurant/restaurant_manager";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<Restaurant> list(RestaurantQuery query){
        query.setType(1);
        PageList<Restaurant> list = restaurantService.getPageList(query);
        return list;
    }
    
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult edit(@RequestParam(value="cover_img",required=false) MultipartFile coverImg, @RequestParam("images") MultipartFile[] images, Restaurant restaurant){
        if(restaurant.getStation() == null || restaurant.getStation().getId() == null) {
            return AjaxResult.fail("必须选择服务站");
        }
        try {
            if(restaurant.getId() == null) {
                if(StringUtils.isEmpty(restaurant.getName())){
                    return AjaxResult.fail("饭店名称不能为空");
                }
                if(restaurant.getMinimun() == null) {
                    return AjaxResult.fail("必须填写最低消费");
                }
                
                if(StringUtils.isEmpty(restaurant.getMobile())){
                    return AjaxResult.fail("联系电话不能为空");
                }
                
                if(coverImg == null || coverImg.isEmpty()) {
                    return AjaxResult.fail("封面图片不能为空");
                }
                
                if(images == null || !VerifyUtil.isNotEmptyMultipartFiles(images)) {
                    return AjaxResult.fail("酒店图片不能为空");
                }
                restaurant.setType(1);
                restaurantService.saveAndGetId(coverImg, images, restaurant);
                AjaxResult r = AjaxResult.success("添加成功");
                r.setData(restaurant.getId());
                return r;
            }else {
                restaurantService.updateById(coverImg, images, restaurant);
                return AjaxResult.success("修改成功");
            }
        } catch (BizExecption e) {
            logger.error("饭店编辑异常：", e);
            return AjaxResult.fail(e.getMessage());
        }catch (Exception e) {
            logger.error("饭店编辑异常：", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月19日下午2:25:33;</p>
     *  <p>Description: 更改酒店状态;</p>
     *  @param station
     *  @return
     */
    @RequestMapping("/changeStatus.do")
    @ResponseBody
    public AjaxResult changeStatus(Restaurant restaurant) {

        try {
            if (restaurant == null || restaurant.getId() == null || restaurant.getStatus() == null) {
                return AjaxResult.fail("参数异常");
            }
            restaurantService.changeStatus(restaurant);
            return AjaxResult.success("状态修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月20日下午4:16:54;</p>
     *	<p>Description: 获取所有的饭店简单信息  id 和 name;</p>
     *  @return
     */
    @RequestMapping("/findRestaurantsSimpleInfo.do")
    @ResponseBody
    public List<Restaurant> findRestaurantsSimpleInfo(){
        return restaurantService.findRestaurantsSimpleInfo();
    }
}
