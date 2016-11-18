/**
 * @Title: FeedbackQuery.java
 * @Package tech.youmu.ckl.query
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月18日 下午12:46:09
 * @version V1.0
 */

package tech.youmu.ckl.query;

import java.util.Date;

/**
 * 用户反馈查询对象
 * @author youmu-yjh
 * 
 */
public class FeedbackQuery extends BaseQuery {
    private String keywords;        //用户关键字
    private String content;         //反馈内容
    private Integer status;         //反馈状态
    private Date beginTime;         //开始时间
    private Date endTime;           //截止时间
    private Long userId;
    
    
    public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
}
