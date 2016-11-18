package tech.youmu.ckl.mapper;

import tech.youmu.ckl.domain.SysRole;

public interface SysRoleMapper extends BaseMapper {
	
	/**
	  * saveAndGetId(保存一个角色并返回该角色的主键 主键防止role对象的Id中)
	  * @return 该角色的主键
	 */
	public void saveAndGetId(SysRole role);

	/**
	  * setRoleMenus(给系统角色设置相应的菜单权限,不会覆盖之前的设置)
	  * @param role 系统角色
	  */
	public void saveRoleMenus(SysRole role);

	/**
	  * getWithMenusById(通过Id获取系统角色和角色拥有的菜单权限)
	  * @param id 系统角色的id
	  * @return 系统角色
	  */
	public SysRole getWithMenusById(Long id);

	/**
	  * deleteRoleMenusById(通过Id 删除与该系统角色拥有的所有菜单权限)
	  * @param role 系统角色
	  */
	public void deleteRoleMenusById(SysRole role);
	
}