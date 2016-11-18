package tech.youmu.ckl.domain;

import java.util.Date;

public class TemplateTravelNoteContent {
    private Long id;

    private Integer type;

    private Integer order;

    private Long travelNoteId;

    private Boolean isDel;

    private Date createTime;

    private Date modifyTime;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getTravelNoteId() {
        return travelNoteId;
    }

    public void setTravelNoteId(Long travelNoteId) {
        this.travelNoteId = travelNoteId;
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
}