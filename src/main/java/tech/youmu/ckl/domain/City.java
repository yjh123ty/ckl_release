/**
 * Project Name:ckl
 * File Name:City.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

/**
 * <p>Title:City</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月1日下午1:10:54</p>
 * <p>Description:市</p>
 */
public class City {
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
    private Province province;

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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "City [code=" + code + ", name=" + name + ", province=" + province + "]";
    }
}
