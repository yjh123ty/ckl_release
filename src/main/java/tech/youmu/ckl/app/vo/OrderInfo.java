package tech.youmu.ckl.app.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "订单信息")
public class OrderInfo {
	
	@ApiModelProperty(value="orderId")
    private long orderId;
	
	@ApiModelProperty(value="productId")
    private long productId;
	
	@ApiModelProperty(value="服务站id")
    private long stationId;
	
	@ApiModelProperty(value="服务站名称")
    private String stationName;
	
	@ApiModelProperty(value="服务站图片")
    private String stationCover;
	
	@ApiModelProperty(value="纬度")
    private double lng;
    
    @ApiModelProperty(value="经度")
    private double lat;
	
	@ApiModelProperty(value="订单类型(1-酒店，2-饭店，3-汽车保养, 4-零部件维修 ，5-道路救援,6-线下订单)")
    private int orderType;
	
	@ApiModelProperty(value="开始时间（酒店入驻时间，饭店开始使用时间）")
    private String startTime;

    @ApiModelProperty(value="截止时间（酒店退房时间、饭店套餐使用截止日期）")
    private String endTime;
    
    @ApiModelProperty(value="套餐名称")
    private String comboName;
    
    @ApiModelProperty(value="房间类型")
    private String roomType;

	@ApiModelProperty(value="实付金额")
    private double totalAmount;

	@ApiModelProperty(value="订单状态(1已退款；2 退款中 ；3已取消；4 待支付；5待服务；6待完成；7待评价；8已完成)")
    private int status;
	
	@ApiModelProperty(value="所有服务是否付款完成(是否有补差价的服务)，0没有，1完成")
    private boolean isPayNew;
	
	@ApiModelProperty(value="待支付金额")
    private double stayPayAmount;
	
	@ApiModelProperty(value="服务人员名称")
    private String serviceUserName;
    
    @ApiModelProperty(value="服务人员级别")
    private String serviceUserJob;
    
    @ApiModelProperty(value="服务人员平均星数")
    private double serviceAvgStar;
    
    @ApiModelProperty(value="服务人员头像")
    private String serviceHeadIcon;
	
	
	
	@ApiModelProperty(value="图片")
	private List<String> imgs;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }


    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    
    
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
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

	public boolean getIsPayNew() {
		return isPayNew;
	}

	public void setIsPayNew(boolean isPayNew) {
		this.isPayNew = isPayNew;
	}

	public double getStayPayAmount() {
		return stayPayAmount;
	}

	public void setStayPayAmount(double stayPayAmount) {
		this.stayPayAmount = stayPayAmount;
	}

    public String getStationCover() {
        return stationCover;
    }

    public void setStationCover(String stationCover) {
        this.stationCover = stationCover;
    }

    public String getServiceUserName() {
        return serviceUserName;
    }

    public void setServiceUserName(String serviceUserName) {
        this.serviceUserName = serviceUserName;
    }

    public String getServiceUserJob() {
        return serviceUserJob;
    }

    public void setServiceUserJob(String serviceUserJob) {
        this.serviceUserJob = serviceUserJob;
    }

    public double getServiceAvgStar() {
        return serviceAvgStar;
    }

    public void setServiceAvgStar(double serviceAvgStar) {
        this.serviceAvgStar = serviceAvgStar;
    }

    public String getServiceHeadIcon() {
        return serviceHeadIcon;
    }

    public void setServiceHeadIcon(String serviceHeadIcon) {
        this.serviceHeadIcon = serviceHeadIcon;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

	



}