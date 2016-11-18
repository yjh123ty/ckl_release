package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "游记信息")
public class TravelNoteInfo {
	
    @ApiModelProperty(value="游记id")
    private long travelNoteId;
    
	
	@ApiModelProperty(value="日")
    private String day;
	
	@ApiModelProperty(value="标题")
    private String title;
    
    
    @ApiModelProperty(value="路线图片")
    private String routeImg;
    

    @ApiModelProperty(value="距离")
    private int distance;


    @ApiModelProperty(value="天数")
    private int dayCount;


    public long getTravelNoteId() {
        return travelNoteId;
    }


    public void setTravelNoteId(long travelNoteId) {
        this.travelNoteId = travelNoteId;
    }


    public String getDay() {
        return day;
    }


    public void setDay(String day) {
        this.day = day;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getRouteImg() {
        return routeImg;
    }


    public void setRouteImg(String routeImg) {
        this.routeImg = routeImg;
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