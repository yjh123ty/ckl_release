package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "旅伴帖子详情信息")
public class CompanionTopicDetailInfo {
    
    @ApiModelProperty(value="帖子id")
    private long companionTopicId;
    
    @ApiModelProperty(value="标题")
    private String title;
    
    @ApiModelProperty(value="内容")
    private String content;
    
    @ApiModelProperty(value="发帖人姓名")
    private String userName;
    
    @ApiModelProperty(value="发帖人头像")
    private String headIcon;

    @ApiModelProperty(value="发帖时间")
    private String time;
    
    @ApiModelProperty(value="评论数量")
    private int commentCount;
    
    @ApiModelProperty(value="开始时间")
    private String startTime;
    
    @ApiModelProperty(value="结束时间")
    private String endTime;

    @ApiModelProperty(value="路线id")
    private long routeId;
    
    @ApiModelProperty(value="路线名称")
    private String routeName;
    
    @ApiModelProperty(value="分享路径")
    private String shareUrl;
    
    @ApiModelProperty(value="图片")
    private List<String> imgs;
    
    
    @ApiModelProperty(value="评论")
    private List<CompanionTopicCommentInfo> companionTopicCommentInfos;

    public long getCompanionTopicId() {
        return companionTopicId;
    }

    public void setCompanionTopicId(long companionTopicId) {
        this.companionTopicId = companionTopicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
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

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
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

    public List<CompanionTopicCommentInfo> getCompanionTopicCommentInfos() {
        return companionTopicCommentInfos;
    }

    public void setCompanionTopicCommentInfos(List<CompanionTopicCommentInfo> companionTopicCommentInfos) {
        this.companionTopicCommentInfos = companionTopicCommentInfos;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }
    
    
    
    

}