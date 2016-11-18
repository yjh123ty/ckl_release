<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户业绩</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>


<script type="text/javascript" src="${ctx}/js/userCommission/list_detail.js"></script>

</head>
<body>
	<!-- 操作栏 -->
	<form id="userCommission-search-form" method="post" class="am-form">
		<table class="am-margin">
			<tr>
				<td>
					<a 
						href="${ctx}/userDistribution/index.do"
							class="am-btn am-btn-success am-btn-xs"
							style="border-radius: 300px;" type="button">用户业绩</a>
					<a 
						href="${ctx}/rechargeCombo/index.do"
							class="am-btn am-btn-default am-btn-xs"
							style="border-radius: 300px;" type="button">分润设置</a>
				</td>
			</tr>
			<tr><td class="am-padding-top"></td></tr>
			<tr>
				<td>
				<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm',viewMode:'months',minViewMode: 'months'}">
				  <input type="text" name="startTimeStr" class="am-form-field" placeholder="开始时间" readonly>
				  <span class="am-input-group-btn am-datepicker-add-on">
				    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
				  </span>
				</div>
				</td>
				<td class="am-padding-left">到</td>
				<td class="am-padding-left">
				<div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm',viewMode:'months',minViewMode: 'months'}">
				  <input type="text" name="endTimeStr" class="am-form-field" placeholder="结束时间" readonly>
				  <span class="am-input-group-btn am-datepicker-add-on">
				    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
				  </span>
				</div>
				</td>
				<td class="am-padding-left">
					<input name="keyword" type="text" class="am-form-field" placeholder="请输入关键词" style="width:400px">
				</td>
				<td class="am-padding-left">
					<button 
						id="search-confirm"
						class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px;" type="button">确&nbsp;定</button>
					<button
						id="search-cancel"
						class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px;" type="button">清&nbsp;空</button>
				</td>
			</tr>
		</table>
	</form>
	<!-- 数据表格 -->
	<table id="userCommission-datagrid" style="min-height: 400px; height: auto; width: 100%;"></table>
</body>
</html>