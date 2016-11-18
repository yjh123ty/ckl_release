/**
 * File Name:Restaurant.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "饭店套餐明细信息")
public class RestaurantComboDetailInfo  {
    
    @ApiModelProperty(value="comboId")
    private long comboId;
    
    @ApiModelProperty(value="套餐名称")
    private String comboName;

    @ApiModelProperty(value="套餐金额")
    private double comboPrice;
    
    @ApiModelProperty(value="销售数量")
    private int comboSellCount;
    
    @ApiModelProperty(value="服务站名称")
    private String  stationName;
    
    @ApiModelProperty(value="封面图片")
    private String comboCoverImg;

    @ApiModelProperty(value="饭店名称")
    private String restaurantName;

    
    @ApiModelProperty(value="地址")
    private String address;
    
    @ApiModelProperty(value="电话")
    private String mobile;
    
    @ApiModelProperty(value="星数")
    private int stars;
    
    @ApiModelProperty(value="开始时间（饭店开始使用时间）")
    private String startTime;

    @ApiModelProperty(value="截止时间（饭店套餐使用截止日期）")
    private String endTime;
    
    @ApiModelProperty(value="开始营业时间")
    private String openTime;
    
    @ApiModelProperty(value="营业结束时间")
    private String closeTime;
    
    @ApiModelProperty(value="订餐提示")
    private String orderTips;

    @ApiModelProperty(value="用餐规则")
    private String orderRule;
    
    @ApiModelProperty(value="温馨提示")
    private String orderSweet;
    
    @ApiModelProperty(value="菜品列表")
    private List<RestaurantComboDishInfo> comboDishInfos;
    
    @ApiModelProperty(value="饭店介绍")
    private String restaurantIntroduce;
    
    @ApiModelProperty(value="饭店封面")
    private String restaurantcoverImg;
    

    public long getComboId() {
        return comboId;
    }

    public void setComboId(long comboId) {
        this.comboId = comboId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public double getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(double comboPrice) {
        this.comboPrice = comboPrice;
    }

    public int getComboSellCount() {
        return comboSellCount;
    }

    public void setComboSellCount(int comboSellCount) {
        this.comboSellCount = comboSellCount;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getComboCoverImg() {
        return comboCoverImg;
    }

    public void setComboCoverImg(String comboCoverImg) {
        this.comboCoverImg = comboCoverImg;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getOrderTips() {
        return orderTips;
    }

    public void setOrderTips(String orderTips) {
        this.orderTips = orderTips;
    }

    public String getOrderRule() {
        return orderRule;
    }

    public void setOrderRule(String orderRule) {
        this.orderRule = orderRule;
    }

    public String getOrderSweet() {
        return orderSweet;
    }

    public void setOrderSweet(String orderSweet) {
        this.orderSweet = orderSweet;
    }

    public List<RestaurantComboDishInfo> getComboDishInfos() {
        return comboDishInfos;
    }

    public void setComboDishInfos(List<RestaurantComboDishInfo> comboDishInfos) {
        this.comboDishInfos = comboDishInfos;
    }

    public String getRestaurantIntroduce() {
        return restaurantIntroduce;
    }

    public void setRestaurantIntroduce(String restaurantIntroduce) {
        this.restaurantIntroduce = restaurantIntroduce;
    }

    public String getRestaurantcoverImg() {
        return restaurantcoverImg;
    }

    public void setRestaurantcoverImg(String restaurantcoverImg) {
        this.restaurantcoverImg = restaurantcoverImg;
    }
    
    
   
    
  
}
