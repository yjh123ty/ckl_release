/**
 * @Title: SalaryController.java
 * @Package tech.youmu.ckl.web.controller
 * 
 * @author yjh
 * @date 2016年9月10日 下午9:30:30
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.constants.Global;
import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.Salary;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.SalaryQuery;
import tech.youmu.ckl.service.IEmployeeService;
import tech.youmu.ckl.service.ISalaryService;

/**
 * @author yjh
 *
 */
@Controller
@RequestMapping("/salary")
public class SalaryController {
	
	@Autowired
	private ISalaryService salaryService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping("/index.do")
	public String index(Long id,String cmd,Model model){
	    
	    // 获取数据跳转到编辑页面
        if(StringUtils.equals("update", cmd)) {
            model.addAttribute("salary", salaryService.getById(id));
            return "salary/salary_edit";
        }
		return "salary/salary";
	}
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年9月22日下午6:02:03;</p>
	 *	<p>Description: 工资列表;</p>
	 *  @param salaryQuery
	 *  @param beginTimeStr
	 *  @param endTimeStr
	 *  @return
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public PageList<Salary> list(SalaryQuery salaryQuery,String searchTimeStr, String beginTimeStr,String endTimeStr){
	    salaryQuery.setSearchTime(searchTimeStr);
		salaryQuery.setBeginTimeStr(beginTimeStr);
		salaryQuery.setEndTimeStr(endTimeStr);
		return salaryService.getPageList(salaryQuery);
	}
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年10月11日下午4:14:12;</p>
	 *	<p>Description: 编辑工资;</p>
	 *  @param salary
	 *  @return
	 */
	@RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(Salary salary){
        AjaxResult result = null;
        try{
            if(salary.getId()==null){
                result = AjaxResult.fail("请选择一条工资记录！");
            }else{
                
                System.err.println("编辑之前，该员工的实发工资为： "+salary.getRealSalary());
                
                //重新计算实发工资
                BigDecimal realSalary 
                    = salary.getBaseSalary()
                            .add(salary.getPointSalary()!=null?salary.getPointSalary():Global.BIG_DECIMAL_ZORE)
                            .add(salary.getPerformanceSalary()!=null?salary.getPerformanceSalary():Global.BIG_DECIMAL_ZORE)
                            .add(salary.getAllowance()!=null?salary.getAllowance():Global.BIG_DECIMAL_ZORE)
                            .add(salary.getBouns()!=null?salary.getBouns():Global.BIG_DECIMAL_ZORE)
                            .subtract(salary.getDeductSalary()!=null?salary.getDeductSalary():Global.BIG_DECIMAL_ZORE);
                
                System.err.println("编辑之后，该员工的实发工资为： "+realSalary);
                
                salary.setRealSalary(realSalary);
                //修改
                salaryService.updateById(salary);
                
                //修改员工总工资
                Long employeeId = salary.getEmployee().getId();
                Employee employee = employeeService.getEmployeeInfoById(employeeId);
                if(employee == null){
                    result = AjaxResult.fail("没有该员工的工资记录!");
                }else {
                    BigDecimal salaryInCount = employee.getSalaryInCount()!=null?employee.getSalaryInCount():Global.BIG_DECIMAL_ZORE;
                    employee.setSalaryInCount(salaryInCount.add(realSalary));
                    employeeService.update(employee);
                    result = AjaxResult.success("修改成功!");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("修改失败！" , 200);
        }
        return result;
    }
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年9月22日下午6:01:54;</p>
	 *	<p>Description: 导出工资表;</p>
	 *  @param baseQuery
	 *  @param request
	 *  @param response
	 *  @throws Exception
	 */
	@RequestMapping("/download.do")
	public void download(SalaryQuery baseQuery,HttpServletRequest request,
            HttpServletResponse response) throws Exception {
	    salaryService.download(baseQuery, request, response);
	}
}	
