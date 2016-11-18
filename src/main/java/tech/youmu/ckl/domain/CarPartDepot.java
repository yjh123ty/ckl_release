/**
 * Project Name:ckl
 * File Name:CarPartDepot.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.util.Date;

import tech.youmu.ckl.constants.Global;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title:CarPartDepot</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月11日下午5:10:02</p>
 * <p>Description:零部件仓库po</p>
 */
@SuppressWarnings("serial")
public class CarPartDepot implements Serializable {
    
    private Long id;
    
    private String name;
    
    /**
     * 是否是中央仓库
     */
    private Boolean centre;
    
    /**
     * 仓库编号
     */
    private String sn;
    
    /**
     * 仓库地址
     */
    private String address;
    
    /**
     * 服务站id
     */
    private Station station;
    
    /**
     * 库管员
     */
    private Employee keeper;
    
    private Boolean isDel;
    
    private Date createTime;
    
    private Date modifyTime;

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

    public Boolean getCentre() {
        return centre;
    }

    public void setCentre(Boolean centre) {
        this.centre = centre;
    }
    

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Employee getKeeper() {
        return keeper;
    }

    public void setKeeper(Employee keeper) {
        this.keeper = keeper;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    @JsonFormat(pattern=Global.DATE_TIME_FORMAT, timezone="GMT+8")
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
    
}
