package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "佣金信息")
public class CommissionInfo {

    @ApiModelProperty(value="上个月总佣金")
    private double lastMonthTotalCommission;
    
    @ApiModelProperty(value="上个月总点数")
    private double lastMonthTotalPoint;
    
    @ApiModelProperty(value="年度总佣金")
    private double yearTotalCommission;

    public double getLastMonthTotalCommission() {
        return lastMonthTotalCommission;
    }

    public void setLastMonthTotalCommission(double lastMonthTotalCommission) {
        this.lastMonthTotalCommission = lastMonthTotalCommission;
    }

    public double getLastMonthTotalPoint() {
        return lastMonthTotalPoint;
    }

    public void setLastMonthTotalPoint(double lastMonthTotalPoint) {
        this.lastMonthTotalPoint = lastMonthTotalPoint;
    }

    public double getYearTotalCommission() {
        return yearTotalCommission;
    }

    public void setYearTotalCommission(double yearTotalCommission) {
        this.yearTotalCommission = yearTotalCommission;
    }
    
    
    
    
}
