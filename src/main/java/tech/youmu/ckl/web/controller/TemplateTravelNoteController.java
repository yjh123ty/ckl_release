/**
 * Project Name:ckl
 * File Name:TravelNoteController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.SysRole;
import tech.youmu.ckl.domain.TemplateTravelNote;
import tech.youmu.ckl.domain.TemplateTravelNoteContent;
import tech.youmu.ckl.domain.TravelNote;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.TravelNoteQuery;
import tech.youmu.ckl.service.IRouteService;
import tech.youmu.ckl.service.ITemplateTravelNoteService;
import tech.youmu.ckl.service.ITravelNoteService;
import tech.youmu.ckl.utils.UploadUtils;
import tech.youmu.ckl.utils.UserContext;

/**
 * <p>Title:TravelNoteController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月5日下午2:43:26</p>
 * <p>Description:用户游记管理控制器</p>
 */
@Controller
@RequestMapping("/templatetravelnote")
public class TemplateTravelNoteController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ITemplateTravelNoteService travelNoteService;
    
    @Autowired
    private IRouteService routeService;
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日下午2:44:04;</p>
     *	<p>Description: 控制导向;</p>
     *  @return
     */
    @RequestMapping("/index.do")
    public String index(String cmd, Long id, Long routeId, Model model){
        if("save".equals(cmd)){
            model.addAttribute("routes", routeService.findNoneTemplateTravelNoteRoutes());
            return "travel_note/template_travel_note_edit";
        }
        if("edit".equals(cmd)){
            model.addAttribute("routes", routeService.getAll());
            model.addAttribute("note", travelNoteService.getTemplateTravelNoteByRouteId(routeId));
            model.addAttribute("routeId", routeId);
            return "travel_note/template_travel_note_edit";
        }
        
        if("saveContent".equals(cmd)){
            model.addAttribute("noteId", id);
            model.addAttribute("routeId", routeId);
            return "travel_note/template_travel_note_content_edit";
        }
        
        if("editContent".equals(cmd)){
            model.addAttribute("content", travelNoteService.getTemplateTravelNoteContentById(id));
            model.addAttribute("routeId", routeId);
            return "travel_note/template_travel_note_content_edit";
        }
        return "travel_note/template_travel_note";
    }
    
    @RequestMapping("/create.do")
    public String create(Long routeId){
        travelNoteService.createTemplateTravelNote(routeId);
        return "redirect:/templatetravelnote/index.do?cmd=edit&routeId="+routeId;
    }
    
    @RequestMapping("/view.do")
    public String view(Long id, Model model){
        model.addAttribute("note", travelNoteService.getTemplateTravelNoteByRouteId(id));
        model.addAttribute("routeId", id);
        return "travel_note/template_travel_note_view";
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
                travelNoteService.deleteTemplateTravelNote(id);
                return AjaxResult.success("删除成功");
            }
        } catch (Exception e) {
            logger.error("模板游记删除异常", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
    @RequestMapping("/edit.do")
    @ResponseBody
    public AjaxResult edit(TemplateTravelNote travelNote){
        try {
            if(travelNote.getId() == null) {
                travelNoteService.save(travelNote);
                return AjaxResult.success("添加成功");
            } else {
                travelNoteService.update(travelNote);
                return AjaxResult.success("修改成功");
            }
        } catch (Exception e) {
            logger.error("模板游记添加/修改异常", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
    @RequestMapping("/editContent.do")
    @ResponseBody
    public AjaxResult editContent(@RequestParam(value="image",required=false) MultipartFile image,  TemplateTravelNoteContent content){
        try {
            if(content.getType() == 2 && image != null && !image.isEmpty()) {
                content.setContent(UploadUtils.uploadFile(image, UploadUtils.TEMPLATE_TRAVEL_CONTENT_IMG_PATH));
            }
            if(content.getId() == null) {
                if(content.getType() == 1) {
                    if(StringUtils.isBlank(content.getContent())) {
                        return AjaxResult.fail("文字内容不为空");
                    }
                }
                if(content.getType() == 2) {
                    if(StringUtils.isBlank(content.getContent())) {
                        return AjaxResult.fail("图片不为空");
                    }
                }
                travelNoteService.saveContent(content);
                return AjaxResult.success("添加成功");
            } else {
                travelNoteService.updateContent(content);
                return AjaxResult.success("修改成功");
            }
        } catch (BizExecption e) {
            logger.error("模板游记内容添加/修改异常", e);
            return AjaxResult.fail(e.getMessage());
        }catch (Exception e) {
            logger.error("模板游记内容线添加/修改异常", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
    @RequestMapping("/deleteContent.do")
    @ResponseBody
    public AjaxResult deleteContent(Long id){
        try {
            if(id == null) {
                return AjaxResult.fail("参数异常");
            }
            travelNoteService.deleteContentById(id);
            return AjaxResult.success("删除成功");
        } catch (Exception e) {
            logger.error("模板游记内容删除异常:", e);
            return AjaxResult.fail("系统异常");
        }
    }
    
}
