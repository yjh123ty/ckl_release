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

import tech.youmu.ckl.app.vo.UserFriendApplyInfo;
import tech.youmu.ckl.app.vo.UserFriendInfo;

/**
 * 
 * <p>Title:IFriendService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月24日下午2:07:46</p>
 * <p>Description:好友</p>
 */
public interface IFriendService {

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月24日下午2:12:34;</p>
	 *	<p>Description: TODO;</p>
	 *  @param userId
	 *  @return
	 */
	List<UserFriendInfo> findUserFriendInfo(Long userId);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年9月5日上午10:26:17;</p>
	 *	<p>Description: 设置好友备注;</p>
	 *  @param id
	 *  @param note
	 */
    void setUserFriendNote(Long id, String note);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月5日下午1:53:50;</p>
     *	<p>Description: 获取好友信息;</p>
     *  @param friendId
     *  @param userId
     */
    UserFriendInfo getUserFriendInfo(Long friendId, Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日上午10:13:39;</p>
     *	<p>Description: 添加好友申请;</p>
     *  @param friendId
     *  @param userId
     *  @param remark
     *  @return
     */
    void addUserFriendApply(Long friendId, Long userId, String remark);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日上午10:57:54;</p>
     *	<p>Description: 好友申请列表;</p>
     *  @param userId
     *  @return
     */
    List<UserFriendApplyInfo> findUserFriendApplyInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日上午11:29:42;</p>
     *	<p>Description: 同意添加好友;</p>
     *  @param userFriendApplyId
     */
    void agreeAddUserFriend(Long userFriendApplyId, Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日下午1:33:57;</p>
     *	<p>Description: TODO;</p>
     *  @param mobile
     *  @return
     */
    UserFriendInfo getUserFriendInfoByMobile(Long userId, String mobile);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年11月11日上午10:39:44;</p>
     *	<p>Description: 删除好友;</p>
     *  @param userId
     *  @param friendId
     */
    void deleteUserFriend(Long userId, Long friendId);




}
