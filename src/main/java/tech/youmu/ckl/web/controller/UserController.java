/**
 * @Title: UserController.java
 * @Package tech.youmu.ckl.web.controller
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-Administrator
 * @date 2016年8月2日 上午10:42:29
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.app.vo.OrderInfo;
import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Order;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.domain.UserBillBalanceInfo;
import tech.youmu.ckl.domain.UserCenter;
import tech.youmu.ckl.query.OrderQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.UserBillQuery;
import tech.youmu.ckl.query.UserQuery;
import tech.youmu.ckl.service.IOrderService;
import tech.youmu.ckl.service.IUserBillService;
import tech.youmu.ckl.service.IUserService;

/**
 * @author yjh
 *
 */
@Controller
@RequestMapping("/user")
public class UserController{
	
	@Autowired
	private IUserService userService;
	
	@Autowired
    private IUserBillService userBillService;
	
	@Autowired
    private IOrderService orderService;
	
	/**
	 * 用户导向
	 * @return
	 */
	@RequestMapping("/index.do")
	public String index(String cmd,Long id,Model model){
	    if(StringUtils.equals("center", cmd)) {
	    	
            UserCenter userCenter = userService.getUserCenterByUserId(id);
            model.addAttribute("userCenter",userCenter);
            
          //跳转至用户个人中心
            return "user/user_center";
        }
		return "user/user";
	}
	
	@RequestMapping("/orderInfo.do")
    public String orderInfo(Long userId, Model model){
        model.addAttribute("userId", userId);
        return "order/order";
    }
	
	/**
	 * 用户列表
	 * @return
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public PageList<User> list(UserQuery userQuery,String beginTimeStr,String endTimeStr) throws Exception{
		if (beginTimeStr!=null&&!beginTimeStr.isEmpty()) {
            SimpleDateFormat formatBegin = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = formatBegin.parse(beginTimeStr);
            userQuery.setBeginTime(date2);
        }
        if (endTimeStr!=null&&!endTimeStr.isEmpty()) {
            SimpleDateFormat formatEnd = new SimpleDateFormat("yyyy-MM-dd");
            Date date3 = formatEnd.parse(endTimeStr);
            userQuery.setEndTime(date3);
            System.err.println(endTimeStr);
        }
		return userService.pageList(userQuery);
	}
	
	/**
	 * 修改/添加用户
	 * @param user
	 * @return
	 */
//	@RequestMapping("/save.do")
//	@ResponseBody
//	public AjaxResult save(User user){
//	    AjaxResult result = null;
//		if(user.getId()==null){
//			//新增一条用户数据，记录录入时间
//			user.setRegTime(new Date());
//			//保存之前，查询数据库，进行用户名过重验证,返回数据库中对应的用户
//			User checkedUser = userService.checkLogin(user.getMobile());
//			if(checkedUser != null){
//			    result = AjaxResult.fail("该用户名已经存在！！",500);
//			}
//			//添加用户
//			userService.save(user);
//			result = AjaxResult.success("保存成功");
//		}else{
//			//修改用户
//			userService.updateById(user);
//			result = AjaxResult.success("修改成功");
//		}
//		return result;
//	}
	
	/**
	 * index(禁用该用户)
	 * @return 操作结果消息
	 */
	@RequestMapping("/disable.do")
	@ResponseBody
	public AjaxResult disable(Long id){
		AjaxResult ajaxResult = new AjaxResult();
		try {
			if(id != null) {
				userService.disableById(id);
				ajaxResult.setMsg("禁用成功");
			}else {
				ajaxResult.setSuccess(false);
				ajaxResult.setMsg("用户编号不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMsg("操作失败");
		}
		return ajaxResult;
	}
	
	/**
	 * 开通该用户
	 */
	@RequestMapping("/invoke.do")
	@ResponseBody
	public AjaxResult invoke(Long id){
		AjaxResult ajaxResult = new AjaxResult();
		try {
			if(id != null) {
				userService.invokeById(id);
				ajaxResult.setMsg("开通成功");
			}else {
				ajaxResult.setSuccess(false);
				ajaxResult.setMsg("用户编号不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMsg("操作失败");
		}
		return ajaxResult;
	}
}
