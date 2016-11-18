package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "账单信息")
public class UserBillInfo {
    
    @ApiModelProperty(value="账单id")
    private long userbillId;

    @ApiModelProperty(value="时间")
    private String time;
    
    @ApiModelProperty(value="账单类型 1充值，2付款,3提现,4退款")
    private String type;

	@ApiModelProperty(value="金额")
    private double balance;
	
	@ApiModelProperty(value="备注信息")
    private String intro;
	
	@ApiModelProperty(value="类型（ 1-酒店服务，2-饭店服务，3-汽车保养服务, 4-零部件维修服务,5-道路救援;6 退款 ； 7提现 ；8 充值 ；）")
    private int serviceType;
	


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public double getBalance() {
        return balance;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }




    public String getIntro() {
        return intro;
    }


    public void setIntro(String intro) {
        this.intro = intro;
    }


    public long getUserbillId() {
        return userbillId;
    }


    public void setUserbillId(long userbillId) {
        this.userbillId = userbillId;
    }


    public int getServiceType() {
        return serviceType;
    }


    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }


    
	
	
}