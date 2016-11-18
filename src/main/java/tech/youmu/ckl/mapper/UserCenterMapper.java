/**
 * Project Name:ckl
 * File Name:UserCenter.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.mapper;

import tech.youmu.ckl.domain.UserCenter;

/**
 * <p>Title:UserCenter</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月18日下午6:31:28</p>
 * <p>Description:用户中心</p>
 */
public interface UserCenterMapper {
    public UserCenter getUserCenterByUserId(Long id);
}
