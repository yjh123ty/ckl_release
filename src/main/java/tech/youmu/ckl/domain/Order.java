package tech.youmu.ckl.domain;

import java.util.List;

public class Order {
    /**
     * 
     */
    private Long id;
    
    /**
     * 发票
     */
    private String invoiceTitle;
    
    /**
     * 房间/套餐id
     */
    private Long productId;

    /**
     * 订单的唯一编号
     */
    private String orderNumber;

    /**
     * 服务人员id
     */
    private Long empId;

    private Long stationId;

    private Long userId;

    /**
     * 服务二维码码
     */
    private String code;

    /**
     * 订单类型(1-酒店，2-饭店，3-汽车保养, 4-零部件维修 ，5-道路救援,6-线下订单)
     */
    private Integer orderType;

    /**
     * 用户车型
     */
    private String carType;

    /**
     * 用户车牌
     */
    private String carPlate;

    /**
     * 行驶里程单位km
     */
    private Integer travelDistance;

    /**
     * 汽车上市年份
     */
    private String carMarketYear;

    /**
     * 汽车上牌时间
     */
    private String carPlateDate;

    /**
     * 汽车位置、饭店地址、酒店地址
     */
    private String serviceAddress;

    /**
     * 车主姓名、饭店名称、酒店名称
     */
    private String serviceName;

    /**
     * 车主电话、饭店电话、酒店电话
     */
    private String serviceMobile;

    /**
     * 服务备注
     */
    private String serviceRemark;

    /**
     * 汽车问题状况
     */
    private String carCondition;

    /**
     * 开始时间（酒店入驻时间，饭店开始使用时间）
     */
    private String startTime;

    /**
     * 截止时间（酒店退房时间）
     */
    private String endTime;
    
    /**
     * 套餐id
     */
    private String comboId;

    /**
     * 套餐名称
     */
    private String comboName;
    
    /**
     * 数量
     */
    private Integer comboNumber;

    /**
     * 套餐金额
     */
    private Double comboAmount;
    
    private String openTime;

    /**
     * 营业结束时间
     */
    private String closeTime;

    /**
     * 订餐提示
     */
    private String orderTips;

    /**
     * 用餐规则
     */
    private String orderRule;
    
    /**
     * 温馨提示
     */
    private String orderSweet;

    /**
     * 入坐天数
     */
    private Integer days;

    /**
     * 房间类型
     */
    private String roomType;

    /**
     * 房间数量
     */
    private Integer roomNumber;
    
    /**
     * 房间金额
     */
    private Double roomAmount;

    /**
     * 预计到达时间
     */
    private String planTime;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactMobile;

    /**
     * 订单状态(1已退款；2 退款中 ；3已取消；4 待支付；5待服务；6待完成；7待评价；8已完成 )
     */
    private Integer status;

    /**
     * 支付方式（1支付宝 ；2 微信支付； 3 车刻丽币 ；）
     */
    private Integer payMethod;

    /**
     * 有服务是否付款完成(补差价的服务)，0没有，1完成
     */
    private Boolean isPayNew = false;
    
    /**
     * 订单总金额
     */

    private Double totalAmount;

    /**
     * 已支付金额
     */
    private Double paidAmount;

    /**
     * 待支付金额
     */
    private Double stayPayAmount;
    
    /**
     * 退款金额
     */
    private Double refundAmount;
    
    private Boolean isRefund =false;

    private Boolean isDel;

    private String createTime;

    private String modifyTime;
    
    private List<OrderServiceDetail> orderServiceDetails;
    
    
    private User user;
    
    private ServiceType serviceType;
    
    private Integer serviceTypeType;
    
    private String serviceTypeName;
    
    private Station station;
    
    private String stationName;
    
    private Employee employee;
    
    

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Order() {
    }
    
    public Order(String invoiceTitle,Long productId,Long stationId,String orderNumber, Integer orderType, Long userId,String serviceName, String serviceAddress, String serviceMobile, String comboName,Integer comboNumber,Double comboAmount, String startTime, String endTime,String openTime, String closeTime, String orderTips, String orderRule,String orderSweet,Boolean isPayNew,Double totalAmount,Integer status) {
        this.invoiceTitle = invoiceTitle;
        this.productId = productId;
        this.stationId = stationId;
        this.orderNumber = orderNumber;
        this.orderType =orderType;
        this.userId = userId;
        this.serviceName = serviceName;
        this.serviceAddress = serviceAddress;
        this.serviceMobile = serviceMobile;
        this.comboName = comboName;
        this.comboNumber = comboNumber;
        this.comboAmount = comboAmount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isPayNew = isPayNew;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.orderTips = orderTips;
        this.orderRule = orderRule;
        this.orderSweet = orderSweet;
        this.totalAmount = totalAmount;
        this.status = status;
    }






    public Order(String invoiceTitle,Long productId,Long stationId, String orderNumber, Integer orderType, Long userId, String serviceName, String serviceAddress, String serviceMobile,  String startTime, String endTime, Integer days, Integer roomNumber, String roomType,Double roomAmount, String contactMobile, String planTime, String contactName, Double totalAmount,Integer status) {
        this.productId = productId;
        this.stationId = stationId;
        this.orderNumber = orderNumber;
        this.orderType = orderType;
        this.userId = userId;
        this.serviceName = serviceName;
        this.serviceMobile = serviceMobile;
        this.serviceAddress = serviceAddress;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days =days;
        this.roomAmount = roomAmount;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.contactName = contactName;
        this.contactMobile = contactMobile;
        this.planTime =planTime;
        this.totalAmount = totalAmount;
        this.status = status;
        this.invoiceTitle = invoiceTitle;
    }

    public Order(Long employeeId, String orderNumber, String serviceName,String serviceMobile, Double totalAmount, Integer status) {
        this.empId = employeeId;
        this.orderNumber = orderNumber;
        this.serviceName = serviceName;
        this.serviceMobile = serviceMobile;
        this.totalAmount = totalAmount;
        this.status = status;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Integer getTravelDistance() {
        return travelDistance;
    }

    public void setTravelDistance(Integer travelDistance) {
        this.travelDistance = travelDistance;
    }

    public String getCarMarketYear() {
        return carMarketYear;
    }

    public void setCarMarketYear(String carMarketYear) {
        this.carMarketYear = carMarketYear;
    }

    public String getCarPlateDate() {
        return carPlateDate;
    }

    public void setCarPlateDate(String carPlateDate) {
        this.carPlateDate = carPlateDate;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceMobile() {
        return serviceMobile;
    }

    public void setServiceMobile(String serviceMobile) {
        this.serviceMobile = serviceMobile;
    }

    public String getServiceRemark() {
        return serviceRemark;
    }

    public void setServiceRemark(String serviceRemark) {
        this.serviceRemark = serviceRemark;
    }

    public String getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(String carCondition) {
        this.carCondition = carCondition;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Boolean getIsPayNew() {
        return isPayNew;
    }

    public void setIsPayNew(Boolean isPayNew) {
        this.isPayNew = isPayNew;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
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

    public List<OrderServiceDetail> getOrderServiceDetails() {
        return orderServiceDetails;
    }

    public void setOrderServiceDetails(List<OrderServiceDetail> orderServiceDetails) {
        this.orderServiceDetails = orderServiceDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Double getStayPayAmount() {
        return stayPayAmount;
    }

    public void setStayPayAmount(Double stayPayAmount) {
        this.stayPayAmount = stayPayAmount;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", orderNumber=" + orderNumber + ", empId=" + empId + ", stationId=" + stationId + ", userId=" + userId + ", code=" + code + ", orderType=" + orderType + ", carType=" + carType + ", carPlate=" + carPlate + ", travelDistance=" + travelDistance + ", carMarketYear=" + carMarketYear + ", carPlateDate=" + carPlateDate + ", serviceAddress=" + serviceAddress + ", serviceName=" + serviceName + ", serviceMobile=" + serviceMobile + ", serviceRemark=" + serviceRemark + ", carCondition=" + carCondition + ", startTime=" + startTime + ", endTime=" + endTime + ", comboName=" + comboName + ", days=" + days + ", roomType=" + roomType + ", roomNumber=" + roomNumber + ", planTime=" + planTime + ", contactName=" + contactName + ", contactMobile=" + contactMobile + ", status=" + status + ", payMethod=" + payMethod + ", isPayNew=" + isPayNew + ", totalAmount=" + totalAmount + ", paidAmount=" + paidAmount + ", stayPayAmount=" + stayPayAmount + ", isDel=" + isDel
                + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", orderServiceDetails=" + orderServiceDetails + ", user=" + user + ", serviceType=" + serviceType + ", station=" + station + ", employee=" + employee + "]";
    }

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }




    public Integer getComboNumber() {
        return comboNumber;
    }

    public void setComboNumber(Integer comboNumber) {
        this.comboNumber = comboNumber;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getOrderTips() {
        return orderTips;
    }

    public void setOrderTips(String orderTips) {
        this.orderTips = orderTips;
    }

    public String getOrderRule() {
        return orderRule;
    }

    public void setOrderRule(String orderRule) {
        this.orderRule = orderRule;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getOrderSweet() {
        return orderSweet;
    }

    public void setOrderSweet(String orderSweet) {
        this.orderSweet = orderSweet;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public Integer getServiceTypeType() {
        return serviceTypeType;
    }

    public void setServiceTypeType(Integer serviceTypeType) {
        this.serviceTypeType = serviceTypeType;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Boolean getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(Boolean isRefund) {
        this.isRefund = isRefund;
    }

    public Double getComboAmount() {
        return comboAmount;
    }

    public void setComboAmount(Double comboAmount) {
        this.comboAmount = comboAmount;
    }

    public Double getRoomAmount() {
        return roomAmount;
    }

    public void setRoomAmount(Double roomAmount) {
        this.roomAmount = roomAmount;
    }
    
    
    
    
    
    

}