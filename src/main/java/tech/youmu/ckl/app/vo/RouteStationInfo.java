package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "路线站点信息")
public class RouteStationInfo {
	
    @ApiModelProperty(value="stationId")
	private long stationId;
	
    @ApiModelProperty(value="名称")
	private String stationName;
	
    @ApiModelProperty(value="是否收藏")
	private boolean isCollect;
	
    @ApiModelProperty(value="距离单位km")
	private String distance;
    
    @ApiModelProperty(value="1景点, 2车刻丽服务站")
    private int stationType;
    
    @ApiModelProperty(value="纬度")
    private double lng;
    
    @ApiModelProperty(value="经度")
    private double lat;
    
    @ApiModelProperty(value="是否经过")
    private boolean isPass;

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public boolean getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getStationType() {
        return stationType;
    }

    public void setStationType(int stationType) {
        this.stationType = stationType;
    }


    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public boolean getIsPass() {
        return isPass;
    }

    public void setIsPass(boolean isPass) {
        this.isPass = isPass;
    }
    
    
    

}
