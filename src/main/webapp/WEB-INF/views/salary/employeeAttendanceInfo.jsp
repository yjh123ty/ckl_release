<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工考勤表</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/salary/employeeAttendanceInfo.js"></script>

</head>
<body>

<div class="am-g" style='margin: 1px; padding: 10px'>
		
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salary/index.do">员工工资表</a>
			&nbsp;
			<a class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" href="${ctx}/employeeAttendanceInfo/index.do">员工考勤表</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salaryBase/index.do">基本工资标准</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salaryPointStandard/index.do">薪点工资标准</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salaryInflationRate/index.do">薪点值</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/starsRule/index.do">服务星级评定标准</a>
		
</div>
<hr>

  <div id="content" style='margin: 10px; padding: 10px'>
	<!-- 查询栏 -->
	<form id="employeeAttendanceInfo-search-form" method="post">
	
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
					style="text-align: center;">时间：</div> 
			<div class="am-input-group am-datepicker-date am-u-sm-3 am-u-end" data-am-datepicker="{format: 'yyyy-mm',viewMode:'months',minViewMode: 'months'}">
				  <input type="text" name="searchTimeStr" class="am-form-field" placeholder="月份" readonly>
				  <span class="am-input-group-btn am-datepicker-add-on">
				    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
				  </span>
			</div>
		</div>
		
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">关键词：</div>
			
			<div class="am-u-sm-5 am-u-end">
				<input name="keywords" type="text" class="am-form-field" placeholder="员工账号、姓名">
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
	<table id="employeeAttendanceInfo-datagrid" style="height: auto; min-height:400px; width: 100%;"></table>
  </div>
  
   <script type="text/javascript">
	
	var searchForm = $('#employeeAttendanceInfo-search-form');
	var datagrid = $('#employeeAttendanceInfo-datagrid');
	
	// 搜索确定按钮
	var searchConfirm = $("#search-confirm");
	
	$(function() {
		//查询对象
		var paramObj = searchForm.serializeJson();
		
		//表单清空
		$("#resetSearchForm").on("click", function() {
			searchForm.form("clear");
		});
		
		// 设置搜索按钮的点击事件
		searchConfirm.click(function(){
			paramObj = searchForm.serializeJson();
			datagrid.datagrid("load",paramObj);
		});
		
	})
  </script>
  
</body>
</html>