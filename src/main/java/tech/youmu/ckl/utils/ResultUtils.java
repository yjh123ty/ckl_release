package tech.youmu.ckl.utils;

import org.apache.commons.lang3.StringUtils;

import tech.youmu.ckl.app.vo.RemoteResult;
import tech.youmu.ckl.constants.ResultStatus;

/**
 * 
 * <p>Title:ResultUtils</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月22日上午10:59:29</p>
 * <p>Description:TODO</p>
 */
@SuppressWarnings("rawtypes")
public class ResultUtils {

	/**
	 * 默认结构为成功消息
	 * @param data 需要返回的数据
	 * @return RemoteResult  返回数据结构 
	 */
	
	@SuppressWarnings("unchecked")
	public static RemoteResult createDefResult(Object data) {
		RemoteResult remoteResult=new RemoteResult();
		remoteResult.setData(data);
		remoteResult.setMessage(ResultStatus.SUCCESS_MSG);
		return remoteResult;
	}
	
	public static RemoteResult createNullResult(){
		return createDefResult(null);
	}
	
	@SuppressWarnings("unchecked")
	public static RemoteResult createResult(int status,Object data,String message,String error) {
		RemoteResult remoteResult=new RemoteResult();
		remoteResult.setStatus(status);
		remoteResult.setData(data);
		remoteResult.setMessage(message);
		remoteResult.setError(error);
		return remoteResult;
	}
	
	
	public static RemoteResult createParamErrorResult(String message){
		return createResult(ResultStatus.PARAM_ERROR, null, message, "");
	}
	
	public static RemoteResult createBizErrorResult(String message){
		return createResult(ResultStatus.BIZ_ERROR, null, message, "");
	}
	public static RemoteResult createSysErrorResult(String error){
		if(StringUtils.isNotBlank(error)){
			return createResult(ResultStatus.INNER_ERROR, null,null, error);
		}
		return createResult(ResultStatus.INNER_ERROR, null,null, ResultStatus.SYSTEM_ERROR_MSG);
	}
	public static RemoteResult createExceptionResult(String message){
		return createResult(ResultStatus.EXCEPTION, null, message, ResultStatus.ERROR_MSG);
	}
	
	public static RemoteResult createTokenErrorResult(String message){
		return createResult(ResultStatus.ACCESS_ERROR, null, message, "");
	}
	public static RemoteResult createVersionResult(Integer status,String version,String url,String content){
		RemoteResult remoteResult=createNullResult();
		remoteResult.setUrl(url);
		remoteResult.setVersion(version);
		remoteResult.setContent(content);
		remoteResult.setStatus(status);
		return remoteResult;
	}
	 
}
