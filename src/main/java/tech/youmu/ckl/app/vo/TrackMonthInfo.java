package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "轨迹月份")
public class TrackMonthInfo {
	
	@ApiModelProperty(value="月份")
    private String month;

	@ApiModelProperty(value="轨迹信息")
    private List<TrackInfo> trackInfos ;


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<TrackInfo> getTrackInfos() {
        return trackInfos;
    }

    public void setTrackInfos(List<TrackInfo> trackInfos) {
        this.trackInfos = trackInfos;
    }

    
	
	
}