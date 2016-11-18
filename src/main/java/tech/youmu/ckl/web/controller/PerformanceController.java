/**
 * @Title: PerformanceController.java
 * @Package tech.youmu.ckl.web.controller
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月19日 上午10:34:50
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.Order;
import tech.youmu.ckl.domain.Performance;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.PerformanceQuery;
import tech.youmu.ckl.service.IPerformanceService;

/**
 * 员工绩效管理
 * @author youmu-yjh
 * 
 */
@Controller
@RequestMapping("/performance")
public class PerformanceController {
    @Autowired
    private IPerformanceService performanceService;
    
    /**
     * 导向页面
     * @return
     */
    @RequestMapping("/index.do")
    public String index(String cmd, Model model){
        
        //跳转到工资页面
        if(StringUtils.equals("salary", cmd)){
            return "performance/perf_salary";
        }
        //跳转到设置工资结算日页面
        if(StringUtils.equals("settle", cmd)){
            return "performance/settle_date";
        }
        //跳转到绩效管理页面
        return "performance/performance";
    }
    
    /**
     * 绩效列表
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<Performance> list(PerformanceQuery perfQuery,String searchTimeStr,String beginTimeStr,String endTimeStr) throws Exception{
        perfQuery.setSearchTime(searchTimeStr);
        perfQuery.setBeginTimeStr(beginTimeStr);
        perfQuery.setEndTimeStr(endTimeStr);
        return performanceService.getPageList(perfQuery);
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月22日下午6:05:51;</p>
     *	<p>Description: 绩效报表;</p>
     *  @param baseQuery
     *  @param request
     *  @param response
     *  @throws Exception
     */
    @RequestMapping("/download.do")
    public void download(PerformanceQuery baseQuery,HttpServletRequest request,
            HttpServletResponse response) throws Exception{
        performanceService.download(baseQuery, request, response);
    }
    
}
