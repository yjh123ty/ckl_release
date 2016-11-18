/**
 * Project Name:ckl
 * File Name:OrderController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.AddOrderDetailList;
import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Order;
import tech.youmu.ckl.domain.Printer;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.exception.ParamException;
import tech.youmu.ckl.query.OrderQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IOrderService;
import tech.youmu.ckl.service.IOrderServiceDetailService;
import tech.youmu.ckl.service.IPrinterService;
import tech.youmu.ckl.utils.VerifyUtil;

/**
 * <p>Title:OrderController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月31日下午3:25:06</p>
 * <p>Description:订单管理</p>
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private IOrderService orderService;
    
    
    @Autowired
    private IOrderServiceDetailService orderServiceDetailService;
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月31日下午4:06:58;</p>
     *	<p>Description: 订单导向页面;</p>
     *  @return
     */
    @RequestMapping("/index.do")
    public String index(String cmd,Long id,Model model){
        
        if(StringUtils.equals(cmd, "order_allowed")){
            //同意退款订单界面
            return "order/order_allowed";
        }
        
        if(StringUtils.equals(cmd, "refund")){
            //退款管理界面
            return "order/order_refund";
        }
        
        if(StringUtils.equals(cmd, "detail")){
            //根据订单id获取订单明细
            model.addAttribute("detail",orderService.getById(id));
            //根据订单id获取订单详情
            model.addAttribute("orderServiceDetail",orderServiceDetailService.getByOrderId(id));
            //订单详情界面
            return "order/order_detail";
        }
        
        if(StringUtils.equals(cmd, "refundDetail")){
            //根据订单id获取订单明细
            model.addAttribute("detail",orderService.getById(id));
            //根据订单id获取订单详情
            model.addAttribute("orderServiceDetail",orderServiceDetailService.getByOrderId(id));
            //退款订单详情界面
            return "order/order_refundDetail";
        }
        //订单主界面
        return "order/order";
    }
    
    /**
     * @param query 订单查询对象
     * @param beginTimeStr 时间起点
     * @param endTimeStr 时间截止
     * @param sort 排序字段
     * @param order 排序方式
     * @return 
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<Order> list(OrderQuery query,String beginTimeStr,String endTimeStr){
        query.setBeginTimeStr(beginTimeStr);
        query.setEndTimeStr(endTimeStr);
        return orderService.getPageList(query);
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月1日下午4:45:18;</p>
     *	<p>Description: 退款订单列表;</p>
     *  @param query 订单查询对象
     *  @param beginTimeStr 时间起点
     *  @param endTimeStr 时间截止
     *  @param sort 排序字段
     *  @param order 排序方式
     *  @return
     */
    @RequestMapping("/refund.do")
    @ResponseBody
    public PageList<Order> refundList(OrderQuery query,String beginTimeStr,String endTimeStr){
        query.setBeginTimeStr(beginTimeStr);
        query.setEndTimeStr(endTimeStr);
        
        return orderService.refundPageList(query);
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月1日上午10:12:59;</p>
     *	<p>Description: 订单退款操作;</p>
     *  @param id
     *  @return 
     */
    @RequestMapping("/refundById.do")
    @ResponseBody
    public AjaxResult refundById(Long id){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            if(id != null) {
                orderService.refundById(id);
                ajaxResult.setMsg("退款成功！");
            }else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMsg("订单编号不能为空!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMsg("操作失败");
        }
        return ajaxResult; 
    }
    
    
    
    /**
     * 
     * @param id	订单id
     * @return	验证是否通过的 json对象
     */
    @RequestMapping("/checkCodeById.do")
    @ResponseBody
    public AjaxResult checkCodeById(Long id,String code){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            if(id != null) {
            	//判断订单是否验证通过
            	Boolean result = orderService.checkCodeById(id,code);
            	
            	if(result){
            		//验证通过
            		ajaxResult.setMsg("验证通过！");
            		ajaxResult.setSuccess(true);
            	}else {
					//验证失败
            		ajaxResult.setSuccess(false);
            		ajaxResult.setMsg("验证失败！请输入正确的验证码！");
				}
            	
            }else {
                ajaxResult.setSuccess(false);
                ajaxResult.setMsg("验证失败！未找到该订单！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMsg("验证异常！");
        }
        return ajaxResult; 
    }
    
 	/**
 	 * 下载订单
 	 * @param baseQuery
 	 * @param request
 	 * @param response
 	 * @return
 	 * @throws Exception
 	 */
 	@RequestMapping("/download.do")
 	public void download(OrderQuery baseQuery,HttpServletRequest request,
		      HttpServletResponse response) throws Exception {
 	   orderService.download(baseQuery, request, response);
 	}
 	
 	    /**
 	     * 完成酒店服务记录
 	     * @param id
 	     * @param serviceName
 	     * @param number
 	     * @return
 	     */
    @RequestMapping("/finishHotelService.do")
    @ResponseBody
    public AjaxResult finishHotelService(Long id,String serviceName,Integer number){
       AjaxResult ajaxResult = new AjaxResult();
       
       try {
          if(id != null) {
              Boolean isFinish = orderService.finishHotelService(id,serviceName,number);
              if(isFinish){
            	  ajaxResult.setSuccess(true);
                  ajaxResult.setMsg("服务完成！");
              }else if(!isFinish){
            	  ajaxResult.setSuccess(false);
                  ajaxResult.setMsg("服务未提交！");
              }
          }else {
              ajaxResult.setSuccess(false);
              ajaxResult.setMsg("订单编号不能为空!");
          }
       } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMsg("提交失败！");
       }
       return ajaxResult;
    }
 	    
 	/**
 	 * 完成饭店服务记录
 	 * @param id
 	 * @param addLists
 	 * @return
 	 */
 	@RequestMapping("/finish.do")
 	@ResponseBody
 	public AjaxResult finishService(Long id,AddOrderDetailList addLists){
 	   AjaxResult ajaxResult = new AjaxResult();
 	   
 	   try {
 	      if(id != null) {
              Boolean isFinish = orderService.finishById(id,addLists);
              if(isFinish){
                  ajaxResult.setMsg("服务完成！");
              }else if(!isFinish){
                  ajaxResult.setMsg("服务未提交！");
              }
          }else {
              ajaxResult.setSuccess(false);
              ajaxResult.setMsg("订单编号不能为空!");
          }
       } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setMsg("提交失败！");
       }
 	   return ajaxResult;
 	}
 	
 	/**
 	 * 发送待支付短信提醒
 	 * @param mobile
 	 * @return
 	 */
 	@RequestMapping("/sendPayRemainMessage.do")
 	@ResponseBody
 	public AjaxResult sendPayRemainMessage(String mobile){
 	   AjaxResult ajaxResult = new AjaxResult();
 	   try {
 	      if(StringUtils.isBlank(mobile)) {
 	    	 ajaxResult.setSuccess(false);
             ajaxResult.setMsg("手机号不能为空!");
          }
 	      if(!VerifyUtil.isMobile(mobile)){
 	    	 ajaxResult.setSuccess(false);
 			throw new ParamException("手机号码格式错误");
 	      }
          orderService.sendPayRemainMessage(mobile);
          ajaxResult.setSuccess(true);
          ajaxResult.setMsg("发送成功!");
       } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMsg("提交失败！");
       }
 	   return ajaxResult;
 	}
 	
 	@RequestMapping("/finishRoadSide.do")
    @ResponseBody
    public AjaxResult finishRoadSide(Long id){
       AjaxResult ajaxResult = new AjaxResult();
       try {
          if(id == null){
             ajaxResult.setSuccess(false);
            throw new ParamException("未找到该订单！");
          }
          orderService.finishRoadSide(id);
          ajaxResult.setSuccess(true);
          ajaxResult.setMsg("服务完成!");
       } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMsg("提交异常！");
       }
       return ajaxResult;
    }
 	
 	@RequestMapping("/print.do")
    @ResponseBody
 	public AjaxResult print(Long id){
        AjaxResult ajaxResult = new AjaxResult();
        try {
           if(id == null){
              ajaxResult.setSuccess(false);
             throw new ParamException("未找到该订单！");
           }
           if(orderService.printById(id)){
               ajaxResult.setSuccess(true);
               ajaxResult.setMsg("打印完成!");
           }else{
               throw new BizExecption("打印失败！");
           }
        } catch (Exception e) {
             e.printStackTrace();
             ajaxResult.setSuccess(false);
             ajaxResult.setMsg("打印异常！");
        }
        return ajaxResult;
     }
 	
}
