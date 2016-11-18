<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工考勤明细</title>
<script type="text/javascript" src="${ctx}/js/salary/employeeAttendance.js"></script>
</head>
<body >
		<div  style="padding:10px;margin:10px">
		<!-- 查询栏 -->
		<form id="employeeAttendance-search-form" method="post">
			<input id="search-employee-id" type="hidden" name="employeeId" value="${employeeId}">
			<input id="search-record-month" type="hidden" name="recordMonth" value="${recordMonth}">
		</form>
		
		<a href="${ctx}/employeeAttendanceInfo/index.do" class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px; width: 100px" role="button">返回</a>
		
			<!-- 数据表格 -->
			<table id="employeeAttendance-datagrid" style="height: auto; min-height:400px; width: 100%;"></table>
			
		</div>
</body>
</html>