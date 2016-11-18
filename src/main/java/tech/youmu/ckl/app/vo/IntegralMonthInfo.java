package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "积分月份")
public class IntegralMonthInfo{
	
	@ApiModelProperty(value="月份")
    private String month;

	@ApiModelProperty(value="积分明细信息")
    private List<IntegralDetailInfo> integralDetailInfos ;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<IntegralDetailInfo> getIntegralDetailInfos() {
        return integralDetailInfos;
    }

    public void setIntegralDetailInfos(List<IntegralDetailInfo> integralDetailInfos) {
        this.integralDetailInfos = integralDetailInfos;
    }

    
	
	


	
}