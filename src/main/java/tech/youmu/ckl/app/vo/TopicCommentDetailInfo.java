package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "社区帖子评论信息")
public class TopicCommentDetailInfo {
    
    @ApiModelProperty(value="帖子id")
    private long topicId;
    
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
    
    @ApiModelProperty(value="被回复人")
    private String byReplyName;
    
    @ApiModelProperty(value="被回复内容")
    private String byReplyContent;
    
    @ApiModelProperty(value="1-评论，2-回复")
    private int type;
    
    @ApiModelProperty(value="发帖人")
    private String postUserName;
    
    @ApiModelProperty(value="帖子内容")
    private String topicContent;
    
    @ApiModelProperty(value="帖子图片")
    private String img;
    
    @ApiModelProperty(value="1-社区帖子，2-找旅伴帖子")
    private int topicType;
    
    @ApiModelProperty(value="标题")
    private String title;
    
    @ApiModelProperty(value="开始时间")
    private String startTime;
    
    @ApiModelProperty(value="结束时间")
    private String endTime;
    
    @ApiModelProperty(value="路线名称")
    private String routeName;

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

    public String getPostUserName() {
        return postUserName;
    }

    public void setPostUserName(String postUserName) {
        this.postUserName = postUserName;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getTopicType() {
        return topicType;
    }

    public void setTopicType(int topicType) {
        this.topicType = topicType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getByReplyName() {
        return byReplyName;
    }

    public void setByReplyName(String byReplyName) {
        this.byReplyName = byReplyName;
    }

    public String getByReplyContent() {
        return byReplyContent;
    }

    public void setByReplyContent(String byReplyContent) {
        this.byReplyContent = byReplyContent;
    }

    
   
    
    
    
    
    

}