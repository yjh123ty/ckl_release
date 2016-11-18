/**
 * Project Name:ckl
 * File Name:Hotel.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import tech.youmu.ckl.constants.Global;
import tech.youmu.ckl.utils.ImageURLUtil;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title:Hotel</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月7日上午9:25:48</p>
 * <p>Description:站点的酒店</p>
 */
@SuppressWarnings("serial")
public class Hotel implements Serializable {
    
    private Long id;

    private String name;

    /**
     * 服务站点
     */
    private Station station;
    
    private String stationName;
    
    private Long stationId;
    
    /**
     * 服务站所在的区
     */
    private Integer districtCode;

    /**
     * 详细地址
     */
    private String address;
    
    /**
     * 服务站所在的区
     */
    private District district;
    
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    /**
     * 省市县三级区域地址
     */
    private String districtAddress;
    
    private List<String> outsideImgs;
    
    private List<String> innerImgs;
    
    private String latitude;    //纬度
    
    private String longtitude;  //经度
    
    /**
     * 封面图片
     */
    private String cover;

    /**
     * 酒店联系电话
     */
    private String mobile;

    /**
     * 酒店描述
     */
    private String intro;

    /**
     * 酒店星级
     */
    private Integer stars;

    /**
     * 是否删除
     */
    private Boolean isDel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date modifyTime;
    
    /**
     * 所有房间的总数量
     */
    private Integer totalRoomNum;
    
    private List<SysDicDetail> serviceContents = new ArrayList<SysDicDetail>(0);
    
    private List<HotelRoom> hotelRooms = new ArrayList<HotelRoom>(0);
    
    /**
     * 酒店状态 0开通(默认)  -1关闭
     */
    private Integer status;
    
    /**
     * 最低消费
     */
    private Double minimun;
    

    /**
     * 商家类型： 1-直营(默认)   2-合作
     */
    private Integer type;
    
    /**
     * 是否满房
     */
    private Boolean isFull;
    
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }


    public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getMinimun() {
        return minimun;
    }

    public void setMinimun(Double minimun) {
        this.minimun = minimun;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    @JsonFormat(pattern=Global.DATE_TIME_FORMAT, timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }


    public List<String> getOutsideImgs() {
        return outsideImgs;
    }

    public void setOutsideImgs(List<String> outsideImgs) {
        this.outsideImgs = outsideImgs;
    }

    public List<String> getInnerImgs() {
        return innerImgs;
    }

    public void setInnerImgs(List<String> innerImgs) {
        this.innerImgs = innerImgs;
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

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }


    public Integer getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    public List<SysDicDetail> getServiceContents() {
        return serviceContents;
    }

    public void setServiceContents(List<SysDicDetail> serviceContents) {
        this.serviceContents = serviceContents;
    }

    public List<HotelRoom> getHotelRooms() {
        return hotelRooms;
    }

    public void setHotelRooms(List<HotelRoom> hotelRooms) {
        this.hotelRooms = hotelRooms;
    }

    public Integer getTotalRoomNum() {
        return totalRoomNum;
    }

    public void setTotalRoomNum(Integer totalRoomNum) {
        this.totalRoomNum = totalRoomNum;
    }

    /*
     * 返回全地址
     */
    public String getFullAddress() {
        if(StringUtils.isNotBlank(this.districtAddress) && StringUtils.isNotBlank(this.address)) {
            return this.districtAddress + this.address;
        }
        return null;
    }

    public String getDistrictAddress() {
        return districtAddress;
    }

    public void setDistrictAddress(String districtAddress) {
        this.districtAddress = districtAddress;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Boolean getIsFull() {
        return isFull;
    }

    public void setIsFull(Boolean isFull) {
        this.isFull = isFull;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", station=" + station + ", stationName=" + stationName
				+ ", stationId=" + stationId + ", districtCode=" + districtCode + ", address=" + address + ", district="
				+ district + ", districtAddress=" + districtAddress + ", outsideImgs=" + outsideImgs + ", innerImgs="
				+ innerImgs + ", latitude=" + latitude + ", longtitude=" + longtitude + ", cover=" + cover + ", mobile="
				+ mobile + ", intro=" + intro + ", stars=" + stars + ", isDel=" + isDel + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + ", totalRoomNum=" + totalRoomNum + ", serviceContents="
				+ serviceContents + ", hotelRooms=" + hotelRooms + ", status=" + status + ", minimun=" + minimun
				+ ", type=" + type + ", isFull=" + isFull + "]";
	}

    
}
