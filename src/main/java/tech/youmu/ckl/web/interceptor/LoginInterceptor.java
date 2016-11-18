/**
 * @Title: LoginInterceptor.java
 * @Package tech.youmu.ckl.web.interceptor
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月10日 下午3:16:28
 * @version V1.0
 */

package tech.youmu.ckl.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.utils.ConfigUtil;
import tech.youmu.ckl.utils.UserContext;

/**
 * @ClassName: LoginInterceptor
 * @Description: 拦截需要登录的请求地址 对需要登录的地址使用该拦截器
 * @author youmu-zh
 * @date 2016年8月10日 下午3:16:28
 *
 */
public class LoginInterceptor extends AbstractInterceptor {

	private String loginUrl;
	
	public static final String  TO_URL = "to_url";
	
	public static final String  IMG_HOST = "img_host";

	/**
	 *   1. 如果该资源需要登录 用户未登录跳转到登录界面 
	 *   2. 如果已经进行了登录检查 即 用户登录通过 则不用再检查登录
	 *   3. 如果用户登录成功跳转到请求的资源地址
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean intercept(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 使用默认账户
		// 获取session中的user
		Employee user = UserContext.getUser();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String imgHost = httpRequest.getParameter(IMG_HOST);
		if(StringUtils.isBlank(imgHost)){
		    httpRequest.setAttribute(IMG_HOST, ConfigUtil.getImgHost());
		}

		if (user != null) {
			return true;
		} else {
			boolean isAjax = StringUtils.equalsIgnoreCase(httpRequest.getHeader("x-requested-with"), "XMLHttpRequest");
			if(isAjax){
			    // 设置session状态过期 便于前台处理
			    response.setHeader("sessionstatus", "timeout");
			    return false;
			}else {
			
			// 用户未登陆且当前URI资源登录才能访问
				String toUrl = httpRequest.getRequestURL().toString();

				// 如果包含参数 添加到URL末尾
				if (!StringUtils.isEmpty(httpRequest.getQueryString())) {
					toUrl += "?" + httpRequest.getQueryString();
				}

				// 将用户的请求URL保存在session中，用于登录成功之后，跳到目标URL
				httpRequest.getSession().setAttribute(TO_URL, toUrl);

				// 转发到登录页
				request.getRequestDispatcher(loginUrl).forward(request, response);
				return false;
			}
		}
	}
	
	/**
	 * setter method
	 * @param loginUrl the loginUrl to set
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}