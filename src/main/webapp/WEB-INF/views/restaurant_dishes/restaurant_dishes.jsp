<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<title>饭店菜品信息管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/js/restaurant_dishes/restaurant_dishes.js"></script>
</head>
<body>
	<!-- 饭店菜品工具条 -->
	<div class="am-g am-margin-left" style="padding: 8px;">
		<div class="am-g">
			<div class="am-u-sm-3">
				<a href="${ctx}/restaurantdishes/index.do?cmd=save"
					class="am-btn am-btn-success am-btn-xs"
					style="border-radius: 300px;">添&nbsp;加</a>
			</div>
		</div>
		
		<form id="restaurantdishes-search-form" class="am-form">
			<div class="am-form-group">
				<label class="am-u-sm-1 am-form-label">关键词：</label>
				<div class="am-u-sm-3 am-u-end">
					<div class="am-input-group">
						<input name="keyword" type="text" class="am-form-field"
							placeholder="请输入菜品名称"> <span class="am-input-group-btn">
							<button data-cmd="do-search" class="am-btn am-btn-success"
								type="button">
								<span class="am-icon-search"></span>
							</button>
						</span>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- 饭店菜品表格 -->
	<table id="restaurantdishes-datagrid" style="width: 100%;	height: auto; min-height: 400px;">
	</table>
	
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
</body>
</html>