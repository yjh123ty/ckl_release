/**
 * @Title: LoginException.java
 * @Package tech.youmu.ckl.exception
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月3日 上午10:21:53
 * @version V1.0
 */

package tech.youmu.ckl.exception;

/**
 * 逻辑异常（由用户误操作导致的异常）
 * @author yjh
 *
 */
@SuppressWarnings("serial")
public class LogicException extends RuntimeException {
	
	private Integer errorCode=-99; // 默认值

	public Integer getErrorCode() {
		return errorCode;
	}
	
	public LogicException() {
		super();
	}

	public LogicException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogicException(String message) {
		super(message);
	}
	
	public LogicException(String message,Integer errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public LogicException(Throwable cause) {
		super(cause);
	}
}
