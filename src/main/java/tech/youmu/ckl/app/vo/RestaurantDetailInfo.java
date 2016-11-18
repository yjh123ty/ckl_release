/**
 * File Name:Restaurant.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "饭店明细信息")
public class RestaurantDetailInfo {
    
    @ApiModelProperty(value="restaurantId")
    private long restaurantId;
    
    @ApiModelProperty(value="饭店名称")
    private String restaurantName;

    @ApiModelProperty(value="服务站名称")
    private String stationName;
    
    @ApiModelProperty(value="地址")
    private String address;
    
    @ApiModelProperty(value="电话")
    private String mobile;
    
    @ApiModelProperty(value="开始营业时间")
    private String openTime;
    
    @ApiModelProperty(value="营业结束时间")
    private String closeTime;
    
    @ApiModelProperty(value="星数")
    private int stars;
    
    @ApiModelProperty(value="封面图片")
    private String coverImg;
    
    @ApiModelProperty(value="套餐列表")
    private List<RestaurantComboInfo> comboInfos;
    
    @ApiModelProperty(value="环境图片")
    private List<String> restaurantImgs;

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public List<RestaurantComboInfo> getComboInfos() {
        return comboInfos;
    }

    public void setComboInfos(List<RestaurantComboInfo> comboInfos) {
        this.comboInfos = comboInfos;
    }

    public List<String> getRestaurantImgs() {
        return restaurantImgs;
    }

    public void setRestaurantImgs(List<String> restaurantImgs) {
        this.restaurantImgs = restaurantImgs;
    }
    
    


    
    
    
  
}
