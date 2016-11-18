/** 
* 2015-11-21 
* User.java 
* author:Zack Chan
*/ 
package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tech.youmu.ckl.utils.ConfigUtil;

@ApiModel(value = "荣云信息")
public class RongTokenInfo{
	
	
	@ApiModelProperty(value="token")
	private String token;
	
	@ApiModelProperty(value="key")
	private String key =ConfigUtil.getRongAppKey();
	
	@ApiModelProperty(value="secret")
    private String secret =ConfigUtil.getRongAppSecret();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

	
	
	
	
	
}
