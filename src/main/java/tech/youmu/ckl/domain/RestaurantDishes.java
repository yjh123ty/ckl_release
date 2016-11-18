/**
 * Project Name:ckl
 * File Name:RestaurantDishes.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;



/**
 * <p>Title:RestaurantDishes</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月18日下午2:07:06</p>
 * <p>Description:饭店菜品</p>
 */
public class RestaurantDishes {
    
    /**
     * 菜品的id
     */
    private Long id;
    
    /**
     * 所属饭店的id
     */
    private Long restaurantId;
    
    /**
     * 饭店的名称
     */
    private String restaurantName;
    
    /**
     * 菜品的名称
     */
    private String name;
    
    /**
     * 菜品的价格
     */
    private Double price;
    
    
    /**
     * 套餐的图片
     */
    private String img;
    
    /**
     * 菜品 在套餐中的总数量
     */
    private Integer num;
    
    /**
     * 菜品在套餐中的总价
     */
    private Double total;

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
    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
}
