package tech.youmu.ckl.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlipayToolUtil {
    
    private static Logger log=LoggerFactory.getLogger(AlipayToolUtil.class);
    
    public static String getAlipayString(Map<String,String> params){
        Collection<String> keyset= params.keySet();  
        List<String> keys = new ArrayList<String>(keyset);  
        //对key键值按字典升序排序  
        Collections.sort(keys);  
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = null;
            try {
                value = URLEncoder.encode(params.get(key), AlipayUtil.CHARSET);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            buffer.append((i == 0 ? "" : "&") + key + "=" + value);
        }
        return  buffer.toString();
    }
    
}
