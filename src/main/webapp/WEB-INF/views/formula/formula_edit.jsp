<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绩效公式添加/编辑</title>
</head>
<body class="am-g">
	<div class="am-u-lg-7 am-u-md-10 am-margin-top">
		<form id="formulaEditForm" class="am-form am-form-horizontal">
			<input type="hidden" name="id" value="${formula.id}">
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">部门：</label>
				<div class="am-u-sm-10">
					<select name="jobTitle.dept.id" style="width:300px" required="required">
						<c:forEach items="${depts}" var="deptVal">
					        <option  value="${deptVal.id}"
					        <c:if test="${formula.jobTitle.dept.id == deptVal.id}">
				     			   selected="selected" 
							</c:if>
					        >${deptVal.name}</option>
						</c:forEach>
			        </select>
		      	</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">职位：</label>
				<div class="am-u-sm-10">
					<select name="jobTitle.id" style="width:300px" required="required">
						<c:forEach items="${jobs}" var="jobVal">
					        <option  value="${jobVal.id}"
					        <c:if test="${formula.jobTitle.id == jobVal.id}">
				     			   selected="selected" 
							</c:if>
					        >${jobVal.name}</option>
						</c:forEach>
			        </select>
		      	</div>
			</div>
			
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">员工分数：</label>
				<div class="am-u-sm-10">
					<input name="pointCondition" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入" value="${formula.pointCondition}">
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">奖金：</label>
				<div class="am-u-sm-10">
					<input name="bouns" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入" value="${formula.bouns}">
				</div>
			</div>
    		
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="confirm" type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<input type="button" class="am-btn am-btn-success am-btn-sm" onclick="location.href='${ctx}/formula/index.do'"  value="返回"></input> 
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
				/* 
				3.验证表单
				4.使用ajax的方式提交
				5.成功返回上级界面刷新
				6.失败直接提示 */
				var form = $("#formulaEditForm");
				var data = {};
				form.ajaxSubmit( {    
				       url:       _ctx + "/formula/edit.do", 
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
						    		   location.href = _ctx + "/formula/index.do";
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