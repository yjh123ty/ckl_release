package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "团队信息")
public class TeamInfo {

	@ApiModelProperty(value = "id")
	private String userId;

	@ApiModelProperty(value = "头像")
	private String headIcon;
	
	@ApiModelProperty(value = "名称")
    private String userName;
	
	@ApiModelProperty(value = "时间")
    private String time;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

	
    
	
	

}