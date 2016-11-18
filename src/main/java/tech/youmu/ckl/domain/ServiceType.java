package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ServiceType {
    
    private Long id;

    private String name;

    private Boolean isDel;
    
    /**
     * 用作判断的服务类型
     */
    private Integer type;
    
    /**
     * 道路救援起步价
     */
    private BigDecimal startPrice;
    
    /**
     * 道路救援公里价
     */
    private BigDecimal milPrice;

    private Date createTime;

    private Date modifyTime;
    
    public Long getId() {
        return id;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getMilPrice() {
        return milPrice;
    }

    public void setMilPrice(BigDecimal milPrice) {
        this.milPrice = milPrice;
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
    
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ServiceType [id=" + id + ", name=" + name + ", isDel=" + isDel + ", type=" + type + ", startPrice=" + startPrice + ", milPrice=" + milPrice + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
    }
    
    
}