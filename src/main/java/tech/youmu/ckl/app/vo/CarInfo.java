package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "车牌信息")
public class CarInfo {
	@ApiModelProperty(value="车牌id,即carId")
    private long carId;

	@ApiModelProperty(value="车型")
    private String carType;

	@ApiModelProperty(value="车牌")
    private String plateNumber;
	
	@ApiModelProperty(value="发动机号")
    private String engineNumber;
	
	@ApiModelProperty(value="车架号")
    private String frameNumber;
	
	@ApiModelProperty(value="是否默认 false不是，true是")
    private boolean isDefault;

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}


	public boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }
	
	

   
}