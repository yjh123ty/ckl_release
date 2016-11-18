package tech.youmu.ckl.exception;
/**
 * 
 * <p>Title:ParamException</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月16日下午4:57:04</p>
 * <p>Description:参数异常</p>
 */
public class ParamException extends RuntimeException {

	public ParamException() {
		super();
	}

	public ParamException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParamException(String message) {
		super(message);
	}

	public ParamException(Throwable cause) {
		super(cause);
	}

}
