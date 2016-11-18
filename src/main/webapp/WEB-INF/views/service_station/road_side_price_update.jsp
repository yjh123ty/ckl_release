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
<title>服务站添加/修改</title>
<script type="text/javascript"
	src="${ctx}/js/service_station/road_side_price_update.js"></script>
</head>
<body class="am-g">
	<div class="am-u-lg-10 am-u-md-12 am-margin-top">
		<form id="form-service-station-edit"
			class="am-form am-form-horizontal"
			method="post">

			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">道路救援起步价：</label>
				<div class="am-u-sm-10">
					<input name="startPrice" type="number" id="doc-ipt-3-a" class="am-form-field" value="${roadSide.startPrice}"
						placeholder="请输入道路救援起步价" required="required" min="0.00">
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">道路救援公里价：</label>
				<div class="am-u-sm-10">
					<input name="milPrice" type="number" id="doc-ipt-3-a" class="am-form-field" value="${roadSide.milPrice}"
						placeholder="请输入道路救援公里价" required="required" min="0.00">
				</div>
			</div>

			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input data-cmd="confirm" type="button"
						class="am-btn am-btn-success am-btn-sm" value="确定"></input> <input
						type="button" class="am-btn am-btn-success am-btn-sm"
						onclick="location.href='${ctx}/servicestation/index.do'"
						value="返回"></input>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
</body>
</html>