package tech.youmu.ckl.web.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.service.impl.SalaryDisplayServiceImpl;

/**
 * 工资计算演示
 * @author yjh
 *
 */
@Controller
@RequestMapping("/salaryDisplay")
public class SalaryDisplayController {
	
	@Autowired
	private SalaryDisplayServiceImpl salaryDisplayService;
	
	@RequestMapping("/run.do")
	@ResponseBody
	public AjaxResult display(){
		salaryDisplayService.setEmpOrderInfoAndSalary();
		return AjaxResult.success("计算完成！");
	}
}
