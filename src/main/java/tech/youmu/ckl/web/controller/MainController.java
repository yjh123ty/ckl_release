/**
 * @Title: MainController.java
 * @Package tech.youmu.ckl.web.controller
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 下午4:40:57
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.SysMenu;
import tech.youmu.ckl.service.IAdminTaskService;
import tech.youmu.ckl.service.ISysMenuService;
import tech.youmu.ckl.utils.UserContext;

/**
 * 车刻丽后台管理系统主页
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	private ISysMenuService menuService;
	
	@Autowired
	private IAdminTaskService adminTaskService;
	
	/**
	 * 页面导向
	 * @return
	 */
	@RequestMapping("/index.do")
	public String index(String cmd,Model model){
	    model.addAttribute("taskCount", adminTaskService.getTaskCount(UserContext.getUser().getId()));
		return "main";
	}
	
	/**
	  * getUserMenus(获取当前用户的所有的菜单)
	  *
	  * @return List<Menu> 用户对应的菜单列表
	 */
	@RequestMapping("/getUserMenuTree.do")
	@ResponseBody
	public List<SysMenu> getUserMenus(){
		return menuService.getUserMenuTree();
	}
	
	/**
	 * mainInfo(当前系统的一些信息)
	 *
	 * @return List<Menu> 用户对应的菜单列表
	 */
	@RequestMapping("/mainInfo.do")
	public String mainInfo(){
		return "main_info";
	}
}
