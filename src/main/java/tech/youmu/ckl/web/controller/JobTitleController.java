/**
 * @Title: JobTitleController.java
 * @Package tech.youmu.ckl.web.controller
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月16日 上午10:45:30
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
import tech.youmu.ckl.domain.JobTitle;
import tech.youmu.ckl.query.JobTitleQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IDepartmentService;
import tech.youmu.ckl.service.IJobTitleService;

/**
 * 职位管理
 * @author youmu-yjh
 * 
 */
@Controller
@RequestMapping("/jobTitle")
public class JobTitleController {
    @Autowired
    private IJobTitleService jobTitleService;
    
    @Autowired
    private IDepartmentService departmentService; 
    
    /**
     * 导向
     * @return
     */
    @RequestMapping("/index.do")
    public String index(String cmd, Long id, Model model){
        // 直接跳转到编辑页面
        if(StringUtils.equals("save", cmd)) {
            return "jobTitle/jobTitle_edit";
        }
        
        // 获取数据跳转到编辑页面
        if(StringUtils.equals("update", cmd)) {
            //通过id获得要修改的职位
            model.addAttribute("jobTitle", jobTitleService.getById(id));
            return "jobTitle/jobTitle_edit";
        }
        return "jobTitle/jobTitle";
    }
    
    /**
     * 职位列表
     * @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<JobTitle> list(JobTitleQuery jobTitleQuery,String beginTimeStr,String endTimeStr) throws Exception{
        if (beginTimeStr!=null&&!beginTimeStr.isEmpty()) {
            SimpleDateFormat formatBegin = new SimpleDateFormat("yyyy-MM-dd");
            Date bdate = formatBegin.parse(beginTimeStr);
            jobTitleQuery.setBeginTime(bdate);
        }
        if (endTimeStr!=null&&!endTimeStr.isEmpty()) {
            SimpleDateFormat formatEnd = new SimpleDateFormat("yyyy-MM-dd");
            Date edate = formatEnd.parse(endTimeStr);
            jobTitleQuery.setEndTime(edate);
            System.err.println(endTimeStr);
        }
        return jobTitleService.pageList(jobTitleQuery);
    }
    
    /**
     * 修改/添加职位
     * @param user
     * @return
     */
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(JobTitle jobTitle){
        AjaxResult result = null;
        try{
            if(jobTitle.getId()==null){
                //新增一条职位数据，记录录入时间
                jobTitle.setCreateTime(new Date());
                //添加职位
                jobTitleService.save(jobTitle);
                result = AjaxResult.success("添加成功");
            }else{
                //修改职位
                jobTitleService.updateById(jobTitle);
                result = AjaxResult.success("修改成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("添加失败！" , 200);
        }
        return result;
    }
    
    @RequestMapping("/getAll.do")
    @ResponseBody
    public List<JobTitle> getAll(){
        return jobTitleService.getAll();
    }
    
}
