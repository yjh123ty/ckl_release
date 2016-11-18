<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>
<%-- 导入公共的头文件  --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/js/car_care_price/list.js"></script>
</head>
<body>
	
	<div class="am-margin">
		<a href="${ctx}/carcareprice/index.do?cmd=save"
						class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px;">添&nbsp;加</a>
	</div>
	<!-- 菜单表格 -->
	<table id="car-care-price-datagrid" style="width: 100%; height: auto; min-height: 400px;">
	</table>

</body>
</html>