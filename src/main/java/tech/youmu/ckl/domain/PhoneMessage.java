/**
 * Project Name:ckl
 * File Name:PhoneMessage.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title:PhoneMessage</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016�?0�?0日上�?1:06:15</p>
 * <p>Description:管理人员接收短信预留的手机号</p>
 */
public class PhoneMessage {

    private Long id;
    
    /**
     * 管理人员提供的接收短信的手机�?
     */
    private String phone;
    
    /**
     * 备注信息
     */
    private String intro;
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @JsonFormat(pattern = "yyyy.MM.dd", timezone = "GMT+8")
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
        return "PhoneMessage [id=" + id + ", phone=" + phone + ", intro=" + intro + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
    }
    
    
}
