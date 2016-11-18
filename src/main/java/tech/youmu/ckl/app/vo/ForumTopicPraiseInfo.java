package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "社区帖子点赞信息")
public class ForumTopicPraiseInfo {
    
    
    @ApiModelProperty(value="点赞人id")
    private long userId;

    @ApiModelProperty(value="点赞人姓名")
    private String userName;

    @ApiModelProperty(value="点赞人头像")
    private String headIcon;
    


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

    
    
    
    

}