/** 
* 2015-11-21 
* User.java 
* author:Zack Chan
*/ 
package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "车友信息")
public class RidersInfo implements Comparable<RidersInfo>{
	
    @ApiModelProperty(value="好友id,即friendId")
    private long userId;

    @ApiModelProperty(value="好友名称")
    private String userName;
    
    @ApiModelProperty(value="好友头像")
    private String headIcon;
    
    @ApiModelProperty(value="距离 m")
    private double distance;
    
    @ApiModelProperty(value="纬度")
    private String lng;
    
    @ApiModelProperty(value="经度")
    private String lat;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    

    public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public int compareTo(RidersInfo o) {
        if(this.distance<o.distance){
            return -1;
        }else if(this.distance==o.distance){
            return 0;
        }
        return 1;
    }
    
    


	
}
