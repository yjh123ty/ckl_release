/**
 * Project Name:ckl
 * File Name:IUserBillService.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service;

import java.util.List;
import java.util.Map;

import tech.youmu.ckl.app.vo.UserBillDetailInfo;
import tech.youmu.ckl.app.vo.UserBillInfo;
import tech.youmu.ckl.app.vo.UserBillMonthInfo;
import tech.youmu.ckl.app.vo.WeixinInfo;
import tech.youmu.ckl.domain.UserBill;
import tech.youmu.ckl.domain.UserBillBalanceInfo;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.UserBillQuery;

/**
 * <p>Title:IUserBillService</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月6日下午4:09:41</p>
 * <p>Description:TODO</p>
 */
public interface IUserBillService extends IBaseService{
    
	/**
	 * 每个用户的充值、提现明细列表（分页）
	 * @param userBillQuery
	 * @return
	 */
	public PageList<UserBill> getListByUserId(UserBillQuery userBillQuery);
    
    /**
     * 用户充值、提现，总览列表(分页)
     * @param UserBillQuery（type = 1为充值查询； type = 2为提现查询）
     * @return
     */
    public PageList<UserBillBalanceInfo> getPageList(UserBillQuery userBillQuery);

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月7日下午2:21:26;</p>
     *	<p>Description: 完成用户提现转账操作;</p>
     *  @param userBill
     */
    public void transferById(UserBill userBill);
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年8月26日下午3:12:05;</p>
     *  <p>Description: 消费记录;</p>
     *  @param userId
     *  @param date
     *  @return
     */
    public List<UserBillMonthInfo> findUserBillMonthInfo(Long userId);
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年8月26日下午3:12:18;</p>
     *  <p>Description: 账单明细;</p>
     *  @param billId
     *  @return
     */
    public UserBillDetailInfo getUserBillDetailInfo(Long userBillId);
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月22日下午5:39:34;</p>
     *  <p>Description: 支付包支付;</p>
     *  @param orderId
     *  @return
     */
    String payAlipay(Long orderId,Double amount);
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月27日上午9:13:17;</p>
     *  <p>Description: 充值;</p>
     *  @param userId
     *  @param amount
     *  @return
     */
    String rechargeAlipay(Long userId, Double amount,Long rechargeComboId,String promotionCode);

    public boolean notifyAlipay(Map<String,String> paraMap);

    public WeixinInfo payWeixin(Long orderId, Double amount,String ip);

    public String notifyWeixin(String body);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月29日下午2:12:09;</p>
     *	<p>Description: 平台币支付;</p>
     *  @param orderId
     *  @param amount
     *  @param userId
     */
    public void payPlatform(Long orderId, Double amount, Long userId,String payPassword);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月29日下午3:52:07;</p>
     *	<p>Description: 微信充值;</p>
     *  @param userId
     *  @param amount
     *  @return
     */
    public WeixinInfo rechargeWeixin(Long userId, Double amount,String ip,Long rechargeComboId,String promotionCode);

    

    
}
