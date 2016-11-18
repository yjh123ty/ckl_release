package tech.youmu.ckl.utils;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tech.youmu.ckl.utils.ConfigUtil;
import tech.youmu.ckl.utils.HttpUtil;
import tech.youmu.ckl.utils.MapUtil;
import tech.youmu.ckl.utils.UUIDUtil;

public class WeixinUtil {
    
    private static Logger log=LoggerFactory.getLogger(WeixinUtil.class);
    
    
    private static final String TRADE_TYPE="APP";
    
    private static final String UNIFIEDORDER_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
    
    private static final String REFUND_URL="https://api.mch.weixin.qq.com/secapi/pay/refund";
    
   
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月28日上午11:21:25;</p>
     *	<p>Description: 统一下单;</p>
     */
    public static String payUnifiedorder(String name,String outOrderNumber,Double amount,String ip){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("appid", ConfigUtil.getWeixinAppId());
        params.put("mch_id", ConfigUtil.getWeixinPartnerId());
        params.put("nonce_str", UUIDUtil.getUUID());
        params.put("body", name);
        params.put("out_trade_no", outOrderNumber);
        int totalFee = (int) (amount*100);
        params.put("total_fee", 1);
        params.put("spbill_create_ip", ip);
        params.put("notify_url",ConfigUtil.getWeixinNotifyUrl());
        params.put("trade_type", TRADE_TYPE);
        String sign = WeixinToolUtil.getSign(params);
        params.put("sign", sign);
        String xml = HttpUtil.postBody(UNIFIEDORDER_URL, WeixinToolUtil.getXml(params));
        Map<String,String> resultMap = null;
        resultMap =  WeixinToolUtil.getMapByXml(xml);
        if(resultMap == null){
            return null;
        }
        String returnCode = resultMap.get("return_code");
        if(returnCode ==null ||!returnCode.equals("SUCCESS")){
            return null;
        }
        String prepayId = resultMap.get("prepay_id");
        log.info("prepayId:"+prepayId);
        return prepayId;
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月28日下午5:49:36;</p>
     *	<p>Description: 微信退款;</p>
     *  @param outOrderNumber
     *  @param paidAmount
     *  @param refundAmount
     *  @return
     */
    public static boolean payRefund(String outOrderNumber,Double paidAmount,Double refundAmount){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("appid", ConfigUtil.getWeixinAppId());
        params.put("mch_id", ConfigUtil.getWeixinPartnerId());
        params.put("nonce_str", UUIDUtil.getUUID());
        params.put("out_trade_no", outOrderNumber);
        params.put("out_refund_no", UUIDUtil.getUUID());
//        params.put("total_fee", paidAmount*100);
//        params.put("refund_fee", refundAmount*100);
        params.put("total_fee", 1);
        params.put("refund_fee", 1);
        params.put("op_user_id", ConfigUtil.getWeixinPartnerId());
        String sign = WeixinToolUtil.getSign(params);
        params.put("sign", sign);
        String xml = WeixinToolUtil.HttpPostSSLBody(REFUND_URL, WeixinToolUtil.getXml(params));
        log.info(xml);
        if(xml == null){
            return false;
        }
        Map<String, String> result = WeixinToolUtil.getMapByXml(xml);
        if(result == null){
            return false;
        }
        if(!"SUCCESS".equals(result.get("result_code"))){
            return false;
        }
        return true;
    }
}
