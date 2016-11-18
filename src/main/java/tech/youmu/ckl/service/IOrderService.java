/**
 * @Title: IUserService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:57:38
 * @version V1.0
 */

package tech.youmu.ckl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tech.youmu.ckl.app.vo.OrderDetailInfo;
import tech.youmu.ckl.app.vo.OrderInfo;
import tech.youmu.ckl.domain.AddOrderDetailList;
import tech.youmu.ckl.domain.Department;
import tech.youmu.ckl.domain.Order;
import tech.youmu.ckl.domain.OrderInfoHistory;
import tech.youmu.ckl.domain.ServiceEvaluation;
import tech.youmu.ckl.query.DepartmentQuery;
import tech.youmu.ckl.query.OrderInfoHistoryQuery;
import tech.youmu.ckl.query.OrderQuery;
import tech.youmu.ckl.query.PageList;

/**
 * 
 * <p>Title:IOrderService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月26日下午3:54:03</p>
 * <p>Description:订单</p>
 */
public interface IOrderService extends IBaseService<Order>{

    List<OrderInfo> findOrderInfo(Integer page,Integer rows,Long userId,Long employeeId, Integer status);

    OrderDetailInfo getOrderDetailInfo(Long orderId);
    
    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月29日上午9:51:41;</p>
     *	<p>Description: 保存订单完成数量的中间表;</p>
     */
    void saveOrderInfoHis(); 
    
    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月29日上午9:52:07;</p>
     *	<p>Description: 获取每月员工完成订单数量;</p>
     *  @return 
     */
    List<OrderInfoHistory> getOrderInfoHis(OrderInfoHistoryQuery historyQuery);
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月24日下午5:00:16;</p>
     *	<p>Description: 退款订单列表;</p>
     *  @param orderQuery
     *  @return
     */
    public PageList<Order> refundPageList(OrderQuery orderQuery);
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月1日上午10:07:49;</p>
     *	<p>Description: 订单同意退款操作;</p>
     */
    void refundById(Long id);
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月1日下午4:50:14;</p>
     *	<p>Description: 申请退款操作;</p>
     *  @param id
     */
//    void askRefundById(Long id);

	/**
	 * 根据订单id校验订单的服务码
	 * @param id 订单id
	 */
	Boolean checkCodeById(Long id,String serviceCode);
	
	/**
	 * 提交验证码，更改状态为服务中
	 * @param id
	 */
	void awaitById(Long id);
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年9月13日下午5:32:22;</p>
	 *	<p>Description: 提交服务记录;</p>
	 *  @param id 主订单id
	 *  @param addLists 追加的订单明细    
	 *  @return
	 */
	Boolean finishById(Long id,AddOrderDetailList addLists);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年9月8日下午5:28:24;</p>
	 *	<p>Description: 评价;</p>
	 *  @param serviceEvaluation
	 */
    void orderServiceEvaluation(ServiceEvaluation serviceEvaluation);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月9日下午4:52:23;</p>
     *	<p>Description: 申请退款;</p>
     *  @param orderId
     *  @param explain
     *  @param reason
     *  @param amount
     */
    void orderApplyRefund(Long orderId, String explain, String reason, Double amount);

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月14日下午5:13:06;</p>
     *	<p>Description: 提交酒店服务;</p>
     *  @param id
     *  @param serviceName
     *  @param number
     *  @param price
     *  @return
     */
    Boolean finishHotelService(Long id, String serviceName, Integer number);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月14日下午5:07:15;</p>
     *	<p>Description: 下单;</p>
     *  @param order
     */
    long createCarOrder(Order order);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月18日下午2:19:28;</p>
     *	<p>Description: 饭店;</p>
     *  @param userId
     *  @param comboId
     *  @param totalAmount
     *  @return
     */
    long createRestaurantOrder(Long userId,  Long comboId,Integer comboNumber, Double totalAmount,String invoiceTitle);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月18日下午3:57:26;</p>
     *	<p>Description: 酒店下单;</p>
     *  @param userId
     *  @param startTime
     *  @param endTime
     *  @param hotelRoomId
     *  @param days
     *  @param contactMobile
     *  @param planTime
     *  @param checkInName
     *  @param totalAmount
     *  @return
     */
    long createHotelOrder(Long userId, String startTime, String endTime, Long hotelRoomId,Integer roomNumber, Integer days, String contactMobile, String planTime, String[] checkInName, Double totalAmount,String invoiceTitle);

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月19日下午11:13:06;</p>
     *	<p>Description: 提交酒店服务;</p>
     *  @param mobile
     */
	void sendPayRemainMessage(String mobile);
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年9月22日下午5:54:44;</p>
	 *	<p>Description: 导出订单;</p>
	 *  @param baseQuery
	 *  @param request
	 *  @param response
	 *  @return
	 *  @throws Exception
	 */
	public void download(OrderQuery baseQuery,HttpServletRequest request,
            HttpServletResponse response) throws Exception;

	

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月23日下午12:07:32;</p>
     *	<p>Description: 提交道路救援服务;</p>
     *  @param id
     *  @param status
     */
    void finishRoadSide(Long id);
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月14日下午5:11:30;</p>
     *	<p>Description: 取消订单;</p>
     *  @param orderId
     */
    void cancelOrder(Long orderId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月19日上午9:24:09;</p>
     *	<p>Description: 扫二维码;</p>
     *  @param code
     *  @return
     */
    long scanQRCode(String code,Long employeeId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月19日上午9:48:38;</p>
     *	<p>Description: 修改汽车订单;</p>
     *  @param order
     */
    void updateCarOrder(Order order);

    void warnUserPay(Long orderId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月25日下午4:05:45;</p>
     *	<p>Description: 完成订单;</p>
     *  @param employeeId
     *  @param orderId
     */
    void finishOrder(Long employeeId, Long orderId);

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年11月3日下午4:12:15;</p>
     *	<p>Description: 根据订单id打印订单;</p>
     *  @param id
     *  @return
     */
    boolean printById(Long id);

    
}
