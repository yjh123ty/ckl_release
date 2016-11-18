package tech.youmu.ckl.domain;

import java.util.Date;

/**
 * 
 * <p>Title:CompanionTopic</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月6日下午3:56:15</p>
 * <p>Description:找旅伴帖子</p>
 */
public class CompanionTopic {
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 发帖人
     */
    private Long userId;

    private String startTime;

    private String endTime;

    private Long routeId;

    /**
     * 用来置顶的字段 保存时间戳
     */
    private Long top;

    private Boolean isDel;

    private String createTime;

    private String modifyTime;

    /**
     * 查看权限 1-所有人，2-实名认证，3-部分好友，4-女可以查看，5-男可以查看
     */
    private Integer permission;

    private String content;
    
    public CompanionTopic() {
    }

    public CompanionTopic(String startTime, String endTime, Long routeId, String title, String content, Long userId, Integer permission,Long top) {
        this.startTime =startTime;
        this.endTime = endTime;
        this.routeId = routeId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.permission = permission;
        this.top = top;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

   

    public Long getTop() {
        return top;
    }

    public void setTop(Long top) {
        this.top = top;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    

}