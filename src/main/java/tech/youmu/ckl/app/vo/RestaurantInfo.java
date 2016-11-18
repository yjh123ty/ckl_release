/**
 * File Name:Restaurant.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "饭店信息")
public class RestaurantInfo implements Comparable<RestaurantInfo> {
    
    @ApiModelProperty(value="restaurantId")
    private long restaurantId;
    
    @ApiModelProperty(value="饭店名称")
    private String restaurantName;

    @ApiModelProperty(value="服务站名称")
    private String stationName;
    
    @ApiModelProperty(value="距离单位：km")
    private double distance;
    
    @ApiModelProperty(value="1-自营,2合作")
    private int type;
    
    @ApiModelProperty(value="最低消费")
    private double minConsume;
    
    @ApiModelProperty(value="星数")
    private int stars;
    
    @ApiModelProperty(value="封面图片")
    private String coverImg;

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getMinConsume() {
        return minConsume;
    }

    public void setMinConsume(double minConsume) {
        this.minConsume = minConsume;
    }


    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
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

    @Override
    public int compareTo(RestaurantInfo o) {
        if(this.type<o.type||this.distance<o.distance){
            return -1;
        }else if(this.type==o.type&&this.distance==o.distance){
            return 0;
        }else if(this.type==o.type||this.distance==o.distance){
            return 0;
        }
        return 1;
    }

    
    
    
  
}
