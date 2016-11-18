/**
 * @Title: DepartmentController.java
 * @Package tech.youmu.ckl.web.controller
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-Administrator
 * @date 2016年8月10日 下午6:13:45
 * @version V1.0
 */

package tech.youmu.ckl.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Department;
import tech.youmu.ckl.query.DepartmentQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IDepartmentService;

/**
 * @author yjh
 *
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private IDepartmentService departmentService;
	
	/**
	 * 部门导向
	 * @return
	 */
	@RequestMapping("/index.do")
	public String index(String cmd, Long id, Model model){
	    // 跳转到编辑页面
        if(StringUtils.equals("save", cmd)) {
            return "department/department_edit";
        }
        
        // 获取数据跳转到编辑页面
        if(StringUtils.equals("update", cmd)) {
            model.addAttribute("dept", departmentService.getById(id));
            return "department/department_edit";
        }
		return "department/department";
	}
	
	/**
	 * 获取所有部门
	 */
	@RequestMapping("/getDataTree.do")
	@ResponseBody
	public List<Department> list(){
		return departmentService.getAll();
	}
	
	/**
     * 部门列表
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<Department> list(DepartmentQuery departmentQuery,String beginTimeStr,String endTimeStr) throws Exception{
        if (beginTimeStr!=null&&!beginTimeStr.isEmpty()) {
            SimpleDateFormat formatBegin = new SimpleDateFormat("yyyy-MM-dd");
            Date bdate = formatBegin.parse(beginTimeStr);
            departmentQuery.setBeginTime(bdate);
        }
        if (endTimeStr!=null&&!endTimeStr.isEmpty()) {
            SimpleDateFormat formatEnd = new SimpleDateFormat("yyyy-MM-dd");
            Date edate = formatEnd.parse(endTimeStr);
            departmentQuery.setEndTime(edate);
            System.err.println(endTimeStr);
        }
        return departmentService.pageList(departmentQuery);
    }
    
    /**
     * 修改/添加部门
     * @param user
     * @return
     */
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(Department dept){
        AjaxResult result = null;
        try{
            if(dept.getId()==null){
                
                if(StringUtils.isBlank(dept.getName())){
                    result = AjaxResult.fail("请输入部门名称！");
                }
                //新增一条部门数据，记录录入时间
                dept.setCreateTime(new Date());
                //添加
                departmentService.save(dept);
                result = AjaxResult.success("添加成功");
            }else{
                //修改
                departmentService.updateById(dept);
                result = AjaxResult.success("修改成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("服务异常！" , 200);
        }
        return result;
    }
}
