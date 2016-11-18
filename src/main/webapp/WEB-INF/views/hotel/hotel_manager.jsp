<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<title>酒店信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/js/hotel/hotel_manager.js"></script>
</head>
<body>
	<!-- 酒店工具条 -->
	<div class="am-g am-margin-left" style="padding: 8px;">
		<div class="am-g">
			<div class="am-u-sm-3">
				<a href="${ctx}/hotel/index.do?cmd=save"
					class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;">添&nbsp;加</a>
			</div>
		</div>
		<form id="hotel-search-form" class="am-form">
			<div class="am-g am-margin-top">
				<div class=" am-sm-1 am-u-md-1 am-u-lg-1"
					style="text-align: center;">关键词：</div>
				<div class="am-u-sm-6 am-u-md-5 am-u-lg-4 am-u-end">
					<div class="am-input-group">
						<input name="keyword" type="text" class="am-form-field" placeholder="请输入酒店/服务站名称">
						<span class="am-input-group-btn">
							<button  id="do-search" class="am-btn am-btn-success" type="button">
								<span class="am-icon-search"></span>
							</button>
						</span>
					</div>
				</div>
			</div>
		</form>
		<div class="am-g am-margin">
			<div class="am-u-sm-1"
						style="text-align: center;">酒店星级：</div>
			<div id="stars-btn-group" class="am-u-sm-5 am-u-end">
			<button
					class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;">全部</button>
				<button
					class="am-btn am-btn-default am-btn-xs"
					style="border-radius: 300px;">5星</button>
				<button
					class="am-btn am-btn-default am-btn-xs"
					style="border-radius: 300px;">4星</button>
				<button
					class="am-btn am-btn-default am-btn-xs"
					style="border-radius: 300px;">3星</button>
				<button
					class="am-btn am-btn-default am-btn-xs"
					style="border-radius: 300px;">2星</button>
				<button
					class="am-btn am-btn-default am-btn-xs"
					style="border-radius: 300px;">1星</button>
			</div>
		</div>
	
	</div>
	<!-- 酒店表格 -->
	<table id="hotel-datagrid" style="width: 100%;height: auto; min-height: 400px;">
	</table>
	<script type="text/javascript">
	var hotelDatagrid = $("#hotel-datagrid");
		function changeStatus(id,status){
			$.post(_ctx+"/hotel/changeStatus.do",{id:id,status:status},function(data){
				if(data && data.success) {
					hotelDatagrid.datagrid("reload");
				}
			}, "json");
		}
	</script>
</body>
</html>