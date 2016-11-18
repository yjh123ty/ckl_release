/**
 * @Title: LoginController.java
 * @Package tech.youmu.ckl.web.controller
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月3日 上午9:59:01
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.exception.LogicException;
import tech.youmu.ckl.service.IAdminService;
import tech.youmu.ckl.utils.MD5Util;
import tech.youmu.ckl.utils.UserContext;

/**
 * 用户登录
 * @author yjh
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private IAdminService adminService;
	
	@RequestMapping("/index.do")	
	public String index(){
		return "forward:/login.jsp";
	}
	
	/**
	 *  <p>Author:zh;</p>
	 *  <p>Date:2016年8月22日上午11:22:12;</p>
	 *	<p>Description: 员工登录检查;</p>
	 *  @param mobile 用户电话
	 *  @param password 密码
	 *  @return
	 */
	@RequestMapping("/checkLogin.do")
	@ResponseBody
	public AjaxResult checkLogin(String mobile,String password){
	    
	    if(StringUtils.isBlank(mobile)){
	        return AjaxResult.fail("用户名不能为空！"); 
	    }
	    
	    if(StringUtils.isBlank(password)){
            return AjaxResult.fail("密码不能为空！"); 
        }
	    
		try{
			// 检查登录
			Employee admin = adminService.checkLogin(mobile);
			
			//系统内无该员工
			if(admin == null){
				return AjaxResult.fail("登录失败，系统内无该员工！"); 
			}
			
			if(admin.getRole() == null || admin.getRole().getId() == 0) {
			    return AjaxResult.fail("权限不足！");
			}
			
			// 对密码进行加密
			password = MD5Util.MD5(password);
			
			//密码不正确
			if(!password.equals(admin.getPassword())){
				return AjaxResult.fail("登录失败，密码不正确！");
			}
			//登录了把用户放入session
			UserContext.setUser(admin);
			// 更改登录信息
			adminService.login(admin);
			// 封装响应信息
			return AjaxResult.success("登录成功");
		}catch(LogicException e){
			// 封装响应信息
			return AjaxResult.fail("登录失败，"+e.getMessage()+"!", e.getErrorCode());
		}
	}
}
