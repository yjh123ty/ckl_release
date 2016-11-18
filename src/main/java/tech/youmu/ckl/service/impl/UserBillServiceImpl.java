/**
 * Project Name:ckl
 * File Name:UserBillServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.app.vo.UserBillDetailInfo;
import tech.youmu.ckl.app.vo.UserBillMonthInfo;
import tech.youmu.ckl.app.vo.WeixinInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.CommissionDetail;
import tech.youmu.ckl.domain.CommissionPointDetail;
import tech.youmu.ckl.domain.Order;
import tech.youmu.ckl.domain.RechargeCombo;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.domain.UserBill;
import tech.youmu.ckl.domain.UserBillBalanceInfo;
import tech.youmu.ckl.domain.UserDistribution;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.CommissionMapper;
import tech.youmu.ckl.mapper.OrderMapper;
import tech.youmu.ckl.mapper.RechargeComboMapper;
import tech.youmu.ckl.mapper.UserBillMapper;
import tech.youmu.ckl.mapper.UserDistributionMapper;
import tech.youmu.ckl.mapper.UserMapper;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.UserBillQuery;
import tech.youmu.ckl.service.IUserBillService;
import tech.youmu.ckl.service.component.BadgeComponent;
import tech.youmu.ckl.utils.AlipayUtil;
import tech.youmu.ckl.utils.ConfigUtil;
import tech.youmu.ckl.utils.MD5Util;
import tech.youmu.ckl.utils.MapUtil;
import tech.youmu.ckl.utils.OrderCodeUtil;
import tech.youmu.ckl.utils.WeixinToolUtil;
import tech.youmu.ckl.utils.WeixinUtil;
import tech.youmu.ckl.utils.XingeUtil;

/**
 * <p>Title:UserBillServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月6日下午4:10:33</p>
 * <p>Description:TODO</p>
 */
@Service
public class UserBillServiceImpl extends BaseServiceImpl implements IUserBillService{

    private UserBillMapper userBillMapper;
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserDistributionMapper userDistributionMapper;
    
    @Autowired
    private CommissionMapper commissionMapper;
    
    @Autowired
    private  RechargeComboMapper rechargeComboMapper;
    
    @Autowired
    private BadgeComponent badgeComponent;
    
    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月6日下午4:10:48;</p>
     *	<p>Description: 用户账单服务层;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public UserBillServiceImpl(UserBillMapper userBillMapper) {
        super(userBillMapper);
        this.userBillMapper = userBillMapper;
    }


	/**
	 * @see tech.youmu.ckl.service.IUserBillService#pageList(tech.youmu.ckl.query.UserBillQuery)
	 */
	@Override
	public PageList<UserBillBalanceInfo> getPageList(UserBillQuery userBillQuery) {
		List<UserBillBalanceInfo> list = userBillMapper.getSumOfBillInfoByQuery(userBillQuery);
        Long count = userBillMapper.getSumOfBillInfoCountByQuery(userBillQuery);
        PageList<UserBillBalanceInfo> pageList = new PageList<UserBillBalanceInfo>();
        pageList.setRows(list);
        pageList.setTotal(count);
        return pageList;
	}


	/**
	 * @see tech.youmu.ckl.service.IUserBillService#getListByUserId(tech.youmu.ckl.query.UserBillQuery)
	 */
	@Override
	public PageList<UserBill> getListByUserId(UserBillQuery userBillQuery) {
		List<UserBill> list = userBillMapper.getBillInfoByUserId(userBillQuery);
        Long count = userBillMapper.getInfoCountByQuery(userBillQuery);
        PageList<UserBill> pageList = new PageList<UserBill>();
        pageList.setRows(list);
        pageList.setTotal(count);
        return pageList;
	}


    /**
     * @see tech.youmu.ckl.service.IUserBillService#transferById(java.lang.Long)
     */
    @Override
    public void transferById(UserBill userBill) {
    	if(userBill==null){
    	    throw new BizExecption("账单记录为空！");
    	}
    	if(userBill.getStatus()!=null && userBill.getStatus()==tech.youmu.ckl.constants.Global.UserBill.BILL_WITHDRAW){
    		userBillMapper.transferById(userBill);
    	}
    }



    @Override
    public UserBillDetailInfo getUserBillDetailInfo(Long billId) {
        return userBillMapper.getUserBillDetailInfo(billId);
    }

    @Override
    public String payAlipay(Long orderId,Double amount){
        Order order = orderMapper.getById(orderId);
        if(order ==null){
            throw new BizExecption("没有该订单");
        }
        if(order.getIsPayNew()&&!order.getStatus().equals(StatusConst.SIX)){
            throw new BizExecption("不是待服务，不能补差价");
        }
        if(!order.getIsPayNew()&&!order.getStatus().equals(StatusConst.FOUR)){
            throw new BizExecption("不是待支付，不能付款");
        }
        if(order.getIsPayNew()&&!amount.equals(order.getStayPayAmount())){
            throw new BizExecption("补差价金额与实际支付金额不符");
        }
        if(!order.getIsPayNew()&&!amount.equals(order.getTotalAmount())){
            throw new BizExecption("订单金额与实际支付金额不符");
        }
        UserBill userBill = userBillMapper.getByOrderId(order.getId(),StatusConst.TWO);
        if(userBill !=null){
            userBill.setBalance(amount);
            userBill.setOutOrderNumber(UUID.randomUUID().toString().replaceAll("-",""));
            userBill.setPayMethod(StatusConst.ONE);
            userBill.setStatus(StatusConst.ONE);
            userBillMapper.update(userBill);
        }else{
            String intro = order.getStationName() ==null?order.getServiceTypeName():(order.getStationName()+"-"+order.getServiceTypeName());
            userBill = new UserBill(order.getStationId(),order.getUserId(), order.getServiceTypeType(),order.getServiceTypeName(),amount,order.getStationName(), StatusConst.TWO, StatusConst.ONE, orderId,order.getOrderNumber(),intro,StatusConst.ONE);
            userBillMapper.save(userBill);
        }
        return AlipayUtil.payAmount(userBill.getIntro(), userBill.getOutOrderNumber(), amount);
    }
    
    @Override
    public String rechargeAlipay(Long userId, Double amount,Long rechargeComboId,String promotionCode) {
        UserBill userBill = new UserBill(userId,"我的钱包","充值",StatusConst.EIGHT,amount,StatusConst.ONE,StatusConst.ONE,StatusConst.ONE,"钱包充值",rechargeComboId,promotionCode);
        userBillMapper.save(userBill);
        return AlipayUtil.payAmount(userBill.getIntro(), userBill.getOutOrderNumber(), amount);

    }


    @Override
    public boolean notifyAlipay(Map<String,String> paraMap) {
        if (!AlipayUtil.signVerify(paraMap)) {
            return false;
        }
        String tradeStatus = paraMap.get("trade_status");
        if ("TRADE_SUCCESS".equals(tradeStatus)) {
            String outOrderNumber = (String) paraMap.get("out_trade_no");
            return this.notifySuccess(outOrderNumber);
        }
        return false;
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月27日下午2:49:22;</p>
     *	<p>Description: 回调成功;</p>
     */
    private boolean notifySuccess(String outOrderNumber){
        UserBill userBill = userBillMapper.getByOutOrderNumber(outOrderNumber);
        if(userBill == null){
            return false;
        }
        if (StatusConst.ONE == userBill.getType()&&userBill.getStatus().equals(StatusConst.ONE)) {
            rechargeSuccess(userBill);
        }else if(StatusConst.TWO == userBill.getType()&&userBill.getStatus().equals(StatusConst.ONE)){
            Order order = orderMapper.getById(userBill.getOrderId());
            order.setPayMethod(userBill.getPayMethod());
            paySuccess(order,userBill.getBalance());
        }
        userBill.setStatus(StatusConst.TWO);
        userBillMapper.update(userBill);
        return true;
    }

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月27日下午3:46:51;</p>
     *	<p>Description: 充值成功;</p>
     *  @param userBill
     */
    private void rechargeSuccess(UserBill userBill) {
        Long userId = userBill.getUserId();
        Double amount = userBill.getBalance();
        User user = userMapper.getById(userId);
        if(amount>=360){
            user.setIsAttend(StatusConst.TRUE);
        }
        user.setBalance(user.getBalance()+amount);
        this.handleDistribution(userBill, user);
        user.setIsRecharge(StatusConst.TRUE);
        userMapper.update(user);
        
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月11日下午5:46:38;</p>
     *	<p>Description: 分销处理;</p>
     *  @param userBill
     *  @param user
     */
    private void handleDistribution(UserBill userBill,User user){
        Long userId = userBill.getUserId();
        Double amount = userBill.getBalance();
        UserDistribution userDistribution = (UserDistribution) userDistributionMapper.getByUserId(userId);
        if(userDistribution ==null){
            if(userBill.getPromotionCode() ==null){
                return;
            }
            String userCode = userBill.getPromotionCode().substring(1, userBill.getPromotionCode().length()).toLowerCase();
            User userLv1 = userMapper.getByCode(userCode);
            if(userId.equals(userLv1.getId())){
                return;
            }
            if(!userLv1.getIsAttend()){
                return;
            }
            UserDistribution userDistributionLv1 = (UserDistribution) userDistributionMapper.getByUserId(userLv1.getId());
            if(userDistributionLv1 !=null){
                userDistribution = new UserDistribution(userId, userLv1.getId(), userDistributionLv1.getParentLv1(), userDistributionLv1.getParentLv2());
            }else{
                userDistribution = new UserDistribution(userId, userLv1.getId());
            }
            XingeUtil.inviteMessage(userLv1.getId());
            userDistributionMapper.save(userDistribution);
        }
        
        String rechargeComboName ="其他";
        if(userBill.getRechargeComboId()!=null){
            RechargeCombo rechargeCombo =rechargeComboMapper.getById(userBill.getRechargeComboId());
            if(rechargeCombo !=null){
                rechargeComboName =rechargeCombo.getName();
            }
        }else if(userBill.getPromotionCode() != null){
            String code = userBill.getPromotionCode().substring(0, 1).toLowerCase();
            RechargeCombo rechargeCombo =rechargeComboMapper.getRechargeComboByCode(code);
            if(rechargeCombo !=null){
                rechargeComboName =rechargeCombo.getName();
            }
        }
        if(!user.getIsRecharge()){
            double recommendAmount = amount*0.1;
            userMapper.addBalance(userDistribution.getParentLv1(),recommendAmount);
            CommissionDetail commissionDetail = new CommissionDetail(userDistribution.getParentLv1(),rechargeComboName,userId,user.getMobile(),recommendAmount,StatusConst.ONE,StatusConst.ONE);
            commissionMapper.saveCommissionDetail(commissionDetail);
        }
        List<CommissionPointDetail> commissionPointDetails = new ArrayList<>();
        CommissionPointDetail commissionPointDetailLv1 = new CommissionPointDetail(userDistribution.getParentLv1(), user.getMobile(), userBill.getId(), rechargeComboName, StatusConst.FALSE, amount, StatusConst.ONE);
        commissionPointDetails.add(commissionPointDetailLv1);
        if(userDistribution.getParentLv2() !=null){
            CommissionPointDetail commissionPointDetailLv2 = new CommissionPointDetail(userDistribution.getParentLv2(), user.getMobile(), userBill.getId(), rechargeComboName, StatusConst.FALSE, amount, StatusConst.ONE);
            commissionPointDetails.add(commissionPointDetailLv2);
        }
        if(userDistribution.getParentLv3() !=null){
            CommissionPointDetail commissionPointDetailLv3 = new CommissionPointDetail(userDistribution.getParentLv3(), user.getMobile(), userBill.getId(), rechargeComboName, StatusConst.FALSE, amount, StatusConst.ONE);
            commissionPointDetails.add(commissionPointDetailLv3);
        }
        
        commissionMapper.saveBatchCommissionPointDetail(commissionPointDetails);
    }


    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月27日下午3:46:16;</p>
     *	<p>Description: 支付成功;</p>
     *  @param userBill
     */
    private void paySuccess(Order order,Double amount) {
        
        if(order.getIsPayNew()){
            order.setStatus(StatusConst.SEVEN);
            order.setPaidAmount(order.getPaidAmount()+amount);
            order.setStayPayAmount(0d);
            order.setIsPayNew(StatusConst.FALSE);
        }else{
            order.setStatus(StatusConst.FIVE);
            order.setPaidAmount(amount);
            order.setCode(OrderCodeUtil.getOrderCode());
        }
        orderMapper.update(order);
        badgeComponent.consumeBadge(order.getUserId());
    }


    @Override
    public WeixinInfo payWeixin(Long orderId, Double amount,String ip) {
        Order order = orderMapper.getById(orderId);
        if(order ==null){
            throw new BizExecption("没有该订单");
        }
        if(order.getIsPayNew()&&!order.getStatus().equals(StatusConst.SIX)){
            throw new BizExecption("不是待服务，不能补差价");
        }
        if(!order.getIsPayNew()&&!order.getStatus().equals(StatusConst.FOUR)){
            throw new BizExecption("不是待支付，不能付款");
        }
        if(order.getIsPayNew()&&!amount.equals(order.getStayPayAmount())){
            throw new BizExecption("补差价金额与实际支付金额不符");
        }
        if(!order.getIsPayNew()&&!amount.equals(order.getTotalAmount())){
            throw new BizExecption("订单金额与实际支付金额不符");
        }
        UserBill userBill = userBillMapper.getByOrderId(order.getId(),StatusConst.TWO);
        if(userBill !=null){
            userBill.setBalance(amount);
            userBill.setOutOrderNumber(UUID.randomUUID().toString().replaceAll("-",""));
            userBill.setPayMethod(StatusConst.TWO);
            userBill.setStatus(StatusConst.ONE);
            userBillMapper.update(userBill);
        }else{
            String intro = order.getStationName() ==null?order.getServiceTypeName():(order.getStationName()+"-"+order.getServiceTypeName());
            userBill = new UserBill(order.getStationId(),order.getUserId(), order.getServiceTypeType(),order.getServiceTypeName(),amount,order.getStationName(), StatusConst.TWO, StatusConst.TWO, orderId,order.getOrderNumber(),intro,StatusConst.ONE);
            userBillMapper.save(userBill);
        }
        String prepayid = WeixinUtil.payUnifiedorder(userBill.getIntro(), userBill.getOutOrderNumber(), amount, ip);
        WeixinInfo weixinInfo = new WeixinInfo(ConfigUtil.getWeixinAppId(),ConfigUtil.getWeixinPartnerId(),prepayid);
        String sign = WeixinToolUtil.getSign(MapUtil.beanToMap(weixinInfo));
        weixinInfo.setSign(sign);
        return weixinInfo;
    }


    @Override
    public String notifyWeixin(String body) {
        Map<String,Object> result = new HashMap();
        result.put("return_code", "success");
        Map params = WeixinToolUtil.getMapByXml(body);
        if(params == null){
            result.put("return_code", "false");
            result.put("return_msg", "参数错误");
            return WeixinToolUtil.getXml(result);
        }
        String sign =WeixinToolUtil.getSign(params);
        String paramSign = (String) params.get("sign");
        if(paramSign == null ||!paramSign.equals(sign)){
            result.put("return_code", "false");
            result.put("return_msg", "签名错误");
            return WeixinToolUtil.getXml(result);
        }
        String outOrderNumber = (String) params.get("out_trade_no");
        if(!this.notifySuccess(outOrderNumber)){
            result.put("return_code", "false");
            return WeixinToolUtil.getXml(result);
        }
        return WeixinToolUtil.getXml(result);
    }


    @Override
    public void payPlatform(Long orderId, Double amount, Long userId,String payPassword) {
        User user = userMapper.getById(userId);
        if(user == null){
            throw new BizExecption("用户不存在");
        }
        if(!MD5Util.MD5(payPassword).equals(user.getPayPassword())){
            throw new BizExecption("支付密码错误");
        }
        if(user.getBalance() == null||amount>user.getBalance()){
            throw new BizExecption("余额不足");
        }
        Order order = orderMapper.getById(orderId);
        if(order ==null){
            throw new BizExecption("没有该订单");
        }
        if(order.getIsPayNew()&&!order.getStatus().equals(StatusConst.SIX)){
            throw new BizExecption("不是待服务，不能补差价");
        }
        if(!order.getIsPayNew()&&!order.getStatus().equals(StatusConst.FOUR)){
            throw new BizExecption("不是待支付，不能付款");
        }
        if(order.getIsPayNew()&&!amount.equals(order.getStayPayAmount())){
            throw new BizExecption("补差价金额与实际支付金额不符");
        }
        if(!order.getIsPayNew()&&!amount.equals(order.getTotalAmount())){
            throw new BizExecption("订单金额与实际支付金额不符");
        }
        UserBill userBill = userBillMapper.getByOrderId(order.getId(),StatusConst.TWO);
        if(userBill !=null){
            userBill.setBalance(amount);
            userBill.setPayMethod(StatusConst.THREE);
            userBill.setStatus(StatusConst.TWO);
            userBillMapper.update(userBill);
        }else{
            String intro = order.getStationName() ==null?order.getServiceTypeName():(order.getStationName()+"-"+order.getServiceTypeName());
            userBill = new UserBill(order.getStationId(),order.getUserId(), order.getServiceTypeType(),order.getServiceTypeName(),amount,order.getStationName(), StatusConst.TWO, StatusConst.THREE, orderId,order.getOrderNumber(),intro,StatusConst.TWO);
            userBillMapper.save(userBill);
        }
        user.setBalance(user.getBalance()-amount);
        userMapper.update(user);
        order.setPayMethod(StatusConst.THREE);
        paySuccess(order, amount);
    }


    @Override
    public WeixinInfo rechargeWeixin(Long userId, Double amount,String ip,Long rechargeComboId,String promotionCode) {
        UserBill userBill = new UserBill(userId,"我的钱包","充值",StatusConst.EIGHT,amount,StatusConst.ONE,StatusConst.TWO,StatusConst.ONE,"钱包充值",rechargeComboId,promotionCode);
        userBillMapper.save(userBill);
        String prepayid = WeixinUtil.payUnifiedorder(userBill.getIntro(), userBill.getOutOrderNumber(), amount, ip);
        WeixinInfo weixinInfo = new WeixinInfo(ConfigUtil.getWeixinAppId(),ConfigUtil.getWeixinPartnerId(),prepayid);
        String sign = WeixinToolUtil.getSign(MapUtil.beanToMap(weixinInfo));
        weixinInfo.setSign(sign);
        return weixinInfo;
    }


    @Override
    public List<UserBillMonthInfo> findUserBillMonthInfo(Long userId) {
        return userBillMapper.findUserBillMonthInfo(userId);
    }
    
}
