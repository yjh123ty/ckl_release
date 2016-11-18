package tech.youmu.ckl.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.aliyun.api.gateway.demo.Client;
import com.aliyun.api.gateway.demo.Request;
import com.aliyun.api.gateway.demo.Response;
import com.aliyun.api.gateway.demo.constant.HttpHeader;
import com.aliyun.api.gateway.demo.enums.Method;

/**
 * 易源接口 http://www.showapi.com
 * 昆明秀派科技有限公司 制作
 */
public class AliyunRequestUtil {
	private     String app_key = ""; //APP KEY
	private     String app_secret = "";// APP密钥
	private int connectTimeout = 3000;//3秒
	private int readTimeout = 15000;//15秒
	private String url;
	private String char_set="utf-8";
	private Map ret_headers;
	Map<String, String> headers = new HashMap<String, String>();
	private Map<String,String> textMap=new HashMap<String, String>();
	private Map<String,List<String>> res_headtMap=new HashMap<String,List<String>>();//返回时的Map
	public AliyunRequestUtil(String app_key,String app_secret,String url){
		this.app_key=app_key;
		this.app_secret=app_secret;
		this.url=url;
		headers.put(HttpHeader.HTTP_HEADER_ACCEPT, "application/json");
	}
	
	
	
	public Map<String, List<String>> getRes_headtMap() {
		return res_headtMap;
	}
	public void setRes_headtMap(Map<String, List<String>> res_headtMap) {
		this.res_headtMap = res_headtMap;
	}

	
	public String getChar_set() {
		return char_set;
	}


	public void setChar_set(String char_set) {
		this.char_set = char_set;
	}



	public Map<String, String> getTextMap() {
		return textMap;
	}
	public void setTextMap(Map<String, String> textMap) {
		this.textMap = textMap;
	}
	public String getUrl() {
		return url;
	}
	 
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public int getReadTimeout() {
		return readTimeout;
	}
	public AliyunRequestUtil setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
		return this;
	}
	public AliyunRequestUtil setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
		return this;
	}
	 
	
	public Map getRet_headers() {
		return ret_headers;
	}



	public void setRet_headers(Map ret_headers) {
		this.ret_headers = ret_headers;
	}



	public   String buildQuery(Map  params )   {
		if (params == null || params.isEmpty()) {
			return null;
		}
		StringBuilder query = new StringBuilder();
		Set<Entry<String, String>> entries = params.entrySet();
		boolean hasParam = false;
		try {
			for (Entry<String, String> entry : entries) {
				String name = entry.getKey();
				String value = entry.getValue();
				// 忽略参数名为空的参数
				if(name!=null&&name.length()>0 ){
					if (hasParam) {
						query.append("&");
					} else {
						hasParam = true;
					}
					query.append(name).append("=").append(value);
				}
			}
		} catch ( Exception e) {
			e.printStackTrace();
		}

		return query.toString();
	}

	/**
	 * 添加post体的字符串参数
	 */
	public void addTextPara(String key,String value) {
		this.textMap.put(key,value);
	}
	
	
	public String post() throws  Exception   {
		byte b[] =postAsByte();
	        String str=new String(b,"utf-8");
		return str;
	}
	
	public byte[] postAsByte() throws Exception   {
		int ind=url.lastIndexOf("/");
		String host=url.substring(0,ind);
		String path=url.substring(ind);
		Request request = new Request(Method.POST_FORM, host,path, app_key, app_secret, connectTimeout);
		request.setHeaders(headers);
		request.setBodys(textMap);
		Response response =  Client.execute(request);
		ret_headers=response.getHeaders();
		return response.getContent_byte();
	}
	
	
	


	public String get() throws Exception   {
		byte b[] =getAsByte();
	        String str=new String(b,char_set);
		return str;
	}
	
	public byte[] getAsByte() throws Exception   {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(HttpHeader.HTTP_HEADER_ACCEPT, "application/json");
		int ind=url.lastIndexOf("/");
		String host=url.substring(0,ind);
		String path=url.substring(ind);
		
		Request request = new Request(Method.GET, host,path, app_key, app_secret, connectTimeout);
		request.setQuerys(this.textMap);
		request.setHeaders(headers);
		Response response =  Client.execute(request);
		ret_headers=response.getHeaders();
		return response.getContent_byte();
	}
 
}