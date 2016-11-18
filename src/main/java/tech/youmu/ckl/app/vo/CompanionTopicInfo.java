package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "旅伴帖子信息")
public class CompanionTopicInfo {
    
    @ApiModelProperty(value="帖子id")
    private long companionTopicId;
    
    @ApiModelProperty(value="标题")
    private String title;
    
    @ApiModelProperty(value="发帖人id")
    private long userId;

    @ApiModelProperty(value="发帖人姓名")
    private String userName;

    @ApiModelProperty(value="发帖人头像")
    private String headIcon;
    
    @ApiModelProperty(value="发帖时间")
    private String time;
    
    @ApiModelProperty(value="访问数量")
    private int viewCount;
    
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
    
    @ApiModelProperty(value="查看权限 1-所有人，2-实名认证，3-部分好友，4-女可以查看，5-男可以查看")
    private int permission;
    
    public long getCompanionTopicId() {
        return companionTopicId;
    }

    public void setCompanionTopicId(long companionTopicId) {
        this.companionTopicId = companionTopicId;
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

    

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
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

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
    
    

}