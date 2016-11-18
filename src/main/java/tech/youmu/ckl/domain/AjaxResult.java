/**
 * @Title: AjaxResult.java
 * @Package tech.youmu.ckl.domain
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-zh
 * @date 2016年8月3日 下午12:30:02
 * @version V1.0
 */

package tech.youmu.ckl.domain;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
  * @ClassName: AjaxResult
  * @Description: 基于ajax请求的返回的提示信息
  * @author youmu-zh
  * @date 2016年8月3日 下午12:30:02
  *
  */

public class AjaxResult {
	
	/**
	 * 操作是否成功
	 */
	public boolean success = true;
	
	/**
	 * 错误的状态码
	 */
	public int errorCode;
	
	/**
	 * 提示信息
	 */
	public String msg;
	
	/**
	 * 额外数据
	 */
	public Object data;
	

	/**
	 *  成功时
	 */
	public AjaxResult() {
		super();
	}

	/**
	 * 实例化
	 * @param msg  成功后的提示消息
	 */
	public AjaxResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	/**
	 *  错误时
	 * @param msg  错误消息
	 * @param errorCode 错误码
	 */
	public AjaxResult(String msg, Integer errorCode) {
		super();
		this.success = false;
		this.msg = msg;
		this.errorCode = errorCode;
	}
	
	/**
	 * 成功
	  * success(操作成功静态方法)
	  * @param msg
	  * @return
	 */
	public static AjaxResult success(String msg){
		return new AjaxResult(true, msg);
	}
	
	/**
	 * 成功
	 * success(操作失败静态方法)
	 * @param msg
	 * @return
	 */
	public static AjaxResult fail(String msg, Integer errorCode){
		return new AjaxResult(msg, errorCode);
	}
	/**
	 * 失败
	 * fail(操作失败静态方法)
	 * @param msg 错误消息
	 * @return
	 */
	public static AjaxResult fail(String msg){
		return new AjaxResult(false, msg);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	  * error(数据验证错误的结果处理)
	  * @param br 错误结果
	  * @return
	  */
	public static AjaxResult error(BindingResult br) {
		List<ObjectError> errors = br.getAllErrors();
		StringBuilder builder = new StringBuilder();
		for (ObjectError objectError : errors) {
			builder.append(objectError.getDefaultMessage()).append("<br />");
		}
		return fail(builder.toString());
	}

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "AjaxResult [success=" + success + ", errorCode=" + errorCode + ", msg=" + msg + "]";
    }
	
}
