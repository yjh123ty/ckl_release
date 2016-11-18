/**
 * Project Name:ckl
 * File Name:TravelNoteController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.TravelNote;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.TravelNoteQuery;
import tech.youmu.ckl.service.ITravelNoteService;

/**
 * <p>Title:TravelNoteController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月5日下午2:43:26</p>
 * <p>Description:用户游记管理控制器</p>
 */
@Controller
@RequestMapping("/usertravelnote")
public class UserTravelNoteController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ITravelNoteService travelNoteService;
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日下午2:44:04;</p>
     *	<p>Description: 控制导向;</p>
     *  @return
     */
    @RequestMapping("/index.do")
    public String index(){
        return "travel_note/user_travel_note";
    }
    
    
    @RequestMapping("/travelNoteInfo.do")
    public String travelNoteInfo(Long userId, Model model){
		model.addAttribute("userId", userId);
        return "travel_note/user_travel_note";
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日下午2:44:04;</p>
     *  <p>Description: 控制导向;</p>
     *  @return
     */
    @RequestMapping("/view.do")
    public String view(Long id, Model model){
        model.addAttribute("note", travelNoteService.getTravelNoteById(id));
        return "travel_note/user_travel_note_view";
    }
    
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日下午3:00:23;</p>
     *	<p>Description: 列出游记;</p>
     *  @return
     */
    @RequestMapping("/list.do")
    @ResponseBody
    public PageList<TravelNote> list(TravelNoteQuery query){
        return travelNoteService.getPageList(query);
    }
    
    @RequestMapping("/delete.do")
    @ResponseBody
    public AjaxResult delete(Long id){
        try {
            if(id == null) {
                return AjaxResult.fail("参数错误");
            }else {
                travelNoteService.deleteUserTravelNote(id);
                return AjaxResult.success("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("系统异常");
        }
    }
    
}
