/**
 * @Title: UserServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:58:35
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.app.vo.BadgeInfo;
import tech.youmu.ckl.mapper.BadgeMapper;
import tech.youmu.ckl.service.IBadgeService;
import tech.youmu.ckl.utils.ImageURLUtil;

/**
 * 
 * <p>Title:BadgeServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月23日下午3:31:39</p>
 * <p>Description:TODO</p>
 */
@Service
public class BadgeServiceImpl  implements IBadgeService {
	
	
	@Autowired
	private BadgeMapper badgeMapper;

	@Override
	public List<BadgeInfo> findBadgeInfo(Long userId) {
		List<BadgeInfo> badgeInfos =  badgeMapper.findBadgeInfo(userId);
		for(BadgeInfo badgeInfo : badgeInfos){
			badgeInfo.setImg(ImageURLUtil.fillPath(badgeInfo.getImg()));
		}
		return badgeInfos;
	}



}
