package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "车牌违章信息")
public class CarIllegallyInfo {
    
    @ApiModelProperty(value="车牌")
    private String carNumber;
    
	@ApiModelProperty(value="罚款数量")
    private int count;
	
	@ApiModelProperty(value="总扣分")
    private int totalDegree;
    
    @ApiModelProperty(value="总罚款金额")
    private int totalMoney;
    
    @ApiModelProperty(value="是否查询成功，true成功")
    private boolean flag;
    
    @ApiModelProperty(value="查询失败信息")
    private String msg;
    
    
    @ApiModelProperty(value="罚款记录")
    private List<CarIllegallyDetailInfo> records;


    public List<CarIllegallyDetailInfo> getRecords() {
        return records;
    }

    public void setRecords(List<CarIllegallyDetailInfo> records) {
        this.records = records;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalDegree() {
        return totalDegree;
    }

    public void setTotalDegree(int totalDegree) {
        this.totalDegree = totalDegree;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    

    
    
    
    
    
    
    

	

   
}