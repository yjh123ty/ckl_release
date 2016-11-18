package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.SysMenu;

public interface SysMenuMapper extends BaseMapper {

	/**
	  * getUserSysMenus(获取用户所有菜单 用来形成树)
	  * @param id 用户id
	  * @return 菜单列表
	  */
	List<SysMenu> getUserSysMenus(Long id);
	
	/**
	 * getAllMenusTree(获取所有菜单 用来形成树)
	 * @return 菜单列表
	 */
	List<SysMenu> getAllMenusTree();
}
