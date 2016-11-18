package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "轨迹信息")
public class TrackInfo {
	
    @ApiModelProperty(value="轨迹id")
    private long trackId;
    
    @ApiModelProperty(value="日")
    private String day;
    
    @ApiModelProperty(value="图片")
    private String routeImg;
    
    @ApiModelProperty(value="开始地点")
    private String startName;

    @ApiModelProperty(value="结束地点")
    private String endName;

    @ApiModelProperty(value="距离")
    private int distance;


    @ApiModelProperty(value="天数")
    private int dayCount;


    public long getTrackId() {
        return trackId;
    }


    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }


    public String getDay() {
        return day;
    }


    public void setDay(String day) {
        this.day = day;
    }


    public String getRouteImg() {
        return routeImg;
    }


    public void setRouteImg(String routeImg) {
        this.routeImg = routeImg;
    }


    public String getStartName() {
        return startName;
    }


    public void setStartName(String startName) {
        this.startName = startName;
    }


    public String getEndName() {
        return endName;
    }


    public void setEndName(String endName) {
        this.endName = endName;
    }


    public int getDistance() {
        return distance;
    }


    public void setDistance(int distance) {
        this.distance = distance;
    }


    public int getDayCount() {
        return dayCount;
    }


    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

   
    
    


	
	
}