/**
 * @Title: ServiceEvaluationController.java
 * @Package tech.youmu.ckl.web.controller
 * 
 * @author yjh
 * @date 2016年8月29日 下午10:59:31
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Department;
import tech.youmu.ckl.domain.Performance;
import tech.youmu.ckl.domain.ServiceEvaluation;
import tech.youmu.ckl.domain.ServiceType;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.ServiceEvaluationQuery;
import tech.youmu.ckl.service.IServiceEvaluationService;
import tech.youmu.ckl.service.IServiceTypeService;

/**
 * 
 * <p>Title:ServiceEvaluationController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月30日上午10:25:46</p>
 * <p>Description:服务评价管理</p>
 */
@Controller
@RequestMapping("/serviceEvaluation")
public class ServiceEvaluationController {
    
    @Autowired
    private IServiceEvaluationService serviceEvaluationService;
    
    @Autowired
    private IServiceTypeService serviceTypeService;
	
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月31日上午10:18:59;</p>
     *	<p>Description:导向页面;</p>
     *  @return
     */
	@RequestMapping("/index.do")
	public String index(){
		return "serviceEvaluation/serviceEvaluation";
	}
	
	@RequestMapping("/serviceEvaluationInfo.do")
    public String serviceEvaluationInfo(Long employeeId,Long userId, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("employeeId", employeeId);
        return "serviceEvaluation/serviceEvaluation";
    }
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年8月31日上午10:19:07;</p>
	 *	<p>Description: 服务评价列表;</p>
	 *  @param seQuery 查询对象
	 *  @param beginTimeStr 开始日期
	 *  @param endTimeStr  截止日期
	 *  @return  列表
	 *  @throws Exception
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public PageList<ServiceEvaluation> list(ServiceEvaluationQuery seQuery,String beginTimeStr,String endTimeStr) throws Exception{
	    seQuery.setBeginTimeStr(beginTimeStr);
	    seQuery.setEndTimeStr(endTimeStr);
        return serviceEvaluationService.getPageList(seQuery);
    }
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年8月30日下午3:44:11;</p>
	 *	<p>Description: 获取所有服务类型;</p>
	 *  @return
	 */
    @RequestMapping("/getAllServiceType.do")
    @ResponseBody
    public List<ServiceType> list(){
        return serviceTypeService.getAll();
    }
	
	
	
}
