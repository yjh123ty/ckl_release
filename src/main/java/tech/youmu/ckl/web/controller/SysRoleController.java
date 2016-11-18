/**
 * @Title: SysRoleController.java
 * @Package tech.youmu.ckl.web.controller
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月12日 上午10:20:23
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.SysRole;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.SysRoleQuery;
import tech.youmu.ckl.service.IAdminService;
import tech.youmu.ckl.service.ISysMenuService;
import tech.youmu.ckl.service.ISysRoleService;

/**
  * @ClassName: SysRoleController
  * @Description: 系统角色管理的控制器
  * @author youmu-zh
  * @date 2016年8月12日 上午10:20:23
  *
  */

@Controller
@RequestMapping("/role")
public class SysRoleController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private ISysRoleService sysRoleService;
	
	@Autowired
	ISysMenuService sysMenuService;
	
	@Autowired
	private IAdminService adminService;
	
	/**
	  * index(跳转到角色管理界面)
	  * @return 角色管理jsp的相对路径
	 */
	@RequestMapping("/index.do")
	public String index(String cmd, Long id, Model model){
		logger.debug("index... to " + cmd);
		// 直接跳转到编辑页面
		if(StringUtils.equals("save", cmd)) {
			model.addAttribute("menus", sysMenuService.getAllTree());
			return "sys_role/sys_role_edit";
		}
		
		// 获取数据跳转到编辑页面
		if(StringUtils.equals("update", cmd)) {
			model.addAttribute("menus", sysMenuService.getAllTree());
			model.addAttribute("role", sysRoleService.getWithMenusById(id));
			return "sys_role/sys_role_edit";
		}
		
		// 跳转到员工授权列表
		if(StringUtils.equals("grantlist", cmd)) {
			return "sys_role/emp_sys_role";
		}
		
		// 跳转到员工授权列表
		if(StringUtils.equals("grant", cmd)) {
			model.addAttribute("roles", sysRoleService.getAll());
			return "sys_role/sys_role_grant";
		}
		
		// 跳转到员工授权修改
		if(StringUtils.equals("grantUpdate", cmd)) {
			model.addAttribute("roles", sysRoleService.getAll());
			model.addAttribute("emp", adminService.getById(id));
			return "sys_role/sys_role_grant";
		}
		
		// 跳转到角色列表界面
		return "sys_role/sys_role";
	}
	
	/**
	 * list(获取角色管理 列表数据)
	 * @return 
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public PageList<SysRole> list(SysRoleQuery query){
		return sysRoleService.getPageList(query);
	}
	
	/**
	 * list(角色 添加 赋予权限 或者 修改)
	 * @return 
	 */
	@RequestMapping("/edit.do")
	@ResponseBody
	public AjaxResult edit(@Valid @NotNull(message="{parameter.not.null}") SysRole role, BindingResult br){
		logger.debug("edit.do  系统角色参数：" + role);
		AjaxResult result = null;
		if(br.hasErrors()) {
			return AjaxResult.error(br);
		}
		Long id = role.getId();
		try {
			if(id == null) {
				role.setCreateTime(new Date());
				role.setIsDel(false);
				sysRoleService.saveRoleWithMenus(role);
				result = AjaxResult.success("添加成功");
			}else {
				sysRoleService.updateRoleWithMenus(role);
				result = AjaxResult.success("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = AjaxResult.fail("服务异常！");
		}
		return result;
	}
}
