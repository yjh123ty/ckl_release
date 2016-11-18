/**
 * Project Name:ckl
 * File Name:ImagesTables.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.constants;

/**
 * <p>Title:ImagesTables</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月14日上午11:58:05</p>
 * <p>Description:多图片表名</p>
 */
public enum ImagesTables {
    /**
     * 服务站图片
     */
    STATION_IMAGES,
    
    /**
     * 酒店图片
     */
    HOTEL_IMAGES,
    
    /**
     * 酒店房间图片
     */
    HOTEL_ROOM_IMAGES,
    
    /**
     * 饭店图片
     */
    RESTAURANT_IMAGES,
    
    /**
     * 合作商家图片
     */
    COOPERATOR_IMAGES;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
