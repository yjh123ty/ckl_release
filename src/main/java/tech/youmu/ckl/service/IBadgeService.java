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

import tech.youmu.ckl.app.vo.BadgeInfo;

/**
 * 
 * <p>Title:IBadgeService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月23日下午3:30:21</p>
 * <p>Description:徽章</p>
 */
public interface IBadgeService {

	List<BadgeInfo> findBadgeInfo(Long userId);


}
