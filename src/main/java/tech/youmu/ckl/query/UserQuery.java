/**
 * @Title: UserQuery.java
 * @Package tech.youmu.ckl.query
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-Administrator
 * @date 2016年8月11日 上午10:07:42
 * @version V1.0
 */

package tech.youmu.ckl.query;

import java.util.Date;

/**
 * 用户/员工查询对象
 * @author yjh
 *
 */
public class UserQuery extends BaseQuery {
	private String keywords;    //关键词  (账号，姓名) 
	private Integer status;
	private Date beginTime;     //起始时间
    private Date endTime;       //截止时间
	
	public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
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
