package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "游记内容信息")
public class TravelNoteContentInfo {

    @ApiModelProperty(value="内容图片id")
    private long travelNoteContentId;
    
    @ApiModelProperty(value="1-文字，2-图片")
    private int type;

    @ApiModelProperty(value="内容")
    private String content;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTravelNoteContentId() {
        return travelNoteContentId;
    }

    public void setTravelNoteContentId(long travelNoteContentId) {
        this.travelNoteContentId = travelNoteContentId;
    }

    
    
    
}