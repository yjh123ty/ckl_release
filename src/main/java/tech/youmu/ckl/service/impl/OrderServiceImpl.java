/**
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:58:35
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.status.Status;
import net.sf.json.JSONObject;
import tech.youmu.ckl.app.vo.OrderDetailInfo;
import tech.youmu.ckl.app.vo.OrderInfo;
import tech.youmu.ckl.constants.Global;
import tech.youmu.ckl.constants.Global.ServiceType;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.AddOrderDetailList;
import tech.youmu.ckl.domain.AdminTask;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.Hotel;
import tech.youmu.ckl.domain.HotelRoom;
import tech.youmu.ckl.domain.Order;
import tech.youmu.ckl.domain.OrderEvaluation;
import tech.youmu.ckl.domain.OrderImage;
import tech.youmu.ckl.domain.OrderInfoHistory;
import tech.youmu.ckl.domain.OrderRefund;
import tech.youmu.ckl.domain.OrderServiceDetail;
import tech.youmu.ckl.domain.Printer;
import tech.youmu.ckl.domain.Restaurant;
import tech.youmu.ckl.domain.RestaurantCombo;
import tech.youmu.ckl.domain.RestaurantDishes;
import tech.youmu.ckl.domain.ServiceEvaluation;
import tech.youmu.ckl.domain.ServiceEvaluationDetail;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.domain.UserBill;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.AdminTaskMapper;
import tech.youmu.ckl.mapper.CarCarePriceMapper;
import tech.youmu.ckl.mapper.CarPartMapper;
import tech.youmu.ckl.mapper.EmployeeMapper;
import tech.youmu.ckl.mapper.HotelRoomMapper;
import tech.youmu.ckl.mapper.OrderMapper;
import tech.youmu.ckl.mapper.OrderRefundMapper;
import tech.youmu.ckl.mapper.OrderServiceDetailMapper;
import tech.youmu.ckl.mapper.PrinterMapper;
import tech.youmu.ckl.mapper.RestaurantComboMapper;
import tech.youmu.ckl.mapper.RestaurantDishesMapper;
import tech.youmu.ckl.mapper.ServiceEvaluationDetailMapper;
import tech.youmu.ckl.mapper.ServiceEvaluationMapper;
import tech.youmu.ckl.mapper.UserBillMapper;
import tech.youmu.ckl.mapper.UserMapper;
import tech.youmu.ckl.query.OrderInfoHistoryQuery;
import tech.youmu.ckl.query.OrderQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IAdminTaskService;
import tech.youmu.ckl.service.IOrderService;
import tech.youmu.ckl.service.component.BadgeComponent;
import tech.youmu.ckl.utils.AlipayUtil;
import tech.youmu.ckl.utils.ConfigUtil;
import tech.youmu.ckl.utils.DateUtil;
import tech.youmu.ckl.utils.ImageURLUtil;
import tech.youmu.ckl.utils.OrderCodeUtil;
import tech.youmu.ckl.utils.OrderUtil;
import tech.youmu.ckl.utils.PrintMessageUtil;
import tech.youmu.ckl.utils.QRCodeUtil;
import tech.youmu.ckl.utils.SendSmsUtil;
import tech.youmu.ckl.utils.WeixinUtil;
import tech.youmu.ckl.utils.XingeUtil;

/**
 * 
 * <p>Title:OrderServiceImpl</p>
 * @author xiongchuan
 * @version v1.0
 * <p>Date:2016年8月26日下午3:57:20</p>
 * <p>Description:TODO</p>
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order>  implements IOrderService {
    
    private OrderMapper orderMapper;
    
    @Autowired
    private IAdminTaskService adminTaskService;
    
    
    
    @Autowired
    private CarPartMapper carPartMapper;
    
    @Autowired
    private ServiceEvaluationMapper serviceEvaluationMapper;
    
    @Autowired
    private ServiceEvaluationDetailMapper serviceEvaluationDetailMapper;
    
    @Autowired
    private OrderRefundMapper orderRefundMapper;
    
    @Autowired
    private OrderServiceDetailMapper detailMapper;
    
    @Autowired
    private RestaurantComboMapper restaurantComboMapper;
    
    @Autowired
    private RestaurantDishesMapper restaurantDishesMapper;
    
    @Autowired
    private HotelRoomMapper hotelRoomMapper;
    
    @Autowired
    private UserBillMapper userBillMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private CarCarePriceMapper carCarePriceMapper;
    
    @Autowired
    private EmployeeMapper employeeMapper;
    
    @Autowired
    private BadgeComponent badgeComponent;
    
    @Autowired
    private PrinterMapper printerMapper;
    
    
    
    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月31日下午4:23:35;</p>
     *  <p>Description: TODO;</p>
     *  @param TODO 
     *  @throws TODO
     */
    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper) {
        super(orderMapper);
        this.orderMapper = orderMapper;
    }


    @Override
    public List<OrderInfo> findOrderInfo(Integer page,Integer rows,Long userId,Long employeeId, Integer status) {
        int startRows = (page-1)*rows;
        List<OrderInfo> orderInfos =  orderMapper.findOrderInfo(startRows,rows,userId,employeeId,status);
        for(OrderInfo orderInfo : orderInfos){
            orderInfo.setImgs(ImageURLUtil.fillPath(orderInfo.getImgs()));
            orderInfo.setStationCover(ImageURLUtil.fillPath(orderInfo.getStationCover()));
            orderInfo.setServiceHeadIcon(ImageURLUtil.fillPath(orderInfo.getServiceHeadIcon()));
        }
        return orderInfos;
    }

    @Override
    public OrderDetailInfo getOrderDetailInfo(Long orderId) {
        OrderDetailInfo orderDetailInfo =  orderMapper.getOrderDetailInfo(orderId);
        orderDetailInfo.setTime(DateUtil.getDifferTime(System.currentTimeMillis(),DateUtil.getTime(orderDetailInfo.getCreateTime(),1, Calendar.DATE)));
        orderDetailInfo.setStationCover(ImageURLUtil.fillPath(orderDetailInfo.getStationCover()));
        orderDetailInfo.setServiceHeadIcon(ImageURLUtil.fillPath(orderDetailInfo.getServiceHeadIcon()));
        if(orderDetailInfo.getCode() !=null){
            orderDetailInfo.setCodeUrl(QRCodeUtil.getQRCodeUrl(orderDetailInfo.getCode()));
        }
        return orderDetailInfo;
    }

    /**
     * @see tech.youmu.ckl.service.IOrderService#getOrderInfoTemps()
     */
    @Override
    public List<OrderInfoHistory> getOrderInfoHis(OrderInfoHistoryQuery historyQuery) {
        return orderMapper.getOrderInfoHis(historyQuery);
    }

    /**
     * @see tech.youmu.ckl.service.IOrderService#saveOrderInfoTemps()
     */
    @Override
    public void saveOrderInfoHis() {
        orderMapper.saveOrderInfoHis();
    }


    /**
     * @see tech.youmu.ckl.service.IOrderService#refundById()
     */
    @Override
    public void refundById(Long id) {
        Order order = orderMapper.getById(id);
        if(order == null){
            throw new BizExecption("未找到该订单！");
        }
        if(order.getStatus() != StatusConst.TWO && order.getStatus() != StatusConst.FIVE){
            throw new BizExecption("该订单已不能退款！");
        }
        UserBill userBill = userBillMapper.getByOrderId(id,StatusConst.TWO);
        if(userBill == null){
            throw new BizExecption("没有付款记录不能退款");
        }
        if(userBill.getPayMethod().equals(StatusConst.ONE)){
            if(!AlipayUtil.payRefund(userBill.getOutOrderNumber(), userBill.getBalance())){
                throw new BizExecption("退款失败");
            }
        }else if(userBill.getPayMethod().equals(StatusConst.TWO)){
            if(!WeixinUtil.payRefund(userBill.getOutOrderNumber(), userBill.getBalance(), userBill.getBalance())){
                throw new BizExecption("退款失败");
            }
        }else if(userBill.getPayMethod().equals(StatusConst.THREE)){
            userBillMapper.save(new UserBill(userBill.getOrderNumber(),userBill.getStationId(),userBill.getUserId(),id,"我的钱包","退款",StatusConst.SIX,userBill.getBalance(),StatusConst.FOUR,StatusConst.THREE,StatusConst.TWO,"钱包退款"));
            User user = userMapper.getById(userBill.getUserId());
            if(user == null){
                throw new BizExecption("用户为空！");
            }
            user.setBalance(user.getBalance() + userBill.getBalance());
            userMapper.update(user);
        }
        orderMapper.refundById(id);
        XingeUtil.askRefundOrderMessage(order.getUserId());
    }


    /**
     * @see tech.youmu.ckl.service.IOrderService#askRefundById(java.lang.Long)
     */
//    @Override
//    public void askRefundById(Long id) {
//        Order order = orderMapper.getById(id);
//        if(order == null){
//            throw new BizExecption("未找到该订单！");
//        }
//        if(order.getStatus() != StatusConst.TWO){
//            throw new BizExecption("该订单已不能退款！");
//        }
//        UserBill userBill = userBillMapper.getByOrderId(id);
//        if(userBill == null){
//            throw new BizExecption("没有付款记录不能退款");
//        }
//        if(userBill.getPayMethod().equals(StatusConst.ONE)){
//            AlipayUtil.payRefund(userBill.getOutOrderNumber(), userBill.getBalance());
//        }else if(userBill.getPayMethod().equals(StatusConst.TWO)){
//            WeixinUtil.payRefund(userBill.getOutOrderNumber(), userBill.getBalance(), userBill.getBalance());
//        }else if(userBill.getPayMethod().equals(StatusConst.THREE)){
//          userBillMapper.save(new UserBill(userBill.getUserId(),id,"我的钱包","退款",StatusConst.SIX,userBill.getBalance(),StatusConst.FOUR,StatusConst.THREE,StatusConst.TWO,"钱包退款"));
//      }
//        orderMapper.askRefundById(id);
//        XingeUtil.askRefundOrderMessage(order.getUserId());
//    }


    /**
     * @see tech.youmu.ckl.service.IOrderService#getCodeById(java.lang.Long)
     */
    @Override
    public Boolean checkCodeById(Long id,String code) {
        String codeById = orderMapper.getCodeById(id);
        if(StringUtils.isBlank(code)){
            throw new BizExecption("验证码不能为空！");
        }
        if(StringUtils.isBlank(codeById)){
            throw new BizExecption("未找到该订单的验证码！");
        }
        System.err.println("serviceCode: " + code + " " + "codeById: " + " " + codeById);
        if (StringUtils.equals(code, codeById)) {
            //更改订单状态： 待服务 变为 待完成
            awaitById(id);
            return true;
        }
        return false;
    }


    /**
     * @see tech.youmu.ckl.service.IOrderService#awaitById(java.lang.Long)
     */
    @Override
    public void awaitById(Long id) {
        Order order = orderMapper.getById(id);
        if(order == null){
            throw new BizExecption("未找到该订单！");
        }
        if(order.getStatus() != StatusConst.FIVE){
            throw new BizExecption("该订单未完成支付！");
        }
        orderMapper.awaitById(id);
    }


    /**
     * @see tech.youmu.ckl.service.IOrderService#finishHotelService(java.lang.Long, java.lang.String, java.lang.String, java.lang.Double)
     */
    @Override
    public Boolean finishHotelService(Long id, String serviceName, Integer number) {
        Order order = orderMapper.getById(id);
        if(order == null){
            throw new BizExecption("未找到该订单！");
        }
        if(order.getStatus() != StatusConst.SIX){
            throw new BizExecption("该订单未完成服务！");
        }
        //同时为空，表明没有新增商品信息，直接提交酒店服务记录
        if(StringUtils.isBlank(serviceName) && number == null){
            //更改:1、订单状态为待评价  2、是否为补差价订单，否
            
            order.setIsPayNew(false);
            order.setStatus(StatusConst.SEVEN);
            orderMapper.update(order);
            return true;
        }
        
        if(number==null && serviceName != null){
            throw new BizExecption("房间不能为空！");
        }
        if(number!=null && serviceName == null){
            throw new BizExecption("天数不能为空！");
        }
        
        System.err.println(number + " 房间数量：  " + order.getRoomNumber());
        if(number > order.getRoomNumber()){
            throw new BizExecption("新增商品数量不能超过订单的房间总数！");
        }
        
        if(number!=null && serviceName != null){
            //封装明细
            OrderServiceDetail detail = new OrderServiceDetail();
            detail.setType(StatusConst.TWO);;
            detail.setNumber(number);
            detail.setOrder(order);
            detail.setOrderNumber(order.getOrderNumber());
            detail.setServiceName(serviceName);
            
            //房间单价 = 订单总金额 / 房间数 / 天数   (因为订单只能下同一种类型的房间，故单价可以这样计算)
            BigDecimal totalAmount = new BigDecimal(order.getTotalAmount());
            BigDecimal roomNumber = new BigDecimal(order.getRoomNumber());
            BigDecimal days = new BigDecimal(order.getDays());
            BigDecimal price = totalAmount.divide(roomNumber).divide(days);
                    
            detail.setServicePrice(price);
            //保存明细
            detailMapper.save(detail);
            
            
            //更改主订单的以下字段:1、待支付金额，2、总金额，3、是否清算
            //待支付金额
            BigDecimal stayPayAmount = new BigDecimal(serviceName).multiply(new BigDecimal(number)).multiply(price);
            order.setStayPayAmount(stayPayAmount.doubleValue());
            //更改总金额
            order.setTotalAmount(order.getTotalAmount() + stayPayAmount.doubleValue());
            //设置为 未清算
            order.setIsPayNew(true);
            
            orderMapper.updateOrderAmountById(order);
            return true;
        }
        
        return false;
    }
    
    /**
     * @see tech.youmu.ckl.service.IOrderService#finishById(java.lang.Long)
     */
    @Override
    public Boolean finishById(Long id,AddOrderDetailList addLists) {
        Order order = orderMapper.getById(id);
        if(order == null){
            throw new BizExecption("未找到该订单！");
        }
        if(order.getStatus() != StatusConst.SIX){
            throw new BizExecption("该订单未完成服务！");
        }
        
        //获取前端传递的订单明细
        List<OrderServiceDetail> details = addLists.getAddLists();
        OrderServiceDetail firstServiceDetail = details.get(0);
        //判断是否需要追加服务
        if(details.size()<=0 || (StringUtils.isBlank(firstServiceDetail.getServiceName()) && firstServiceDetail.getServicePrice()==null && firstServiceDetail.getNumber()==null ) ){
            //直接提交服务记录，更改:1、订单状态  2、是否支付完成
            orderMapper.finishById(id);
            
            order.setIsPayNew(true);
            orderMapper.update(order);
            return true;
        }else {
            //若是有追加服务,则需要更改主订单的某些字段，和封装订单明细，并保存
          //待支付
            BigDecimal stayPayAmount = Global.BIG_DECIMAL_ZORE;
            
            for(OrderServiceDetail detail:details){
                OrderServiceDetail oDetail = new OrderServiceDetail();
                oDetail.setType(StatusConst.TWO);;
                
                Integer number = detail.getNumber();
                
                if(number==null || detail.getNumber() == 0){
                   throw new BizExecption("新增商品数量不能为空！");
                }
                oDetail.setNumber(number);
                oDetail.setOrderNumber(order.getOrderNumber());
                oDetail.setOrder(order);
                
                if(StringUtils.isBlank(detail.getServiceName())){
                    throw new BizExecption("新增商品名称不能为空！");
                }
                oDetail.setServiceName(detail.getServiceName());
                
                BigDecimal servicePrice = detail.getServicePrice()==null?Global.BIG_DECIMAL_ZORE:detail.getServicePrice();
                oDetail.setServicePrice(servicePrice);
                //封装后，保存该条明细
                detailMapper.save(oDetail);
                stayPayAmount = stayPayAmount.add(servicePrice.multiply(new BigDecimal(number)));
            }
            
            //更改主订单状态:1、待支付，2、总金额，3、是否清算
            //待支付金额
            order.setStayPayAmount(stayPayAmount.doubleValue());
            //更改总金额
            order.setTotalAmount(order.getTotalAmount() + stayPayAmount.doubleValue());
            //设置为 未清算
            order.setIsPayNew(true);
            
            orderMapper.updateOrderAmountById(order);
            return true;
        }
        
        
            
           
    }


    @Override
    public void orderServiceEvaluation(ServiceEvaluation serviceEvaluation) {
       OrderEvaluation orderEvaluation =serviceEvaluationMapper.getByOrderId(serviceEvaluation.getOrderId());
       if(orderEvaluation != null){
           throw new BizExecption("该订单已经评价");
       }
       Order order =  orderMapper.getById(serviceEvaluation.getOrderId());
       if(order ==null ||!order.getStatus().equals(StatusConst.SEVEN)){
           throw new BizExecption("订单不是待评价状态，不能评价");
       }
       double totalStar =0;
       for(ServiceEvaluationDetail serviceEvaluationDetail :serviceEvaluation.getServiceEvaluationDetails()){
           totalStar+=serviceEvaluationDetail.getStar();
       }
       serviceEvaluation.setStar(totalStar/serviceEvaluation.getServiceEvaluationDetails().size());
       serviceEvaluationMapper.save(serviceEvaluation);
       serviceEvaluationDetailMapper.batchSaveServiceEvaluationDetail(serviceEvaluation.getOrderId(),serviceEvaluation.getServiceEvaluationDetails());
       order.setStatus(StatusConst.EIGHT);
       orderMapper.update(order);
    }


    @Override
    public void orderApplyRefund(Long orderId, String explain, String reason, Double amount) {
        Order order = orderMapper.getById(orderId);
        if(order ==null ||!order.getStatus().equals(StatusConst.FIVE)){
            throw new BizExecption("订单已服务不能退款");
        }
        if(amount>order.getPaidAmount()){
            throw new BizExecption("退款金额大于支付金额不能退款");
        }
        orderRefundMapper.save(new OrderRefund(StatusConst.TWO, amount, explain, reason, orderId));
        order.setStatus(StatusConst.TWO);
        orderMapper.update(order);
        AdminTask task = new AdminTask(AdminTask.REFUND_HANDLE_URL,"你有一条退款消息;",StatusConst.TWO);
        adminTaskService.save(task);
        adminTaskService.push(task);
    }



    @Override
    public long createCarOrder(Order order) {
        order.setOrderNumber(OrderUtil.createOrderNumber(order.getUserId()));
        if(order.getTotalAmount()==null||order.getTotalAmount()==StatusConst.ZERO){
            order.setStatus(StatusConst.FIVE);
            order.setCode(OrderCodeUtil.getOrderCode());
        }else{
            order.setStatus(StatusConst.FOUR);
        }
        order.setIsPayNew(StatusConst.FALSE);
        orderMapper.save(order);
        List<OrderServiceDetail> orderServiceDetails=order.getOrderServiceDetails();
        if(orderServiceDetails!=null&&orderServiceDetails.size()>0){
            for(OrderServiceDetail orderServiceDetail:orderServiceDetails){
                if(order.getOrderType().equals(StatusConst.FOUR)){
                    orderServiceDetail.setIsCarPart(StatusConst.TRUE);
                }else if(order.getOrderType().equals(StatusConst.THREE)){
                    orderServiceDetail.setIsCarPart(StatusConst.FALSE);
                    orderServiceDetail.setNumber(1);
                }
                orderServiceDetail.setType(StatusConst.ONE);
            }
            
            detailMapper.batchSaveOrderServiceDetail(orderServiceDetails,order.getId(),order.getOrderNumber());
        }
        if(StatusConst.FOUR ==order.getOrderType()&&orderServiceDetails!=null){
            List<String> pics = carPartMapper.findPic(orderServiceDetails);
            if(pics !=null&&pics.size()>0){
                orderMapper.batchSaveOrderImg(order.getId(),pics);
            }
        }else if(StatusConst.THREE ==order.getOrderType()&&orderServiceDetails!=null){
            List<String> pics = carCarePriceMapper.findPic(orderServiceDetails);
            if(pics !=null&&pics.size()>0){
                orderMapper.batchSaveOrderImg(order.getId(),pics);
            }
        }else if(StatusConst.FIVE ==order.getOrderType()){
            List<String> pics = new ArrayList<>();
            pics.add(OrderServiceDetail.ASSISTANCE_PIC);
            if(pics !=null&&pics.size()>0){
                orderMapper.batchSaveOrderImg(order.getId(),pics);
            }
        }
        return order.getId();
    }




    @Override
    public long createRestaurantOrder(Long userId, Long comboId,Integer comboNumber, Double totalAmount,String invoiceTitle) {
        RestaurantCombo restaurantCombo = restaurantComboMapper.getRestaurantComboById(comboId);
        if(restaurantCombo ==null){
            throw new BizExecption("没有该套餐");
        }
        Restaurant restaurant = restaurantCombo.getRestaurant();
        if(restaurant ==null){
            throw new BizExecption("没有该饭店");
        }
        Double totalPrice = restaurantCombo.getPrice()*comboNumber;
        if(!totalAmount.equals(totalPrice)){
            throw new BizExecption("订单价格错误");
        }
        Order order = new Order(invoiceTitle,restaurantCombo.getId(),restaurant.getStationId(),OrderUtil.createOrderNumber(userId),StatusConst.TWO,userId,restaurant.getName(),restaurant.getFullAddress(),restaurant.getMobile(),restaurantCombo.getName(),comboNumber,totalAmount,DateUtil.dateToString(restaurantCombo.getStartTime()),DateUtil.dateToString(restaurantCombo.getEndTime()),restaurant.getOpenTime(),restaurant.getCloseTime(),restaurant.getOrderTips(),restaurant.getOrderRule(),restaurant.getOrderSweet(),StatusConst.FALSE,totalAmount,StatusConst.FOUR);
        orderMapper.save(order);
        List<RestaurantDishes> restaurantDishesList = restaurantDishesMapper.getDishesByComboId(comboId);
        List<OrderServiceDetail> orderServiceDetails = new ArrayList<>();
        for(RestaurantDishes restaurantDishes:restaurantDishesList){
            OrderServiceDetail orderServiceDetail = new OrderServiceDetail(order.getId(),restaurantDishes.getName(),restaurantDishes.getPrice(),restaurantDishes.getNum(),StatusConst.ONE);
            orderServiceDetails.add(orderServiceDetail);
        }
        if(orderServiceDetails!=null&&orderServiceDetails.size()>0){
            detailMapper.batchSaveOrderServiceDetail(orderServiceDetails,order.getId(),order.getOrderNumber());
        }
        if(restaurantCombo.getImg() !=null){
            orderMapper.saveOrderImg(new OrderImage(order.getId(),restaurantCombo.getImg()));
        }
        return order.getId();
    }


    @Override
    public long createHotelOrder(Long userId, String startTime, String endTime, Long hotelRoomId,Integer roomNumber, Integer days, String contactMobile, String planTime, String[] checkInName, Double totalAmount,String invoiceTitle) {
        HotelRoom hotelRoom = (HotelRoom) hotelRoomMapper.getHotelRoomById(hotelRoomId);
        if(hotelRoom == null){
            throw new BizExecption("该房间已经取消");
        }
        Hotel hotel = hotelRoom.getHotel();
        if(hotel == null){
            throw new BizExecption("没有该酒店");
        }
        Double totalPrice = hotelRoom.getPrice()*days*roomNumber;
        if(!totalAmount.equals(totalPrice)){
            throw new BizExecption("订单价格错误");
        }
        Order order = new Order(invoiceTitle,hotelRoom.getId(),hotel.getStationId(),OrderUtil.createOrderNumber(userId),StatusConst.ONE,userId,hotel.getName(),hotel.getFullAddress(),hotel.getMobile(),startTime,endTime,days,roomNumber,hotelRoom.getBedTypeName(),totalAmount,contactMobile,planTime,checkInName[0],totalAmount,StatusConst.FOUR);
        orderMapper.save(order);
        List<OrderServiceDetail> orderServiceDetails = new ArrayList<>();
        for(String name:checkInName){
            OrderServiceDetail orderServiceDetail = new OrderServiceDetail(order.getId(),name,StatusConst.ONE);
            orderServiceDetails.add(orderServiceDetail);
        }
        if(orderServiceDetails!=null&&orderServiceDetails.size()>0){
            detailMapper.batchSaveOrderServiceDetail(orderServiceDetails,order.getId(),order.getOrderNumber());
        }
        if(hotelRoom.getCover() !=null){
            orderMapper.saveOrderImg(new OrderImage(order.getId(),hotelRoom.getCover()));
        }
        return order.getId();
    }


    /**
     * @see tech.youmu.ckl.service.IOrderService#sendPayRemainMessage(java.lang.String)
     */
    @Override
    public void sendPayRemainMessage(String mobile) {
        JSONObject json = new JSONObject();
//      json.put("content", "尊敬的用户，您有未付款的订单需要处理~");
        json.put("code", "666666");
        json.put("product", ConfigUtil.getSMSSign());
        if(!SendSmsUtil.sendMessage(mobile,json.toString(),ConfigUtil.getSMSTemplateCodeRegist())){
            throw new BizExecption("发送失败");
        }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IOrderService#download(tech.youmu.ckl.query.OrderQuery, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void download(OrderQuery baseQuery,HttpServletRequest request,
            HttpServletResponse response) throws Exception {
      
      // 表头的内容是固定
      String[] heads = { "订单号", "订单金额", "订单类型", "服务站点", "用户账号", "用户昵称","订单状态","创建时间" };
        
      OutputStream os = null;
      SXSSFWorkbook wb = null;
      try {
          
          // 下载保证查询后的所有数据
          baseQuery.setPageSize(Integer.MAX_VALUE);
          PageList<Order> pageList = this.getPageList(baseQuery);
          // 行(由于这里设置的页面显示数据是最大尺寸，因此，查询到的结果也是所有的数据，导出的文件也应该是全部的数据)
          List<Order> rows = pageList.getRows();
          // 把List<Order>变成List<String[]>
          List<String[]> list = new ArrayList<String[]>();
          for (Order order : rows) {
              String[] strings = new String[heads.length];
              strings[0] = order.getOrderNumber();
              strings[1] = order.getTotalAmount() == null ? "" : order.getTotalAmount().toString();
              if(order.getServiceType() == null){
                strings[2] = "";
              }else{
                  strings[2] = order.getServiceType().getName() == null ? "" : order.getServiceType().getName();
              }
              
              if( order.getStation() == null){
                strings[3] = "";
              }else {
                  strings[3] = order.getStation().getName() == null ? "" : order.getStation().getName();
              }
              if(order.getUser()==null){
                strings[4] = "";
                strings[5] = "";
              }else {
                  strings[4] = order.getUser().getMobile() == null ? "" : order.getUser().getMobile();
                  strings[5] = order.getUser().getNickName() == null ? "" : order.getUser().getNickName();
              }
              
              Integer status = order.getStatus();
              if(status==null){
                  strings[6] = "";
              }
              switch (status) {
                case 1:
                    strings[6] = "已退款";
                    break;
                case 2:
                    strings[6] = "退款中";
                    break;
                case 3:
                    strings[6] = "已取消";
                    break;
                case 4:
                    strings[6] = "待支付";
                    break;
                case 5:
                    strings[6] = "待服务";
                    break;
                case 6:
                    strings[6] = "待完成";
                    break;
                case 7:
                    strings[6] = "待评价";
                    break;
                case 8:
                    strings[6] = "已完成";
                    break;
              }
              
              strings[7] =order.getCreateTime() == null ? "" : order.getCreateTime();
              
              // 最关键的方法
              list.add(strings);
          }
          // 下载相关处理
          SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
          // 时间戳
          String timestamp = sdf.format(new Date());
          // 返回的文件名
          String fileName = "订单明细_"+ timestamp +".xlsx";
          
          byte[] fileNameByte = fileName.getBytes("GBK");
         String filename = new String(fileNameByte, "ISO8859-1");
         
          wb = this.download(heads, list);
          response.setContentType("application/vnd.ms-excel");
          response.setHeader("Content-disposition", "attachment;filename="+ filename);
          os = response.getOutputStream();
          wb.write(os);
      } catch (Exception e) {
          e.printStackTrace(); 
      } finally{  
          os.flush();  
          os.close();  
      }   
  }
    

    
    


    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IOrderService#finishRoadSide(java.lang.Long, java.lang.Integer)
     */
    @Override
    public void finishRoadSide(Long id) {
        Order order = orderMapper.getById(id);
        if(order==null){
            throw new BizExecption("未找到该订单！");
        }
        order.setStatus(StatusConst.SEVEN);
        orderMapper.update(order);
    }


    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderMapper.getById(orderId);
        if(order==null){
            throw new BizExecption("未找到该订单！");
        }
        if(!order.getStatus().equals(StatusConst.FOUR)){
            throw new BizExecption("订单不是待付款状态不能取消！");
        }
        order.setStatus(StatusConst.THREE);
        orderMapper.update(order);
        
    }


    @Override
    public long scanQRCode(String code,Long employeeId) {
        Employee employee = employeeMapper.getById(employeeId);
        if(employee==null){
            throw new BizExecption("没有该员工");
        }
        if(employee.getOrderId() !=null){
            throw new BizExecption("还有订单没有处理完");
        }
        Order order = orderMapper.getOrderByCode(code);
        if(order == null){
            throw new BizExecption("没有该订单");
        }
        if(order.getEmpId()!=null){
            throw new BizExecption("该服务码已经扫码,不能重复扫码");
        }
        if(order.getStatus()!=StatusConst.FIVE){
            throw new BizExecption("该订单不是待服务状态");
        }
        if(order.getOrderType()==null||(!order.getOrderType().equals(StatusConst.THREE)&&!order.getOrderType().equals(StatusConst.FOUR))){
            throw new BizExecption("该订单不是汽车维修或者保养订单,不能接单");
        }
        order.setEmpId(employeeId);
        order.setStationId(employee.getStationId());
        order.setStatus(StatusConst.SIX);
        orderMapper.update(order);
        employee.setOrderId(order.getId());
        employeeMapper.update(employee);
        badgeComponent.orderBadge(order.getUserId());
        return order.getId();
    }


    @Override
    public void updateCarOrder(Order order) {
        Order dbOrder = orderMapper.getById(order.getId());
        if(dbOrder == null){
            throw new BizExecption("没有该订单");
        }
        if(dbOrder.getEmpId() == null){
            throw new BizExecption("该订单还没有扫码接单");
        }
        Employee employee = employeeMapper.getById(dbOrder.getEmpId());
        if(employee == null){
            throw new BizExecption("该订单没有该员工");
        }
        if(!dbOrder.getId().equals(employee.getOrderId())){
            throw new BizExecption("该员工没有接收该订单");
        }
        if(dbOrder.getStatus()!=StatusConst.SIX){
            throw new BizExecption("该订单不是待完成状态，不能修改");
        }
        if(dbOrder.getIsPayNew()||dbOrder.getIsRefund()){
            throw new BizExecption("该订单已经修改过，不能修改");
        }
        if(dbOrder.getPaidAmount()<order.getTotalAmount()){
            dbOrder.setStayPayAmount(order.getTotalAmount()-dbOrder.getPaidAmount());
            dbOrder.setIsPayNew(StatusConst.TRUE);
            orderMapper.update(dbOrder);
        }else if(dbOrder.getPaidAmount()>order.getTotalAmount()){
            dbOrder.setRefundAmount(dbOrder.getPaidAmount()-order.getTotalAmount());
            dbOrder.setPaidAmount(order.getTotalAmount());
            dbOrder.setTotalAmount(order.getTotalAmount());
            UserBill userBill = userBillMapper.getByOrderId(order.getId(),StatusConst.TWO);
            if(userBill != null){
                if(userBill.getPayMethod().equals(StatusConst.ONE)){
                    AlipayUtil.payRefund(userBill.getOutOrderNumber(), dbOrder.getRefundAmount());
                }else if(userBill.getPayMethod().equals(StatusConst.TWO)){
                    WeixinUtil.payRefund(userBill.getOutOrderNumber(), userBill.getBalance(), dbOrder.getRefundAmount());
                }else if(userBill.getPayMethod().equals(StatusConst.THREE)){
                    userBillMapper.save(new UserBill(userBill.getOrderNumber(),userBill.getStationId(),userBill.getUserId(),order.getId(),"我的钱包","退款",StatusConst.SIX,dbOrder.getRefundAmount(),StatusConst.FOUR,StatusConst.THREE,StatusConst.TWO,"钱包退款"));
                    User user = userMapper.getById(userBill.getUserId());
                    if(user == null){
                        throw new BizExecption("用户为空！");
                    }
                    user.setBalance(user.getBalance() + dbOrder.getRefundAmount());
                    userMapper.update(user);
                }
            }
            dbOrder.setIsRefund(StatusConst.TRUE);
            orderMapper.update(dbOrder);
        }
        List<OrderServiceDetail> orderServiceDetails=order.getOrderServiceDetails();
        List<OrderServiceDetail> addOrderServiceDetails = new ArrayList();
        List<OrderServiceDetail> updateOrderServiceDetails = new ArrayList();
        if(orderServiceDetails !=null){
            for(OrderServiceDetail orderServiceDetail:orderServiceDetails){
                if(orderServiceDetail.getType() == null){
                    continue;
                }
                if(orderServiceDetail.getType()==StatusConst.TWO){
                    addOrderServiceDetails.add(orderServiceDetail);
                }else if(orderServiceDetail.getType()==StatusConst.ONE){
                    updateOrderServiceDetails.add(orderServiceDetail);
                }else if(orderServiceDetail.getType()==StatusConst.THREE){
                    orderServiceDetail.setNumber(null);
                    orderServiceDetail.setPrice(null);
                    updateOrderServiceDetails.add(orderServiceDetail);
                }
            }
        }
        if(addOrderServiceDetails!=null&&addOrderServiceDetails.size()>0){
            detailMapper.batchSaveOrderServiceDetail(addOrderServiceDetails,dbOrder.getId(),dbOrder.getOrderNumber());
        }
        
        if(updateOrderServiceDetails!=null&&updateOrderServiceDetails.size()>0){
            detailMapper.batchUpdateOrderServiceDetail(updateOrderServiceDetails);
        }
        
    }


    @Override
    public void warnUserPay(Long orderId) {
        Order order = orderMapper.getById(orderId);
        if(order == null){
            throw new BizExecption("没有订单");
        }
        User user =order.getUser();
        if(user!=null){
            JSONObject json = new JSONObject();
            json.put("orderNumber", order.getOrderNumber());
            SendSmsUtil.sendMessage(user.getMobile(),json.toString(),ConfigUtil.getSMSTemplateWarnPay());
        }
        XingeUtil.warnUserPayMessage(order.getUserId());
    }


    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IOrderService#pageList(tech.youmu.ckl.query.OrderQuery)
     */
    @Override
    public PageList<Order> refundPageList(OrderQuery orderQuery) {
        List<Order> list = orderMapper.getRefundOrdersByQuery(orderQuery);
        Long count = orderMapper.getRefundOrdersCountByQuery(orderQuery);
        PageList<Order> pageList = new PageList<Order>();
        pageList.setRows(list);
        pageList.setTotal(count);
        return pageList;
    }


    @Override
    public void finishOrder(Long employeeId, Long orderId) {
        Order dbOrder = orderMapper.getById(orderId);
        if(dbOrder == null){
            throw new BizExecption("没有该订单");
        }
        if(dbOrder.getEmpId() == null){
            throw new BizExecption("该订单还没有扫码接单");
        }
        Employee employee = employeeMapper.getById(dbOrder.getEmpId());
        if(employee == null){
            throw new BizExecption("该订单没有该员工");
        }
        if(!dbOrder.getId().equals(employee.getOrderId())){
            throw new BizExecption("该员工没有接收该订单");
        }
       
        if(dbOrder.getStatus()!=StatusConst.SIX){
            employeeMapper.setEmployeeOrderId(employee.getId(),null,null);
            return;
        }
        if(!dbOrder.getIsPayNew()){
            dbOrder.setStatus(StatusConst.SEVEN);
            orderMapper.update(dbOrder);
        }
        int numInCount=employee.getNumInCount();
        employeeMapper.setEmployeeOrderId(employee.getId(),null,numInCount+1);
    }


    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IOrderService#printById(java.lang.Long)
     */
    @Override
    public boolean printById(Long id) {
        /**
         * 1、根据订单id得到服务站id
         * 2、根据服务站id得到对应的打印机
         * 3、调用打印机工具类，设置打印的内容，完成打印
         */
        Order order = orderMapper.getById(id);
        List<OrderServiceDetail> detalis = detailMapper.getByOrderId(id);
        if(order == null){
            throw new BizExecption("未找到该订单！");
        }
        if(order.getStatus() != StatusConst.EIGHT){
            throw new BizExecption("订单未完成！");
        }
        if(detalis.size()==0){
            throw new BizExecption("未找到该订单明细！");
        }
        Long stationId = order.getStationId();
        if(stationId == null){
            throw new BizExecption("未找到所在服务站！");
        }
        List<Printer> printers = printerMapper.getByStationId(stationId);
        if(printers.size() == 0 || printers == null){
            throw new BizExecption("该服务站未绑定打印机！");
        }
        Printer printer = printers.get(0);
        
        /*
         * 订单字段处理
         */
        //服务项目
        String serviceName = null;
        //服务明细
        StringBuffer serviceContent = new StringBuffer();
        for (OrderServiceDetail detali : detalis) {
            //商品名称
            String detailName = detali.getServiceName();
            //商品数量
            Integer number = detali.getNumber();
            //商品单价
            BigDecimal servicePrice = detali.getServicePrice();
            
            //商品小计
            BigDecimal multiply = new BigDecimal(number).multiply(servicePrice);
            serviceContent.append(detailName + " X" + number + "    " + multiply +"\r");
        }
        if(order.getServiceType().getType()!= null){
            switch(order.getServiceType().getType()){
                case ServiceType.HOTEL:
                    serviceName = "酒店服务";
                    break;
                case ServiceType.RESTAURANT:
                    serviceName = "饭店服务";
                    break;
                case ServiceType.CAR_CARE:
                    serviceName = "汽车维护";
                    break;
                case ServiceType.CAR_REPAIR:
                    serviceName = "汽车维修";
                    break;
                case ServiceType.ROAD_SIDE:
                    serviceName = "道路救援";
                    break;
                case ServiceType.OFFLINE:
                    serviceName = "线下订单";
                    break;
            }
        }
        StringBuffer content = new StringBuffer();
       content.append("<FB>消费清单</FB>\r");
       content.append("----------------------\r");
//       content.append("发票抬头："+ order.getInvoiceTitle() +"\r");
       content.append("服务站点："+ order.getStation().getName() +"\r");
       content.append("订单号："+ order.getOrderNumber() +"\r");
       content.append("服务项目："+ serviceName +"\r");
       content.append("----------------------\r");
       
       //拼接购买明细
       content.append(serviceContent.toString());
       
       content.append("----------------------\r");
       content.append("合计金额："+ order.getTotalAmount() +"\r");
       content.append("实收金额："+ order.getPaidAmount() +"\r");
       content.append(DateUtil.getDate()+"\r");
       content.append("谢谢惠顾，欢迎再次光临！\r");
        
        PrintMessageUtil.sendContent(printer, content.toString());
        return true;
    }

    
    

}
