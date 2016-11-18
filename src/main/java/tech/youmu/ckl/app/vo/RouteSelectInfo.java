package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tech.youmu.ckl.domain.Station;

@ApiModel(value = "路线选择")
public class RouteSelectInfo {
	
    
    @ApiModelProperty(value="路线id")
    private long routeId;
	
    @ApiModelProperty(value="路线名称")
	private String name;
	
    @ApiModelProperty(value="开始地点")
	private String startSite;
    
    @ApiModelProperty(value="结束地点")
    private String endSite;

    public RouteSelectInfo() {
    }

    public RouteSelectInfo(Long routeId, String name) {
            this.routeId = routeId;
            this.name = name;
    }


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

    public String getStartSite() {
        return startSite;
    }

    public void setStartSite(String startSite) {
        this.startSite = startSite;
    }

    public String getEndSite() {
        return endSite;
    }

    public void setEndSite(String endSite) {
        this.endSite = endSite;
    }

    
    
}
