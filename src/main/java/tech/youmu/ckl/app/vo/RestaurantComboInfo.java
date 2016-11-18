/**
 * File Name:Restaurant.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "饭店套餐信息")
public class RestaurantComboInfo  {
    
    @ApiModelProperty(value="comboId")
    private long comboId;
    
    @ApiModelProperty(value="套餐名称")
    private String comboName;

    @ApiModelProperty(value="套餐金额")
    private double comboPrice;
    
    @ApiModelProperty(value="销售数量")
    private int comboSellCount;
    
    @ApiModelProperty(value="图片")
    private String comboCoverImg;

    public long getComboId() {
        return comboId;
    }

    public void setComboId(long comboId) {
        this.comboId = comboId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public double getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(double comboPrice) {
        this.comboPrice = comboPrice;
    }


    public int getComboSellCount() {
        return comboSellCount;
    }

    public void setComboSellCount(int comboSellCount) {
        this.comboSellCount = comboSellCount;
    }

    public String getComboCoverImg() {
        return comboCoverImg;
    }

    public void setComboCoverImg(String comboCoverImg) {
        this.comboCoverImg = comboCoverImg;
    }
    
    
    
    
  
}
