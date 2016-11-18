package tech.youmu.ckl.exception;
/**
 * 
 * <p>Title:BizExecption</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月16日下午4:56:42</p>
 * <p>Description:业务异常</p>
 */
public class TokenExecption extends RuntimeException {

	public TokenExecption() {
		super();
	}

	public TokenExecption(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenExecption(String message) {
		super(message);
	}

	public TokenExecption(Throwable cause) {
		super(cause);
	}

}
