package tech.youmu.ckl.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import tech.youmu.ckl.app.vo.EmployeeTokenInfo;
import tech.youmu.ckl.app.vo.UserTokenInfo;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.exception.TokenExecption;
import tech.youmu.ckl.utils.EmployeeTokenUtil;
import tech.youmu.ckl.utils.UserTokenUtil;

/**
 * 
 * <p>Title:AuthInterceptor</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月19日下午1:50:31</p>
 * <p>Description:app权限拦截，拦截.action</p>
 */
public class AppAuthInterceptor implements HandlerInterceptor{
	public static final Logger logger = LoggerFactory.getLogger(AppAuthInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("app权限拦截器");
		String token = request.getHeader("token");
		String signature = request.getHeader("signature");
		String timestamp = request.getHeader("timestamp");
		if(null ==token || null == signature || null == timestamp){
		    throw new TokenExecption("token||signature||timestamp is null");
		}
		UserTokenInfo userTokenInfo = UserTokenUtil.getUserTokenInfo(token);
		EmployeeTokenInfo employeeTokenInfo = EmployeeTokenUtil.getEmployeeTokenInfo(token);
		String key = null;
		if(userTokenInfo !=null){
		    logger.info("userTokenInfo:"+token);
		    key = userTokenInfo.getKey();
		}else if(employeeTokenInfo!=null){
		    logger.info("employeeTokenInfo:"+token);
		    key=employeeTokenInfo.getKey();
		}else{
		    throw new TokenExecption("没有token");
		}
		logger.info("key:"+key);
		if(!UserTokenUtil.verifySignature(signature,timestamp,token,key)){
			throw new TokenExecption("签名错误");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

	
	

	
}
