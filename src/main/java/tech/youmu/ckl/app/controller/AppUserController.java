package tech.youmu.ckl.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tech.youmu.ckl.app.vo.BadgeInfo;
import tech.youmu.ckl.app.vo.CarIllegallyInfo;
import tech.youmu.ckl.app.vo.CarInfo;
import tech.youmu.ckl.app.vo.CollectStationInfo;
import tech.youmu.ckl.app.vo.CommissionInfo;
import tech.youmu.ckl.app.vo.CommissionMonthInfo;
import tech.youmu.ckl.app.vo.CommissionPointMonthInfo;
import tech.youmu.ckl.app.vo.CommissionYearInfo;
import tech.youmu.ckl.app.vo.IntegralMonthInfo;
import tech.youmu.ckl.app.vo.OrderDetailInfo;
import tech.youmu.ckl.app.vo.OrderInfo;
import tech.youmu.ckl.app.vo.PraiseDetailInfo;
import tech.youmu.ckl.app.vo.PresentServiceInfo;
import tech.youmu.ckl.app.vo.PromotionCodeInfo;
import tech.youmu.ckl.app.vo.RechargeComboInfo;
import tech.youmu.ckl.app.vo.RecommendInfo;
import tech.youmu.ckl.app.vo.RemoteResult;
import tech.youmu.ckl.app.vo.RongTokenInfo;
import tech.youmu.ckl.app.vo.TeamInfo;
import tech.youmu.ckl.app.vo.TopicCommentDetailInfo;
import tech.youmu.ckl.app.vo.TrackDetailInfo;
import tech.youmu.ckl.app.vo.TrackInfo;
import tech.youmu.ckl.app.vo.TrackMonthInfo;
import tech.youmu.ckl.app.vo.TravelNoteDetailInfo;
import tech.youmu.ckl.app.vo.TravelNoteMonthInfo;
import tech.youmu.ckl.app.vo.UserBillDetailInfo;
import tech.youmu.ckl.app.vo.UserBillMonthInfo;
import tech.youmu.ckl.app.vo.UserFriendApplyInfo;
import tech.youmu.ckl.app.vo.UserFriendInfo;
import tech.youmu.ckl.app.vo.UserInfo;
import tech.youmu.ckl.app.vo.UserRouteInfo;
import tech.youmu.ckl.app.vo.WeixinInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.Feedback;
import tech.youmu.ckl.domain.ServiceEvaluation;
import tech.youmu.ckl.domain.TravelNote;
import tech.youmu.ckl.domain.TravelNoteContent;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.domain.UserCar;
import tech.youmu.ckl.exception.ParamException;
import tech.youmu.ckl.service.IBadgeService;
import tech.youmu.ckl.service.ICarService;
import tech.youmu.ckl.service.ICommissionService;
import tech.youmu.ckl.service.IFeedbackService;
import tech.youmu.ckl.service.IForumTopicService;
import tech.youmu.ckl.service.IFriendService;
import tech.youmu.ckl.service.IIntegralService;
import tech.youmu.ckl.service.IOrderService;
import tech.youmu.ckl.service.IRechargeComboService;
import tech.youmu.ckl.service.ISosService;
import tech.youmu.ckl.service.IStationService;
import tech.youmu.ckl.service.ITrackService;
import tech.youmu.ckl.service.ITravelNoteService;
import tech.youmu.ckl.service.IUserBillService;
import tech.youmu.ckl.service.IUserDistributionService;
import tech.youmu.ckl.service.IUserRouteService;
import tech.youmu.ckl.service.IUserService;
import tech.youmu.ckl.utils.QRCodeUtil;
import tech.youmu.ckl.utils.ResultUtils;

@RestController
@RequestMapping(value="/user/remote")    
@Api(description="我的页面")
public class AppUserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IBadgeService badgeService;
	
	@Autowired
	private ICarService carService;
	
	@Autowired
	private IIntegralService integralService;
	
	@Autowired
	private IFriendService friendService;
	
	@Autowired
	private IUserBillService userBillService;
	
	@Autowired
	private ITrackService trackService;
	
	@Autowired
	private IStationService stationService;
	
	@Autowired
	private IFeedbackService feedbackService;
	
	@Autowired
    private IOrderService orderService;
	
	@Autowired
    private ITravelNoteService travelNoteService;
	
	@Autowired
    private IForumTopicService forumTopicService;

	@Autowired
	private ICommissionService commissionService;

	@Autowired
	private IUserDistributionService userDistributionService;
	
	@Autowired
    private IUserRouteService userRouteService;
	
	@Autowired
    private IRechargeComboService rechargeComboService;
	
	@Autowired
    private ISosService sosService;
	
	@ApiOperation(value="我的信息")
	@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    @RequestMapping(value="getUserInfo.action",method=RequestMethod.POST)
    public RemoteResult<UserInfo> getUserInfo(@RequestParam(value="userId") Long userId) {
		 if(userId == null){
			 throw new ParamException("userId不能为空");
		 }
		UserInfo userInfo = userService.getUserInfo(userId);
        return ResultUtils.createDefResult(userInfo);
    }
	
	@ApiOperation(value="徽章信息")
	@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    @RequestMapping(value="findBadgeInfo.action",method=RequestMethod.POST)
    public RemoteResult<BadgeInfo> findBadgeInfo(@RequestParam(value="userId") Long userId) {
		if(userId == null){
			 throw new ParamException("userId不能为空");
		}
		List<BadgeInfo> badgeInfos = badgeService.findBadgeInfo(userId);
        return ResultUtils.createDefResult(badgeInfos);
    }
	
	@ApiOperation(value="我的车牌")
	@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    @RequestMapping(value="findCarInfo.action",method=RequestMethod.POST)
    public RemoteResult<List<CarInfo>> findCarInfo(@RequestParam(value="userId") Long userId) {
		if(userId == null){
			 throw new ParamException("userId不能为空");
		}
		List<CarInfo> carInfos = carService.findCarInfo(userId);
        return ResultUtils.createDefResult(carInfos);
    }
	
	@ApiOperation(value="积分明细")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
	})
	@RequestMapping(value="findIntegralMonthInfo.action",method=RequestMethod.POST)
    public RemoteResult<IntegralMonthInfo> findIntegralDetailInfo(@RequestParam(value="userId") Long userId) {
		if(userId == null){
			 throw new ParamException("userId不能为空");
		}
		List<IntegralMonthInfo> integralMonthInfos = integralService.findIntegralMonthInfo(userId);
        return ResultUtils.createDefResult(integralMonthInfos);
    }
	
	@ApiOperation(value="添加车牌")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
		@ApiImplicitParam(name = "carType", value = "车辆类型", required = true,paramType="query", dataType = "String"),
		@ApiImplicitParam(name = "plateNumber", value = "车牌", required = true,paramType="query", dataType = "String"),
		@ApiImplicitParam(name = "engineNumber", value = "发动机号", required = true,paramType="query", dataType = "String"),
        @ApiImplicitParam(name = "frameNumber", value = "车架号", required = true,paramType="query", dataType = "String")
	})	
	@RequestMapping(value="saveCar.action",method=RequestMethod.POST)
    public RemoteResult saveCar(Long userId, String carType,String plateNumber,String engineNumber,String frameNumber) {
		if(userId == null){
			 throw new ParamException("userId不能为空");
		}
		if(StringUtils.isBlank(carType)){
			 throw new ParamException("车辆类型不能为空");
		}
		if(StringUtils.isBlank(plateNumber)){
			 throw new ParamException("车牌不能为空");
		}
		if(StringUtils.isBlank(engineNumber)){
            throw new ParamException("发动机号不能为空");
       }
		if(StringUtils.isBlank(frameNumber)){
            throw new ParamException("车架号不能为空");
       }
		carService.save(new UserCar(userId,carType,plateNumber,engineNumber,frameNumber));
        return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value="修改车牌")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "carId", value = "汽车id", required = true,paramType="query", dataType = "long",defaultValue="1"),
		@ApiImplicitParam(name = "carType", value = "车辆类型", required = true,paramType="query", dataType = "String"),
        @ApiImplicitParam(name = "plateNumber", value = "车牌", required = true,paramType="query", dataType = "String"),
        @ApiImplicitParam(name = "engineNumber", value = "发动机号", required = true,paramType="query", dataType = "String"),
        @ApiImplicitParam(name = "frameNumber", value = "车架号", required = true,paramType="query", dataType = "String")
	})	
	@RequestMapping(value="updateCar.action",method=RequestMethod.POST)
    public RemoteResult updateCar(Long carId, String carType,String plateNumber,String engineNumber,String frameNumber) {
	    if(carId == null){
            throw new ParamException("carId不能为空");
       }
	    if(StringUtils.isBlank(carType)){
            throw new ParamException("车辆类型不能为空");
       }
       if(StringUtils.isBlank(plateNumber)){
            throw new ParamException("车牌不能为空");
       }
       if(StringUtils.isBlank(engineNumber)){
           throw new ParamException("发动机号不能为空");
      }
       if(StringUtils.isBlank(frameNumber)){
           throw new ParamException("车架号不能为空");
      }
		carService.update(new UserCar(carId,null,carType,plateNumber,engineNumber,frameNumber));
        return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value="删除车牌")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "carId", value = "汽车id", required = true,paramType="query", dataType = "long",defaultValue="1")
	})	
	@RequestMapping(value="deleteCar.action",method=RequestMethod.POST)
    public RemoteResult deleteCar(Long carId) {
		if(carId == null){
			 throw new ParamException("carId不能为空");
		}
		carService.delete(carId);
        return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value="设置默认车")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "carId", value = "汽车id", required = true,paramType="query", dataType = "long",defaultValue="2"),
		@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
	})	
	@RequestMapping(value="setDefaultCar.action",method=RequestMethod.POST)
    public RemoteResult setDefaultCar(Long carId,Long userId) {
		if(carId == null){
			 throw new ParamException("carId不能为空");
		}
		if(userId == null){
			 throw new ParamException("userId不能为空");
		}
		carService.setDefaultCar(carId,userId);
        return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value="添加好友申请")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "friendId", value = "好友id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "userId", value = "用户id,申请人id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "remark", value = "说明内容", required = true,paramType="query", dataType = "String",defaultValue="add")
    })
    @RequestMapping(value="addUserFriendApply.action",method=RequestMethod.POST)
    public RemoteResult addUserFriendApply(Long friendId,Long userId,String remark) {
         if(friendId == null){
             throw new ParamException("friendId不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
         if(remark == null){
             throw new ParamException("说明不能为空");
         }
        friendService.addUserFriendApply(friendId,userId,remark);
        return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value="好友申请列表")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    @RequestMapping(value="findUserFriendApplyInfo.action",method=RequestMethod.POST)
    public RemoteResult<UserFriendApplyInfo> findUserFriendApplyInfo(Long userId) {
        if(userId == null){
             throw new ParamException("userId不能为空");
        }
        List<UserFriendApplyInfo> userFriendApplyInfos = friendService.findUserFriendApplyInfo(userId);
        return ResultUtils.createDefResult(userFriendApplyInfos);
    }
	
	@ApiOperation(value="同意添加好友")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userFriendApplyId", value = "申请好友id", required = true,paramType="query", dataType = "long",defaultValue="10"),
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="agreeAddUserFriend.action",method=RequestMethod.POST)
    public RemoteResult agreeAddUserFriend(Long userFriendApplyId,Long userId) {
         if(userFriendApplyId == null){
             throw new ParamException("userFriendApplyId不能为空");
         }
         if(userId == null){
             throw new ParamException("userId不能为空");
         }
        friendService.agreeAddUserFriend(userFriendApplyId,userId);
        return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value="好友列表")
	@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
	@RequestMapping(value="findUserFriendInfo.action",method=RequestMethod.POST)
    public RemoteResult<UserFriendInfo> findUserFriendInfo(Long userId) {
		if(userId == null){
			 throw new ParamException("userId不能为空");
		}
		List<UserFriendInfo> friendInfos = friendService.findUserFriendInfo(userId);
        return ResultUtils.createDefResult(friendInfos);
    }
	
	@ApiOperation(value="获取好友信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "friendId", value = "好友id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="getUserFriendInfo.action",method=RequestMethod.POST)
    public RemoteResult<UserFriendInfo> getUserFriendInfo(Long friendId,Long userId) {
         if(friendId == null){
             throw new ParamException("friendId不能为空");
         }
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        UserFriendInfo friendInfo = friendService.getUserFriendInfo(friendId,userId);
        return ResultUtils.createDefResult(friendInfo);
    }
	
	@ApiOperation(value="根据手机号查询好友信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "mobile", value = "手机号", required = true,paramType="query", dataType = "String",defaultValue="1")
    })
    @RequestMapping(value="getUserFriendInfoByMobile.action",method=RequestMethod.POST)
    public RemoteResult<UserFriendInfo> getUserFriendInfoByMobile(Long userId,String mobile) {
         if(userId == null){
             throw new ParamException("userId不能为空");
         }
         if(mobile == null){
             throw new ParamException("手机号不能为空");
         }
        UserFriendInfo friendInfo = friendService.getUserFriendInfoByMobile(userId,mobile);
        return ResultUtils.createDefResult(friendInfo);
    }
	
	@ApiOperation(value="设置好友备注")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "好友关系id,即friendInfo id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "note", value = "好友id", required = true,paramType="query", dataType = "String",defaultValue="张三")
    })    @RequestMapping(value="setUserFriendNote.action",method=RequestMethod.POST)
    public RemoteResult setUserFriendNote(Long id,String note) {
        if(id == null){
             throw new ParamException("id不能为空");
        }
        if(StringUtils.isBlank(note)){
            throw new ParamException("note不能为空");
       }
       friendService.setUserFriendNote(id, note);
       return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value="删除好友")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "friendId", value = "好友id", required = true,paramType="query", dataType = "long",defaultValue="3")
    })    
	@RequestMapping(value="deleteUserFriend.action",method=RequestMethod.POST)
    public RemoteResult deleteUserFriend(Long userId,Long friendId) {
        if(userId == null){
             throw new ParamException("userId不能为空");
        }
        if(friendId == null){
            throw new ParamException("friendId不能为空");
       }
       friendService.deleteUserFriend(userId, friendId);
       return ResultUtils.createNullResult();
    }

	@ApiOperation(value="消费记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
	})
	@RequestMapping(value="findUserBillMonthInfo.action",method=RequestMethod.POST)
    public RemoteResult<UserBillMonthInfo> findUserBillMonthInfo(@RequestParam(value="userId") Long userId) {
		if(userId == null){
			 throw new ParamException("userId不能为空");
		}
		List<UserBillMonthInfo> userBillMonthInfos = userBillService.findUserBillMonthInfo(userId);
        return ResultUtils.createDefResult(userBillMonthInfos);
    }
	
	@ApiOperation(value="消费记录明细")
    @ApiImplicitParam(name = "billId", value = "账单id", required = true,paramType="query", dataType = "long",defaultValue="1")
    @RequestMapping(value="getUserBillDetailInfo.action",method=RequestMethod.POST)
    public RemoteResult<UserBillDetailInfo> getUserBillDetailInfo(@RequestParam(value="billId") Long billId) {
        if(billId == null){
             throw new ParamException("billId不能为空");
        }
        UserBillDetailInfo billDetailInfo = userBillService.getUserBillDetailInfo(billId);
        return ResultUtils.createDefResult(billDetailInfo);
    }

	@ApiOperation(value="我的轨迹")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
	})
	@RequestMapping(value="findTrackMonthInfo.action",method=RequestMethod.POST)
    public RemoteResult<TrackMonthInfo> findTrackMonthInfo(@RequestParam(value="userId") Long userId) {
		if(userId == null){
			 throw new ParamException("userId不能为空");
		}
		List<TrackMonthInfo> trackMonthInfos = trackService.findTrackMonthInfo(userId);
        return ResultUtils.createDefResult(trackMonthInfos);
    }
	
	
	@ApiOperation(value="我的轨迹明细")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "trackId", value = "轨迹id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })
    @RequestMapping(value="getTrackDetailInfo.action",method=RequestMethod.POST)
    public RemoteResult<TrackDetailInfo> getTrackDetailInfo(@RequestParam(value="trackId") Long trackId) {
        if(trackId == null){
             throw new ParamException("trackId不能为空");
        }
        TrackDetailInfo trackDetailInfo = trackService.getTrackDetailInfo(trackId,null);
        return ResultUtils.createDefResult(trackDetailInfo);
    }
	
	
	@ApiOperation(value="我的收藏的服务站")
	@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
	@RequestMapping(value="findCollectStationInfo.action",method=RequestMethod.POST)
    public RemoteResult<List<CollectStationInfo>> findCollectStationInfo(@RequestParam(value="userId") Long userId) {
		if(userId == null){
			 throw new ParamException("userId不能为空");
		}
		List<CollectStationInfo> collectStationInfos = stationService.findCollectStationInfo(userId);
        return ResultUtils.createDefResult(collectStationInfos);
    }
	
	@ApiOperation(value="意见反馈")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
		@ApiImplicitParam(name = "content", value = "内容", required = true,paramType="query", dataType = "String",defaultValue="好"),
	    @ApiImplicitParam(name = "byReportUserId", value = "被举报用户Id", required = true,paramType="query", dataType = "long"),
	    @ApiImplicitParam(name = "topicId", value = "帖子Id", required = true,paramType="query", dataType = "long"),
	    @ApiImplicitParam(name = "type", value = "1-帖子,2-结伴", required = true,paramType="query", dataType = "long"),
	})
	@RequestMapping(value="saveFeedback.action",method=RequestMethod.POST)
    public RemoteResult<List<TrackInfo>> saveFeedback(Long userId, String content,Long byReportUserId,Long topicId,Integer type) {
		if(userId == null){
			 throw new ParamException("参数不能为空");
		}
		if(StringUtils.isBlank(content)){
			 throw new ParamException("内容不能为空");
		}
		feedbackService.saveFeedback(new Feedback(userId,content));
        return ResultUtils.createNullResult();
    }
	
	

	@ApiOperation(value="验证登陆密码")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
		@ApiImplicitParam(name = "loginPassword", value = "密码", required = true,paramType="query", dataType = "String",defaultValue="123456")
	})
	@RequestMapping(value="verifyLoginPassword.action",method=RequestMethod.POST)
    public RemoteResult verifyLoginPassword(Long userId,String loginPassword) {
		if(userId == null){
			 throw new ParamException("userId不能为空");
		}
		if(StringUtils.isBlank(loginPassword)){
			 throw new ParamException("密码不能为空");
		}
		userService.verifyLoginPassword(userId,loginPassword);
        return ResultUtils.createNullResult();
    }
	
	
	@ApiOperation(value="修改登陆密码")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
		@ApiImplicitParam(name = "newLoginPassword", value = "新密码", required = true,paramType="query", dataType = "String",defaultValue="123456"),
		@ApiImplicitParam(name = "oldLoginPassword", value = "旧密码", required = true,paramType="query", dataType = "String",defaultValue="12345678")
	})
	@RequestMapping(value="updateLoginPassword.action",method=RequestMethod.POST)
    public RemoteResult updateLoginPassword(Long userId,String newLoginPassword,String oldLoginPassword) {
		if(userId == null){
			 throw new ParamException("userId不能为空");
		}
		if(StringUtils.isBlank(newLoginPassword)){
			 throw new ParamException("新密码不能为空");
		}
		if(StringUtils.isBlank(oldLoginPassword)){
			 throw new ParamException("旧密码不能为空");
		}
		userService.updateLoginPassword(userId,newLoginPassword,oldLoginPassword);
        return ResultUtils.createNullResult();
    }
	
	
	@ApiOperation(value="验证支付密码")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
		@ApiImplicitParam(name = "payPassword", value = "支付密码", required = true,paramType="query", dataType = "String",defaultValue="123456")
	})
	@RequestMapping(value="verifyPayPassword.action",method=RequestMethod.POST)
    public RemoteResult verifyPayPassword(Long userId,String payPassword) {
		if(userId == null){
			 throw new ParamException("userId不能为空");
		}
		if(StringUtils.isBlank(payPassword)){
			 throw new ParamException("支付密码不能为空");
		}
		userService.verifyPayPassword(userId,payPassword);
        return ResultUtils.createNullResult();
    }
	
	
	
	@ApiOperation(value="修改支付密码")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
		@ApiImplicitParam(name = "newPayPassword", value = "新支付密码", required = true,paramType="query", dataType = "String",defaultValue="123456"),
		@ApiImplicitParam(name = "oldPayPassword", value = "旧支付密码", required = false,paramType="query", dataType = "String",defaultValue="123456")
	})
	@RequestMapping(value="updatePayPassword.action",method=RequestMethod.POST)
    public RemoteResult updatePayPassword(Long userId,String newPayPassword,String oldPayPassword) {
		if(userId == null){
			 throw new ParamException("userId不能为空");
		}
		if(StringUtils.isBlank(newPayPassword)){
			 throw new ParamException("新支付密码不能为空");
		}
		userService.updatePayPassword(userId,newPayPassword,oldPayPassword);
        return ResultUtils.createNullResult();
    }
	
	
	@ApiOperation(value="退出登陆")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "token", required = true,paramType="header", dataType = "String",defaultValue="79")
	})
	@RequestMapping(value="logout.action",method=RequestMethod.POST)
    public RemoteResult logout(@RequestHeader("token") String token) {
		userService.logout(token);
        return ResultUtils.createNullResult();
    }
	
	
	@ApiOperation(value="订单列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "第几页", required = true,paramType="query", dataType = "int",defaultValue="1"),
        @ApiImplicitParam(name = "rows", value = "每页个数", required = true,paramType="query", dataType = "int",defaultValue="10"),
        @ApiImplicitParam(name = "userId", value = "userId", required = true,paramType="query", dataType = "Long",defaultValue="79"),
        @ApiImplicitParam(name = "status", value = "订单状态", required = false,paramType="query", dataType = "int")
    })
    @RequestMapping(value="findOrderInfo.action",method=RequestMethod.POST)
    public RemoteResult<OrderInfo> findOrderInfo(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer rows,Long userId,Integer status) {
	    if(userId == null){
            throw new ParamException("userId不能为空");
	    }
	    List<OrderInfo> orderInfos = orderService.findOrderInfo(page,rows,userId,null,status);
        return ResultUtils.createDefResult(orderInfos);
    }
	
	@ApiOperation(value="订单明细")
    @ApiImplicitParam(name = "orderId", value = "orderId", required = true,paramType="query", dataType = "Long",defaultValue="1")
    @RequestMapping(value="getOrderDetailInfo.action",method=RequestMethod.POST)
    public RemoteResult<OrderDetailInfo> getOrderDetailInfo(Long orderId) {
        if(orderId == null){
            throw new ParamException("orderId不能为空");
        }
        OrderDetailInfo orderDetailInfo = orderService.getOrderDetailInfo(orderId);
        return ResultUtils.createDefResult(orderDetailInfo);
    }
	
	@ApiOperation(value="上传头像")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "userId", value = "userId", required = true,paramType="query", dataType = "Long",defaultValue="79"),
	    @ApiImplicitParam(name = "headIcon", value = "图片", required = false,paramType="form", dataType = "file")
	})
	@RequestMapping(value="uploadUserHeadIcon.action",method=RequestMethod.POST)
    public RemoteResult<String> uploadUserHeadIcon(Long userId,MultipartFile headIcon) {
        if(userId == null){
            throw new ParamException("userId不能为空");
        }
        if(headIcon == null){
            throw new ParamException("headIcon不能为空");
        }
        String headIconPath = userService.uploadHeadIcon(userId,headIcon);
        return ResultUtils.createDefResult(headIconPath);
    }
	
	
	@ApiOperation(value="修改用户信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "nickName", value = "昵称", required = false,paramType="query", dataType = "String",defaultValue="张三"),
        @ApiImplicitParam(name = "sex", value = "性别（1：女，2：男）", required = true,paramType="query", dataType = "int",defaultValue="1"),
        @ApiImplicitParam(name = "realName", value = "真是姓名", required = false,paramType="query", dataType = "String",defaultValue="张"),
        @ApiImplicitParam(name = "identityCard", value = "身份证号", required = true,paramType="query", dataType = "String",defaultValue="123456789"),
    })
    @RequestMapping(value="updateUserInfo.action",method=RequestMethod.POST)
    public RemoteResult updateUserInfo(Long userId,String nickName,Integer sex,String realName,String identityCard) {
        if(userId == null){
             throw new ParamException("userId参数不能为空");
        }
        userService.updateUserInfo(new User(userId,nickName,sex,realName,identityCard));
        return ResultUtils.createNullResult();
    }
	
    @ApiOperation(value="订单评价")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orderId", value = "订单id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "content", value = "评价内容", required = true,paramType="query", dataType = "String",defaultValue="aaaaaaa"),
        @ApiImplicitParam(name = "serviceEvaluationDetails[0].name", value = "服务名称", required = true,paramType="query", dataType = "String",defaultValue="态度好"),
        @ApiImplicitParam(name = "serviceEvaluationDetails[0].star", value = "星级", required = true,paramType="query", dataType = "int",defaultValue="4")
    })
    @RequestMapping(value="orderServiceEvaluation.action",method=RequestMethod.POST)
    public RemoteResult orderServiceEvaluation(ServiceEvaluation serviceEvaluation) {
        if(serviceEvaluation == null||serviceEvaluation.getOrderId() ==null ){
             throw new ParamException("orderId参数不能为空");
        }
        if(serviceEvaluation.getContent() ==null){
            throw new ParamException("内容不能为空");
        }
        if(serviceEvaluation.getServiceEvaluationDetails()==null ||serviceEvaluation.getServiceEvaluationDetails().size()==0){
            throw new ParamException("星级评级不能为空");
       }
       orderService.orderServiceEvaluation(serviceEvaluation);
       return ResultUtils.createNullResult();
    }
    
    
    @ApiOperation(value="订单申请退款")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orderId", value = "订单id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "explain", value = "退款说明", required = true,paramType="query", dataType = "String",defaultValue="11111"),
        @ApiImplicitParam(name = "reason", value = "退款原因", required = true,paramType="query", dataType = "String",defaultValue="222"),
        @ApiImplicitParam(name = "amount", value = "退款金额", required = true,paramType="query", dataType = "double",defaultValue="200")
    })
    @RequestMapping(value="orderApplyRefund.action",method=RequestMethod.POST)
    public RemoteResult orderApplyRefund(Long orderId,String explain,String reason,Double amount) {
        if(orderId ==null ){
             throw new ParamException("orderId参数不能为空");
        }
        if(StringUtils.isBlank(reason)){
            throw new ParamException("退款原因不能为空");
        }
        if(amount == null){
            throw new ParamException("退款金额不能为空");
        }
       orderService.orderApplyRefund(orderId,explain,reason,amount);
       return ResultUtils.createNullResult();
    }
    
    
    @ApiOperation(value="我的游记")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="findTravelNoteMonthInfo.action",method=RequestMethod.POST)
    public RemoteResult<TravelNoteMonthInfo> findTravelNoteMonthInfo(@RequestParam(value="userId") Long userId) {
        if(userId == null){
             throw new ParamException("userId不能为空");
        }
        List<TravelNoteMonthInfo> travelNoteMonthInfos = travelNoteService.findTravelNoteMonthInfo(userId);
        return ResultUtils.createDefResult(travelNoteMonthInfos);
    }
    
    
    @ApiOperation(value="我的游记明细")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "travelNoteId", value = "游记id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })
    @RequestMapping(value="getTravelNoteDetailInfo.action",method=RequestMethod.POST)
    public RemoteResult<TravelNoteDetailInfo> getTravelNoteDetailInfo(@RequestParam(value="travelNoteId") Long travelNoteId) {
        if(travelNoteId == null){
             throw new ParamException("travelNoteId不能为空");
        }
        TravelNoteDetailInfo travelNoteDetailInfo = travelNoteService.getTravelNoteDetailInfo(travelNoteId,null);
        return ResultUtils.createDefResult(travelNoteDetailInfo);
    }
    
    @ApiOperation(value="修改游记")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "游记id,即travelNoteId", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "title", value = "游记标题(没修改可以不用传)", required = false,paramType="query", dataType = "String",defaultValue="aaaa"),
        @ApiImplicitParam(name = "capitaCost", value = "人均消费(没修改可以不用传)", required = false,paramType="query", dataType = "double",defaultValue="100"),
        @ApiImplicitParam(name = "dayCount", value = "游玩天数(没修改可以不用传)", required = false,paramType="query", dataType = "int",defaultValue="1"),
        @ApiImplicitParam(name = "travelNoteContents[0].id", value = "travelNoteContentId,修改时候传这个参数，增加不用传，没修改这个条数据不传", required = false,paramType="query", dataType = "int"),
        @ApiImplicitParam(name = "travelNoteContents[0].type", value = "1-文字，2-图片(没有文字或者图片的修改travelNoteContents整个参数可以不用传)", required = false,paramType="query", dataType = "int"),
        @ApiImplicitParam(name = "travelNoteContents[1].type", value = "1-文字，2-图片(没有文字或者图片的修改travelNoteContents整个参数可以不用传)", required = false,paramType="query", dataType = "int"),
        @ApiImplicitParam(name = "travelNoteContents[0].order", value = "顺序", required = false,paramType="query", dataType = "int"),
        @ApiImplicitParam(name = "travelNoteContents[1].order", value = "顺序", required = false,paramType="query", dataType = "int"),
        @ApiImplicitParam(name = "travelNoteContents[0].content", value = "文字", required = false,paramType="query", dataType = "String"),
        @ApiImplicitParam(name = "travelNoteContents[0].img", value = "图片", required = false,paramType="form", dataType = "file"),
        @ApiImplicitParam(name = "travelNoteContents[1].content", value = "文字", required = false,paramType="query", dataType = "String"),
        @ApiImplicitParam(name = "travelNoteContents[1].img", value = "图片", required = false,paramType="form", dataType = "file"),
        @ApiImplicitParam(name = "suitNames[0]", value = "适合人群(没修改可以不用传)", required = false,paramType="query", dataType = "String"),
        @ApiImplicitParam(name = "suitNames[1]", value = "适合人群(没修改可以不用传)", required = false,paramType="query", dataType = "String")

    })
    @RequestMapping(value="updateTravelNote.action",method=RequestMethod.POST)
    public RemoteResult updateTravelNote(TravelNote travelNote) {
        if(travelNote == null||travelNote.getId() == null){
             throw new ParamException("travelNoteId不能为空");
        }
        if(travelNote.getTravelNoteContents() !=null){
            int i=0;
            for(TravelNoteContent travelNoteContent:travelNote.getTravelNoteContents()){
                if(travelNoteContent.getType()==null){
                    throw new ParamException("travelNoteContents["+i+"].type不能为空");
                }
                if(travelNoteContent.getOrder()==null){
                    throw new ParamException("travelNoteContents["+i+"].order不能为空");
                }
                if(StatusConst.ONE == travelNoteContent.getType()&&StringUtils.isBlank(travelNoteContent.getContent())){
                    throw new ParamException("travelNoteContents["+i+"].content不能为空");
                }
                if(travelNoteContent.getId()==null&&StatusConst.TWO == travelNoteContent.getType()&&travelNoteContent.getImg() == null){
                    throw new ParamException("travelNoteContents["+i+"].img不能为空");
                }
                i++;
            }
        }
        travelNoteService.updateTravelNote(travelNote);
        return ResultUtils.createNullResult();
    }
    
    
    @ApiOperation(value="点赞明细列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="findPraiseDetailInfo.action",method=RequestMethod.POST)
    public RemoteResult<PraiseDetailInfo> findPraiseDetailInfo(Long userId) {
         if(userId == null){
             throw new ParamException("userId不能为空");
         }
        List<PraiseDetailInfo> praiseDetailInfos = forumTopicService.findPraiseDetailInfo(userId);
        return ResultUtils.createDefResult(praiseDetailInfos);
    }
    
    
    @ApiOperation(value="评论明细列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "type", value = "0-所有，1-评论我的，2-我发出的", required = true,paramType="query", dataType = "int",defaultValue="0")
    })
    @RequestMapping(value="findTopicCommentDetailInfo.action",method=RequestMethod.POST)
    public RemoteResult<TopicCommentDetailInfo> findTopicCommentDetailInfo(Long userId,Integer type) {
         if(userId == null){
             throw new ParamException("userId不能为空");
         }
         if(type == null){
             throw new ParamException("type不能为空");
         }
        List<TopicCommentDetailInfo> topicCommentDetailInfos = forumTopicService.findTopicCommentDetailInfo(userId,type);
        return ResultUtils.createDefResult(topicCommentDetailInfos);
    }
    
    @ApiOperation(value="我的推荐信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="getRecommendInfo.action",method=RequestMethod.POST)
    public RemoteResult<RecommendInfo> getRecommendInfo(Long userId) {
         if(userId == null){
             throw new ParamException("userId不能为空");
         }
        RecommendInfo recommendInfo = userDistributionService.getRecommendInfo(userId);
        return ResultUtils.createDefResult(recommendInfo);
    }
    
    
    @ApiOperation(value="我的佣金信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="getCommissionInfo.action",method=RequestMethod.POST)
    public RemoteResult<CommissionInfo> getCommissionInfo(Long userId) {
         if(userId == null){
             throw new ParamException("userId不能为空");
         }
         CommissionInfo commissionInfo = commissionService.getCommissionInfo(userId);
        return ResultUtils.createDefResult(commissionInfo);
    }
    
    @ApiOperation(value="佣金点数明细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="findCommissionPointMonthInfo.action",method=RequestMethod.POST)
    public RemoteResult<CommissionPointMonthInfo> findCommissionPointMonthInfo(Long userId) {
         if(userId == null){
             throw new ParamException("userId不能为空");
         }
         List<CommissionPointMonthInfo> commissionPointMonthInfos = commissionService.findCommissionPointMonthInfo(userId);
        return ResultUtils.createDefResult(commissionPointMonthInfos);
    }
    
    
    @ApiOperation(value="佣金明细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="findCommissionMonthInfo.action",method=RequestMethod.POST)
    public RemoteResult<CommissionMonthInfo> findCommissionMonthInfo(Long userId) {
         if(userId == null){
             throw new ParamException("userId不能为空");
         }
         List<CommissionMonthInfo> commissionMonthInfos = commissionService.findCommissionMonthInfo(userId);
        return ResultUtils.createDefResult(commissionMonthInfos);
    }
    
    
    @ApiOperation(value="年度总佣金信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="findCommissionYearInfo.action",method=RequestMethod.POST)
    public RemoteResult<CommissionYearInfo> findCommissionYearInfo(Long userId) {
         if(userId == null){
             throw new ParamException("userId不能为空");
         }
         List<CommissionYearInfo> commissionYearInfos = commissionService.findCommissionYearInfo(userId);
        return ResultUtils.createDefResult(commissionYearInfos);
    }
    
    
    @ApiOperation(value="我的团队信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="findTeamInfo.action",method=RequestMethod.POST)
    public RemoteResult<TeamInfo> findTeamInfo(Long userId) {
         if(userId == null){
             throw new ParamException("userId不能为空");
         }
         List<TeamInfo> teamInfos = userDistributionService.findTeamInfo(userId);
        return ResultUtils.createDefResult(teamInfos);
    }

    
    @ApiOperation(value = "我的行程列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "第几页", required = true,paramType="query", dataType = "int",defaultValue="1"),
        @ApiImplicitParam(name = "rows", value = "每页个数", required = true,paramType="query", dataType = "int",defaultValue="10"),
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value = "findUserRouteInfo.action", method = RequestMethod.POST)
    public RemoteResult<UserRouteInfo> findRouteInfo(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer rows,Long userId) {
        if(userId == null){
            throw new ParamException("userId不能为空");
        }
        List<UserRouteInfo> userRouteInfos = userRouteService.findUserRouteInfo(page,rows,userId);
        return ResultUtils.createDefResult(userRouteInfos);
    }
    
    @ApiOperation(value = "删除行程")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userRouteId", value = "行程id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value = "deleteUserRoute.action", method = RequestMethod.POST)
    public RemoteResult deleteUserRoute(Long userRouteId) {
        if(userRouteId == null){
            throw new ParamException("userRouteId不能为空");
        }
        userRouteService.deleteUserRoute(userRouteId);
        return ResultUtils.createNullResult();
    }
    
    @ApiOperation(value = "微信充值")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户Id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "amount", value = "支付金额", required = true,paramType="query", dataType = "double",defaultValue="0.01"),
        @ApiImplicitParam(name = "rechargeComboId", value = "充值套餐id", required = false,paramType="query", dataType = "long"),
        @ApiImplicitParam(name = "promotionCode", value = "推广代码", required = false,paramType="query", dataType = "String")
    })
    @RequestMapping(value = "rechargeWeixin.action", method = RequestMethod.POST)
    public RemoteResult<String> rechargeWeixin(HttpServletRequest request,Long userId,Double amount,Long rechargeComboId,String promotionCode) {
        if(amount == null){
            throw new ParamException("amount不能为空");
        }
        if(userId == null){
            throw new ParamException("userId不能为空");
        }
        
        WeixinInfo weixinInfo = userBillService.rechargeWeixin(userId,amount,request.getRemoteAddr(),rechargeComboId,promotionCode);
        return ResultUtils.createDefResult(weixinInfo);
    }
    
    @ApiOperation(value = "支付宝充值")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户Id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "amount", value = "支付金额", required = true,paramType="query", dataType = "double",defaultValue="0.01"),
        @ApiImplicitParam(name = "rechargeComboId", value = "充值套餐id", required = false,paramType="query", dataType = "long"),
        @ApiImplicitParam(name = "promotionCode", value = "推广代码", required = false,paramType="query", dataType = "String")
    })
    @RequestMapping(value = "rechargeAlipay.action", method = RequestMethod.POST)
    public RemoteResult<String> rechargeAlipay(Long userId,Double amount,Long rechargeComboId,String promotionCode) {
        if(amount == null){
            throw new ParamException("amount不能为空");
        }
        if(userId == null){
            throw new ParamException("userId不能为空");
        }
        
        String alipayInfo = userBillService.rechargeAlipay(userId,amount,rechargeComboId,promotionCode);
        return ResultUtils.createDefResult(alipayInfo);
    }
    
    
    @ApiOperation(value = "充值套餐信息")
    @RequestMapping(value = "findRechargeComboInfo.action", method = RequestMethod.POST)
    public RemoteResult<RechargeComboInfo> findRechargeComboInfo() {
        List<RechargeComboInfo> rechargeComboInfos = rechargeComboService.findRechargeComboInfo();
        return ResultUtils.createDefResult(rechargeComboInfos);
    }
    
    @ApiOperation(value = "充值套餐赠送服务信息")
    @ApiImplicitParam(name = "rechargeComboId", value = "套餐Id", required = true,paramType="query", dataType = "long",defaultValue="79")
    @RequestMapping(value = "findPresentServiceInfoByRechargeComboId.action", method = RequestMethod.POST)
    public RemoteResult<PresentServiceInfo> findPresentServiceInfoByRechargeComboId(Long rechargeComboId) {
        if(rechargeComboId == null){
            throw new ParamException("rechargeComboId不能为空");
        }
        List<PresentServiceInfo> presentServiceInfos = rechargeComboService.findPresentServiceInfoByRechargeComboId(rechargeComboId);
        return ResultUtils.createDefResult(presentServiceInfos);
    }
    
    @ApiOperation(value = "根据推广码获取充值套餐信息")
    @ApiImplicitParam(name = "promotionCode", value = "推广码", required = true,paramType="query", dataType = "String",defaultValue="A1234")
    @RequestMapping(value = "getRechargeComboInfoByPromotionCode.action", method = RequestMethod.POST)
    public RemoteResult<RechargeComboInfo> getRechargeComboInfoByPromotionCode(String promotionCode) {
        if(promotionCode == null){
            throw new ParamException("promotionCode不能为空");
        }
        RechargeComboInfo rechargeComboInfos = rechargeComboService.getRechargeComboInfoByPromotionCode(promotionCode);
        return ResultUtils.createDefResult(rechargeComboInfos);
    }
    
    @ApiOperation(value = "根据推广码获取充值套餐赠送服务信息")
    @ApiImplicitParam(name = "promotionCode", value = "推广码", required = true,paramType="query", dataType = "String",defaultValue="A1234")
    @RequestMapping(value = "findPresentServiceInfoByPromotionCode.action", method = RequestMethod.POST)
    public RemoteResult<PresentServiceInfo> findPresentServiceInfoByPromotionCode(String promotionCode) {
        if(promotionCode == null){
            throw new ParamException("promotionCode不能为空");
        }
        List<PresentServiceInfo> presentServiceInfos = rechargeComboService.findPresentServiceInfoByPromotionCode(promotionCode);
        return ResultUtils.createDefResult(presentServiceInfos);
    }
    
    @ApiOperation(value = "推广码信息")
    @ApiImplicitParam(name = "userId", value = "套餐Id", required = true,paramType="query", dataType = "long",defaultValue="79")
    @RequestMapping(value = "findPromotionCodeInfo.action", method = RequestMethod.POST)
    public RemoteResult<PromotionCodeInfo> findPromotionCodeInfo(Long userId) {
        List<PromotionCodeInfo> rechargeComboInfos = rechargeComboService.findPromotionCodeInfo(userId);
        return ResultUtils.createDefResult(rechargeComboInfos);
    }
    
    @ApiOperation(value = "获取二维码")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "code", value = "二维码", required = true,paramType="query", dataType = "String",defaultValue="A1234"),
    @ApiImplicitParam(name = "width", value = "二维码宽 默认 300", required = false,paramType="query", dataType = "int"),
    @ApiImplicitParam(name = "height", value = "二维码高 默认 300", required = false,paramType="query", dataType = "int"),
    })
    @RequestMapping(value = "getQRCode")
    public void getQRCode(HttpServletResponse response,String code,@RequestParam(defaultValue="300")Integer width,@RequestParam(defaultValue="300")Integer height) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        BufferedImage image = QRCodeUtil.encode(code, width, height);
        ImageIO.write(image, "png",response.getOutputStream());
    }
    
    
    @ApiOperation(value="获取荣云token")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79")
    })
    @RequestMapping(value="getRongToken.action",method=RequestMethod.POST)
    public RemoteResult<RongTokenInfo> getRongToken(Long userId) {
         if(userId == null){
             throw new ParamException("用户Id不能为空");
         }
        RongTokenInfo rongTokenInfo = userService.getRongToken(userId);
        return ResultUtils.createDefResult(rongTokenInfo);
    }
    
    @ApiOperation(value="汽车违章查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "plateNumber", value = "车牌", required = true,paramType="query", dataType = "String"),
        @ApiImplicitParam(name = "engineNumber", value = "发动机号", required = true,paramType="query", dataType = "String"),
        @ApiImplicitParam(name = "frameNumber", value = "车架号", required = true,paramType="query", dataType = "String")
    })
    @RequestMapping(value="getCarIllegallyInfo.action",method=RequestMethod.POST)
    public RemoteResult<CarIllegallyInfo> getCarIllegallyInfo(String plateNumber,String engineNumber,String frameNumber) {
        if(StringUtils.isBlank(plateNumber)){
            throw new ParamException("车牌不能为空");
       }
       if(StringUtils.isBlank(engineNumber)){
           throw new ParamException("发动机号不能为空");
      }
       if(StringUtils.isBlank(frameNumber)){
           throw new ParamException("车架号不能为空");
      }
       CarIllegallyInfo carIllegallyInfo = carService.getCarIllegallyInfo(plateNumber,engineNumber,frameNumber);
        return ResultUtils.createDefResult(carIllegallyInfo);
    }
    
    @ApiOperation(value="取消订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orderId", value = "订单id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })
    @RequestMapping(value="cancelOrder.action",method=RequestMethod.POST)
    public RemoteResult cancelOrder(Long orderId) {
        if(orderId == null){
             throw new ParamException("orderId参数不能为空");
        }
       orderService.cancelOrder(orderId);
       return ResultUtils.createNullResult();
    }
    
    
    @ApiOperation(value="sos")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "lng", value = "纬度", required = true,paramType="query", dataType = "String",defaultValue="116.481247"),
        @ApiImplicitParam(name = "lat", value = "经度", required = true,paramType="query", dataType = "String",defaultValue="39.990704"),
        @ApiImplicitParam(name = "message", value = "求救信息", required = true,paramType="query", dataType = "String")
    })
    @RequestMapping(value="sos.action",method=RequestMethod.POST)
    public RemoteResult sos(Long userId,String lng,String lat,String message) {
        if(userId == null){
             throw new ParamException("userIduserId参数不能为空");
        }
        sosService.sos(userId,lng,lat,message);
       return ResultUtils.createNullResult();
    }
    
    
    @ApiOperation(value="分享推广邀请h5")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "rechargeComboId", value = "充值套餐id", required = true,paramType="query", dataType = "long",defaultValue="1")
    })
    @RequestMapping(value="showPromotionCodeHtml")
    public ModelAndView showCompanionTopicHtml(Long userId,Long rechargeComboId) {
        ModelAndView modelAndView = new ModelAndView("h5/promotionCodeHtml");
        if(userId == null||rechargeComboId == null){
            return modelAndView;
        }
        PromotionCodeInfo promotionCodeInfo = rechargeComboService.getPromotionCodeInfo(userId,rechargeComboId);
        modelAndView.addObject("promotionCodeInfo", promotionCodeInfo);
        return modelAndView;
    }
    
}