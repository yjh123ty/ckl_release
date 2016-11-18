package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

public class OrderRefund {
    private Long id;

    private Integer status;

    private Double amount;

    private String explain;

    private String reason;

    private Long orderId;

    private Boolean isDel;

    private String createTime;

    private String modifyTime;
    
    public OrderRefund() {
    }
    
    public OrderRefund(Integer status,Double amount,String explain,String reason,Long orderId) {
        this.status = status;
        this.amount =amount;
        this.explain = explain;
        this.reason =reason;
        this.orderId =orderId;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    
}