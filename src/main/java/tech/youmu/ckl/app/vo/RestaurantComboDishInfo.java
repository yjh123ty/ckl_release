/**
 * File Name:Restaurant.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "饭店套餐菜品明细信息")
public class RestaurantComboDishInfo  {
    
    
    @ApiModelProperty(value="菜品名称")
    private String dishName;

    @ApiModelProperty(value="菜品价格")
    private double dishPrice;
    
    @ApiModelProperty(value="数量")
    private int dishCount;
    
    @ApiModelProperty(value="图片")
    private String dishImg;

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public int getDishCount() {
        return dishCount;
    }

    public void setDishCount(int dishCount) {
        this.dishCount = dishCount;
    }

    public String getDishImg() {
        return dishImg;
    }

    public void setDishImg(String dishImg) {
        this.dishImg = dishImg;
    }

  
}
