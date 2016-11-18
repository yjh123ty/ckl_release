package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "导航路线信息")
public class NavigateRouteInfo {
	
    @ApiModelProperty(value="站点id")
    private long stationId;
    
    @ApiModelProperty(value="时间")
    private String time;
	
    @ApiModelProperty(value="距离 km")
	private double distance;
	
    @ApiModelProperty(value="过路费")
	private double toll;
    
    @ApiModelProperty(value="1-最近的服务站,2-顺路的服务站")
    private int type;
    
    @ApiModelProperty(value="纬度")
    private String lng;
    
    @ApiModelProperty(value="经度")
    private String lat;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getToll() {
        return toll;
    }

    public void setToll(double toll) {
        this.toll = toll;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    
    
    public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    
	
    

}
