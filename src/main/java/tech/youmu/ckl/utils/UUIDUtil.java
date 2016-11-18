package tech.youmu.ckl.utils;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UUIDUtil {
    
    private static Logger log=LoggerFactory.getLogger(UUIDUtil.class);

   
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
