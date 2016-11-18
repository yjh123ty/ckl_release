/**
 * @Title: SpringApplicationContextUtil.java
 * @Package tech.youmu.ckl.utils
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 下午4:07:28
 * @version V1.0
 */

package tech.youmu.ckl.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

public class SpringApplicationContextUtil {
	
	private static WebApplicationContext springApplicationContext;

	public SpringApplicationContextUtil() {
	}

	public static void setWebApplicationContext(WebApplicationContext webApplicationContext)
			throws BeansException {
		springApplicationContext = webApplicationContext;
	}

	public static ApplicationContext getApplicationContext(){
		return springApplicationContext;
	}
	
	public static Object getBean(String name) throws BeansException {
		return springApplicationContext.getBean(name);
	}

	public static boolean isContainBean(String name) {
		return springApplicationContext.containsBean(name);
	}
}
