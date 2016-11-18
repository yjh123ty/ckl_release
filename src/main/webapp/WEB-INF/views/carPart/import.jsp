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
<script type="text/javascript" src="${ctx}/js/carPart/import.js"></script>
<title>零部件批量添加</title>
</head>
<body class="am-g">
	<div class="am-u-lg-7 am-u-md-10 am-margin-top">
		<form id="form-import" class="am-form am-form-horizontal" enctype="multipart/form-data" method="post">
			
			<div class="am-form-group am-form-file">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">零部件Excel：</label>
				<div class="am-u-sm-9">
					<div style="display: inline-block;">
						<button type="button" class="am-btn am-btn-success am-btn-sm">
							<i class="am-icon-cloud-upload"></i> 选择要上传的文件
						</button>
						<input name="file" class="doc-form-file" type="file">
					</div>
					<div class="file-list" style="display: inline-block;">没有选择任何文件</div>
				</div>
			</div>
			
			<div class="am-form-group">
				<div class="am-u-sm-9 am-u-sm-offset-3">
					<input id="car-confirm" 
						type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<a class="am-btn am-btn-success am-btn-sm" href="${ctx}/carPart/index.do">返回</a>
				</div>
			</div>
			
		</form>
	</div>
	
	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	
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