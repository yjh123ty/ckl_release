package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "社区帖子信息")
public class ForumTopicDetailInfo {
    
    @ApiModelProperty(value="帖子id")
    private long forumTopicId;
    
    @ApiModelProperty(value="发帖人id")
    private long userId;

    @ApiModelProperty(value="发帖人姓名")
    private String userName;

    @ApiModelProperty(value="发帖人头像")
    private String headIcon;
    
    @ApiModelProperty(value="发帖时间")
    private String time;
    
    @ApiModelProperty(value="点赞数量")
    private int praiseCount;
    
    @ApiModelProperty(value="评论数量")
    private int commentCount;
    
    @ApiModelProperty(value="是否点赞")
    private boolean isPraise;
    
    @ApiModelProperty(value="图片")
    private List<String> imgs;
    
    @ApiModelProperty(value="内容")
    private String content;
    
    @ApiModelProperty(value="分享路径")
    private String shareUrl;
    
    @ApiModelProperty(value="类型，1-创建的，2-轨迹分享，3-游记分享")
    private Integer type;
    
    @ApiModelProperty(value="轨迹游记分享图片")
    private String shareInImg;
    
    private Long shareInId;
    



    public long getForumTopicId() {
        return forumTopicId;
    }

    public void setForumTopicId(long forumTopicId) {
        this.forumTopicId = forumTopicId;
    }

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

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(boolean isPraise) {
        this.isPraise = isPraise;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getShareInImg() {
        return shareInImg;
    }

    public void setShareInImg(String shareInImg) {
        this.shareInImg = shareInImg;
    }

    public Long getShareInId() {
        return shareInId;
    }

    public void setShareInId(Long shareInId) {
        this.shareInId = shareInId;
    }

    

    
    
}