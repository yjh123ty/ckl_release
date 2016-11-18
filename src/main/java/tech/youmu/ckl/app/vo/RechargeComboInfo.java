package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "充值套餐信息")
public class RechargeComboInfo{

	@ApiModelProperty(value = "rechargeComboId")
	private long rechargeComboId;

	@ApiModelProperty(value = "名称")
	private String name;
	
	@ApiModelProperty(value = "内容")
    private String content;
	
	@ApiModelProperty(value = "价格")
    private double price;
	
	@ApiModelProperty(value = "充值套餐赠送服务信息")
	private List<PresentServiceInfo> presentServiceInfos;

    public long getRechargeComboId() {
        return rechargeComboId;
    }

    public void setRechargeComboId(long rechargeComboId) {
        this.rechargeComboId = rechargeComboId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<PresentServiceInfo> getPresentServiceInfos() {
        return presentServiceInfos;
    }

    public void setPresentServiceInfos(List<PresentServiceInfo> presentServiceInfos) {
        this.presentServiceInfos = presentServiceInfos;
    }

    
	

}