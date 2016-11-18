/**
 * Project Name:ckl
 * File Name:SysDicController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.SysDicDetail;
import tech.youmu.ckl.service.ISysDicService;

/**
 * <p>Title:SysDicController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月9日下午3:28:28</p>
 * <p>Description:数据字典接口</p>
 */
@Controller
@RequestMapping("/dic")
public class SysDicController {
    
    @Autowired
    private ISysDicService sysDicService;
    
    @RequestMapping("/getdetails.do")
    @ResponseBody
    public List<SysDicDetail> getDetails(String name){
       return sysDicService.getDetailsByTypeName(name);
    }
    
}
