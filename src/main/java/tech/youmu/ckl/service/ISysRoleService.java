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

package tech.youmu.ckl.service;

import tech.youmu.ckl.domain.SysRole;

/**
  * @ClassName: ISysRoleService
  * @Description: 系统角色服务接口
  * @author youmu-zh
  * @date 2016年8月11日 上午9:26:55
  *
  */
public interface ISysRoleService extends IBaseService<SysRole> {

	/**
	  * saveRoleWithMenus(添加一个角色并为该角色设置权限)
	  * @param role 系统角色
	  */
	void saveRoleWithMenus(SysRole role);

	/**
	  * getWithMenusById(通过Id获取系统角色和角色拥有的菜单权限)
	  * @param id 系统角色的id
	  * @return 系统角色
	  */
	SysRole getWithMenusById(Long id);

	/**
	  * updateRoleWithMenus(更新角色记录 和其 关联的权限)
	  *
	  * @param role
	  */
	void updateRoleWithMenus(SysRole role);

}
