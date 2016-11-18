package tech.youmu.ckl.dto;

public class CarPartDto{
    
    private Long carPartId;
    
    private String carPartName;
    
    private Double price;
    
    private String code;

    public Long getCarPartId() {
        return carPartId;
    }

    public void setCarPartId(Long carPartId) {
        this.carPartId = carPartId;
    }

    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCarPartName() {
        return carPartName;
    }

    public void setCarPartName(String carPartName) {
        this.carPartName = carPartName;
    }

    
    
}