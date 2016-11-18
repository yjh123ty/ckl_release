/**
 * @Title: UserController.java
 * @Package tech.youmu.ckl.web.controller
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-Administrator
 * @date 2016年8月18日 下午1:28:29
 * @version V1.0
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

import tech.youmu.ckl.constants.Global;
import tech.youmu.ckl.domain.AjaxResult;
import tech.youmu.ckl.domain.Feedback;
import tech.youmu.ckl.query.FeedbackQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IFeedbackService;

/**
 * @author yjh
 *
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController{
	
	@Autowired
	private IFeedbackService feedbackService;
	
	/**
	 * 反馈导向
	 * @return
	 */
	@RequestMapping("/index.do")
	public String index(){
		return "feedback/feedback";
	}
	
	@RequestMapping("/feedbackInfo.do")
    public String feedbackInfo(Long userId, Model model){
		model.addAttribute("userId", userId);
        return "feedback/feedback";
    }
	
	/**
	 * 反馈列表
	 * @return
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public PageList<Feedback> list(FeedbackQuery feedbackQuery,String beginTimeStr,String endTimeStr) throws Exception{
		if (beginTimeStr!=null&&!beginTimeStr.isEmpty()) {
            SimpleDateFormat formatBegin = new SimpleDateFormat(Global.DATE_TIME_FORMAT);
            Date date2 = formatBegin.parse(beginTimeStr);
            feedbackQuery.setBeginTime(date2);
        }
        if (endTimeStr!=null&&!endTimeStr.isEmpty()) {
            SimpleDateFormat formatEnd = new SimpleDateFormat(Global.DATE_TIME_FORMAT);
            Date date3 = formatEnd.parse(endTimeStr);
            feedbackQuery.setEndTime(date3);
        }
		return feedbackService.getPageList(feedbackQuery);
	}
	
	/**
	 * 修改/添加反馈
	 * @param user
	 * @return
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public AjaxResult save(Feedback feedback){
	    AjaxResult result = null;
		if(feedback.getId()==null){
			//新增一条反馈数据，记录录入时间
		    feedback.setCreateTime(new Date());
			//添加反馈
		    feedbackService.save(feedback);
			result = AjaxResult.success("保存成功");
		}else{
			//修改反馈
		    feedbackService.updateById(feedback);
			result = AjaxResult.success("修改成功");
		}
		return result;
	}
	
	/**
	 * @param id(删除该反馈)
	 * @return 操作结果消息
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public AjaxResult deleteById(Long id){
	    AjaxResult result = new AjaxResult();
        try {
            if(id != null) {
                feedbackService.deleteById(id);
                result.setMsg("删除成功");
            }else {
                result.setSuccess(false);
                result.setMsg("反馈编号不能为空!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg("操作失败");
        }
        return result;
	}
	
	/**
	 * @param id(处理该反馈)
	 * @return 操作结果消息
	 */
	@RequestMapping("/handle.do")
	@ResponseBody
    public AjaxResult handleById(Long id){
	    AjaxResult result = new AjaxResult();
        try {
            if(id != null) {
                //更改状态
                feedbackService.handleById(id);
                result.setMsg("处理完成");
            }else {
                result.setSuccess(false);
                result.setMsg("反馈编号不能为空!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg("操作失败");
        }
        return result;
    }
	
}
