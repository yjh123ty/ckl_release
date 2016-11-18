package tech.youmu.ckl.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tech.youmu.ckl.exception.BizExecption;

public class CheckCodeUtil
{
	private static Logger log=LoggerFactory.getLogger(CheckCodeUtil.class);
    private static Map<String, String> checkCodeMap = new ConcurrentHashMap<String, String>();
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年8月23日下午6:06:51;</p>
     *	<p>Description: TODO;</p>
     *  @param phone
     *  @return
     */
    public static String newCheckCode(String phone)
    {
        StringBuffer checkCode = new StringBuffer();
        while(checkCode.length() < 6)
        {
            checkCode.append((int)(Math.random() * 10));
        }
        checkCodeMap.put(phone, checkCode + "-" + System.currentTimeMillis());
        log.info("code:"+checkCode.toString());
        return checkCode.toString();
    }
    
    
    public static void removeCheckCode(String phone){
    	checkCodeMap.remove(phone);
    }
    
    
    public static String getCheckCode(String phone){
    	if(null == checkCodeMap || checkCodeMap.isEmpty()){
    		return null;
    	}
    	String checkCode = CheckCodeUtil.checkCodeMap.get(phone);
    	if(checkCode!=null){
    		return checkCode;
    	}else{
    		return null;
    	}
    	
    }
    
    
    public static boolean verify(String phone, String verify) {
		String codeAndTime = (String) CheckCodeUtil.getCheckCode(phone);
		if (codeAndTime == null) {
			throw new BizExecption("没有获取验证码");
		}
		String oldCode = String.valueOf(codeAndTime.split("-")[0]);
		Long oldTime = Long.valueOf(codeAndTime.split("-")[1]);
		Long newTime = System.currentTimeMillis();
		if ((newTime - oldTime) / 1000 > 300) // 5min
		{
			throw new BizExecption("验证码失效");
		}
		if (!oldCode.equals(verify)) {
			throw new BizExecption("验证码错误");
		}
		return true;
	}
}
