package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import tech.youmu.ckl.constants.Global;
import tech.youmu.ckl.utils.ImageURLUtil;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
  * @ClassName: Station
  * @Description: 景点 服务站   等 路线上的点
  * @author youmu-zh
  * @date 2016年8月17日 下午2:46:26
  *
 */
@SuppressWarnings("serial")
public class Station implements Serializable{

	
	private Long id;
	
	
	private String name;
	
	/**
	 * 纬度
	 */
	private String longtitude;
	
	/**
	 * 经度
	 */
	private String latitude;
	
	/**
	 * 服务站所在的区
	 */
	private District district;
	
	/**
	 * 详细地址
	 */
	private String address;
	
	private String districtAddress;
	
	/**
	 * 该线路点在路线中的位置 大于0 只有添加路线关联站点表时才用
	 */
	private Integer order;
	
	/**
     * 该线路点在路线中 距离上一个站点的距离 只有路线关联时可用
     */
    private Double distance;
    
	
	/**
	 * 1  为景点
	 * 2 为车刻丽服务站
	 */
	private Integer type;
	
	
	/**
	 * 图片
	 */
	private List<String> imgs;
	
	/**
	 * 状态 
	 */
	private Integer status;
	
	/**
	 * 录入时间
	 */
	private Date createTime;
	
	/**
	 * 介绍
	 */
	private String intro;
	
	/**
	 * 平均评价星级
	 */
	private Integer stars;
	
	private String mobile;
	
	/**
	 * 是否不用
	 */
	
	private Boolean isDel;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	
	/**
	 * 站点的服务类型 列表
	 */
	private List<ServiceType> serviceTypes = new ArrayList<>(0);

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
    


    public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonFormat(pattern=Global.DATE_TIME_FORMAT, timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(List<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
    
    public String getFullAddress() {
        if(StringUtils.isNotBlank(this.address) && StringUtils.isNotBlank(this.getDistrictAddress())){
            return this.getDistrictAddress() + this.address;
        }else {
            return null;
        }
    }

    public String getDistrictAddress() {
        return districtAddress;
    }

    public void setDistrictAddress(String districtAddress) {
        this.districtAddress = districtAddress;
    }

    @Override
    public String toString() {
        return "Station [id=" + id + ", name=" + name + ", longtitude=" + longtitude + ", latitude=" + latitude + ", district=" + district + ", address=" + address + ", districtAddress=" + districtAddress + ", order=" + order + ", distance=" + distance + ", type=" + type + ", imgs=" + imgs + ", status=" + status + ", createTime=" + createTime + ", intro=" + intro + ", stars=" + stars + ", mobile=" + mobile + ", isDel=" + isDel + ", modifyTime=" + modifyTime + ", serviceTypes=" + serviceTypes + "]";
    }
    
    
}