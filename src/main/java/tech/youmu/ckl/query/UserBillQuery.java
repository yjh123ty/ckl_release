/**
 * Project Name:ckl
 * File Name:BillQuery.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.query;

import tech.youmu.ckl.domain.User;

/**
 * <p>Title:BillQuery</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月6日下午4:03:50</p>
 * <p>Description:账单的查询对象（充值账单和提现账单）</p>
 */
public class UserBillQuery extends BaseQuery {
    
    private String keywords;        //关键词
    
    private Integer type;    //账单类型（ 1-酒店服务，2-饭店服务，3-汽车保养服务, 4-零部件维修服务,5-道路救援;6 退款 ； 7提现 ；8 充值 ；）
    
    private Long userId;         //员工id
    
    private Integer status;		//充值状态 (1操作中 ；2操作成功；3 操作失败)
    
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    
    
}
