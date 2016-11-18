/**
 * Project Name:ckl
 * File Name:UserCommission.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>Title:UserCommission</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月17日下午3:27:47</p>
 * <p>Description:用户佣金</p>
 */
@SuppressWarnings("serial")
public class UserCommission implements Serializable{
    
    private Long userId;
    
    private String userMobile;
    
    private String userNickName;
    
    /**
     * 该用户的累计佣金金额
     */
    private BigDecimal totalCommisssion;

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

    public BigDecimal getTotalCommisssion() {
        return totalCommisssion;
    }

    public void setTotalCommisssion(BigDecimal totalCommisssion) {
        this.totalCommisssion = totalCommisssion;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
}
