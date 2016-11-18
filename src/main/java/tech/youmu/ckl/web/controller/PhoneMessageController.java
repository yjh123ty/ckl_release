/**
 * Project Name:ckl
 * File Name:PhoneMessageController.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Department;
import tech.youmu.ckl.domain.PhoneMessage;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.DepartmentQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IPhoneMessageService;

/**
 * <p>Title:PhoneMessageController</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月10日上午11:43:25</p>
 * <p>Description:TODO</p>
 */
@Controller
@RequestMapping("/phoneMessage")
public class PhoneMessageController {
    
    @Autowired
    private IPhoneMessageService phoneMessageService;

    @RequestMapping("/index.do")
    public String index(String cmd, Long id, Model model){
        
        // 跳转到编辑页面
        if(StringUtils.equals("save", cmd)) {
            return "phoneMessage/phoneMessage_edit";
        }
        
        // 获取数据跳转到编辑页面
        if(StringUtils.equals("update", cmd)) {
            model.addAttribute("phoneMessage", phoneMessageService.getById(id));
            return "phoneMessage/phoneMessage_edit";
        }
        return "phoneMessage/phoneMessage";
    }
    
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<PhoneMessage> list(BaseQuery baseQuery) throws Exception{
        return phoneMessageService.getPageList(baseQuery);
    }
    
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult save(PhoneMessage phoneMessage){
        AjaxResult result = null;
        try{
        	System.err.println("xxxxxxxx"+ phoneMessage.getPhone() + "   if:" + StringUtils.isBlank(phoneMessage.getPhone()));
        	if(StringUtils.isNotBlank(phoneMessage.getPhone())){
	            if(phoneMessage.getId()==null){
	        		//添加
	        		phoneMessageService.save(phoneMessage);
	        		result = AjaxResult.success("添加成功");
	            }else{
	                //修改
	                phoneMessageService.updateById(phoneMessage);
	                result = AjaxResult.success("修改成功");
	            }
        	}else {
        		result = AjaxResult.fail("手机号不能为空！");
			}
        }
        catch (Exception e) {
            e.printStackTrace();
            result = AjaxResult.fail("服务异常！" , 200);
        }
        System.err.println("result : " + result);
        return result;
    }
}
