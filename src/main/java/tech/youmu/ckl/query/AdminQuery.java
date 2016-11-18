/**
 * Project Name:ckl
 * File Name:AdminQuery.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.query;

import tech.youmu.ckl.utils.UserContext;

/**
 * <p>Title:AdminQuery</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月9日下午5:19:06</p>
 * <p>Description:后台管理人员的</p>
 */
public class AdminQuery extends BaseQuery {
    
    public Long getLoginUserId() {
        return UserContext.getUser().getId();
    }
}
