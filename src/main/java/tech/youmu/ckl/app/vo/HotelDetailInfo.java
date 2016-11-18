/**
 * File Name:Restaurant.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "酒店明细信息")
public class HotelDetailInfo {
    
    @ApiModelProperty(value="hotelId")
    private long hotelId;
    
    @ApiModelProperty(value="名称")
    private String hotelName;

    @ApiModelProperty(value="服务站名称")
    private String stationName;
    
    @ApiModelProperty(value="地址")
    private String address;
    
    @ApiModelProperty(value="电话")
    private String mobile;
    
    @ApiModelProperty(value="酒店介绍")
    private String introduce;
    
    @ApiModelProperty(value="可提供的服务")
    private List<String> serviceContents;
    
    @ApiModelProperty(value="星数")
    private int stars;
    
    @ApiModelProperty(value="封面图片")
    private String coverImg;
    
    @ApiModelProperty(value="房间列表")
    private List<HotelRoomInfo> roomInfos;
    
    @ApiModelProperty(value="大厅图片")
    private List<String> hotelInnerImgs;
    
    @ApiModelProperty(value="外观图片")
    private List<String> hotelOutSideImgs;
    
    @ApiModelProperty(value="房间图片")
    private List<String> hotelRoomImgs;

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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }


    public List<String> getServiceContents() {
        return serviceContents;
    }

    public void setServiceContents(List<String> serviceContents) {
        this.serviceContents = serviceContents;
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

    public List<HotelRoomInfo> getRoomInfos() {
        return roomInfos;
    }

    public void setRoomInfos(List<HotelRoomInfo> roomInfos) {
        this.roomInfos = roomInfos;
    }

    public List<String> getHotelInnerImgs() {
        return hotelInnerImgs;
    }

    public void setHotelInnerImgs(List<String> hotelInnerImgs) {
        this.hotelInnerImgs = hotelInnerImgs;
    }

    public List<String> getHotelOutSideImgs() {
        return hotelOutSideImgs;
    }

    public void setHotelOutSideImgs(List<String> hotelOutSideImgs) {
        this.hotelOutSideImgs = hotelOutSideImgs;
    }

    public List<String> getHotelRoomImgs() {
        return hotelRoomImgs;
    }

    public void setHotelRoomImgs(List<String> hotelRoomImgs) {
        this.hotelRoomImgs = hotelRoomImgs;
    }

    
    
    
    
  
}
