/**
 * Project Name:ckl
 * File Name:UserBillBalanceInfo.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import tech.youmu.ckl.constants.Global;

/**
 * <p>Title:UserBillBalanceInfo</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月6日下午5:59:43</p>
 * <p>Description:用户充值，提现报表</p>
 */
public class UserBillBalanceInfo {
	
	/**
	 * 用户id
	 */
    private Long uid;
    
    /**
     * 总金额
     */
    private BigDecimal total;
    
    /**
     * 用户昵称
     */
    private String uname;
    
    /**
     * 用户账号
     */
    private String umobile;
    
    /**
     * 最后一次操作时间（提现、充值、退款）
     */
    private Date lastTime;
    

    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUmobile() {
		return umobile;
	}

	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}

	@Override
	public String toString() {
		return "UserBillBalanceInfo [uid=" + uid + ", total=" + total + ", uname=" + uname + ", umobile=" + umobile
				+ ", lastTime=" + lastTime + "]";
	}
    
    
}
