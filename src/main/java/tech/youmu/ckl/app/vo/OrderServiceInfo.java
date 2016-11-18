package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "订单服务信息")
public class OrderServiceInfo {

    @ApiModelProperty(value="服务名称")
    private long orderServiceId;
    
    @ApiModelProperty(value="商品id")
    private long productId;
    
	@ApiModelProperty(value="服务名称")
    private String serviceName;

	@ApiModelProperty(value="服务价格")
    private double servicePrice;

	@ApiModelProperty(value="类型  1-正常，2新增，3-删除")
    private int type;
	
	@ApiModelProperty(value="数量")
    private int number;
	
	@ApiModelProperty(value="是否是汽车零部件")
    private boolean isCarPart;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(double servicePrice) {
		this.servicePrice = servicePrice;
	}


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getOrderServiceId() {
        return orderServiceId;
    }

    public void setOrderServiceId(long orderServiceId) {
        this.orderServiceId = orderServiceId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public boolean getIsCarPart() {
        return isCarPart;
    }

    public void setIsCarPart(boolean isCarPart) {
        this.isCarPart = isCarPart;
    }
	
    
    
    
    
	

    
}