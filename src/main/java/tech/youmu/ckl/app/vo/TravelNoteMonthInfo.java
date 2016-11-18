package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "游记月份")
public class TravelNoteMonthInfo {
	
	@ApiModelProperty(value="月份")
    private String month;

	@ApiModelProperty(value="游记信息")
    private List<TravelNoteInfo> travelNoteInfos ;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<TravelNoteInfo> getTravelNoteInfos() {
        return travelNoteInfos;
    }

    public void setTravelNoteInfos(List<TravelNoteInfo> travelNoteInfos) {
        this.travelNoteInfos = travelNoteInfos;
    }

	
	


	
}