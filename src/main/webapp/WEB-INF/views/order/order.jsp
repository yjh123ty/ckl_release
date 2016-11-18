<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单管理</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>

<script type="text/javascript" src="${ctx}/js/order/order.js"></script>

</head>
<body>
	<!-- 操作栏 -->
	<div class="am-g">
		<input type="button" onclick="downloadDomain('order-search-form','order')" style="border-radius: 300px;"
				class="am-btn am-btn-success am-btn-xs" value="下载订单">
	</div>
	
	<hr>
	
	<!-- 查询栏 -->
	<form id="order-search-form" method="post">
		<!-- 从财务报表 跳转到该界面直接显示 一个服务站的查询数据 -->
		<input id="search-station-id" type="hidden" name="stationId" value="${stationId}">
		<!-- 从用户中心 跳转到该界面直接显示 一个用户的订单数据 -->
		<input id="search-user-id" type="hidden" name="userId" value="${userId}">
		<!--从员工中心 跳转到该界面直接显示 一个员工的订单数据 -->
		<input id="search-employee-id" type="hidden" name="employeeId" value="${employeeId}">
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">时间：</div>
			<div class="am-input-group am-datepicker-date am-sm-3 am-u-md-3 am-u-lg-3" data-am-datepicker="{format: 'yyyy-mm-dd',minView:2}">
			  <input type="text" name="beginTimeStr" class="am-form-field" placeholder="请输入开始时间" value="${beginTimeStr}" readonly>
			  <span class="am-input-group-btn am-datepicker-add-on">
			    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
			  </span>
			</div>
			<div class="am-u-sm-1" style="text-align: center;">
				到
			</div> 
			<div class="am-input-group am-datepicker-date am-sm-3 am-u-md-3 am-u-lg-3 am-u-end" data-am-datepicker="{format: 'yyyy-mm-dd',minView:2}">
			  <input type="text" name="endTimeStr" class="am-form-field" placeholder="请输入开始时间" value="${endTimeStr}" readonly>
			  <span class="am-input-group-btn am-datepicker-add-on">
			    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
			  </span>
			</div>
		</div>
		
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">订单状态：</div>
			<div class="am-u-sm-3 am-u-end">
				<select name="status" id="orderStatus" class="am-form-field">
					<option value="-1">全部</option>
					<option value="1">已退款</option>
					<option value="2">退款中 </option>
					<option value="3">已取消</option>
					<option value="4">待支付</option>
					<option value="5">待服务</option>
					<option value="6">待完成</option>
					<option value="7">待评价</option>
					<option value="8">已完成</option>
				</select>
			</div>
		</div>
		
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">关键词：</div>
			
			<div class="am-u-sm-5 am-u-end">
				<input name="keywords" type="text" class="am-form-field" placeholder="订单号、用户账号或昵称、服务站点">
			</div>
		</div>	
	</form>
	
	
	<div class="am-g am-margin">
		<div class="am-u-sm-1"
							style="text-align: center;">订单类型：</div>
		<div id="serviceType-btn-group" class="am-u-sm-5 am-u-end">
			<button class="am-btn am-btn-default am-btn-xs" style="border-radius: 200px;">全部</button>
			&nbsp;
			<button class="am-btn am-btn-default am-btn-xs" style="border-radius: 200px;">酒店</button>
			&nbsp;
			<button class="am-btn am-btn-default am-btn-xs" style="border-radius: 200px;">餐饮</button>
			&nbsp;
			<button class="am-btn am-btn-default am-btn-xs" style="border-radius: 200px;">汽车维护</button>
			&nbsp;
			<button class="am-btn am-btn-default am-btn-xs" style="border-radius: 200px;">汽车维修</button>
			&nbsp;
			<button class="am-btn am-btn-default am-btn-xs" style="border-radius: 200px;">道路救援</button>
		</div>
	</div>
	
	
	<div class="am-g am-margin">
		<div class="am-u-sm-1"
							style="text-align: center;">订单金额：</div>
		<div id="isPayNew-btn-group" class="am-u-sm-5 am-u-end">
			<button class="am-btn am-btn-default am-btn-xs" style="border-radius: 200px;">全部</button>
			&nbsp;
			<button class="am-btn am-btn-default am-btn-xs" style="border-radius: 200px;">不需补差价</button>
			&nbsp;
			<button class="am-btn am-btn-default am-btn-xs" style="border-radius: 200px;">待补差价</button>
		</div>
	</div>
	
	<div class="am-g am-margin">
			<div class=" am-u-sm-2 am-u-md-4" style="text-align: left;">
				<button 
					id="search-confirm" class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;">查&nbsp;询</button>&emsp;
				<button 
					class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;" type="reset" id="resetSearchForm">清&nbsp;空</button>
			</div>
	</div>
	
	<!-- 数据表格 -->
	<table id="order-datagrid" style="height: auto; min-height:400px; width: 100%;"></table>

	<script type="text/javascript">
	
	
	var isPayNewBtnGroup = $('#isPayNew-btn-group');
	var serviceTypeBtnGroup = $('#serviceType-btn-group');
	
	var searchForm = $('#order-search-form');
	var datagrid = $('#order-datagrid');
	
	// 搜索确定按钮
	var searchConfirm = $("#search-confirm");
	
	$(function(){
		
		var isPayNewBtn = isPayNewBtnGroup.children();
		var serviceTypeBtn = serviceTypeBtnGroup.children();
		
		//查询对象
		var paramObj = searchForm.serializeJson();
		//缓存当前查询
		var statusVal = paramObj.status;
		var isPayNewVal = paramObj.isPayNew;
		var serviceTypeIdVal = paramObj.serviceTypeId;
		
		//是否补差价的查询按钮组
		isPayNewBtn.each(function(i, btn){
			var isPayNew = i-1;
			$(btn).click(function(){
				isPayNewBtn.removeClass('am-btn-success');
				isPayNewBtn.addClass('am-btn-default');
				$(this).addClass('am-btn-success');
				if(isPayNew == -1){
					paramObj.isPayNew = null;
				}else{
					paramObj.isPayNew = isPayNew;
				}
				isPayNewVal = isPayNew;
				// 发送post请求刷新 datagrid 的数据
				datagrid.datagrid("load",paramObj);
			});
		});
		
		//订单类型的查询按钮组
		serviceTypeBtn.each(function(i, btn){
			var serviceTypeId = i;
			$(btn).click(function(){
				serviceTypeBtn.removeClass('am-btn-success');
				serviceTypeBtn.addClass('am-btn-default');
				$(this).addClass('am-btn-success');
				
				paramObj.serviceTypeId = serviceTypeId;
				serviceTypeIdVal = serviceTypeId;
				// 发送post请求刷新 datagrid 的数据
				datagrid.datagrid("load",paramObj);
			});
		});
		
		//订单类型下拉框
		var select = $('#orderStatus');
		select.on("change",function(){
			paramObj.status = select.val();
			statusVal = select.val();
			// 发送post请求刷新 datagrid 的数据
			datagrid.datagrid("load",paramObj);
		});
		
		//表单清空
		$("#resetSearchForm").on("click", function() {
			isPayNewBtn.removeClass('am-btn-success');
			isPayNewBtn.addClass('am-btn-default');
			serviceTypeBtn.removeClass('am-btn-success');
			serviceTypeBtn.addClass('am-btn-default');
			//清空表单
			searchForm.form("clear");
			//重新添加下拉框
			select.html("<option value='-1'>--请选择--</option>")
			.append("<option value='1'>已退款</option>")
			.append("<option value='2'>退款中 </option>")
			.append("<option value='3'>已取消</option>")
			.append("<option value='4'>待支付</option>")
			.append("<option value='5'>待服务</option>")
			.append("<option value='6'>待完成</option>")
			.append("<option value='7'>待评价</option>")
			.append("<option value='8'>已完成</option>");
			statusVal = null;
			isPayNewVal = null;
			serviceTypeIdVal = null;
		});
		
		// 设置搜索按钮的点击事件
		searchConfirm.click(function(){
			paramObj = searchForm.serializeJson();
			
			paramObj.status = statusVal;
			paramObj.isPayNew = isPayNewVal;
			paramObj.serviceTypeId = serviceTypeIdVal;
			// 发送post请求刷新 datagrid 的数据
			datagrid.datagrid("load",paramObj);
		});
		
	});
	
	</script> 
</body>
</html>