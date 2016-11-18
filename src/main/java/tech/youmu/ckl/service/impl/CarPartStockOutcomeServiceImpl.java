/**
 * Project Name:ckl
 * File Name:CarPartStockOutcomeService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.CarPartDepot;
import tech.youmu.ckl.domain.CarPartStock;
import tech.youmu.ckl.domain.CarPartStockOutcome;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.Order;
import tech.youmu.ckl.domain.OrderServiceDetail;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.domain.UserBill;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.CarPartDepotMapper;
import tech.youmu.ckl.mapper.CarPartMapper;
import tech.youmu.ckl.mapper.CarPartStockMapper;
import tech.youmu.ckl.mapper.CarPartStockOutcomeMapper;
import tech.youmu.ckl.mapper.EmployeeMapper;
import tech.youmu.ckl.mapper.OrderMapper;
import tech.youmu.ckl.mapper.OrderServiceDetailMapper;
import tech.youmu.ckl.mapper.UserBillMapper;
import tech.youmu.ckl.mapper.UserMapper;
import tech.youmu.ckl.service.ICarPartStockOutcomeService;
import tech.youmu.ckl.utils.AlipayUtil;
import tech.youmu.ckl.utils.OrderCodeUtil;
import tech.youmu.ckl.utils.OrderUtil;
import tech.youmu.ckl.utils.WeixinUtil;

/**
 * <p>Title:CarPartStockOutcomeService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月13日下午2:28:59</p>
 * <p>Description:零部件出库服务</p>
 */
@Service
public class CarPartStockOutcomeServiceImpl extends BaseServiceImpl<CarPartStockOutcome> implements ICarPartStockOutcomeService {

    private CarPartStockOutcomeMapper carPartStockOutcomeMapper;
    
    @Autowired
    private OrderServiceDetailMapper detailMapper;
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private CarPartMapper carPartMapper;
    
    @Autowired
    private CarPartDepotMapper carPartDepotMapper;
    
    @Autowired
    private  CarPartStockMapper carPartStockMapper;
    
    @Autowired
    private EmployeeMapper empMapper;
    
    @Autowired
    private UserBillMapper userBillMapper;
    
    @Autowired
    private UserMapper userMapper;
    

    @Autowired
    public CarPartStockOutcomeServiceImpl(CarPartStockOutcomeMapper carPartStockOutcomeMapper) {
        super(carPartStockOutcomeMapper);
        this.carPartStockOutcomeMapper = carPartStockOutcomeMapper;
    }

    @Override
    public void outcome(Long employeeId,Double totalAmount, Integer type, List<CarPartStockOutcome> carPartStockOutcomes) {
        CarPartDepot carPartDepot =carPartDepotMapper.getByEmployeeId(employeeId);
        if(carPartDepot == null){
            throw new BizExecption("没有所属仓库不能出库");
        }
        List<CarPartStockOutcome> dbCarPartStockOutcomes = carPartStockOutcomeMapper.findRlreadyOutcome(carPartStockOutcomes,type);
        if(dbCarPartStockOutcomes!=null&&dbCarPartStockOutcomes.size()>0){
            StringBuffer buffer = new StringBuffer();
            for(CarPartStockOutcome carPartStockOutcome:dbCarPartStockOutcomes){
                buffer.append(carPartStockOutcome.getCode()).append(";");
            }
            throw new BizExecption("已出库的条形码："+buffer.toString());
        }
        carPartStockOutcomeMapper.batchSaveCarPartStockOutcome(employeeId,type,carPartDepot.getId(),carPartStockOutcomes);
        handleCarpartStockNum(carPartStockOutcomes, carPartDepot);
        if(type == StatusConst.ONE){
            handleOfflineOrder(employeeId, totalAmount, carPartStockOutcomes);         
        }else if(type == StatusConst.TWO){
//            handleOnlineOrder(orderId);
        }
    }

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月21日上午11:08:58;</p>
     *	<p>Description: 线上订单处理;</p>
     *  @param orderId
     */
    private void handleOnlineOrder(Long orderId) {
        Order order = orderMapper.getById(orderId);
        if(order == null){
            return;
        }
        if(!order.getIsPayNew()){
            order.setStatus(StatusConst.SEVEN);
        }
        if(order.getRefundAmount()!=0&&!order.getIsRefund()){
            UserBill userBill = userBillMapper.getByOrderId(orderId,StatusConst.TWO);
            if(userBill != null){
                if(userBill.getPayMethod().equals(StatusConst.ONE)){
                    AlipayUtil.payRefund(userBill.getOutOrderNumber(), order.getRefundAmount());
                }else if(userBill.getPayMethod().equals(StatusConst.TWO)){
                    WeixinUtil.payRefund(userBill.getOutOrderNumber(), userBill.getBalance(), order.getRefundAmount());
                }else if(userBill.getPayMethod().equals(StatusConst.THREE)){
                    userBillMapper.save(new UserBill(userBill.getOrderNumber(),userBill.getStationId(),userBill.getUserId(),orderId,"我的钱包","退款",StatusConst.SIX,order.getRefundAmount(),StatusConst.FOUR,StatusConst.THREE,StatusConst.TWO,"钱包退款"));
                    User user = userMapper.getById(userBill.getUserId());
                    if(user == null){
                        throw new BizExecption("用户为空！");
                    }
                    user.setBalance(user.getBalance() + order.getRefundAmount());
                    userMapper.update(user);
                }
            }
            order.setIsRefund(StatusConst.TRUE);
        }
        orderMapper.update(order);
    }

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月21日上午11:02:20;</p>
     *	<p>Description: 线下订单处理;</p>
     *  @param employeeId
     *  @param totalAmount
     *  @param carPartStockOutcomes
     */
    private void handleOfflineOrder(Long employeeId, Double totalAmount, List<CarPartStockOutcome> carPartStockOutcomes) {
        Employee employee = empMapper.getById(employeeId);
        Order order = new Order(employeeId,OrderUtil.createOrderNumber(employeeId),employee.getRealName(),employee.getMobile(),totalAmount,StatusConst.EIGHT);
        orderMapper.save(order);
        Map<Long,OrderServiceDetail> map = new HashMap();
        for(CarPartStockOutcome carPartStockOutcome:carPartStockOutcomes){
            Long carPartId = carPartStockOutcome.getCarPartId();
            OrderServiceDetail orderServiceDetail = map.get(carPartId);
            if(orderServiceDetail==null){
                orderServiceDetail = new OrderServiceDetail(carPartId,carPartStockOutcome.getCarPartName(),carPartStockOutcome.getOutPrice(),StatusConst.ONE);
                map.put(carPartId, orderServiceDetail);
            }else{
                orderServiceDetail.setPrice(orderServiceDetail.getPrice()+carPartStockOutcome.getOutPrice());
                map.put(carPartId, orderServiceDetail);
            }
        }
        List<OrderServiceDetail> orderServiceDetails=new ArrayList();
        for(Long key:map.keySet()){
            orderServiceDetails.add(map.get(key));
        }
        if(orderServiceDetails!=null&&orderServiceDetails.size()>0){
            detailMapper.batchSaveOrderServiceDetail(orderServiceDetails,order.getId(),order.getOrderNumber());
        }
        List<String> pics = carPartMapper.findPic(orderServiceDetails);
        if(pics !=null&&pics.size()>0){
            orderMapper.batchSaveOrderImg(order.getId(),pics);
        }
    }

    private void handleCarpartStockNum(List<CarPartStockOutcome> carPartStockOutcomes, CarPartDepot carPartDepot) {
        Map<Long,Integer> map = new HashMap();
        for(CarPartStockOutcome carPartStockOutcome:carPartStockOutcomes){
            Long carPartId = carPartStockOutcome.getCarPartId();
            Integer number = map.get(carPartStockOutcome.getCarPartId());
            if(number==null||number==0){
                map.put(carPartId, 1);
            }else{
                map.put(carPartId, number+1);
            }
        }
        List<Long> carPartIds=new ArrayList(map.keySet());
        List<CarPartStock> carPartStocks = carPartStockMapper.findByDepotIdAndCarPartId(carPartDepot.getId(),carPartIds);
        List<CarPartStock> updateCarPartStocks = new ArrayList<>();
        List<Long> dbCarPartIds=new ArrayList();
        for(CarPartStock carPartStock :carPartStocks){
            Long carPartId = carPartStock.getCarPartId();
            int num = carPartStock.getNum()+map.get(carPartId);
            carPartStock.setNum(num);
            updateCarPartStocks.add(carPartStock);
            dbCarPartIds.add(carPartId);
        }
        if(updateCarPartStocks.size()>0){
            carPartStockMapper.batchUpdateCarPartStockNum(updateCarPartStocks);
        }
    }
        

}
