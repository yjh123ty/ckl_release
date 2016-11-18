<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<script type="text/javascript" src="${ctx}/js/route/route_edit.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>路线添加/修改</title>
</head>
<body class="am-g">
	<div class="am-u-lg-10 am-u-md-12 am-margin-top">
		<form id="form-route-edit" class="am-form am-form-horizontal" enctype="multipart/form-data" method="post">
			<input type="hidden" name="id" id="route-id" value="${route.id}">
			<c:if test="${route.id == null}">
				<div class="am-form-group">
					<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">线路：</label>
					<div class="am-u-sm-10">
						<select id="line-select" name="template.id">
						</select> <span class="am-form-caret"></span>
					</div>
				</div>
			</c:if>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">路线名称：</label>
				<div class="am-u-sm-10">
					<input name="name" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入路线名称" value="${route.name}" required="required">
				</div>
			</div>
			<div class="am-form-group am-form-file">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">路线图片：</label>
				<div class="am-u-sm-2">
					<button type="button" class="am-btn am-btn-success am-btn-sm">
						<i class="am-icon-cloud-upload"></i> 选择要上传的文件
					</button>
					<input name="image" id="doc-form-file" type="file">
				</div>
				<div id="file-list" class="am-u-sm-8 am-u-end">没有选择任何文件</div>
			</div>

			<div class="am-form-group">
		      <label class="am-u-sm-2 am-form-label">线路详情:</label>
		      <div class="am-u-sm-10">
		      	<textarea name="intro" class="" rows="5" readonly="readonly">${route.intro}</textarea>
		      </div>
		    </div>
		    <div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">路线里程：</label>
				<div class="am-u-sm-5">
					<input name="distance" type="number"  id="doc-ipt-3-a" class="am-form-field" value="${route.distance}" readonly="readonly">
				</div>
				<div class="am-u-sm-1 am-u-end">
					千米
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">游玩天数：</label>
				<div class="am-u-sm-5">
					<input name="days" type="number"  id="doc-ipt-3-a" class="am-form-field" value="${route.days}" required="required">
				</div>
				<div class="am-u-sm-1 am-u-end">
					天
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">适合人群：</label>
				<div class="am-u-sm-10" id="route-suit-container">
					<c:forEach items="${routeSuit}" var="item">
						<label class="am-checkbox-inline">
							<input type="checkbox" value="${item.identity}"
							<c:forEach items="${route.suits}" var="suit" >
								<c:if test="${suit.type == item.identity}">
									checked="checked" minchecked="1"
									</c:if>
								</c:forEach>
							> ${item.name}
						</label> 
					</c:forEach>
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">旅游费用：</label>
				<div class="am-u-sm-5">
					<input name="cost" type="number"  id="doc-ipt-3-a" class="am-form-field" value="${route.cost}" required="required">
				</div>
				<div class="am-u-sm-1 am-u-end">
					元
				</div>
			</div>
			
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="confirm" type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<input type="button" class="am-btn am-btn-success am-btn-sm" onclick="location.href='${ctx}/route/index.do'"  value="返回"></input> 
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