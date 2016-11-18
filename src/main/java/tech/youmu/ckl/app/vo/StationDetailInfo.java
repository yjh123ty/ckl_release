package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "站点明细信息")
public class StationDetailInfo {
	
    @ApiModelProperty(value="stationId")
	private long stationId;
    
    @ApiModelProperty(value="星数")
    private int stars;
    
    @ApiModelProperty(value="名称")
	private String stationName;
    
    @ApiModelProperty(value="地址")
    private String address;
    
    @ApiModelProperty(value="纬度")
    private double lng;
    
    @ApiModelProperty(value="经度")
    private double lat;
	
    @ApiModelProperty(value="电话")
	private String mobile;
    
    @ApiModelProperty(value="介绍")
    private String introduce;
    
    @ApiModelProperty(value="封面图片")
    private String stationCoverImg;
    
    @ApiModelProperty(value="1 景点 2 车刻丽服务站")
    private int type;
    
    @ApiModelProperty(value="图片")
    private List<String> stationImgs;
    
    @ApiModelProperty(value="服务类型")
    private List<StationServiceInfo> serviceInfos;

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getStationCoverImg() {
        return stationCoverImg;
    }

    public void setStationCoverImg(String stationCoverImg) {
        this.stationCoverImg = stationCoverImg;
    }

    public List<String> getStationImgs() {
        return stationImgs;
    }

    public void setStationImgs(List<String> stationImgs) {
        this.stationImgs = stationImgs;
    }

    
    public List<StationServiceInfo> getServiceInfos() {
        return serviceInfos;
    }

    public void setServiceInfos(List<StationServiceInfo> serviceInfos) {
        this.serviceInfos = serviceInfos;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
    
    
    
    
}
