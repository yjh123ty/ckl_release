/**
 * Project Name:ckl
 * File Name:ServiceEvaluation.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title:ServiceEvaluation</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月29日下午5:58:25</p>
 * <p>Description:服务评价</p>
 */
public class ServiceEvaluation {
    private Long id;
    private String content;                 //评价内容
    private Order order;                    //关联订单
    private Double star;                   //平均星数，评价星数（1-5星）
    private Date createTime;                //创建时间
    private Date modifyTime;                //修改时间
    private Boolean isDel;                  //是否删除
    
    private Long orderId;
    
    List<ServiceEvaluationDetail> serviceEvaluationDetails;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Double getStar() {
        return star;
    }
    public void setStar(Double star) {
        this.star = star;
    }
    public Boolean getIsDel() {
        return isDel;
    }
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
    
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
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
    
    
    
    public List<ServiceEvaluationDetail> getServiceEvaluationDetails() {
        return serviceEvaluationDetails;
    }
    public void setServiceEvaluationDetails(List<ServiceEvaluationDetail> serviceEvaluationDetails) {
        this.serviceEvaluationDetails = serviceEvaluationDetails;
    }
    @Override
    public String toString() {
        return "ServiceEvaluation [id=" + id + ", content=" + content + ", order=" + order + ", star=" + star + ", isDel=" + isDel + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
    }
    
    
    
    
    
}
