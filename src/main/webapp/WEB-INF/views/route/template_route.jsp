<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>路线添加/修改</title>
<script type="text/javascript" src="${ctx}/js/route/template_route.js"></script>
</head>
<body>
	<!-- 模板路线表格 -->
	<table id="route-datagrid">
	</table>
	
	<!-- 模板路线工具条 -->
	<div id="route-toolbar">
		<a href="#" class="easyui-linkbutton" data-cmd="add" data-options="iconCls:'icon-add',text:'添加'"> </a>
		<a href="#" class="easyui-linkbutton" data-cmd="update" data-options="iconCls:'icon-edit',text:'修改'"></a>
		<a href="#" class="easyui-linkbutton" data-cmd="refresh" data-options="iconCls:'icon-reload',text:'刷新'"></a>
	</div>


	<%-- 系统模板路线的弹出dialog --%>
	<div id="route-dialog">
		<%-- 添加修改的from表单 --%>
		<form id="route-form" method="post">
			<input type="hidden" name="id">
			<table style="margin: 20px;">
				<caption style="border-bottom: 1px solid #EEEEEE; padding-bottom: 5px; margin-bottom: 5px; font-weight: bold; color: gray;">模板路线</caption>
				<tr>
					<td>名称:</td>
					<td>
						<input class="easyui-textbox" type="text" name="name" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>图标:</td>
					<td>
						<input class="easyui-textbox" type="text" name="iconCls"/>
					</td>
				</tr>
				
				<tr>
					<td>url:</td>
					<td align="left">
						<input class="easyui-textbox" type="text" name="url"/>
					</td>
				</tr>
				<tr>
					<td>父模板路线:</td>
					<td align="left">
						<input id="cc" class="easyui-combobox" name="parent.id" data-options="valueField:'id',textField:'name',url:'/route/tree.do'" />  
					</td>
				</tr>
				<tr>
					<td>备注:</td>
					<td>
						<textarea rows="3" cols="20" name="intro"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="route-dialog-buttons">
		<a href="#" class="easyui-linkbutton" data-cmd="editSubmit" data-options="iconCls:'icon-ok c6',text:'确定'"> </a>
		<a href="#" class="easyui-linkbutton" data-cmd="editReset" data-options="iconCls:'icon-reload c6',text:'清空'"> </a>
		<a href="#" class="easyui-linkbutton" data-cmd="editCancel" data-options="iconCls:'icon-cancel',text:'取消'"></a>
	</div>
	<%-- end 模板路线的弹出dialog --%>
</body>
</html>