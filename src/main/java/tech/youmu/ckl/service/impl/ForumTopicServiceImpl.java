package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.ForumTopicCommentInfo;
import tech.youmu.ckl.app.vo.ForumTopicDetailInfo;
import tech.youmu.ckl.app.vo.ForumTopicInfo;
import tech.youmu.ckl.app.vo.ForumTopicPraiseInfo;
import tech.youmu.ckl.app.vo.PraiseDetailInfo;
import tech.youmu.ckl.app.vo.TopicCommentDetailInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.ForumTopic;
import tech.youmu.ckl.domain.ForumTopicComment;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.ForumTopicMapper;
import tech.youmu.ckl.service.IForumTopicService;
import tech.youmu.ckl.service.component.BadgeComponent;
import tech.youmu.ckl.service.component.UserComponent;
import tech.youmu.ckl.utils.ImageURLUtil;
import tech.youmu.ckl.utils.ShareUtil;
import tech.youmu.ckl.utils.UploadUtils;
import tech.youmu.ckl.utils.XingeUtil;

/**
 * 
 * <p>Title:ForumTopicServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月1日下午5:54:27</p>
 * <p>Description:TODO</p>
 */
@Service
public class ForumTopicServiceImpl implements IForumTopicService{

    @Autowired
    private ForumTopicMapper forumTopicMapper;
    
    @Autowired
    private UserComponent userComponent;
    
    @Autowired
    private BadgeComponent badgeComponent;

    @Override
    public List<ForumTopicInfo> findForumTopicInfo(Integer page,Integer rows,Long userId,Long friendId) {
        List<ForumTopicInfo> forumTopicInfos = forumTopicMapper.findForumTopicInfo((page-1)*rows,rows,userId,friendId);
        for(ForumTopicInfo forumTopicInfo : forumTopicInfos){
            forumTopicInfo.setHeadIcon(ImageURLUtil.fillPath(forumTopicInfo.getHeadIcon()));
            forumTopicInfo.setImgs(ImageURLUtil.fillPath(forumTopicInfo.getImgs()));
            forumTopicInfo.setShareInImg(ImageURLUtil.fillPath(forumTopicInfo.getShareInImg()));
            forumTopicInfo.setShareUrl(ShareUtil.getForumTopicShareUrl(forumTopicInfo.getForumTopicId()));
        }
        return forumTopicInfos;
    }

    @Override
    public void deleteForumTopic(Long forumTopicId, Long userId) {
        ForumTopic forumTopic =  (ForumTopic) forumTopicMapper.getById(forumTopicId);
        if(null == forumTopic){
            throw new BizExecption("帖子已经删除");
        }
        if(!userId.equals(forumTopic.getUserId())){
            throw new BizExecption("不是自己的帖子不能删除");
        }
        forumTopic.setIsDel(true);
        forumTopicMapper.update(forumTopic);
    }

    @Override
    public void praiseForumTopic(Long forumTopicId, Long userId) {
        ForumTopic forumTopic =  (ForumTopic) forumTopicMapper.getById(forumTopicId);
        if(null == forumTopic){
            throw new BizExecption("帖子已经删除");
        }
        if(forumTopicMapper.isPraiseForumTopic(forumTopicId,userId)){
            throw new BizExecption("已经点赞");
        }
        if(userId.equals(forumTopic.getUserId())){
            throw new BizExecption("不能给自己的帖子点赞");
        }
        XingeUtil.topicMessage(forumTopic.getUserId());
        forumTopicMapper.praiseForumTopic(forumTopicId,userId);
    }

    @Override
    public void addForumTopic(Long userId, String content, MultipartFile[] imgs) {
        ForumTopic forumTopic = new ForumTopic(userId, content,StatusConst.ONE);
        forumTopicMapper.save(forumTopic);
        List<String> urls = UploadUtils.uploadFiles(imgs, UploadUtils.TOPIC_PATH);
        if(urls!=null&&urls.size()>0)
        forumTopicMapper.batchSaveImg(urls, forumTopic.getId());
        userComponent.addIntegral(userId,"发帖",1);
        badgeComponent.topicBadge(userId);
    }

    @Override
    public void commentForumTopic(Long userId, String content, Long forumTopicId) {
        ForumTopic forumTopic =(ForumTopic) forumTopicMapper.getById(forumTopicId);
        if(forumTopic == null){
            throw new BizExecption("帖子已经删除不能评论");
        }
        if(userId.equals(forumTopic.getUserId())){
            throw new BizExecption("自己的帖子不能评论");
        }
        XingeUtil.topicMessage(forumTopic.getUserId());
        forumTopicMapper.saveForumTopicComment(new ForumTopicComment(userId,content,forumTopicId,StatusConst.ONE));
    }

    @Override
    public List<ForumTopicCommentInfo> findForumTopicCommentInfo(Long forumTopicId,Long userId) {
        ForumTopic forumTopic =(ForumTopic) forumTopicMapper.getById(forumTopicId);
        if(forumTopic == null){
            throw new BizExecption("帖子已经删除不能评论");
        }
        List<ForumTopicCommentInfo> forumTopicCommentInfos =  forumTopicMapper.findForumTopicCommentInfo(forumTopicId,userId);
        for(ForumTopicCommentInfo forumTopicCommentInfo :forumTopicCommentInfos){
            forumTopicCommentInfo.setHeadIcon(ImageURLUtil.fillPath(forumTopicCommentInfo.getHeadIcon()));
        }
        return forumTopicCommentInfos;
    }

    @Override
    public void replyForumTopic(Long userId, String content, Long forumTopicId, Long byReplyId,String byReplyContent) {
        ForumTopic forumTopic =(ForumTopic) forumTopicMapper.getById(forumTopicId);
        if(forumTopic == null){
            throw new BizExecption("帖子已经删除不能回复");
        }
        if(!userId.equals(forumTopic.getUserId())){
            throw new BizExecption("不是自己的帖子不能回复");
        }
        XingeUtil.topicMessage(byReplyId);
        forumTopicMapper.saveForumTopicComment(new ForumTopicComment(userId,content,forumTopicId,StatusConst.TWO,byReplyId,byReplyContent));

    }

    @Override
    public List<ForumTopicPraiseInfo> findForumTopicPraiseInfo(Long forumTopicId,Long userId) {
        List<ForumTopicPraiseInfo> forumTopicPraiseInfos =  forumTopicMapper.findForumTopicPraiseInfo(forumTopicId,userId);
        for(ForumTopicPraiseInfo forumTopicPraiseInfo : forumTopicPraiseInfos){
            forumTopicPraiseInfo.setHeadIcon(ImageURLUtil.fillPath(forumTopicPraiseInfo.getHeadIcon()));
        }
        return forumTopicPraiseInfos;
    }

    @Override
    public List<PraiseDetailInfo> findPraiseDetailInfo(Long userId) {
        List<PraiseDetailInfo> praiseDetailInfos =  forumTopicMapper.findPraiseDetailInfo(userId);
        for(PraiseDetailInfo praiseDetailInfo:praiseDetailInfos){
            praiseDetailInfo.setHeadIcon(ImageURLUtil.fillPath(praiseDetailInfo.getHeadIcon()));
            praiseDetailInfo.setImg(ImageURLUtil.fillPath(praiseDetailInfo.getImg()));
        }
        return praiseDetailInfos;
    }

    @Override
    public List<TopicCommentDetailInfo> findTopicCommentDetailInfo(Long userId, Integer type) {
        List<TopicCommentDetailInfo> topicCommentDetailInfos =  forumTopicMapper.findTopicCommentDetailInfo(userId,type);
        for(TopicCommentDetailInfo topicCommentDetailInfo:topicCommentDetailInfos){
            topicCommentDetailInfo.setHeadIcon(ImageURLUtil.fillPath(topicCommentDetailInfo.getHeadIcon()));
            topicCommentDetailInfo.setImg(ImageURLUtil.fillPath(topicCommentDetailInfo.getImg()));
        }
        return topicCommentDetailInfos;
    }

	@Override
	public ForumTopicDetailInfo getForumTopicDetailInfo(Long forumTopicId, Long userId) {
		ForumTopicDetailInfo forumTopicDetailInfo = forumTopicMapper.getForumTopicDetailInfo(forumTopicId,userId);
		if(forumTopicDetailInfo !=null){
		 forumTopicDetailInfo.setHeadIcon(ImageURLUtil.fillPath(forumTopicDetailInfo.getHeadIcon()));
         forumTopicDetailInfo.setImgs(ImageURLUtil.fillPath(forumTopicDetailInfo.getImgs()));
         forumTopicDetailInfo.setShareUrl(ShareUtil.getForumTopicShareUrl(forumTopicId));
         forumTopicDetailInfo.setShareInImg(ImageURLUtil.fillPath(forumTopicDetailInfo.getShareInImg()));
		}
		return forumTopicDetailInfo;
	}

    

}
