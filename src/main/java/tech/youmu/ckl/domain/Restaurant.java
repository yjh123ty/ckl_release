/**
 * Project Name:ckl
 * File Name:Restaurant.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import tech.youmu.ckl.constants.Global;
import tech.youmu.ckl.utils.ImageURLUtil;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * Title:Restaurant
 * </p>
 * @author zh
 * @version v1.0
 *          <p>
 *          Date:2016年9月7日上午11:37:56
 *          </p>
 *          <p>
 *          Description:饭店对象
 *          </p>
 */
@SuppressWarnings("serial")
public class Restaurant implements Serializable {
    
    private Long id;

    private String name;

    /**
     * 区号
     */
    private Integer districtCode;

    /**
     * 详细地址
     */
    private String address;
    
    /**
     * 全地址
     */
    private String districtAddress;

    /**
     * 饭店环境图片
     */
    private List<String> imgs;
    
    /**
     * 饭店电话
     */
    private String mobile;

    /**
     * 饭店描述
     */
    private String intro;

    /**
     * 饭店星级
     */
    private Integer stars;

    /**
     * 营业开始时间
     */
    @DateTimeFormat(pattern="HH:mm")
    @JsonFormat(pattern="HH:mm")
    private Date openTime;

    /**
     * 营业结束时间
     */
    @DateTimeFormat(pattern="HH:mm")
    @JsonFormat(pattern="HH:mm")
    private Date closeTime;

    /**
     * 订餐提示
     */
    private String orderTips;

    /**
     * 用餐规则
     */
    private String orderRule;
    
    /**
     * 温馨提示
     */
    private String orderSweet;

    private Boolean isDel;
    
    private String latitude;
    
    private String longtitude;

    @JsonFormat(pattern=Global.DATE_TIME_FORMAT, timezone="GMT+8")
    private Date createTime;

    private Date modifyTime;
    
    private String stationName;
    
    private Double minimun;
    
    /**
     * 封面图片
     */
    private String cover;

    /**
     * 状态   0开通    -1关闭
     */
    private Integer status;
    
    /**
     * 饭店套餐
     */
    private List<RestaurantCombo> restaurantCombos = new ArrayList<>(0);
    
    private Station station;
    
    
    private Long stationId;
    
    private Integer type;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullAddress() {
        if(StringUtils.isNotBlank(this.districtAddress) && StringUtils.isNotBlank(this.address)){
            return this.districtAddress+this.address;
        }
        return null;
    }


    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
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

    public String getOpenTime() {
        return openTime == null ? null : new SimpleDateFormat("HH:mm").format(openTime);
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        if(closeTime != null ) {
            return new SimpleDateFormat("HH:mm").format(closeTime);
        }else {
            return null;
        }
    }

    public void setCloseTime(Date closeTime) {
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

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

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

    public List<RestaurantCombo> getRestaurantCombos() {
        return restaurantCombos;
    }

    public void setRestaurantCombos(List<RestaurantCombo> restaurantCombos) {
        this.restaurantCombos = restaurantCombos;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
    public String getDistrictAddress() {
		return districtAddress;
	}

	public void setDistrictAddress(String districtAddress) {
		this.districtAddress = districtAddress;
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

	public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Double getMinimun() {
        return minimun;
    }

    public void setMinimun(Double minimun) {
        this.minimun = minimun;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getOrderSweet() {
        return orderSweet;
    }

    public void setOrderSweet(String orderSweet) {
        this.orderSweet = orderSweet;
    }

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", districtCode=" + districtCode + ", address=" + address
				+ ", districtAddress=" + districtAddress + ", imgs=" + imgs + ", mobile=" + mobile + ", intro=" + intro
				+ ", stars=" + stars + ", openTime=" + openTime + ", closeTime=" + closeTime + ", orderTips="
				+ orderTips + ", orderRule=" + orderRule + ", orderSweet=" + orderSweet + ", isDel=" + isDel
				+ ", latitude=" + latitude + ", longtitude=" + longtitude + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + ", stationName=" + stationName + ", minimun=" + minimun + ", cover="
				+ cover + ", status=" + status + ", restaurantCombos=" + restaurantCombos + ", station=" + station
				+ ", stationId=" + stationId + ", type=" + type + "]";
	}
    
    
}
