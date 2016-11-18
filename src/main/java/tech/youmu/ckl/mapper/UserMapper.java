/**
 * @Title: UserMapper.java
 * @Package tech.youmu.ckl.mapper
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:54:48
 * @version V1.0
 */

package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.UserInfo;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.query.UserQuery;

/**
 * @ClassName: UserMapper
 * @Description: 用户的数据操作mapper
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:09:55
 *
 */
public interface UserMapper extends BaseMapper<User> {


	/**
	 * 带查询条件的用户列表
	 * @param userQuery
	 * @return
	 */
	public List<User> queryList(UserQuery userQuery);

	/**
	 * 符合查询条件的用户列表数
	 * @param userQuery
	 * @return
	 */
	public Long getCount(UserQuery userQuery);

	/**
	 * 禁用用户
	 * @param id
	 */
	public void disableById(Long id);

	/**
	 * 开通员工
	 * @param id
	 */
	public void invokeById(Long id);


	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月19日上午10:08:07;</p>
	 *	<p>Description: 根据电话类型查询用户;</p>
	 *  @param mobile 电话
	 */
	public User getByPhoneNumber(@Param("mobile")String mobile);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月19日下午3:14:50;</p>
	 *	<p>Description: TODO;</p>
	 *  @param userId
	 *  @return
	 */
	public UserInfo getUserInfoByUserId(Long userId);

    public List<String> findCode();

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月9日下午2:58:19;</p>
     *	<p>Description: 查询用户的车友;</p>
     *  @param userId
     *  @return
     */
    public List<User> findUserByRiders(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月11日下午3:22:52;</p>
     *	<p>Description: 增加余额;</p>
     *  @param userId
     *  @param balance
     */
    public void addBalance(@Param("userId")Long userId, @Param("balance")Double balance);

    public User getByCode(String userCode);


    public void batchUpdateGroupId(@Param("userIds")String[] userIds, @Param("groupId")String groupId);

    public List<String> findGroupId(@Param("userIds")String[] userIds);




}
