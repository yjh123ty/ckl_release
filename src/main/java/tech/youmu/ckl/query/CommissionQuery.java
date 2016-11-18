/**
 * Project Name:ckl
 * File Name:CommissionQuery.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.query;

/**
 * <p>Title:CommissionQuery</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月18日下午4:56:10</p>
 * <p>Description:分销查询对象</p>
 */
public class CommissionQuery extends BaseQuery {
    
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
