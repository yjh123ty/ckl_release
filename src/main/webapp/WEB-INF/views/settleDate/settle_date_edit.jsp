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
	
<script type="text/javascript" src="${ctx}/js/settle/settle_date.js"></script>

</head>
<body>
	<form id="settleEditForm" class="am-form am-form-horizontal">
		<div id="content" style='margin: 20px; padding: 20px'>
			<input type="hidden" name="id" value="1">
			
			<div class="am-form-group">
				<span>每月工资结算日：</span> 
				<select id="selectDay" name="settleDay" required="required" style="width:120px">
					<option value="-1">--请选择--</option>
					<c:forEach begin="1" end="30" step="1" var="day">
						<option value="${day}">${day}</option>
					</c:forEach>
				</select>
			</div>
			
<!-- 			 onblur="disabledSelect(起到一个禁用的前端效果的作用,具体逻辑处理交给后端完成)" -->
			<div class="am-form-group">
				<label class="am-checkbox-inline"> 
					<input name="isFinal" type="checkbox" value="true"
						<c:if test="${settleDate.isFinal == true}">
				     			   checked="checked" 
						</c:if>
					> 每月最后一日
				</label>
			</div>
			
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="confirm" type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<input type="button" class="am-btn am-btn-success am-btn-sm" onclick="location.href='${ctx}/settleDate/index.do'"  value="返回"></input>
				</div>
			</div>
			
		</div>
	</form>
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
						    	   if(data.success) {
						    		   location.href = _ctx + "/settleDate/index.do";
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