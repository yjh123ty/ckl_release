<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/WEB-INF/views/commons/head.jsp"%>
	<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
</head>
<body> 
<div class="am-u-lg-10 am-u-md-12 am-margin-top">
		<h3 style="text-align: center;">${item == null || item.id == null ? "添加" : "修改"}标题</h3>
		
		<form id="form-item" class="am-form am-form-horizontal" enctype="multipart/form-data" method="post">
			<input type="hidden" name="id" value="${item.id}">
			<input type="hidden" name="routeId" id="route-id" value="${route-id}${item.routeId==null ? routeId : item.routeId}">
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">标题名称：</label>
				<div class="am-u-sm-10">
					<input name="name" type="text"  id="doc-ipt-3-a" class="am-form-field" placeholder="请输入标题名称" value="${item.name}">
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">序号：</label>
				<div class="am-u-sm-10">
					<input name="no" type="number" maxlength="3" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入序号" value="${item.no}">
				</div>
			</div>
			<div class="am-form-group am-form-file">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">图片：</label>
				<div class="am-u-sm-3">
					<button type="button" class="am-btn am-btn-success am-btn-sm">
						<i class="am-icon-cloud-upload"></i> 选择要上传的文件
					</button>
					<input name="image" id="doc-form-file" type="file">
				</div>
				<div id="file-list" class="am-u-sm-7 am-u-end">没有选择任何文件</div>
			</div>

			<div class="am-form-group">
		      <label class="am-u-sm-2 am-form-label">文字:</label>
		      <div class="am-u-sm-10">
		      	<textarea name="text" class="" rows="5">${item.text}</textarea>
		      </div>
		    </div>
			
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="confirm" type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<input type="button" class="am-btn am-btn-success am-btn-sm" onclick="location.href='${ctx}/routeguide/index.do?routeId=${route-id}${item.routeId==null ? routeId : item.routeId}'"  value="返回"></input> 
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
   		var modal = $("#msg-modal");
	   $("#confirm").click(function(){
		   $("#form-item").ajaxSubmit({
		       url:       _ctx + "/routeguide/editItem.do", 
		       type:      "post",
		       data: {
		       },        
		       clearForm: false,     
		       resetForm: false,     
		       beforeSubmit: function(){
		       }, 
		       success: function(data){
		    	   if(isJsonObj(data)){
			    	   modal.find("#msg_content").html(data.msg);
			    	   modal.find(".data-am-modal-confirm").on("click", function(){
			    		   console.debug("alert 确定....");
				    	   if(data.success) {
				    		   location.href = _ctx + "/routeguide/index.do?routeId=${item.routeId==null ? routeId : item.routeId}";
				    	   }
			    	   });
			    	   modal.modal();
		    	  }else {
		    		  modal.find("#msg_content").html("服务器异常");
		    		  modal.modal();
		    	  }
		       } 
		   });
	   });
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