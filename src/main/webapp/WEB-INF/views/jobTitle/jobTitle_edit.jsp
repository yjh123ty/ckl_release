<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>职位添加</title>

</head>
<body class="am-g">
	<div class="am-u-lg-7 am-u-md-10 am-margin-top">
		<form id="editForm" class="am-form am-form-horizontal">
			<input type="hidden" name="id" value="${jobTitle.id}">
			
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">岗位类别：</label>
				<div class="am-u-sm-10">
					<input style="width:300px" name="name" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入岗位类别" value="${jobTitle.name}">
				</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">奖金系数：</label>
				<div class="am-u-sm-10">
					<input style="width:300px" name="ratio" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入奖金系数(%)" value="${jobTitle.ratio}">
				</div>
			</div>
			
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="confirm" type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<input type="button" class="am-btn am-btn-success am-btn-sm" onclick="location.href='${ctx}/jobTitle/index.do'"  value="返回"></input> 
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
		$(function(){
			$("#confirm").click(function(){
				/* 1.封装额外的参数
					放入参数对象中
				3.验证表单
				4.使用ajax的方式提交
				5.成功返回上级界面刷新
				6.失败直接提示 */
				var form = $("#editForm");
				var data = {};
				form.ajaxSubmit( {    
				       url:       _ctx + "/jobTitle/edit.do", 
				       type:      "post",
				       data: data,        
				       clearForm: true,     
				       resetForm: true,     
				       beforeSubmit: function(){
				       }, 
				       success: function(data){
		    	           var modal = $('#msg-modal');
				    	   if(isJsonObj(data)){
					    	   modal.find("#msg_content").text(data.msg);
					    	   
					    	   modal.find(".data-am-modal-confirm").on("click", function(){
						    	   if(data.success) {
						    		   location.href = _ctx + "/jobTitle/index.do";
						    	   }
					    	   });
					    	   modal.modal();
				    	  }else {
				    		  modal.find("#msg_content").text("服务器异常");
				    		  modal.modal();
				    	  }
				       } 
				   });
			});
		});
	</script>
</body>
</html>