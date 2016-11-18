package tech.youmu.ckl.utils;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderCodeUtil {
    
    private static Logger log=LoggerFactory.getLogger(OrderCodeUtil.class);
    
    private static Set<String> orderCodeSet = new CopyOnWriteArraySet<String>();

    
    public static String getOrderCode(){
       while(true){
           String code = createOrderCode(6);
           if(orderCodeSet.contains(code)){
               log.info("重复："+code);
               continue;
           }
           orderCodeSet.add(code);
           log.info("返回："+code);
           return code;
       }
    }
    
    private static String createOrderCode(int length){
        String base="abcdefghigkmnpkrstuvwxyz0123456789";
        StringBuffer code = new StringBuffer();
        Random random=new Random();
        while(code.length() < length)
        {
            int number=random.nextInt(base.length());
            code.append(base.charAt(number));
        }
        return code.toString();
    }
    
    public static void createOrderCodeSet(List<String> codes){
        log.info("codes size:"+codes.size());
        orderCodeSet.addAll(codes);
        log.info("set size:"+orderCodeSet.size());
    }
    
    
}
