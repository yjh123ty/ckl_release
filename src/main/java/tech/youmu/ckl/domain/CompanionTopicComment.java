package tech.youmu.ckl.domain;

import java.util.Date;

public class CompanionTopicComment {
    private Long id;

    private Long userId;

    private String content;

    private Long companionTopicId;

    private Boolean isDel;

    private Integer type;

    private Date createTime;

    private Date modifyTime;
    
    private Long byReplyId;
    
    private String byReplyContent;
    
    public CompanionTopicComment() {
    }

    public CompanionTopicComment(Long userId, String content, Long companionTopicId,Integer type) {
            this.userId = userId;
            this.content = content;
            this.companionTopicId = companionTopicId;
            this.type = type;
    }

    public CompanionTopicComment(Long userId, String content, Long companionTopicId,Integer type, Long byReplyId,String byReplyContent) {
        this.userId = userId;
        this.content = content;
        this.companionTopicId = companionTopicId;
        this.type = type;
        this.byReplyId = byReplyId;
        this.byReplyContent = byReplyContent;
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

    

   

    public Long getCompanionTopicId() {
        return companionTopicId;
    }

    public void setCompanionTopicId(Long companionTopicId) {
        this.companionTopicId = companionTopicId;
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