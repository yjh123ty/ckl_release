/**
 * Project Name:ckl
 * File Name:UserBillController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.constants.Global;
import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.UserBill;
import tech.youmu.ckl.domain.UserBillBalanceInfo;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.UserBillQuery;
import tech.youmu.ckl.service.IUserBillService;
import tech.youmu.ckl.utils.UploadUtils;

/**
 * <p>Title:UserBillController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月6日下午4:48:46</p>
 * <p>Description:TODO</p>
 */
@Controller
@RequestMapping("/userBill")
public class UserBillController {
	@Autowired
	private IUserBillService userBillService;
	
    @RequestMapping("/index.do")
    public String index(String cmd,Long uid,Model model){
        //用户提现总览界面
    	if(StringUtils.equals(cmd, "withdraw")){
    		return "userBill/userBillWithdraw";
    	}
    	
    	//用户充值明细界面
    	if(StringUtils.equals(cmd, "rechargeDetail")){
    	    //根据传入的用户id查询结果
    	    model.addAttribute("uid", uid);
    	    return "userBill/userBillRechargeDetail";
    	}
    	
    	//用户提现明细界面
        if(StringUtils.equals(cmd, "withdrawDetail")){
            //根据传入的用户id查询结果
            model.addAttribute("uid", uid);
            return "userBill/userBillWithdrawDetail";
        }
    	
    	//用户充值总览界面
        return "userBill/userBillRecharge";
    }
    
    /**
     * 充值报表总览
     * @param userBillQuery
     * @param beginTimeStr
     * @param endTimeStr
     * @return
     */
    @RequestMapping("/userBillRechargeList.do")
    @ResponseBody
    public PageList<UserBillBalanceInfo> rechargeList(UserBillQuery userBillQuery,String beginTimeStr,String endTimeStr){
    	userBillQuery.setBeginTimeStr(beginTimeStr);
    	userBillQuery.setEndTimeStr(endTimeStr);
    	userBillQuery.setType(Global.UserBill.BILL_RECHARGE);
    	userBillQuery.setStatus(2);
		return userBillService.getPageList(userBillQuery);
    }
    
    /**
     * 提现报表总览
     * @param userBillQuery
     * @param beginTimeStr
     * @param endTimeStr
     * @return
     */
    @RequestMapping("/userBillWithdrawList.do")
    @ResponseBody
    public PageList<UserBillBalanceInfo> withdrawList(UserBillQuery userBillQuery,String beginTimeStr,String endTimeStr){
    	userBillQuery.setBeginTimeStr(beginTimeStr);
    	userBillQuery.setEndTimeStr(endTimeStr);
    	userBillQuery.setType(Global.UserBill.BILL_WITHDRAW);
		return userBillService.getPageList(userBillQuery);
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月7日下午2:13:40;</p>
     *	<p>Description: 用户充值明细;</p>
     *  @param userBillQuery
     *  @param beginTimeStr
     *  @param endTimeStr
     *  @param uid
     *  @param sort
     *  @param order
     *  @return
     */
    @RequestMapping("/userBillRechargeDetailList.do")
    @ResponseBody
    public PageList<UserBill> rechargeDetailList(UserBillQuery userBillQuery,String beginTimeStr,String endTimeStr,Long uid){
        userBillQuery.setBeginTimeStr(beginTimeStr);
        userBillQuery.setEndTimeStr(endTimeStr);
        userBillQuery.setType(Global.UserBill.BILL_RECHARGE);
        userBillQuery.setUserId(uid);
        
        return userBillService.getListByUserId(userBillQuery);
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月7日下午2:14:02;</p>
     *	<p>Description: 用户转账明细;</p>
     *  @param userBillQuery
     *  @param beginTimeStr
     *  @param endTimeStr
     *  @param uid
     *  @param sort
     *  @param order
     *  @return
     */
    @RequestMapping("/userBillWithdrawDetailList.do")
    @ResponseBody
    public PageList<UserBill> withdrawDetailList(UserBillQuery userBillQuery,String beginTimeStr,String endTimeStr,Long uid){
        userBillQuery.setBeginTimeStr(beginTimeStr);
        userBillQuery.setEndTimeStr(endTimeStr);
        userBillQuery.setType(Global.UserBill.BILL_WITHDRAW);
        userBillQuery.setUserId(uid);
        return userBillService.getListByUserId(userBillQuery);
    }
    
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月7日下午2:17:26;</p>
     *	<p>Description: 提交用户的提现转账;</p>
     *  @return
     */
    @RequestMapping("/transferById.do")
    @ResponseBody
    public AjaxResult transferById(@RequestParam("introImg") MultipartFile img,Long id,String transferTime){
        AjaxResult result = new AjaxResult();
        System.err.println(transferTime);
        try {
            if(id != null){
                //根据id获取具体的用户账单
                UserBill userBill = (UserBill) userBillService.getById(id);
                if (transferTime!=null&&!transferTime.isEmpty()) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date tTime = format.parse(transferTime);
                    System.err.println(tTime);
                    //设置转账时间
                    userBill.setTransferTime(tTime);
                }
                //设置转账图片
                userBill.setIntroImg(UploadUtils.uploadFile(img,UploadUtils.TRANSFER_IMG));
                
                //更新转账时间和转账后的状态
                userBillService.transferById(userBill);
                result = AjaxResult.success("转账成功");
            }else{
                result = AjaxResult.fail("转账失败！请选择所需转账明细！", 200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("转账失败！", 200);
        }
        return result;
    }
}
