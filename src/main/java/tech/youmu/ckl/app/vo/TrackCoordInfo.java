package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "轨迹坐标信息")
public class TrackCoordInfo {
	
    @ApiModelProperty(value="纬度")
    private String lng;
    
    @ApiModelProperty(value="经度")
    private String lat;

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

    
    

}