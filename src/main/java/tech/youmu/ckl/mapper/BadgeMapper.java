package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.BadgeInfo;

/**
 * 
 * <p>Title:BadgeMapper</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月23日上午11:37:02</p>
 * <p>Description:徽章</p>
 */
public interface BadgeMapper extends BaseMapper{

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月23日上午11:39:48;</p>
	 *	<p>Description: 获取用户徽章数量;</p>
	 *  @param userId
	 *  @return
	 */
	int getBadgeCountByUserId(Long userId);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年8月23日下午3:33:46;</p>
	 *	<p>Description: 我的徽章;</p>
	 *  @param userId
	 *  @return
	 */
	List<BadgeInfo> findBadgeInfo(Long userId);

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年9月8日上午11:08:06;</p>
	 *	<p>Description: 判断用户是否有该徽章;</p>
	 *  @param userId
	 *  @param badgeId
	 *  @return
	 */
    boolean isExistUserBadge(@Param("userId")Long userId, @Param("badgeId")Long badgeId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月8日上午11:14:53;</p>
     *	<p>Description: 添加徽章;</p>
     *  @param userId
     *  @param badgeId
     */
    void saveUserBadge(@Param("userId")Long userId, @Param("badgeId")Long badgeId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年11月1日下午3:29:59;</p>
     *	<p>Description: TODO;</p>
     *  @param userId
     *  @return
     */
    int getTopicCount(Long userId);

    double getConsumeAmount(Long userId);

    int getOrderCount(Long userId);
}