/**
 * Project Name:ckl
 * File Name:District.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

/**
 * <p>Title:District</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月1日下午1:14:38</p>
 * <p>Description:区</p>
 */
public class District {
    /**
     * 市编码
     */
    private Integer code;
    
    /**
     * 市名称
     */
    private String name;
    
    /**
     * 省的编码
     */
    private City city;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
