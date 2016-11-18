package tech.youmu.ckl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.CompanionTopicDetailInfo;
import tech.youmu.ckl.app.vo.CompanionTopicInfo;

/**
 * 
 * <p>Title:ICompanionTopicService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月1日下午3:44:21</p>
 * <p>Description:旅伴帖子</p>
 */
public interface ICompanionTopicService {


    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月6日下午4:23:45;</p>
     *	<p>Description: 添加结伴;</p>
     *  @param startTime
     *  @param endTime
     *  @param routeId
     *  @param title
     *  @param content
     *  @param userId
     *  @param permission
     *  @param imgs
     */
    void addCompanionTopic(String startTime, String endTime, Long routeId, String title, String content, Long userId,Long[] friendIds, Integer permission, MultipartFile[] imgs);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月6日下午5:47:03;</p>
     *	<p>Description: 旅伴帖子信息;</p>
     *  @param userId
     *  @param routeId
     *  @param time
     *  @return
     */
    List<CompanionTopicInfo> findCompanionTopicInfo(Long userId, Long routeId, String time);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月7日上午11:23:33;</p>
     *	<p>Description: 删除旅伴帖子;</p>
     *  @param companionTopicId
     *  @param userId
     */
    void deleteCompanionTopic(Long companionTopicId, Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月7日上午11:30:41;</p>
     *	<p>Description: 评论;</p>
     *  @param userId
     *  @param content
     *  @param companionTopicId
     */
    void commentCompanionTopic(Long userId, String content, Long companionTopicId);


    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月7日上午11:31:09;</p>
     *	<p>Description: 回复;</p>
     *  @param userId
     *  @param content
     *  @param companionTopicId
     *  @param byReplyId
     */
    void replyCompanionTopic(Long userId, String content, Long companionTopicId, Long byReplyId,String byReplyContent);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月9日上午10:54:43;</p>
     *	<p>Description: 我自己的帖子;</p>
     *  @param userId
     *  @return
     */
    List<CompanionTopicInfo> findOneselfCompanionTopicInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月9日上午11:10:06;</p>
     *	<p>Description: 帖子详情;</p>
     *  @param companionTopicId
     *  @param userId
     *  @return
     */
    CompanionTopicDetailInfo getCompanionTopicDetailInfo(Long companionTopicId, Long userId);

}
