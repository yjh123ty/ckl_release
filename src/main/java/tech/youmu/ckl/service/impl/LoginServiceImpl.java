package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import tech.youmu.ckl.app.vo.EmployeeTokenInfo;
import tech.youmu.ckl.app.vo.UserTokenInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.EmployeeMapper;
import tech.youmu.ckl.mapper.UserMapper;
import tech.youmu.ckl.service.ILoginService;
import tech.youmu.ckl.utils.CheckCodeUtil;
import tech.youmu.ckl.utils.ConfigUtil;
import tech.youmu.ckl.utils.DateUtil;
import tech.youmu.ckl.utils.EmployeeTokenUtil;
import tech.youmu.ckl.utils.MD5Util;
import tech.youmu.ckl.utils.SendSmsUtil;
import tech.youmu.ckl.utils.UserCodeUtil;
import tech.youmu.ckl.utils.UserTokenUtil;

/**
 * 
 * <p>Title:LoginServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月19日上午9:35:00</p>
 * <p>Description:用户登陆</p>
 */
@Service
public class LoginServiceImpl  implements ILoginService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public UserTokenInfo doPasswordLogin(String mobile, String password) {
		User user = userMapper.getByPhoneNumber(mobile);
		if(user == null){
			throw new BizExecption("该手机号还未注册");
		}
		if(user.getStatus().equals(StatusConst.BLOCKED)){
			throw new BizExecption("账户已冻结");
		}
		if(!MD5Util.MD5(password).equals(user.getPassword())){
			throw new BizExecption("密码错误");
		}
		return UserTokenUtil.bindUserTokenInfo(user.getId());
	}

	@Override
	public void sendVerifyCode(String mobile) {
		String code = CheckCodeUtil.newCheckCode(mobile);
		JSONObject json = new JSONObject();
		json.put("code", code);
		if(!SendSmsUtil.sendMessage(mobile,json.toString(),ConfigUtil.getSMSTemplateCodeRegist())){
			throw new BizExecption("发送失败");
		}
	}

	@Override
	public UserTokenInfo doVerifyCodeLogin(String mobile, String verifyCode) {
		CheckCodeUtil.verify(mobile, verifyCode);
		User user = userMapper.getByPhoneNumber(mobile);
		if(user == null){
			user = new User(mobile,DateUtil.getDate(),StatusConst.ZERO,StatusConst.FALSE,UserCodeUtil.getUserCode());
			user.setBalance(30d);
			userMapper.save(user);
		}
		if(user.getStatus().equals(StatusConst.BLOCKED)){
			throw new BizExecption("账户已冻结");
		}
		return UserTokenUtil.bindUserTokenInfo(user.getId());
	}

	@Override
	public boolean verifyMobile(String mobile) {
		User user = userMapper.getByPhoneNumber(mobile);
		if(user == null){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void doRegister(String mobile, String password, String verifyCode) {
		CheckCodeUtil.verify(mobile, verifyCode);
		User user = userMapper.getByPhoneNumber(mobile);
		if(user != null){
			throw new BizExecption("该手机已经注册");
		}
		user = new User(mobile,MD5Util.MD5(password),DateUtil.getDate(),StatusConst.ZERO,StatusConst.FALSE,UserCodeUtil.getUserCode());
		user.setBalance(30d);
		userMapper.save(user);
	}

	@Override
	public boolean doVerifyCode(String mobile, String verifyCode) {
		User user = userMapper.getByPhoneNumber(mobile);
		if(user ==null){
			throw new BizExecption("该手机没有注册");
		}
		return CheckCodeUtil.verify(mobile, verifyCode);
	}

	@Override
	public void updatePassword(String mobile, String password,String verifyCode) {
		User user = userMapper.getByPhoneNumber(mobile);
		if(user == null){
			throw new BizExecption("该手机没有注册");
		}
		if(!CheckCodeUtil.verify(mobile, verifyCode)){
		    throw new BizExecption("验证码错误");
		}
		user.setPassword(MD5Util.MD5(password));
		userMapper.update(user);
	}
	
	 @Override
	    public EmployeeTokenInfo doLogin(String mobile, String password) {
	        Employee employee = employeeMapper.getByPhoneNumber(mobile);
	        if(employee == null){
	            throw new BizExecption("电话号码错误");
	        }
	        if(employee.getStatus().equals(StatusConst.BLOCKED)){
	            throw new BizExecption("账户已冻结");
	        }
	        if(!MD5Util.MD5(password).equals(employee.getPassword())){
	            throw new BizExecption("密码错误");
	        }
	        return EmployeeTokenUtil.bindEmployeeTokenInfo(employee.getId());
	    }

}

