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
<script type="text/javascript" src="${ctx}/js/carPartType/edit.js"></script>
<title>零部件分类添加/修改</title>
</head>
<body class="am-g">
	<div class="am-u-lg-7 am-u-md-10 am-margin-top">
		<form id="form-edit" class="am-form am-form-horizontal" enctype="multipart/form-data" method="post">
			<input type="hidden" name="id" id="id" value="${carPartType.id}">
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">名称：</label>
				<div class="am-u-sm-10">
					<input name="name" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入商品名称" value="${carPartType.name}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">排序编号：</label>
				<div class="am-u-sm-10">
					<input name="order" type="number"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入分类排序编号" value="${carPartType.order}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">分类描述：</label>
				<div class="am-u-sm-10">
					<textarea name="intro" rows="4" cols="16"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入备注">${carPartType.intro}</textarea>
				</div>
			</div>
			
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="car-type-confirm" 
						type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<a class="am-btn am-btn-success am-btn-sm" href="${ctx}/carPartType/index.do">返回</a>
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
</body>
</html>