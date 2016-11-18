/**
 * @Title: IFeedbackService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月18日 下午1:07:32
 * @version V1.0
 */

package tech.youmu.ckl.service;

import tech.youmu.ckl.domain.Feedback;

/**
 * 用户反馈
 * @author youmu-yjh
 * 
 */
public interface IFeedbackService extends IBaseService<Feedback>{

    /**
     * 处理用户反馈
     * @param id
     */
    void handleById(Long id);

	void saveFeedback(Feedback feedback);
    
}
