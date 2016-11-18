package tech.youmu.ckl.domain;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.utils.ImageURLUtil;

public class TravelNoteContent {
    private Long id;

    private MultipartFile img;

    private Long travelNoteId;

    private Boolean isDel;

    private String createTime;

    private String modifyTime;

    private String content;
    
    /**
     * 1-文字，2-图片
     */
    private Integer type;
    
    private Integer order;
    
    public TravelNoteContent() {
    }

    public TravelNoteContent(Long id, String content) {
            this.id = id;
            this.content = content;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
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
    
    
    
}