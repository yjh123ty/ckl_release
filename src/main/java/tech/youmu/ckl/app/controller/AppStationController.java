package tech.youmu.ckl.app.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import tech.youmu.ckl.app.vo.CarCarePriceInfo;
import tech.youmu.ckl.app.vo.CarInfo;
import tech.youmu.ckl.app.vo.CarPartInfo;
import tech.youmu.ckl.app.vo.CarPartTypeInfo;
import tech.youmu.ckl.app.vo.HotelDetailInfo;
import tech.youmu.ckl.app.vo.HotelInfo;
import tech.youmu.ckl.app.vo.NavigateRouteInfo;
import tech.youmu.ckl.app.vo.RemoteResult;
import tech.youmu.ckl.app.vo.RestaurantComboDetailInfo;
import tech.youmu.ckl.app.vo.RestaurantDetailInfo;
import tech.youmu.ckl.app.vo.RestaurantInfo;
import tech.youmu.ckl.app.vo.StationInfo;
import tech.youmu.ckl.app.vo.WeixinInfo;
import tech.youmu.ckl.domain.Order;
import tech.youmu.ckl.exception.ParamException;
import tech.youmu.ckl.service.ICarCarePriceService;
import tech.youmu.ckl.service.ICarPartService;
import tech.youmu.ckl.service.ICarService;
import tech.youmu.ckl.service.IHotelService;
import tech.youmu.ckl.service.IOrderService;
import tech.youmu.ckl.service.IRestaurantComboService;
import tech.youmu.ckl.service.IRestaurantService;
import tech.youmu.ckl.service.IStationService;
import tech.youmu.ckl.service.IUserBillService;
import tech.youmu.ckl.utils.ResultUtils;

@RestController
@RequestMapping(value = "/station/remote")
@Api(description = "驿站页面")
public class AppStationController {
    
    private static Logger log=LoggerFactory.getLogger(AppStationController.class);

	@Autowired
	private ICarService carService;
	
	@Autowired
    private ICarPartService carPartService;
	
	@Autowired
    private IOrderService orderService;
	
	@Autowired
    private IRestaurantService restaurantService;
	
	@Autowired
    private IRestaurantComboService restaurantComboService;
	
	@Autowired
    private IHotelService hotelService;
	
	@Autowired
    private IStationService stationService;
	
	@Autowired
    private IUserBillService userBillService;
	
	@Autowired
    private ICarCarePriceService carCarePriceService;
	

	@ApiOperation(value = "获取用户默认车牌信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
	@RequestMapping(value = "getDefaultCarInfo.action", method = RequestMethod.POST)
	public RemoteResult<CarInfo> getDefaultCarInfo(Long userId) {
	    if(userId == null){
	        throw new ParamException("userId不能为空");
	    }
	    CarInfo carInfo = carService.getDefaultCarInfo(userId);
		return ResultUtils.createDefResult(carInfo);
	}

	@ApiOperation(value = "获取汽车配件类型信息")
    @RequestMapping(value = "findCarPartTypeInfo.action", method = RequestMethod.POST)
    public RemoteResult<CarPartTypeInfo> findCarPartTypeInfo() {
        List<CarPartTypeInfo>  carPartTypeInfos = carPartService.findCarPartTypeInfo();
        return ResultUtils.createDefResult(carPartTypeInfos);
    }
	
	@ApiOperation(value = "获取汽车配件信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "carPartTypeId", value = "配件类型id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "key", value = "搜索值", required = false,paramType="query", dataType = "String")
    })
    @RequestMapping(value = "findCarPartInfo.action", method = RequestMethod.POST)
    public RemoteResult<CarPartInfo> findCarPartInfo(Long carPartTypeId,String key) {
	    if(carPartTypeId == null){
            throw new ParamException("carPartTypeId不能为空");
        }
        List<CarPartInfo>  carPartInfos = carPartService.findCarPartInfo(carPartTypeId,key);
        return ResultUtils.createDefResult(carPartInfos);
    }
	
	@ApiOperation(value = "获取汽车维护价格信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "key", value = "搜索值", required = false,paramType="query", dataType = "String")
    })
    @RequestMapping(value = "findCarCarePriceInfo.action", method = RequestMethod.POST)
    public RemoteResult<CarCarePriceInfo> findCarCarePriceInfo(String key) {
        List<CarCarePriceInfo>  carCarePriceInfos = carCarePriceService.findCarCarePriceInfo(key);
        return ResultUtils.createDefResult(carCarePriceInfos);
    }
	
	
	
	
	
	@ApiOperation(value = "饭店下单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "comboId", value = "套餐Id", required = false,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "comboNumber", value = "套餐数量", required = false,paramType="query", dataType = "String",defaultValue="2"),
        @ApiImplicitParam(name = "totalAmount", value = "订单总金额", required = false,paramType="query", dataType = "double",defaultValue="200.00"),
        @ApiImplicitParam(name = "invoiceTitle", value = "发票抬头", required = true,paramType="query", dataType = "String",defaultValue="123456")
    })
    @RequestMapping(value = "createRestaurantOrder.action", method = RequestMethod.POST)
    public RemoteResult<Long> createRestaurantOrder(Long userId,Long comboId,Integer comboNumber,Double totalAmount,String invoiceTitle) {
        if(userId == null){
            throw new ParamException("userId不能为空");
        }
        if(comboId == null){
            throw new ParamException("comboId不能为空");
        }
        if(totalAmount == null){
            throw new ParamException("totalAmount不能为空");
        }
        if(comboNumber == null){
            throw new ParamException("comboNumber不能为空");
        }
        if(invoiceTitle == null){
            throw new ParamException("invoiceTitle不能为空");
        }
        long orderId = orderService.createRestaurantOrder(userId,comboId,comboNumber,totalAmount,invoiceTitle);
        return ResultUtils.createDefResult(orderId);
    }
	
	
	@ApiOperation(value = "酒店下单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "startTime", value = "开始时间（酒店入驻时间，饭店开始使用时间）", required = false,paramType="query", dataType = "String",defaultValue="2016-05-25"),
        @ApiImplicitParam(name = "endTime", value = " 截止时间（酒店退房时间）", required = false,paramType="query", dataType = "String",defaultValue="2016-06-25"),
        @ApiImplicitParam(name = "days", value = "入坐天数", required = false,paramType="query", dataType = "int",defaultValue="2"),
        @ApiImplicitParam(name = "hotelRoomId", value = "房间id", required = false,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "roomNumber", value = "房间数量", required = false,paramType="query", dataType = "int",defaultValue="2"),
        @ApiImplicitParam(name = "planTime", value = "预计到达时间", required = false,paramType="query", dataType = "String",defaultValue="2016-05-25 11:33:22"),
        @ApiImplicitParam(name = "contactMobile", value = "联系人电话", required = false,paramType="query", dataType = "String",defaultValue="12356"),
        @ApiImplicitParam(name = "checkInNames", value = "入住人", required = false,paramType="query", dataType = "array",defaultValue="zs"),
        @ApiImplicitParam(name = "checkInNames", value = "入住人", required = false,paramType="query", dataType = "array",defaultValue="zs1"),
        @ApiImplicitParam(name = "totalAmount", value = "订单总金额", required = false,paramType="query", dataType = "double",defaultValue="200.00"),
        @ApiImplicitParam(name = "invoiceTitle", value = "发票抬头", required = true,paramType="query", dataType = "String",defaultValue="123456")
    })
    @RequestMapping(value = "createHotelOrder.action", method = RequestMethod.POST)
    public RemoteResult<Long> createHotelOrder(Long userId,String startTime,String endTime,Integer days,Long hotelRoomId,Integer roomNumber,String planTime,String contactMobile,String[] checkInNames,Double totalAmount,String invoiceTitle) {
        if(userId == null){
            throw new ParamException("userId不能为空");
        }
        if(startTime == null){
            throw new ParamException("startTime不能为空");
        }
        if(endTime== null){
            throw new ParamException("endTime不能为空");
        }
        if(hotelRoomId== null){
            throw new ParamException("hotelRoomId不能为空");
        }
        if(roomNumber== null){
            throw new ParamException("roomNumber不能为空");
        }
        if(days== null){
            throw new ParamException("days不能为空");
        }
        if(planTime== null){
            throw new ParamException("planTime不能为空");
        }
        if(contactMobile== null){
            throw new ParamException("contactMobile不能为空");
        }
        if(checkInNames== null||checkInNames.length == 0){
            throw new ParamException("checkInName不能为空");
        }
        if(totalAmount== null){
            throw new ParamException("totalAmount不能为空");
        }
        long orderId = orderService.createHotelOrder(userId,startTime,endTime,hotelRoomId,roomNumber,days,contactMobile,planTime,checkInNames,totalAmount,invoiceTitle);
        return ResultUtils.createDefResult(orderId);
    }

	@ApiOperation(value = "获取最近的驿站")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "lng", value = "纬度", required = true,paramType="query", dataType = "double",defaultValue="116.481247"),
        @ApiImplicitParam(name = "lat", value = "经度", required = true,paramType="query", dataType = "double",defaultValue="39.990704")
    })
    @RequestMapping(value = "getRecentStationInfo.action", method = RequestMethod.POST)
    public RemoteResult<StationInfo> getRecentStationInfo(String lng,String lat) {
	    if(lng == null){
            throw new ParamException("startLng不能为空");
        }
        if(lat == null){
            throw new ParamException("startLat不能为空");
        }
        StationInfo  stationInfo = stationService.getRecentStationInfo(lng,lat);
        return ResultUtils.createDefResult(stationInfo);
    }
	
	@ApiOperation(value = "汽车服务下单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "stationId", value = "服务站Id", required = false,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "orderType", value = "订单类型(3-汽车保养, 4-零部件维修 ，5-道路救援,6-洗车服务)", required = true,paramType="query", dataType = "long",defaultValue="4"),
        @ApiImplicitParam(name = "carType", value = "用户车型", required = false,paramType="query", dataType = "String",defaultValue="x6"),
        @ApiImplicitParam(name = "carPlate", value = "用户车牌", required = false,paramType="query", dataType = "String",defaultValue="8888"),
        @ApiImplicitParam(name = "travelDistance", value = "行驶里程单位km", required = false,paramType="query", dataType = "int",defaultValue="79"),
        @ApiImplicitParam(name = "carMarketYear", value = "汽车上市年份", required = false,paramType="query", dataType = "String",defaultValue="2015年"),
        @ApiImplicitParam(name = "carPlateDate", value = " 汽车上牌时间", required = false,paramType="query", dataType = "String",defaultValue="2015-09-02"),
        @ApiImplicitParam(name = "serviceAddress", value = "汽车位置", required = false,paramType="query", dataType = "String",defaultValue="home"),
        @ApiImplicitParam(name = "serviceName", value = "车主姓名", required = false,paramType="query", dataType = "String",defaultValue="hh"),
        @ApiImplicitParam(name = "serviceMobile", value = "车主电话", required = false,paramType="query", dataType = "String",defaultValue="123456"),
        @ApiImplicitParam(name = "serviceRemark", value = "服务备注", required = false,paramType="query", dataType = "String",defaultValue="not"),
        @ApiImplicitParam(name = "carCondition", value = "汽车问题状况、受损情况", required = false,paramType="query", dataType = "String",defaultValue="home"),
        @ApiImplicitParam(name = "totalAmount", value = "订单总金额", required = false,paramType="query", dataType = "double",defaultValue="200.00"),
        @ApiImplicitParam(name = "invoiceTitle", value = "发票抬头", required = true,paramType="query", dataType = "String",defaultValue="123456"),
        @ApiImplicitParam(name = "orderServiceDetails[0].productId", value = "商品零部件id,carPartId", required = false,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "orderServiceDetails[0].name", value = "名称", required = false,paramType="query", dataType = "String",defaultValue="换后市静"),
        @ApiImplicitParam(name = "orderServiceDetails[0].price", value = "价格", required = false,paramType="query", dataType = "double",defaultValue="300.00"),
        @ApiImplicitParam(name = "orderServiceDetails[0].number", value = "数量", required = false,paramType="query", dataType = "int",defaultValue="1")
    })
    @RequestMapping(value = "createCarOrder.action", method = RequestMethod.POST)
    public RemoteResult<Long> createCarOrder(Order order) {
        if(order == null){
            throw new ParamException("order不能为空");
        }
        if(order.getUserId() == null){
            throw new ParamException("userId不能为空");
        }
        if(order.getOrderType() == null){
            throw new ParamException("orderType不能为空");
        }
        if(order.getTotalAmount()== null){
            throw new ParamException("totalAmount不能为空");
        }
        long orderId = orderService.createCarOrder(order);
        return ResultUtils.createDefResult(orderId);
    }

	
	@ApiOperation(value = "获取饭店信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "lng", value = "纬度", required = true,paramType="query", dataType = "double",defaultValue="116.481247"),
        @ApiImplicitParam(name = "lat", value = "经度", required = true,paramType="query", dataType = "double",defaultValue="39.990704"),
        @ApiImplicitParam(name = "sort", value = "1-距离最近,2-最顺路,3-价格最低，4-价格最高", required = true,paramType="query", dataType = "int",defaultValue="1"),
        @ApiImplicitParam(name = "stars", value = "星级，不传-不限，1-1星级，2-2星级，3-3星级，4-4星级，5-5星级", required = false,paramType="query", dataType = "int"),
        @ApiImplicitParam(name = "startPrice", value = "，不传-不限，开始区间", required = false,paramType="query", dataType = "int"),
        @ApiImplicitParam(name = "endPrice", value = "不传-不限，结束区间", required = false,paramType="query", dataType = "int"),
        @ApiImplicitParam(name = "search", value = "不传-不限，结束区间", required = false,paramType="query", dataType = "String"),
        @ApiImplicitParam(name = "stationId", value = "服务站id", required = false,paramType="query", dataType = "long")
    })
    @RequestMapping(value = "findRestaurantInfo.action", method = RequestMethod.POST)
    public RemoteResult<RestaurantInfo> findRestaurantInfo(String lng,String lat,@RequestParam(defaultValue="1")Integer sort,Integer stars,Integer startPrice,Integer endPrice,String search,Long stationId) {
	    if(lng == null){
            throw new ParamException("startTime不能为空");
        }
        if(lat == null){
            throw new ParamException("lat不能为空");
        }
	    List<RestaurantInfo>  restaurantInfos = restaurantService.findRestaurantInfo(lng,lat,sort,stars,startPrice,endPrice,search,stationId);
        return ResultUtils.createDefResult(restaurantInfos);
    }
	
	
	@ApiOperation(value = "获取酒店信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "startTime", value = "入住时间", required = true,paramType="query", dataType = "String",defaultValue="2016-08-09"),
        @ApiImplicitParam(name = "lng", value = "纬度", required = true,paramType="query", dataType = "double",defaultValue="116.481247"),
        @ApiImplicitParam(name = "lat", value = "经度", required = true,paramType="query", dataType = "double",defaultValue="39.990704"),
        @ApiImplicitParam(name = "sort", value = "1-距离最近,2-最顺路,3-价格最低，4-价格最高", required = true,paramType="query", dataType = "int",defaultValue="1"),
        @ApiImplicitParam(name = "stars", value = "星级，不传-不限，1-1星级，2-2星级，3-3星级，4-4星级，5-5星级", required = false,paramType="query", dataType = "int"),
        @ApiImplicitParam(name = "startPrice", value = "，不传-不限，开始区间", required = false,paramType="query", dataType = "int"),
        @ApiImplicitParam(name = "endPrice", value = "不传-不限，结束区间", required = false,paramType="query", dataType = "int"),
        @ApiImplicitParam(name = "search", value = "不传-不限，结束区间", required = false,paramType="query", dataType = "String"),
        @ApiImplicitParam(name = "stationId", value = "服务站id", required = false,paramType="query", dataType = "long")
    })
    @RequestMapping(value = "findHotelInfo.action", method = RequestMethod.POST)
    public RemoteResult<HotelInfo> findHotelInfo(String startTime,String lng,String lat,@RequestParam(defaultValue="1")Integer sort,Integer stars,Integer startPrice,Integer endPrice,String search,Long stationId) {
	    if(startTime == null){
            throw new ParamException("startTime不能为空");
        }
	    if(lng == null){
            throw new ParamException("startTime不能为空");
        }
	    if(lat == null){
            throw new ParamException("lat不能为空");
        }
	    List<HotelInfo>  hotelInfos = hotelService.findHotelInfo(startTime,lng,lat,sort,stars,startPrice,endPrice,search,stationId);
        return ResultUtils.createDefResult(hotelInfos);
    }
	
	
	@ApiOperation(value = "获取饭店明细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "restaurantId", value = "饭店id", required = true,paramType="query", dataType = "long",defaultValue="64"),
    })
    @RequestMapping(value = "getRestaurantDetailInfo.action", method = RequestMethod.POST)
    public RemoteResult<RestaurantDetailInfo> getRestaurantDetailInfo(Long restaurantId) {
	    if(restaurantId == null){
            throw new ParamException("restaurantId不能为空");
        }
	    RestaurantDetailInfo  restaurantDetailInfo = restaurantService.getRestaurantDetailInfo(restaurantId);
        return ResultUtils.createDefResult(restaurantDetailInfo);
    }
    
    
    @ApiOperation(value = "获取酒店明细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "startTime", value = "入住时间", required = true,paramType="query", dataType = "String",defaultValue="2016-09-12"),
        @ApiImplicitParam(name = "hotelId", value = "酒店id", required = true,paramType="query", dataType = "long",defaultValue="1"),
    })
    @RequestMapping(value = "getHotelDetailInfo.action", method = RequestMethod.POST)
    public RemoteResult<HotelDetailInfo> getHotelDetailInfo(Long hotelId,String startTime) {
        if(startTime == null){
            throw new ParamException("startTime不能为空");
        }
        if(hotelId == null){
            throw new ParamException("hotelId不能为空");
        }
        HotelDetailInfo  hotelInfo = hotelService.getHotelDetailInfo(hotelId,startTime);
        return ResultUtils.createDefResult(hotelInfo);
    }
    
    
    @ApiOperation(value = "获取饭店套餐明细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "comboId", value = "套餐id", required = true,paramType="query", dataType = "long",defaultValue="64"),
    })
    @RequestMapping(value = "getRestaurantComboDetailInfo.action", method = RequestMethod.POST)
    public RemoteResult<RestaurantComboDetailInfo> getRestaurantComboDetailInfo(Long comboId) {
        if(comboId == null){
            throw new ParamException("comboId不能为空");
        }
        RestaurantComboDetailInfo  restaurantComboDetailInfo = restaurantComboService.getRestaurantComboDetailInfo(comboId);
        return ResultUtils.createDefResult(restaurantComboDetailInfo);
    }
    
    
    @ApiOperation(value = "获取最近和顺路的服务站信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户Id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "lng", value = "纬度", required = true,paramType="query", dataType = "double",defaultValue="116.481247"),
        @ApiImplicitParam(name = "lat", value = "经度", required = true,paramType="query", dataType = "double",defaultValue="39.990704")
    })
    @RequestMapping(value = "findNavigateRouteInfo.action", method = RequestMethod.POST)
    public RemoteResult<NavigateRouteInfo> findNavigateRouteInfo(Long userId,String lng,String lat) {
        if(userId == null){
            throw new ParamException("userId不能为空");
        }
        if(lng == null){
            throw new ParamException("lng不能为空");
        }
        if(lat == null){
            throw new ParamException("lat不能为空");
        }
        List<NavigateRouteInfo>  navigateRouteInfos = stationService.findNavigateRouteInfo(userId,lng,lat);
        return ResultUtils.createDefResult(navigateRouteInfos);
    }
    
    
    @ApiOperation(value = "支付宝支付")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orderId", value = "订单Id", required = false,paramType="query", dataType = "long",defaultValue="70"),
        @ApiImplicitParam(name = "amount", value = "支付金额", required = true,paramType="query", dataType = "double",defaultValue="0.01")
    })
    @RequestMapping(value = "payAlipay.action", method = RequestMethod.POST)
    public RemoteResult<String> payAlipay(Long orderId,Double amount) {
        if(amount == null){
            throw new ParamException("amount不能为空");
        }
        if(orderId == null){
            throw new ParamException("orderId不能为空");
        }
        
        String alipayInfo = userBillService.payAlipay(orderId,amount);
        return ResultUtils.createDefResult(alipayInfo);
    }
    
    
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月27日下午12:52:52;</p>
     *	<p>Description: 支付宝回调;</p>
     *  @param userId
     *  @param amount
     *  @return
     */
    @ApiOperation(value = "支付宝支付回调")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "trade_status", value = "状态", required = false,paramType="query", dataType = "String",defaultValue="TRADE_SUCCESS"),
        @ApiImplicitParam(name = "out_trade_no", value = "支付订单号", required = true,paramType="query", dataType = "String")
    })
    @RequestMapping(value = "notifyAlipay", method = RequestMethod.POST)
    public String notifyAlipay(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            log.info(">>>>>参数" + name + ":" + valueStr);
            params.put(name, valueStr);
        }
        if(userBillService.notifyAlipay(params)){
            return "success";
        }
        return "fail";
    }
    
    
    @ApiOperation(value = "微信支付")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orderId", value = "订单Id", required = true,paramType="query", dataType = "long",defaultValue="70"),
        @ApiImplicitParam(name = "amount", value = "支付金额", required = true,paramType="query", dataType = "double",defaultValue="0.01")
    })
    @RequestMapping(value = "payWeixin.action", method = RequestMethod.POST)
    public RemoteResult<WeixinInfo> payWeixin(HttpServletRequest request,Long orderId,Double amount) {
        if(amount == null){
            throw new ParamException("amount不能为空");
        }
        if(orderId == null){
            throw new ParamException("orderId不能为空");
        }
        
        WeixinInfo weixinInfo = userBillService.payWeixin(orderId,amount,request.getRemoteAddr());
        return ResultUtils.createDefResult(weixinInfo);
    }
    
    @ApiOperation(value = "微信支付回调")
    @RequestMapping(value = "notifyWeixin", method = RequestMethod.POST)
    public String notifyWeixin(HttpServletRequest request,@RequestBody String body) {
        return userBillService.notifyWeixin(body);
       
    }
    
   
    
    @ApiOperation(value = "平台币支付")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orderId", value = "订单Id", required = true,paramType="query", dataType = "long",defaultValue="83"),
        @ApiImplicitParam(name = "amount", value = "支付金额", required = true,paramType="query", dataType = "double",defaultValue="0.01"),
        @ApiImplicitParam(name = "userId", value = "用户Id", required = true,paramType="query", dataType = "double",defaultValue="79"),
        @ApiImplicitParam(name = "payPassword", value = "支付密码", required = true,paramType="query", dataType = "String",defaultValue="123456")
    })
    @RequestMapping(value = "payPlatform.action", method = RequestMethod.POST)
    public RemoteResult<WeixinInfo> payPlatform(Long orderId,Double amount,Long userId,String payPassword) {
        if(amount == null){
            throw new ParamException("amount不能为空");
        }
        if(orderId == null){
            throw new ParamException("orderId不能为空");
        }
        if(userId == null){
            throw new ParamException("userId不能为空");
        }
        
        if(payPassword == null){
            throw new ParamException("支付密码不能为空");
        }
        
        userBillService.payPlatform(orderId,amount,userId,payPassword);
        return ResultUtils.createNullResult();
    }
    
    
}