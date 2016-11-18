/**
 * Project Name:ckl
 * File Name:UserBillMapper.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.UserBillDetailInfo;
import tech.youmu.ckl.app.vo.UserBillInfo;
import tech.youmu.ckl.app.vo.UserBillMonthInfo;
import tech.youmu.ckl.domain.UserBill;
import tech.youmu.ckl.domain.UserBillBalanceInfo;
import tech.youmu.ckl.query.UserBillQuery;

/**
 * <p>Title:UserBillMapper</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月6日下午4:09:06</p>
 * <p>Description:TODO</p>
 */
public interface UserBillMapper extends BaseMapper{
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月6日下午5:25:58;</p>
     *	<p>Description: 查询用户账单明细;</p>
     *  @param userBillQuery
     *  @return
     */
    List<UserBill> getBillInfoByUserId(UserBillQuery userBillQuery);
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月6日下午5:26:16;</p>
     *	<p>Description: 查询账单明细个数;</p>
     *  @param userBillQuery
     *  @return
     */
    long getInfoCountByQuery(UserBillQuery userBillQuery);
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月6日下午6:15:10;</p>
     *	<p>Description: 查询充值，提现界面总览;</p>
     *  @param userBillQuery
     *  @return
     */
    List<UserBillBalanceInfo> getSumOfBillInfoByQuery(UserBillQuery userBillQuery);
    
    /**
     * 查询充值，体现总览的条数
     * @param userBillQuery
     * @return
     */
    long getSumOfBillInfoCountByQuery(UserBillQuery userBillQuery);

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月7日下午2:22:20;</p>
     *	<p>Description: 用户提现的转账操作;</p>
     *  @param userBill
     */
    void transferById(UserBill userBill);
    
    

    UserBillDetailInfo getUserBillDetailInfo(Long userBillId);

    UserBill getByOutOrderNumber(String outOrderNumber);

    UserBill getByOrderId(@Param("orderId")Long orderId,@Param("type")Integer type);

    List<UserBillMonthInfo> findUserBillMonthInfo(Long userId);

    List<Long> findLastMonthMeetExpenseUserId(String lastMonthOfToday);

    
}
