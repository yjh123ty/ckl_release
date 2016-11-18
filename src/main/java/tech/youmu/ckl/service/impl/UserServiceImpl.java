/**
 * @Title: UserServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:58:35
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.OrderCount;
import tech.youmu.ckl.app.vo.RidersInfo;
import tech.youmu.ckl.app.vo.RongTokenInfo;
import tech.youmu.ckl.app.vo.UserInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.domain.UserCenter;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.BadgeMapper;
import tech.youmu.ckl.mapper.FriendMapper;
import tech.youmu.ckl.mapper.IntegralMapper;
import tech.youmu.ckl.mapper.OrderMapper;
import tech.youmu.ckl.mapper.PhoneMessageMapper;
import tech.youmu.ckl.mapper.StationMapper;
import tech.youmu.ckl.mapper.UserCenterMapper;
import tech.youmu.ckl.mapper.UserMapper;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.UserQuery;
import tech.youmu.ckl.service.IUserService;
import tech.youmu.ckl.utils.GaodeUtil;
import tech.youmu.ckl.utils.ImageURLUtil;
import tech.youmu.ckl.utils.MD5Util;
import tech.youmu.ckl.utils.RongUtil;
import tech.youmu.ckl.utils.UploadUtils;
import tech.youmu.ckl.utils.UserTokenUtil;

/**
 * 
 * <p>Title:UserServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月26日上午9:32:43</p>
 * <p>Description:TODO</p>
 */
@Service
public class UserServiceImpl  implements IUserService {
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private BadgeMapper badgeMapper;
	
	@Autowired
	private StationMapper stationMapper;
	
	@Autowired
	private FriendMapper friendMapper;
	
	@Autowired
	private IntegralMapper integralMapper;
	
	@Autowired
    private PhoneMessageMapper phoneMessageMapper;
	
	@Autowired
	private UserCenterMapper userCenterMapper;



	/**
	 * @see tech.youmu.ckl.service.IUserService#pageList(tech.youmu.ckl.query.UserQuery)
	 */
	@Override
	public PageList<User> pageList(UserQuery userQuery) {
		List<User> list = userMapper.queryList(userQuery);
		Long count = userMapper.getCount(userQuery);
		PageList<User> pageList = new PageList<User>();
		pageList.setRows(list);
		pageList.setTotal(count);
		return pageList;
	}

	/**
	 * 禁用用户
	 */
	@Override
	public void disableById(Long id) {
	    UserTokenUtil.removeUserTokenInfo(id);
		userMapper.disableById(id);
	}

	/**
	 * 开通用户
	 * @see tech.youmu.ckl.service.IUserService#invokeById(java.lang.Long)
	 */
	@Override
	public void invokeById(Long id) {
		userMapper.invokeById(id);
	}

	@Override
	public UserInfo getUserInfo(Long userId) {
		UserInfo userInfo = userMapper.getUserInfoByUserId(userId);
		userInfo.setHeadIcon(ImageURLUtil.fillPath(userInfo.getHeadIcon()));
		List<OrderCount> orderCounts = orderMapper.findOrderCountByUserId(userId);
		for(OrderCount orderCount:orderCounts){
			if(StatusConst.FOUR == orderCount.getStatus()){
				userInfo.setAwaitPayCount(orderCount.getCount());
			}
			if(StatusConst.FIVE == orderCount.getStatus()){
				userInfo.setAwaitServiceCount(orderCount.getCount());
			}
			if(StatusConst.SIX == orderCount.getStatus()){
				userInfo.setAwaitFinishCount(orderCount.getCount());
			}
			if(StatusConst.SEVEN == orderCount.getStatus()){
				userInfo.setAwaitEvaluateCount(orderCount.getCount());
			}
		}
		int friendCount = friendMapper.getFriendCountByUserId(userId);
		userInfo.setFriendCount(friendCount);
		int badgeCount = badgeMapper.getBadgeCountByUserId(userId);
		userInfo.setBadgeCount(badgeCount);
		int collectStationCount = stationMapper.getCollectStationCountByUserId(userId);
		userInfo.setCollectStationCount(collectStationCount);
		int nextLevelIntegral = integralMapper.getNextLevelIntegral(userInfo.getIntegral());
		userInfo.setNextLevelIntegral(nextLevelIntegral);
		return userInfo;
	}

	@Override
	public User getById(Long id) {
		return (User) userMapper.getById(id);
	}

	@Override
	public void verifyLoginPassword(Long userId, String loginPassword) {
		User user = (User) userMapper.getById(userId);
		if(user == null){
			throw new BizExecption("没有该用户");
		}
		if(!MD5Util.MD5(loginPassword).equals(user.getPassword())){
			throw new BizExecption("密码错误");
		}
	}

	@Override
	public void updateLoginPassword(Long userId, String newLoginPassword, String oldLoginPassword) {
		User user = (User) userMapper.getById(userId);
		if(user == null){
			throw new BizExecption("没有该用户");
		}
		if(!MD5Util.MD5(oldLoginPassword).equals(user.getPassword())){
			throw new BizExecption("旧密码错误");
		}
		user.setPassword(MD5Util.MD5(newLoginPassword));
		userMapper.update(user);
	}

	@Override
	public void verifyPayPassword(Long userId, String payPassword) {
		User user = (User) userMapper.getById(userId);
		if(user == null){
			throw new BizExecption("没有该用户");
		}
		if(!MD5Util.MD5(payPassword).equals(user.getPayPassword())){
			throw new BizExecption("支付密码错误");
		}
		
	}

	@Override
	public void updatePayPassword(Long userId, String newPayPassword, String oldPayPassword) {
		User user = (User) userMapper.getById(userId);
		if(user == null){
			throw new BizExecption("没有该用户");
		}
		if(oldPayPassword!=null&&!MD5Util.MD5(oldPayPassword).equals(user.getPayPassword())){
			throw new BizExecption("旧密码错误");
		}
		user.setPayPassword(MD5Util.MD5(newPayPassword));
		userMapper.update(user);
		
	}

	@Override
	public void logout(String token) {
	    UserTokenUtil.removeUserTokenInfo(token);
	}

    @Override
    public String uploadHeadIcon(Long userId, MultipartFile headIcon) {
        String headIconPath = UploadUtils.uploadFile(headIcon, UploadUtils.HEAD_USER_ICON_PATH);
        User user = (User) userMapper.getById(userId);
        if(null == user){
            throw new BizExecption("没有该用户");
        }
        user.setHeadIcon(headIconPath);
        userMapper.update(user);
        return ImageURLUtil.fillPath(headIconPath);
    }


    @Override
    public void updateUserInfo(User user) {
        User dbUser = (User) userMapper.getById(user.getId());
        if(null == dbUser){
            throw new BizExecption("没有该用户");
        }
        userMapper.update(user);
           
    }

    @Override
    public void uploadCoord(Long userId, String lng, String lat) {
        userMapper.update(new User(userId,lng,lat));
    }

    @Override
    public List<RidersInfo> findNearbyRiders(Long userId,String lng, String lat) {
        List<RidersInfo> ridersInfos = new ArrayList<>();
        List<User> users = userMapper.findUserByRiders(userId);
        StringBuffer origins = new StringBuffer();
        StringBuffer destination = new StringBuffer();
        destination.append(lng).append(",").append(lat);
        for(User user :users){
            if(user.getLongtitude() == null||user.getLongtitude() ==null){
                origins.append(0).append(",").append(0).append("|");
            }else{
                origins.append(user.getLongtitude()).append(",").append(user.getLatitude()).append("|");
            }
        }
        List<Double> distances = GaodeUtil.getDistances(origins.toString(), destination.toString(), 1);
        for(int i=0;i<users.size();i++){
        	if(distances.size() == 0){
        		User user = users.get(i);
        		RidersInfo ridersInfo = new RidersInfo();
                ridersInfo.setUserId(user.getId());
                ridersInfo.setUserName(user.getNickName());
                ridersInfo.setHeadIcon(ImageURLUtil.fillPath(user.getHeadIcon()));
                ridersInfo.setDistance(0);
                ridersInfo.setLng(user.getLongtitude());
                ridersInfo.setLat(user.getLatitude());
                ridersInfos.add(ridersInfo);
        	}else{
	            double distance = distances.get(i);
	            if(distance>0&&distance<10000){
	                User user = users.get(i);
	                RidersInfo ridersInfo = new RidersInfo();
	                ridersInfo.setUserId(user.getId());
	                ridersInfo.setUserName(user.getNickName());
	                ridersInfo.setHeadIcon(ImageURLUtil.fillPath(user.getHeadIcon()));
	                ridersInfo.setDistance(distances.get(i));
	                ridersInfo.setLng(user.getLongtitude());
	                ridersInfo.setLat(user.getLatitude());
	                ridersInfos.add(ridersInfo);
	            }
	            Collections.sort(ridersInfos);
        	}
        }
        
        return ridersInfos;
        
    }

    @Override
    public void setStrangerTalkback(Long userId, Boolean isAllow) {
        User user = new User(userId);
        user.setIsAllow(isAllow);
        userMapper.update(user);
    }

    @Override
    public RongTokenInfo getRongToken(Long userId) {
        RongTokenInfo rongTokenInfo = new RongTokenInfo();
        User user = userMapper.getById(userId);
        String token =  RongUtil.getToken(userId, user.getNickName(), user.getHeadIcon());
        rongTokenInfo.setToken(token);
        return rongTokenInfo;
    }

    @Override
    public String createGroup(String[] userIds) {
        List<String> groupIds = userMapper.findGroupId(userIds);
        for(String groupId:groupIds){
            RongUtil.quitGroup(userIds,groupId);
        }
        String groupId =  RongUtil.createGroup(userIds);
        userMapper.batchUpdateGroupId(userIds,groupId);
        return groupId;
    }

    @Override
    public void quitGroup(String userId,String groupId) {
        RongUtil.quitGroup(userId,groupId);
        
    }

    

	/**
	 * @see tech.youmu.ckl.service.IUserService#getUserCenterByUserId(java.lang.Long)
	 */
	@Override
	public UserCenter getUserCenterByUserId(Long id) {
		return userCenterMapper.getUserCenterByUserId(id);
	}

    


}
