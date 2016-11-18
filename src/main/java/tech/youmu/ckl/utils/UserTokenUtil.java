package tech.youmu.ckl.utils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tech.youmu.ckl.app.vo.UserTokenInfo;

public class UserTokenUtil
{
	private static Logger log=LoggerFactory.getLogger(UserTokenUtil.class);
    private static Map<String, UserTokenInfo> userTokenMap = new ConcurrentHashMap<String, UserTokenInfo>();
    
    
    
    public static void removeUserTokenInfo(Long userId){
    	for(String token:userTokenMap.keySet()){
    		UserTokenInfo userTokenInfo = userTokenMap.get(token);
    		if(userTokenInfo !=null&&userId.equals(userTokenInfo.getUserId())){
    			userTokenMap.remove(token);
    		}
    	}
    }
    
    
    private static UserTokenInfo createUserTokenInfo(Long userId){
    	String token = UUID.randomUUID().toString();
		String key = UUID.randomUUID().toString();
		UserTokenInfo userTokenInfo = new UserTokenInfo(userId,token,key);
		return userTokenInfo;
    }
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年8月19日下午1:36:31;</p>
     *	<p>Description: 绑定token;</p>
     *  @param userId
     */
    public static UserTokenInfo bindUserTokenInfo(Long userId){
    	removeUserTokenInfo(userId);
    	UserTokenInfo userTokenInfo = createUserTokenInfo(userId);
    	userTokenMap.put(userTokenInfo.getToken(), userTokenInfo);
    	return userTokenInfo;
    }
    
    public static UserTokenInfo getUserTokenInfo(String token){
    	return userTokenMap.get(token);
    }
    
    

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年8月19日下午2:20:01;</p>
     *	<p>Description: key+timestamp+token mad5;</p>
     *  @param signature
     *  @param timestamp
     *  @param token
     *  @param key
     *  @return
     */
	public static boolean verifySignature(String signature, String timestamp, String token, String key) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("timestamp=").append(timestamp).append("&token=").append(token).append("&key=").append(key);
		String sign = MD5Util.MD5(buffer.toString());
		if(sign.equals(signature.toUpperCase())){
			return true;
		}
		return false;
	}
	
	public static void removeUserTokenInfo(String token){
    	userTokenMap.remove(token);
    }
    
}
