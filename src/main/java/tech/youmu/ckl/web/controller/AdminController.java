/**
 * Project Name:ckl
 * File Name:AdminController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.query.AdminQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IAdminService;
import tech.youmu.ckl.utils.MD5Util;
import tech.youmu.ckl.utils.UserContext;
import tech.youmu.ckl.utils.VerifyUtil;

/**
 * <p>Title:AdminController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月8日下午2:00:19</p>
 * <p>Description:系统管理人员控制器</p>
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private IAdminService adminService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @RequestMapping("/index.do")
    public String index(){
        return "admin/change_password";
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月9日下午5:17:43;</p>
     *	<p>Description: 获取后台管理人员的数据不包括自己;</p>
     *  @param query
     *  @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<Employee> list(AdminQuery query){
        return adminService.getPageList(query);
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月8日下午2:08:34;</p>
     *	<p>Description: 添加或者修改一个授权用户;</p>
     *  @return
     */
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult edit(Employee admin){

        try {
            if(admin.getId() != null) {
                // 修改一个用户的授权
                adminService.updateById(admin);
                return AjaxResult.success("修改成功");
            }else {
                // 添加一个授权用户
                // 密码md5
                if(StringUtils.isBlank(admin.getMobile())){
                    return AjaxResult.fail("手机号不能为空");
                }
                if(StringUtils.isBlank(admin.getRealName())) {
                    return AjaxResult.fail("姓名不能为空");
                }
                if(StringUtils.isBlank(admin.getPassword())){
                    return AjaxResult.fail("密码不能为空");
                }
                admin.setPassword(MD5Util.MD5(admin.getPassword()));
                adminService.save(admin);
                return AjaxResult.success("添加成功");
            }
        } catch (BizExecption e) {
            e.printStackTrace();
            return AjaxResult.fail(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月17日上午10:16:15;</p>
     *	<p>Description: 修改管理员;</p>
     *  @return
     */
    @RequestMapping(value="/changePassword.do", method=RequestMethod.POST)
    @ResponseBody
    public AjaxResult changePassword(String oldPassword, String newPassword){
        try {
            if(StringUtils.isBlank(oldPassword)){
                return AjaxResult.fail("旧密码不能为空");
            }
            
            if(!VerifyUtil.checkSysPassword(newPassword)){
                return AjaxResult.fail("长度在6~20之间，只能包含字符、数字和下划线");
            }
            
            if(StringUtils.equals(oldPassword, newPassword)){
                return AjaxResult.fail("输入的旧密码和原密码相同");
            }
            
            if(adminService.changePassword(MD5Util.MD5(oldPassword),MD5Util.MD5(newPassword))){
                return AjaxResult.success("密码修改成功");
            }else {
                return AjaxResult.fail("原密码错误");
            }
        } catch (BizExecption e) {
            logger.error("修改密码错误：", e);
            return AjaxResult.fail(e.getMessage());
        }catch (Exception e) {
            logger.error("修改密码错误：", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
    @RequestMapping("/logout.do")
    public String logout(){
        UserContext.removeUser();
        return "redirect:/login/index.do";
    }
}
