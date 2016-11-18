/**
 * @Title: IUserService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:57:38
 * @version V1.0
 */

package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.app.vo.IntegralDetailInfo;
import tech.youmu.ckl.app.vo.IntegralMonthInfo;

/**
 * 
 * <p>Title:IIntegralService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月23日下午4:16:50</p>
 * <p>Description:积分</p>
 */
public interface IIntegralService {

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月23日下午4:19:18;</p>
	 *	<p>Description: 获取积分明细;</p>
	 *  @param userId
	 *  @return
	 */
    List<IntegralMonthInfo> findIntegralMonthInfo(Long userId);



}
