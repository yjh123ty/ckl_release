/**
 * Project Name:ckl
 * File Name:UserBill.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>Title:UserBill</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月6日下午3:54:42</p>
 * <p>Description:用户账单</p>
 */
public class UserBill {
    
    /**
     * 订单编号
     */
    private Long id;
    
    private Long stationId;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 支付人
     */
    private User user;
    
    private Long userId;
    
    /**
     * 服务类型（1-酒店服务，2-饭店服务，3-汽车保养服务, 4-零部件维修服务,5-道路救援;6 退款 ； 7提现 ；8 充值）
     */
    private Integer serviceType;
    
    private String serviceTypeName;
    

    
    /**
     * 账单类型  1充值，2付款，3提现,4-退款
     */
    private Integer type;    
    
    /**
     * 金额
     */
    private Double balance;
    
    /**
     * 账单编号
     */
    private String outOrderNumber ;
    
    
    /**
     * 订单编号
     */
    private String orderNumber;
    /**
     * 订单Id
     */
    private Long orderId;
    
    
    /**
     * 支付方式（1支付宝 ；2 微信支付； 3 车刻丽币 ； 4 人工帮助）
     */
    private Integer payMethod;
    
    /**
     * 状态(1操作中 ；2操作成功；3 操作失败)
     */
    private Integer status;
    
    private Long rechargeComboId;
    
    /**
     * 推广代码
     */
    private String  promotionCode;
    
    /**
     *  备注
     */
    private String intro;
    
    /**
     * 备注图片，转账截图
     */
    private String introImg;
    
    /**
     * 转账时间
     */
    private Date transferTime;
    
    /**
     * 充值时间
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
    
    public UserBill() {
    }
    
    public UserBill(Long stationId,Long userId,Integer serviceType,String serviceTypeName,Double balance,String name,Integer type,Integer payMethod,Long orderId,String orderNumber,String intro,Integer stauts) {
        this.userId = userId;
        this.stationId = stationId;
        this.serviceType = serviceType;
        this.serviceTypeName = serviceTypeName;
        this.balance = balance;
        this.name = name;
        this.type = type;
        this.payMethod = payMethod;
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.intro = intro;
        this.status = stauts;
        this.outOrderNumber  = UUID.randomUUID().toString().replaceAll("-","");
    }

    public UserBill(Long userId, String name, String serviceTypeName,Integer serviceType, Double amount,Integer type, Integer payMethod,Integer status,String intro,Long rechargeComboId,String promotionCode) {
        this.userId = userId;
        this.name = name;
        this.serviceTypeName = serviceTypeName;
        this.serviceType = serviceType;
        this.balance = amount;
        this.type = type;
        this.payMethod = payMethod;
        this.status = status;
        this.intro = intro;
        this.rechargeComboId = rechargeComboId;
        this.promotionCode = promotionCode;
        this.outOrderNumber  = UUID.randomUUID().toString().replaceAll("-","");
    }

    public UserBill(String orderNumber,Long stationId,Long userId,Long orderId,String name, String serviceTypeName, int serviceType, Double amount, int type, int payMethod, int status, String intro) {
        this.orderNumber = orderNumber;
        this.stationId = stationId;
        this.userId = userId;
        this.orderId = orderId;
        this.name = name;
        this.serviceTypeName = serviceTypeName;
        this.serviceType = serviceType;
        this.balance = amount;
        this.type = type;
        this.payMethod = payMethod;
        this.status = status;
        this.intro = intro;
        this.outOrderNumber  = UUID.randomUUID().toString().replaceAll("-","");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getOutOrderNumber() {
        return outOrderNumber;
    }

    public void setOutOrderNumber(String outOrderNumber) {
        this.outOrderNumber = outOrderNumber;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss", timezone = "GMT+8")
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

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
    
    public String getIntroImg() {
        return introImg;
    }

    public void setIntroImg(String introImg) {
        this.introImg = introImg;
    }
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    


    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }
    
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Long getRechargeComboId() {
        return rechargeComboId;
    }

    public void setRechargeComboId(Long rechargeComboId) {
        this.rechargeComboId = rechargeComboId;
    }

    
    
    
    
}
