package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.ForumTopicCommentInfo;
import tech.youmu.ckl.app.vo.ForumTopicDetailInfo;
import tech.youmu.ckl.app.vo.ForumTopicInfo;
import tech.youmu.ckl.app.vo.ForumTopicPraiseInfo;
import tech.youmu.ckl.app.vo.PraiseDetailInfo;
import tech.youmu.ckl.app.vo.TopicCommentDetailInfo;
import tech.youmu.ckl.domain.ForumTopicComment;

public interface ForumTopicMapper extends BaseMapper {

    List<ForumTopicInfo> findForumTopicInfo(@Param("startRow")Integer startRow,@Param("rows") Integer rows,@Param("userId")Long userId,@Param("friendId")Long friendId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月2日上午11:06:13;</p>
     *	<p>Description: 帖子是否点赞;</p>
     *  @param forumTopicId
     *  @param userId
     */
    boolean isPraiseForumTopic(@Param("forumTopicId")Long forumTopicId,@Param("userId")Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月2日上午11:32:19;</p>
     *	<p>Description: 点赞;</p>
     *  @param forumTopicId
     *  @param userId
     */
    void praiseForumTopic(@Param("forumTopicId")Long forumTopicId,@Param("userId")Long userId);

    void batchSaveImg(@Param("urls")List<String> urls, @Param("forumTopicId")Long forumTopicId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月6日上午10:01:47;</p>
     *	<p>Description: 评论回复;</p>
     *  @param forumTopicComment
     */
    void saveForumTopicComment(ForumTopicComment forumTopicComment);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月6日上午10:30:04;</p>
     *	<p>Description: 社区帖子评论回复;</p>
     *  @param forumTopicId
     *  @param userId 用户id
     *  @return
     */
    List<ForumTopicCommentInfo> findForumTopicCommentInfo(@Param("forumTopicId")Long forumTopicId,@Param("userId")Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月9日下午2:07:42;</p>
     *	<p>Description: 点赞人列表;</p>
     *  @param forumTopicId
     *  @param userId
     *  @return
     */
    List<ForumTopicPraiseInfo> findForumTopicPraiseInfo(@Param("forumTopicId")Long forumTopicId,@Param("userId")Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日下午3:29:45;</p>
     *	<p>Description: TODO;</p>
     *  @param userId
     *  @return
     */
    List<PraiseDetailInfo> findPraiseDetailInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日下午3:29:50;</p>
     *	<p>Description: TODO;</p>
     *  @param userId
     *  @param type
     *  @return
     */
    List<TopicCommentDetailInfo> findTopicCommentDetailInfo(@Param("userId")Long userId,@Param("type") Integer type);

	ForumTopicDetailInfo getForumTopicDetailInfo(@Param("forumTopicId")Long forumTopicId, @Param("userId")Long userId);


}