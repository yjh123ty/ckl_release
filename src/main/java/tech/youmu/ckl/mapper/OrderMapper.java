package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.OrderCount;
import tech.youmu.ckl.app.vo.OrderDetailInfo;
import tech.youmu.ckl.app.vo.OrderInfo;
import tech.youmu.ckl.domain.HistoryStationCountOrderInfo;
import tech.youmu.ckl.domain.Order;
import tech.youmu.ckl.domain.OrderImage;
import tech.youmu.ckl.domain.OrderInfoHistory;
import tech.youmu.ckl.query.HistoryStationCountOrderInfoQuery;
import tech.youmu.ckl.query.OrderInfoHistoryQuery;
import tech.youmu.ckl.query.OrderQuery;

public interface OrderMapper extends BaseMapper<Order>{

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月23日上午11:37:42;</p>
	 *	<p>Description: 获取订单状态数量;</p>
	 *  @param userId
	 *  @return
	 */
	List<OrderCount> findOrderCountByUserId(Long userId);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月26日下午3:58:00;</p>
	 *	<p>Description: 订单列表;</p>
	 *  @param userId
	 *  @param status
	 *  @return
	 */
    List<OrderInfo> findOrderInfo(@Param("startRows")Integer startRows,@Param("rows")Integer rows,@Param("userId")Long userId,@Param("employeeId")Long employeeId,@Param("status") Integer status);

    OrderDetailInfo getOrderDetailInfo(Long orderId);

	/**
	 * 获取当月员工完成订单的情况
	 * @return
	 */
	List<OrderInfoHistory> getOrderInfoHis(OrderInfoHistoryQuery historyQuery);
	
	/**
	 * 设置员工每日完成订单数，录入员工的订单信息历史表
	 */
	void saveOrderInfoHis();
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年10月12日下午3:30:24;</p>
	 *	<p>Description: 系统录入上月各个服务站完成汽车类订单总数的记录;</p>
	 */
	void saveStationOrderInfoHis();
	
	List<HistoryStationCountOrderInfo> getStationOrderInfoHis(HistoryStationCountOrderInfoQuery historyQuery);
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年9月1日上午10:05:17;</p>
	 *	<p>Description: 根据订单id修改订单状态（由退款中变为已退款）;</p>
	 */
	void refundById(Long id);
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年9月1日下午4:49:32;</p>
	 *	<p>Description: 申请退款;</p>
	 *  @param id
	 */
	void askRefundById(Long id);

	/**
	 * 获取订单服务码
	 * @param id
	 * @return
	 */
	String getCodeById(Long id);
	
	
	/**
	 * 提交验证码，更改状态为服务中
	 * @param id
	 */
	void awaitById(Long id);
	
	/**
	 * 
	 *  <p>Author:yjh;</p>
	 *  <p>Date:2016年9月7日下午5:58:23;</p>
	 *	<p>Description: 完成服务;</p>
	 *  @param id
	 */
	void finishById(Long id);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年9月18日上午10:35:00;</p>
	 *	<p>Description: 使用码;</p>
	 *  @return
	 */
    List<String> findCode();
    
    /**
     *
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月18日上午11:17:27;</p>
     *	<p>Description: 更改订单金额明细;</p>
     */
    void updateOrderAmountById(Order order);
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月13日下午7:22:48;</p>
     *	<p>Description: 根据服务站id获取员工完成服务记录;</p>
     *  @param orderInfoHistoryQuery
     *  @return
     */
    List<OrderInfoHistory> getOrderInfoHisByStationId(OrderInfoHistoryQuery orderInfoHistoryQuery);

    void saveOrderImg(OrderImage orderImage);

    void batchSaveOrderImg(@Param("orderId")Long orderId, @Param("pics")List<String> pics);

    Order getOrderByCode(String code);
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月24日下午4:57:38;</p>
     *	<p>Description: 退款订单列表;</p>
     *  @return
     */
    List<Order> getRefundOrdersByQuery(OrderQuery orderQuery);
    
    long getRefundOrdersCountByQuery(OrderQuery orderQuery);

    List<Order> findOrderByStatus(Integer status);

    void batchCanelOverdueOrder(@Param("list")List<Long> cancelOrderIds);

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年11月16日下午2:11:12;</p>
     *	<p>Description: 设置默认评价为三星;</p>
     *  @param setOrderIds
     */
    void batchSetDefaultEvaluationOrder(@Param("list")List<Long> setOrderIds);

}