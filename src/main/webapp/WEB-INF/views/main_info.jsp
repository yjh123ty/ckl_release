<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>车刻丽运营平台</title>
<%@ include file="/WEB-INF/views/commons/head.jsp" %>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp" %>
<style type="text/css">
	#welcomebox{
		text-align: center;
		margin-top: 200px;
	}
	#welcomebox span{
		font-size: 20px;
		font-weight: bold;
		color: gray;
		display: block;
		margin-top: 20px;
	}
</style>
</head>
<body>
	<div id="welcomebox">
	  		<img alt="欢迎图片" src="${ctx}/images/welcome.png" width="400px">
	  		<span>欢迎登录车刻丽运营平台</span>
	</div>
</body>
</html>