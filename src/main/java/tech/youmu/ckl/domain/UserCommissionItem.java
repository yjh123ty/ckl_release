/**
 * Project Name:ckl
 * File Name:UserCommissionItem.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

import tech.youmu.ckl.constants.Global;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title:UserCommissionItem</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月17日下午3:34:23</p>
 * <p>Description:用户佣金项</p>
 */
@SuppressWarnings("serial")
public class UserCommissionItem implements Serializable{
    
    private Long userId;
    
    private String userNickName;
    
    private String userMobile;
    
    private BigDecimal amount;
    
    private Long rechargeUserId;
    
    private String rechargeUserNickName;
    
    private String rechargeUserMobile;
    
    private Integer type;
    
    private Date createTime;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @JsonFormat(pattern=Global.DATE_TIME_FORMAT, timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Long getRechargeUserId() {
        return rechargeUserId;
    }

    public void setRechargeUserId(Long rechargeUserId) {
        this.rechargeUserId = rechargeUserId;
    }

    public String getRechargeUserNickName() {
        return rechargeUserNickName;
    }

    public void setRechargeUserNickName(String rechargeUserNickName) {
        this.rechargeUserNickName = rechargeUserNickName;
    }

    public String getRechargeUserMobile() {
        return rechargeUserMobile;
    }

    public void setRechargeUserMobile(String rechargeUserMobile) {
        this.rechargeUserMobile = rechargeUserMobile;
    }
}