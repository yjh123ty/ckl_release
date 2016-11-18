<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户充值报表</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/userBill/userBillRechargeDetail.js"></script>

</head>
<body>
<div id="content" style='margin: 10px; padding: 10px'>
	<form id="userBillRechargeDetail-search-form" method="post">
		<input type="hidden" value="${uid}" id="uid">
		
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">时间：</div>
			<div class="am-input-group am-datepicker-date am-u-sm-3" data-am-datepicker="{format: 'yyyy-mm-dd',viewMode:'days',minViewMode: 'days'}">
				到 
			<input name="endTimeStr" class="easyui-datebox" style="width: 200px">
		</div>

		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">关键词：</div>
			<input name="keywords" class="easyui-searchbox" 
			data-options="prompt:'用户账号或昵称',searcher:Searching"
			style="width:150px">
		</div>		
	</form>
	
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
	<table id="userBillRechargeDetail-datagrid" style="height: auto; min-height:400px; width: 100%;">
	</table>
</div>

	<script language="javascript">

		//查询控件
		function Searching(){
 			search();
 		}
		
		 var searchForm =  $('#userBillRechargeDetail-search-form');
		 var datagrid = $('#userBillRechargeDetail-datagrid');

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
		
		$(function() {
			// 设置搜索按钮的点击事件
			searchConfirm.click(search);
			//表单清空
			$("#resetSearchForm").on("click", function() {
				searchForm.form("clear");
			});
		})

	</script>


	</script>

</body>
</html>