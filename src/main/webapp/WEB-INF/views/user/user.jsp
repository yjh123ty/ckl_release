<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/user/user.js"></script>

</head>
<body>
	<form id="user-search-form" method="post">
	
	<div class="am-g am-margin">
		<div class="am-u-sm-1"
							style="text-align: center;">状态：</div>
		<div class="am-u-sm-11">
		<button class="am-btn am-btn-default am-btn-xs" id="allStatusBtn" style="border-radius: 200px;">全部</button>
		&nbsp;
		<button class="am-btn am-btn-default am-btn-xs" id="invokeBtn" style="border-radius: 200px;">已开通</button>
		&nbsp;
		<button class="am-btn am-btn-default am-btn-xs" id="freezeBtn" style="border-radius: 200px;">已冻结</button>
		</div>
	</div>
	
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
				<input name="keywords" type="text" class="am-form-field" placeholder="用户账号或昵称">
			</div>
		</div>	
	</form>
	
	
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
	<table id="user-datagrid" style="height: auto; min-height:400px; width: 100%;">
	</table>

	<script language="javascript">

		//查询控件
		function Searching(){
 			search();
 		}
		
		
		var searchForm = $('#user-search-form');
		var datagrid = $("#user-datagrid");
		
		var search = function(){
			// TODO 验证表单参数
			// 获取搜索表单 
			// 封装表单参数
			var paramObj = searchForm.serializeJson();
			//发送post请求刷新 datagrid 的数据
			datagrid.datagrid("load",paramObj);
		};
		
		// 搜索确定按钮
		var searchConfirm = $("#search-confirm");
		
		
		$(function() {
			var allStatusBtn = $("#allStatusBtn");
			var invokeBtn = $("#invokeBtn");
			var freezeBtn = $("#freezeBtn");
			
			// 设置搜索按钮的点击事件
			searchConfirm.click(search);
			
			allStatusBtn.click(function(){
				$(this).removeClass('am-btn-default');
				$(this).addClass('am-btn-success');
				
				invokeBtn.removeClass('am-btn-success');
				invokeBtn.addClass('am-btn-default');
				freezeBtn.removeClass('am-btn-success');
				freezeBtn.addClass('am-btn-default');
				var paramObj = searchForm.serializeJson();
				paramObj['status'] = "";
				//发送post请求刷新 datagrid 的数据
				datagrid.datagrid("load",paramObj);
			});
			
			//已冻结按钮
			invokeBtn.click(function(){
				$(this).removeClass('am-btn-default');
				$(this).addClass('am-btn-success');
				
				freezeBtn.removeClass('am-btn-success');
				freezeBtn.addClass('am-btn-default');
				allStatusBtn.removeClass('am-btn-success');
				allStatusBtn.addClass('am-btn-default');
				var paramObj = searchForm.serializeJson();
				paramObj['status'] = 0;
				//发送post请求刷新 datagrid 的数据
				datagrid.datagrid("load",paramObj);
			});
			
			
			//已开通按钮
			freezeBtn.click(function(){
				$(this).removeClass('am-btn-default');
				$(this).addClass('am-btn-success');
				
				invokeBtn.removeClass('am-btn-success');
				invokeBtn.addClass('am-btn-default');
				allStatusBtn.removeClass('am-btn-success');
				allStatusBtn.addClass('am-btn-default');
				var paramObj = searchForm.serializeJson();
				paramObj['status'] = -1;
				//发送post请求刷新 datagrid 的数据
				datagrid.datagrid("load",paramObj);
			});
			
			//表单清空
			$("#resetSearchForm").on("click", function() {
				invokeBtn.removeClass('am-btn-success');
				invokeBtn.addClass('am-btn-default');
				freezeBtn.removeClass('am-btn-success');
				freezeBtn.addClass('am-btn-default');
				allStatusBtn.removeClass('am-btn-success');
				allStatusBtn.addClass('am-btn-default');
				searchForm.form("clear");
			});
			
			
		})

	</script>

</body>
</html>