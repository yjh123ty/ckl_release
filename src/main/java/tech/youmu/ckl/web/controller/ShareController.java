/**
 * Project Name:ckl
 * File Name:ShareController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tech.youmu.ckl.service.ITravelNoteService;

/**
 * <p>Title:ShareController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月28日下午5:48:13</p>
 * <p>Description:分享页面</p>
 */

@Controller
@RequestMapping("/share")
public class ShareController {
    
    @Autowired
    private ITravelNoteService travelNoteService;
    
    @RequestMapping("/index.action")
    public String index(Integer type){
        type = type == null ? 1 : type;
        switch (type) {
        case 1:
            return "h5/forumTopicHtml";
        case 2:
            return "h5/companionTopicHtml";
        case 3:
            return "h5/promotionCodeHtml";
        case 4:
            return "h5/trackHtml";
        case 5:
            return "h5/travelNoteHtml";
        default:
            return "h5/forumTopicHtml";
        }
    }
    
}
