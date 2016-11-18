/** 
* 2015-11-21 
* User.java 
* author:Zack Chan
*/ 
package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户权限信息")
public class UserTokenInfo{
	
	private long userId;
	
	@ApiModelProperty(value="token", required = true)
	private String token;
	
	@ApiModelProperty(value="key", required = true)
	private String key;

	public UserTokenInfo(){}
	
	public UserTokenInfo(String token, String key) {
			this.token = token;
			this.key = key;
	}

	public UserTokenInfo(long userId, String token, String key) {
		this.token = token;
		this.key = key;
		this.userId = userId;
	}

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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	
	
	
	
	
	
}
