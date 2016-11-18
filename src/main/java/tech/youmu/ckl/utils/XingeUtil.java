package tech.youmu.ckl.utils;

import java.util.List;

import com.tencent.xinge.XingeApp;

public class XingeUtil {
    
    private static void pushMessage(String account,String content){
        Long accessId = ConfigUtil.getXingeAccessId();
        String secretKey = ConfigUtil.getXingeSecretKey();
        XingeApp.pushAccountAndroid(accessId, secretKey,"", content, account);
        XingeApp.pushAccountIos(accessId, secretKey, content, account, XingeApp.IOSENV_PROD);
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月9日上午10:13:28;</p>
     *	<p>Description: 同意退款推送;</p>
     *  @param userId
     */
    public static void askRefundOrderMessage(Long userId){
        pushMessage(userId.toString(), "你的退款已到账，请注意查收");
    }
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月26日下午3:06:35;</p>
     *	<p>Description: 提醒用户付款;</p>
     *  @param userId
     *  @param orderId
     */
    public static void warnUserPayMessage(Long userId) {
        pushMessage(userId.toString(), "你有一个订单需要付款");
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月26日下午3:08:21;</p>
     *	<p>Description: 邀请新用户信息;</p>
     *  @param userId
     *  @param orderId
     */
    public static void inviteMessage(Long userId) {
        pushMessage(userId.toString(), "有新用户加入你的分销计划");
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月26日下午3:11:24;</p>
     *	<p>Description:佣金发放 ;</p>
     *  @param userId
     *  @param orderId
     */
    public static void grantCommissionMessage(List<Long> userIds) {
        for(Long userId:userIds){
            pushMessage(userId.toString(), "你的分销奖励已经发放，请注意查收");
        }
    }
    
    
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月26日下午3:14:00;</p>
     *	<p>Description: 帖子消息;</p>
     *  @param userId
     */
    public static void topicMessage(Long userId) {
        pushMessage(userId.toString(), "你收到一条新消息");
    }
    
}
