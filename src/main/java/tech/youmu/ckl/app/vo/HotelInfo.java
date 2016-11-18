/**
 * File Name:Restaurant.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "酒店信息")
public class HotelInfo implements Comparable<HotelInfo> {
    
    @ApiModelProperty(value="hotelId")
    private long hotelId;
    
    @ApiModelProperty(value="酒店名称")
    private String hotelName;

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
    
    @ApiModelProperty(value="是否满房")
    private boolean isFull;

    


    public long getHotelId() {
        return hotelId;
    }




    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }




    public String getHotelName() {
        return hotelName;
    }




    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public boolean getIsFull() {
        return isFull;
    }




    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }




    @Override
    public int compareTo(HotelInfo o) {
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
