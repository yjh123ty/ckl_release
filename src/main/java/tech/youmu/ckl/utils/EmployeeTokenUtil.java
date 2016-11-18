package tech.youmu.ckl.utils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tech.youmu.ckl.app.vo.EmployeeTokenInfo;

public class EmployeeTokenUtil
{
	private static Logger log=LoggerFactory.getLogger(EmployeeTokenUtil.class);
    private static Map<String, EmployeeTokenInfo> employeeTokenMap = new ConcurrentHashMap<String, EmployeeTokenInfo>();
    
    
    
    private static void removeEmployeeTokenInfo(Long employeeId){
    	for(String token:employeeTokenMap.keySet()){
    		EmployeeTokenInfo employeeTokenInfo = employeeTokenMap.get(token);
    		if(employeeTokenInfo !=null&&employeeId.equals(employeeTokenInfo.getEmployeeId())){
    			employeeTokenMap.remove(token);
    		}
    	}
    }
    
    
    private static EmployeeTokenInfo createEmployeeTokenInfo(Long employeeId){
    	String token = UUID.randomUUID().toString();
		String key = UUID.randomUUID().toString();
		EmployeeTokenInfo employeeTokenInfo = new EmployeeTokenInfo(employeeId,token,key);
		return employeeTokenInfo;
    }
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年8月19日下午1:36:31;</p>
     *	<p>Description: 绑定token;</p>
     *  @param employeeId
     */
    public static EmployeeTokenInfo bindEmployeeTokenInfo(Long employeeId){
    	removeEmployeeTokenInfo(employeeId);
    	EmployeeTokenInfo employeeTokenInfo = createEmployeeTokenInfo(employeeId);
    	employeeTokenMap.put(employeeTokenInfo.getToken(), employeeTokenInfo);
    	return employeeTokenInfo;
    }
    
    public static EmployeeTokenInfo getEmployeeTokenInfo(String token){
    	return employeeTokenMap.get(token);
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
	
	public static void removeEmployeeTokenInfo(String token){
    	employeeTokenMap.remove(token);
    }
    
}
