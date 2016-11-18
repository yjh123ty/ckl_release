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
	src="${ctx}/js/service_station/service_station_edit.js"></script>
</head>
<body class="am-g">
	<div class="am-u-lg-10 am-u-md-12 am-margin-top">
		<form id="form-service-station-edit"
			class="am-form am-form-horizontal" enctype="multipart/form-data"
			method="post">
			<input type="hidden" name="id" id="service-station-id" value="${station.id}">
			<input type="hidden" name="type" value="2">
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">服务站名称：</label>
				<div class="am-u-sm-10">
					<input name="name" type="text" id="doc-ipt-3-a"
						class="am-form-field" placeholder="请输服务站名称"
						value="${station.name}" required="required">
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">服务站地址：</label>
				<div class="am-u-sm-3">
					<select id="addr-prov" required="required" min="1">
					</select>
				</div>
				<div class="am-u-sm-3">
					<select id="addr-city" required="required" min="1">
					</select>
				</div>
				<div class="am-u-sm-3 am-u-end">
					<select id="addr-dist" name="district.code" required="required" min="1">
					</select>
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">详细地址：</label>
				<div class="am-u-sm-10">
					<input name="address" type="text" id="doc-ipt-3-a"
						class="am-form-field" placeholder="请输详细地址"
						value="${station.address}" required="required">
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">服务站电话：</label>
				<div class="am-u-sm-10">
					<input name="mobile" type="number" id="doc-ipt-3-a"
						class="am-form-field" placeholder="请输入电话"
						value="${station.mobile}" required="required" pattern="^[1][3,4,5,8][0-9]{9}$">
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">服务站星级：</label>
				<div class="am-u-sm-5 am-u-end">
						<select name="stars">
							<option value="1"
								<c:if test="${station.stars == 1}">
									selected="selected"
								</c:if> 
							>1</option>
							<option value="2" 
								<c:if test="${station.stars == 2}">
									selected="selected"
								</c:if>
								>2</option>
							<option value="3"
								<c:if test="${station.stars == 3}">
									selected="selected"
								</c:if>
							>3</option>
							<option value="4"
								<c:if test="${station.stars == 4}">
									selected="selected"
								</c:if>
							>4</option>
							<option value="5"
								<c:if test="${station.stars == 5}">
									selected="selected"
								</c:if> 
							>5</option>
					</select>
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">服务站描述：</label>
				<div class="am-u-sm-10">
					<input name="intro" type="text" id="doc-ipt-3-a"
						class="am-form-field" placeholder="描述内容" value="${station.intro}">
				</div>
			</div>
			<div class="am-form-group am-form-file">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">服务站图片：</label>
				<div class="am-u-sm-2">
					<button type="button" class="am-btn am-btn-success am-btn-sm">
						<i class="am-icon-cloud-upload"></i> 选择要上传的文件
					</button>
					<input name="images" id="doc-form-file" type="file"
						multiple="multiple" accept="image/png,image/jpeg">
				</div>
				<div id="file-list" class="am-u-sm-8 am-u-end">没有选择任何文件</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">服务内容：</label>
				<div class="am-u-sm-10">
					<c:forEach items="${serviceTypes}" var="type">
						<label class="am-checkbox-inline"> <input class="service-types" type="checkbox" value="${type.type}" 
							<c:forEach items="${station.serviceTypes}" var="t">
								<c:if test="${t.id == type.id}">
									checked="checked"
								</c:if>
							</c:forEach>
							>
							${type.name}
						</label>
					</c:forEach>
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
	<script type="text/javascript">
	addressInit('${station.district.city.province.code}','${station.district.city.code}','${station.district.code}');
		/* 
			文件上传 js 效果
		 */
		$(function() {
			$('#doc-form-file').on(
					'change',
					function() {
						var fileNames = '';
						$.each(this.files, function() {
							fileNames += '<span class="am-badge">' + this.name
									+ '</span> ';
						});
						fileNames = fileNames ? fileNames : "没有选择任何文件";
						$('#file-list').html(fileNames);
					});
		});
	</script>
</body>
</html>