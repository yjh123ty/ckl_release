<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绩效工资比例规则</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/starsRule/starsRule.js"></script>
</head>
<body>

	<div class="am-g" style='margin: 1px; padding: 10px'>
		
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salary/index.do">员工工资表</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/employeeAttendanceInfo/index.do">员工考勤表</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salaryBase/index.do">基本工资标准</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salaryPointStandard/index.do">薪点工资标准</a>
			&nbsp;
			<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="${ctx}/salaryInflationRate/index.do">薪点值</a>
			&nbsp;
			<a class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" href="${ctx}/starsRule/index.do">服务星级评定标准</a>
			
	</div>
	<hr>

	<div id="content" style='margin: 10px; padding: 10px'>
		<!-- 操作栏 -->
		<div class="am-g" style='margin: 10px'>
			<a href="${ctx}/starsRule/index.do?cmd=save"
				class="am-btn am-btn-success am-btn-xs"
				style="border-radius: 300px;" role="button">添加</a>
		</div>

		<!-- 数据表格 -->
		<table id="starsRule-datagrid"></table>
	</div>
	
	
	
</body>
</html>