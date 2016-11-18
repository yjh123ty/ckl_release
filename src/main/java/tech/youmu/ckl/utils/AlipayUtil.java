package tech.youmu.ckl.utils;

import java.util.HashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;

import net.sf.json.JSONObject;
import tech.youmu.ckl.exception.BizExecption;

public class AlipayUtil {

    public static final String CHARSET = "utf-8";
    
    public static final String SIGN_TYPE = "RSA";
    
    public static final String PAYAMOUNT_METHOD = "alipay.trade.app.pay";
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月29日下午5:55:36;</p>
     *	<p>Description: 支付宝付款;</p>
     *  @return
     */
    public static String payAmount(String tilte,String outOrderNumber,Double amount){
        Map<String,String> params = new HashMap<>();
        params.put("app_id", ConfigUtil.getAlipayAppId());
        params.put("method", PAYAMOUNT_METHOD);
        params.put("charset", CHARSET);
        params.put("sign_type", SIGN_TYPE);
        params.put("timestamp", DateUtil.getDate());
        params.put("version", "1.0");
        params.put("notify_url", ConfigUtil.getAlipayNotifyUrl());
        JSONObject content = new JSONObject();
        content.put("subject", tilte);
        content.put("out_trade_no", outOrderNumber);
        content.put("total_amount", 0.01);
        content.put("product_code", "QUICK_MSECURITY_PAY");
        params.put("biz_content", content.toString());
        String sign = null;
        try {
            sign = AlipaySignature.rsaSign(params, ConfigUtil.getAlipayPrivateKey(), CHARSET);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(sign == null){
            throw new BizExecption("支付失败");
        }
        params.put("sign", sign);
        return AlipayToolUtil.getAlipayString(params);
    }
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月29日下午5:52:23;</p>
     *	<p>Description: 验证签名;</p>
     *  @param paramsMap
     *  @return
     */
    public static boolean signVerify(Map<String, String> paramsMap){
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, ConfigUtil.getAlipayPublicKey(), CHARSET);
            return signVerified;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月29日上午9:22:14;</p>
     *	<p>Description: 退款;</p>
     */
    public static boolean payRefund(String outOrderNumber,Double refundAmount){
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",ConfigUtil.getAlipayAppId(),ConfigUtil.getAlipayPrivateKey(),"json","utf-8",ConfigUtil.getAlipayPublicKey());
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        JSONObject json = new JSONObject();
        json.put("out_trade_no", outOrderNumber);
//        json.put("refund_amount", refundAmount);
        json.put("refund_amount", 0.01);
        request.setBizContent(json.toString());
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return false;
        }
        if(response == null){
            return false;
        }
        if(response.isSuccess()){
            return true;
        } else {
            return false;
        }
    }
}
