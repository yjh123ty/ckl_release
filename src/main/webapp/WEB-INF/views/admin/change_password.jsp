<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<script type="text/javascript" src="${ctx}/js/admin/change_password.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
</head>
<body class="am-g">
	<div class="am-u-lg-10 am-u-md-12 am-margin-top">
		<form id="form-admin-change-password" class="am-form am-form-horizontal" enctype="multipart/form-data" method="post">
			<c:if test="${route.id == null}">
				<div class="am-form-group">
					<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">旧密码：</label>
					<div class="am-u-sm-10">
						<input name="oldPassword" type="password"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入旧密码" required>
					</div>
				</div>
			</c:if>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">新密码：</label>
				<div class="am-u-sm-10">
					<input id="new-password" name="newPassword" type="password"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入新密码" pattern="^(\w){6,20}$" required>
				</div>
			</div>

			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">确认新密码：</label>
				<div class="am-u-sm-10">
					<input type="password"  id="doc-ipt-3-a" class="am-form-field" placeholder="请与上面输入的值一致" required="required" data-equeal-to="new-password">
				</div>
			</div>
			
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="confirm" type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<input type="button" class="am-btn am-btn-success am-btn-sm am-back" value="返回"></input> 
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
</body>
</html>