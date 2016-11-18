package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "路线信息")
public class RouteInfo {
	
    
    @ApiModelProperty(value="路线id")
    private long routeId;
	
    @ApiModelProperty(value="路线名称")
	private String name;
	
    @ApiModelProperty(value="路线图片")
	private String img;
	
    @ApiModelProperty(value="游玩天数")
	private int days;


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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
    
	
    
}
