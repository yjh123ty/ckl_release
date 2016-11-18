package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "徽章信息")
public class BadgeInfo {

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "图片")
	private String img;
	
	@ApiModelProperty(value = "是否以获取徽章，true已经获取，false没有")
    private boolean isExist;
	
	@ApiModelProperty(value = "描述")
    private String intro;
	
	@ApiModelProperty(value = "时间")
    private String time;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

    public boolean getIsExist() {
        return isExist;
    }

    public void setExist(boolean isExist) {
        this.isExist = isExist;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    
    
	
	

}