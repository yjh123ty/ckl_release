package tech.youmu.ckl.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class RongUtil {
    
    private static Logger log=LoggerFactory.getLogger(RongUtil.class);
    
    private static final String BASE_URL="http://api.cn.ronghub.com";
    private static final String APPKEY = "RC-App-Key";
    private static final String NONCE = "RC-Nonce";
    private static final String TIMESTAMP = "RC-Timestamp";
    private static final String SIGNATURE = "RC-Signature";
    
    public static String getUrl(String method){
        StringBuffer buffer = new StringBuffer(BASE_URL);
        buffer.append(method).append(".json");
        return buffer.toString();
        
    }

    public static String getSign(String nonce,String timestamp){
        StringBuilder toSign = new StringBuilder(ConfigUtil.getRongAppSecret()).append(nonce)
                .append(timestamp);
        String sign = SHA1Util.hexSHA1(toSign.toString());
        return sign;
    }
    
    public static String getToken(Long userId,String userName,String headIcon){
        Map<String,Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("userName", userName);
        params.put("portraitUri", headIcon);
        String url=getUrl("/user/getToken");
        String result = HttpUtil.postHttp(url, getHeaders(), params);
        log.info(result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        String token =  jsonObject.getString("token");
        log.info(token);
        return token;
        
    }

    private static Map<String, Object> getHeaders() {
        String nonce = String.valueOf(Math.random() * 1000000);
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        Map<String,Object> headers = new HashMap();
        headers.put(APPKEY, ConfigUtil.getRongAppKey());
        headers.put(NONCE, nonce);
        headers.put(TIMESTAMP, timestamp);
        headers.put(SIGNATURE, getSign(nonce, timestamp));
        return headers;
    }
    
    public static String createGroup(String[] userIds){
        Map<String,Object> params = new HashMap<>();
        params.put("userId", userIds);
        String groupId = UUIDUtil.getUUID();
        log.info("groupId:"+groupId);
        params.put("groupId", groupId);
        params.put("groupName", groupId);
        String url=getUrl("/group/create");
        String result = HttpUtil.postHttp(url, getHeaders(), params);
        log.info(result);
        return groupId;
    }
    
    public static void quitGroup(String userId,String groupId){
        if(userId == null||groupId == null){
            return;
        }
        Map<String,Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("groupId", groupId);
        String url=getUrl("/group/quit");
        String result = HttpUtil.postHttp(url, getHeaders(), params);
        log.info(result);
    }

    public static void quitGroup(String[] userIds, String groupId) {
        if(userIds == null||userIds.length==0||groupId == null){
            return;
        }
        Map<String,Object> params = new HashMap<>();
        params.put("userId", userIds);
        params.put("groupId", groupId);
        String url=getUrl("/group/quit");
        String result = HttpUtil.postHttp(url, getHeaders(), params);
        log.info(result);
    }
}
