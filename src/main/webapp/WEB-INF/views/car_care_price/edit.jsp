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
	src="${ctx}/js/car_care_price/edit.js"></script>
</head>
<body class="am-g">
	<div class="am-u-lg-10 am-u-md-12 am-margin-top">
		<form id="form-carcareprice-edit"
			class="am-form am-form-horizontal"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" id="car-care-price-id" value="${carCarPrice.id}">
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">名称：</label>
				<div class="am-u-sm-10">
					<input name="name" type="text" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入维护项目名称" value="${carCarPrice.name}" required="required">
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">价格：</label>
				<div class="am-u-sm-10">
					<input name="price" type="number" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入维护项目价格" value="${carCarPrice.price}" required="required" min="0">
				</div>
			</div>
			
			<div class="am-form-group am-form-file">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">保养项目图片：</label>
				<div class="am-u-sm-10">
					<div style="display: inline-block;">
						<button type="button" class="am-btn am-btn-success am-btn-sm">
							<i class="am-icon-cloud-upload"></i> 选择要上传的文件
						</button>
						<input name="img" class="doc-form-file" type="file"
							accept="image/png,image/jpeg">
					</div>
					<div class="file-list" style="display: inline-block;">没有选择任何文件</div>
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">服务内容：</label>
				<div class="am-u-sm-10">
					<textarea rows="3" cols="16" name="content">${carCarPrice.content}</textarea>
				</div>
			</div>

			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="confirm" type="button"
					class="am-btn am-btn-success am-btn-sm" value="确定"/>
					<input type="button" class="am-btn am-btn-success am-btn-sm"
					onclick="location.href='${ctx}/carcareprice/index.do'" value="返回"/>
				</div>
			</div>
		</form>
	</div>
	
	<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">温馨提示</div>
	    <div class="am-modal-bd">
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn">确定</span>
	    </div>
	  </div>
	</div>
	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	<script type="text/javascript">
	/*
	 * 文件上传 js 效果
	 */
	$('.doc-form-file').on('change', function() {
		var fileNames = '';
		$.each(this.files, function() {
			fileNames += '<span class="am-badge">' + this.name + '</span> ';
		});
		fileNames = fileNames ? fileNames : "没有选择任何文件";
		$(this).closest(".am-form-file").find('.file-list').html(fileNames);
	});
	</script>
</body>
</html>