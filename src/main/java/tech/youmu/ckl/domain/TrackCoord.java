package tech.youmu.ckl.domain;

import java.util.Date;

public class TrackCoord {
    private Long id;

    private String latitude;

    private String longtitude;

    private Integer order;

    private Boolean isDel;

    private Date createTime;

    private Date modifyTime;

    public TrackCoord() {
    }
    
    public TrackCoord(String longtitude, String latitude, Integer order) {
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.order =order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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
}