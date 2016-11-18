/**
 * @Title: EmployeeController.java
 * @Package tech.youmu.ckl.web.controller
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-Administrator
 * @date 2016年8月11日 上午10:03:09
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.OrderInfoHistory;
import tech.youmu.ckl.query.EmployeeQuery;
import tech.youmu.ckl.query.OrderInfoHistoryQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IDepartmentService;
import tech.youmu.ckl.service.IEmployeeService;
import tech.youmu.ckl.service.IJobTitleService;
import tech.youmu.ckl.service.IOrderService;
import tech.youmu.ckl.service.IStationService;
import tech.youmu.ckl.utils.DateUtil;
import tech.youmu.ckl.utils.MD5Util;

/**
 * 员工管理
 * @author yjh
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IEmployeeService empService;
	@Autowired
	private IDepartmentService departmentService;
    @Autowired
    private IJobTitleService jobTitleService;
    @Autowired
    private IStationService stationService;
    @Autowired
    private IOrderService orderService;
	/**
	 * 导向页面
	 */
	@RequestMapping("/index.do")
	public String index(String cmd, Long id, Model model){
	    
	    //跳转到员工个人中心界面
	    if(StringUtils.equals("center", cmd)) {
            //根据id获取员工
            model.addAttribute("employee", empService.getWithStationsById(id));
            
           
          //根据员工id获取上个月的平均评价星数
            OrderInfoHistoryQuery orderInfoHistoryQuery = new OrderInfoHistoryQuery();
            orderInfoHistoryQuery.setEmpId(id);
            orderInfoHistoryQuery.setRecordMonth(DateUtil.getLastMonthOfToday());
            List<OrderInfoHistory> orderInfoHisList = orderService.getOrderInfoHis(orderInfoHistoryQuery);
            if(orderInfoHisList != null && orderInfoHisList.size()>0){
                OrderInfoHistory orderInfoHistory = orderInfoHisList.get(0);
                if(orderInfoHistory!=null){
                    model.addAttribute("avgStar",orderInfoHistory.getAvgStar());
                }
            }else{
                model.addAttribute("avgStar",0);
            }
            return "employee/employee_center";
        }
	    //获取所有部门
	    model.addAttribute("depts", departmentService.getAll());
	    //所有职位
	    model.addAttribute("jobs", jobTitleService.getAll());
	    //所有服务站
	    model.addAttribute("stations", stationService.getAll());
	    
	    //所有管理服务站
        model.addAttribute("stationsForManager", stationService.getAll());
	    
		// 跳转到编辑页面
		if(StringUtils.equals("save", cmd)) {
			return "employee/employee_edit";
		}
		
		// 获取数据跳转到编辑页面
		if(StringUtils.equals("update", cmd)) {
		    model.addAttribute("employee", empService.getWithStationsById(id));
			return "employee/employee_edit";
		}
		return "employee/employee";
	}
	
	@RequestMapping("/orderInfo.do")
    public String orderInfo(Long employeeId, Model model){
        model.addAttribute("employeeId", employeeId);
        return "order/order";
    }
	
	@RequestMapping("/salaryInfo.do")
    public String salaryInfo(Long employeeId, Model model){
	    model.addAttribute("employeeId", employeeId);
        return "salary/salary";
    }
	
	/**
	 * 员工列表
	 * @return
	 * @throws ParseException 解析异常
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public PageList<Employee> list(EmployeeQuery empQuery,String beginTimeStr,String endTimeStr) throws ParseException{
		if (beginTimeStr!=null&&!beginTimeStr.isEmpty()) {
            SimpleDateFormat formatBegin = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = formatBegin.parse(beginTimeStr);
            empQuery.setBeginTime(date2);
        }
        if (endTimeStr!=null&&!endTimeStr.isEmpty()) {
            SimpleDateFormat formatEnd = new SimpleDateFormat("yyyy-MM-dd");
            Date date3 = formatEnd.parse(endTimeStr);
            empQuery.setEndTime(date3);
            System.err.println(endTimeStr);
        }
		return empService.getPageList(empQuery);
	}
	
	/**
	 * 修改/添加员工
	 * @param emp
	 * @return
	 */
	@RequestMapping("/edit.do")
	@ResponseBody
	public AjaxResult save(Employee emp){
	    AjaxResult result= null;
	    try{
	            if(emp.getStation() != null && emp.getStations().size() > 0){
	            	result = AjaxResult.fail("请确认该员工为管理人员或非管理人员!");
	            }
	            
	            if(emp.getId()==null){
	                //新增一条员工数据，记录录入时间
	                emp.setCreateTime(new Date());
	                //默认开通
	                emp.setStatus(emp.EMPLOYEE_STATUS_INVOKE);
	                
	                if(empService.findEmployeeByMobileOrNum(emp.getMobile(), emp.getEmpNumber()) != null){
	                	result = AjaxResult.fail("保存失败！该员工信息已存在！" , 200);
	                }
	                if(StringUtils.isBlank(emp.getRealName())){
	                	result = AjaxResult.fail("保存失败！请输入姓名！" , 200);
	                }
	                if(StringUtils.isBlank(emp.getMobile()) || StringUtils.isBlank(emp.getEmpNumber())){
	                	result = AjaxResult.fail("保存失败！员工手机号或工号为空！" , 200);
	                }
	                
	                //初始化员工密码为 ： 123456
	                emp.setPassword(MD5Util.MD5("123456"));
	                
	                //添加员工
	                empService.saveByChecking(emp);
	                result = AjaxResult.success("添加成功");
	            }else{
	                //修改员工
	                empService.updateEmployeeWithStations(emp);
	                result = AjaxResult.success("修改成功");
	            }
	    }catch(Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("保存失败！" , 200);
        }
		return result;
	}
	
	
	
	@RequestMapping("/checkEmployeeByMobileOrNum.do")
	@ResponseBody
	public AjaxResult checkEmployeeByMobileOrNum(Long employeeId ,String mobile,String empNumber){
		AjaxResult result = null;
		//id为空才进行验证重复
		if(employeeId == null){
			System.err.println("接受的mobile :" + mobile);
			System.err.println("查找的员工为："+empService.findEmployeeByMobileOrNum(mobile,empNumber));
			if(empService.findEmployeeByMobileOrNum(mobile,empNumber)!=null){
				return result = AjaxResult.fail("该员工已经存在！");//已存在
	        }
			return result= AjaxResult.success("可以使用！");
		}else{
			return result = AjaxResult.success("不需要验证！");
		}
	}
	
	/**
	 * index(禁用该员工)
	 * @return 操作结果消息
	 */
	@RequestMapping("/disable.do")
	@ResponseBody
	public AjaxResult disable(Long id){
		AjaxResult ajaxResult = new AjaxResult();
		try {
			if(id != null) {
				empService.disableByEmpId(id);
				ajaxResult.setMsg("冻结成功");
			}else {
				ajaxResult.setSuccess(false);
				ajaxResult.setMsg("员工编号不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMsg("操作失败");
		}
		return ajaxResult;
	}
	
	/**
	 * 开通该员工
	 */
	@RequestMapping("/invoke.do")
	@ResponseBody
	public AjaxResult invoke(Long id){
		AjaxResult ajaxResult = new AjaxResult();
		try {
			if(id != null) {
				empService.invokeByEmpId(id);
				ajaxResult.setMsg("开通成功");
			}else {
				ajaxResult.setSuccess(false);
				ajaxResult.setMsg("员工编号不能为空!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setSuccess(false);
			ajaxResult.setMsg("操作失败");
		}
		return ajaxResult;
	}
	
	 	/**
	 	 * 导出员工
	 	 * @param baseQuery
	 	 * @param request
	 	 * @param response
	 	 * @return
	 	 * @throws Exception
	 	 */
	 	@RequestMapping("/download.do")
	 	public void download(EmployeeQuery baseQuery,HttpServletRequest request,
			      HttpServletResponse response) throws Exception {
	 	   empService.download(baseQuery, request, response);
	 	}
	 	
	 	/**
	 	 * 
	 	 *  <p>Author:yjh;</p>
	 	 *  <p>Date:2016年9月22日下午1:45:01;</p>
	 	 *	<p>Description: 下载导入模板;</p>
	 	 *  @param request
	 	 *  @param response
	 	 */               
	 	@RequestMapping("/downloadTemplate.do")
	 	public void downloadTemplate(HttpServletRequest request,
                HttpServletResponse response){
	 	   try {
                empService.downloadTemplate(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
	 	}
	 	
	 	/**
	 	 * 
	 	 *  <p>Author:yjh;</p>
	 	 *  <p>Date:2016年9月22日下午1:44:43;</p>
	 	 *	<p>Description: 导入员工;</p>
	 	 *  @param importFile
	 	 *  @return
	 	 */
	 	@RequestMapping("/import.do")
	 	@ResponseBody
	 	public AjaxResult importExcelFile(MultipartFile importFile){
	 		AjaxResult ajaxResult = new AjaxResult();
	 		if(importFile!=null){
	 			try {
					empService.importExcelFile(importFile);
					ajaxResult.setSuccess(true);
					ajaxResult.setMsg("导入成功！");
				} catch (Exception e) {
					e.printStackTrace();
					ajaxResult.setSuccess(false);
		 			ajaxResult.setMsg("导入异常！");
				}
	 		}else {
	 			ajaxResult.setSuccess(false);
	 			ajaxResult.setMsg("导入失败！文件不能为空！");
			}
	 		return ajaxResult;
	 	} 
	
}
