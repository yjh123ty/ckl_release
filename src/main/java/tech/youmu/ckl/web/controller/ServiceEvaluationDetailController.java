/**
 * @Title: ServiceEvaluationDetailController.java
 * @Package tech.youmu.ckl.web.controller
 * 
 * @author yjh
 * @date 2016年8月30日 下午9:58:24
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.ServiceEvaluation;
import tech.youmu.ckl.domain.ServiceEvaluationDetail;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IServiceEvaluationDetailService;

/**
 * 服务评价明细管理
 * @author yjh
 *
 */
@Controller
@RequestMapping("/serviceEvaluationDetail")
public class ServiceEvaluationDetailController {
	
	@Autowired
	private IServiceEvaluationDetailService serviceEvaluationDetailService;
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年8月31日上午10:23:52;</p>
	 *	<p>Description:星数展示弹框页面;</p>
	 *  @return
	 */
	@RequestMapping("index.do")
	public String index(Model model,Long orderId){
	    model.addAttribute("orderStars",serviceEvaluationDetailService.getListByOrderId(orderId));
	    return "/serviceEvaluation/order_star";
	}
	
}
