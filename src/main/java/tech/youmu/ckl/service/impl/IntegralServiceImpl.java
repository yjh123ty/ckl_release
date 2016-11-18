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

import tech.youmu.ckl.app.vo.IntegralDetailInfo;
import tech.youmu.ckl.app.vo.IntegralMonthInfo;
import tech.youmu.ckl.mapper.IntegralMapper;
import tech.youmu.ckl.service.IIntegralService;

/**
 * 
 * <p>Title:IntegralServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月23日下午4:17:28</p>
 * <p>Description:积分</p>
 */
@Service
public class IntegralServiceImpl  implements IIntegralService {
	
	
	@Autowired
	private IntegralMapper integralMapper;


    @Override
    public List<IntegralMonthInfo> findIntegralMonthInfo(Long userId) {
        return integralMapper.findIntegralMonthInfo(userId);
    }




}
