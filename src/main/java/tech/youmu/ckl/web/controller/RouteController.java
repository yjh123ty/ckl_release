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


import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import tech.youmu.ckl.domain.Route;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.RouteQuery;
import tech.youmu.ckl.service.IRouteService;
import tech.youmu.ckl.service.ISysDicService;
import tech.youmu.ckl.utils.LogUtils;
import tech.youmu.ckl.utils.UploadUtils;

/**
  * @ClassName: RouteController
  * @Description: 路线管理
  * @author youmu-zh
  * @date 2016年8月16日 下午5:19:32
  *
  */
@Controller
@RequestMapping("/route")
public class RouteController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IRouteService routeService;
	
	@Autowired
    private ISysDicService sysDicService;
	
	/**
	  * 路线管理导向
	  * index(路线模块界面导向)
	  * @return
	 */
	@RequestMapping("/index.do")
	public String index(String cmd, Long id, Model model){
		logger.info("路线导向 cmd : " + cmd + " id : " + id);
		if(StringUtils.equals("save", cmd)) {
		    model.addAttribute("routeSuit", sysDicService.getDetailsByTypeName("route_suit"));
			return "route/route_edit";
		}
		if(StringUtils.equals("update", cmd)) {
		    // 获取 路线适合人群的 数据
		    model.addAttribute("routeSuit", sysDicService.getDetailsByTypeName("route_suit"));
			model.addAttribute("route", routeService.getById(id));
			return "route/route_edit";
		}
		return "route/route";
	}
	
	/**
	  * list(路线列表 查询 分页方法)
	  * @return
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public PageList<Route> list(RouteQuery query){
		System.out.println("路线查询参数： "+query);
		PageList<Route> pageList = routeService.getPageList(query);
		LogUtils.getInstance(getClass()).debugBean(pageList);
		return pageList;
	}
	
	/**
	  * list(路线添加/修改方法)
	  * @return
	 */
	@RequestMapping("/edit.do")
	@ResponseBody
	public AjaxResult edit(@RequestParam(value="image",required=false) MultipartFile img, Route route){
		try {
			// 添加/修改路线
			if(route.getId() == null) {
			    if(img == null || img.isEmpty()) {
			        return AjaxResult.fail("路线图片不能为空");
			    }
				routeService.save(img, route);
				return AjaxResult.success("添加成功");
			} else {
				routeService.updateById(img, route);
				return AjaxResult.success("修改成功");
			}
		} catch (BizExecption e) {
		    logger.error("路线编辑异常：", e);
			// 返回结果
			return AjaxResult.fail(e.getMessage());
		} catch (Exception e) {
		    logger.error("路线编辑异常：", e);
            // 返回结果
            return AjaxResult.fail("系统异常");
        }
	}
	
	/**
	  * getTemplateRoutes(获取所有已经定义好的模板路线)
	  * @return
	 */
	@RequestMapping("/getTemplateRoutes.do")
	@ResponseBody
	public List<Route> getTemplateRoutes(){
		return routeService.getAllTemplateRoutes();
	}
	
	@RequestMapping("/getRouteById.do")
	@ResponseBody
	public Route getRouteById(Long id){
		return routeService.getById(id);
	}
	
	@RequestMapping("/getAllRoute.do")
    @ResponseBody
    public List<Route> getAllRoute(Long id){
        return routeService.getAll();
    }
	
	/**
	  * addTemplateRoute(添加一条模板线路)
	  * @return
	 */
	@RequestMapping("/addTemplateRoute.do")
	@ResponseBody
	public AjaxResult addTemplateRoute(Route route){
		try {
			// 路线基本设置
			// 添加路线
			route.setCreateTime(new Date());
			// 添加路线和点之间的关联
			routeService.saveTemplateRoute(route);
		} catch (Exception e) {
			logger.debug("模板路线保存异常： ", e);
			return AjaxResult.fail(e.getMessage());	
		}
		return AjaxResult.success("模板路线保存成功");
	}
	
}
