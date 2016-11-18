/**
 * @Title: SysMenuServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月5日 上午9:52:30
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.SysMenu;
import tech.youmu.ckl.mapper.SysMenuMapper;
import tech.youmu.ckl.service.ISysMenuService;
import tech.youmu.ckl.utils.SysMenuTreeUtil;
import tech.youmu.ckl.utils.UserContext;

/**
 * @ClassName: SysMenuServiceImpl
 * @Description: 菜单服务实现类
 * @author youmu-zh
 * @date 2016年8月5日 上午9:52:30
 *
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements ISysMenuService {
	
	private SysMenuMapper menuMapper;
	
	@Autowired
	public SysMenuServiceImpl(SysMenuMapper menuMapper) {
		super(menuMapper);
		this.menuMapper = menuMapper;
	}

	/**
	 * @see tech.youmu.ckl.service.ISysMenuService#getUserMenuTree()
	 */
	@Override
	public List<SysMenu> getUserMenuTree() {
		// 判断用户是否是超级管理员
		
		// 获取用户的权限 判断获取用户有权限的菜单 形成菜单树
		List<SysMenu> menus = menuMapper.getUserSysMenus(UserContext.getUser().getId());
		return SysMenuTreeUtil.toTree(menus);
	}

	/**
	  * @see tech.youmu.ckl.service.ISysMenuService#getAllTree()
	  */
	@Override
	public List<SysMenu> getAllTree() {
		return SysMenuTreeUtil.toTree(menuMapper.getAllMenusTree());
	}

}
