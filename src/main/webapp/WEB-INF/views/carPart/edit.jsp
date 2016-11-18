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
<script type="text/javascript" src="${ctx}/js/carPart/edit.js"></script>
<title>零部件添加/修改</title>
</head>
<body class="am-g">
	<div class="am-u-lg-7 am-u-md-10 am-margin-top">
		<form id="form-edit" class="am-form am-form-horizontal" enctype="multipart/form-data" method="post">
			<input type="hidden" name="id" id="id" value="${carPart.id}">
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">名称：</label>
				<div class="am-u-sm-10">
					<input name="name" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入商品名称" value="${carPart.name}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">编号：</label>
				<div class="am-u-sm-10">
					<input name="sn" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入商品编号" value="${carPart.sn}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">分类：</label>
				<div class="am-u-sm-10">
					<select name="type.id" style="width:300px" required="required">
					<c:forEach items="${types}" var="type">
					        <option  value="${type.id}"
					        	<c:if test="${carPart.type.id == type.id}">
				     			   selected="selected" 
								</c:if>
							>
								${type.name}
					        </option>
					</c:forEach>
			        </select>
		      	</div>
			</div>
			
			<div class="am-form-group am-form-file">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">零部件图片：</label>
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
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">中央仓库出库价：</label>
				<div class="am-u-sm-10">
					<input name="centreOutPrice" type="number"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入中央仓库出库价格" value="${carPart.centreOutPrice}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">零售价：</label>
				<div class="am-u-sm-10">
					<input name="salePrice" type="number"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入零售价" value="${carPart.salePrice}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">老化月份数：</label>
				<div class="am-u-sm-10">
					<input name="oldMonths" type="number"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入老化月份数" value="${carPart.oldMonths}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">型号：</label>
				<div class="am-u-sm-10">
					<input name="model" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入零部件型号" value="${carPart.model}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">计量单位：</label>
				<div class="am-u-sm-10">
					<select name="unit.id" style="width:300px" required="required">
					<c:forEach items="${units}" var="unit">
					        <option  value="${unit.id}"
					        	<c:if test="${carPart.unit.id == unit.id}">
				     			   selected="selected" 
								</c:if>
							>
								${unit.name}
					        </option>
					</c:forEach>
			        </select>
		      	</div>
			</div>
			
			<div class="am-form-group">
				<label  for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">备注：</label>
				<div class="am-u-sm-10">
					<textarea name="remark" rows="4" cols="16"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入备注">${carPart.remark}</textarea>
				</div>
			</div>
			
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
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