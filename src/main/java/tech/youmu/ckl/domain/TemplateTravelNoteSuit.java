package tech.youmu.ckl.domain;

import java.util.Date;

public class TemplateTravelNoteSuit {
    private Long id;

    private Long travelNoteId;

    private String name;

    private Boolean isDel;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTravelNoteId() {
        return travelNoteId;
    }

    public void setTravelNoteId(Long travelNoteId) {
        this.travelNoteId = travelNoteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}