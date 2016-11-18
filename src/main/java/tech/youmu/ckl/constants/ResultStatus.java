package tech.youmu.ckl.constants;

/**
 * 
 * <p>Title:ResultStatus</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月22日上午10:59:22</p>
 * <p>Description:TODO</p>
 */
public class ResultStatus {
	
	//------------------消息码-----------------------------
	/**
	 * 返回成功
	 */
	public static final int SUCCESS=200; //返回成功
	
	public static final int PARAM_ERROR=201;//参数错误
	
	public static final int BIZ_ERROR=202;//业务错误
	
	public static final int ACCESS_ERROR=203;//token错误
	
	public static final int VERSION_UPDATE=2001;//更新
	
	public static final int VERSION_FORCE_UPDATE=2002;//强制更新
	
	/**
	 * 内部逻辑异常
	 */
	public static final int EXCEPTION=101;//内部运行逻辑错误
	
	public static final int INNER_ERROR=102;//系统错误
	
	
	
	
	//-----------------消息---------------------------
	
	/**
	 * 返回成功默认消息
	 */
	public static final String SUCCESS_MSG="OK";
	/**
	 * 返回错误默认消息
	 */
	public static final String ERROR_MSG="ERROR";
	
	/**
	 * 运行异常
	 */
	public static final String EXCEPTION_MSG="EXCEPTION";
	
	/**
	 * 系统内部错误
	 */
	public static final String SYSTEM_ERROR_MSG="SYSTEM_ERROR";
}
