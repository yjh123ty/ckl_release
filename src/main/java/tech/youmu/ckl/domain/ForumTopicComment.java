package tech.youmu.ckl.domain;

import java.util.Date;

public class ForumTopicComment {
    private Long id;

    private Long userId;

    private String content;

    private Long forumTopicId;

    private Boolean isDel;

    private Integer type;

    private Date createTime;

    private Date modifyTime;
    
    private Long byReplyId;
    
    private String byReplyContent;
    
    public ForumTopicComment() {
    }

    public ForumTopicComment(Long userId, String content, Long forumTopicId,Integer type) {
            this.userId = userId;
            this.content = content;
            this.forumTopicId = forumTopicId;
            this.type = type;
    }

    public ForumTopicComment(Long userId, String content, Long forumTopicId,Integer type, Long byReplyId,String byReplyContent) {
        this.userId = userId;
        this.content = content;
        this.forumTopicId = forumTopicId;
        this.type = type;
        this.byReplyId = byReplyId;
        this.byReplyContent=byReplyContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    

    public Long getForumTopicId() {
        return forumTopicId;
    }

    public void setForumTopicId(Long forumTopicId) {
        this.forumTopicId = forumTopicId;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Long getByReplyId() {
        return byReplyId;
    }

    public void setByReplyId(Long byReplyId) {
        this.byReplyId = byReplyId;
    }

    public String getByReplyContent() {
        return byReplyContent;
    }

    public void setByReplyContent(String byReplyContent) {
        this.byReplyContent = byReplyContent;
    }
    
    
    
    
}