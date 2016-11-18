package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "上个月绩效信息")
public class LastMonthPerformanceInfo {

	@ApiModelProperty(value = "上个月订单数量")
	private int totalOrderCount;

	@ApiModelProperty(value = "上个月金额")
	private int totalAmount;
	
	@ApiModelProperty(value = "平均星数")
    private double avgStar;

    public int getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(int totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getAvgStar() {
        return avgStar;
    }

    public void setAvgStar(double avgStar) {
        this.avgStar = avgStar;
    }
	
	
	
}