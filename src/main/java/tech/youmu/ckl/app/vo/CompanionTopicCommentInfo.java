package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "旅伴帖子评论信息")
public class CompanionTopicCommentInfo {
    
    
    @ApiModelProperty(value="评论/回复人id")
    private long userId;

    @ApiModelProperty(value="评论/回复人姓名")
    private String userName;

    @ApiModelProperty(value="评论/回复人头像")
    private String headIcon;
    
    @ApiModelProperty(value="评论/回复时间")
    private String time;
    
    @ApiModelProperty(value="评论/回复内容")
    private String content;
    
    @ApiModelProperty(value="1-评论，2-回复")
    private int type;
    
    @ApiModelProperty(value="被回复人姓名")
    private String byReplyName;


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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getByReplyName() {
        return byReplyName;
    }

    public void setByReplyName(String byReplyName) {
        this.byReplyName = byReplyName;
    }
    
    
    
    
    

}