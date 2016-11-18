/**
 * Project Name:ckl
 * File Name:HotelRoom.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.awt.Image;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.util.ImageUtils;

import tech.youmu.ckl.utils.ImageURLUtil;

/**
 * <p>Title:HotelRoom</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月7日上午10:15:54</p>
 * <p>Description:酒店房间对象</p>
 */
@SuppressWarnings("serial")
public class HotelRoom implements Serializable {
    
    private Long id;

    private String name;

    /**
     * 房间预订的价格
     */
    private Double price;

    /**
     * 床的类型的id
     */
    private Long bedTypeId;
    
    /**
     * 床的类型的名字
     */
    private String bedTypeName;

    /**
     * 房间的总数量
     */
    private Integer totalNum;
    
    private List<String> imgs;
    
    private Hotel hotel;
    
    private Long hotelId;

    /**
     * 房间的面积
     */
    private Double size;

    private Date modifyTime;

    private Date createTime;

    private Boolean isDel;
    
    private String cover;
    
    /////////////////////房间数量管理额外参数
    /**
     * 当天 房间的剩余数量
     */
    private Integer remainNum;
    
    /**
     * 当天的记录的id 方便修改 记录中的数据
     */
    private Long recordId;
    
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(Long bedTypeId) {
        this.bedTypeId = bedTypeId;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
    
    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }


    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    
}
