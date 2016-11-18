package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户当前行程信息")
public class CurrentUserRouteInfo {
	
    
    @ApiModelProperty(value="当前路线id")
    private long currentRouteId;
    
    @ApiModelProperty(value="是否有行程")
    private boolean isExistUserRoute;

    public CurrentUserRouteInfo() {
    }
    
    public CurrentUserRouteInfo(boolean isExistUserRoute) {
            this.isExistUserRoute = isExistUserRoute;
    }

    public CurrentUserRouteInfo(Long currentUserRouteId) {
        this.currentRouteId = currentUserRouteId;
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
    
    

    
    
}
