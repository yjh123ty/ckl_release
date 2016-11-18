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
		<form id="roleEditForm" class="am-form am-form-horizontal">
			<input type="hidden" name="id" value="${role.id}">
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">角色名称：</label>
				<div class="am-u-sm-10">
					<input name="name" type="text" required="required" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入角色名称" value="${role.name}">
				</div>
			</div>
			<div class="am-form-group">
				<label for="doc-ipt-3-a" class="am-u-sm-2 am-form-label">角色描述：</label>
				<div class="am-u-sm-10">
					<input name="intro" type="text" id="doc-ipt-3-a" class="am-form-field" placeholder="请输入角色描述" value="${role.intro}">
				</div>
			</div>
			<c:forEach items="${menus}" var="menu">
				<div class="am-form-group">
					 <label for="" class="am-u-sm-2 am-form-label">${menu.name}：</label>
					 <div class="am-u-sm-10">
						<label class="am-checkbox-inline"> 
							<input type="checkbox" value="${menu.id}"
								<c:forEach items="${role.menus}" var="m">
									<c:if test="${m.id == menu.id}">
										checked="checked"
									</c:if>
								</c:forEach>
							>
							${menu.name}
						</label>
						<c:forEach items="${menu.children}" var="child">
							<label class="am-checkbox-inline"> <input type="checkbox" value="${child.id}"
								<c:forEach items="${role.menus}" var="m">
									<c:if test="${m.id == child.id}">
										checked="checked"
									</c:if>
								</c:forEach>
							>${child.name}
							</label>
						</c:forEach>
					</div>
	    		</div>
			</c:forEach>
    		
			<div class="am-form-group">
				<div class="am-u-sm-10 am-u-sm-offset-2">
					<input id="confirm" type="button" class="am-btn am-btn-success am-btn-sm" value="确定"></input> 
					<input type="button" class="am-btn am-btn-success am-btn-sm" onclick="location.href='${ctx}/role/index.do'"  value="返回"></input> 
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
			var form = $("#roleEditForm");
			// 设置表单验证
			form.validator();
			$("#confirm").click(function(){
				/* 1.封装额外的参数
					获取用户选择的checkdid
					封装成menus的参数 menus[i].id=xxxx
					放入参数对象中
				3.验证表单
				4.使用ajax的方式提交
				5.成功返回上级界面刷新
				6.失败直接提示 */
				var data = {};
				var selects = $("input:checked");
				selects.each(function(i){
					data["menus["+ i +"].id"] = this.value;
				});
				form.ajaxSubmit( {    
				       url:       _ctx + "/role/edit.do", 
				       type:      "post",
				       data: data,        
				       clearForm: true,     
				       resetForm: true,     
				       beforeSubmit: function(){
				    	   // 判断表单验证是否成功
				    	   return form.validator('isFormValid');
				       }, 
				       success: function(data){
		    	           var modal = $('#msg-modal');
				    	   if(typeof(data) == "object" &&  Object.prototype.toString.call(data).toLowerCase() == "[object object]" && !data.length){
					    	   modal.find("#msg_content").html(data.msg);
					    	   modal.find(".data-am-modal-confirm").on("click", function(){
						    	   if(data.success) {
						    		   location.href = _ctx + "/role/index.do";
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