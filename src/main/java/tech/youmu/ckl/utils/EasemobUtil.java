package tech.youmu.ckl.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class EasemobUtil {
    
    private static String getUrl(String method){
        StringBuffer buffer = new StringBuffer();
        buffer.append("https://a1.easemob.com/");
        buffer.append(ConfigUtil.getEasemobOrgName()).append("/");
        buffer.append(ConfigUtil.getEasemobAppName()).append("/");
        buffer.append(method);
        return buffer.toString();
        
    }
    
    private  static String  getToken(){
        JSONObject json = new JSONObject();
        json.put("grant_type", "client_credentials");
        json.put("client_id", ConfigUtil.getEasemobClientId());
        json.put("client_secret", ConfigUtil.getEasemobClientSecret());
        String result = HttpUtil.postBody(getUrl("token"), json.toString());
        JSONObject jsonResult= JSONObject.fromObject(result);
        if(jsonResult !=null){
            return jsonResult.getString("access_token");
        }else{
            return null;
        }
    }
   
    public  static String  registerUser(Long userId){
        Map<String,Object> headers = new HashMap();
        headers.put("Authorization", "Bearer "+getToken());
        JSONObject json = new JSONObject();
        json.put("username", userId);
        json.put("password", MD5Util.MD5(userId.toString()));
        String result = HttpUtil.postBody(getUrl("users"),headers, json.toString());
        return result;
    }
}
