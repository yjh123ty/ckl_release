/**
 * Project Name:ckl
 * File Name:CarCarePrice.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import tech.youmu.ckl.constants.Global;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>Title:CarCarePrice</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月10日上午9:42:15</p>
 * <p>Description:汽车维护价目对象</p>
 */
@SuppressWarnings("serial")
public class CarCarePrice  implements Serializable{
    
    private Long id;

    /**
     * 汽车维护的名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 该汽车服务项的内容
     */
    private String content;
    
    /**
     * 该车辆保养项目的图片一张
     */
    private String pic;

    /**
     * 是否删除
     */
    private Boolean isDel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;
    

    public CarCarePrice() {
    }
    
    public CarCarePrice(String name, BigDecimal price, String content) {
        this.name = name;
        this.price = price;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    @JsonFormat(pattern=Global.DATE_TIME_FORMAT, timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    

}
