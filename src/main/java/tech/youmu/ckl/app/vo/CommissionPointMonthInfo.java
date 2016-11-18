package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "佣金点数月份信息")
public class CommissionPointMonthInfo {

    @ApiModelProperty(value="月份")
    private String month;

    @ApiModelProperty(value="佣金点数明细信息")
    private List<CommissionPointDetailInfo> commissionPointDetailInfos ;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<CommissionPointDetailInfo> getCommissionPointDetailInfos() {
        return commissionPointDetailInfos;
    }

    public void setCommissionPointDetailInfos(List<CommissionPointDetailInfo> commissionPointDetailInfos) {
        this.commissionPointDetailInfos = commissionPointDetailInfos;
    }
    
    
    
}
