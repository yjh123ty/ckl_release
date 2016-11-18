/**
 * @Title: RouteQuery.java
 * @Package tech.youmu.ckl.query
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月17日 下午2:42:24
 * @version V1.0
 */

package tech.youmu.ckl.query;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import tech.youmu.ckl.constants.Global;

/**
  * @ClassName: RouteQuery
  * @Description: TODO
  * @author youmu-zh
  * @date 2016年8月17日 下午2:42:24
  *
  */

public class RouteQuery extends BaseQuery {
	
	/**
	 * 路线录入开始时间
	 */
	@DateTimeFormat(pattern=Global.QUERY_DATE_TIME_FORMAT)
	private Date startTime;
	
	/**
	 * 路线录入结束时间
	 */
	@DateTimeFormat(pattern=Global.QUERY_DATE_TIME_FORMAT)
	private Date endTime;
	
	/**
	 * 关键词  路线名称  车刻丽服务站名称       景点的名称
	 */
	private String keyword;
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
	    if(!StringUtils.isBlank(keyword)) {
            this.keyword = keyword;
        }
	}
	
	@Override
	public String toString() {
		return "RouteQuery [startTime=" + startTime + ", endTime=" + endTime
				+ ", keyword=" + keyword;
	}
}
