package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "收藏的服务站信息")
public class CollectStationInfo {
	
	@ApiModelProperty(value="服务站id")
    private long stationId;
	
	@ApiModelProperty(value="服务站名称")
    private String stationName;

	@ApiModelProperty(value="服务站星级")
    private int stationStars;

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

	public int getStationStars() {
		return stationStars;
	}

	public void setStationStars(int stationStars) {
		this.stationStars = stationStars;
	}

	

}