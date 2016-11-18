/**
 * Project Name:ckl
 * File Name:RouteSuit.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.io.Serializable;

/**
 * <p>Title:RouteSuit</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月22日上午10:14:26</p>
 * <p>Description:路线适合人群对象</p>
 */
@SuppressWarnings("serial")
public class RouteSuit implements Serializable{
    
    private Long id;
    
    private String name;
    
    private Integer type;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
