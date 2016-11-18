package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "订单明细信息")
public class OrderDetailInfo {
	
	@ApiModelProperty(value="订单id")
    private long orderId;
	
	@ApiModelProperty(value="发票抬头")
    private String invoiceTitle;
	

	@ApiModelProperty(value="订单编号")
    private String orderNumber;
	
	@ApiModelProperty(value="服务码")
    private String code;
	
	@ApiModelProperty(value="服务二维码")
    private String codeUrl;

	@ApiModelProperty(value="订单类型(1-酒店，2-饭店，3-汽车保养, 4-零部件维修 ，5-道路救援,6-线下订单)")
    private int orderType;
	
	@ApiModelProperty(value="用户车型")
    private String carType;

    @ApiModelProperty(value="用户车牌")
    private String carPlate;

    
    @ApiModelProperty(value="行驶里程单位km")
    private int travelDistance;

    @ApiModelProperty(value="汽车上市年份")
    private String carMarketYear;

    @ApiModelProperty(value="汽车上牌时间")
    private String carPlateDate;
    
    @ApiModelProperty(value="服务站id")
    private String stationId;
    
    @ApiModelProperty(value="纬度")
    private double lng;
    
    @ApiModelProperty(value="经度")
    private double lat;
    
    @ApiModelProperty(value="服务站名称")
    private String stationName;
    
    @ApiModelProperty(value="服务站图片")
    private String stationCover;
    
    @ApiModelProperty(value="服务站电话")
    private String stationMobile;
    
    @ApiModelProperty(value="服务站地址")
    private String stationAddress;


	@ApiModelProperty(value="车主姓名、饭店名称、酒店名称")
    private String serviceName;
	
	@ApiModelProperty(value="汽车位置、饭店地址、酒店地址")
    private String serviceAddress;
	
	@ApiModelProperty(value="车主电话、饭店电话、酒店电话")
    private String serviceMobile;
	
	@ApiModelProperty(value="汽车问题状况")
	private String carCondition;

	@ApiModelProperty(value="下单时间")
    private String createTime;

	@ApiModelProperty(value="所有服务是否付款完成(是否有补差价的服务)，0没有，1完成")
    private boolean isPayNew;

	@ApiModelProperty(value="开始时间（酒店入驻时间，饭店开始使用时间）")
    private String startTime;

	@ApiModelProperty(value="截止时间（酒店退房时间、饭店套餐使用截止日期）")
    private String endTime;
	
	@ApiModelProperty(value="套餐名称")
    private String comboName;
	
	@ApiModelProperty(value="套餐数量")
    private int comboNumber;
	
	@ApiModelProperty(value="套餐金额")
    private double comboAmount;
	
	@ApiModelProperty(value="营业开始时间")
	private String openTime;

	@ApiModelProperty(value="营业结束时间")
    private String closeTime;

	@ApiModelProperty(value="订餐提示")
    private String orderTips;

	@ApiModelProperty(value="用餐规则")
    private String orderRule;
	
	@ApiModelProperty(value="温馨提示")
    private String orderSweet;

	
	@ApiModelProperty(value="入住天数")
    private int days;
	
	@ApiModelProperty(value="房间类型")
    private String roomType;
	
	@ApiModelProperty(value="房间数量")
    private int roomNumber;
	
	@ApiModelProperty(value="房间金额")
    private int roomAmount;
	
	@ApiModelProperty(value="预计到达时间")
    private String planTime;
	
	@ApiModelProperty(value="联系人姓名")
    private String contactName;
	
	@ApiModelProperty(value="联系人电话")
    private String contactMobile;
	
	@ApiModelProperty(value="待支付金额")
    private double stayPayAmount;
	
	@ApiModelProperty(value="退款金额")
    private double refundAmount;
	
	@ApiModelProperty(value="订单状态(1已退款；2 退款中 ；3已取消；4 待支付；5待服务；6待完成；7待评价；8已完成)")
    private int status;

    @ApiModelProperty(value="已支付金额")
    private double paidAmount;

    @ApiModelProperty(value="订单总金额")
    private double totalAmount;
    
    @ApiModelProperty(value="服务备注")
    private String serviceRemark;

	@ApiModelProperty(value="订单服务")
	private List<OrderServiceInfo> orderServiceInfos;
	
	@ApiModelProperty(value="服务人员名称")
    private String serviceUserName;
	
	@ApiModelProperty(value="服务人员级别")
    private String serviceUserJob;
	
	@ApiModelProperty(value="服务人员平均星数")
    private double serviceAvgStar;
	
	@ApiModelProperty(value="服务人员头像")
    private String serviceHeadIcon;
	
	@ApiModelProperty(value="服务评价内容")
    private String servicevaluationContent;
	
	@ApiModelProperty(value="剩余付款时间")
    private String time;
	
	@ApiModelProperty(value="服务评价明细")
    private List<ServiceEvaluationDetailInfo> serviceEvaluationDetailInfos;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }


    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
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

    public int getTravelDistance() {
        return travelDistance;
    }

    public void setTravelDistance(int travelDistance) {
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getServiceMobile() {
        return serviceMobile;
    }

    public void setServiceMobile(String serviceMobile) {
        this.serviceMobile = serviceMobile;
    }

    public String getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(String carCondition) {
        this.carCondition = carCondition;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean getIsPayNew() {
        return isPayNew;
    }

    public void setIsPayNew(boolean isPayNew) {
        this.isPayNew = isPayNew;
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

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
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

    public double getStayPayAmount() {
        return stayPayAmount;
    }

    public void setStayPayAmount(double stayPayAmount) {
        this.stayPayAmount = stayPayAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getServiceRemark() {
        return serviceRemark;
    }

    public void setServiceRemark(String serviceRemark) {
        this.serviceRemark = serviceRemark;
    }

    public List<OrderServiceInfo> getOrderServiceInfos() {
        return orderServiceInfos;
    }

    public void setOrderServiceInfos(List<OrderServiceInfo> orderServiceInfos) {
        this.orderServiceInfos = orderServiceInfos;
    }

    public String getServiceUserName() {
        return serviceUserName;
    }

    public void setServiceUserName(String serviceUserName) {
        this.serviceUserName = serviceUserName;
    }

    public String getServiceHeadIcon() {
        return serviceHeadIcon;
    }

    public void setServiceHeadIcon(String serviceHeadIcon) {
        this.serviceHeadIcon = serviceHeadIcon;
    }

    public String getServicevaluationContent() {
        return servicevaluationContent;
    }

    public void setServicevaluationContent(String servicevaluationContent) {
        this.servicevaluationContent = servicevaluationContent;
    }

    public List<ServiceEvaluationDetailInfo> getServiceEvaluationDetailInfos() {
        return serviceEvaluationDetailInfos;
    }

    public void setServiceEvaluationDetailInfos(List<ServiceEvaluationDetailInfo> serviceEvaluationDetailInfos) {
        this.serviceEvaluationDetailInfos = serviceEvaluationDetailInfos;
    }

    public double getServiceAvgStar() {
        return serviceAvgStar;
    }

    public void setServiceAvgStar(double serviceAvgStar) {
        this.serviceAvgStar = serviceAvgStar;
    }

    public int getComboNumber() {
        return comboNumber;
    }

    public void setComboNumber(int comboNumber) {
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

    public void setPayNew(boolean isPayNew) {
        this.isPayNew = isPayNew;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationMobile() {
        return stationMobile;
    }

    public void setStationMobile(String stationMobile) {
        this.stationMobile = stationMobile;
    }

    public String getOrderSweet() {
        return orderSweet;
    }

    public void setOrderSweet(String orderSweet) {
        this.orderSweet = orderSweet;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getServiceUserJob() {
        return serviceUserJob;
    }

    public void setServiceUserJob(String serviceUserJob) {
        this.serviceUserJob = serviceUserJob;
    }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

    public String getStationCover() {
        return stationCover;
    }

    public void setStationCover(String stationCover) {
        this.stationCover = stationCover;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public double getComboAmount() {
        return comboAmount;
    }

    public void setComboAmount(double comboAmount) {
        this.comboAmount = comboAmount;
    }

    public int getRoomAmount() {
        return roomAmount;
    }

    public void setRoomAmount(int roomAmount) {
        this.roomAmount = roomAmount;
    }

	
    
    
	

	
}