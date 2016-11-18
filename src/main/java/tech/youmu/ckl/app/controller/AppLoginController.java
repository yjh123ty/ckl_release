package tech.youmu.ckl.app.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import tech.youmu.ckl.app.vo.RemoteResult;
import tech.youmu.ckl.app.vo.UserTokenInfo;
import tech.youmu.ckl.exception.ParamException;
import tech.youmu.ckl.service.ILoginService;
import tech.youmu.ckl.utils.ResultUtils;
import tech.youmu.ckl.utils.VerifyUtil;

@RestController
@RequestMapping(value = "/login/remote")
@Api(description = "登陆页面")
public class AppLoginController {

	@Autowired
	private ILoginService loginService;

	@ApiOperation(value = "用户密码登陆")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String", paramType = "query", defaultValue = "13000000000"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query", defaultValue = "123456") })
	@RequestMapping(value = "doPasswordLogin", method = RequestMethod.POST)
	public RemoteResult<UserTokenInfo> doPasswordLogin(String mobile, String password) {
		if (StringUtils.isBlank(mobile)) {
			throw new ParamException("手机不能为空");
		}
		if(!VerifyUtil.isMobile(mobile)){
			throw new ParamException("手机号码格式错误");
		}
		if (StringUtils.isBlank(password)) {
			throw new ParamException("密码不能为空");
		}
		UserTokenInfo userTokenInfo = loginService.doPasswordLogin(mobile, password);
		return ResultUtils.createDefResult(userTokenInfo);
	}

	@ApiOperation(value = "获取验证码")
	@ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String", paramType = "query", defaultValue = "13000000000")
	@RequestMapping(value = "sendVerifyCode", method = RequestMethod.POST)
	public RemoteResult sendVerifyCode(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			throw new ParamException("手机不能为空");
		}
		if(!VerifyUtil.isMobile(mobile)){
			throw new ParamException("手机号码格式错误");
		}
		loginService.sendVerifyCode(mobile);
		return ResultUtils.createNullResult();
	}

	@ApiOperation(value = "用户验证码登陆")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String", paramType = "query", defaultValue = "13000000000"),
			@ApiImplicitParam(name = "verifyCode", value = "验证码", required = true, dataType = "String", paramType = "query",defaultValue = "123456") })
	@RequestMapping(value = "doVerifyCodeLogin", method = RequestMethod.POST)
	public RemoteResult<UserTokenInfo> doVerifyCodeLogin(String mobile, String verifyCode) {
		if (StringUtils.isBlank(mobile)) {
			throw new ParamException("手机不能为空");
		}
		if(!VerifyUtil.isMobile(mobile)){
			throw new ParamException("手机号码格式错误");
		}
		if (StringUtils.isBlank(verifyCode)) {
			throw new ParamException("验证码不能为空");
		}
		UserTokenInfo userTokenInfo = loginService.doVerifyCodeLogin(mobile, verifyCode);
		return ResultUtils.createDefResult(userTokenInfo);
	}

	@ApiOperation(value = "验证手机是否存在")
	@ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String", paramType = "query", defaultValue = "13000000000")
	@RequestMapping(value = "verifyMobile", method = RequestMethod.POST)
	public RemoteResult<Boolean> verifyMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			throw new ParamException("手机不能为空");
		}
		if(!VerifyUtil.isMobile(mobile)){
			throw new ParamException("手机号码格式错误");
		}
		return ResultUtils.createDefResult(loginService.verifyMobile(mobile));
	}
	
	@ApiOperation(value = "用户注册")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String", paramType = "query", defaultValue = "13000000000"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query", defaultValue = "123456"),
			@ApiImplicitParam(name = "verifyCode", value = "验证码", required = true, dataType = "String", paramType = "query",defaultValue = "123456") })
	@RequestMapping(value = "doRegister", method = RequestMethod.POST)
	public RemoteResult doRegister(String mobile,String password, String verifyCode) {
		if (StringUtils.isBlank(mobile)) {
			throw new ParamException("手机不能为空");
		}
		if(!VerifyUtil.isMobile(mobile)){
			throw new ParamException("手机号码格式错误");
		}
		if (StringUtils.isBlank(password)) {
			throw new ParamException("密码不能为空");
		}
		if (StringUtils.isBlank(verifyCode)) {
			throw new ParamException("验证码不能为空");
		}
		loginService.doRegister(mobile,password, verifyCode);
		return ResultUtils.createNullResult();
	}
	
	
	@ApiOperation(value = "验证验证码是否正确")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String", paramType = "query", defaultValue = "13000000000"),
			@ApiImplicitParam(name = "verifyCode", value = "验证码", required = true, dataType = "String", paramType = "query",defaultValue = "123456") })
	@RequestMapping(value = "doVerifyCode", method = RequestMethod.POST)
	public RemoteResult doVerifyCode(String mobile, String verifyCode) {
		if (StringUtils.isBlank(mobile)) {
			throw new ParamException("手机不能为空");
		}
		if(!VerifyUtil.isMobile(mobile)){
			throw new ParamException("手机号码格式错误");
		}
		if (StringUtils.isBlank(verifyCode)) {
			throw new ParamException("验证码不能为空");
		}
		return ResultUtils.createDefResult(loginService.doVerifyCode(mobile, verifyCode));
	}
	
	@ApiOperation(value = "忘记密码修改密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String", paramType = "query", defaultValue = "13000000000"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query", defaultValue = "123456"),
			@ApiImplicitParam(name = "confirmPassword", value = "确认密码", required = true, dataType = "String", paramType = "query", defaultValue = "123456"),
	        @ApiImplicitParam(name = "verifyCode", value = "验证码", required = true, dataType = "String", paramType = "query", defaultValue = "123456")
	})
	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	public RemoteResult updatePassword(String mobile,String password,String confirmPassword,String verifyCode) {
		if (StringUtils.isBlank(mobile)) {
			throw new ParamException("手机不能为空");
		}
		if(!VerifyUtil.isMobile(mobile)){
			throw new ParamException("手机号码格式错误");
		}
		if (StringUtils.isBlank(password)) {
			throw new ParamException("密码不能为空");
		}
		if(!password.equals(confirmPassword)){
			throw new ParamException("两次密码不一致");
		}
		if (StringUtils.isBlank(verifyCode)) {
            throw new ParamException("验证码不能为空");
        }
		loginService.updatePassword(mobile,password,verifyCode);
		return ResultUtils.createNullResult();
	}

}