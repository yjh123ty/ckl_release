/**
 * Project Name:ckl
 * File Name:AdminTaskController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AdminTask;
import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.service.IAdminTaskService;
import tech.youmu.ckl.utils.UserContext;

/**
 * <p>Title:AdminTaskController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月24日上午9:27:23</p>
 * <p>Description:后台任务控制器</p>
 */
@RequestMapping("/adminTask")
@Controller
public class AdminTaskController {
    
    @Autowired
    private IAdminTaskService adminTaskService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    
    @RequestMapping("/index.do")
    public String index(Model model){
        List<AdminTask> allTask = adminTaskService.getAdminAllTask(UserContext.getUser().getId());
        model.addAttribute("tasks", allTask);
        return "adminTask/list";
    }
    
    @RequestMapping("/handleTask.do")
    @ResponseBody
    public AjaxResult handleTask(Long taskId, Model model){
        if(taskId == null){
            return AjaxResult.fail("参数不能为空");
        }
        try {
            adminTaskService.handledTask(UserContext.getUser().getId(), taskId);
            return AjaxResult.success("任务完成");
        } catch (Exception e) {
            logger.error("完成任务异常：", e);
            return AjaxResult.fail("系统异常");
        }
    }
}
