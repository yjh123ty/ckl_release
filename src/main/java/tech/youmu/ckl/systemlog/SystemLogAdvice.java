/**
 * Project Name:ckl
 * File Name:SystemLogAdvice.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.systemlog;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.youmu.ckl.domain.SysLog;
import tech.youmu.ckl.service.ISysLogService;
import tech.youmu.ckl.systemlog.SystemLog.Model;
import tech.youmu.ckl.utils.UserContext;


/**
 * <p>Title:SystemLogAdvice</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月23日下午4:28:07</p>
 * <p>Description: 系统日志 实现类</p>
 */
@Component
@Aspect
public class SystemLogAdvice {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ISysLogService sysLogService;
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月23日下午4:32:47;</p>
     *	<p>Description: 处理系统的日志;</p>
     */
    @Pointcut("execution(* tech.youmu.ckl.service..*.*(..))")
    public void pointCut(){
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月23日下午4:34:15;</p>
     *	<p>Description: 添加一条日志记录到数据库;</p>
     *  @param joinPoint
     *  @throws Exception
     */
    @Around("pointCut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Exception{
        Throwable ex = null;
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Exception e) {
            throw e;
        } catch (Throwable e) {
			e.printStackTrace();
		}finally {
            saveLog(obj, joinPoint, ex);
        }
        return obj;
    }
    
    public void saveLog(Object obj, ProceedingJoinPoint joinPoint, Throwable e) throws Exception {
        String oprName = null;
        // 获取当前服务的class
        Class<?> serviceClass = joinPoint.getTarget().getClass();
        // 获取执行的方法  
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获取该方法的 注解
       Method method = serviceClass.getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
       SystemLog methodAnnotation = method.getAnnotation(SystemLog.class);
       SystemLog classAnnotation = serviceClass.getAnnotation(SystemLog.class);
       if(methodAnnotation == null && classAnnotation == null) {
           return;
       }
       Model model = methodAnnotation == null ? classAnnotation.model() : methodAnnotation.model();
       // 创建日志对象
       SysLog sysLog = new SysLog();
       String prefix = classAnnotation == null ? null : classAnnotation.value();
       if(StringUtils.isBlank(prefix)){
           prefix = serviceClass.getName();
       }
       String suffix = methodAnnotation== null ? null : methodAnnotation.value();
       if(StringUtils.isBlank(suffix)){
           suffix = method.getName();
       }
       oprName = prefix +":"+suffix;
       sysLog.setOprName(oprName);
       sysLog.setIsException(e != null);
       sysLog.setOprTime(new Date());
       sysLog.setOprUserMobile(UserContext.getUser().getMobile());
       sysLog.setOprUserIp(UserContext.getRequest().getRemoteAddr());
       sysLog.setRequestUri(UserContext.getRequest().getRequestURI());
       sysLog.setUserAgent(UserContext.getRequest().getHeader("user-agent"));
        // 调用日志服务插入一条日志对象
       StringBuilder builder = new StringBuilder();
       switch (model) {
            //只记录方法和异常 
            case SIMPLE:
                builder.
                append(" 访问的方法: ")
                .append(methodSignature.toString());
                if(e != null) {
                    builder.append(" 异常: ");
                    builder.append(e.getLocalizedMessage());
                }
                break;
                
            // 记录各种参数
            case DETAIL:
                builder.
                append(" 访问的方法: ")
                .append(methodSignature.toString());
                Object[] args = joinPoint.getArgs();
                builder.append(" 参数值: ");
                for (Object object : args) {
                      builder.append(object);
                }
                builder.append(" 返回值: ");
                builder.append(obj);
                if(e != null) {
                    builder.append(" 异常: ");
                    builder.append(e.getLocalizedMessage());
                }
                break;
        };
      
       sysLog.setOprDetail(builder.toString());
       sysLogService.save(sysLog);
    }
   
}
