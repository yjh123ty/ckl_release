/**
 * Project Name:ckl
 * File Name:LogUtils.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tech.youmu.ckl.constants.Global;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <p>Title:LogUtils</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月8日下午4:46:37</p>
 * <p>Description:TODO</p>
 */
public class LogUtils {
    
    private static LogUtils instance = new LogUtils();
    
    private static Logger logger;
    
    private static Gson gson = new GsonBuilder().setDateFormat(Global.DATE_TIME_FORMAT).setPrettyPrinting().serializeNulls().create();
    
    private LogUtils(){
        
    }
    
    public static LogUtils getInstance(Class<?> clz){
        logger = LoggerFactory.getLogger(clz);
        return instance;
    }
    
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月10日上午10:27:52;</p>
     *	<p>Description: 使用json格式化答应对象;</p>
     *  @param bean
     */
    public void debugBean(Object bean){
        
       try {
           String json = gson.toJson(bean);
            logger.debug("debug print json bean info start-------->");
            logger.debug(json);
            logger.debug("<---------debug print json bean info end");
    } catch (Exception e) {    
        logger.error("对象解析异常", e);
    }
    }
    
    public void debug(String msg){
        logger.debug(msg);
    }
}
