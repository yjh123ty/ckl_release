<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品管理</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

<script type="text/javascript" src="${ctx}/js/carPart/list.js"></script>

</head>
<body>
	<!-- 操作栏 -->
	<!-- 查询栏 -->
	<form id="carPart-search-form" method="post" class="am-form">
		<table class="am-margin">
			<tr>
				<td>
					<a href="${ctx}/carPart/index.do?cmd=save" 
						class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" role="button">添加</a>
					<a href="${ctx}/carPart//downloadExcelTemplate.do"
						class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" role="button">Excel模板</a>
					<a href="${ctx}/carPart/index.do?cmd=import"
						id="import-excel" 
						class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" role="button">导入</a>
				</td>
				<td class="am-padding-left">
					<select name="typeId" style="width: 300px">
					<option value="-1">全部类型</option>
					<c:forEach items="${types}" var="type">
					        <option  value="${type.id}"
					        	<c:if test="${carPart.type.id == type.id}">
				     			   selected="selected" 
								</c:if>
							>
								${type.name}
					        </option>
					</c:forEach>
			        </select>
				</td>
				<td class="am-padding-left">
					<input name="keywords" type="text" class="am-form-field" placeholder="商品名、商品类型名称">
				</td>
				<td class="am-padding-left">
					<button 
						id="search-confirm"
						class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px;" type="button">确&nbsp;定</button>
					<button 
						class="am-btn am-btn-success am-btn-xs"
						style="border-radius: 300px;" type="reset">清&nbsp;空</button>
				</td>
			</tr>
		</table>
	</form>
	
	<!-- 数据表格 -->
	<table id="carPart-datagrid" style="min-height: 400px; height: auto; width: 100%;"></table>
</body>
</html>