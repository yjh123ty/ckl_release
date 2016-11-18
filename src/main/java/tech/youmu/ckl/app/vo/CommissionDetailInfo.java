package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "佣金明细信息")
public class CommissionDetailInfo {

    @ApiModelProperty(value="说明")
    private String describe;
    
    @ApiModelProperty(value="佣金来源(套餐，奖励)")
    private String source;
    
    @ApiModelProperty(value="金额")
    private double amount;
    
    @ApiModelProperty(value="状态 1-充值成功,2-发放成功")
    private int status;
    
    @ApiModelProperty(value="日期")
    private String time;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    

    
}
