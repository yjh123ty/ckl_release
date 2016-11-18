<%-- 角色授权给员工  --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 导入公共的头文件  --%>
<%@ include file="/WEB-INF/views/commons/head.jsp"%>
<%@ include file="/WEB-INF/views/commons/head_amazeui.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色添加</title>
</head>
<body class="am-g">
	<div class="am-u-lg-10 am-u-md-12 am-margin-top">
		<form id="roleGrantForm" class="am-form am-form-horizontal">
			<input type="hidden" name="id" value="${emp.id}">
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">用户姓名：</label>
				<div class="am-u-sm-10">
					<input name="realName" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" placeholder="员工姓名" value="${emp.realName}" required>
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">手机号：</label>
				<div class="am-u-sm-10">
					<input name="mobile" type="number" required="required" id="doc-ipt-3-a" class="am-form-field" pattern="^[1][3,4,5,8][0-9]{9}$" placeholder="员工手机号码" value="${emp.mobile}"  required>
				</div>
			</div>
			<c:if test="${emp.id == null}">
				<div class="am-form-group">
					<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">密码：</label>
					<div class="am-u-sm-10">
						<input id="new-password" name="password" type="password" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入密码" required>
					</div>
				</div>
				<div class="am-form-group">
					<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">确认密码：</label>
					<div class="am-u-sm-10">
						<input name="confirmPassword" type="password" id="doc-ipt-3-a" class="am-form-field" placeholder="请确认密码" data-equeal-to="new-password" required="required">
					</div>
				</div>
			</c:if>
			<div class="am-form-group">
				<label for="" class="am-u-sm-2 am-form-label">角色：</label>
				<div class="am-u-sm-10">
					<c:forEach items="${roles}" var="role" varStatus="status">
						<label class="am-checkbox-inline"> 
							<input name="role.id" type="radio" value="${role.id}"
								<c:if test="${status.index == 0}">
								required="required"
								</c:if>
								<c:if test="${emp.role.id == role.id}">
									checked="checked"
								</c:if>
							> ${role.name}
						</label>
					</c:forEach>
				</div>
			</div>
			<div class="am-form-group">
				<label for="" class="am-u-sm-2 am-form-label">状态:</label>
				<div class="am-u-sm-10">
						<label class="am-checkbox-inline">
					        <input type="radio" name="status" value="0" 
					        	<c:if test="${emp.status == 0 || emp.status == null}">
									checked="checked"
								</c:if>
							>
					       	启用
					     </label>
						<label class="am-checkbox-inline">
					        <input type="radio" name="status" value="-1" 
					        	<c:if test="${emp.status == -1}">
									checked="checked"
								</c:if>
							>
					       	禁用
					     </label>
				</div>
			</div>
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="confirm" type="button"
						class="am-btn am-btn-success am-btn-sm" value="确定"></input> <input
						type="button" class="am-btn am-btn-success am-btn-sm"
						onclick="location.href='${ctx}/role/index.do?cmd=grantlist'"
						value="返回"></input>
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
			var form = $("#roleGrantForm");
			form.validator({});
			$("#confirm").click(function(){
				/* 1.封装额外的参数
					获取用户选择的checkdid
					封装成menus的参数 menus[i].id=xxxx
					放入参数对象中
				3.验证表单
				4.使用ajax的方式提交
				5.成功返回上级界面刷新
				6.失败直接提示 */
				// 表单提交的额外数据
				var data = {};
				form.ajaxSubmit( {    
				       url:       _ctx + "/admin/edit.do", 
				       type:      "post",
				       data: data,        
				       resetForm: true,     
				       beforeSubmit: function(){
				    	   return form.validator('isFormValid');
				       }, 
				       success: function(data){
		    	           var modal = $('#msg-modal');
				    	   if(typeof(data) == "object" &&  Object.prototype.toString.call(data).toLowerCase() == "[object object]" && !data.length){
					    	   modal.find("#msg_content").html(data.msg);
					    	   
					    	   modal.find(".data-am-modal-confirm").on("click", function(){
						    	   if(data.success) {
						    		   location.href = _ctx + "/role/index.do?cmd=grantlist";
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
		});
	</script>
</body>
</html>