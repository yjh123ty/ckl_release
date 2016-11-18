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

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.RidersInfo;
import tech.youmu.ckl.app.vo.RongTokenInfo;
import tech.youmu.ckl.app.vo.UserInfo;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.domain.UserCenter;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.UserQuery;

/**
 * @ClassName: IUserService
 * @Description: 用户管理服务层
 * @author youmu-yjh
 * @date 2016年8月2日 上午11:15:48
 *
 */
public interface IUserService {

	public User getById(Long id);


	/**
	 * 分页显示用户数据
	 * @param userQuery	用户查询对象
	 * @return
	 */
	public PageList<User> pageList(UserQuery userQuery);

	/**
	 * 禁用用户
	 * @param id
	 */
	public void disableById(Long id);

	/**
	 * 开通用户
	 * @param id
	 */
	public void invokeById(Long id);


	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月19日下午3:13:48;</p>
	 *	<p>Description: 获取用户信息;</p>
	 *  @param userId
	 *  @return
	 */
	public UserInfo getUserInfo(Long userId);




	public void verifyLoginPassword(Long userId, String loginPassword);


	public void updateLoginPassword(Long userId, String newLoginPassword,String oldLoginPassword);


	public void verifyPayPassword(Long userId, String payPassword);


	public void updatePayPassword(Long userId, String newPayPassword, String oldPayPassword);


	public void logout(String token);


    public String uploadHeadIcon(Long userId, MultipartFile headIcon);


    public void updateUserInfo(User user);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月9日下午2:28:58;</p>
     *	<p>Description: 上传用户坐标;</p>
     *  @param userId
     *  @param lng
     *  @param lat
     */
    public void uploadCoord(Long userId, String lng, String lat);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月9日下午2:29:16;</p>
     *	<p>Description: 获取附近车友;</p>
     *  @param lng
     *  @param lat
     */
    public List<RidersInfo> findNearbyRiders(Long userId,String lng, String lat);


    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月9日下午4:52:43;</p>
     *	<p>Description: 设置陌生人对讲;</p>
     *  @param userId
     *  @param isAllow
     */
    public void setStrangerTalkback(Long userId, Boolean isAllow);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月13日上午10:22:36;</p>
     *	<p>Description: TODO;</p>
     *  @param userId
     *  @return
     */
    public RongTokenInfo getRongToken(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月14日上午11:05:16;</p>
     *	<p>Description: 创建群;</p>
     *  @return
     */
    public String createGroup(String[] userIds);

    public void quitGroup(String userId,String groupId);


    public UserCenter getUserCenterByUserId(Long id);

}
