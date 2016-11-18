<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/js/sys_role/emp_sys_role.js"></script>
</head>
<body>
	
	<!-- 角色工具条 -->
	<div class="am-g" style="padding:8px;">
		<a href="${ctx}/role/index.do?cmd=grant" class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" role="button">添&nbsp;加</a>
	</div>
	<!-- 角色表格 -->
	<table id="emp-role-datagrid" style="width: 100%;height: auto; min-height: 400px;">
	</table>
</body>
</html>