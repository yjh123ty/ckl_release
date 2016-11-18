package tech.youmu.ckl.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderUtil {
    
    private static Logger log=LoggerFactory.getLogger(OrderUtil.class);
    
    

    
    public static String createOrderNumber(Long userId){
        StringBuffer buffer = new StringBuffer();
        buffer.append("o").append(userId).append(getDate());
        return buffer.toString();
        
    }
    
    private static String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        return simpleDateFormat.format(date);

    }
    
    public static void main(String[] args) {
        System.out.println(createOrderNumber(1l));
        
    }
    
    
}
