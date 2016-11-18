package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "佣金点数月份信息")
public class CommissionYearInfo {

    @ApiModelProperty(value="年份")
    private String year;
    
    @ApiModelProperty(value="总金额")
    private double totalAmount;

    @ApiModelProperty(value="年度佣金明细信息")
    private List<CommissionYearDetailInfo> commissionYearDetailInfos ;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<CommissionYearDetailInfo> getCommissionYearDetailInfos() {
        return commissionYearDetailInfos;
    }

    public void setCommissionYearDetailInfos(List<CommissionYearDetailInfo> commissionYearDetailInfos) {
        this.commissionYearDetailInfos = commissionYearDetailInfos;
    }


    
    
    
}
