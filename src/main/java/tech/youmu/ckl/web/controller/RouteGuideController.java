/**
 * @Title: RouteController.java
 * @Package tech.youmu.ckl.web.controller
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月16日 下午5:19:32
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.RouteGuideItem;
import tech.youmu.ckl.domain.RouteGuideSubItem;
import tech.youmu.ckl.service.IRouteGuideService;

/**
  * @ClassName: RouteController
  * @Description: 路线管理
  * @author youmu-zh
  * @date 2016年8月16日 下午5:19:32
  *
  */
@Controller
@RequestMapping("/routeguide")
public class RouteGuideController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IRouteGuideService routeGuideService;
	
	/**
	  * 路线管理导向
	  * index(路线模块界面导向)
	  * @return
	 */
	@RequestMapping("/index.do")
	public String index(String cmd, Long id, Long routeId, Long superId, Model model){
	    model.addAttribute("routeId", routeId);
	    
	    if("saveItem".equals(cmd)) {
	       return "route_guide/edit_item";
	    }
	    
	    if("editItem".equals(cmd)) {
	            model.addAttribute("item", routeGuideService.getRouteGuideItem(id));
	           return "route_guide/edit_item";
        }
	    
        if ("saveSubItem".equals(cmd)) {
            model.addAttribute("superId", superId);
            return "route_guide/edit_sub_item";
        }

        if ("editSubItem".equals(cmd)) {
            model.addAttribute("item", routeGuideService.getRouteGuideSubItem(id));
            return "route_guide/edit_sub_item";
        }
	    
	    if("deleteItem".equals(cmd)){
	        routeGuideService.deleteRouteGuideItem(id);
	    }else
	    if("deleteItem".equals(cmd)){
            routeGuideService.deleteRouteGuideItem(id);
        }else
        if("deleteSubItem".equals(cmd)){
            routeGuideService.deleteRouteGuideSubItem(id);
        }
	    model.addAttribute("items", routeGuideService.findRouteGuide(routeId));
        return "route_guide/list";
	}
	
	@RequestMapping("/editItem.do")
	@ResponseBody
	public AjaxResult editItem(@RequestParam(value="image",required=false) MultipartFile image, RouteGuideItem item, Model model){
	    try {
            if(item.getId() == null) {
                routeGuideService.saveRouteGuideItem(image, item);
                return AjaxResult.success("保存成功");
            }else {
                routeGuideService.updateRouteGuideItem(image, item);
                return AjaxResult.success("修改成功");
            }
        } catch (Exception e) {
            logger.error("攻略标题编辑异常：", e);
            return AjaxResult.fail("系统异常");
        }
	}
	
	
	@RequestMapping("/editSubItem.do")
    @ResponseBody
    public AjaxResult editSubItem(@RequestParam(value="image",required=false) MultipartFile image, RouteGuideSubItem item, Model model){
        try {
            if(item.getId() == null) {
                routeGuideService.saveRouteGuideSubItem(image, item);
                return AjaxResult.success("保存成功");
            }else {
                routeGuideService.updateRouteGuideSubItem(image, item);
                return AjaxResult.success("修改成功");
            }
        } catch (Exception e) {
            logger.error("攻略子标题编辑异常：", e);
            return AjaxResult.fail("系统异常");
        }
    }
}
