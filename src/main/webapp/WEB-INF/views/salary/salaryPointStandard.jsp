<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>薪点工资标准</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/salary/salaryPointStandard.js"></script>

</head>
<body>

<div class="am-g" style='margin: 1px; padding: 10px'>
		
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salary/index.do">员工工资表</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/employeeAttendanceInfo/index.do">员工考勤表</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salaryBase/index.do">基本工资标准</a>
			&nbsp;
			<a class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" href="${ctx}/salaryPointStandard/index.do">薪点工资标准</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salaryInflationRate/index.do">薪点值</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/starsRule/index.do">服务星级评定标准</a>
			&emsp;&emsp;
		
</div>
<hr>

  <div id="content" style='margin: 10px; padding: 10px'>
  
  	<!-- 查询栏 -->
	<form id="salaryPointStandard-search-form" method="post">
	
		<div class="am-g am-margin">
               <div class="am-u-sm-1"
					style="text-align: center;">岗位类别：</div>
				<div class="am-u-sm-3 am-u-end">
               <input id="jobId" name="jobId" class="easyui-combobox" 
               	data-options="
               		url: '${ctx}/jobTitle/getAll.do',
               		valueField:'id',
               		textField:'name',
               		panelHeight:'auto',
               		" >
               	</div>
		</div>
		
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
							style="text-align: center;">薪点级别：</div> 
			<div class="am-u-sm-3 am-u-end">
				<select name="salaryLevel" id="salaryLevel" class="am-form-field">
					<option value="-1">全部</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
				</select>
			</div>
		</div>	
	</form>
	
	<div class="am-g am-margin">
			<div class=" am-u-sm-2 am-u-md-4" style="text-align: left;">
				<button 
					id="search-confirm" class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;" id="resetSearchForm">查&nbsp;询</button>&emsp;
				<button 
					class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;" type="reset" id="resetSearchForm">清&nbsp;空</button>
			</div>
		</div>
	
  	<!-- 数据表格 -->
	<table id="salaryPointStandard-datagrid" style="height: auto; min-height:400px; width: 100%;"></table>
  </div>
  
  <script type="text/javascript">
 	var searchForm = $('#salaryPointStandard-search-form');
	var datagrid = $('#salaryPointStandard-datagrid');
	
	// 搜索确定按钮
	var searchConfirm = $("#search-confirm");
	
$(function(){
		
		//查询对象
		var paramObj = searchForm.serializeJson();
		//缓存当前查询
		var salaryLevelVal = paramObj.salaryLevel;
		
		var select = $('#salaryLevel');
		select.on("change",function(){
			paramObj.salaryLevel = select.val();
			salaryLevelVal = select.val();
			// 发送post请求刷新 datagrid 的数据
			datagrid.datagrid("load",paramObj);
		});
		
		//表单清空
		$("#resetSearchForm").on("click", function() {
			//清空表单
			searchForm.form("clear");
			//重新添加下拉框
			select.html("<option value='-1'>--请选择--</option>")
			.append("<option value='1'>1</option>")
			.append("<option value='2'>2 </option>")
			.append("<option value='3'>3</option>")
			.append("<option value='4'>4</option>")
			.append("<option value='5'>5</option>")
			.append("<option value='6'>6</option>")
			.append("<option value='7'>7</option>")
			.append("<option value='8'>8</option>")
			.append("<option value='9'>9</option>");
			salaryLevelVal = null;
		});
		
		// 设置搜索按钮的点击事件
		searchConfirm.click(function(){
			paramObj = searchForm.serializeJson();
			
			paramObj.salaryLevel = salaryLevelVal;
			// 发送post请求刷新 datagrid 的数据
			datagrid.datagrid("load",paramObj);
		});
		
	});
	
	
	</script> 
  
</body>
</html>