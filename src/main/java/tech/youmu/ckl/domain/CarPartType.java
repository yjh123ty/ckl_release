/**
 * Project Name:ckl
 * File Name:ProductType.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title:ProductType</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月6日下午2:31:05</p>
 * <p>Description:商品类型</p>
 */
public class CarPartType {
    
    private Long id;
    
    /**
     * 产品名字
     */
    private String name;
    
    /**
     * 描述
     */
    private String intro;
    
    /**
     * 显示时排序的字段
     */
    private Integer order;
    
    /**
     * 录入时间
     */
    private Date createTime;
    
    private Date modifyTime;
    
    private Boolean isDel;
    
    public CarPartType() {
        super();
    }
    

    public CarPartType(Long id, String name, String intro, Integer order) {
        super();
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.order = order;
    }


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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }


    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
    
    
}
