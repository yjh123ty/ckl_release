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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.app.vo.UserFriendApplyInfo;
import tech.youmu.ckl.app.vo.UserFriendInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.domain.UserFriend;
import tech.youmu.ckl.domain.UserFriendApply;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.FriendMapper;
import tech.youmu.ckl.mapper.UserMapper;
import tech.youmu.ckl.service.IFriendService;
import tech.youmu.ckl.utils.ImageURLUtil;

/**
 * 
 * <p>Title:FriendServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月24日下午2:08:21</p>
 * <p>Description:TODO</p>
 */
@Service
public class FriendServiceImpl  implements IFriendService {
	
	
	@Autowired
	private FriendMapper friendMapper;
	
	@Autowired
    private UserMapper userMapper;

	@Override
	public List<UserFriendInfo> findUserFriendInfo(Long userId) {
		List<UserFriendInfo> userFriendInfos =  friendMapper.findUserFriendInfo(userId);
		for(UserFriendInfo userFriendInfo: userFriendInfos){
			userFriendInfo.setHeadIcon(ImageURLUtil.fillPath(userFriendInfo.getHeadIcon()));
		}
		return userFriendInfos;
			
	}

    @Override
    public void setUserFriendNote(Long id, String note) {
        UserFriend userFriend = (UserFriend) friendMapper.getById(id);
        if(userFriend ==null){
            throw new BizExecption("不是好友不能设置备注");
        }
        userFriend.setNote(note);
        friendMapper.updateUserFriend(userFriend);
    }

    @Override
    public UserFriendInfo getUserFriendInfo(Long friendId, Long userId) {
        User user = (User) userMapper.getById(friendId);
        if(user == null){
            throw new BizExecption("好友账号已删除");
        }
        UserFriendInfo userFriendInfo = friendMapper.getUserFriendInfo(friendId,userId);
        if(userFriendInfo != null){
            userFriendInfo.setHeadIcon(ImageURLUtil.fillPath(userFriendInfo.getHeadIcon()));
        }
        return userFriendInfo;
    }

    @Override
    public void addUserFriendApply(Long friendId, Long userId, String remark) {
        User user = (User) userMapper.getById(friendId);
        if(user == null){
            throw new BizExecption("好友账号已删除");
        }
        boolean isFriend = friendMapper.isFriend(friendId, userId);
        if(isFriend){
            throw new BizExecption("已经是好友");
        }
        
        UserFriendApply userFriendApply = friendMapper.getUserFriendApply(friendId, userId);
        if(userFriendApply !=null){
            throw new BizExecption("已经申请,等待对方回复");
        }
        friendMapper.saveUserFriendApply(new UserFriendApply(friendId,userId,remark,StatusConst.ONE));
    }

    @Override
    public List<UserFriendApplyInfo> findUserFriendApplyInfo(Long userId) {
        List<UserFriendApplyInfo> userFriendApplyInfos = friendMapper.findUserFriendApplyInfo(userId);
        for(UserFriendApplyInfo userFriendApplyInfo :userFriendApplyInfos){
            userFriendApplyInfo.setHeadIcon(ImageURLUtil.fillPath(userFriendApplyInfo.getHeadIcon()));
        }
        return userFriendApplyInfos;
    }

    @Override
    public void agreeAddUserFriend(Long userFriendApplyId, Long userId) {
       
        UserFriendApply userFriendApply = friendMapper.getUserFriendApplyById(userFriendApplyId);
        if(userFriendApply ==null){
            throw new BizExecption("没有申请");
        }
        if(!userFriendApply.getStatus().equals(StatusConst.ONE)){
            throw new BizExecption("不是待同意状态，不能同意");
        }
        if(!userId.equals(userFriendApply.getFriendId())){
            throw new BizExecption("不是你的申请，不能同意");
        }
        userFriendApply.setStatus(StatusConst.TWO);
        friendMapper.updateUserFriendApply(userFriendApply);
        boolean isFriend = friendMapper.isFriend(userFriendApply.getFriendId(),userFriendApply.getUserId());
        if(!isFriend)
        friendMapper.saveUserFriend(userFriendApply.getFriendId(),userFriendApply.getUserId());

    }

    @Override
    public UserFriendInfo getUserFriendInfoByMobile(Long userId,String mobile) {
        UserFriendInfo userFriendInfo = friendMapper.getUserFriendInfoByMobile(userId,mobile);
        if(userFriendInfo != null){
            userFriendInfo.setHeadIcon(ImageURLUtil.fillPath(userFriendInfo.getHeadIcon()));
        }
        return userFriendInfo;
    }

    @Override
    public void deleteUserFriend(Long userId, Long friendId) {
        
        friendMapper.deleteUserFriend(userId,friendId);
    }




}
