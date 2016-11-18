package tech.youmu.ckl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeixinToolUtil {
    
    private static Logger log=LoggerFactory.getLogger(WeixinToolUtil.class);
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月28日上午11:21:25;</p>
     *	<p>Description: 获取签名;</p>
     */
    public static String getSign(Map<String,Object> params){
        Collection<String> keyset= params.keySet();  
        List<String> keys = new ArrayList<String>(keyset);  
        //对key键值按字典升序排序  
        Collections.sort(keys);  
        StringBuffer buffer = new StringBuffer();
        for(String key:keys){
            if("package_".equals(key)){
                log.info(key+"="+params.get(key));
                buffer.append("package").append("=").append(params.get(key)).append("&");
                continue;
            }
            if("sign".equals(key)){
                continue;
            }
            log.info(key+"="+params.get(key));
            buffer.append(key).append("=").append(params.get(key)).append("&");
        }
        buffer.append("key").append("=").append(ConfigUtil.getWeixinKey());
        log.info("buffer:"+buffer.toString());
        return MD5Util.MD5(buffer.toString()).toUpperCase();
    }
    
    public static String getXml(Map<String,Object> map){
        Document document = DocumentHelper.createDocument();
        // root element
        Element rootElement = document.addElement("xml");
        for(String key:map.keySet()){
            Element nameElement = rootElement.addElement(key);
            nameElement.setText(String.valueOf(map.get(key)));
        }
        log.info("xml:"+document.asXML());
        return document.asXML();
    }
    
    public static Map<String,String> getMapByXml(String xml) {
        Map<String, String> map = new HashMap<String, String>(); 
        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e1) {
            e1.printStackTrace();
            return null;
        }
        document.getName();
        Element root = document.getRootElement(); 
        for (Iterator iterator = root.elementIterator(); iterator.hasNext();) { 
            Element e = (Element) iterator.next(); 
            map.put(e.getName(), e.getText()); 
        } 
        return map; 
    }
    
    public static String HttpPostSSLBody(String url, String param) {
        InputStream instream = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = null;
        String responseContent = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            instream = ConfigUtil.class.getResourceAsStream("/apiclient_cert.p12");
            keyStore.load(instream, ConfigUtil.getWeixinPartnerId().toCharArray());
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, ConfigUtil.getWeixinPartnerId().toCharArray()).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            HttpPost httpPost = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(param, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                responseContent = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (httpclient != null)
                        httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseContent;
    }
}
