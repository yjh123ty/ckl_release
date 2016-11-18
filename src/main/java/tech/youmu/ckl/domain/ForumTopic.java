package tech.youmu.ckl.domain;

import java.util.Date;

/**
 * 
 * <p>Title:ForumTopic</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月2日上午10:23:03</p>
 * <p>Description:社区帖子</p>
 */
public class ForumTopic {
    private Long id;

    /**
     * 发帖人id
     */
    private Long userId;

    private Boolean isDel;
    
    private Long shareInId;
    
    private String shareInImg;
    
    /**
     * 类型，1-创建的，2-轨迹分享，3-游记分享
     */
    private Integer type;

    private Date createTime;

    private Date modifyTime;

    /**
     * 发帖内容
     */
    private String content;
    
    public ForumTopic() {
    }

    public ForumTopic(Long userId, String content,Integer type) {
           this.userId = userId;
           this.content = content;
           this.type = type;
    }

    public ForumTopic(Long userId, Long shareInId, String content, String shareInImg, Integer type) {
        this.userId = userId;
        this.content = content;
        this.type = type;
        this.shareInId = shareInId;
        this.shareInImg = shareInImg;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getShareInId() {
        return shareInId;
    }

    public void setShareInId(Long shareInId) {
        this.shareInId = shareInId;
    }

    public String getShareInImg() {
        return shareInImg;
    }

    public void setShareInImg(String shareInImg) {
        this.shareInImg = shareInImg;
    }
    
    
    
}