/**
 * @Title: MenuController.java
 * @Package tech.youmu.ckl.web.controller
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月2日 上午10:38:05
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.SysMenu;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.ISysMenuService;

/**
  * @ClassName: MenuController
  * @Description: 菜单控制器  <br />
  * 	1. 权限的crud
  * 	2. 根据控制器自动生成权限  自己写一个需要添加权限的资源标签? 
  * 	3. 权限的菜单控制         菜单关联权限         
  * 	4. 数据访问的权限         使用自定义的权限标签? 直接使用角色来划分权限在底层sql查询的时候控制数据的展示?
  * @author youmu-zh
  * @date 2016年8月2日 上午10:38:05
  *
  */
@Controller
@RequestMapping("/menu")
public class SysMenuController {
	
	@Autowired
	ISysMenuService menuService;
	
	/**
	  * index(导向到权限控制页面)
	  * @return 访问的资源地址
	 */
	@RequestMapping("/index.do")
	public String index(){
		return "sys_menu/sys_menu";
	}
	
	/**
	  * index(导向到权限控制页面)
	  * @return 访问的资源地址
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public PageList<SysMenu> list(BaseQuery query){
		return menuService.getPageList(query);
	}
	
	/**
	 * index(导向到权限控制页面)
	 * @return 访问的资源地址
	 */
	@RequestMapping("/tree.do")
	@ResponseBody
	public List<SysMenu> tree(){
		return menuService.getAllTree();
	}
	
	/**
	 * index(权限修改/添加操作)
	 * @return 访问的资源地址
	 */
	@RequestMapping("/edit.do")
	@ResponseBody
	public AjaxResult edit(SysMenu menu){
		AjaxResult ajaxResult = new AjaxResult();
		try {
			if(menu.getId() == null) {
				// 新增
			    menu.setCreateTime(new Date());
				menuService.save(menu);
				ajaxResult.setMsg("添加成功");
			}else {
				// 修改
				menuService.updateById(menu);
				ajaxResult.setMsg("修改成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMsg("操作失败");
		}
		return ajaxResult;
	}
	
	/**
     * index(权限修改/添加操作)
     * @return 访问的资源地址
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public AjaxResult delete(Long id){
        try {
            if(id != null) {
                menuService.deleteById(id);
                return AjaxResult.success("删除成功");
            }else {
                return AjaxResult.fail("参数错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
}
