package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "充值套餐赠送服务信息")
public class PresentServiceInfo{

	@ApiModelProperty(value = "presentServiceId")
	private long presentServiceId;

	@ApiModelProperty(value = "名称")
	private String name;
	
	@ApiModelProperty(value = "次数")
    private int count;
	
	@ApiModelProperty(value = "描述")
    private String intro;

    public long getPresentServiceId() {
        return presentServiceId;
    }

    public void setPresentServiceId(long presentServiceId) {
        this.presentServiceId = presentServiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

	
	

	

}