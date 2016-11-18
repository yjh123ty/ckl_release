/**
 * @Title: SysMenuTreeUtil.java
 * @Package tech.youmu.ckl.utils
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月16日 上午10:37:56
 * @version V1.0
 */

package tech.youmu.ckl.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tech.youmu.ckl.domain.SysMenu;

/**
  * @ClassName: SysMenuTreeUtil
  * @Description: 系统菜单树的工具
  * @author youmu-zh
  * @date 2016年8月16日 上午10:37:56
  *
  */

public class SysMenuTreeUtil {
	
	/**
	  * toTree(将查询的菜单列表 变成树型菜单列表)
	  * @return 树型菜单列表
	 */
	public static List<SysMenu> toTree(List<SysMenu> menus){
		if(menus == null || menus.size() == 0) {
			return new ArrayList<>(0);
		}
		// 形成菜单树
		Iterator<SysMenu> iterator = menus.iterator();
		while (iterator.hasNext()) {
			SysMenu child = iterator.next();
			// 将儿子添加到父菜单中
			if (child.getParent() != null) {
				for (SysMenu parent : menus) {
					if (child.getParent().getId().equals(parent.getId())) {
						child.setParent(null);
						parent.getChildren().add(child);
						break;
					}
				}
				// 移除儿子
				iterator.remove();
			}
		}
		return menus;
	}
}
