package tech.youmu.ckl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.ForumTopicCommentInfo;
import tech.youmu.ckl.app.vo.ForumTopicDetailInfo;
import tech.youmu.ckl.app.vo.ForumTopicInfo;
import tech.youmu.ckl.app.vo.ForumTopicPraiseInfo;
import tech.youmu.ckl.app.vo.PraiseDetailInfo;
import tech.youmu.ckl.app.vo.TopicCommentDetailInfo;

/**
 * 
 * <p>Title:IForumTopicService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月1日下午5:50:35</p>
 * <p>Description:TODO</p>
 */
public interface IForumTopicService {

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月1日下午5:53:08;</p>
     *	<p>Description: 社区帖子列表;</p>
     *  @param userId
     *  @return
     */
    List<ForumTopicInfo> findForumTopicInfo(Integer page,Integer rows,Long userId,Long friendId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月2日上午10:21:42;</p>
     *	<p>Description: 删除社区帖子;</p>
     *  @param forumTopicId
     *  @param userId
     */
    void deleteForumTopic(Long forumTopicId, Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月2日上午11:04:07;</p>
     *	<p>Description: 点赞;</p>
     *  @param forumTopicId
     *  @param userId
     */
    void praiseForumTopic(Long forumTopicId, Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月5日下午3:52:05;</p>
     *	<p>Description: 发帖;</p>
     *  @param userId
     *  @param content
     *  @param imgs
     */
    void addForumTopic(Long userId, String content, MultipartFile[] imgs);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月6日上午9:51:04;</p>
     *	<p>Description: 评论帖子;</p>
     *  @param userId
     *  @param content
     *  @param forumTopicId
     */
    void commentForumTopic(Long userId, String content, Long forumTopicId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月6日上午10:29:07;</p>
     *	<p>Description: 社区帖子评论回复;</p>
     *  @param forumTopicId
     *  @return
     */
    List<ForumTopicCommentInfo> findForumTopicCommentInfo(Long forumTopicId,Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月6日上午11:31:32;</p>
     *	<p>Description: 社区帖子回复;</p>
     *  @param userId
     *  @param content
     *  @param forumTopicId
     *  @param byReplyId
     */
    void replyForumTopic(Long userId, String content, Long forumTopicId, Long byReplyId,String byReplyContent);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月7日下午5:24:10;</p>
     *	<p>Description: 帖子点赞信息;</p>
     *  @param forumTopicId
     *  @return
     */
    List<ForumTopicPraiseInfo> findForumTopicPraiseInfo(Long forumTopicId,Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日下午2:12:42;</p>
     *	<p>Description: 点赞明细;</p>
     *  @param userId
     *  @return
     */
    List<PraiseDetailInfo> findPraiseDetailInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月13日下午3:28:49;</p>
     *	<p>Description: 评论回复明细;</p>
     *  @param userId
     *  @param type
     *  @return
     */
    List<TopicCommentDetailInfo> findTopicCommentDetailInfo(Long userId, Integer type);

	ForumTopicDetailInfo getForumTopicDetailInfo(Long forumTopicId, Long userId);


}
