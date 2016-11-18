<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>每月工资结算日设置</title>
<!-- 服务端引入，可以访问webinf的资源 -->
<%@include file="/WEB-INF/views/commons/head.jsp"%>
<%@include file="/WEB-INF/views/commons/head_easyui.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>

	<div class="am-g" style='margin: 1px; padding: 10px'>
		<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="/performance/index.do">员工绩效</a>
		&nbsp;
		<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="/formula/index.do">绩效公式</a>
		&nbsp;
		<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="/performance/index.do?cmd=salary">员工工资表</a>
		&nbsp;
		<a class="am-btn am-btn-default am-btn-xs" style="border-radius: 300px;" href="/serviceRule/index.do">员工分数生成规则</a>
		&nbsp;
		<a class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" href="/settleDate/index.do">设置工资结算日</a>
	</div>
	<hr>
	
</head>
<body>
		<div id="content" style='margin: 20px; padding: 20px'>
			<div class="am-form-group">
				<span>每月工资结算日：</span> 
				
				&emsp;
				<b><font style="color:red">${settleDate.settleDay}日</font></b>
				
				
			</div>
			
			<div class="am-form-group">
				<div class="am-g">
					<a href="${ctx}/settleDate/index.do?cmd=update" 
						class="am-btn am-btn-success am-btn-xs" style="border-radius: 300px;" role="button">重置</a>
				</div>
			</div>
			
		</div>
</body>

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
				/* 
				3.验证表单
				4.使用ajax的方式提交
				5.成功返回上级界面刷新
				6.失败直接提示 */
				var form = $("#settleEditForm");
				var data = {};
				form.ajaxSubmit( {    
				       url:       _ctx + "/settleDate/edit.do", 
				       type:      "post",
				       data: data,        
				       clearForm: true,     
				       resetForm: true,     
				       beforeSubmit: function(){
				       }, 
				       success: function(data){
		    	           var modal = $('#msg-modal');
				    	   if(typeof(data) == "object" &&  Object.prototype.toString.call(data).toLowerCase() == "[object object]" && !data.length){
					    	   modal.find("#msg_content").text(data.msg);
					    	   
					    	   modal.find(".data-am-modal-confirm").on("click", function(){
					    		   console.debug("alert 确定....");
						    	   if(data.success) {
						    		   location.href = _ctx + "/performance/index.do";
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
</html>