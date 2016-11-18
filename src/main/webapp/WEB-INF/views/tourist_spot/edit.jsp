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
<title>景点添加/修改</title>
<script type="text/javascript" src="${ctx}/js/tourist_spot/edit.js"></script>
</head>
<body class="am-g">
	<div class="am-u-lg-10 am-u-md-12 am-margin-top">
		<form id="form-tourist-spot-edit"
			class="am-form am-form-horizontal" enctype="multipart/form-data"
			method="post">
			<input type="hidden" name="id" id="tourist-spot-id" value="${touristSpot.id}">
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">景点站名称：</label>
				<div class="am-u-sm-10">
					<input name="name" type="text" id="doc-ipt-3-a"
						class="am-form-field" placeholder="请输景点名称"
						value="${touristSpot.name}" required="required">
				</div>
			</div>
			<div class="am-form-group am-form-file">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">景点图片：</label>
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
					<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">区域地址：</label>
					<div class="am-u-sm-3">
						<select id="addr-prov" required="required" min="0">
						</select>
					</div>
					<div class="am-u-sm-3">
						<select id="addr-city" required="required" min="0">
						</select>
					</div>
					<div class="am-u-sm-3 am-u-end">
						<select id="addr-dist" name="district.code" required="required" min="0">
						</select>
					</div>
				</div>
				<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">详细地址：</label>
				<div class="am-u-sm-10">
					<input name="address" type="text" id="doc-ipt-3-a"
						class="am-form-field" placeholder="详细地址" value="${touristSpot.address}" required="required"/>
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">景点描述：</label>
				<div class="am-u-sm-10">
					<textarea name="intro" type="text" id="doc-ipt-3-a"
						class="am-form-field" placeholder="描述内容" cols="20" rows="3" maxlength="200" required="required">${touristSpot.intro}</textarea>
				</div>
			</div>
			
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input data-cmd="confirm" type="button"
						class="am-btn am-btn-success am-btn-sm" value="确定"></input> <input
						type="button" class="am-btn am-btn-success am-btn-sm"
						onclick="location.href='${ctx}/touristspot/index.do'"
						value="返回"></input>
				</div>
			</div>
		</form>
	</div>


	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	<script type="text/javascript">
	addressInit('${touristSpot.district.city.province.code}','${touristSpot.district.city.code}','${touristSpot.district.code}');
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