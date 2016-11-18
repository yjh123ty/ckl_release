/**
 * Project Name:ckl
 * File Name:RouteGuideSuper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.util.List;

import tech.youmu.ckl.utils.ImageURLUtil;

/**
 * <p>Title:RouteGuideSuper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月22日上午11:46:10</p>
 * <p>Description:路线攻略的一级标题</p>
 */
public class RouteGuideItem {
    
    private Long id;
    
    private Long routeId;
    
    /**
     * 改标题的名称
     */
    private String name;
    
    /**
     * 改标题的图片
     */
    private String img;
    
    /**
     * 排序在攻略中的
     */
    private Integer no;
    
    /**
     * 标题的文本
     */
    private String text;
    
    /**
     * 该标题是否删除
     */
    private Boolean isDel;

    /**
     * 该标题下的所有子标题
     */
    private List<RouteGuideSubItem> subItems;

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

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public List<RouteGuideSubItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<RouteGuideSubItem> subItems) {
        this.subItems = subItems;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }
}
