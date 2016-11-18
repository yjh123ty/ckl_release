package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "路线详情信息")
public class RouteDetailInfo {
	
    @ApiModelProperty(value="routeId")
	private long routeId;
	
    @ApiModelProperty(value="路线名称")
	private String name;
	
    @ApiModelProperty(value="距离")
	private String totalDistance;
	
    @ApiModelProperty(value="游玩天数")
	private String days;
    
    @ApiModelProperty(value="平均消费单位元")
    private double cost;
    
    @ApiModelProperty(value="适合人群")
    private List<String> suitNames;
    
    @ApiModelProperty(value="当前路线id")
    private long currentRouteId;
    
    @ApiModelProperty(value="是否有行程")
    private boolean isExistUserRoute;
    
    @ApiModelProperty(value="是否是当前行程")
    private boolean isCurrentUserRoute;
    
    @ApiModelProperty(value="路线列表，第一个为起点，最后一个为终点")
    private List<RouteStationInfo> routeStationInfos;

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<String> getSuitNames() {
        return suitNames;
    }

    public void setSuitNames(List<String> suitNames) {
        this.suitNames = suitNames;
    }

    public List<RouteStationInfo> getRouteStationInfos() {
        return routeStationInfos;
    }

    public void setRouteStationInfos(List<RouteStationInfo> routeStationInfos) {
        this.routeStationInfos = routeStationInfos;
    }

    public long getCurrentRouteId() {
        return currentRouteId;
    }

    public void setCurrentRouteId(long currentRouteId) {
        this.currentRouteId = currentRouteId;
    }

    public boolean getIsExistUserRoute() {
        return isExistUserRoute;
    }

    public void setIsExistUserRoute(boolean isExistUserRoute) {
        this.isExistUserRoute = isExistUserRoute;
    }

    public boolean getIsCurrentUserRoute() {
        return isCurrentUserRoute;
    }

    public void setIsCurrentUserRoute(boolean isCurrentUserRoute) {
        this.isCurrentUserRoute = isCurrentUserRoute;
    }

    
    
}
