/**
 * Project Name:ckl
 * File Name:Printer.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title:Printer</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016�?1�?3日上�?1:16:17</p>
 * <p>Description:云打印机</p>
 */
@SuppressWarnings("serial")
public class Printer implements Serializable {
    

    /** 
     * id
     */
    private String id;
    
    /**
     * 打印机使用者编号
     */
    private String partner;

    /** 打印机名称*/
    private String name;

    /** 
     * 打印机状态 (0是离线 ; 1是在线 ; 2是缺纸)
     */
    private Integer status;

    /** 
     * 打印机终端号
     */
    private String machineCode;

    /** 
     * 云连接API密钥 
     */
    private String apiKey;

    /** 
     * 打印机密钥 
     */
    private String printerKey;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 修改时间
     */
    private Date modifyTime;
    
    /**
     * 是否删除
     */
    private Boolean isDel;
    
    /**
     * 打印机对应的服务站
     */
    private Station station;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getPrinterKey() {
        return printerKey;
    }

    public void setPrinterKey(String printerKey) {
        this.printerKey = printerKey;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
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

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "Printer [id=" + id + ", partner=" + partner + ", name=" + name + ", status=" + status + ", machineCode=" + machineCode + ", apiKey=" + apiKey + ", printerKey=" + printerKey + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + ", station=" + station + "]";
    }
    
    
}
