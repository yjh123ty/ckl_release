/**
 * File Name:Restaurant.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "酒店房间 信息")
public class HotelRoomInfo  {
    
    @ApiModelProperty(value="roomId")
    private long roomId;
    
    @ApiModelProperty(value="名称")
    private String roomName;

    @ApiModelProperty(value="金额")
    private double roomPrice;
    
    @ApiModelProperty(value="床类型")
    private String typeName;
    
    @ApiModelProperty(value="剩余数量")
    private int roomCount;
    
    @ApiModelProperty(value="封面图片")
    private String roomCoverImg;
    
    @ApiModelProperty(value="房间面积")
    private int roomSize;
    
    @ApiModelProperty(value="房间图片")
    private List<String> roomImgs;

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public String getRoomCoverImg() {
        return roomCoverImg;
    }

    public void setRoomCoverImg(String roomCoverImg) {
        this.roomCoverImg = roomCoverImg;
    }

    public List<String> getRoomImgs() {
        return roomImgs;
    }

    public void setRoomImgs(List<String> roomImgs) {
        this.roomImgs = roomImgs;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    
}
