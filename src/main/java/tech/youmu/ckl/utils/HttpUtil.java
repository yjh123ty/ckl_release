package tech.youmu.ckl.utils;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
//import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class HttpUtil {
    
    public static String getHttp(String url, Map<String, String> params) {
        String responseMsg = "";

        // 1.构造HttpClient的实例  
        HttpClient httpClient = new HttpClient();

        // 用于测试的http接口的url  
        StringBuffer buffer = new StringBuffer("?");
        for (String key : params.keySet()) {
            buffer.append(key).append("=").append(params.get(key)).append("&");
        }
        String paramStr = buffer.toString();
        String urlParam = url+ paramStr.substring(0, paramStr.length() - 1);
        // 2.创建GetMethod的实例  
        GetMethod getMethod = new GetMethod(urlParam);

        // 使用系统系统的默认的恢复策略  
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        
        for (String key : params.keySet()) {
            getMethod.getParams().setParameter(key, params.get(key));
        }
         
        try {
            //3.执行getMethod,调用http接口  
            httpClient.executeMethod(getMethod);

            //4.读取内容  
            byte[] responseBody = getMethod.getResponseBody();

            //5.处理返回的内容  
            responseMsg = new String(responseBody);

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.释放连接  
            getMethod.releaseConnection();
        }
        System.out.println(responseMsg);
        return responseMsg;
    }
    
    public static String getHttp(String url, Map<String, String> headers, Map<String, String> params) {
        String responseMsg = "";

        // 1.构造HttpClient的实例  
        HttpClient httpClient = new HttpClient();

        // 用于测试的http接口的url  
        StringBuffer buffer = new StringBuffer("?");
        for (String key : params.keySet()) {
            buffer.append(key).append("=").append(params.get(key)).append("&");
        }
        String paramStr = buffer.toString();
        String urlParam = url+ paramStr.substring(0, paramStr.length() - 1);
        // 2.创建GetMethod的实例  
        GetMethod getMethod = new GetMethod(urlParam);

        // 使用系统系统的默认的恢复策略  
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        for (String key : headers.keySet()) {
            getMethod.addRequestHeader(key, headers.get(key));
        }
        
        for (String key : params.keySet()) {
            getMethod.getParams().setParameter(key, params.get(key));
        }
         
        try {
            //3.执行getMethod,调用http接口  
            httpClient.executeMethod(getMethod);

            //4.读取内容  
            byte[] responseBody = getMethod.getResponseBody();

            //5.处理返回的内容  
            responseMsg = new String(responseBody);

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.释放连接  
            getMethod.releaseConnection();
        }
        System.out.println(responseMsg);
        return responseMsg;
    }

    public static String postHttp(String url, Map<String, Object> params) {
        String responseMsg = "";

        //1.构造HttpClient的实例  
        HttpClient httpClient = new HttpClient();

        httpClient.getParams().setContentCharset("utf-8");

        //2.构造PostMethod的实例  
        PostMethod postMethod = new PostMethod(url);

        //3.把参数值放入到PostMethod对象中  
        for (String key : params.keySet()) {
            postMethod.addParameter(key, String.valueOf(params.get(key)));
            System.out.println("key= " + key + " and value= " + params.get(key));
        }

        try {
            // 4.执行postMethod,调用http接口  
            httpClient.executeMethod(postMethod);//200  

            //5.读取内容  
            responseMsg = postMethod.getResponseBodyAsString().trim();

            //6.处理返回的内容  

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //7.释放连接  
            postMethod.releaseConnection();
        }
        System.out.println(responseMsg);
        return responseMsg;
    }
    
    
    public static String postHttp(String url, Map<String, Object> headers, Map<String, Object> params) {
        String responseMsg = "";

        //1.构造HttpClient的实例  
        HttpClient httpClient = new HttpClient();

        httpClient.getParams().setContentCharset("utf-8");

        //2.构造PostMethod的实例  
        PostMethod postMethod = new PostMethod(url);

        for (String key : headers.keySet()) {
            postMethod.addRequestHeader(key, String.valueOf(headers.get(key)));
        }
        //3.把参数值放入到PostMethod对象中  
        for (String key : params.keySet()) {
            Object value = params.get(key);
            if(value instanceof  Object[]){
                Object[] array = (Object[]) value;
                for(int i=0;i<array.length;i++){
                    postMethod.addParameter(key, String.valueOf(array[i]));
                }
            }else{
                postMethod.addParameter(key, String.valueOf(params.get(key)));
            }
            System.out.println("key= " + key + " and value= " + params.get(key));
        }

        try {
            // 4.执行postMethod,调用http接口  
            httpClient.executeMethod(postMethod);//200  

            //5.读取内容  
            responseMsg = postMethod.getResponseBodyAsString().trim();

            //6.处理返回的内容  

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //7.释放连接  
            postMethod.releaseConnection();
        }
        System.out.println(responseMsg);
        return responseMsg;
    }
    
    
    public static String postBody(String url, String param) {
        String responseMsg = "";

        //1.构造HttpClient的实例  
        HttpClient httpClient = new HttpClient();

        httpClient.getParams().setContentCharset("utf-8");

        //2.构造PostMethod的实例  
        PostMethod postMethod = new PostMethod(url);

        postMethod.setRequestBody(param);
        try {
            // 4.执行postMethod,调用http接口  
            httpClient.executeMethod(postMethod);//200  

            //5.读取内容  
            responseMsg = postMethod.getResponseBodyAsString().trim();

            //6.处理返回的内容  

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //7.释放连接  
            postMethod.releaseConnection();
        }
        System.out.println(responseMsg);
        return responseMsg;
    }
    
    
    public static String postBody(String url,Map<String, Object> headers, String param) {
        String responseMsg = "";

        //1.构造HttpClient的实例  
        HttpClient httpClient = new HttpClient();

        httpClient.getParams().setContentCharset("utf-8");

        //2.构造PostMethod的实例  
        PostMethod postMethod = new PostMethod(url);
        
        for (String key : headers.keySet()) {
            postMethod.addRequestHeader(key, String.valueOf(headers.get(key)));
        }

        postMethod.setRequestBody(param);
        try {
            // 4.执行postMethod,调用http接口  
            httpClient.executeMethod(postMethod);//200  

            //5.读取内容  
            responseMsg = postMethod.getResponseBodyAsString().trim();

            //6.处理返回的内容  

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //7.释放连接  
            postMethod.releaseConnection();
        }
        System.out.println(responseMsg);
        return responseMsg;
    }
    
    public static String postBody(String url,Map<String, Object> headers,Map<String, Object> params, String body) {
        String responseMsg = "";

        //1.构造HttpClient的实例  
        HttpClient httpClient = new HttpClient();

        httpClient.getParams().setContentCharset("utf-8");

        //2.构造PostMethod的实例  
        PostMethod postMethod = new PostMethod(url);
        
        if(headers !=null){
            for (String key : headers.keySet()) {
                postMethod.addRequestHeader(key, String.valueOf(headers.get(key)));
            }
        }
        if(params !=null){
            for (String key : params.keySet()) {
                postMethod.addParameter(key, String.valueOf(params.get(key)));
                System.out.println("key= " + key + " and value= " + params.get(key));
            }
        }
        if(body !=null){
            postMethod.setRequestBody(body);
        }
        try {
            // 4.执行postMethod,调用http接口  
            httpClient.executeMethod(postMethod);//200  

            //5.读取内容  
            responseMsg = postMethod.getResponseBodyAsString().trim();

            //6.处理返回的内容  

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //7.释放连接  
            postMethod.releaseConnection();
        }
        System.out.println("result:"+responseMsg);
        return responseMsg;
    }
  
}