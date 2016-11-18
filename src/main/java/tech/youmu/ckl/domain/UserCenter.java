/**
 * Project Name:ckl
 * File Name:UserCenter.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;

/**
 * <p>Title:UserCenter</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月17日下午5:06:19</p>
 * <p>Description:用户中心</p>
 */
public class UserCenter {
    private Long id;

    /**
     * 用户(id、账号、名称、等级、钱包)
     */
    private User user;
    
    /**
     * 佣金总计
     */
    private Integer totalCommission;
    
    /**
     * 充值金额（刻）
     */
    private Integer rechargeCount;
    
    /**
     * 徽章总计
     */
    private Integer badgeCount;
    
    /**
     * 订单总计
     */
    private Integer orderNumCount;
    
    /**
     * 用户评价
     */
    private Double avgStar;
    
    /**
     * 社区帖子总计
     */
    private Integer topicCount;
    
    /**
     * 找旅伴帖子
     */
    private Integer companionTopicCount;
    
    /**
     * 用户游记总计
     */
    private Integer userTravelNoteCount;
    
    /**
     * 用户轨迹总计
     */
    private Integer userTrackCount;
    
    /**
     * 意见反馈总计
     */
    private Integer feedbackCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTotalCommission() {
		return totalCommission;
	}

	public void setTotalCommission(Integer totalCommission) {
		this.totalCommission = totalCommission;
	}

	public Integer getRechargeCount() {
        return rechargeCount;
    }

    public void setRechargeCount(Integer rechargeCount) {
        this.rechargeCount = rechargeCount;
    }

    public Integer getBadgeCount() {
        return badgeCount;
    }

    public void setBadgeCount(Integer badgeCount) {
        this.badgeCount = badgeCount;
    }

    public Integer getOrderNumCount() {
        return orderNumCount;
    }

    public void setOrderNumCount(Integer orderNumCount) {
        this.orderNumCount = orderNumCount;
    }

    public Integer getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount) {
        this.topicCount = topicCount;
    }

    
    public Integer getUserTrackCount() {
        return userTrackCount;
    }

    public void setUserTrackCount(Integer userTrackCount) {
        this.userTrackCount = userTrackCount;
    }

    public Integer getFeedbackCount() {
        return feedbackCount;
    }

    public void setFeedbackCount(Integer feedbackCount) {
        this.feedbackCount = feedbackCount;
    }
    public Integer getUserTravelNoteCount() {
		return userTravelNoteCount;
	}

	public void setUserTravelNoteCount(Integer userTravelNoteCount) {
		this.userTravelNoteCount = userTravelNoteCount;
	}

    public Double getAvgStar() {
        return avgStar;
    }

    public void setAvgStar(Double avgStar) {
        this.avgStar = avgStar;
    }

    public Integer getCompanionTopicCount() {
        return companionTopicCount;
    }

    public void setCompanionTopicCount(Integer companionTopicCount) {
        this.companionTopicCount = companionTopicCount;
    }

    @Override
    public String toString() {
        return "UserCenter [id=" + id + ", user=" + user + ", totalCommission=" + totalCommission + ", rechargeCount=" + rechargeCount + ", badgeCount=" + badgeCount + ", orderNumCount=" + orderNumCount + ", avgStar=" + avgStar + ", topicCount=" + topicCount + ", companionTopicCount=" + companionTopicCount + ", userTravelNoteCount=" + userTravelNoteCount + ", userTrackCount=" + userTrackCount + ", feedbackCount=" + feedbackCount + "]";
    }
    
    
    
}
