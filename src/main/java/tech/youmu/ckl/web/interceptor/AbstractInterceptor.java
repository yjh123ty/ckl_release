package tech.youmu.ckl.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
  * @ClassName: AbstractInterceptor
  * @Description: 减少拦截器里面的方法
  * @author youmu-zh
  * @date 2016年8月10日 下午3:21:18
  *
 */
public abstract class AbstractInterceptor implements HandlerInterceptor{

	/**
	 * 在控制器方法之前执行
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return intercept(request, response, handler);
	}
	
	/**
	 * 一般的拦截方法都是在 之前进行拦截  
	 * @return
	 */
	public abstract boolean intercept(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

	/**
	 * 在控制器方法执行后 在视图处理之前 主要用户结果的修改
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	/**
	 * 视图处理完成后 主要用于资源的清理
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
