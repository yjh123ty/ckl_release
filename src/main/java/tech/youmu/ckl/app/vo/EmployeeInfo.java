/** 
* 2015-11-21 
* User.java 
* author:Zack Chan
*/ 
package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "员工信息")
public class EmployeeInfo {
	
	@ApiModelProperty(value="名称")
	private String name;
	
	@ApiModelProperty(value="头像")
    private String headIcon;
	
	@ApiModelProperty(value="电话")
    private String mobile;
	
	@ApiModelProperty(value="岗位")
    private String jobName;
	
	@ApiModelProperty(value="工号")
    private String employeeNumber;
	
	@ApiModelProperty(value="服务站")
    private String stationName;
	
	@ApiModelProperty(value="1-管理，2-技术，3-事务")
    private int jobType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getJobType() {
        return jobType;
    }

    public void setJobType(int jobType) {
        this.jobType = jobType;
    }
	
    
    
    
    

	
}
