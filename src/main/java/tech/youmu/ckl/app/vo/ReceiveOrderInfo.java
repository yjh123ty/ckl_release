package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "接收订单信息")
public class ReceiveOrderInfo {

	@ApiModelProperty(value = "是否接收订单")
	private boolean isReceiveOrder;

	@ApiModelProperty(value = "订单id")
	private long orderId;
	
	public ReceiveOrderInfo() {
    }

    public ReceiveOrderInfo(boolean isReceiveOrder) {
        this.isReceiveOrder = isReceiveOrder;
    }

    public boolean getIsReceiveOrder() {
        return isReceiveOrder;
    }

    public void setIsReceiveOrder(boolean isReceiveOrder) {
        this.isReceiveOrder = isReceiveOrder;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
	
	
}