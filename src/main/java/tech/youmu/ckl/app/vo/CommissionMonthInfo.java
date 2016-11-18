package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "佣金点数月份信息")
public class CommissionMonthInfo {

    @ApiModelProperty(value="月份")
    private String month;

    @ApiModelProperty(value="佣金明细信息")
    private List<CommissionDetailInfo> commissionDetailInfos ;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<CommissionDetailInfo> getCommissionDetailInfos() {
        return commissionDetailInfos;
    }

    public void setCommissionDetailInfos(List<CommissionDetailInfo> commissionDetailInfos) {
        this.commissionDetailInfos = commissionDetailInfos;
    }

    
    
    
}
