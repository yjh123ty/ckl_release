package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "路线攻略信息")
public class RouteGuideInfo {
	
    @ApiModelProperty(value="目录名称")
	private String name;
	
    @ApiModelProperty(value="路线图片")
	private String img;
	
    @ApiModelProperty(value="文字")
	private String text;
    
    @ApiModelProperty(value="子信息")
    private List<RouteGuideSubInfo> routeGuideSubInfos;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<RouteGuideSubInfo> getRouteGuideSubInfos() {
        return routeGuideSubInfos;
    }

    public void setRouteGuideSubInfos(List<RouteGuideSubInfo> routeGuideSubInfos) {
        this.routeGuideSubInfos = routeGuideSubInfos;
    }

    
    
	
    
}
