/**
 * @Title: IUserService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:57:38
 * @version V1.0
 */

package tech.youmu.ckl.service;

import tech.youmu.ckl.app.vo.EmployeeTokenInfo;
import tech.youmu.ckl.app.vo.UserTokenInfo;

/**
 * 
 * <p>Title:LoginService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月19日上午9:22:26</p>
 * <p>Description:App登陆</p>
 */
public interface ILoginService{

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月19日下午2:50:36;</p>
	 *	<p>Description: 密码登陆;</p>
	 *  @param mobile
	 *  @param password
	 *  @return
	 */
    UserTokenInfo doPasswordLogin(String mobile, String password);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月23日下午5:19:55;</p>
	 *	<p>Description: 获取验证码;</p>
	 *  @param mobile
	 */
	void sendVerifyCode(String mobile);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月23日下午6:05:22;</p>
	 *	<p>Description: 验证码登陆;</p>
	 *  @param mobile
	 *  @param verifyCode
	 *  @return
	 */
	UserTokenInfo doVerifyCodeLogin(String mobile, String verifyCode);

	boolean verifyMobile(String mobile);

	void doRegister(String mobile, String password, String verifyCode);

	boolean doVerifyCode(String mobile, String verifyCode);

	void updatePassword(String mobile, String password,String verifyCode);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年10月14日上午9:25:57;</p>
	 *	<p>Description: 员工登陆;</p>
	 *  @param mobile
	 *  @param password
	 *  @return
	 */
    EmployeeTokenInfo doLogin(String mobile, String password);

	

}
