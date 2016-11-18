package tech.youmu.ckl.exception;
/**
 * 
 * <p>Title:BizExecption</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月16日下午4:56:42</p>
 * <p>Description:业务异常</p>
 */
@SuppressWarnings("serial")
public class BizExecption extends RuntimeException {

	public BizExecption() {
		super();
	}

	public BizExecption(String message, Throwable cause) {
		super(message, cause);
	}

	public BizExecption(String message) {
		super(message);
	}

	public BizExecption(Throwable cause) {
		super(cause);
	}

}
