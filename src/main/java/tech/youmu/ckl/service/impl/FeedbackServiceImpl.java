/**
 * @Title: FeedbackServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月18日 下午1:09:03
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.Feedback;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.FeedbackMapper;
import tech.youmu.ckl.service.IFeedbackService;

/**
 * 
 * @author youmu-yjh
 * 
 */
@Service
public class FeedbackServiceImpl extends BaseServiceImpl<Feedback> implements IFeedbackService {

    private FeedbackMapper feedbackMapper;
    
    /**
     * @param baseMapper
     */
    @Autowired
    public FeedbackServiceImpl(FeedbackMapper feedbackMapper) {
        super(feedbackMapper);
        this.feedbackMapper = feedbackMapper;
    }

    /** 
     * @see tech.youmu.ckl.service.IFeedbackService#handleById(java.lang.Long)
     */
    @Override
    public void handleById(Long id) {
    	//拿到之前的反馈信息
        Feedback feedback = (Feedback) feedbackMapper.getById(id);
        if(feedback == null){
            throw new BizExecption("没有该反馈信息！");
        }
        if(feedback.getStatus() == Feedback.STATUS_COMPLETE_FEEDBACK){
            throw new BizExecption("该反馈信息已经处理！");
        }
        //修改 状态 与 时间
        feedback.setStatus(Feedback.STATUS_COMPLETE_FEEDBACK);
        feedback.setHandleTime(new Date());
    	//更改状态
    	feedbackMapper.handleById(feedback);
    }

	@Override
	public void saveFeedback(Feedback feedback) {
		feedbackMapper.save(feedback);
	}


}
