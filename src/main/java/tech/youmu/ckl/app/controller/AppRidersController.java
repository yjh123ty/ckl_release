package tech.youmu.ckl.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tech.youmu.ckl.app.vo.CompanionTopicDetailInfo;
import tech.youmu.ckl.app.vo.CompanionTopicInfo;
import tech.youmu.ckl.app.vo.ForumTopicCommentInfo;
import tech.youmu.ckl.app.vo.ForumTopicDetailInfo;
import tech.youmu.ckl.app.vo.ForumTopicInfo;
import tech.youmu.ckl.app.vo.ForumTopicPraiseInfo;
import tech.youmu.ckl.app.vo.RemoteResult;
import tech.youmu.ckl.app.vo.RidersInfo;
import tech.youmu.ckl.exception.ParamException;
import tech.youmu.ckl.service.ICompanionTopicService;
import tech.youmu.ckl.service.IForumTopicService;
import tech.youmu.ckl.service.IUserService;
import tech.youmu.ckl.utils.LogUtils;
import tech.youmu.ckl.utils.ResultUtils;

@RestController
@RequestMapping(value = "/riders/remote")
@Api(description = "车友页面")
public class AppRidersController {

	@Autowired
	private IForumTopicService forumTopicService;
	
	@Autowired
    private ICompanionTopicService companionTopicService;
	
	@Autowired
    private IUserService userService;
	

	
	@ApiOperation(value="社区帖子")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "page", value = "第几页", required = true,paramType="query", dataType = "int",defaultValue="1"),
	    @ApiImplicitParam(name = "rows", value = "每页个数", required = true,paramType="query", dataType = "int",defaultValue="10"),
	    @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
	    @ApiImplicitParam(name = "friendId", value = "好友id，如果不是查询好友帖子不用传该参数,查询自己的的帖子friendId=userId", required = false,paramType="query", dataType = "long",defaultValue="1")
	})
	@RequestMapping(value="findForumTopicInfo.action",method=RequestMethod.POST)
    public RemoteResult<ForumTopicInfo> findForumTopicInfo(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer rows,Long userId,Long friendId) {
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        List<ForumTopicInfo> forumTopicInfos = forumTopicService.findForumTopicInfo(page,rows,userId,friendId);
        return ResultUtils.createDefResult(forumTopicInfos);
    }
	
	@ApiOperation(value="社区帖子详情")
	@ApiImplicitParams({
		 @ApiImplicitParam(name = "forumTopicId", value = "帖子id", required = true,paramType="query", dataType = "long",defaultValue="1"),
	     @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
	})
	@RequestMapping(value="getForumTopicDetailInfo.action",method=RequestMethod.POST)
    public RemoteResult<ForumTopicDetailInfo> getForumTopicDetailInfo(Long forumTopicId,Long userId) {
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        ForumTopicDetailInfo forumTopicDetailInfo = forumTopicService.getForumTopicDetailInfo(forumTopicId,userId);
        return ResultUtils.createDefResult(forumTopicDetailInfo);
    }
	
	@ApiOperation(value="删除社区帖子")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "forumTopicId", value = "帖子id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "userId", value = "删除用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="deleteForumTopic.action",method=RequestMethod.POST)
    public RemoteResult deleteForumTopic(Long forumTopicId,Long userId) {
         if(forumTopicId == null){
             throw new ParamException("forumTopicId不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        forumTopicService.deleteForumTopic(forumTopicId,userId);
        return ResultUtils.createNullResult();
    }
	
	
	@ApiOperation(value="社区帖子点赞")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "forumTopicId", value = "帖子id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="praiseForumTopic.action",method=RequestMethod.POST)
    public RemoteResult praiseForumTopic(Long forumTopicId,Long userId) {
         if(forumTopicId == null){
             throw new ParamException("forumTopicId不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        forumTopicService.praiseForumTopic(forumTopicId,userId);
        return ResultUtils.createNullResult();
    }
	
	
	
	
	
	@ApiOperation(value="社区发帖")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "content", value = "内容", required = true,paramType="query", dataType = "String",defaultValue="asdfqw"),
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "imgs", value = "多上图片已数组方式传", required = false,paramType="form", dataType = "file")
    })
    @RequestMapping(value="addForumTopic.action",method=RequestMethod.POST)
    public RemoteResult<Long> addForumTopic(Long userId,String content,@RequestPart MultipartFile[] imgs) {
         if(content == null){
             throw new ParamException("内容不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
         forumTopicService.addForumTopic(userId,content,imgs);
        return ResultUtils.createNullResult();
    }
	
	
	@ApiOperation(value="社区评论帖子")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "content", value = "评论内容", required = true,paramType="query", dataType = "String",defaultValue="asdfqw"),
        @ApiImplicitParam(name = "userId", value = "评论人id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "forumTopicId", value = "帖子id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })
    @RequestMapping(value="commentForumTopic.action",method=RequestMethod.POST)
    public RemoteResult<Long> commentForumTopic(Long userId,String content,Long forumTopicId) {
         if(content == null){
             throw new ParamException("内容不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
         if(forumTopicId == null){
             throw new ParamException("forumTopicId不能为空");
         }
         forumTopicService.commentForumTopic(userId,content,forumTopicId);
        return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value="获取社区帖子评论回复")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "forumTopicId", value = "帖子id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })    @RequestMapping(value="findForumTopicCommentInfo.action",method=RequestMethod.POST)
    public RemoteResult<ForumTopicCommentInfo> findForumTopicCommentInfo(Long userId,Long forumTopicId) {
         if(forumTopicId == null){
             throw new ParamException("forumTopicId不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        List<ForumTopicCommentInfo> forumTopicCommentInfos = forumTopicService.findForumTopicCommentInfo(forumTopicId,userId);
        return ResultUtils.createDefResult(forumTopicCommentInfos);
    }
	
	@ApiOperation(value="获取社区帖子点赞人信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "forumTopicId", value = "帖子id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })    
	@RequestMapping(value="findForumTopicPraiseInfo.action",method=RequestMethod.POST)
    public RemoteResult<ForumTopicPraiseInfo> findForumTopicPraiseInfo(Long forumTopicId,Long userId) {
         if(forumTopicId == null){
             throw new ParamException("forumTopicId不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        List<ForumTopicPraiseInfo> forumTopicCommentInfos = forumTopicService.findForumTopicPraiseInfo(forumTopicId,userId);
        return ResultUtils.createDefResult(forumTopicCommentInfos);
    }
	
	@ApiOperation(value="社区帖子回复")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "content", value = "回复内容", required = true,paramType="query", dataType = "String",defaultValue="asdfqw"),
        @ApiImplicitParam(name = "byReplyId", value = "被回复人Id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "userId", value = "回复人id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "forumTopicId", value = "帖子id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "byReplyContent", value = "被回复内容", required = true,paramType="query", dataType = "String",defaultValue="as112dfqw")

    })
    @RequestMapping(value="replyForumTopic.action",method=RequestMethod.POST)
    public RemoteResult<Long> replyForumTopic(Long userId,String content,Long forumTopicId,Long byReplyId,String byReplyContent) {
         if(content == null){
             throw new ParamException("内容不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
         if(byReplyId == null){
             throw new ParamException("byReplyId不能为空");
         }
         if(forumTopicId == null){
             throw new ParamException("forumTopicId不能为空");
         }
         if(byReplyContent == null){
             throw new ParamException("byReplyContent不能为空");
         }
         forumTopicService.replyForumTopic(userId,content,forumTopicId,byReplyId,byReplyContent);
        return ResultUtils.createNullResult();
    }
	
	
	@ApiOperation(value="添加结伴帖子")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "startTime", value = "开始时间", required = true,paramType="query", dataType = "String",defaultValue="2016-09-06 16:55:00"),
        @ApiImplicitParam(name = "endTime", value = "截止时间", required = true,paramType="query", dataType = "String",defaultValue="2016-09-08 16:55:00"),
        @ApiImplicitParam(name = "routeId", value = "路线id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "title", value = "标题", required = true,paramType="query", dataType = "String",defaultValue="as"),
        @ApiImplicitParam(name = "content", value = "内容", required = true,paramType="query", dataType = "String",defaultValue="asdfqw"),
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "friendIds", value = "好友id,多个已数组方式传", required = false,paramType="query", dataType = "long"),
        @ApiImplicitParam(name = "permission", value = " 查看权限 1-所有人，2-实名认证，3-部分好友，4-女可以查看，5-男可以查看", required = true,paramType="query", dataType = "int",defaultValue="1"),
        @ApiImplicitParam(name = "imgs", value = "多上图片已数组方式传", required = false,paramType="form", dataType = "file")
    })
    @RequestMapping(value="addCompanionTopic.action",method=RequestMethod.POST)
    public RemoteResult<Long> addCompanionTopic(String startTime,String endTime,Long routeId,String title,Long userId,Long[] friendIds,String content,Integer permission,@RequestPart MultipartFile[] imgs) {
         if(startTime == null){
             throw new ParamException("开始时间不能为空");
         }
         if(endTime == null){
             throw new ParamException("截止时间不能为空");
         }
         if(routeId == null){
             throw new ParamException("路线没有选择");
         }
         if(title == null){
             throw new ParamException("标题不能为空");
         }
         if(permission == null){
             throw new ParamException("谁可以看没有选择");
         }
         if(content == null){
             throw new ParamException("内容不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
         companionTopicService.addCompanionTopic(startTime,endTime,routeId,title,content,userId,friendIds,permission,imgs);
        return ResultUtils.createNullResult();
    }
	
	
	@ApiOperation(value="旅伴帖子")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "routeId", value = "路线id", required = false,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "time", value = "时间", required = false,paramType="query", dataType = "String",defaultValue="2016-09")
    })
    @RequestMapping(value="findCompanionTopicInfo.action",method=RequestMethod.POST)
    public RemoteResult<CompanionTopicInfo> findCompanionTopicInfo(Long userId,Long routeId,String time) {
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        List<CompanionTopicInfo> companionTopicInfos = companionTopicService.findCompanionTopicInfo(userId,routeId,time);
        return ResultUtils.createDefResult(companionTopicInfos);
    }
	
	
	@ApiOperation(value="我的结伴帖子")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="findOneselfCompanionTopicInfo.action",method=RequestMethod.POST)
    public RemoteResult<CompanionTopicInfo> findOneselfCompanionTopicInfo(Long userId) {
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        List<CompanionTopicInfo> companionTopicInfos = companionTopicService.findOneselfCompanionTopicInfo(userId);
        return ResultUtils.createDefResult(companionTopicInfos);
    }
	
	@ApiOperation(value="删除旅伴帖子")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "companionTopicId", value = "帖子id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "userId", value = "删除用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="deleteCompanionTopic.action",method=RequestMethod.POST)
    public RemoteResult deleteCompanionTopic(Long companionTopicId,Long userId) {
         if(companionTopicId == null){
             throw new ParamException("companionTopicId不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        companionTopicService.deleteCompanionTopic(companionTopicId,userId);
        return ResultUtils.createNullResult();
    }
	
	
	@ApiOperation(value="旅伴帖子评论")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "content", value = "评论内容", required = true,paramType="query", dataType = "String",defaultValue="asdfqw"),
        @ApiImplicitParam(name = "userId", value = "评论人id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "companionTopicId", value = "帖子id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })
    @RequestMapping(value="commentCompanionTopic.action",method=RequestMethod.POST)
    public RemoteResult<Long> commentCompanionTopic(Long userId,String content,Long companionTopicId) {
         if(content == null){
             throw new ParamException("内容不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
         if(companionTopicId == null){
             throw new ParamException("companionTopicId不能为空");
         }
         companionTopicService.commentCompanionTopic(userId,content,companionTopicId);
        return ResultUtils.createNullResult();
    }
    
    @ApiOperation(value="获取旅伴帖子详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "companionTopicId", value = "帖子id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })    
    @RequestMapping(value="findCompanionTopicDetailInfo.action",method=RequestMethod.POST)
    public RemoteResult<CompanionTopicDetailInfo> findCompanionTopicDetailInfo(Long userId,Long companionTopicId) {
         if(companionTopicId == null){
             throw new ParamException("companionTopicId不能为空");
         }
         if(userId == null){
             throw new ParamException("userId不能为空");
         }
        CompanionTopicDetailInfo companionTopicDetailInfo = companionTopicService.getCompanionTopicDetailInfo(companionTopicId,userId);
        return ResultUtils.createDefResult(companionTopicDetailInfo);
    }
    
    
    @ApiOperation(value="旅伴帖子回复")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "content", value = "回复内容", required = true,paramType="query", dataType = "String",defaultValue="asdfqw"),
        @ApiImplicitParam(name = "byReplyId", value = "被回复人Id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "userId", value = "回复人id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "companionTopicId", value = "帖子id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "byReplyContent", value = "回复内容", required = true,paramType="query", dataType = "String",defaultValue="a11sdfqw"),
    })
    @RequestMapping(value="replyCompanionTopic.action",method=RequestMethod.POST)
    public RemoteResult<Long> replyCompanionTopic(Long userId,String content,Long companionTopicId,Long byReplyId,String byReplyContent) {
         if(content == null){
             throw new ParamException("内容不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
         if(byReplyId == null){
             throw new ParamException("byReplyId不能为空");
         }
         if(companionTopicId == null){
             throw new ParamException("companionTopicId不能为空");
         }
         if(byReplyContent == null){
             throw new ParamException("byReplyContent不能为空");
         }
         companionTopicService.replyCompanionTopic(userId,content,companionTopicId,byReplyId,byReplyContent);
        return ResultUtils.createNullResult();
    }
    
    
    @ApiOperation(value="上传坐标")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "lng", value = "纬度", required = true,paramType="query", dataType = "double",defaultValue="116.481247"),
        @ApiImplicitParam(name = "lat", value = "经度", required = true,paramType="query", dataType = "double",defaultValue="39.990704"),
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="uploadCoord.action",method=RequestMethod.POST)
    public RemoteResult<Long> uploadCoord(Long userId,String lng,String lat) {
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
         if(lng == null){
             throw new ParamException("纬度不能为空");
         }
         if(lat == null){
             throw new ParamException("经度不能为空");
         }
        userService.uploadCoord(userId,lng,lat);
        return ResultUtils.createNullResult();
    }
    
    
    @ApiOperation(value="获取附近车友")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "lng", value = "纬度", required = true,paramType="query", dataType = "double",defaultValue="116.481247"),
        @ApiImplicitParam(name = "lat", value = "经度", required = true,paramType="query", dataType = "double",defaultValue="39.990704"),
        @ApiImplicitParam(name = "userId", value = "回复人id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="findnearbyRiders.action",method=RequestMethod.POST)
    public RemoteResult<Long> findnearbyRiders(Long userId,String lng,String lat) {
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
         if(lng == null){
             throw new ParamException("纬度不能为空");
         }
         if(lat == null){
             throw new ParamException("经度不能为空");
         }
        List<RidersInfo> ridersInfo = userService.findNearbyRiders(userId,lng,lat);
        return ResultUtils.createDefResult(ridersInfo);
    }
    
    
    @ApiOperation(value="设置陌生人对讲权限")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "isAllow", value = "是否允许陌生人对讲，true允许，false不允许", required = true,paramType="query", dataType = "boolean",defaultValue="true"),
        @ApiImplicitParam(name = "userId", value = "回复人id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="setStrangerTalkback.action",method=RequestMethod.POST)
    public RemoteResult<Long> setStrangerTalkback(Long userId,Boolean isAllow) {
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
         if(isAllow == null){
             throw new ParamException("isAllow不能为空");
         }
         userService.setStrangerTalkback(userId,isAllow);
        return ResultUtils.createNullResult();
    }
    
    
    @ApiOperation(value="创建群")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userIds", value = "用户id", required = true,paramType="query", dataType = "String",defaultValue="79"),
        @ApiImplicitParam(name = "userIds", value = "用户id", required = true,paramType="query", dataType = "String",defaultValue="92"),
        @ApiImplicitParam(name = "userIds", value = "用户id", required = true,paramType="query", dataType = "String",defaultValue="93"),
    })
    @RequestMapping(value="createGroup.action",method=RequestMethod.POST)
    public RemoteResult<String> createGroup(String[] userIds) {
        if(userIds == null||userIds.length==0){
            throw new ParamException("userIds不能为空");
        }
        String groupId= userService.createGroup(userIds);
        return ResultUtils.createDefResult(groupId);
    }
    
    @ApiOperation(value="退出群")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "String"),
        @ApiImplicitParam(name = "groupId", value = "群id", required = true,paramType="query", dataType = "String"),
    })
    @RequestMapping(value="quitGroup.action",method=RequestMethod.POST)
    public RemoteResult<Long> quitGroup(String userId,String groupId) {
        if(StringUtils.isBlank(userId)){
            throw new ParamException("userId不能为空");
        }
        if(StringUtils.isBlank(groupId)){
            throw new ParamException("groupId不能为空");
        }
        userService.quitGroup(userId,groupId);
        return ResultUtils.createNullResult();
    }
    
    @ApiOperation(value="找旅伴h5")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "companionTopicId", value = "游记id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })
    @RequestMapping(value="showCompanionTopicHtml")
    public ModelAndView showCompanionTopicHtml(Long companionTopicId) {
        ModelAndView modelAndView = new ModelAndView("h5/companionTopicHtml");
        if(companionTopicId == null){
            return modelAndView;
        }
        CompanionTopicDetailInfo companionTopicDetailInfo = companionTopicService.getCompanionTopicDetailInfo(companionTopicId, null);
        LogUtils.getInstance(getClass()).debugBean(companionTopicDetailInfo);
        modelAndView.addObject("companionTopicDetailInfo", companionTopicDetailInfo);
        return modelAndView;
    }
    
    @ApiOperation(value="社区h5")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "forumTopicId", value = "社区id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })
    @RequestMapping(value="showForumTopicHtml")
    public ModelAndView showForumTopicHtml(Long forumTopicId) {
        ModelAndView modelAndView = new ModelAndView("h5/forumTopicHtml");
        if(forumTopicId == null){
            return modelAndView;
        }
        ForumTopicDetailInfo forumTopicDetailInfo = forumTopicService.getForumTopicDetailInfo(forumTopicId, null);
        modelAndView.addObject("forumTopicDetailInfo", forumTopicDetailInfo);
        return modelAndView;
    }
	

}