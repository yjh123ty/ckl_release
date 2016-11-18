<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户反馈管理</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/feedback/feedback.js"></script>

</head>
<body>
<div id="content" style='margin: 10px; padding: 10px'>
	<!-- 查询栏 -->
	<form id="feedback-search-form" method="post">
		<!-- 从用户中心 跳转到该界面直接显示 一个关于该用户的数据 -->
		<input id="search-user-id" type="hidden" name="userId" value="${userId}">
		
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
	
	<div class="am-g am-margin">
		<div class="am-u-sm-1"
							style="text-align: center;">处理状态：</div>
		<div id="status-btn-group" class="am-u-sm-5 am-u-end">
			<button class="am-btn am-btn-default am-btn-xs" style="border-radius: 200px;">全部</button>
			&nbsp;
			<button class="am-btn am-btn-default am-btn-xs" style="border-radius: 200px;">未处理</button>
			&nbsp;
			<button class="am-btn am-btn-default am-btn-xs" style="border-radius: 200px;">已处理</button>
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
	<table id="feedback-datagrid" style="height: auto; min-height:400px; width: 100%;"></table>
	
</div>
	
	<script type="text/javascript">
	
		var statusBtnGroup = $('#status-btn-group');
		var searchForm = $('#feedback-search-form');
		var datagrid = $('#feedback-datagrid');
		
 		function Searching(){
 			search();
 		}

		var search = function(){
			// TODO 验证表单参数
			// 获取搜索表单 
			// 封装表单参数
			var paramObj = searchForm.serializeJson();
			// 发送post请求刷新 datagrid 的数据
			datagrid.datagrid("load",paramObj);
		};
		// 搜索确定按钮
		var searchConfirm = $("#search-confirm");
		
		
		$(function(){
			//查询对象
			var paramObj = searchForm.serializeJson();
			//缓存当前查询
			var statusVal = paramObj.status;
			
			var statusBtn = statusBtnGroup.children();
			
			statusBtn.each(function(i, btn){
				var status = i - 1 ;
				$(btn).click(function(){
					statusBtn.removeClass('am-btn-success');
					statusBtn.addClass('am-btn-default');
					$(this).addClass('am-btn-success');
					paramObj.status = status;
					statusVal = status;
					// 发送post请求刷新 datagrid 的数据
					datagrid.datagrid("load",paramObj);
				});
			});
			
			//表单清空
			$("#resetSearchForm").on("click", function() {
				statusBtn.removeClass('am-btn-success');
				statusBtn.addClass('am-btn-default');
				$('#feedback-search-form').form("clear");
				statusVal = null;
			});
			
			// 搜索操作
			searchConfirm.click(function(){
				paramObj = searchForm.serializeJson();
				paramObj.status = statusVal;
				// 发送post请求刷新 datagrid 的数据
				datagrid.datagrid("load",paramObj);
			});
		});

	</script>
	
</body>
</html>