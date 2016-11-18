package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "今天考勤信息")
public class TodayAttendanceInfo {

    @ApiModelProperty(value="服务站名称")
    private String stationName;
    
	@ApiModelProperty(value = "时间")
    private String time;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

	
	
}