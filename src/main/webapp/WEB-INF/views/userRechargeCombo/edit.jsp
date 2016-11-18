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
<script type="text/javascript" src="${ctx}/js/userRechargeCombo/edit.js"></script>
<title>用户充值套餐添加/修改</title>
</head>
<body class="am-g">
	<div class="am-u-lg-7 am-u-md-10 am-margin-top">
		<form id="form-edit" class="am-form am-form-horizontal" method="post">
			<input type="hidden" name="id" id="id" value="${rechargeCombo.id}">
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">套餐名称：</label>
				<div class="am-u-sm-10">
					<input name="name" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入套餐名称" value="${rechargeCombo.name}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">充值金额：</label>
				<div class="am-u-sm-10">
					<input name="price" type="number"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入充值金额" value="${rechargeCombo.price}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">返点比例：</label>
				<div class="am-u-sm-10">
					<input type="text" class="am-form-field" readonly="readonly" value="10%">
				</div>
			</div>
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">发放时间：</label>
				<div class="am-u-sm-10">
					<input type="text" class="am-form-field" readonly="readonly" value="实时">
				</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">赠送服务：</label>
				<div class="am-u-sm-10">
					<textarea name="content" cols="16" rows="4">${rechargeCombo.content}</textarea>
				</div>
			</div>
			
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="userRechargeCombo-confirm" 
						type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<a class="am-btn am-btn-success am-btn-sm" href="${ctx}/rechargeCombo/index.do">返回</a>
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