<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仓库列表</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>


<script type="text/javascript" src="${ctx}/js/carPartDepot/list.js"></script>

</head>
<body>
	<!-- 操作栏 -->
	<form id="carPartDepot-search-form" method="post" class="am-form">
		<table class="am-margin">
			<tr>
				<td>
					<a href="${ctx}/carPartDepot/index.do?cmd=save" 
						class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" role="button">添加</a>
				</td>
				<td class="am-padding-left">
					<input name="keyword" type="text" class="am-form-field" placeholder="仓库名称、服务站名称、库管员名称" style="width:400px">
				</td>
				<td class="am-padding-left">
					<button 
						id="search-confirm"
						class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px;" type="button">确&nbsp;定</button>
				</td>
			</tr>
		</table>
	</form>
	<!-- 数据表格 -->
	<table id="carPartDepot-datagrid" style="min-height: 400px; height: auto; width: 100%;"></table>
</body>
</html>