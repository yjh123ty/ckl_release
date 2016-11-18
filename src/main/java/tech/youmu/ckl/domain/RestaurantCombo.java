/**
 * Project Name:ckl
 * File Name:ResturantCombo.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;





import tech.youmu.ckl.utils.ImageURLUtil;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title:ResturantCombo</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月7日上午11:42:06</p>
 * <p>Description:饭店套餐对象</p>
 */
@SuppressWarnings("serial")
@JsonAutoDetect(creatorVisibility=Visibility.ANY)
public class RestaurantCombo implements Serializable {
    
    private Long id;
    
    private String name;
    
    /**
     * 套餐对应的饭店的Id
     */
    private Long restaurantId;
    
    private Restaurant restaurant;
    
    /**
     * 套餐名字对应的数据字典的id
     * 用于回显
     */
    private Long typeId;

    /**
     * 套餐图片 单图片
     */
    private String img;

    /**
     * 套餐价格
     */
    private Double price;

    /**
     * 套餐的有效期 开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    /**
     * 套餐的有效期 结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    /**
     * 套餐的详细描述
     */
    private String detail;

    private Boolean isDel;

    private Date createTime;

    private Date modifyTime;
    
    /**
     * 套餐下面的菜品 
     */
    private List<RestaurantDishes> dishes = new ArrayList<RestaurantDishes>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<RestaurantDishes> getDishes() {
        return dishes;
    }

    public void setDishes(List<RestaurantDishes> dishes) {
        this.dishes = dishes;
    }
    
    
    
}
