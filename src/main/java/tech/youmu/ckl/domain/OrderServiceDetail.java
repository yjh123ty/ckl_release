/**
 * Project Name:ckl
 * File Name:OrderServiceDetail.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.models.auth.In;

/**
 * <p>Title:OrderServiceDetail</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月2日上午10:27:56</p>
 * <p>Description:订单明细</p>
 */
public class OrderServiceDetail {
    
    public static final String ASSISTANCE_PIC ="/upload/static/assistance.png";
    
    private Long id;
    
    /**
     * 零部件
     */
    private Long productId;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 服务价格
     */
    private Double price;
    
    
    /**
     * 订单编号
     */
    private String orderNumber;
    
    /**
     * 类型  1-正常，2新增，3-删除
     */
    private Integer type;
    
    /**
     * 关联主订单
     */
    private Long orderId;
    
    /**
     * 商品数量
     */
    private Integer number;
    
    /**
     * 是否删除
     */
    private Boolean isDel;
    
    /**
     * 创建时间
     */
    private String createTime;
    
    /**
     * 修改时间
     */
    private String modifyTime;
    
    private String serviceName;
    
    private Order order;
    
    private BigDecimal servicePrice;
    
    private Boolean isCarPart;
    
    public OrderServiceDetail() {
    }
    
    public OrderServiceDetail(Long orderId,String name, Double price, Integer num,Integer type) {
           this.orderId = orderId;
           this.name = name;
           this.price = price;
           this.number = num;
           this.type = type;
    }


    public OrderServiceDetail(Long orderId, String name,Integer type) {
        this.orderId = orderId;
        this.name = name;
        this.type = type;
    }

    public OrderServiceDetail(Long productId,String name, Double price,Integer type) {
        this.name = name;
        this.price = price;
        this.productId = productId;
        this.type=type;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Boolean getIsCarPart() {
        return isCarPart;
    }

    public void setIsCarPart(Boolean isCarPart) {
        this.isCarPart = isCarPart;
    }

    
    
    
    
}
