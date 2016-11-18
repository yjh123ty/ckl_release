package tech.youmu.ckl.domain;

public class UserCar {
	

	private Long userId;
	
    private Long id;

    /**
     * 车辆类型
     */
    private String carType;

    /**
     * 车牌号
     */
    private String plateNumber;
    
    private String engineNumber;
    
    private String frameNumber;
	
    private Boolean isDefault;
    
    private Boolean isDel;
    
    public UserCar() {
    }

	public UserCar(Long carId, Boolean isDel) {
			this.id = carId;
			this.isDel = isDel;
	}
	
	public UserCar(Long userId,Long carId,Boolean isDefault) {
		this.userId = userId;
		this.id = carId;
		this.isDefault = isDefault;
	}


    public UserCar(Long userId, String carType, String plateNumber, String engineNumber, String frameNumber) {
        this.userId = userId;
        this.carType =carType;
        this.plateNumber = plateNumber;
        this.engineNumber = engineNumber;
        this.frameNumber = frameNumber;
    }

    public UserCar(Long carId, Long userId, String carType, String plateNumber, String engineNumber, String frameNumber) {
        this.userId = userId;
        this.id= carId;
        this.carType =carType;
        this.plateNumber = plateNumber;
        this.engineNumber = engineNumber;
        this.frameNumber = frameNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    
   
}