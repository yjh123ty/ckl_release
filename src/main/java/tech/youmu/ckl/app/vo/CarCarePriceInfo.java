package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "汽车维护价格列表信息")
public class CarCarePriceInfo {
    
    @ApiModelProperty(value="id")
    private long id;
    
	@ApiModelProperty(value="维护名称")
    private String name;
	
	@ApiModelProperty(value="维护价格")
	private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
	
	
   
}