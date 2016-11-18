package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "帖子点赞明细")
public class PraiseDetailInfo {
    
    
    @ApiModelProperty(value="点赞人id")
    private long userId;

    @ApiModelProperty(value="点赞人姓名")
    private String userName;

    @ApiModelProperty(value="点赞人头像")
    private String headIcon;
    
    @ApiModelProperty(value="时间")
    private String time;
    
    @ApiModelProperty(value="帖子id")
    private long topicId;
    
    @ApiModelProperty(value="发帖人")
    private String postUserName;
    
    @ApiModelProperty(value="帖子内容")
    private String content;
    
    @ApiModelProperty(value="帖子图片")
    private String img;

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

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPostUserName() {
        return postUserName;
    }

    public void setPostUserName(String postUserName) {
        this.postUserName = postUserName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
    
    
    
    

}