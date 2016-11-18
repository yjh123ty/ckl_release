package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "行程信息")
public class UserRouteInfo {
	
    @ApiModelProperty(value="行程id")
    private long userRouteId;
    
    @ApiModelProperty(value="路线id")
    private long routeId;
	
    @ApiModelProperty(value="开始名称")
	private String startStationName;
	
    @ApiModelProperty(value="结束名称")
	private String endStationName;
	
    @ApiModelProperty(value="状态，1-正在进行，2-结束,3-已完成")
	private int status;
    
    @ApiModelProperty(value="时间")
    private String time;

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getUserRouteId() {
        return userRouteId;
    }

    public void setUserRouteId(long userRouteId) {
        this.userRouteId = userRouteId;
    }


    
    
}
