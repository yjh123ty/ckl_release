/**
 * @Title: FeedbackMapper.java
 * @Package tech.youmu.ckl.mapper
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月18日 上午11:31:51
 * @version V1.0
 */

package tech.youmu.ckl.mapper;

import tech.youmu.ckl.domain.Feedback;

/**
 * 用户反馈
 * @author youmu-yjh
 * 
 */
public interface FeedbackMapper extends BaseMapper{

    /**
     * 处理用户反馈
     * @param feedback
     */
    void handleById(Feedback feedback);

}
