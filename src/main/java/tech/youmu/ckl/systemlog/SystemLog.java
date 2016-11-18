/**
 * Project Name:ckl
 * File Name:SystemLog.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.systemlog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title:SystemLog</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月23日下午4:22:08</p>
 * <p>
 *      Description: 系统日志注解 该注解贴在service的方法上面 使用aop来判断是否有该注解 有该注解的操作方法会被记录并保存到数据库的sys_log 表里面
 * </p>
 */
@Target(value={ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLog {
    public enum Model{
        SIMPLE,
        DETAIL
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月24日上午9:26:21;</p>
     *	<p>Description: 操作方法的名字;</p>
     *  @return
     */
    String value() default "";
    
    /**
     * 
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月24日上午9:26:37;</p>
     *	<p>Description: 插入日志的级别;</p>
     *  @return
     */
    Model model() default Model.SIMPLE;
}
