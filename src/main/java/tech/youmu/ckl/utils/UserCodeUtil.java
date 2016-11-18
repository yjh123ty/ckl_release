package tech.youmu.ckl.utils;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserCodeUtil {
    
    private static Logger log=LoggerFactory.getLogger(UserCodeUtil.class);
    
    private static Set<String> userCodeSet = new CopyOnWriteArraySet<String>();

    
    public static String getUserCode(){
       while(true){
           String code = createUserCode(4);
           if(userCodeSet.contains(code)){
               log.info("重复："+code);
               continue;
           }
           userCodeSet.add(code);
           log.info("返回："+code);
           return code;
       }
    }
    
    private static String createUserCode(int length){
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
    
    public static void createUserCodeSet(List<String> codes){
        log.info("codes size:"+codes.size());
        userCodeSet.addAll(codes);
        log.info("set size:"+userCodeSet.size());
    }
    
    
}
