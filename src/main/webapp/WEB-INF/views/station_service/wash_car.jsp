<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>酒店添加/修改</title>
</head>
<body class="am-g">

<c:forEach items="services" var="service">
	<div class="am-u-lg-10 am-u-md-12 am-margin-top">
		<c:forEach items="#{services}" var="service">
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">洗车价格：</label>
				<div class="am-u-sm-9">
					${service.minimal}
				</div>
			</div>
		</c:forEach>
	</div>
</c:forEach>
</body>
</html>