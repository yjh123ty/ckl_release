package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "车牌违章明细信息")
public class CarIllegallyDetailInfo {
    
	@ApiModelProperty(value="违章时间")
    private String time;
	
	@ApiModelProperty(value="扣分")
    private int degree;
	
	@ApiModelProperty(value="违章地址")
    private String address;

	@ApiModelProperty(value="违章原因描述")
    private String reason;
	
	@ApiModelProperty(value="罚款金额")
    private int money;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

	   

   
}