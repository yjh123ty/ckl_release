package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "年度佣金明细信息")
public class CommissionYearDetailInfo {

    
    @ApiModelProperty(value="金额")
    private double amount;
    
    @ApiModelProperty(value="日期")
    private String time;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    
    
}
