<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<script type="text/javascript" src="${ctx}/js/travel_note/template_travel_note_content_edit.js"></script>
<title>游记管理</title>
</head>
<body class="am-g">
	<input id="route-id" type="hidden" value="${routeId}">
	<form id="template-travel-note-form" class="am-form am-form-horizontal" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${content.id}">
		<input type="hidden" name="travelNoteId" value="${noteId==null ? content.travelNoteId : noteId}">
		<div class="am-form-group">
 			<label class="am-form-label am-u-sm-3">
 				内容类型：
 			</label>
 			<div class="am-u-sm-5 am-u-end">
 				<label class="am-radio-inline">
 					<input name="type" type="radio" value="1"
 						<c:if test="${content.type == 1||content.type != 2}"> 
 						checked="checked"
 						</c:if>
 					>文字
				</label>
 				<label class="am-radio-inline"> 
 					<input name="type" type="radio" value="2"
 					<c:if test="${content.type == 2}"> 
 						checked="checked"
 					</c:if>
 					>图片
				</label>
 			</div>
 		</div>
 		
 		<div class="am-form-group">
 			<label class="am-form-label am-u-sm-3">
 				内容排序：
 			</label>
 			<div class="am-u-sm-5 am-u-end">
 				<input name="order" type="number" value="${content.order}" required="required" min="0">
 			</div>
 		</div>
 			
	 		<div id="imgContent" class="am-form-group am-form-file" style="
		 		<c:if test="${content.type == 1 || content.type != 2}">
			 		display: none;
		 		</c:if>
	 		">
					<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">图片：</label>
					<div class="am-u-sm-3">
						<button type="button" class="am-btn am-btn-success am-btn-sm">
							<i class="am-icon-cloud-upload"></i> 选择要上传的文件
						</button>
						<input name="image" id="doc-form-file" type="file">
					</div>
					<div id="file-list" class="am-u-sm-6 am-u-end">没有选择任何文件</div>
			</div>
		
		
		<div id="textContent" class="am-form-group am-form-file" style="
			<c:if test="${content.type == 2}">
			 		display: none;
		 	</c:if>
		">
				<label for="doc-ipt-3-a" class="am-u-sm-3 am-form-label">文字：</label>
				<div class="am-u-sm-5 am-u-end">
					<textarea rows="3" cols="16" name="content">${content.content}</textarea>
				</div>
		</div>
		
		<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-3">
					<input id="confirm" type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<input type="button" class="am-btn am-btn-success am-btn-sm" onclick="location.href='${ctx}/templatetravelnote/index.do'"  value="返回"></input> 
				</div>
		</div>
		
	</form>

	<div class="am-modal am-modal-alert" tabindex="-1" id="msg-modal">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">温馨提示：</div>
			<div id="msg_content" class="am-modal-bd"></div>
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