/**
 * Project Name:ckl
 * File Name:HotelQuery.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.query;

/**
 * <p>Title:HotelQuery</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月7日下午4:25:42</p>
 * <p>Description:酒店查询对象</p>
 */
public class RestaurantQuery extends BaseQuery{
    
    /**
     * 酒店的星数
     */
    private Integer stars;
    
    /**
     * 类型：1-直营 2-合作
     */
    private Integer type;
    
    

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}
