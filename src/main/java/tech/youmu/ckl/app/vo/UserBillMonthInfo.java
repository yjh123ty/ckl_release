package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "账单月份")
public class UserBillMonthInfo {
	
	@ApiModelProperty(value="月份")
    private String month;

	@ApiModelProperty(value="账单信息")
    private List<UserBillInfo> userBillInfos ;


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<UserBillInfo> getUserBillInfos() {
        return userBillInfos;
    }

    public void setUserBillInfos(List<UserBillInfo> userBillInfos) {
        this.userBillInfos = userBillInfos;
    }

    
    
	
	
}