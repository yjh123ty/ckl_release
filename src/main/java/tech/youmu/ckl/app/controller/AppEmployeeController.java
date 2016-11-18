package tech.youmu.ckl.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import tech.youmu.ckl.app.vo.CarPartInfo;
import tech.youmu.ckl.app.vo.EmployeeInfo;
import tech.youmu.ckl.app.vo.EmployeeTokenInfo;
import tech.youmu.ckl.app.vo.LastMonthPerformanceInfo;
import tech.youmu.ckl.app.vo.OrderInfo;
import tech.youmu.ckl.app.vo.ReceiveOrderInfo;
import tech.youmu.ckl.app.vo.RemoteResult;
import tech.youmu.ckl.app.vo.TodayAttendanceInfo;
import tech.youmu.ckl.domain.CarPartStockIncome;
import tech.youmu.ckl.domain.CarPartStockOutcome;
import tech.youmu.ckl.domain.Order;
import tech.youmu.ckl.dto.CarPartComeDto;
import tech.youmu.ckl.dto.CarPartDto;
import tech.youmu.ckl.exception.ParamException;
import tech.youmu.ckl.service.ICarPartService;
import tech.youmu.ckl.service.ICarPartStockIncomeService;
import tech.youmu.ckl.service.ICarPartStockOutcomeService;
import tech.youmu.ckl.service.IEmployeeAttendanceService;
import tech.youmu.ckl.service.IEmployeeService;
import tech.youmu.ckl.service.ILoginService;
import tech.youmu.ckl.service.IOrderService;
import tech.youmu.ckl.service.IPerformanceService;
import tech.youmu.ckl.utils.CarPartCodeUtil;
import tech.youmu.ckl.utils.ResultUtils;
import tech.youmu.ckl.utils.VerifyUtil;

@RestController
@RequestMapping(value = "/employee/remote")
@Api(description = "员工端")
public class AppEmployeeController {

    @Autowired
    private IEmployeeService employeeService;
    
    @Autowired
    private ILoginService loginService;
    
    @Autowired
    private IOrderService orderService;
    
    @Autowired
    private IPerformanceService performanceService;
    
    @Autowired
    private IEmployeeAttendanceService employeeAttendanceService;
    
    @Autowired
    private ICarPartService carPartService;
    
    @Autowired
    private ICarPartStockIncomeService carPartStockIncomeService;
    
    @Autowired
    private ICarPartStockOutcomeService carPartStockOutcomeService;


	@ApiOperation(value = "用户密码登陆")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String", paramType = "query", defaultValue = "13000000000"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query", defaultValue = "123456") })
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public RemoteResult<EmployeeTokenInfo> doLogin(String mobile, String password) {
		if (StringUtils.isBlank(mobile)) {
			throw new ParamException("手机不能为空");
		}
		if(!VerifyUtil.isMobile(mobile)){
			throw new ParamException("手机号码格式错误");
		}
		if (StringUtils.isBlank(password)) {
			throw new ParamException("密码不能为空");
		}
		EmployeeTokenInfo employeeTokenInfo = loginService.doLogin(mobile, password);
		return ResultUtils.createDefResult(employeeTokenInfo);
	}
	
	
	@ApiOperation(value="退出登陆")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "token", required = true,paramType="header", dataType = "String",defaultValue="79")
    })
    @RequestMapping(value="logout.action",method=RequestMethod.POST)
    public RemoteResult logout(@RequestHeader("token") String token) {
        employeeService.logout(token);
        return ResultUtils.createNullResult();
    }
	
	
	@ApiOperation(value = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId", value = "员工id", required = true, dataType = "String", paramType = "query", defaultValue = "4"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query", defaultValue = "123456"),
            @ApiImplicitParam(name = "confirmPassword", value = "确认密码", required = true, dataType = "String", paramType = "query", defaultValue = "123456"),
            @ApiImplicitParam(name = "oldPassword", value = "原密码", required = true, dataType = "String", paramType = "query", defaultValue = "123456") 
    })
    @RequestMapping(value = "updatePasswordByOldPassword.action", method = RequestMethod.POST)
    public RemoteResult updatePasswordByOldPassword(Long employeeId,String password,String confirmPassword,String oldPassword) {
        if (employeeId ==null) {
            throw new ParamException("用户不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new ParamException("密码不能为空");
        }
        if(!password.equals(confirmPassword)){
            throw new ParamException("两次密码不一致");
        }
        if (StringUtils.isBlank(oldPassword)) {
            throw new ParamException("原密码不能为空");
        }
        employeeService.updatePassword(employeeId,password,oldPassword);
        return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value = "忘记密码修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, dataType = "String", paramType = "query", defaultValue = "13000000000"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query", defaultValue = "123456"),
            @ApiImplicitParam(name = "confirmPassword", value = "确认密码", required = true, dataType = "String", paramType = "query", defaultValue = "123456"),
            @ApiImplicitParam(name = "verifyCode", value = "验证码", required = true, dataType = "String", paramType = "query", defaultValue = "123456") 
    })
    @RequestMapping(value = "updatePasswordByVerifyCode.action", method = RequestMethod.POST)
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
        employeeService.updatePassword(mobile,password,verifyCode);
        return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value="验证密码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "employeeId", value = "员工id", required = true,paramType="query", dataType = "long",defaultValue="4"),
        @ApiImplicitParam(name = "password", value = "原密码", required = true,paramType="query", dataType = "String",defaultValue="123456")
    })
    @RequestMapping(value="verifyPassword.action",method=RequestMethod.POST)
    public RemoteResult verifyPassword(Long employeeId,String password) {
        if(employeeId == null){
             throw new ParamException("employeeId不能为空");
        }
        if(StringUtils.isBlank(password)){
             throw new ParamException("密码不能为空");
        }
        employeeService.verifyPassword(employeeId,password);
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
        return ResultUtils.createDefResult(employeeService.doVerifyCode(mobile, verifyCode));
    }
	
	
	@ApiOperation(value = "获取员工信息")
    @ApiImplicitParam(name = "employeeId", value = "员工id", required = true, dataType = "long", paramType = "query", defaultValue = "4")
    @RequestMapping(value = "getEmployeeInfo.action", method = RequestMethod.POST)
    public RemoteResult<EmployeeInfo> getEmployeeInfo(Long employeeId) {
        if (employeeId == null) {
            throw new ParamException("employeeId不能为空");
        }
        EmployeeInfo employeeInfo = employeeService.getEmployeeInfo(employeeId);
        return ResultUtils.createDefResult(employeeInfo);
    }
	
	
	@ApiOperation(value="订单列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "第几页", required = true,paramType="query", dataType = "int",defaultValue="1"),
        @ApiImplicitParam(name = "rows", value = "每页个数", required = true,paramType="query", dataType = "int",defaultValue="10"),
        @ApiImplicitParam(name = "employeeId", value = "employeeId", required = true,paramType="query", dataType = "Long",defaultValue="4"),
        @ApiImplicitParam(name = "status", value = "订单状态", required = false,paramType="query", dataType = "int")
    })
    @RequestMapping(value="findOrderInfo.action",method=RequestMethod.POST)
    public RemoteResult<OrderInfo> findOrderInfo(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer rows,Long employeeId,Integer status) {
        if(employeeId == null){
            throw new ParamException("userId不能为空");
        }
        List<OrderInfo> orderInfos = orderService.findOrderInfo(page,rows,null,employeeId,status);
        return ResultUtils.createDefResult(orderInfos);
    }
	
	
	@ApiOperation(value="上个月绩效")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "employeeId", value = "employeeId", required = true,paramType="query", dataType = "Long",defaultValue="4"),
    })
    @RequestMapping(value="getLastMonthPerformanceInfo.action",method=RequestMethod.POST)
    public RemoteResult<LastMonthPerformanceInfo> getLastMonthPerformanceInfo(Long employeeId) {
        if(employeeId == null){
            throw new ParamException("userId不能为空");
        }
        List<LastMonthPerformanceInfo> lastMonthPerformanceInfo = performanceService.getLastMonthPerformanceInfo(employeeId);
        return ResultUtils.createDefResult(lastMonthPerformanceInfo);
    }
	
	@ApiOperation(value="上传头像")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "employeeId", value = "employeeId", required = true,paramType="query", dataType = "Long",defaultValue="4"),
        @ApiImplicitParam(name = "headIcon", value = "图片", required = false,paramType="form", dataType = "file")
    })
    @RequestMapping(value="uploadHeadIcon.action",method=RequestMethod.POST)
    public RemoteResult<String> uploadHeadIcon(Long employeeId,MultipartFile headIcon) {
        if(employeeId == null){
            throw new ParamException("employeeId不能为空");
        }
        if(headIcon == null){
            throw new ParamException("headIcon不能为空");
        }
        String headIconPath = employeeService.uploadHeadIcon(employeeId,headIcon);
        return ResultUtils.createDefResult(headIconPath);
    }
	
	@ApiOperation(value="今天考勤记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "employeeId", value = "employeeId", required = true,paramType="query", dataType = "Long",defaultValue="4"),
    })
    @RequestMapping(value="findTodayAttendanceInfo.action",method=RequestMethod.POST)
    public RemoteResult<TodayAttendanceInfo> findTodayAttendanceInfo(Long employeeId) {
        if(employeeId == null){
            throw new ParamException("userId不能为空");
        }
        List<TodayAttendanceInfo> todayAttendanceInfos = employeeAttendanceService.findTodayAttendanceInfo(employeeId);
        return ResultUtils.createDefResult(todayAttendanceInfos);
    }
	
	@ApiOperation(value="保存今天考勤记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "employeeId", value = "employeeId", required = true,paramType="query", dataType = "Long",defaultValue="4"),
        @ApiImplicitParam(name = "lng", value = "纬度", required = true,paramType="query", dataType = "double",defaultValue="116.481247"),
        @ApiImplicitParam(name = "lat", value = "经度", required = true,paramType="query", dataType = "double",defaultValue="39.990704")
    })
    @RequestMapping(value="saveTodayAttendance.action",method=RequestMethod.POST)
    public RemoteResult saveTodayAttendance(Long employeeId,String lng,String lat) {
        if(employeeId == null){
            throw new ParamException("userId不能为空");
        }
        employeeAttendanceService.saveTodayAttendance(employeeId,lng,lat);
        return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value="扫码商品码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "条形码", required = true,paramType="query", dataType = "String",defaultValue="pj00000021"),
    })
    @RequestMapping(value="scanQRodeByCarPart.action",method=RequestMethod.POST)
    public RemoteResult<CarPartInfo> scanQRodeByCarPart(String code) {
        if(StringUtils.isBlank(code)){
            throw new ParamException("条形码不能为空");
        }
        CarPartInfo carPartInfo=carPartService.scanQRCode(code);
        return ResultUtils.createDefResult(carPartInfo);
    }
	
	@ApiOperation(value="批量扫码商品码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "codes", value = "条形码", required = true,paramType="query", dataType = "String",defaultValue="pj00000021"),
        @ApiImplicitParam(name = "codes", value = "条形码", required = true,paramType="query", dataType = "String",defaultValue="pj00000021"),
    })
    @RequestMapping(value="batchScanQRodeByCarPart.action",method=RequestMethod.POST)
    public RemoteResult<CarPartInfo> batchScanQRodeByCarPart(@RequestParam("codes")String[] codes) {
        if(codes ==null||codes.length==0){
            throw new ParamException("条形码不能为空");
        }
        List<CarPartInfo> carPartInfo=carPartService.batchScanQRodeByCarPart(codes);
        return ResultUtils.createDefResult(carPartInfo);
    }
	
	
	@ApiOperation(value="扫码接订单码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "订单码", required = true,paramType="query", dataType = "String",defaultValue="pj00000021"),
        @ApiImplicitParam(name = "employeeId", value = "employeeId", required = true,paramType="query", dataType = "Long",defaultValue="4"),
    })
    @RequestMapping(value="scanQRodeByOrder.action",method=RequestMethod.POST)
    public RemoteResult<Long> scanQRodeByOrder(String code,Long employeeId) {
        if(StringUtils.isBlank(code)){
            throw new ParamException("二维码不能为空");
        }
        long orderId=orderService.scanQRCode(code,employeeId);
        return ResultUtils.createDefResult(orderId);
    }
	
	@ApiOperation(value = "修改汽车订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "订单id", required = true,paramType="query", dataType = "long",defaultValue="79"),
        @ApiImplicitParam(name = "serviceRemark", value = "服务备注", required = false,paramType="query", dataType = "String",defaultValue="not"),
        @ApiImplicitParam(name = "totalAmount", value = "订单总金额", required = false,paramType="query", dataType = "double",defaultValue="200.00"),
        @ApiImplicitParam(name = "orderServiceDetails[0].id", value = "服务id,orderServiceId", required = false,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "orderServiceDetails[0].productId", value = "商品零部件id,carPartId", required = false,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "orderServiceDetails[0].name", value = "名称", required = false,paramType="query", dataType = "String",defaultValue="换后市静"),
        @ApiImplicitParam(name = "orderServiceDetails[0].price", value = "价格", required = false,paramType="query", dataType = "double",defaultValue="300.00"),
        @ApiImplicitParam(name = "orderServiceDetails[0].type", value = "类型  1-正常，2新增，3-删除", required = false,paramType="query", dataType = "int",defaultValue="2"),
        @ApiImplicitParam(name = "orderServiceDetails[0].number", value = "数量", required = false,paramType="query", dataType = "int",defaultValue="1"),
        @ApiImplicitParam(name = "orderServiceDetails[0].isCarPart", value = "是否是零部件", required = false,paramType="query", dataType = "boolean",defaultValue="false"),
    })
    @RequestMapping(value = "updateCarOrder.action", method = RequestMethod.POST)
    public RemoteResult updateCarOrder(Order order) {
        if(order == null){
            throw new ParamException("order不能为空");
        }
        if(order.getId() == null){
            throw new ParamException("id不能为空");
        }
        if(order.getTotalAmount()== null){
            throw new ParamException("totalAmount不能为空");
        }
        orderService.updateCarOrder(order);
        return ResultUtils.createNullResult();
    }
	
	
	
	@ApiOperation(value="入库")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "type", value = "入库类型1-正常入库，2-退货入库", required = true,paramType="query", dataType = "int",defaultValue="1"),
        @ApiImplicitParam(name = "employeeId", value = "employeeId", required = true,paramType="query", dataType = "Long",defaultValue="4"),
        @ApiImplicitParam(name = "carPartDtos[0].carPartId", value = "商品id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "carPartDtos[0].price", value = "入库金额", required = true,paramType="query", dataType = "Long",defaultValue="4"),
        @ApiImplicitParam(name = "carPartDtos[0].code", value = "条形码", required = true,paramType="query", dataType = "String",defaultValue="sj0123"),
        @ApiImplicitParam(name = "carPartDtos[1].carPartId", value = "商品id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "carPartDtos[1].price", value = "入库金额", required = true,paramType="query", dataType = "Long",defaultValue="4"),
        @ApiImplicitParam(name = "carPartDtos[1].code", value = "条形码", required = true,paramType="query", dataType = "String",defaultValue="sj012113"),

    })
    @RequestMapping(value="income.action",method=RequestMethod.POST)
    public RemoteResult income(CarPartComeDto carPartComeDto) {
        if(carPartComeDto == null){
            throw new ParamException("商品不能为空");
        }
        if(carPartComeDto.getEmployeeId() == null){
            throw new ParamException("入库人不能为空");
        }
        if(carPartComeDto.getType() == null){
            throw new ParamException("类型不能为空");
        }
        if(carPartComeDto.getCarPartDtos() == null||carPartComeDto.getCarPartDtos().size() == 0){
            throw new ParamException("商品不能为空");
        }
        List<CarPartStockIncome> carPartStockIncomes = new ArrayList();
        for(CarPartDto carPartDto:carPartComeDto.getCarPartDtos()){
            if(carPartDto.getCode() == null){
                throw new ParamException("条形码不能为空");
            }
            CarPartStockIncome carPartStockIncome = new CarPartStockIncome(carPartDto.getCarPartId(),carPartDto.getCode(),CarPartCodeUtil.getInPrice(carPartDto.getCode()));
            carPartStockIncomes.add(carPartStockIncome);
        }
        carPartStockIncomeService.income(carPartComeDto.getEmployeeId(),carPartComeDto.getType(),carPartStockIncomes);
        return ResultUtils.createNullResult();
    }
	
	
	@ApiOperation(value="出库")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "type", value = "类型 1-线下销售出库 2-线上维修出库", required = true,paramType="query", dataType = "int",defaultValue="1"),
        @ApiImplicitParam(name = "totalAmount", value = "总金额", required = true,paramType="query", dataType = "double",defaultValue="1"),
        @ApiImplicitParam(name = "employeeId", value = "employeeId", required = true,paramType="query", dataType = "Long",defaultValue="4"),
        @ApiImplicitParam(name = "carPartDtos[0].carPartId", value = "商品id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "carPartDtos[0].carPartName", value = "商品名称", required = true,paramType="query", dataType = "String",defaultValue="不见"),
        @ApiImplicitParam(name = "carPartDtos[0].price", value = "金额", required = true,paramType="query", dataType = "Long",defaultValue="4"),
        @ApiImplicitParam(name = "carPartDtos[0].code", value = "条形码", required = true,paramType="query", dataType = "String",defaultValue="sj0123"),
        @ApiImplicitParam(name = "carPartDtos[1].carPartId", value = "商品id", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "carPartDtos[1].price", value = "入库金额", required = true,paramType="query", dataType = "Long",defaultValue="4"),
        @ApiImplicitParam(name = "carPartDtos[1].code", value = "条形码", required = true,paramType="query", dataType = "String",defaultValue="sj012113"),

    })
    @RequestMapping(value="outcome.action",method=RequestMethod.POST)
    public RemoteResult outcome(CarPartComeDto carPartComeDto) {
        if(carPartComeDto == null){
            throw new ParamException("商品不能为空");
        }
        if(carPartComeDto.getTotalAmount() == null){
            throw new ParamException("totalAmount不能为空");
        }
        if(carPartComeDto.getEmployeeId() == null){
            throw new ParamException("出库人不能为空");
        }
        if(carPartComeDto.getType() == null){
            throw new ParamException("类型不能为空");
        }
        if(carPartComeDto.getCarPartDtos() == null||carPartComeDto.getCarPartDtos().size() == 0){
            throw new ParamException("商品不能为空");
        }
        List<CarPartStockOutcome> carPartStockOutcomes = new ArrayList();
         for(CarPartDto carPartDto:carPartComeDto.getCarPartDtos()){
            CarPartStockOutcome carPartStockOutcome = new CarPartStockOutcome(carPartDto.getCarPartId(),carPartDto.getCarPartName(),carPartDto.getCode(),carPartDto.getPrice());
            carPartStockOutcomes.add(carPartStockOutcome);
        }
        carPartStockOutcomeService.outcome(carPartComeDto.getEmployeeId(),carPartComeDto.getTotalAmount(),carPartComeDto.getType(),carPartStockOutcomes);
        return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value="提醒用户付款")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "orderId", value = "订单id", required = true,paramType="query", dataType = "long",defaultValue="1"),
    })
    @RequestMapping(value="warnUserPay.action",method=RequestMethod.POST)
    public RemoteResult warnUserPay(Long orderId) {
        if(orderId == null){
            throw new ParamException("订单id不能为空");
        }
        orderService.warnUserPay(orderId);
        return ResultUtils.createNullResult();
    }
	
	@ApiOperation(value="接收订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "employeeId", value = "employeeId", required = true,paramType="query", dataType = "long",defaultValue="1"),
    })
    @RequestMapping(value="getReceiveOrderInfo.action",method=RequestMethod.POST)
    public RemoteResult getReceiveOrderInfo(Long employeeId) {
        if(employeeId == null){
            throw new ParamException("employeeId不能为空");
        }
        ReceiveOrderInfo receiveOrderInfo = employeeService.getReceiveOrderInfo(employeeId);
        return ResultUtils.createDefResult(receiveOrderInfo);
    }
	
	@ApiOperation(value="订单完成")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "employeeId", value = "employeeId", required = true,paramType="query", dataType = "long",defaultValue="1"),
        @ApiImplicitParam(name = "orderId", value = "orderId", required = true,paramType="query", dataType = "long",defaultValue="1"),
    })
    @RequestMapping(value="finishOrder.action",method=RequestMethod.POST)
    public RemoteResult finishOrder(Long employeeId,Long orderId) {
        if(employeeId == null){
            throw new ParamException("employeeId不能为空");
        }
        if(orderId == null){
            throw new ParamException("orderId不能为空");
        }
        orderService.finishOrder(employeeId,orderId);
        return ResultUtils.createNullResult();
    }
	

}