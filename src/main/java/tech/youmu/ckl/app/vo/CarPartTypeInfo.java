package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "汽车配件类型信息")
public class CarPartTypeInfo {
    
    @ApiModelProperty(value="id")
    private long id;
    
	@ApiModelProperty(value="配件类型")
    private String name;

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

	
	
   
}