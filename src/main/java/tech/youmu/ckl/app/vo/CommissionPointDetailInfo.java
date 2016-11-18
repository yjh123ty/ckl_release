package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "佣金点数明细信息")
public class CommissionPointDetailInfo {

    @ApiModelProperty(value="充值人电话")
    private String rechargeUserMobile;
    
    @ApiModelProperty(value="充值套餐名称")
    private String rechargeComboName;
    
    @ApiModelProperty(value="点数")
    private double pointCount;
    
    @ApiModelProperty(value="状态 1-充值成功")
    private int status;
    
    @ApiModelProperty(value="日期")
    private String time;

    public String getRechargeUserMobile() {
        return rechargeUserMobile;
    }

    public void setRechargeUserMobile(String rechargeUserMobile) {
        this.rechargeUserMobile = rechargeUserMobile;
    }

    public String getRechargeComboName() {
        return rechargeComboName;
    }

    public void setRechargeComboName(String rechargeComboName) {
        this.rechargeComboName = rechargeComboName;
    }

    public double getPointCount() {
        return pointCount;
    }

    public void setPointCount(double pointCount) {
        this.pointCount = pointCount;
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
