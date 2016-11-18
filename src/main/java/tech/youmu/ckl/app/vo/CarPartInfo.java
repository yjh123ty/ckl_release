package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "汽车配件信息")
public class CarPartInfo {
    @ApiModelProperty(value="配件id")
    private long carPartId;
    
	@ApiModelProperty(value="配件名称")
    private String name;

	@ApiModelProperty(value="图片")
    private String pic;
	
	@ApiModelProperty(value="价格")
    private double price;

	@ApiModelProperty(value="条形码")
    private List<String> codes;
	
	@ApiModelProperty(value="数量")
    private int number;
	
	public CarPartInfo() {
    }
	
    public CarPartInfo(Long carPartId, String name, double price, String pic, List<String> codes,int number) {
        this.carPartId =carPartId;
        this.name=name;
        this.price=price;
        this.pic =pic;
        this.codes = codes;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getCarPartId() {
        return carPartId;
    }

    public void setCarPartId(long carPartId) {
        this.carPartId = carPartId;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    
    
    

	
   
}