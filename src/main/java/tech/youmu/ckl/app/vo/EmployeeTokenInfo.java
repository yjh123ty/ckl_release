/** 
* 2015-11-21 
* User.java 
* author:Zack Chan
*/ 
package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "员工权限信息")
public class EmployeeTokenInfo{
	
    @ApiModelProperty(value="employeeId", required = true)
	private long employeeId;
	
	@ApiModelProperty(value="token", required = true)
	private String token;
	
	@ApiModelProperty(value="key", required = true)
	private String key;

	public EmployeeTokenInfo(){}
	
	public EmployeeTokenInfo(String token, String key) {
			this.token = token;
			this.key = key;
	}

	public EmployeeTokenInfo(long employeeId, String token, String key) {
		this.token = token;
		this.key = key;
		this.employeeId = employeeId;
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

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

	

	
	
	
	
	
	
	
}
