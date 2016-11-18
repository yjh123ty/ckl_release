/**
 * Project Name:ckl
 * File Name:ServiceStationReportController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import java.text.ParseException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.ServiceStationReport;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.ServiceStationReportQuery;
import tech.youmu.ckl.service.IServiceStationReportService;
import tech.youmu.ckl.service.IStationService;
import tech.youmu.ckl.utils.DateUtil;

/**
 * <p>Title:ServiceStationReportController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月19日上午11:43:03</p>
 * <p>Description:服务站财务报表控制器</p>
 */
@Controller
@RequestMapping("/servicestationreport")
public class ServiceStationReportController {
    
    @Autowired
    private IServiceStationReportService serviceStationReportService;
    
    @Autowired
    private IStationService stationService;
    
    @RequestMapping("/index.do")
    public String index(Model model){
       /* List<Station> stations = stationService.getAll();
        tech.youmu.ckl.utils.LogUtils.getInstance(getClass()).debugBean(stations);*/
        model.addAttribute("stations", stationService.getAll());
        return "service_station/service_station_report";
    }
    
    @RequestMapping("/orderInfo.do")
    public String orderInfo(Long stationId, String startTime, String endTime, Model model){
        if(StringUtils.isNoneBlank(startTime)){
            model.addAttribute("beginTimeStr", startTime+"-01");
        }
        
        if(StringUtils.isNotBlank(endTime)) {
            String endTimeStr = "";
            try {
                Calendar calendar = DateUtils.toCalendar(DateUtils.parseDate(endTime, "yyyy-MM"));
                calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
                endTimeStr = DateFormatUtils.format(calendar, "yyyy-MM-dd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            model.addAttribute("endTimeStr", endTimeStr);
        }
        model.addAttribute("stationId", stationId);
        return "order/order";
    }
    
    @RequestMapping("/starInfo.do")
    public String starInfo(Long stationId, String startTime, String endTime, Model model){
        if(StringUtils.isNoneBlank(startTime)){
            model.addAttribute("beginTimeStr", startTime+"-00");
        }
        if(StringUtils.isNotBlank(endTime)) {
            String endTimeStr = "";
            try {
                Calendar calendar = DateUtils.toCalendar(DateUtils.parseDate(endTime, "yyyy-MM"));
                calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
                endTimeStr = DateFormatUtils.format(calendar, "yyyy-MM-dd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            model.addAttribute("endTimeStr", endTimeStr);
        }
        model.addAttribute("stationId", stationId);
        return "serviceEvaluation/serviceEvaluation";
    }
    
    @RequestMapping(value="/list.do", method=RequestMethod.POST)
    @ResponseBody
    public PageList<ServiceStationReport> list(ServiceStationReportQuery query){
        return serviceStationReportService.findPageList(query);
    }
    
    @RequestMapping(value="/download.do")
    public void download(ServiceStationReportQuery baseQuery,
    		HttpServletRequest request, HttpServletResponse response) throws Exception{
    	serviceStationReportService.download(baseQuery, request, response);
    }
}
