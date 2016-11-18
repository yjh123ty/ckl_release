/**
 * @Title: IMenuService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月4日 下午6:29:59
 * @version V1.0
 */

package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.domain.SysMenu;

/**
  * @ClassName: IMenuService
  * @Description: 菜单的操作服务
  * @author youmu-zh
  * @date 2016年8月4日 下午6:29:59
  *
  */

public interface ISysMenuService extends IBaseService<SysMenu> {

	/**
	  * getUserMenuTree(获取当前用户的菜单树)
	  *
	  * @return List<Menu> 用户可以看见的菜单树
	  */
	List<SysMenu> getUserMenuTree();

	/**
	  * getAllTree(拿到所有的菜单形成树)
	  * @return
	  */
	List<SysMenu> getAllTree();

}
