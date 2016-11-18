package tech.youmu.ckl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.CompanionTopicCommentInfo;
import tech.youmu.ckl.app.vo.CompanionTopicDetailInfo;
import tech.youmu.ckl.app.vo.CompanionTopicInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.CompanionTopic;
import tech.youmu.ckl.domain.CompanionTopicComment;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.CompanionTopicMapper;
import tech.youmu.ckl.mapper.UserMapper;
import tech.youmu.ckl.service.ICompanionTopicService;
import tech.youmu.ckl.service.component.BadgeComponent;
import tech.youmu.ckl.service.component.UserComponent;
import tech.youmu.ckl.utils.DateUtil;
import tech.youmu.ckl.utils.ImageURLUtil;
import tech.youmu.ckl.utils.ShareUtil;
import tech.youmu.ckl.utils.UploadUtils;
import tech.youmu.ckl.utils.XingeUtil;

/**
 * 
 * <p>Title:CompanionTopicServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月1日下午3:45:47</p>
 * <p>Description:TODO</p>
 */
@Service
public class CompanionTopicServiceImpl implements ICompanionTopicService{

    @Autowired
    private CompanionTopicMapper companionTopicMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserComponent userComponent;
    
    @Autowired
    private BadgeComponent badgeComponent;

    @Override
    public void addCompanionTopic(String startTime, String endTime, Long routeId, String title, String content, Long userId,Long[] friendIds, Integer permission, MultipartFile[] imgs) {
        CompanionTopic companionTopic = new CompanionTopic(startTime,endTime,routeId,title,content,userId,permission,DateUtil.getTimestamp());
        companionTopicMapper.save(companionTopic);
        List<String> urls = UploadUtils.uploadFiles(imgs, UploadUtils.TOPIC_PATH);
        if(urls!=null&&urls.size()>0){
            companionTopicMapper.batchSaveImg(urls, companionTopic.getId());
        }
        if(permission.equals(StatusConst.THREE)&&friendIds!=null&&friendIds.length>0){
            companionTopicMapper.batchSaveCompanionTopicPermission(friendIds,companionTopic.getId());
        }
        userComponent.addIntegral(userId,"发帖",1);
        badgeComponent.topicBadge(userId);
    }

    @Override
    public List<CompanionTopicInfo> findCompanionTopicInfo(Long userId, Long routeId, String time) {
        List<CompanionTopicInfo> list = new ArrayList<>();
        User user = (User) userMapper.getById(userId);
        if(user == null){
            throw new BizExecption("你的账户已经被冻结");
        }
        List<Long> companionTopicIds = companionTopicMapper.findUserPermission(userId);
        List<CompanionTopicInfo> companionTopicInfos = companionTopicMapper.findCompanionTopicInfo(userId,routeId,time);
        for(CompanionTopicInfo companionTopicInfo :companionTopicInfos){
            companionTopicInfo.setHeadIcon(ImageURLUtil.fillPath(companionTopicInfo.getHeadIcon()));
            if(userId.equals(companionTopicInfo.getUserId())){
                list.add(companionTopicInfo);
            }else if(StatusConst.ONE == companionTopicInfo.getPermission()){
                list.add(companionTopicInfo);
            }else if(StatusConst.TWO == companionTopicInfo.getPermission()&&StringUtils.isNotBlank(user.getIdentityCard())&&StringUtils.isNotBlank(user.getRealName())){
                list.add(companionTopicInfo);
            }else if(StatusConst.THREE == companionTopicInfo.getPermission()&&companionTopicIds.contains(companionTopicInfo.getCompanionTopicId())){
                list.add(companionTopicInfo);
            }else if(StatusConst.FOUR == companionTopicInfo.getPermission()&&user.getSex()!=null&&StatusConst.ONE == user.getSex()){
                list.add(companionTopicInfo);
            }else if(StatusConst.FIVE == companionTopicInfo.getPermission()&&user.getSex()!=null&&StatusConst.TWO == user.getSex()){
                list.add(companionTopicInfo);
            }
            
        }
        return list;
    }

    @Override
    public void deleteCompanionTopic(Long companionTopicId, Long userId) {
        CompanionTopic companionTopic =  (CompanionTopic) companionTopicMapper.getById(companionTopicId);
        if(null == companionTopic){
            throw new BizExecption("帖子已经删除");
        }
        if(!userId.equals(companionTopic.getUserId())){
            throw new BizExecption("不是自己的帖子不能删除");
        }
        companionTopic.setIsDel(true);
        companionTopicMapper.update(companionTopic);        
    }

    @Override
    public void commentCompanionTopic(Long userId, String content, Long companionTopicId) {
        CompanionTopic companionTopic =(CompanionTopic) companionTopicMapper.getById(companionTopicId);
        if(companionTopic == null){
            throw new BizExecption("帖子已经删除不能评论");
        }
        if(userId.equals(companionTopic.getUserId())){
            throw new BizExecption("自己的帖子不能评论");
        }
        XingeUtil.topicMessage(companionTopic.getUserId());
        companionTopicMapper.saveCompanionTopicComment(new CompanionTopicComment(userId,content,companionTopicId,StatusConst.ONE));
    }

    /*@Override
    public List<CompanionTopicCommentInfo> findCompanionTopicCommentInfo(Long companionTopicId, Long userId) {
        CompanionTopic companionTopic =(CompanionTopic) companionTopicMapper.getById(companionTopicId);
        if(companionTopic == null){
            throw new BizExecption("帖子已经删除不能评论");
        }
        if(!companionTopicMapper.isCompanionTopicView(companionTopicId,userId)){
            companionTopicMapper.saveCompanionTopicView(companionTopicId,userId);
        }
        return companionTopicMapper.findCompanionTopicCommentInfo(companionTopicId,userId);
    }*/

    @Override
    public void replyCompanionTopic(Long userId, String content, Long companionTopicId, Long byReplyId,String byReplyContent) {
        CompanionTopic companionTopic =(CompanionTopic) companionTopicMapper.getById(companionTopicId);
        if(companionTopic == null){
            throw new BizExecption("帖子已经删除不能回复");
        }
        if(!userId.equals(companionTopic.getUserId())){
            throw new BizExecption("不是自己的帖子不能回复");
        }
        XingeUtil.topicMessage(byReplyId);
        companionTopicMapper.saveCompanionTopicComment(new CompanionTopicComment(userId,content,companionTopicId,StatusConst.TWO,byReplyId,byReplyContent));

    }

    @Override
    public List<CompanionTopicInfo> findOneselfCompanionTopicInfo(Long userId) {
        List<CompanionTopicInfo> companionTopicInfos =  companionTopicMapper.findOneselfCompanionTopicInfo(userId);
        for(CompanionTopicInfo companionTopicInfo:companionTopicInfos){
            companionTopicInfo.setHeadIcon(ImageURLUtil.fillPath(companionTopicInfo.getHeadIcon()));
        }
        return companionTopicInfos;
    }

    @Override
    public CompanionTopicDetailInfo getCompanionTopicDetailInfo(Long companionTopicId, Long userId) {
        CompanionTopicDetailInfo companionTopicDetailInfo = companionTopicMapper.getCompanionTopicDetailInfo(companionTopicId,userId);
        if(companionTopicDetailInfo == null){
            throw new BizExecption("帖子已经删除");
        }
        List<CompanionTopicCommentInfo> companionTopicCommentInfos = companionTopicDetailInfo.getCompanionTopicCommentInfos();
        for(CompanionTopicCommentInfo companionTopicCommentInfo: companionTopicCommentInfos){
            companionTopicCommentInfo.setHeadIcon(ImageURLUtil.fillPath(companionTopicCommentInfo.getHeadIcon()));
        }
        companionTopicDetailInfo.setHeadIcon(ImageURLUtil.fillPath(companionTopicDetailInfo.getHeadIcon()));
        companionTopicDetailInfo.setCommentCount(companionTopicCommentInfos.size());
        companionTopicDetailInfo.setImgs(ImageURLUtil.fillPath(companionTopicDetailInfo.getImgs()));
        companionTopicDetailInfo.setShareUrl(ShareUtil.getCompanionTopicShareUrl(companionTopicId));
        return companionTopicDetailInfo;
    }

}
