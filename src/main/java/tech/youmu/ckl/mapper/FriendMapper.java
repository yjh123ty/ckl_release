package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.UserFriendApplyInfo;
import tech.youmu.ckl.app.vo.UserFriendInfo;
import tech.youmu.ckl.domain.UserFriend;
import tech.youmu.ckl.domain.UserFriendApply;

public interface FriendMapper extends BaseMapper{

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月24日下午2:06:55;</p>
	 *	<p>Description: TODO;</p>
	 *  @param userId
	 *  @return
	 */
	int getFriendCountByUserId(Long userId);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月24日下午2:13:05;</p>
	 *	<p>Description: TODO;</p>
	 *  @param userId
	 *  @return
	 */
	List<UserFriendInfo> findUserFriendInfo(Long userId);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年9月5日下午1:56:14;</p>
	 *	<p>Description: 获取好友信息;</p>
	 *  @param friendId
	 *  @param userId
	 *  @return
	 */
    UserFriendInfo getUserFriendInfo(@Param("friendId")Long friendId,@Param("userId")Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日上午10:22:31;</p>
     *	<p>Description: 添加好友申请;</p>
     *  @param userFriendApply
     */
    void saveUserFriendApply(UserFriendApply userFriendApply);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日上午10:34:14;</p>
     *	<p>Description: TODO;</p>
     *  @param userFriend
     */
    void updateUserFriend(UserFriend userFriend);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日上午10:34:19;</p>
     *	<p>Description: TODO;</p>
     *  @param userFriend
     */
    void saveUserFriend(@Param("friendId")Long friendId,@Param("userId")Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日上午11:03:16;</p>
     *	<p>Description: 好友申请信息;</p>
     *  @param userId
     *  @return
     */
    List<UserFriendApplyInfo> findUserFriendApplyInfo(Long userId);

    UserFriendApply getUserFriendApply(@Param("friendId")Long friendId,@Param("userId")Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日上午11:45:09;</p>
     *	<p>Description: ;</p>
     *  @param userFriendApply
     */
    void updateUserFriendApply(UserFriendApply userFriendApply);


    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日下午12:50:43;</p>
     *	<p>Description: 是否是好友;</p>
     *  @param friendId
     *  @param userId
     *  @return
     */
    boolean isFriend(@Param("friendId")Long friendId,@Param("userId")Long userId);

    UserFriendApply getUserFriendApplyById(Long id);

    UserFriendInfo getUserFriendInfoByMobile(@Param("userId")Long userId,@Param("mobile")String mobile);

    void deleteUserFriend(@Param("userId")Long userId, @Param("friendId")Long friendId);

}