<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>饭店菜品添加/修改</title>
<script type="text/javascript" src="${ctx}/js/restaurant_dishes/restaurant_dishes_edit.js"></script>
</head>
<body class="am-g">
	<div class="am-u-lg-10 am-u-md-12 am-margin-top">
		<form id="form-dishes" class="am-form am-form-horizontal" enctype="multipart/form-data" method="post">
			<input type="hidden" name="id" id="route-id" value="${dishes.id}">
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">所属饭店：</label>
				<div class="am-u-sm-10">
					<select id="restaurant-select" name="restaurantId" data-am-selected data-id="${dishes.restaurantId}" required="required">
					  
					</select>
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">菜品名称：</label>
				<div class="am-u-sm-10">
					<input name="name" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入饭店菜品名称" value="${dishes.name}" required="required">
				</div>
			</div>
			<div class="am-form-group am-form-file">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">菜品图片：</label>
				<div class="am-u-sm-2">
					<button type="button" class="am-btn am-btn-success am-btn-sm">
						<i class="am-icon-cloud-upload"></i> 选择要上传的文件
					</button>
					<input name="image" id="doc-form-file" type="file">
				</div>
				<div id="file-list" class="am-u-sm-8 am-u-end">没有选择任何文件</div>
			</div>

		    <div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">菜品价格：</label>
				<div class="am-u-sm-5">
					<input name="price" type="number"  id="doc-ipt-3-a" class="am-form-field" value="${dishes.price}" required="required" min="0">
				</div>
				<div class="am-u-sm-1 am-u-end">
					元
				</div>
			</div>
		    
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input data-cmd="edit-confrim" type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<input type="button" class="am-btn am-btn-success am-btn-sm" onclick="location.href='${ctx}/restaurantdishes/index.do'"  value="返回"></input> 
				</div>
			</div>
		</form>
	</div>

	
	<div class="am-modal am-modal-alert" tabindex="-1" id="msg-modal">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">温馨提示：</div>
	    <div id="msg_content" class="am-modal-bd">
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn data-am-modal-confirm">确定</span>
	    </div>
	  </div>
	</div>
	<script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
	<script type="text/javascript">
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