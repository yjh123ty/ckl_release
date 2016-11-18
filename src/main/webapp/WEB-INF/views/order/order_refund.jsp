<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单退款管理</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>

<script type="text/javascript" src="${ctx}/js/order/orderRefund.js"></script>

</head>
<body>
	<hr>
	
	<!-- 查询栏 -->
	<form id="orderRefund-search-form" method="post">
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">时间：</div>
			<div class="am-input-group am-datepicker-date am-u-sm-3" data-am-datepicker="{format: 'yyyy-mm-dd',viewMode:'days',minViewMode: 'days'}">
				  <input type="text" name="beginTimeStr" class="am-form-field" placeholder="开始时间" readonly>
				  <span class="am-input-group-btn am-datepicker-add-on">
				    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
				  </span>
				</div>
				<div class="am-u-sm-1" style="text-align: center;" >
				到 
				</div>
				<div class="am-input-group am-datepicker-date am-u-sm-3 am-u-end" data-am-datepicker="{format: 'yyyy-mm-dd',viewMode:'days',minViewMode: 'days'}">
				  <input type="text" name="endTimeStr" class="am-form-field" placeholder="结束时间" readonly>
				  <span class="am-input-group-btn am-datepicker-add-on">
				    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
				  </span>
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
				<button class="am-btn am-btn-default am-btn-xs" style="border-radius: 200px;">汽车修理</button>
			</div>
	</div>
	
	<div class="am-g am-margin am-form-group">
			<div class="am-u-sm-1">
			</div>
			<div class=" am-u-sm-11">
				<button 
					id="search-confirm" class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;" id="resetSearchForm">查&nbsp;询</button>&emsp;
				<button 
					class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;" type="reset" id="resetSearchForm">清&nbsp;空</button>
			</div>
	</div>
	
	<!-- 数据表格 -->
	<table id="orderRefund-datagrid" style="height: auto; min-height:400px; width: 100%;"></table>

	<script type="text/javascript">

	var serviceTypeBtnGroup = $('#serviceType-btn-group');
	
	var searchForm = $('#orderRefund-search-form');
	var datagrid = $('#orderRefund-datagrid');
	
	// 搜索确定按钮
	var searchConfirm = $("#search-confirm");
	
$(function(){
		var serviceTypeBtn = serviceTypeBtnGroup.children();
		// 查询对象
		var paramObj = searchForm.serializeJson();
		//缓存当前查询
		var serviceTypeIdVal = paramObj.serviceTypeId;
		
		//订单类型的查询按钮组
		serviceTypeBtn.each(function(i, btn){
			var serviceTypeId = i;
			$(btn).click(function(){
				serviceTypeBtn.removeClass('am-btn-success');
				serviceTypeBtn.addClass('am-btn-default');
				$(this).addClass('am-btn-success');
				
				paramObj.serviceTypeId = serviceTypeId;
				// 发送post请求刷新 datagrid 的数据
				datagrid.datagrid("load",paramObj);
			});
		});
		
		//表单清空
		$("#resetSearchForm").on("click", function() {
			serviceTypeBtn.removeClass('am-btn-success');
			serviceTypeBtn.addClass('am-btn-default');
			searchForm.form("clear");
			serviceTypeIdVal = null;
		});
		
		// 设置搜索按钮的点击事件
		searchConfirm.click(function(){
			paramObj = searchForm.serializeJson();
			
			paramObj.serviceTypeId = serviceTypeIdVal;
			// 发送post请求刷新 datagrid 的数据
			datagrid.datagrid("load",paramObj);
		});
	});

	</script> 
</body>
</html>