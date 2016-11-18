package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tech.youmu.ckl.utils.DateUtil;
import tech.youmu.ckl.utils.UUIDUtil;

@ApiModel(value = "微信支付信息")
public class WeixinInfo {
	
	@ApiModelProperty(value="应用ID")
    private String appid ;
	
	@ApiModelProperty(value="商户号")
    private String partnerid;
	
	@ApiModelProperty(value="预支付交易会话ID")
    private String prepayid;
	
	@ApiModelProperty(value="扩展字段，package")
    private String package_="Sign=WXPay";
	
	@ApiModelProperty(value="随机字符串")
    private String noncestr =UUIDUtil.getUUID();
    
    @ApiModelProperty(value="时间戳")
    private String timestamp = String.valueOf(DateUtil.getTimestamp());
    
    @ApiModelProperty(value="签名")
    private String sign;
    
    public WeixinInfo() {
    }
    
    public WeixinInfo(String appid,String partnerid,String prepayid) {
        this.appid = appid;
        this.partnerid = partnerid;
        this.prepayid =prepayid;
    }

    

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }
    

    public String getPackage_() {
        return package_;
    }

    public void setPackage_(String package_) {
        this.package_ = package_;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }
    

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
	
    
    

	
    


}