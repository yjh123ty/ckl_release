package tech.youmu.ckl.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tech.youmu.ckl.constants.ResultStatus;
/**
 * 
 * <p>Title:RemoteResult</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月22日上午10:59:15</p>
 * <p>Description:TODO</p>
 */
@ApiModel(value = "返回数据格式")
public class RemoteResult<T> {
	
	/**
	 * {@link ResultStatus}
	 * 状态码
	 */
	@ApiModelProperty(value="状态 ;成功:200,token或者签名错误:203，202:业务错误，201:参数错误,101:内部运行逻辑错误,102:系统错误,2001:更新,2002强制更新", required = true)
	public int status=ResultStatus.SUCCESS;
	/**
	 * 返回数据
	 */
	@ApiModelProperty(value="数据", required = true)
	public T  data;
	
	/**
	 * 返回时间戳
	 */
	@ApiModelProperty(value="时间戳", required = true)
	public long responseTime=System.currentTimeMillis();
	
	/**
	 * 提示消息
	 */
	@ApiModelProperty(value="提示消息", required = true)
	public String message="";
	
	/**
	 * 错误消息
	 */
	@ApiModelProperty(value="错误消息", required = true)
	public String error="";
	
	/**
	 * 版本号
	 */
	@ApiModelProperty(value="版本号", required = true)
	private String version;
	
	/**
	 * 更新路径
	 */
	@ApiModelProperty(value="更新路径", required = true)
	private String url;
	
	/**
	 * 更新内容
	 */
	@ApiModelProperty(value="更新内容", required = true)
	private String content;
	
	
	public RemoteResult() {
		super();
	}
	
	/**
	 * {@link ResultStatus}
	 * @return 返回状态码
	 */
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
