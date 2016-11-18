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
<script type="text/javascript" src="${ctx}/js/carPartStockIncome/station_edit.js"></script>
<title>零部件分类添加/修改</title>
</head>
<body class="am-g">
	<div class="am-u-lg-7 am-u-md-10 am-margin-top">
		<form id="form-edit" class="am-form am-form-horizontal" enctype="multipart/form-data" method="post">
		
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">零部件编码：</label>
				<div class="am-u-sm-10">
					<input name="name" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入商品名称" value="${carPartStockIncome.name}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">服务站仓库：</label>
				<div class="am-u-sm-10">
					<select name="depot.id" style="width:300px">
						<c:forEach items="${depots}" var="depot">
						        <option  value="${depot.id}"
						        	<c:if test="${carPartStockIncome.depot.id == depot.id}">
					     			   selected="selected" 
									</c:if>
								>
									${depot.name}
						        </option>
						</c:forEach>
			        </select>
				</div>
			</div>
			
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="car-part-stock-income-confirm" 
						type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<a class="am-btn am-btn-success am-btn-sm" href="${ctx}/carPartStockIncome/index.do?cmd=station">返回</a>
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