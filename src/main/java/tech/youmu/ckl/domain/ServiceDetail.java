/**
 * Project Name:ckl
 * File Name:ServiceDetail.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

import tech.youmu.ckl.utils.ImageURLUtil;

/**
 * <p>Title:ServiceDetail</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月25日下午2:29:49</p>
 * <p>Description:服务的服务项</p>
 */
public class ServiceDetail {
    
    private Long id;

    /**
     * 服务详情的名字 如豪华单人间 双人间等等
     */
    private String name;

    /**
     * 服务项的价格
     */
    private BigDecimal price;

    /**
     * 服务项的描述
     */
    private String intro;

    /**
     * 图片
     */
    private String img;
    
    private Boolean isDel;
    
    /**
     * 酒店的房间大小
     */
    private String attr1;
    
    /**
     * 酒店床的类型
     */
    private String attr2;

    private Date createTime;

    private Date modifyTime;
    
    private StationService service;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public StationService getService() {
        return service;
    }

    public void setService(StationService service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "ServiceDetail [id=" + id + ", name=" + name + ", price=" + price + ", intro=" + intro + ", img=" + img + ", isDel=" + isDel + ", attr1=" + attr1 + ", attr2=" + attr2 + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", service=" + service + "]";
    }    
}
