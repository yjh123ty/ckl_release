package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "推广信息")
public class PromotionCodeInfo{

    @ApiModelProperty(value = "套餐ID")
    private long rechargeComboId;

	@ApiModelProperty(value = "名称")
	private String name;
	
	@ApiModelProperty(value = "套餐内容")
	private String content;
	
	@ApiModelProperty(value = "推广码")
    private String promotionCode;
	
	@ApiModelProperty(value = "推广二维码路径")
    private String promotionQRCodeUrl;
	
	@ApiModelProperty(value = "分享路径")
    private String shareUrl;
	
	@ApiModelProperty(value = "价格")
    private double price;
	
	@ApiModelProperty(value = "充值套餐赠送服务信息")
    private List<PresentServiceInfo> presentServiceInfos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPromotionQRCodeUrl() {
        return promotionQRCodeUrl;
    }

    public void setPromotionQRCodeUrl(String promotionQRCodeUrl) {
        this.promotionQRCodeUrl = promotionQRCodeUrl;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public long getRechargeComboId() {
        return rechargeComboId;
    }

    public void setRechargeComboId(long rechargeComboId) {
        this.rechargeComboId = rechargeComboId;
    }

    public List<PresentServiceInfo> getPresentServiceInfos() {
        return presentServiceInfos;
    }

    public void setPresentServiceInfos(List<PresentServiceInfo> presentServiceInfos) {
        this.presentServiceInfos = presentServiceInfos;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
	
	


	

}