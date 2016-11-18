/**
 * @Title: ISysRoleService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月11日 上午9:26:55
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.SysMenu;
import tech.youmu.ckl.domain.SysRole;
import tech.youmu.ckl.mapper.SysRoleMapper;
import tech.youmu.ckl.service.ISysRoleService;
import tech.youmu.ckl.systemlog.SystemLog;

/**
  * @ClassName: ISysRoleService
  * @Description: 角色服务实现类
  * @author youmu-zh
  * @date 2016年8月11日 上午9:26:55
  *
  */
@Service
@SystemLog("系统角色管理")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements ISysRoleService {

	private SysRoleMapper sysRoleMapper;

	/**
	  * 创建一个新的实例 ISysRoleService. 
	  * @param sysRoleMapper 系统角色mapper
	  */
	@SuppressWarnings("unchecked")
    @Autowired
	public SysRoleServiceImpl(SysRoleMapper sysRoleMapper) {
		super(sysRoleMapper);
		this.sysRoleMapper = sysRoleMapper;
	}

	/**
	  * @see tech.youmu.ckl.service.ISysRoleService#saveRoleWithMenus(tech.youmu.ckl.domain.SysRole)
	  */
	@Override
	public void saveRoleWithMenus(SysRole role) {
		// 1. 添加并获取主键
		// 2. 根据主键建立和menus的一对多的关系
		sysRoleMapper.saveAndGetId(role);
		List<SysMenu> menus = role.getMenus();
		// 校验菜单列表是否有数据
		if(menus != null && menus.size() > 0) {
			sysRoleMapper.saveRoleMenus(role);
		}
	}

	/**
	  * @see tech.youmu.ckl.service.ISysRoleService#getWithMenusById(java.lang.Long)
	  */
	@Override
	public SysRole getWithMenusById(Long id) {
		return sysRoleMapper.getWithMenusById(id);
	}

	/**
	  * @see tech.youmu.ckl.service.ISysRoleService#updateRoleWithMenus(tech.youmu.ckl.domain.SysRole)
	  */
	@Override
	public void updateRoleWithMenus(SysRole role) {
		/**
		 * 1、更新角色的基础属性 直接调用updateById
		 * 2、更新角色关联的菜单权限
		 * 	2.1、删除原有的关联(角色关联菜单) deleteRoleMenus(SysRole role)
		 *  2.2、添加现有的关联关系 saveRoleMenus(SysRole role)
		 */
		updateById(role);
		sysRoleMapper.deleteRoleMenusById(role);
		List<SysMenu> menus = role.getMenus();
		// 校验菜单列表是否有数据
		if (menus != null && menus.size() > 0) {
			sysRoleMapper.saveRoleMenus(role);
		}
	}
}
