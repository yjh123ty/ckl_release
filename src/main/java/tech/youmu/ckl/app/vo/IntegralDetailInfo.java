package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "积分明细")
public class IntegralDetailInfo {

	@ApiModelProperty(value="分数")
    private int integral;

	@ApiModelProperty(value="备注")
    private String remark;

	@ApiModelProperty(value="时间")
    private String time;

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}



    
}