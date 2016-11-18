<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp" %>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<title>SOS求助</title>

<script type="text/javascript" src="${ctx}/js/sos/sosEmployeeIsNull.js"></script>
</head>
<body>

<div id="content" style='margin: 10px; padding: 10px'>
	<form id="sos-none-search-form" method="post">
	</form>

	<!-- 数据表格 -->
	<table id="sos-none-datagrid" style="height: auto; min-height:400px; width: 100%;"></table>
</div>

</body>
</html>