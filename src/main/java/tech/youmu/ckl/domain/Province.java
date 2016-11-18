/**
 * Project Name:ckl
 * File Name:Province.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

/**
 * <p>Title:Province</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月1日下午1:09:19</p>
 * <p>Description:省份</p>
 */
public class Province {
    
    /**
     *  省份编码
     */
    private Integer code;
    
    /**
     * 省份名字
     */
    private String name;
    
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

    @Override
    public String toString() {
        return "Province [code=" + code + ", name=" + name + "]";
    }
}
