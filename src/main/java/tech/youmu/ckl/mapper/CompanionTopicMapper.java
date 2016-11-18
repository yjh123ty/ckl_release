package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.CompanionTopicCommentInfo;
import tech.youmu.ckl.app.vo.CompanionTopicDetailInfo;
import tech.youmu.ckl.app.vo.CompanionTopicInfo;
import tech.youmu.ckl.domain.CompanionTopic;
import tech.youmu.ckl.domain.CompanionTopicComment;

/**
 * 
 * <p>Title:CompanionTopicMapper</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月6日下午4:25:22</p>
 * <p>Description:TODO</p>
 */
public interface CompanionTopicMapper extends BaseMapper {


    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月6日下午4:49:11;</p>
     *	<p>Description: 批量保存图片;</p>
     *  @param urls
     *  @param companionTopicId
     */
    void batchSaveImg(@Param("urls")List<String> urls, @Param("companionTopicId")Long companionTopicId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月6日下午6:01:01;</p>
     *	<p>Description: 部分好友可见限制;</p>
     *  @param friendIds
     *  @param companionTopicId
     */
    void batchSaveCompanionTopicPermission(@Param("friendIds")Long[] friendIds, @Param("companionTopicId")Long companionTopicId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月6日下午6:09:05;</p>
     *	<p>Description: TODO;</p>
     *  @param userId
     *  @param routeId
     *  @param time
     */
    List<CompanionTopicInfo> findCompanionTopicInfo(@Param("userId")Long userId, @Param("routeId")Long routeId, @Param("time")String time);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月7日上午10:17:55;</p>
     *	<p>Description: 查询用户能看到哪些帖子;</p>
     *  @param userId
     *  @return
     */
    List<Long> findUserPermission(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月7日上午11:37:12;</p>
     *	<p>Description: 评论;</p>
     *  @param companionTopicComment
     */
    void saveCompanionTopicComment(CompanionTopicComment companionTopicComment);


    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月7日上午11:40:53;</p>
     *	<p>Description: 保存查看人;</p>
     *  @param companionTopicId
     *  @param userId
     */
    void saveCompanionTopicView(@Param("companionTopicId")Long companionTopicId, @Param("userId")Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月7日上午11:44:23;</p>
     *	<p>Description: 是否查看过;</p>
     *  @param companionTopicId
     *  @param userId
     *  @return
     */
    boolean isCompanionTopicView(@Param("companionTopicId")Long companionTopicId, @Param("userId")Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月9日上午11:23:05;</p>
     *	<p>Description: 自己的帖子;</p>
     *  @param userId
     *  @return
     */
    List<CompanionTopicInfo> findOneselfCompanionTopicInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月9日上午11:18:47;</p>
     *	<p>Description: 帖子详情;</p>
     *  @param companionTopicId
     *  @param userId
     *  @return
     */
    CompanionTopicDetailInfo getCompanionTopicDetailInfo(@Param("companionTopicId")Long companionTopicId, @Param("userId")Long userId);

}