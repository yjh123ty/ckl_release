<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<style>
.hotelModel td, tr {
	margin: 5px;
	padding: 5px;
}

.hotelModel {
	margin: 30px;
	padding: 30px;
	font-size: 15px;
}

.restModel td, tr {
	margin: 5px;
	padding: 5px;
}

.restModel th {
	margin: 8px;
	padding: 8px;
}

.restModel {
	margin: 30px;
	padding: 30px;
	font-size: 12px;
}
</style>
</head>
<body>
	<div id="content" style='margin: 10px; padding: 10px'>
		<!-- 操作栏 -->
		<div class="am-g" style='margin: 1px; padding: 10px'>

			<c:choose>
				<c:when test="${detail.status == 5 && (detail.serviceType.type == 1 || detail.serviceType.type == 2)}">
					<a href="javascript:void(0)"
						class="am-btn am-btn-success am-btn-xs ask-refund"
						style="border-radius: 300px; width: 100px" role="button">退款</a>
					&emsp;
					<a id="struBtn" href="javascript:void(0)"
						class="am-btn am-btn-success am-btn-xs service-code"
						style="border-radius: 300px; width: 100px" role="button">验证服务码</a>
					&emsp;
					<a href="#" class="am-btn am-btn-default am-btn-xs"
						style="border-radius: 300px; width: 100px" role="button"
						disabled="disabled">提交服务记录</a>
					&emsp;
					<a href="${ctx}/order/index.do" class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px; width: 100px" role="button">返回</a>
				</c:when>
				
				<c:when test="${detail.status == 5}">
					<a href="javascript:void(0)"
						class="am-btn am-btn-success am-btn-xs ask-refund"
						style="border-radius: 300px; width: 100px" role="button">退款</a>
					&emsp;
					<a href="${ctx}/order/index.do" class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px; width: 100px" role="button">返回</a>
				</c:when>

				<c:when test="${detail.status == 6 && (detail.serviceType.type == 1 || detail.serviceType.type == 2) && detail.isPayNew == false}">
					<a href="javascript:void(0)"
						class="am-btn am-btn-success am-btn-xs ask-refund"
						style="border-radius: 300px; width: 100px" role="button"
						disabled="disabled">退款</a>
					&emsp;
					<a id="struBtn" href="javascript:void(0)"
						class="am-btn am-btn-success am-btn-xs service-code"
						style="border-radius: 300px; width: 100px" role="button"
						disabled="disabled">验证服务码</a>
					&emsp;
					<a href="javascript:void(0)"
						class="am-btn am-btn-success am-btn-xs service-commited"
						style="border-radius: 300px; width: 100px" role="button">提交服务记录</a>
					&emsp;
					<a href="${ctx}/order/index.do" class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px; width: 100px" role="button">返回</a>
				</c:when>
				
				<c:when
					test="${detail.status == 3 || detail.status == 8}">
					<a href="${ctx}/order/index.do" class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px; width: 100px" role="button">返回</a>
				</c:when>

				<c:otherwise>
					<a href="javascript:void(0)"
						class="am-btn am-btn-success am-btn-xs ask-refund"
						style="border-radius: 300px; width: 100px" role="button"
						disabled="disabled">退款</a>
					&emsp;
					<a href="${ctx}/order/index.do" class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px; width: 100px" role="button">返回</a>
				</c:otherwise>
			</c:choose>
		</div>

		<hr>
		<!-- 数据展示 -->
		<div style="float: left; width: 500px; height: 600px">
			<input type="hidden" value="${detail.paidAmount}"
				id="refundPaidAmount">
			<table class="orderTable">
				<tr>
					<td>订单号:</td>
					<td>${detail.orderNumber}</td>
				</tr>
				<tr>
					<td>订单状态:</td>
					<td><c:choose>
							<c:when test="${detail.status == 1}">
	    			已退款
		    		</c:when>

							<c:when test="${detail.status == 2}">
		    		退款中
		    		</c:when>

							<c:when test="${detail.status == 3}">
		    		已取消
		    		</c:when>

							<c:when test="${detail.status == 4}">
		    		待支付
		    		</c:when>

							<c:when test="${detail.status == 5}">
		    		待服务
		    		</c:when>

							<c:when test="${detail.status == 6}">
		    		待完成
		    		</c:when>

							<c:when test="${detail.status == 7}">
		    		待评价
		    		</c:when>

							<c:when test="${detail.status == 8}">
		    		已完成
		    		</c:when>
						</c:choose></td>
				</tr>

				<tr>
					<td>订单类型:</td>
					<td>${detail.serviceType.name}</td>
				</tr>

				<tr>
					<td>服务站点:</td>
					<td>${detail.station.name}</td>
				</tr>

				<tr>
					<td>订单金额:</td>
					<td>${detail.totalAmount}</td>
				</tr>

				<tr>
					<td>支付方式:</td>
					<td><c:choose>
							<c:when test="${detail.payMethod == 1}">
	    			支付宝
		    		</c:when>

							<c:when test="${detail.payMethod == 2}">
		    		微信支付
		    		</c:when>

							<c:when test="${detail.payMethod == 3}">
		    		平台币
		    		</c:when>

							<c:when test="${detail.payMethod == 4}">
		    		人工帮助
		    		</c:when>
						</c:choose></td>
				</tr>

				<tr>
					<td>用户账号:</td>
					<td>${detail.user.mobile}</td>
				</tr>

				<tr>
					<td>用户昵称:</td>
					<td>${detail.user.nickName}</td>
				</tr>

				<tr>
					<td>创建时间:</td>
					<td>${detail.createTime}</td>
				</tr>
				
				<c:if test="${detail.status == 8}">
					<tr>
						<td></td>
						<td>
							<a href="javascript:void(0)"
								class="am-btn am-btn-success am-btn-xs print-details"
								style="border-radius: 300px; width: 100px" role="button">打印单据</a>
						</td>
					</tr>
				</c:if>
			</table>
		</div>

			<table class="orderTable">
					<tr>
						<th>发票信息</th>
					</tr>
					<tr>
						<td>发票抬头：</td>
						<td>${detail.invoiceTitle}</td>
					</tr>
			</table>
		<hr>
		<!-- 根据不同的订单类型显示不同的字段 -->
		<!-- 酒店 -->
		<c:if test="${detail.serviceType.type == 1}">
			<input id="roomNumber" value="${detail.roomNumber}" type="hidden">
			<input id="totalAmount" value="${detail.totalAmount}" type="hidden">
			<table class="orderTable">
				<tr>
					<th>入住信息</th>
				</tr>
				<tr>
					<td>入住人姓名:</td>
					<td>${detail.contactName}</td>
				</tr>
				<tr>
					<td>手机号:</td>
					<td>${detail.contactMobile}</td>
				</tr>
				<tr>
					<td>房间类型:</td>
					<td>${detail.roomType}</td>
				</tr>
				<tr>
					<td>房间数量:</td>
					<td>${detail.roomNumber}</td>
				</tr>
				<tr>
					<td>入住天数:</td>
					<td>${detail.days}</td>
				</tr>
				<tr>
					<td>入住时间:</td>
					<td>${detail.startTime}</td>
				</tr>
				<tr>
					<td>退房时间:</td>
					<td>${detail.endTime}</td>
				</tr>
			</table>

			<c:forEach items="${orderServiceDetail}" var="detailVal">
				<c:if test="${detailVal.type==2}">
					<table class="orderTable">
						<tr>
							<th>续房信息</th>
						</tr>
						<tr>
							<td>续房天数:</td>
							<td>${detailVal.serviceName}</td>
						</tr>
						<tr>
							<td>房间数量:</td>
							<td>${detailVal.number}</td>
						</tr>
						<tr>
							<td>支付金额:</td>
							<td>${detail.stayPayAmount}</td>
						</tr>
					</table>
				</c:if>
			</c:forEach>
		</c:if>

		<!-- 饭店 -->
		<c:if test="${detail.serviceType.type == 2}">

			<table class="orderTable">
				<tr>
					<th>商品信息</th>
				</tr>
				<tr>
					<td>套餐:</td>
					<td>${detail.comboName}</td>
				</tr>

				<tr>
					<td>使用时间:</td>
					<td>${detail.startTime} - ${detail.endTime}</td>
				</tr>

				<tr>
					<td>菜品介绍:</td>
				</tr>

				<tr>
					<td></td>
					<td>
							<c:forEach items="${orderServiceDetail}" var="detailVal">
								<c:if test="${detailVal.type == 1}">
					  				${detailVal.serviceName} X ${detailVal.number} <br />
								</c:if>
							</c:forEach>
					</td>
				</tr>
				
				<c:forEach items="${orderServiceDetail}" var="detailVal">
				<c:if test="${detailVal.type == 2}">
					<table class="orderTable">
						<tr>
							<th>新增商品信息</th>
						</tr>
						<tr>
							<td>${detailVal.serviceName} X ${detailVal.number} &emsp; ${detailVal.servicePrice}</td>
						</tr>
						<tr>
							<td>支付金额:</td>
							<td>${detail.stayPayAmount}</td>
						</tr>
					</table>
				</c:if>
			</c:forEach>
				
			</table>
		</c:if>

		<!-- 汽车保养 -->
		<c:if test="${detail.serviceType.type == 3 || detail.serviceType.type == 4}">
			<table class="orderTable">
				<tr>
					<td>服务项目:</td>
					<td>${detail.serviceType.name}</td>
				</tr>

				<tr>
					<td>车辆型号:</td>
					<td>${detail.carType}</td>
				</tr>

				<tr>
					<td>车牌号:</td>
					<td>${detail.carPlate}</td>
				</tr>

				<tr>
					<td>上牌时间:</td>
					<td>${detail.carPlateDate}</td>
				</tr>

				<tr>
					<td>行驶里程:</td>
					<td>${detail.travelDistance}</td>
				</tr>
			</table>

			<table class="orderTable">
				<tr>
					<th>服务信息</th>
				</tr>

				<c:forEach items="${orderServiceDetail}" var="detailVal">
					<c:if test="${detailVal.type == 3}">
					  	<tr>
						    <td>${detailVal.serviceName}:</td>
						    <td>${detailVal.servicePrice}元	&emsp;<font style="color:red">(已退)</font></td>
					  	</tr>
				  	</c:if>
			  	
			  	
			  		<c:if test="${detailVal.type == 1 or detailVal.type == 2}">
						<tr>
							<td>${detailVal.serviceName} X ${detailVal.number}</td>
						<td>${detailVal.servicePrice * detailVal.number}元</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>

		</c:if>

		<!-- 道路救援 -->
		<c:if test="${detail.serviceType.type == 5}">
			<table class="orderTable">
				<tr>
					<td>汽车位置:</td>
					<td>${detail.serviceAddress}</td>
				</tr>

				<tr>
					<td>车主电话:</td>
					<td>${detail.contactMobile}</td>
				</tr>

				<tr>
					<td>服务项目:</td>
					<td>${detail.serviceType.name}</td>
				</tr>

				<tr>
					<td>车辆型号:</td>
					<td>${detail.carType}</td>
				</tr>

				<tr>
					<td>车牌号:</td>
					<td>${detail.carPlate}</td>
				</tr>

				<tr>
					<td>上牌时间:</td>
					<td>${detail.carPlateDate}</td>
				</tr>

				<tr>
					<td>行驶里程:</td>
					<td>${detail.travelDistance}Km</td>
				</tr>

				<tr>
					<td>受损情况:</td>
					<td>${detail.carCondition}</td>
				</tr>
			</table>

			<table class="orderTable">
				<tr>
					<th>商品信息</th>
				</tr>

				<c:forEach items="${orderServiceDetail}" var="detailVal">
					<tr>
						<td>${detailVal.serviceName} X ${detailVal.number}</td>
						<td>${detailVal.servicePrice * detailVal.number}元</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

	</div>

	<input id="rowId" name="id" value="${detail.id}" type="hidden" />
	<input id="orderTypeId" value="${detail.serviceType.type}"
		type="hidden" />

	<!-- 验证服务码-模态对话框 -->
	<div style="text-align: center">
		<div id="dd">
			<form id="order-service-code-form" method="post">
				<table>
					<tr>
						<td
							style="width: 80px; height: 140px; margin: 10px; text-align: center; font-size: 20px">
							服务码:</td>
						<td><input name="code" class="easyui-textbox"></td>
					</tr>

					<tr>
						<td
							style="width: 220px; height: 140px; margin: 20px; text-align: center; font-size: 20px">
							<a href="javascript:void(0)"
							class="am-btn am-btn-success check-code"
							style="border-radius: 100px; width: 80px" role="button">验证</a>
						</td>
						<td><a href="javascript:void(0)"
							class="am-btn am-btn-success back"
							style="border-radius: 100px; width: 80px" role="button">返回</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>


	<!-- 提交服务记录-模态对话框 -->
	<div style="text-align: center">
		<div id="serviceRecordModel">
			<!-- 酒店服务 -->
			<table class="hotelModel">
				<tr>
					<th>已购买的酒店服务</th>
				</tr>

				<tr>
					<td>房间类型：</td>
					<td>${detail.roomType}</td>
				</tr>

				<tr>
					<td>入住天数：</td>
					<td>${detail.days}</td>
				</tr>

				<tr>
					<td>入住时间：</td>
					<td>${detail.startTime}</td>
				</tr>

				<tr>
					<td>退房时间：</td>
					<td>${detail.endTime}</td>
				</tr>
			</table>
			<hr>
			<form id="order-hotel-commit-form" method="post" action="">
				<input name="id" value="${detail.id}" type="hidden" />
				<table class="hotelModel">
					<tr>
						<th>续房信息</th>
					</tr>

					<tr>
						<td><input name="serviceName" class="easyui-textbox"
							data-options="prompt:'天数'"></td>
						<td><input name="number" class="easyui-textbox"
							data-options="prompt:'续时房间数量'"></td>
					</tr>

					<tr>
						<td><a href="javascript:void(0)"
							class="am-btn am-btn-success hotel-commit"
							style="border-radius: 100px; width: 80px" role="button">提交</a></td>
						<td><a href="javascript:void(0)"
							class="am-btn am-btn-success hotel-back"
							style="border-radius: 100px; width: 80px" role="button">返回</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>


	<!-- 提交服务记录-模态对话框 -->
	<div style="text-align: center">
		<div id="serviceRecordRestModel">
			<!-- 饭店服务 -->
			<table class="restModel">
				<tr>
					<th style="font-size: 20px">已购买的商品</th>
				</tr>

				<tr>
					<td>套餐:</td>
					<td>${detail.comboName}</td>
				</tr>

				<tr>
					<td>使用时间:</td>
					<td>${detail.startTime} - ${detail.endTime}</td>
				</tr>

				<tr>
					<td>菜品介绍:</td>
				</tr>

				<tr>
					<td></td>
					<td><c:forEach items="${orderServiceDetail}" var="detailVal">
				  		  ${detailVal.serviceName} X ${detailVal.number} <br />
						</c:forEach></td>
				</tr>
			</table>

			<hr>

			<form id="order-rest-commit-form" method="post"
				action='${ctx}/order/finish.do'>
				<input name="id" value="${detail.id}" type="hidden" />
				<table id="restTable" class="restModel">
					<tbody id="restBody">
						<tr>
							<th style="font-size: 20px">未购买的商品</th>
						</tr>

						<tr>
							<td><a id="add-rest" href="javascript:void(0)"
								class="am-btn am-btn-success"
								style="border-radius: 5px; width: 100px" role="button">新添商品</a>
								<a href="javascript:void(0)"
								class="am-btn am-btn-success remove-rest"
								style="border-radius: 5px; width: 70px" role="button">删除</a></td>

						</tr>



						<tr class="restTr">
							<td style="width: 500px"><span style="width: 500px">
									<input name="addLists[0].serviceName" class="easyui-textbox"
									data-options="prompt:'商品名称'"> <input
									name="addLists[0].number" class="easyui-textbox"
									data-options="prompt:'商品数量'"> <input
									name="addLists[0].servicePrice" class="easyui-textbox"
									data-options="prompt:'商品金额'">
							</span></td>
						</tr>
					</tbody>

					<tfoot>
						<tr style="text-align: center">
							<td><span>
									<button href="javascript:void(0)"
										class="am-btn am-btn-success rest-commit"
										style="border-radius: 5px; width: 80px">提交</button> <a
									href="javascript:void(0)"
									class="am-btn am-btn-success rest-back"
									style="border-radius: 5px; width: 80px" role="button">返回</a>
							</span></td>
						</tr>
					</tfoot>
				</table>
			</form>
		</div>
	</div>



	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	<script type="text/javascript">

	$(function(){
		//请求路径
		var url =  _ctx+ '/order/refundById.do';
		var finishUrl = _ctx + "/order/finishRoadSide.do";
		var printUrl =  _ctx+ '/order/print.do';
		//订单id
		var rowId = $('#rowId').val();
		//退款金额
		var refund = $('#refundPaidAmount').val();
		//服务类型
		var orderTypeId = $('#orderTypeId').val();
		
		//初始化表单
		var orderCodeForm = $("#order-service-code-form");
		var hotelServiceCommitForm = $('#order-hotel-commit-form');
		var restServiceCommitForm = $('#order-rest-commit-form');
		
		//初始化验证框
		var codeModel = $('#dd');
		codeModel.dialog({
		    title: '验证服务码',
		    closed: true,
		    cache: false,
		    maximizable:true,
		    modal: true
		});
		
		//初始化 酒店提交服务记录 对话框
		var serviceRecordModel = $('#serviceRecordModel');
		serviceRecordModel.dialog({
		    title: '服务记录',
		    closed: true,
		    cache: false,
		    maximizable:true,
		    modal: true
		});
		
		//初始化 饭店提交服务记录 对话框
		var serviceRecordRestModel = $('#serviceRecordRestModel');
		serviceRecordRestModel.dialog({
		    title: '饭店服务记录',
		    closed: true,
		    cache: false,
		    maximizable:true,
		    modal: true
		});
		
		//申请退款
		$("a.ask-refund").click(function(data){
			$.messager.confirm("温馨提示", "是否申请退款？", function(yes) {
    			if (yes) {
    				$.post(url, {"id" : rowId}, 
						function(data) {
	    					// 判断
	    					if (data.success) {
	    						// 提示
	    						$.messager.alert("温馨提示", data.msg, "info",
	    							function() {
	    								//跳转页面
	    								location.href = _ctx + "/order/index.do";
	    							});
	    					} else {
	    						$.messager.alert("温馨提示", data.msg, "info");
	    					}
    					}, "json");
    			}
			});
		});
		
		//验证服务码
		$("a.service-code").click(function(data){
			//初始化出现位置(以点击的按钮为参照，向右下方偏移100，300)
			var top = $("#struBtn").offset().top + 100;
			var left = $("#struBtn").offset().left + 300;
			codeModel.window('open').window('resize',{width:'460px',height:'350px',top: top,left:left});
		});
		
		//提交服务记录对话框
		$("a.service-commited").click(function(data){
			//初始化出现位置(以点击的按钮为参照，向右下方偏移200，50)
			var top = $("#struBtn").offset().top + 50;
			var left = $("#struBtn").offset().left + 200;
			
			if(orderTypeId==1){
				serviceRecordModel.window('open').window('resize',{width:'400px',height:'500px',top: top,left:left});
			}
			if(orderTypeId==2){
				serviceRecordRestModel.window('open').window('resize',{width:'640px',height:'600px',top: top,left:left});
			}
			//道路救援
			if(orderTypeId==4){
				$.messager.confirm("温馨提示", "是否完成服务？", function(yes) {
	    			if (yes) {
	    				$.post(finishUrl, {"id" : rowId}, 
							function(data) {
		    					// 判断
		    					if (data.success) {
		    						// 提示
		    						$.messager.alert("温馨提示", data.msg, "info",
		    							function() {
		    								//跳转页面
		    								location.href = _ctx + "/order/index.do";
		    							});
		    					} else {
		    						$.messager.alert("温馨提示", data.msg, "info");
		    					}
	    					}, "json");
	    			}
				});
			}
		});
		
		
		//点击验证按钮
		$("a.check-code").click(function(data){
			// 封装服务类型参数
			var data = {'id':rowId};
			
			//提交表单，验证码==数据库查询的当前该订单的验证码?验证成功，修改操作栏显示，修改订单状态 : 验证失败，给出提示
			orderCodeForm.ajaxSubmit( {    
    	       url:       _ctx +'/order/checkCodeById.do', 
    	       data:data,
    	       type:      "post",
    	       clearForm: false,     
    	       resetForm: false,     
    	       beforeSubmit: function(){
    	    	   // 校验参数
    	       }, 
    	       success: function(data){
    	    	   if(isJsonObj(data)){
						if(data.success){
							msgShow("提示", data.msg, "info");
							//关闭
							codeModel.dialog('close');
							//重新跳转
							location.href= _ctx + "/order/index.do?cmd=detail&id=" + rowId;
						}else {
							msgShow("提示", data.msg, "info");
							//关闭
							codeModel.dialog('close');
						}
					} else {
						msgShow("提示", "系统异常", "info");
						//关闭
						codeModel.dialog('close');
					}
    	       }
    	   });
					
		});
		
		//点击酒店服务提交按钮
		$("a.hotel-commit").click(function(data){
			var data = {};
			//封装服务类型参数
			//提交表单，验证码==数据库查询的当前该订单的验证码?验证成功，修改操作栏显示，修改订单状态 : 验证失败，给出提示
			hotelServiceCommitForm.ajaxSubmit( {    
    	       url:       _ctx +'/order/finishHotelService.do', 
    	       data:data,
    	       type:      "post",
    	       clearForm: false,     
    	       resetForm: false,     
    	       beforeSubmit: function(){
    	    	   // 校验参数
    	       }, 
    	       success: function(data){
    	    	   if(isJsonObj(data)){
						if(data.success){
							msgShow("提示", data.msg, "info");
							//关闭
							codeModel.dialog('close');
							//重新跳转
							location.href= _ctx + "/order/index.do?cmd=detail&id=" + rowId;
						}else {
							msgShow("提示", data.msg, "info");
							//关闭
							codeModel.dialog('close');
						}
					} else {
						msgShow("提示", "系统异常", "info");
						//关闭
						codeModel.dialog('close');
					}
    	       }
    	   });
					
		});
		
		
		//点击饭店服务提交按钮
		$("a.rest-commit").click(function(data){
			var data = {};
			//封装服务类型参数
			restServiceCommitForm.ajaxSubmit( {    
    	       url:       _ctx +'/order/finish.do', 
    	       data:data,
    	       type:      "post",
    	       clearForm: false,     
    	       resetForm: false,     
    	       beforeSubmit: function(){
    	    	   // 校验参数
    	       }, 
    	       success: function(data){
    	    	   if(isJsonObj(data)){
						if(data.success){
							msgShow("提示", data.msg, "info");
							//关闭
							codeModel.dialog('close');
							//重新跳转
							location.href= _ctx + "/order/index.do?cmd=detail&id=" + rowId;
						}else {
							msgShow("提示", data.msg, "info");
							//关闭
							codeModel.dialog('close');
						}
					} else {
						msgShow("提示", "系统异常", "info");
						//关闭
						codeModel.dialog('close');
					}
    	       }
    	   });
					
		});
		
		//点击返回按钮
		$("a.back").click(function(data){
			//清除数据
			orderCodeForm.form("clear")
			//关闭
			codeModel.dialog('close');
		});
		
		$("a.hotel-back").click(function(data){
// 			//清除数据
// 			hotelServiceCommitForm.form("clear")
			//关闭
			serviceRecordModel.dialog('close');
		});
		
		$("a.rest-back").click(function(data){
// 			//清除数据
// 			restServiceCommitForm.form("clear")
			//关闭
			serviceRecordRestModel.dialog('close');
		});
		
		
		//饭店数据的行
		var restTr = $(".restTr");
		//准备一个全局的数值，用作记录新增列的序号
		var restIndex = 0;
		//在数据体的最后来追加新的一行
		var restBody = $("#restBody");
		
		//点击新增一行按钮
		$("#add-rest").click(function(){
			var tr = restTr.clone(false);
			restIndex ++;
			tr.find("input").each(function(i, input){
				 input.name = input.name.replace(/\d/, restIndex);
				 //清空新增的一列
				 input.value='';
				 //当前当前焦点，为点击的输入框赋值
				 $(input).on("blur",function(){
					 //拿到当前遍历的这一行的值，更改为页面输入的值
					$(input).parent().find(".textbox-value").val(this.value);
				 });
			});
			//新增一行，追加到末尾
			restBody.append(tr);
		});
		
		//点击删除一行按钮
		$(".remove-rest").click(function(){
			if($("#restTable tr").size()>4){
				$("#restBody tr").last().remove();			
			}
		});
		
		//打印单据
		$("a.print-details").click(function(){
			$.messager.confirm("温馨提示", "是否打印单据？", function(yes) {
    			if (yes) {
    				$.post(printUrl, {"id" : rowId}, 
						function(data) {
	    					// 判断
	    					if (data.success) {
	    						// 提示
	    						$.messager.alert("温馨提示", data.msg, "info");
	    					} else {
	    						$.messager.alert("温馨提示", data.msg, "info");
	    					}
   					}, "json");
    				
    				
    			}
			});
		});
		
		
	})
	
</script>

</body>
</html>