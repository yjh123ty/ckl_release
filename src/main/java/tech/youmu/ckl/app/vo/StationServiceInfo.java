package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "站点服务信息")
public class StationServiceInfo {
	
    @ApiModelProperty(value="stationId")
	private long stationId;
	
    @ApiModelProperty(value="服务类型(1-酒店，2-饭店，3-汽车保养, 4-零部件维修 ，5-道路救援, 6娱乐,7汽车销售, 8卫生间,9停车)")
    private Integer serviceType;
    

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }


    
    
    
}
