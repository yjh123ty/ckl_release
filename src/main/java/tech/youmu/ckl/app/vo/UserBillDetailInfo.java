package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "账单明细")
public class UserBillDetailInfo {
    
    
    @ApiModelProperty(value="服务站id")
    private String stationId;
    
    @ApiModelProperty(value="名称")
    private String name;
    
    @ApiModelProperty(value="金额")
    private double balance;

	@ApiModelProperty(value="类型（ 1-酒店服务，2-饭店服务，3-汽车保养服务, 4-零部件维修服务,5-道路救援;6 退款 ； 7提现 ；8 充值）")
    private int serviceType;
	
	@ApiModelProperty(value="类型名称")
    private String serviceTypeName;
	
	@ApiModelProperty(value="账单类型 1充值，2付款，3提现,4退款")
    private String type;
	
	@ApiModelProperty(value="支付方式（1 支付宝 ；2 微信支付； 3 车刻丽币 ； 4人工帮助）")
    private int payMethod;
	
	@ApiModelProperty(value="订单编号")
    private String orderNumber;
	
	@ApiModelProperty(value="创建时间")
    private String createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }


    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

	

}