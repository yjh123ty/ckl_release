<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接收短信手机号管理</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/phoneMessage/phoneMessage.js"></script>

</head>
<body>
  	<!-- 操作栏 -->
	<div class="am-g am-margin">
		<a href="${ctx}/phoneMessage/index.do?cmd=save" class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" role="button">添加手机号</a>
	</div>
	
	<!-- 数据表格 -->
	<table id="phoneMessage-datagrid" style="height: auto; min-height:400px; width: 100%;"></table>
</body>
</html>