package tech.youmu.ckl.domain;

import java.util.Date;

import tech.youmu.ckl.constants.Global;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Topic {
    
    private Long id;
    
    /**
     * 帖子的标题
     */
    private String title;
    
    /**
     * 帖子的内容
     */
    private String content;
    
    /**
     * 发帖的用户
     */
    private User user;
    
    /**
     * 结伴的开始时间
     */
    @JsonFormat(pattern=Global.DATE_TIME_FORMAT, timezone="GMT+8")
    private Date startTime;
    
    /**
     * 结伴的结束时间
     */
    @JsonFormat(pattern=Global.DATE_TIME_FORMAT, timezone="GMT+8")
    private Date endTime;
    
    /**
     * 结伴的路线
     */
    private Route route;
    
    /**
     * 帖子的查看数量
     */
    private Integer viewCount;
    
    /**
     * 帖子的评论数量
     */
    private Integer commentCount;
    
    /**
     * 是否删除
     */
    private Boolean isDel;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern=Global.DATE_TIME_FORMAT, timezone="GMT+8")
    private Date createTime;
    
    /**
     * 最近修改时间
     */
    private Date modifyTime;
    
    /**
     * 帖子的点赞数量
     */
    private Integer zan;
    
    /**
     * 帖子排序 置顶 每次置顶 查询该top最大值　加1
     */
    private Boolean top;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getZan() {
        return zan;
    }

    public void setZan(Integer zan) {
        this.zan = zan;
    }

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }
}