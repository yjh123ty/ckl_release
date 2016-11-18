/**
 * Project Name:ckl
 * File Name:ServiceStationController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import java.math.BigDecimal;
import java.util.Arrays;
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

import tech.youmu.ckl.constants.Global;
import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Station;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.StationQuery;
import tech.youmu.ckl.service.IServiceTypeService;
import tech.youmu.ckl.service.IStationService;
import tech.youmu.ckl.utils.VerifyUtil;

/**
 * <p>
 * Title:ServiceStationController
 * </p>
 * @author zh
 * @version v1.0
 *          <p>
 *          Date:2016年8月24日上午10:35:25
 *          </p>
 *          <p>
 *          Description:服务站管理控制器
 *          </p>
 */
@Controller
@RequestMapping("/servicestation")
public class ServiceStationController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IStationService stationService;

    @Autowired
    private IServiceTypeService serviceTypeService;

    /**
     * <p>
     * Author:zh;
     * </p>
     * <p>
     * Date:2016年8月24日上午10:36:28;
     * </p>
     * <p>
     * Description: 服务站管理的导向 默认导向 服务站列表展示 使用cmd 字符串控制导向的位置;
     * </p>
     * @return
     */
    @RequestMapping("/index.do")
    public String index(String cmd, Long id, Model model) {
        if (StringUtils.equals("save", cmd)) {
            model.addAttribute("serviceTypes", serviceTypeService.getAll());
            return "service_station/service_station_edit";
        }
        if(StringUtils.equals("update", cmd)){
            Station station = stationService.getById(id);
            model.addAttribute("station", station);
            model.addAttribute("serviceTypes", serviceTypeService.getAll());
            return "service_station/service_station_edit";
        }
        if(StringUtils.equals("updateRoadSidePrice", cmd)){
            model.addAttribute("roadSide", serviceTypeService.getRoadSideInfo());
            return "service_station/road_side_price_update";
        }
        return "service_station/service_station";
    }

    /**
     * <p>
     * Author:zh;
     * </p>
     * <p>
     * Date:2016年8月24日上午10:55:46;
     * </p>
     * <p>
     * Description: 服务站列表方法 根据服务站点的查询条件来查询 服务站的信息;
     * </p>
     * @param query
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<Station> list(StationQuery query) {
        query.setType(Global.StationType.TYPE_SERVICE_STATION);
        return stationService.getPageList(query);
    }

    /**
     * <p>
     * Author:zh;
     * </p>
     * <p>
     * Date:2016年8月24日下午3:41:26;
     * </p>
     * <p>
     * Description: 修改站点的状态; 不仅仅是服务站
     * </p>
     * @param station
     * @return
     */
    @RequestMapping("/changeStatus.do")
    @ResponseBody
    public AjaxResult changeStatus(Station station) {

        try {
            if (station == null || station.getId() == null || station.getStatus() == null) {
                return AjaxResult.fail("参数异常");
            }
            stationService.changeStatus(station);
            return AjaxResult.success("状态修改成功");
        } catch (Exception e) {
            logger.error("服务站删除异常：", e);
            return AjaxResult.fail("系统异常");
        }
    }

    /**
     * <p>
     * Author:zh;
     * </p>
     * <p>
     * Date:2016年8月26日上午10:41:30;
     * </p>
     * <p>
     * Description: 修改/添加一个站点;
     * </p>
     * @param images
     * @param station
     * @return
     */
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult edit(@RequestParam("images") MultipartFile[] images, Station station) {
        logger.debug("edit.. 上传图片： " + Arrays.toString(images));
        logger.debug("edit.. 服务站： " + station);
        try{
            if (station.getId() == null) {
                
                if(StringUtils.isEmpty(station.getName())){
                    return AjaxResult.fail("服务站名称不能为空");
                }
                
                if(StringUtils.isEmpty(station.getFullAddress())){
                    return AjaxResult.fail("地址不能为空");
                }
                
                if(!VerifyUtil.isNotEmptyMultipartFiles(images)){
                    return AjaxResult.fail("服务站图片不能为空");
                }
                
                // 参数校验
                // 保存站点
                stationService.saveStationWithServcieTypes(images, station);
                // 保存站点相关的服务的关联关系
                AjaxResult result = AjaxResult.success("站点保存成功");
                // 返回站点的id方便编辑后面的酒店饭店信息
                result.setData(station.getId());
                return result;
            } else {
                // 修改
                // 保存站点相关的服务
                stationService.updateStationWithServcieTypes(images, station);
                return AjaxResult.success("站点修改成功");
            }
        } catch (BizExecption e) {
            logger.error("服务站编辑异常：", e);
            return AjaxResult.fail(e.getMessage());
        }catch (Exception e) {
            logger.error("服务站编辑异常：", e);
            return AjaxResult.fail("系统异常");
        }
    }
    @RequestMapping("/roadeSidePriceUpdate.do")
    @ResponseBody
    public AjaxResult roadeSidePriceUpdate(BigDecimal startPrice, BigDecimal milPrice) {
        try{
            if(startPrice == null) {
                return AjaxResult.fail("起步价不能为空");
            }
            if(milPrice == null) {
                return AjaxResult.fail("公里价不能为空");
            }
            serviceTypeService.updateRoadSidePrice(startPrice, milPrice);
            return AjaxResult.success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月7日上午9:23:13;</p>
     *	<p>Description: 服务详情;</p>
     *  @return
     */
    @RequestMapping("/serviceContentInfo.do")
    public String serviceContentInfo(Long stationId, Integer type, Model model){
        switch (type) {
            case 1:
            model.addAttribute("hotels", stationService.getHotelServiceInfo(stationId));
                return "hotel/hotel";
            case 2:
            model.addAttribute("restaurants", stationService.getRestaurantServiceInfo(stationId));
                return "restaurant/restaurant";
            case 3:
                return "car_care_price/list";
            case 6:
                return "service_station/wash_car";
        }
        return null;
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月7日上午9:23:13;</p>
     *  <p>Description: 获取包含酒店服务类型的服务站;</p>
     *  @return
     */
    @RequestMapping("/getHasHotelStations.do")
    @ResponseBody
    public List<Station> getHasHotelStations(){
        return stationService.getHasHotelStations();
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月7日上午9:23:13;</p>
     *  <p>Description: 获取包含饭店服务类型的服务站;</p>
     *  @return
     */
    @RequestMapping("/getHasRestaurantStations.do")
    @ResponseBody
    public List<Station> getHasRestaurantStations(){
        return stationService.getHasRestaurantStations();
    }
}
