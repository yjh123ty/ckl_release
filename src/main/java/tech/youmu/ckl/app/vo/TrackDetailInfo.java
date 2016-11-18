package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "轨迹明细信息")
public class TrackDetailInfo {
	
    @ApiModelProperty(value="轨迹id")
    private long trackId;

    @ApiModelProperty(value="日期")
    private String date;
    
	@ApiModelProperty(value="距离")
    private int distance;


	@ApiModelProperty(value="天数")
    private int dayCount;
	
	@ApiModelProperty(value="人均消费")
    private double capitaCost;
	
	@ApiModelProperty(value="分享路径")
    private String shareUrl;
	
	@ApiModelProperty(value="适合人群")
    private List<String> suitNames;
	
	@ApiModelProperty(value="坐标")
    private List<TrackCoordInfo> trackCoordInfos;

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public double getCapitaCost() {
        return capitaCost;
    }

    public void setCapitaCost(double capitaCost) {
        this.capitaCost = capitaCost;
    }

    public List<String> getSuitNames() {
        return suitNames;
    }

    public void setSuitNames(List<String> suitNames) {
        this.suitNames = suitNames;
    }

    public List<TrackCoordInfo> getTrackCoordInfos() {
        return trackCoordInfos;
    }

    public void setTrackCoordInfos(List<TrackCoordInfo> trackCoordInfos) {
        this.trackCoordInfos = trackCoordInfos;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    
    
	

	
}