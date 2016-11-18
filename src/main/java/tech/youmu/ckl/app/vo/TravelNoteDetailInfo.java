package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "游记明细信息")
public class TravelNoteDetailInfo {
	
    @ApiModelProperty(value="游记id")
    private long travelNoteId;
	
    @ApiModelProperty(value="标题")
    private String title;
    
	@ApiModelProperty(value="日期")
    private String time;
	
	@ApiModelProperty(value="用户名称")
    private String userName;
    
    @ApiModelProperty(value="出发时间")
    private String departTime;
    
    @ApiModelProperty(value="游玩天数")
    private int dayCount;
    
    @ApiModelProperty(value="人均消费")
    private double capitaCost;
    
    @ApiModelProperty(value="分享路径")
    private String shareUrl;
    
    @ApiModelProperty(value="适合人群")
    private List<String> suitNames;
    
    @ApiModelProperty(value="游记图片内容")
    private List<TravelNoteContentInfo> travelNoteContentInfos;

    public long getTravelNoteId() {
        return travelNoteId;
    }

    public void setTravelNoteId(long travelNoteId) {
        this.travelNoteId = travelNoteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
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

    public List<TravelNoteContentInfo> getTravelNoteContentInfos() {
        return travelNoteContentInfos;
    }

    public void setTravelNoteContentInfos(List<TravelNoteContentInfo> travelNoteContentInfos) {
        this.travelNoteContentInfos = travelNoteContentInfos;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    

    
    
}