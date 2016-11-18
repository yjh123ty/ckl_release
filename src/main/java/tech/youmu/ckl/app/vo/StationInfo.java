package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "站点信息")
public class StationInfo {
    
    public static final double BASE_PRICE=200;
    
    public static final double DISTANCE_PRICE=20;
	
    @ApiModelProperty(value="stationId")
	private long stationId;
	
    @ApiModelProperty(value="名称")
	private String stationName;
	
    @ApiModelProperty(value="电话")
	private String mobile;
    
    @ApiModelProperty(value="价格")
    private double price;
    
    public StationInfo() {
    }

    public StationInfo(Long id, String name, String mobile,Double price) {
            this.stationId = id;
            this.stationName = name;
            this.mobile = mobile;
            this.price = price;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
    
    
}
