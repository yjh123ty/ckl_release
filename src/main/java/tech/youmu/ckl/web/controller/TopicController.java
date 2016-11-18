/**
 * Project Name:ckl
 * File Name:TopicController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Topic;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.TopicQuery;
import tech.youmu.ckl.service.ITopicService;

/**
 * <p>Title:TopicController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月26日下午2:42:24</p>
 * <p>Description:帖子的控制器</p>
 */
@Controller
@RequestMapping("/topic")
public class TopicController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ITopicService topicService;
    
    @RequestMapping("/index.do")
    public String index(String cmd,String type,Model model,Long userId){
        if("ft".equals(cmd)) {
            if("ftInfo".equals(type)){
                model.addAttribute("userId", userId);
            }
            return "topic/forum_topic";
        }else if("ct".equals(cmd)){
            if("ctInfo".equals(type)){
                model.addAttribute("userId", userId);
            }
            return "topic/companion_topic";
        }else {
            return "";
        }
    }
    
    @RequestMapping("/listForumTopic.do")
    @ResponseBody
    public PageList<Topic> listForumTopic(TopicQuery query){
        return topicService.getForumTopicPageList(query);
    }
    
    @RequestMapping("/deleteForumTopic.do")
    @ResponseBody
    public AjaxResult deleteForumTopic(Long id){
        try {
            if (id != null) {
                topicService.deleteForumTopicById(id);
                return AjaxResult.success("删除成功!");
            } else {
                return AjaxResult.success("参数异常!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
    
    @RequestMapping("/topForumTopic.do")
    @ResponseBody
    public AjaxResult topForumTopic(Long id){
        try {
            if (id != null) {
                topicService.topForumTopicById(id);
                return AjaxResult.success("置顶成功!");
            } else {
                return AjaxResult.success("参数异常!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
    
    
    @RequestMapping("/listCompanionTopic.do")
    @ResponseBody
    public PageList<Topic> listCompanionTopic(TopicQuery query){
        return topicService.getCompanionTopicPageList(query);
    }
    
    @RequestMapping("/deleteCompanionTopic.do")
    @ResponseBody
    public AjaxResult deleteCompanionTopic(Long id){
        try {
            if (id != null) {
                topicService.deleteCompanionTopicById(id);
                return AjaxResult.success("删除成功!");
            } else {
                return AjaxResult.success("参数异常!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
    
    @RequestMapping("/topCompanionTopic.do")
    @ResponseBody
    public AjaxResult topCompanionTopic(Long id){
        try {
            if (id != null) {
                topicService.topCompanionTopicById(id);
                return AjaxResult.success("置顶成功!");
            } else {
                return AjaxResult.success("参数异常!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
}
